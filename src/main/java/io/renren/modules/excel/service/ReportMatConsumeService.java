package io.renren.modules.excel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.excel.entity.ReportMatConsumeEntity;

import java.util.Map;

/**
 * 
 *
 * @author kang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 09:22:55
 */
public interface ReportMatConsumeService extends IService<ReportMatConsumeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

