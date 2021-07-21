package io.renren.modules.excel;

import cn.hutool.core.io.file.FileWriter;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class TableController {

    @RequestMapping(value = "import")
    public List<TableInfo> importStudentInfos(MultipartFile file) throws IOException {
        TableInfoListener listener = new TableInfoListener();
        EasyExcel.read(file.getInputStream(), TableInfo.class, listener).sheet().doRead();
        return listener.getList();
    }


    @RequestMapping(value = "read")
    public List<TableInfo> readExcel() {
        String fileName = "C:\\Users\\HeXuling\\Desktop\\APP需求文档\\手机APP数据库设计（简）-20210616.xlsx";
        TableInfoListener listener = new TableInfoListener();
        FileWriter writer = new FileWriter("C:\\Users\\HeXuling\\Desktop\\添加注释.sql");
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, TableInfo.class, listener).build();
            List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
            for (int i = 1; i < sheets.size(); i++) {
                String sheetName = sheets.get(i).getSheetName();
                ReadSheet readSheet = EasyExcel.readSheet(sheetName).build();
                excelReader.read(readSheet);
                List<TableInfo> list = listener.getList();
                for (TableInfo tableInfo : list) {
                    if (StringUtils.isNotEmpty(tableInfo.getName())
                            && StringUtils.isNotEmpty(tableInfo.getType())
                            && StringUtils.isNotEmpty(tableInfo.getComment())) {
                        writer.append("alter table `" + sheetName + "` modify column `"
                                + tableInfo.getName() + "` " + tableInfo.getType()
                                + " comment '" + tableInfo.getComment() + "';" + "  ");
                    }
                }
                listener.clear();
            }
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }

        return null;
    }
}
