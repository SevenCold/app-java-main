package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.v1.dao.AppElectrityDataDao;
import io.renren.modules.app.v1.entity.AppElectrityDataEntity;
import io.renren.modules.app.v1.service.AppElectrityDataService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("appElectrityDataService")
public class AppElectrityDataServiceImpl extends ServiceImpl<AppElectrityDataDao, AppElectrityDataEntity> implements AppElectrityDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppElectrityDataEntity> page = this.page(
                new Query<AppElectrityDataEntity>().getPage(params),
                new QueryWrapper<AppElectrityDataEntity>()
        );

        return new PageUtils(page);
    }

}