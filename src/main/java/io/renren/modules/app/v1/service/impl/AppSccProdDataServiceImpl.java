package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppSccProdDataDao;
import io.renren.modules.app.v1.entity.AppSccProdDataEntity;
import io.renren.modules.app.v1.service.AppSccProdDataService;


@Service("appSccProdDataService")
public class AppSccProdDataServiceImpl extends ServiceImpl<AppSccProdDataDao, AppSccProdDataEntity> implements AppSccProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSccProdDataEntity> page = this.page(
                new Query<AppSccProdDataEntity>().getPage(params),
                new QueryWrapper<AppSccProdDataEntity>()
        );

        return new PageUtils(page);
    }

}