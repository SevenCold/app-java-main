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
import io.renren.modules.excel.entity.ReportMatConsumeEntity;
import io.renren.modules.excel.service.ReportMatConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 3#烧结机布袋除尘器日报表
 */
@Component
public class ConsumeMatWriter implements EasyExcelWriter {

    @Autowired
    private ReportMatConsumeService service;

    @Override
    public void writeExcel(ExcelWriter excelWriter, WriteSheet writeSheet, String month) {
        DateTime queryMonth = DateUtil.parse(month, "yyyy-MM");
        // 每个月后三天 算 下一个月
        DateTime start = DateUtil.beginOfMonth(queryMonth).offset(DateField.DAY_OF_MONTH, -3);;
        DateTime end = DateUtil.endOfMonth(queryMonth).offset(DateField.DAY_OF_MONTH, -3);
        List<ReportMatConsumeEntity> dataList = service.lambdaQuery()
                .between(ReportMatConsumeEntity::getTimestamp, start.toJdkDate(), end.toJdkDate())
                .orderByAsc(ReportMatConsumeEntity::getTimestamp).list();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("data1", getData1(dataList)), fillConfig, writeSheet);
        excelWriter.fill(new FillWrapper("data2", getData2(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data3", getData3(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data4", getData4(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data5", getData5(dataList)), writeSheet);
        excelWriter.fill(new FillWrapper("data6", getData6(dataList)), writeSheet);
        excelWriter.finish();
    }

    private List<ExcelData> getData1(List<ReportMatConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ExcelData obj = new ExcelData();
            obj.setOne(i + 1 + "");
            obj.setTwo(DateUtil.format(data.get(i).getTimestamp(), "yyyy/MM/dd"));
            obj.setThree(NumberUtil.toStr(data.get(i).getYield(), ""));
            obj.setFour(NumberUtil.toStr(data.get(i).getConIron(), ""));
            obj.setFive(NumberUtil.toStr(data.get(i).getUnitIron(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData2(List<ReportMatConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportMatConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getCon320FuelWet(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getCon240FuelWet(), ""));
            obj.setThree(NumberUtil.toStr(datum.getConTotalFuelWet(), ""));
            obj.setFour(NumberUtil.toStr(datum.getConTotalFuel(), ""));
            obj.setFive(NumberUtil.toStr(datum.getConOutFuel(), ""));
            obj.setSix(NumberUtil.toStr(datum.getConPulWet(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getConPulBill(), ""));
            obj.setEight(NumberUtil.toStr(datum.getConBurntBill(), ""));
            obj.setNine(NumberUtil.toStr(datum.getConH2o(), ""));
            obj.setTen(NumberUtil.toStr(datum.getUnitFuelWet(), ""));
            obj.setEleven(NumberUtil.toStr(datum.getUnitBurntWet(), ""));
            obj.setTwelve(NumberUtil.toStr(datum.getConMhWet(), ""));
            obj.setThirteen(NumberUtil.toStr(datum.getUnitFuelDry(), ""));
            obj.setFourteen(NumberUtil.toStr(datum.getUnitBurntDry(), ""));
            obj.setFifteen(NumberUtil.toStr(datum.getConPulDry(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData3(List<ReportMatConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportMatConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getLimeZf(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getLimeCpj(), ""));
            obj.setThree(NumberUtil.toStr(datum.getLimeRm(), ""));
            obj.setFour(NumberUtil.toStr(datum.getLimeBx(), ""));
            obj.setFive(NumberUtil.toStr(datum.getLimeHg(), ""));
            obj.setSix(NumberUtil.toStr(datum.getLimeQt1(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getLimeQt2(), ""));
            obj.setEight(NumberUtil.toStr(datum.getLimeQt3(), ""));
            obj.setNine(NumberUtil.toStr(datum.getTotalLime(), ""));
            obj.setTen(NumberUtil.toStr(datum.getUnitLime(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData4(List<ReportMatConsumeEntity> data) {
        List<ExcelData> res = new ArrayList<>();
        for (ReportMatConsumeEntity datum : data) {
            ExcelData obj = new ExcelData();
            obj.setOne(NumberUtil.toStr(datum.getStoneXp(), ""));
            obj.setTwo(NumberUtil.toStr(datum.getStoneBx(), ""));
            obj.setThree(NumberUtil.toStr(datum.getStoneHg(), ""));
            obj.setFour(NumberUtil.toStr(datum.getStoneFy(), ""));
            obj.setFive(NumberUtil.toStr(datum.getStoneQt1(), ""));
            obj.setSix(NumberUtil.toStr(datum.getStoneQt2(), ""));
            obj.setSeven(NumberUtil.toStr(datum.getStoneQt3(), ""));
            obj.setEight(NumberUtil.toStr(datum.getTotalStone(), ""));
            obj.setNine(NumberUtil.toStr(datum.getUnitStone(), ""));
            obj.setTen(NumberUtil.toStr(datum.getTlLime(), ""));
            obj.setEleven(NumberUtil.toStr(datum.getUnitTlLime(), ""));
            obj.setTwelve(NumberUtil.toStr(datum.getHeadLime(), ""));
            res.add(obj);
        }
        return res;
    }

    private List<ExcelData> getData5(List<ReportMatConsumeEntity> dataList) {
        ExcelData obj = new ExcelData();
        obj.setOne(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getYield).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwo(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConIron).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThree(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitIron).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFour(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getCon320FuelWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFive(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getCon240FuelWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSix(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConTotalFuelWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeven(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConTotalFuel).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEight(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConOutFuel).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNine(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConPulWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConPulBill).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEleven(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConBurntBill).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwelve(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConH2o).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThirteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitFuelWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFourteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitBurntWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFifteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConMhWet).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSixteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitFuelDry).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeventeen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitBurntDry).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEighteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getConPulDry).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        return CollectionUtil.toList(obj);
    }

    private List<ExcelData> getData6(List<ReportMatConsumeEntity> dataList) {
        ExcelData obj = new ExcelData();
        obj.setOne(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeZf).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwo(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeCpj).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThree(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeRm).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFour(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeBx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFive(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeHg).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSix(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeQt1).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeven(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeQt2).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEight(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getLimeQt3).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNine(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getTotalLime).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitLime).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEleven(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneXp).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwelve(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneBx).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setThirteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneHg).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFourteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneFy).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setFifteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneQt1).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSixteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneQt2).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setSeventeen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getStoneQt3).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setEighteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getTotalStone).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setNineteen(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitStone).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwenty(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getTlLime).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwentyOne(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getUnitTlLime).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        obj.setTwentyTwo(NumberUtil.toStr(dataList.stream().map(ReportMatConsumeEntity::getHeadLime).reduce(BigDecimal.ZERO, NumberUtil::add), ""));
        return CollectionUtil.toList(obj);
    }
}
