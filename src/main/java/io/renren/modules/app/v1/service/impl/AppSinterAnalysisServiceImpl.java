package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppSinterAnalysisDao;
import io.renren.modules.app.v1.entity.AppSinterAnalysisEntity;
import io.renren.modules.app.v1.service.AppSinterAnalysisService;


@Service("appSinterAnalysisService")
public class AppSinterAnalysisServiceImpl extends ServiceImpl<AppSinterAnalysisDao, AppSinterAnalysisEntity> implements AppSinterAnalysisService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSinterAnalysisEntity> page = this.page(
                new Query<AppSinterAnalysisEntity>().getPage(params),
                new QueryWrapper<AppSinterAnalysisEntity>()
        );

        return new PageUtils(page);
    }

}