
package io.renren.modules.app.v1.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.utils.*;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.app.v1.VO.AppMenuVo;
import io.renren.modules.app.v1.annotation.Login;
import io.renren.modules.app.v1.annotation.LoginUser;
import io.renren.modules.app.v1.annotation.Permitted;
import io.renren.modules.app.v1.entity.AppUserEntity;
import io.renren.modules.app.v1.service.AppUserService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.PasswordForm;
import io.renren.modules.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * APP系统用户
 *
 */
@RestController
@RequestMapping("/app/v1/user")
@Api("APP用户相关接口")
public class AppUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Autowired
	private AppUserService appUserService;
	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@ApiOperation("查询所有用户信息")
	@Permitted("sys:user:list")
	public R list(@RequestParam Map<String, Object> params, @LoginUser SysUserEntity user){
		PageUtils page = sysUserService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	@ApiOperation("获取登录的用户信息")
	@Login
	public R info(@LoginUser AppUserEntity user){
		return R.ok().put("user", user);
	}
	
	/**
	 * 修改登录用户密码
	 */
	@PostMapping("/password")
	@ApiOperation("修改登录用户密码")
	@Permitted("sys:user:update")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return ErrorMsgEnum.OLD_PWD_ERROR.getR();
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@ApiOperation("根据用户id查询用户信息")
	@Permitted("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		user.setPassword(null);
		user.setSalt(null);
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}

	/**
	 * 修改用户
	 */
	@PostMapping("/update")
	@ApiOperation("修改用户信息")
	@Permitted("sys:user:update")
	public R update(@RequestBody AppUserEntity user, @LoginUser AppUserEntity loginUser){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		user.setCreateUserId(loginUser.getUserId());
		appUserService.updateAppInfoAndRole(user);
		// 更新redis信息
		// 先从redis中取出完整的用户信息，再根据传入的信息覆盖修改redis
		Long userId = user.getUserId();
		AppUserEntity redisUser = redisUtils.get(RedisKeys.getUserInfoKey(userId), AppUserEntity.class);
		if (redisUser != null) {
			BeanUtil.copyProperties(user, redisUser, "token", "createTime", "createUserId", "roleIdList");
			redisUser.setPassword(null);
			// 由于jwt是通过token获取用户信息，所有要修改key为token的值
			redisUtils.set(RedisKeys.getSysUserTokenKey(redisUser.getToken()), redisUser);
			redisUtils.set(RedisKeys.getUserInfoKey(userId), redisUser);
			Set<String> perms = sysUserService.queryAllPerms(userId);
			redisUtils.set(RedisKeys.getUserRoleKey(userId), perms);
		}
		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@PostMapping("/delete")
	@ApiOperation("删除用户")
	@Permitted("sys:user:delete")
	public R delete(@RequestBody Long userId, @LoginUser SysUserEntity loginUser){
		if(Constant.SUPER_ADMIN == userId){
			return ErrorMsgEnum.MANAGER_DELETE.getR();
		}
		
		if(userId.equals(loginUser.getUserId())){
			return ErrorMsgEnum.SELF_DELETE.getR();
		}
		
		sysUserService.deleteUser(userId);
		
		return R.ok();
	}

	/**
	 * 获取所有角色列表（用于复选框）
	 * @param user
	 * @return
	 */
	@GetMapping("/role/list")
	@ApiOperation("获取所有角色列表（用于复选框）")
	@Login
	public R select(@LoginUser SysUserEntity user){
		Map<String, Object> map = new HashMap<>();
		List<SysRoleEntity> list = sysRoleService.listByMap(map);
		return R.ok().put("list", list);
	}

	/**
	 * 获取分页的角色列表（用于查询）
	 * @param params 查询表单
	 * @return
	 */
	@GetMapping("/role/page")
	@ApiOperation("获取分页的角色列表（用于查询）")
	@Permitted("sys:role:select")
	public R select(@RequestParam Map<String, Object> params){
		PageUtils page = sysRoleService.queryPage(params);
		return R.ok().put("page", page);
	}

	@GetMapping("/menu/list")
	@ApiOperation("查询菜单列表")
	@Permitted("sys:menu:list")
	public R getMenulist(){
		List<SysMenuEntity> menuList = sysMenuService.list();
		List<AppMenuVo> res = new ArrayList<>();
		for (SysMenuEntity sysMenuEntity : menuList) {
			// 顶级菜单 向下递归查询子菜单
			if (sysMenuEntity.getParentId().equals(0L)) {
				AppMenuVo appMenuVo = new AppMenuVo();
				appMenuVo.setMenuId(sysMenuEntity.getMenuId());
				appMenuVo.setMenuName(sysMenuEntity.getName());
				appMenuVo.setChildren(getChildrenMenu(sysMenuEntity, menuList));
				res.add(appMenuVo);
			}
		}
		return R.ok().put("list", res);
	}

	@PostMapping("/role/save")
	@ApiOperation("保存角色信息")
	@Permitted("sys:role:save")
	public R save(@RequestBody SysRoleEntity role, @LoginUser SysUserEntity user){
		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(user.getUserId());
		if (role.getRoleId() != null) {
			sysRoleService.update(role);
		} else {
			sysRoleService.saveRole(role);
		}
		return R.ok();
	}

	@GetMapping("/role/info/{roleId}")
	@ApiOperation("获取角色信息")
	@Permitted("sys:role:info")
	public R roleInfo(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);

		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return R.ok().put("role", role);
	}


	@PostMapping("/role/delete")
	@ApiOperation("删除角色")
	@Permitted("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);

		return R.ok();
	}

	/**
	 *  构建以下数据结构：
	 * [{
	 * 		menuId: 1,
	 * 		menuName: "系统管理",
	 * 		children: [{
	 * 			menuId: 2,
	 * 			menuName: "用户管理",
	 * 			children: [...]
	 * 		}]
	 *
	 * }]
	 */
	private List<AppMenuVo> getChildrenMenu(SysMenuEntity menu, List<SysMenuEntity> menuList) {
		//
		List<SysMenuEntity> children = getChildren(menu, menuList);
		if (CollectionUtil.isEmpty(children)) {
			return null;
		}
		List<AppMenuVo> resList = new ArrayList<>();
		for (SysMenuEntity child : children) {
			AppMenuVo appMenuVo = new AppMenuVo();
			appMenuVo.setMenuId(child.getMenuId());
			appMenuVo.setMenuName(child.getName());
			List<AppMenuVo> childrenMenu = getChildrenMenu(child, menuList);
			appMenuVo.setChildren(childrenMenu);
			resList.add(appMenuVo);
		}
		return resList;
	}

	private List<SysMenuEntity> getChildren(SysMenuEntity parentMenu, List<SysMenuEntity> sourceList) {
		if (parentMenu == null || CollectionUtil.isEmpty(sourceList)) {
			return Collections.emptyList();
		}
		return sourceList.stream().filter(e -> parentMenu.getMenuId().equals(e.getParentId())).collect(Collectors.toList());
	}
}
