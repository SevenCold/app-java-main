package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppRTCurveDao;
import io.renren.modules.app.v1.entity.AppRTCurveEntity;
import io.renren.modules.app.v1.service.AppRTCurveService;


@Service("appRTCurveService")
public class AppRTCurveServiceImpl extends ServiceImpl<AppRTCurveDao, AppRTCurveEntity> implements AppRTCurveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppRTCurveEntity> page = this.page(
                new Query<AppRTCurveEntity>().getPage(params),
                new QueryWrapper<AppRTCurveEntity>()
        );

        return new PageUtils(page);
    }

}