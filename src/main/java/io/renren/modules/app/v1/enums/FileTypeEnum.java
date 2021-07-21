package io.renren.modules.app.v1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 汪少
 * @date 2021/4/7 16:06
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    APK("apk", "apk完整格式安装包"),
    WGT("wgt", "wgt热更新安装包"),
    JPEG("jpeg", "JPEG"),
    JPG("jpg", "jpg"),
    PNG("png", "png");

    private String type;
    private String desc;
}
