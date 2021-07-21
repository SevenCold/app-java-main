package io.renren.modules.app.v1.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.v1.dao.AppPopcalOutDao;
import io.renren.modules.app.v1.entity.AppPopcalOutEntity;
import io.renren.modules.app.v1.service.AppPopcalOutService;


@Service("appPopcalOutService")
public class AppPopcalOutServiceImpl extends ServiceImpl<AppPopcalOutDao, AppPopcalOutEntity> implements AppPopcalOutService {

    @Override
    public List<AppPopcalOutEntity> getEveryMonthData() {
        return this.baseMapper.getEveryMonthData();
    }
}