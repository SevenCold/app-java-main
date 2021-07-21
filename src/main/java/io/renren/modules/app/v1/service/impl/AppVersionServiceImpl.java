package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.v1.dao.AppVersionDao;
import io.renren.modules.app.v1.entity.AppVersionEntity;
import io.renren.modules.app.v1.service.AppVersionService;
import org.springframework.stereotype.Service;

/**
 * app版本查询服务实现类
 * @author kang
 */
@Service("appVersionService")
public class AppVersionServiceImpl extends ServiceImpl<AppVersionDao, AppVersionEntity> implements AppVersionService {

    @Override
    public AppVersionEntity getLatestAppVersion(String currentVersion) {
        // 查询大于当前版本号的版本信息
        return this.baseMapper.selectOne(
                new QueryWrapper<AppVersionEntity>().gt("version_code", currentVersion));
    }

    @Override
    public String getLatestAppVersionNo() {
        AppVersionEntity latestVersion = this.baseMapper.selectOne(new QueryWrapper<AppVersionEntity>()
                .select("version_code").orderByDesc("id").last("limit 1"));
        return latestVersion.getVersionCode();
    }
}
