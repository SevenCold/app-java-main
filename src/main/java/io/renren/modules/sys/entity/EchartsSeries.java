package io.renren.modules.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class EchartsSeries {
    private String name;
    private List<String> data;
}
