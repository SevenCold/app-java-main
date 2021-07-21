package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppEventInforDao;
import io.renren.modules.app.v1.entity.AppEventInforEntity;
import io.renren.modules.app.v1.service.AppEventInforService;


@Service("appEventInforService")
public class AppEventInforServiceImpl extends ServiceImpl<AppEventInforDao, AppEventInforEntity> implements AppEventInforService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppEventInforEntity> page = this.page(
                new Query<AppEventInforEntity>().getPage(params),
                new QueryWrapper<AppEventInforEntity>()
        );

        return new PageUtils(page);
    }

}