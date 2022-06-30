package io.renren.modules.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.common.utils.UploadUtils;
import io.renren.modules.excel.writer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("app/v1/excel")
@Slf4j
public class AppExcelController {

    @Value("${yjs.app.reportsPath}")
    private String reportsPath;

    @Value("${yjs.app.reportsUrl}")
    private String reportsUrl;

    @Autowired
    private ConsumeMatWriter consumeMatWriter;

    @Autowired
    private ConsumeEnergyWriter energyWriter;

    @RequestMapping("/tuoliu")
    public String exportTuoLiu() throws Exception {
        final String templateName = "tuoliu";
        EasyExcelWriter writer = new TuoliuWriter();
        String fileName = writeExcel("脱硫脱硝系统日报", templateName,
                "3#烧结机脱硫脱硝系统日报",
                DatePattern.NORM_DATE_PATTERN,  writer, "");
        return reportsUrl + templateName + "/" + fileName;
    }

    @RequestMapping("/chuchen")
    public String exportChuchen() throws Exception {
        final String templateName = "chuchen";
        EasyExcelWriter writer = new ChuchenWriter();
        String fileName = writeExcel("布袋除尘器日报表", templateName,
                "3#烧结机布袋除尘器日报表",
                DatePattern.NORM_DATE_PATTERN,  writer, "");
        return reportsUrl + templateName + "/" + fileName;
    }

    @RequestMapping("/yuanliao")
    public String exportYuanliao() throws Exception {
        final String templateName = "yuanliao";
        EasyExcelWriter writer = new YuanliaoWriter();
        String fileName = writeExcel("原料除尘器日报表", templateName,
                "3#烧结机原料除尘器日报表",
                DatePattern.NORM_DATE_PATTERN,  writer, "");
        return reportsUrl + templateName + "/" + fileName;
    }

    @RequestMapping("/shengchang")
    public String exportShengchang() throws Exception {
        final String templateName = "shengchang";
        EasyExcelWriter writer = new ShengchangWriter();
        String fileName = writeExcel("生产日报表", templateName,
                "3#烧结机生产日报表",
                DatePattern.NORM_DATE_PATTERN, writer, "");
        return reportsUrl + templateName + "/" + fileName;
    }

    @RequestMapping("/matConsume/{month}")
    public String exportMatConsume(@PathVariable("month") String month) throws Exception {
        final String templateName = "consume_yuanliao";
        String fileName = writeExcel("原料消耗月报表", templateName,
                "原料消耗统计",
                DatePattern.NORM_DATE_PATTERN, consumeMatWriter, month);
        return reportsUrl + templateName + "/" + fileName;
    }

    @RequestMapping("/energyConsume/{month}")
    public String exportEnergyConsume(@PathVariable("month") String month) throws Exception {
        final String templateName = "consume_nengyuan";
        String fileName = writeExcel("能量消耗月报表", templateName,
                "能量消耗统计", DatePattern.NORM_DATE_PATTERN, energyWriter, month);
        return reportsUrl + templateName + "/" + fileName;
    }

    private String writeExcel(String fileName, String templateName, String sheetName,
                              String timeFormat, EasyExcelWriter writer, String param) throws Exception {
        String nowDay = DateUtil.date().toString(timeFormat);
        fileName = fileName + "-" + nowDay + ".xlsx";
        String filePath = UploadUtils.getUploadPath(reportsPath + File.separator + templateName) + File.separator + fileName;
        if (!FileUtil.exist(filePath)) {
            String templateFileName = UploadUtils.getUploadPath(reportsPath + File.separator + "template") + File.separator + templateName + ".xlsx";
            File tempFile = new File(templateFileName);
            if (!tempFile.exists()) {
                log.error("Excel模板文件不存在: " + templateFileName);
            } else {
                ExcelWriter excelWriter = EasyExcel.write(filePath).withTemplate(tempFile).build();
                WriteSheet writeSheet = EasyExcel.writerSheet().sheetName(sheetName).build();
                writer.writeExcel(excelWriter, writeSheet, param);
            }
        }
        return fileName;
    }
}
