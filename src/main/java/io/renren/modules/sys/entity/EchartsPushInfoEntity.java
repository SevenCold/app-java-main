package io.renren.modules.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * ECharts数据
 */
@Data
@AllArgsConstructor
public class EchartsPushInfoEntity {

    private String time;

    private Map<String, String> map;
}
