/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.dao.SysRoleMenuDao;
import io.renren.modules.sys.entity.SysRoleMenuEntity;
import io.renren.modules.sys.service.SysMenuService;
import io.renren.modules.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * 角色与菜单对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		deleteBatch(new Long[]{roleId});

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		for(Long menuId : menuIdList){
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);

			this.save(sysRoleMenuEntity);
		}
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	public int deleteBatch(Long[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

    @Override
    public void deleteRoleCacheByMenuId(long menuId) {
		List<Long> userIdList = sysMenuService.getUserIdByMenuId(menuId);
		for (Long userId : userIdList) {
			redisUtils.delete(RedisKeys.getUserRoleKey(userId));
		}
		//注意：admin账户不是角色，需要单独删除缓存
		redisUtils.delete(RedisKeys.getUserRoleKey(1));
	}

}
