package io.renren.modules.app.v1.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.app.v1.entity.AppSinRunStopEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 
 * 
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:41
 */
@Mapper
public interface AppSinRunStopDao extends BaseMapper<AppSinRunStopEntity> {
    IPage<AppSinRunStopEntity> queryByPage(Page<?> page, Map<String, Object> params);
}
