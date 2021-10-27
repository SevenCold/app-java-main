package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.AppEventInforEntity;

import java.util.Map;

/**
 * 
 *
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:44
 */
public interface AppEventInforService extends IService<AppEventInforEntity> {

    IPage<AppEventInforEntity> queryPage(Map<String, Object> params);
}

