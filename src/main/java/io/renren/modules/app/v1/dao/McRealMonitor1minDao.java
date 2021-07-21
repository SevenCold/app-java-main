package io.renren.modules.app.v1.dao;

import io.renren.modules.app.v1.entity.McRealMonitor1minEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工艺参数状态监控表 采集周期1分钟
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 13:19:38
 */
@Mapper
public interface McRealMonitor1minDao extends BaseMapper<McRealMonitor1minEntity> {
	
}
