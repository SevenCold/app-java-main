package io.renren.modules.app.v1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 汪少
 * @date 2021/4/7 16:06
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    MANAGER(3, "APP管理员");

    private long roleId;
    private String desc;
}
