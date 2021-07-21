package io.renren.modules.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{group}")
@Component
@Slf4j
public class WebSocket {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Map，用来存放每个客户端分组对应的WebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 分组
     */
    private String group = "";

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "group") String group, Session session) {
        this.group = group;
        this.session = session;
        //加入set中
        webSocketMap.put(group, this);
        //添加在线人数
        addOnlineCount();
        log.info("新连接接入。分组为【{}】，当前在线人数为：【{}】。", group, getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (StringUtils.isNotEmpty(group)) {
            //从Map中删除
            webSocketMap.remove(group);
            //在线数减1
            subOnlineCount();
            log.info("有连接关闭, 当前在线人数为：【】。", group, getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.info("收到客户端消息，分组为【】,消息为【】。", group, message);
    }

    /**
     * 暴露给外部的群发
     *
     * @param message
     * @throws IOException
     */
    public static void sendInfo(String message) throws IOException {
        sendAll(message);
    }

    public static void sendToGroup(String message, String group) throws IOException {
        webSocketMap.forEach((key, webSocket) -> {
            if (key.contains(",")) {
                String[] groups = key.split(",");
                if (Arrays.asList(groups).contains(group)) {
                    sendMessage(webSocket, message, group);
                }
            } else {
                if (key.equals(group)) {
                    sendMessage(webSocket, message, group);
                }
            }
        });
    }

    /**
     * 群发
     *
     * @param message
     */
    private static void sendAll(String message) {
        webSocketMap.forEach((key, webSocket) -> {
            //群发
            sendMessage(webSocket, message, key);
        });
    }

    private static void sendMessage(WebSocket webSocket, String message, String group) {
        try {
            webSocket.sendMessage(message);
        } catch (IOException e) {
            log.error("服务端发送消息失败，发送对象分组为【{}】", group);
            log.error("异常信息为：{}", e);
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("服务端Websocket发生异常，异常信息为：{}", error);
        error.printStackTrace();
    }

    /**
     * 减少在线人数
     */
    private void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    /**
     * 添加在线人数
     */
    private void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    /**
     * 当前在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        //获取session远程基本连接发送文本消息
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
