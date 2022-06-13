package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.v1.entity.AppElectrityDataEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-13 08:44:33
 */
public interface AppElectrityDataService extends IService<AppElectrityDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

