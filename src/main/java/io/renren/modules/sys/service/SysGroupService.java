package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysGroupEntity;

import java.util.Map;

/**
 * 分组管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-24 14:22:39
 */
public interface SysGroupService extends IService<SysGroupEntity> {

    PageUtils queryPage(Map<String, Object> params, long userId);
}

