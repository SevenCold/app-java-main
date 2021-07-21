package io.renren.modules.app.v1.entity;

import lombok.Data;

/**
 * 推送客户端标识信息
 * 详细说明见网址： https://www.html5plus.org/doc/zh_cn/push.html#plus.push.ClientInfo
 * @author 汪少
 * @date 2021/4/7 9:51
 */
@Data
public class ClientInfo {

    /**
     * 推送通道标识
     * 目前支持以下推送通道：
     *  1."igexin" - 表示个推推送
     *  2."mipush" - 表示小米推送
     *  3."unipush" - 表示DCloud UniPush
     */
    private String id;

    /**
     * 设备令牌（iOS设备唯一标识），用于APNS服务推送中标识设备的身份
     *  1.Android - 2.2+ (支持) :
     *      设备的唯一标识号，通常与clientid值一致。
     *  2.iOS - 4.5+ (支持) :
     *      设备的DeviceToken值，向APNS服务器发送推送消息时使用。
     */
    private String token;

    /**
     * 推送服务令牌（设备唯一标识），用于标识推送信息接收者身份
     */
    private String clientid;

    /**
     * 第三方推送服务的应用标识
     */
    private String appid;

    /**
     * 第三方推送服务器的应用键值
     */
    private String appkey;
}
