/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.form;

import io.renren.common.annotation.Mobile;
import io.renren.modules.app.v1.entity.ClientInfo;
import io.renren.modules.app.v1.group.AppLoginGroup;
import io.renren.modules.sys.group.SysLoginGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class SysLoginForm {
    private String username;

    @NotBlank(message="密码不能为空", groups = {AppLoginGroup.class, SysLoginGroup.class})
    private String password;
    private String captcha;
    private String uuid;

    @NotBlank(message="手机号不能为空", groups = {AppLoginGroup.class, SysLoginGroup.class})
    @Mobile(groups = {AppLoginGroup.class})
    private String mobile;

    @NotNull(message = "设备标识不能为空", groups = {AppLoginGroup.class})
    private ClientInfo clientInfo;


}
