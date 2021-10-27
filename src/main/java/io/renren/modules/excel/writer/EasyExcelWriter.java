package io.renren.modules.excel.writer;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

public interface EasyExcelWriter {
    void writeExcel(ExcelWriter excelWriter, WriteSheet writeSheet);
}
