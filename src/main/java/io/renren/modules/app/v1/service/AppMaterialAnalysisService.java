package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.v1.entity.AppMaterialAnalysisEntity;

import java.util.Map;

/**
 * 
 *
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:43
 */
public interface AppMaterialAnalysisService extends IService<AppMaterialAnalysisEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

