/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getSysUserTokenKey(String token){
        return "user:token:" + token;
    }

    public static String getUserRoleKey(long userId){
        return "user:perms:userid:" + userId;
    }

    public static String getUserInfoKey(long userId){
        return "user:info:userid:" + userId;
    }
}
