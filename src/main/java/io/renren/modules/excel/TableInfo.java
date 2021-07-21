package io.renren.modules.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadRowHeight(20)
@ColumnWidth(20)
@ContentRowHeight(15)
public class TableInfo {

    @ExcelProperty(index = 2, value = "字段名")
    private String name;

    @ExcelProperty(index = 3, value = "数据类型")
    private String type;

    @ExcelProperty(index = 4, value = "字段描述")
    private String comment;
}
