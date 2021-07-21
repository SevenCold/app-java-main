/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.enums.UserStatusEnum;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.VO.SysUserVo;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.SysLoginForm;
import io.renren.modules.sys.group.SysLoginGroup;
import io.renren.modules.sys.service.SysCaptchaService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {

		//表单校验
		ValidatorUtils.validateEntity(form, SysLoginGroup.class);
		//用户信息
		SysUserEntity user = sysUserService.queryByMobile(form.getMobile());

		R validateResult = validateAccount(user, form.getPassword());
		if (validateResult != null) {
			return validateResult;
		}

		//生成token，并保存到数据库
		SysUserEntity userEntity = sysUserTokenService.createToken(user);
		SysUserVo sysUserVo = new SysUserVo();
		BeanUtil.copyProperties(userEntity, sysUserVo);
		Map<String, Object> res = new HashMap<>();
		res.put("data", sysUserVo);

		return R.ok(res);
	}

	private R validateAccount(SysUserEntity user, String password) {
		//账号不存在、密码错误
		if(user == null) {
			return ErrorMsgEnum.MOBILE_NOT_EXIST.getR();
		}
		if (!new Sha256Hash(password, user.getSalt()).toHex().equals(user.getPassword())) {
			return ErrorMsgEnum.MOBILE_PWD_ERROR.getR();
		}
		if(user.getStatus() == UserStatusEnum.DISABLE.getValue()){
			return ErrorMsgEnum.ACCOUNT_DISABLE.getR();
		}
		if(user.getStatus() == UserStatusEnum.NOT_ENABLED.getValue()){
			return ErrorMsgEnum.ACCOUNT_NOT_ENABLE.getR();
		}
		return  null;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		SysUserEntity user = getUser();
		sysUserTokenService.logout(user.getToken(), user.getUserId());
		return R.ok();
	}
	
}
