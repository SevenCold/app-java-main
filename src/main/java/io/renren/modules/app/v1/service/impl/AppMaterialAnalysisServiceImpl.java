package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppMaterialAnalysisDao;
import io.renren.modules.app.v1.entity.AppMaterialAnalysisEntity;
import io.renren.modules.app.v1.service.AppMaterialAnalysisService;


@Service("appMaterialAnalysisService")
public class AppMaterialAnalysisServiceImpl extends ServiceImpl<AppMaterialAnalysisDao, AppMaterialAnalysisEntity> implements AppMaterialAnalysisService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMaterialAnalysisEntity> page = this.page(
                new Query<AppMaterialAnalysisEntity>().getPage(params),
                new QueryWrapper<AppMaterialAnalysisEntity>()
        );

        return new PageUtils(page);
    }

}