package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppTroModelDataDao;
import io.renren.modules.app.v1.entity.AppTroModelDataEntity;
import io.renren.modules.app.v1.service.AppTroModelDataService;


@Service("appTroModelDataService")
public class AppTroModelDataServiceImpl extends ServiceImpl<AppTroModelDataDao, AppTroModelDataEntity> implements AppTroModelDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppTroModelDataEntity> page = this.page(
                new Query<AppTroModelDataEntity>().getPage(params),
                new QueryWrapper<AppTroModelDataEntity>()
        );

        return new PageUtils(page);
    }

}