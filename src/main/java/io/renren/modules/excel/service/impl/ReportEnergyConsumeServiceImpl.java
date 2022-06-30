package io.renren.modules.excel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.excel.dao.ReportEnergyConsumeDao;
import io.renren.modules.excel.entity.ReportEnergyConsumeEntity;
import io.renren.modules.excel.service.ReportEnergyConsumeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("reportEnergyConsumeService")
public class ReportEnergyConsumeServiceImpl extends ServiceImpl<ReportEnergyConsumeDao, ReportEnergyConsumeEntity> implements ReportEnergyConsumeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportEnergyConsumeEntity> page = this.page(
                new Query<ReportEnergyConsumeEntity>().getPage(params),
                new QueryWrapper<ReportEnergyConsumeEntity>()
        );

        return new PageUtils(page);
    }

}