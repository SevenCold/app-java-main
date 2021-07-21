package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppRmcModelDataDao;
import io.renren.modules.app.v1.entity.AppRmcModelDataEntity;
import io.renren.modules.app.v1.service.AppRmcModelDataService;


@Service("appRmcModelDataService")
public class AppRmcModelDataServiceImpl extends ServiceImpl<AppRmcModelDataDao, AppRmcModelDataEntity> implements AppRmcModelDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppRmcModelDataEntity> page = this.page(
                new Query<AppRmcModelDataEntity>().getPage(params),
                new QueryWrapper<AppRmcModelDataEntity>()
        );

        return new PageUtils(page);
    }

}