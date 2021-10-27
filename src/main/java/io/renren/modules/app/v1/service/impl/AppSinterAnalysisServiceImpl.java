package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.Query;
import io.renren.modules.app.v1.dao.AppSinterAnalysisDao;
import io.renren.modules.app.v1.entity.AppSinterAnalysisEntity;
import io.renren.modules.app.v1.service.AppSinterAnalysisService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("appSinterAnalysisService")
public class AppSinterAnalysisServiceImpl extends ServiceImpl<AppSinterAnalysisDao, AppSinterAnalysisEntity> implements AppSinterAnalysisService {

    @Override
    public IPage<AppSinterAnalysisEntity> queryPage(Map<String, Object> params) {
        Page<AppSinterAnalysisEntity> page = (Page<AppSinterAnalysisEntity>) new Query<AppSinterAnalysisEntity>().getPage(params);
        return this.baseMapper.selectPageVo(page);
    }

}