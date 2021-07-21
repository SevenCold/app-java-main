package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分组管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-24 14:22:39
 */
@Mapper
public interface SysGroupDao extends BaseMapper<SysGroupEntity> {
	
}
