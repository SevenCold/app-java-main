package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppFpsProdDataDao;
import io.renren.modules.app.v1.entity.AppFpsProdDataEntity;
import io.renren.modules.app.v1.service.AppFpsProdDataService;


@Service("appFpsProdDataService")
public class AppFpsProdDataServiceImpl extends ServiceImpl<AppFpsProdDataDao, AppFpsProdDataEntity> implements AppFpsProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppFpsProdDataEntity> page = this.page(
                new Query<AppFpsProdDataEntity>().getPage(params),
                new QueryWrapper<AppFpsProdDataEntity>()
        );

        return new PageUtils(page);
    }

}