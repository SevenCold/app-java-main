package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppMixProdDataDao;
import io.renren.modules.app.v1.entity.AppMixProdDataEntity;
import io.renren.modules.app.v1.service.AppMixProdDataService;


@Service("appMixProdDataService")
public class AppMixProdDataServiceImpl extends ServiceImpl<AppMixProdDataDao, AppMixProdDataEntity> implements AppMixProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMixProdDataEntity> page = this.page(
                new Query<AppMixProdDataEntity>().getPage(params),
                new QueryWrapper<AppMixProdDataEntity>()
        );

        return new PageUtils(page);
    }

}