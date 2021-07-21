package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppEnpProdDataDao;
import io.renren.modules.app.v1.entity.AppEnpProdDataEntity;
import io.renren.modules.app.v1.service.AppEnpProdDataService;


@Service("appEnpProdDataService")
public class AppEnpProdDataServiceImpl extends ServiceImpl<AppEnpProdDataDao, AppEnpProdDataEntity> implements AppEnpProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppEnpProdDataEntity> page = this.page(
                new Query<AppEnpProdDataEntity>().getPage(params),
                new QueryWrapper<AppEnpProdDataEntity>()
        );

        return new PageUtils(page);
    }

}