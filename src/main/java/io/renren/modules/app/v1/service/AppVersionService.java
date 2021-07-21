package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.v1.entity.AppVersionEntity;

/**
 * app版本查询服务
 * @author kang
 */
public interface AppVersionService extends IService<AppVersionEntity> {

    /**
     * 根据当前版本号，获取App最新版本号
     * 若没有更新的版本，返回空对象
     * @param currentVersion 当前版本号
     * @return 最新版本信息
     */
    AppVersionEntity getLatestAppVersion(String currentVersion);

    /**
     * 获取最新的App版本号
     * @return 最新版本号
     */
    String getLatestAppVersionNo();
}
