package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysGroupDao;
import io.renren.modules.sys.entity.SysGroupEntity;
import io.renren.modules.sys.service.SysGroupService;


@Service("sysGroupService")
public class SysGroupServiceImpl extends ServiceImpl<SysGroupDao, SysGroupEntity> implements SysGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, long userId) {
        IPage<SysGroupEntity> page = this.page(
                new Query<SysGroupEntity>().getPage(params),
                new QueryWrapper<SysGroupEntity>().eq("user_id", userId)
        );
        // 将tagName转换成list
        for (SysGroupEntity record : page.getRecords()) {
            record.setTagList(Arrays.asList(record.getTagName().split(",")));
        }

        return new PageUtils(page);
    }

}