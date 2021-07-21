package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.McRealMonitor1minEntity;

import java.util.List;
import java.util.Map;

/**
 * 工艺参数状态监控表 采集周期1分钟
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 13:19:38
 */
public interface AppBusService extends IService<McRealMonitor1minEntity> {

    List<McRealMonitor1minEntity> getRealArt(Map<String, Object> params);
}

