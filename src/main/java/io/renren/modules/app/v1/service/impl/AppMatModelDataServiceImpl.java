package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppMatModelDataDao;
import io.renren.modules.app.v1.entity.AppMatModelDataEntity;
import io.renren.modules.app.v1.service.AppMatModelDataService;


@Service("appMatModelDataService")
public class AppMatModelDataServiceImpl extends ServiceImpl<AppMatModelDataDao, AppMatModelDataEntity> implements AppMatModelDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMatModelDataEntity> page = this.page(
                new Query<AppMatModelDataEntity>().getPage(params),
                new QueryWrapper<AppMatModelDataEntity>()
        );

        return new PageUtils(page);
    }

}