package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppPsmModelDataDao;
import io.renren.modules.app.v1.entity.AppPsmModelDataEntity;
import io.renren.modules.app.v1.service.AppPsmModelDataService;


@Service("appPsmModelDataService")
public class AppPsmModelDataServiceImpl extends ServiceImpl<AppPsmModelDataDao, AppPsmModelDataEntity> implements AppPsmModelDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppPsmModelDataEntity> page = this.page(
                new Query<AppPsmModelDataEntity>().getPage(params),
                new QueryWrapper<AppPsmModelDataEntity>()
        );

        return new PageUtils(page);
    }

}