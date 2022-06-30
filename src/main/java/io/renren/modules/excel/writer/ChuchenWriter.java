package io.renren.modules.excel.writer;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import io.renren.modules.excel.ExcelData;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 3#烧结机布袋除尘器日报表
 */
public class ChuchenWriter implements EasyExcelWriter{

    @Override
    public void writeExcel(ExcelWriter excelWriter, WriteSheet writeSheet, String param) {
        int size = 25;
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("data1", getData1(size)), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("data2", getData2(size)), writeSheet);
        excelWriter.fill(new FillWrapper("data3", getData3(size)), writeSheet);
        excelWriter.finish();
    }

    private List<ExcelData> getData1(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(RandomUtil.randomDay(1, 60).toString());
            obj.setTwo(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThree(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFour(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFive(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSix(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSeven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEight(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setNine(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEleven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTwelve(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThirteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFourteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData2(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTwo(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThree(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFour(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFive(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSix(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSeven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEight(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setNine(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEleven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTwelve(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThirteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData3(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTwo(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThree(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFour(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setFive(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSix(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setSeven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEight(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setNine(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setEleven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setTwelve(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            obj.setThirteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            res.add(obj);
        }
        return res;
    }
}
