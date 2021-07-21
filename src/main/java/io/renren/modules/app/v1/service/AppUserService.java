package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.AppUserEntity;

import java.util.List;

/**
 * app用户服务
 * @author kang
 */
public interface AppUserService extends IService<AppUserEntity> {

	/**
	 * 通过手机号查询用户是否存在
	 * @param mobile 手机号
	 * @return 查询出的用户信息，不存在即为null
	 */
	AppUserEntity queryByMobile(String mobile);

	/**
	 * 保存用户
	 * @param user 用户信息
	 */
	void saveUser(AppUserEntity user);

	/**
	 * 根据角色id查询所有用户cid
	 * @param roleId 角色的id
	 * @return 用户推送设备id
	 */
	List<String> getCidListByRole(long roleId);

	/**
	 * 更新用户信息和角色信息
	 * @param userEntity 用户信息
	 */
	void updateAppInfoAndRole(AppUserEntity userEntity);
}
