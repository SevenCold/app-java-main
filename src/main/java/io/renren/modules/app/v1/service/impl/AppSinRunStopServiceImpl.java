package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppSinRunStopDao;
import io.renren.modules.app.v1.entity.AppSinRunStopEntity;
import io.renren.modules.app.v1.service.AppSinRunStopService;


@Service("appSinRunStopService")
public class AppSinRunStopServiceImpl extends ServiceImpl<AppSinRunStopDao, AppSinRunStopEntity> implements AppSinRunStopService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSinRunStopEntity> page = this.page(
                new Query<AppSinRunStopEntity>().getPage(params),
                new QueryWrapper<AppSinRunStopEntity>()
        );

        return new PageUtils(page);
    }

}