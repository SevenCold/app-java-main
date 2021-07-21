package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppMpuProdDataDao;
import io.renren.modules.app.v1.entity.AppMpuProdDataEntity;
import io.renren.modules.app.v1.service.AppMpuProdDataService;


@Service("appMpuProdDataService")
public class AppMpuProdDataServiceImpl extends ServiceImpl<AppMpuProdDataDao, AppMpuProdDataEntity> implements AppMpuProdDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMpuProdDataEntity> page = this.page(
                new Query<AppMpuProdDataEntity>().getPage(params),
                new QueryWrapper<AppMpuProdDataEntity>()
        );

        return new PageUtils(page);
    }

}