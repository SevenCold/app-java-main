package io.renren.modules.excel.writer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import io.renren.modules.excel.ExcelData;
import io.renren.modules.excel.entity.ReportEnergyConsumeEntity;
import io.renren.modules.excel.service.ReportEnergyConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 3#烧结机布袋除尘器日报表
 */
@Component
public class ConsumeEnergyWriter implements EasyExcelWriter{
    
    @Autowired
    private ReportEnergyConsumeService service;

    @Override
    public void writeExcel(ExcelWriter excelWriter, WriteSheet writeSheet, String month) {
        DateTime queryMonth = DateUtil.parse(month, "yyyy-MM");
        // 每个月后三天 算 下一个月
        DateTime start = DateUtil.beginOfMonth(queryMonth).offset(DateField.DAY_OF_MONTH, -3);;
        DateTime end = DateUtil.endOfMonth(queryMonth).offset(DateField.DAY_OF_MONTH, -3);
        List<ReportEnergyConsumeEntity> dataList = service.lambdaQuery()
                .between(ReportEnergyConsumeEntity::getTimestamp, start.toJdkDate(), end.toJdkDate())
                .orderByAsc(ReportEnergyConsumeEntity::getTimestamp).list();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("data1", getData1(dataList)), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("data2", getData2(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data3", getData3(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data4", getData4(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data5", getData5(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data6", getData6(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data7", getData7(dataList)), writeSheet);
        excelWriter.finish();
    }
    private List<ExcelData> getData1(List<ReportEnergyConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(i + 1 + "");
            obj.setTwo(DateUtil.format(data.get(i).getTimestamp(), "yyyy/MM/dd"));
            obj.setThree(NumberUtil.toStr(data.get(i).getYield(), ""));
            obj.setFour(NumberUtil.toStr(data.get(i).getConEleSinter(), ""));
            obj.setFive(NumberUtil.toStr(data.get(i).getConEleTltx(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData2(List<ReportEnergyConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportEnergyConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getEleCoe(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getConEleTx(), ""));
            obj.setThree(NumberUtil.toStr(datum.getUnitEleTx(), ""));
            obj.setFour(NumberUtil.toStr(datum.getEleTotal(), ""));
            obj.setFive(NumberUtil.toStr(datum.getUnitEleSinter(), ""));
            obj.setSix(NumberUtil.toStr(datum.getUnitEleTltx(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getUnitEleTotal(), ""));
            obj.setEight(NumberUtil.toStr(datum.getConWindSinter(), ""));
            obj.setNine(NumberUtil.toStr(datum.getConWindTltx(), ""));
            obj.setTen(NumberUtil.toStr(datum.getWindCoe(), ""));
            obj.setEleven(NumberUtil.toStr(datum.getConWindTx(), ""));
            obj.setTwelve(NumberUtil.toStr(datum.getUnitWindTx(), ""));
            obj.setThirteen(NumberUtil.toStr(datum.getWindTotal(), ""));
            obj.setFourteen(NumberUtil.toStr(datum.getUnitWindSinter(), ""));
            obj.setFifteen(NumberUtil.toStr(datum.getUnitWindTltx(), ""));
            obj.setSixteen(NumberUtil.toStr(datum.getUnitWindTotal(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData3(List<ReportEnergyConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportEnergyConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getConCokeSinter(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getConCokeTl(), ""));
            obj.setThree(NumberUtil.toStr(datum.getConCokeTotal(), ""));
            obj.setFour(NumberUtil.toStr(datum.getUnitCokeSinter(), ""));
            obj.setFive(NumberUtil.toStr(datum.getUnitCokeTl(), ""));
            obj.setSix(NumberUtil.toStr(datum.getUnitCokeTotal(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getConBlastTl(), ""));
            obj.setEight(NumberUtil.toStr(datum.getUnitBlastTl(), ""));
            obj.setNine(NumberUtil.toStr(datum.getConNh2oSinter(), ""));
            obj.setTen(NumberUtil.toStr(datum.getConNh2oTl(), ""));
            obj.setEleven(NumberUtil.toStr(datum.getConNh2oTotal(), ""));
            obj.setTwelve(NumberUtil.toStr(datum.getUnitNh2oSinter(), ""));
            obj.setThirteen(NumberUtil.toStr(datum.getUnitNh2oTl(), ""));
            obj.setFourteen(NumberUtil.toStr(datum.getUnitNh2oTotal(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData4(List<ReportEnergyConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportEnergyConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getConSewageSinter(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getUnitSewageSinter(), ""));
            obj.setThree(NumberUtil.toStr(datum.getConSteamSinter(), ""));
            obj.setFour(NumberUtil.toStr(datum.getConSteamTl(), ""));
            obj.setFive(NumberUtil.toStr(datum.getConSteamTotal(), ""));
            obj.setSix(NumberUtil.toStr(datum.getUnitSteamSinter(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getUnitSteamTl(), ""));
            obj.setEight(NumberUtil.toStr(datum.getUnitSteamTotal(), ""));
            obj.setNine(NumberUtil.toStr(datum.getConResteamOut(), ""));
            obj.setTen(NumberUtil.toStr(datum.getConResteamHigh(), ""));
            obj.setEleven(NumberUtil.toStr(datum.getConResteamTotal(), ""));
            obj.setTwelve(NumberUtil.toStr(datum.getConResteamRe(), ""));
            obj.setThirteen(NumberUtil.toStr(datum.getConNitrogen(), ""));
            obj.setFourteen(NumberUtil.toStr(datum.getUnitNitrogen(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData5(List<ReportEnergyConsumeEntity> dataList) {
        if (CollectionUtil.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        ExcelData obj = new ExcelData();
        obj.setOne(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getYield).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwo(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConEleSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThree(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConEleTltx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFour(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getEleCoe).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFive(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConEleTx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSix(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitEleTx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeven(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getEleTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEight(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitEleSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNine(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitEleTltx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitEleTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEleven(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConWindSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwelve(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConWindTltx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThirteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getWindCoe).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFourteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConWindTx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFifteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitWindTx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSixteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getWindTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeventeen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitWindSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEighteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitWindTltx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNineteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitWindTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        return CollectionUtil.toList(obj);
    }

    private List<ExcelData> getData6(List<ReportEnergyConsumeEntity> dataList) {
        if (CollectionUtil.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        ExcelData obj = new ExcelData();
        obj.setOne(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConCokeSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwo(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConCokeTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThree(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConCokeTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFour(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitCokeSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFive(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitCokeTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSix(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitCokeTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeven(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConBlastTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEight(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitBlastTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNine(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConNh2oSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConNh2oTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEleven(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConNh2oTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwelve(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitNh2oSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThirteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitNh2oTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFourteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitNh2oTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFifteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConSewageSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSixteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitSewageSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeventeen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConSteamSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEighteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConSteamTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNineteen(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConSteamTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwenty(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitSteamSinter).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwentyOne(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitSteamTl).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwentyTwo(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitSteamTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        return CollectionUtil.toList(obj);
    }

    private List<ExcelData> getData7(List<ReportEnergyConsumeEntity> dataList) {
        if (CollectionUtil.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        ExcelData obj = new ExcelData();
        obj.setOne(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConResteamOut).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwo(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConResteamHigh).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThree(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConResteamTotal).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFour(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConResteamRe).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFive(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getConNitrogen).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSix(NumberUtil.toStr(dataList.stream().map(ReportEnergyConsumeEntity::getUnitNitrogen).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        return CollectionUtil.toList(obj);
    }
}
