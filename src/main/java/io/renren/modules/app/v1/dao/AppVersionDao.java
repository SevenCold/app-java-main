package io.renren.modules.app.v1.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.v1.entity.AppVersionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppVersionDao extends BaseMapper<AppVersionEntity> {
}
