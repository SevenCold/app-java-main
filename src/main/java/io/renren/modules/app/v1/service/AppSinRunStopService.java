package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.AppSinRunStopEntity;

import java.util.Map;

/**
 * 
 *
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:41
 */
public interface AppSinRunStopService extends IService<AppSinRunStopEntity> {

    IPage<AppSinRunStopEntity> queryPage(Map<String, Object> params);
}

