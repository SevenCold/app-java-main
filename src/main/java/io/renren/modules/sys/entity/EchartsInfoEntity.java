package io.renren.modules.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * ECharts数据
 */
@Data
@AllArgsConstructor
public class EchartsInfoEntity {

    private List<String> xAxis;

    private List<EchartsSeries> series;
}
