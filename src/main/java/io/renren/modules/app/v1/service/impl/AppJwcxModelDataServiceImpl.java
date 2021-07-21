package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppJwcxModelDataDao;
import io.renren.modules.app.v1.entity.AppJwcxModelDataEntity;
import io.renren.modules.app.v1.service.AppJwcxModelDataService;


@Service("appJwcxModelDataService")
public class AppJwcxModelDataServiceImpl extends ServiceImpl<AppJwcxModelDataDao, AppJwcxModelDataEntity> implements AppJwcxModelDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppJwcxModelDataEntity> page = this.page(
                new Query<AppJwcxModelDataEntity>().getPage(params),
                new QueryWrapper<AppJwcxModelDataEntity>()
        );

        return new PageUtils(page);
    }

}