package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.enums.UserStatusEnum;
import io.renren.modules.app.v1.dao.UserDao;
import io.renren.modules.app.v1.entity.AppUserEntity;
import io.renren.modules.app.v1.service.AppNoticeService;
import io.renren.modules.app.v1.service.AppUserService;
import io.renren.modules.app.v1.utils.NoticeUtils;
import io.renren.modules.sys.form.AppNoticeForm;
import io.renren.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * app用户服务实现类
 * @author kang
 */
@Service("userService")
public class AppUserServiceImpl extends ServiceImpl<UserDao, AppUserEntity> implements AppUserService {

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private AppNoticeService appNoticeService;

	@Override
	public AppUserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<AppUserEntity>().eq("mobile", mobile));
	}

	@Override
	public void saveUser(AppUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
	}

	@Override
	public List<String> getCidListByRole(long roleId) {
		return this.baseMapper.getCidsByRoleId(roleId);
	}

	@Override
	public void updateAppInfoAndRole(AppUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		} else {
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}

		// 查看用户状态是否为 未启用 -> 正常，判断是否通知用户 账号审批完毕
		AppUserEntity existedUser = this.getById(user.getUserId());
		if (UserStatusEnum.NOT_ENABLED.getValue() == existedUser.getStatus()
				&& UserStatusEnum.NORMAL.getValue() == user.getStatus()) {
			AppNoticeForm noticeForm = NoticeUtils.noticeApproveResult();
			appNoticeService.toSingle(existedUser.getCid(), noticeForm);
		}

		this.updateById(user);

		//检查角色是否越权
		// checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}
}
