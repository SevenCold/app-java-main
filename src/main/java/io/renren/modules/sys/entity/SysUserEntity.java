/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.annotations.Expose;
import io.renren.common.annotation.Mobile;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.RegistGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.app.v1.group.AppRegistGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * APP推送服务器管理的设备唯一标识 clientid
	 */
	private String cid;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class, RegistGroup.class})
	private String username;

	/**
	 * 密码
	 */
	@Expose(deserialize = false)
	@NotBlank(message="密码不能为空", groups = {AddGroup.class, RegistGroup.class, AppRegistGroup.class})
	private String password;

	/**
	 * 盐
	 */
	@Expose(deserialize = false)
	private String salt;

	/**
	 * 邮箱
	 */
	@Expose(deserialize = false)
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	@Expose(deserialize = false)
	@NotBlank(message="手机号不能为空", groups = {AppRegistGroup.class})
	@Mobile(groups = {AppRegistGroup.class})
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private int status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 创建者ID
	 */
	@Expose(deserialize = false)
	private Long createUserId;

	/**
	 * 创建时间
	 */
	@Expose(deserialize = false)
	private Date createTime;

	/**
	 * token失效时间
	 */
	@TableField(exist=false)
	private String token;

	@TableField(exist=false)
	private String oldToken;

	/**
	 * 用户头像地址
	 */
	private String avatar;

	/**
	 * 昵称
	 */
	private String nickName;

}
