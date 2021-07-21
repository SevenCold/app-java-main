package io.renren.modules.job.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebsocketGroupEnum {

    OneSecond("1", "一秒钟发送一次"),
    ThreeSecond("3", "三秒钟发送一次"),
    FiveSecond("5", "五秒钟发送一次"),
    OneMinute("2", "一分钟发送一次")
    ;
    private String value;
    private String desc;
}
