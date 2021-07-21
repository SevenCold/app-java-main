package io.renren.modules.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TableInfoListener extends AnalysisEventListener<TableInfo> {

    @Getter
    private List<TableInfo> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public TableInfoListener() {
        super();
    }

    @Override
    public void invoke(TableInfo tableInfo, AnalysisContext analysisContext) {
        list.add(tableInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
