package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.SqlUtils;
import io.renren.modules.app.v1.dao.McRealMonitor1minDao;
import io.renren.modules.app.v1.entity.McRealMonitor1minEntity;
import io.renren.modules.app.v1.service.AppBusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("mcRealMonitor1minService")
public class AppBusServiceImpl extends ServiceImpl<McRealMonitor1minDao, McRealMonitor1minEntity> implements AppBusService {



    @Override
    public List<McRealMonitor1minEntity> getRealArt(Map<String, Object> params) {
        LambdaQueryWrapper<McRealMonitor1minEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply(params.get(Constant.START) != null,
                SqlUtils.getGtTimeSql("create_time", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.get(Constant.END) != null,
                        SqlUtils.getLtTimeSql("create_time", params.get(Constant.END), Constant.MINUTE_FORMAT));
        return this.baseMapper.selectList(wrapper);
    }

}