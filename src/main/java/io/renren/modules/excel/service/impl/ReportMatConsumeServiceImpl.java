package io.renren.modules.excel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.excel.dao.ReportMatConsumeDao;
import io.renren.modules.excel.entity.ReportMatConsumeEntity;
import io.renren.modules.excel.service.ReportMatConsumeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("reportMatConsumeService")
public class ReportMatConsumeServiceImpl extends ServiceImpl<ReportMatConsumeDao, ReportMatConsumeEntity> implements ReportMatConsumeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportMatConsumeEntity> page = this.page(
                new Query<ReportMatConsumeEntity>().getPage(params),
                new QueryWrapper<ReportMatConsumeEntity>()
        );

        return new PageUtils(page);
    }

}