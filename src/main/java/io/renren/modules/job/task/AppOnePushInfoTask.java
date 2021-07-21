package io.renren.modules.job.task;

import com.alibaba.fastjson.JSONObject;
import io.renren.modules.app.v1.entity.McRealMonitor1minEntity;
import io.renren.modules.app.v1.service.AppBusService;
import io.renren.modules.job.enums.WebsocketGroupEnum;
import io.renren.modules.websocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wk
 */
@Component("appOnePushTask")
@Slf4j
public class AppOnePushInfoTask implements ITask {

    @Autowired
    private AppBusService busService;

    @Override
    public void run(String params) {
        try {
            Map<String, Object> map = new HashMap<>(2);
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.MINUTE, -1);
            map.put("startTime", df.format(calendar.getTime()));
            map.put("endTime", df.format(calendar.getTime()));
            List<McRealMonitor1minEntity> realArt = busService.getRealArt(map);
            // 一分钟只有一条记录，不符合则不发送
            if (!CollectionUtils.isEmpty(realArt) && realArt.size() == 1) {
                WebSocket.sendToGroup(JSONObject.toJSONString(realArt.get(0)),
                        WebsocketGroupEnum.OneMinute.getValue());
            }
        } catch (IOException e) {
            log.error("Websocket发送消息发送错误，【{}】", e);
        }
    }
}
