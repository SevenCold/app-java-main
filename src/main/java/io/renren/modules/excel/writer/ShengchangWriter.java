package io.renren.modules.excel.writer;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import io.renren.modules.excel.ExcelData;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3#烧结机生产日报表
 */
public class ShengchangWriter implements EasyExcelWriter{
    @Override
    public void writeExcel(ExcelWriter excelWriter, WriteSheet writeSheet) {
        int size = 15;
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("data1", getData1(size)), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("data2", getData2(size)), writeSheet);
        excelWriter.fill(new FillWrapper("data3", getData3(size)), writeSheet);
        excelWriter.fill(new FillWrapper("data4", getData4(9)), writeSheet);
        excelWriter.fill(new FillWrapper("data5", getData5(24)), writeSheet);
        excelWriter.fill(new FillWrapper("data6", getData6(22)), writeSheet);
        excelWriter.fill(new FillWrapper("data7", getData7(5)), writeSheet);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", "250");
        excelWriter.fill(map, writeSheet);
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
            obj.setFourteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
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

    private List<ExcelData> getData4(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            if (i == 2 || i == 5) {
            } else {
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
                obj.setFourteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setFifteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setSixteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            }
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData5(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            if (i == 2 || i == 5 || i == 8 || i == 10 || i == 11) {
            } else {
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
                obj.setFourteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setFifteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setSixteen(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            }
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData6(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            if (i == 2 || i == 3 || i == 8 || i == 12 || i == 21) {
            } else {
                obj.setOne(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setTwo(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setThree(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setFour(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setFive(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setSix(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setSeven(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
                obj.setEight(RandomUtil.randomDouble(2, RoundingMode.CEILING) + "");
            }
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData7(int size) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(RandomUtil.randomDay(1, 30).toString());
            obj.setTwo(RandomUtil.randomDay(1, 30).toString());
            int minute = RandomUtil.randomInt(60);
            obj.setThree(minute + "");
            obj.setFour("停机时间：" + minute + "分钟。");
            res.add(obj);
        }
        return res;
    }
}
