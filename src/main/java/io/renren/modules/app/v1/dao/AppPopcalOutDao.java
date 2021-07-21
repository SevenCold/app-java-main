package io.renren.modules.app.v1.dao;

import io.renren.modules.app.v1.entity.AppPopcalOutEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:43
 */
@Mapper
public interface AppPopcalOutDao extends BaseMapper<AppPopcalOutEntity> {

    List<AppPopcalOutEntity> getEveryMonthData();
}