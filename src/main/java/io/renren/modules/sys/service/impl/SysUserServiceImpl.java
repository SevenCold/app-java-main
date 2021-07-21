/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.oauth2.TokenGenerator;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.SysUserTokenService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Value("${yjs.redis-key-expire.token}")
    private Long tokenExpire;
	@Autowired
	private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");
		String nickName = (String)params.get("nickName");

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.like(StringUtils.isNotBlank(nickName),"nick_name", nickName)
				.eq(createUserId != null,"create_user_id", createUserId)
				.orderByAsc("status")
		);

		return new PageUtils(page);
	}

	@Override
	public Set<String> queryAllPerms(Long userId) {
		List<String> list = baseMapper.queryAllPerms(userId);
		Set<String> res = new HashSet<>();
		if (!CollectionUtils.isEmpty(list)) {
			res = list.stream().map(e -> e.split(",")).flatMap(Arrays::stream).collect(Collectors.toSet());
		}
		return res;
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	public SysUserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<SysUserEntity>().eq("mobile", mobile));
	}

	@Override
	@Transactional
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
	    String newPwd = "";
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
		    newPwd = new Sha256Hash(user.getPassword(), user.getSalt()).toHex();
			user.setPassword(newPwd);
		}
        String redisKey = RedisKeys.getSysUserTokenKey(TokenGenerator.generateByUserId(user.getUserId()));
        //如果修改了用户名和密码，需要清除token，踢下线
        SysUserEntity existedUser = this.getById(user.getUserId());
		if (existedUser == null || !existedUser.getUsername().equals(user.getUsername())
                        || (!"".equals(newPwd) &&!existedUser.getPassword().equals(user.getPassword()))) {
		    redisUtils.delete(redisKey);
        } else {
            //更新redis中用户信息
            SysUserEntity redisUser = redisUtils.get(redisKey, SysUserEntity.class);
            if (redisUser != null) {
                redisUser.setUsername(user.getUsername());
                redisUser.setStatus(user.getStatus());
                redisUtils.set(redisKey, redisUser, tokenExpire);
            }
        }
		this.updateById(user);

		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Long[] userId) {
        for (Long id : userId) {
            String token = TokenGenerator.generateByUserId(id);
            sysUserTokenService.logout(token, id);
        }
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

    @Override
    public boolean checkExistenceByName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return false;
        }
	    return baseMapper.selectOne(new QueryWrapper<SysUserEntity>()
				.eq("username", userName)
				.last("limit 1")) != null;
    }

    @Override
	@Transactional
	public void active(SysUserEntity user) {
		this.updateById(user);
		//检查角色是否越权
		checkRole(user);
	}

    @Override
    public SysUserEntity getUserById(long userId) {
		return this.baseMapper.selectById(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        this.removeById(userId);
        // 删除缓存
		redisUtils.delete(RedisKeys.getUserInfoKey(userId));
		redisUtils.delete(RedisKeys.getUserRoleKey(userId));
    }

    /**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员 或者注册用户，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN || user.getCreateUserId() == Constant.REGISTER){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}
}