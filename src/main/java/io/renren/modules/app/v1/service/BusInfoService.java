package io.renren.modules.app.v1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.BusInfoEntity;
import io.renren.modules.sys.entity.EchartsInfoEntity;

import java.text.ParseException;
import java.util.Map;

/**
 * APP查询曲线信息服务
 * @author kang
 */
public interface BusInfoService  extends IService<BusInfoEntity> {

    /**
     * 查询曲线信息
     * @param map 条件表单
     * @return 曲线信息
     * @throws ParseException 查询条件时间格式错误（yyyy-MM-dd HH:mm:ss）
     */
    EchartsInfoEntity getBusInfoByCondition(Map<String, String> map) throws ParseException;
}
