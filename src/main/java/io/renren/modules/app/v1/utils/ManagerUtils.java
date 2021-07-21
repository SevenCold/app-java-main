package io.renren.modules.app.v1.utils;

import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 汪少
 * @date 2021/4/8 16:22
 */
@Component
public class ManagerUtils {

    @Autowired
    private RedisUtils redisUtils;

    public void deleteRedisInfo(SysUserEntity user) {
        if (user == null) {
            return;
        }
        if (StringUtils.isNotEmpty(user.getToken())) {
            redisUtils.delete(RedisKeys.getSysUserTokenKey(user.getToken()));
        }
        Long userId = user.getUserId();
        if (userId != 0L) {
            redisUtils.delete(RedisKeys.getUserInfoKey(userId));
            redisUtils.delete(RedisKeys.getUserRoleKey(userId));
        }
    }
}
