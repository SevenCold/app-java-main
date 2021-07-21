package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.AppPopcalOutEntity;

import java.util.List;

/**
 * 
 *
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-14 15:12:43
 */
public interface AppPopcalOutService extends IService<AppPopcalOutEntity> {

    List<AppPopcalOutEntity> getEveryMonthData();
}

