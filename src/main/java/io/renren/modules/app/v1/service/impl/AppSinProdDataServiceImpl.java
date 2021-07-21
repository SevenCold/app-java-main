package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppSinProdDataDao;
import io.renren.modules.app.v1.entity.AppSinProdDataEntity;
import io.renren.modules.app.v1.service.AppSinProdDataService;


@Service("appSinProdDataService")
public class AppSinProdDataServiceImpl extends ServiceImpl<AppSinProdDataDao, AppSinProdDataEntity> implements AppSinProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSinProdDataEntity> page = this.page(
                new Query<AppSinProdDataEntity>().getPage(params),
                new QueryWrapper<AppSinProdDataEntity>()
        );

        return new PageUtils(page);
    }

}