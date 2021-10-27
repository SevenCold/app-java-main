package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.Query;
import io.renren.modules.app.v1.dao.AppSinRunStopDao;
import io.renren.modules.app.v1.entity.AppSinRunStopEntity;
import io.renren.modules.app.v1.service.AppSinRunStopService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("appSinRunStopService")
public class AppSinRunStopServiceImpl extends ServiceImpl<AppSinRunStopDao, AppSinRunStopEntity> implements AppSinRunStopService {

    @Override
    public IPage<AppSinRunStopEntity> queryPage(Map<String, Object> params) {
        Page<AppSinRunStopEntity> page = (Page<AppSinRunStopEntity>) new Query<AppSinRunStopEntity>().getPage(params);
        return this.baseMapper.queryByPage(page, params);
    }

}