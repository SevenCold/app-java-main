package io.renren.modules.app.v1.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.common.utils.SqlUtils;
import io.renren.modules.app.v1.annotation.Login;
import io.renren.modules.app.v1.entity.*;
import io.renren.modules.app.v1.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工艺参数状态监控表 采集周期1分钟
 *
 * @author kang
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 13:19:38
 */
@RestController
@RequestMapping("/app/v1/bus")
@Api("APP业务信息接口")
public class AppBusController {

    @Autowired
    private AppBusService appBusService;

    @Autowired
    private AppMixProdDataService appMixProdDataService;
    @Autowired
    private AppSinProdDataService appSinProdDataService;
    @Autowired
    private AppSccProdDataService appSccProdDataService;
    @Autowired
    private AppMpuProdDataService appMpuProdDataService;
    @Autowired
    private AppFpsProdDataService appFpsProdDataService;
    @Autowired
    private AppEnpProdDataService appEnpProdDataService;
    @Autowired
    private AppSinRunStopService appSinRunStopService;
    @Autowired
    private AppEventInforService appEventInforService;
    @Autowired
    private AppRTCurveService appRTCurveService;
    @Autowired
    private AppPopcalOutService appPopcalOutService;
    @Autowired
    private AppRmcModelDataService appRmcModelDataService;
    @Autowired
    private AppElectrityDataService appElectricService;
    @Autowired
    private AppMaterialAnalysisService appMaterialAnalysisService;
    @Autowired
    private AppSinterAnalysisService appSinterAnalysisService;
    @Autowired
    private AppMatModelDataService appMatModelDataService;
    @Autowired
    private AppPsmModelDataService appPsmModelDataService;
    @Autowired
    private AppTroModelDataService appTroModelDataService;
    @Autowired
    private AppJwcxModelDataService appJwcxModelDataService;

    @Login
    @GetMapping("/peihun/table")
    @ApiOperation("【主页-配混】最新一条数据")
    public R peiHunTable() {
        List<AppMixProdDataEntity> list = appMixProdDataService.lambdaQuery()
                .orderByDesc(AppMixProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @Login
    @GetMapping("/peihun/line")
    @ApiOperation("【主页-配混】曲线信息")
    public R peiHunLine(@RequestParam Map<String, Object> params) throws ParseException {
        List<AppMixProdDataEntity> list = appMixProdDataService.lambdaQuery()
                .select(AppMixProdDataEntity::getTimestamp, AppMixProdDataEntity::getPlc1mBW,
                        AppMixProdDataEntity::getPlc1mFtPv, AppMixProdDataEntity::getPlc1mPt,
                        AppMixProdDataEntity::getPlc1mAWaterPv, AppMixProdDataEntity::getPlc1mAW,
                        AppMixProdDataEntity::getPlc1mABSignal3, AppMixProdDataEntity::getPlc2mABW,
                        AppMixProdDataEntity::getPlc2mAW)
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppMixProdDataEntity::getTimestamp)
                .list();

        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/shaojie")
    @Login
    @ApiOperation("【主页-烧结】最新一条数据")
    public R shaojie() {
        List<AppSinProdDataEntity> list = appSinProdDataService.lambdaQuery()
                .orderByDesc(AppSinProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/shaojie/line")
    @Login
    @ApiOperation("【主页-烧结】图表信息")
    public R shaojieLine(@RequestParam Map<String, Object> params) throws ParseException {
        List<AppSinProdDataEntity> list = appSinProdDataService.lambdaQuery()
                .select(AppSinProdDataEntity::getTimestamp, AppSinProdDataEntity::getPlcXTrpOut,
                        AppSinProdDataEntity::getPlcTrpTeOut, AppSinProdDataEntity::getPlcXBrpOut,
                        AppSinProdDataEntity::getPlcBrpTeOut, AppSinProdDataEntity::getPlcXBtpOut,
                        AppSinProdDataEntity::getPlcBtpTeOut)
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppSinProdDataEntity::getTimestamp)
                .list();

        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/shaileng")
    @Login
    @ApiOperation("【主页-筛冷】最新一条数据")
    public R shaileng() {
        List<AppSccProdDataEntity> list = appSccProdDataService.lambdaQuery()
                .orderByDesc(AppSccProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/zhuchou/line")
    @Login
    @ApiOperation("【主页-主抽】图表数据")
    public R zhuchou(@RequestParam Map<String, Object> params) {
        List<AppMpuProdDataEntity> list = appMpuProdDataService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppMpuProdDataEntity::getTimestamp)
                .list();

        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/chengpin/line")
    @Login
    @ApiOperation("【主页-成品】图表数据")
    public R chengpin(@RequestParam Map<String, Object> params) {
        List<AppFpsProdDataEntity> list = appFpsProdDataService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppFpsProdDataEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/huanbao")
    @Login
    @ApiOperation("【主页-环保】最新一条数据")
    public R huanbao() {
        List<AppEnpProdDataEntity> list = appEnpProdDataService.lambdaQuery()
                .orderByDesc(AppEnpProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/tingji")
    @Login
    @ApiOperation("【主页-停机记录】一个月内记录")
    public R tingji(@RequestParam Map<String, Object> params) {
        // 查询一个月内的停机记录
        String now = DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        String lastMonth = DateUtil.lastMonth().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        params.put(Constant.START, lastMonth);
        params.put(Constant.END, now);
        IPage<AppSinRunStopEntity> page = appSinRunStopService.queryPage(params);
        return R.ok(page.getRecords());
    }

    @GetMapping("/shijian")
    @Login
    @ApiOperation("【主页-事件记录】一个月内记录")
    public R shijian(@RequestParam Map<String, Object> params) {
        // 查询一个月内的事件记录
        String now = DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        String lastMonth = DateUtil.lastMonth().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        params.put(Constant.START, lastMonth);
        params.put(Constant.END, now);
        IPage<AppEventInforEntity> page = appEventInforService.queryPage(params);
        return R.ok(page.getRecords());
    }

    @GetMapping("/monitor")
    @Login
    @ApiOperation("【状态监控】图表数据")
    public R zhuangtai(@RequestParam Map<String, Object> params) {
        List<AppRTCurveEntity> list = appRTCurveService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppRTCurveEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/changliang/day")
    @Login
    @ApiOperation("【分析-产量】最新一条记录")
    public R changliangDay() {
        String nowDay = DateUtil.date().toString(DatePattern.NORM_DATE_FORMAT);
        List<AppPopcalOutEntity> list = appPopcalOutService.lambdaQuery()
                .apply(SqlUtils.getEtTimeSql("timestamp", nowDay, Constant.DAY_FORMAT))
                .orderByDesc(AppPopcalOutEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/changliang/month")
    @Login
    @ApiOperation("【分析-产量】至今一个月图表数据")
    public R changliangMonth() {
        List<AppPopcalOutEntity> list = appPopcalOutService.lambdaQuery()
                .select(AppPopcalOutEntity::getAAAlOutput, AppPopcalOutEntity::getTimestamp, AppPopcalOutEntity::getAPAlOutput)
                // 至今一个月
                .apply("timestamp between date_sub(now(),interval 1 month) and now()")
                .orderByAsc(AppPopcalOutEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(e -> {
                e.setTimeStr(DateUtil.format(e.getTimestamp(), "MM/dd"));
                e.setTimestamp(null);
            });
        }
        return R.ok(list);
    }

    @GetMapping("/changliang/year")
    @Login
    @ApiOperation("【分析-产量】至今一年图表数据")
    public R changliangYear() {
        List<AppPopcalOutEntity> list = appPopcalOutService.getEveryMonthData();
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(e -> {
                e.setTimeStr(DateUtil.format(e.getTimestamp(), "MM月"));
                e.setTimestamp(null);
            });
        }
        return R.ok(list);
    }

    @GetMapping("/yuanliao")
    @Login
    @ApiOperation("【分析-原料消耗】表格数据")
    public R yuanliao(@RequestParam Map<String, Object> params) {
        List<AppRmcModelDataEntity> list = appRmcModelDataService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByDesc(AppRmcModelDataEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @GetMapping("/chengfen")
    @Login
    @ApiOperation("【分析-原料成分】表格数据")
    public R chengfen(@RequestParam Map<String, Object> params) {
        List<AppMaterialAnalysisEntity> list = appMaterialAnalysisService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByDesc(AppMaterialAnalysisEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @GetMapping("/dianbiao")
    @Login
    @ApiOperation("【分析-电表】表格数据")
    public R dianbiao(@RequestParam Map<String, Object> params) {
        List<AppElectrityDataEntity> list = appElectricService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByDesc(AppElectrityDataEntity::getTimestamp)
                .list();
        Map<String, List<AppElectrityDataEntity>> timeMap = new HashMap<>();
        for (AppElectrityDataEntity entity : list) {
            String key = DateUtil.format(entity.getTimestamp(), DatePattern.NORM_DATE_FORMAT);
            if (timeMap.containsKey(key)) {
                timeMap.get(key).add(entity);
            } else {
                timeMap.put(key, CollectionUtil.newArrayList(entity));
            }
        }
        List<String> names = list.stream().map(AppElectrityDataEntity::getMeterName).collect(Collectors.toList());
        HashSet<String> columns = CollectionUtil.newHashSet(true, names);
        List<Map<String, String>> dataList = new ArrayList<>();
        timeMap.forEach((key, value) -> {
            Map<String, String> map = new HashMap<>();
            map.put("time", key);
            value.sort(Comparator.comparing(AppElectrityDataEntity::getMeterName));
            for (String column : columns) {
                for (AppElectrityDataEntity entity : value) {
                    if (column.equals(entity.getMeterName())) {
                        map.put(column + "-consume", entity.getMeterConsume() + "");
                        map.put(column + "-single", entity.getMeterSingal() + "");
                    }
                }
            }
            dataList.add(map);
        });
        R r = R.ok();
        r.put("columns", columns);
        r.put("data", dataList);
        return r;
    }

    @GetMapping("/chengpinFx")
    @Login
    @ApiOperation("【分析-成品】一个月数据")
    public R chengpinFx(@RequestParam Map<String, Object> params) {
        IPage<AppSinterAnalysisEntity> page = appSinterAnalysisService.queryPage(params);
        return R.ok(page.getRecords());
    }

    @GetMapping("/peiliao")
    @Login
    @ApiOperation("【分析-配料】最新一条数据")
    public R peiliao() {
        List<AppMatModelDataEntity> list = appMatModelDataService.lambdaQuery()
                .orderByDesc(AppMatModelDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/peiliao/line")
    @Login
    @ApiOperation("【分析-配料】图表数据")
    public R peiliaoLine(@RequestParam Map<String, Object> params) {
        List<AppMatModelDataEntity> list = appMatModelDataService.lambdaQuery()
                .select(AppMatModelDataEntity::getTimestamp,AppMatModelDataEntity::getL2PbWet1, AppMatModelDataEntity::getL2PbWet2, AppMatModelDataEntity::getL2PbWet3,
                        AppMatModelDataEntity::getL2PbWet4, AppMatModelDataEntity::getL2PbWet5, AppMatModelDataEntity::getL2PbWet6,
                        AppMatModelDataEntity::getL2PbWet7, AppMatModelDataEntity::getL2PbWet8)
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppMatModelDataEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }

    @GetMapping("/lidu")
    @Login
    @ApiOperation("【分析-粒度】图表数据")
    public R lidu(@RequestParam Map<String, Object> params) {
        List<AppPsmModelDataEntity> list = appPsmModelDataService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppPsmModelDataEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok(list);
    }

    @GetMapping("/taiche")
    @Login
    @ApiOperation("【分析-台车】表格数据")
    public R taiche(@RequestParam Map<String, Object> params) {
        Object no = params.get("no");
        List<AppTroModelDataEntity> list;
        if (no == null) {
            // 默认查询当前位置的台车
            list = appTroModelDataService.lambdaQuery()
                    .eq(AppTroModelDataEntity::getIsAtCameraPos, 1)
                    .orderByDesc(AppTroModelDataEntity::getTrolleyNum)
                    .last("limit 1")
                    .list();
        } else if (((String)no).equals("all")) {
            // 查询全部台车
            list = appTroModelDataService.lambdaQuery()
                    .orderByAsc(AppTroModelDataEntity::getTrolleyNum)
                    .list();
        } else {
            // 查询指定编号的台车
            list = appTroModelDataService.lambdaQuery()
                    .eq(AppTroModelDataEntity::getTrolleyNum, no)
                    .orderByAsc(AppTroModelDataEntity::getTrolleyNum)
                    .last("limit 1")
                    .list();
        }
        return R.ok(list);
    }

    @GetMapping("/jiwei")
    @Login
    @ApiOperation("【分析-机尾】最新一条数据")
    public R jiwei() {
        List<AppJwcxModelDataEntity> list = appJwcxModelDataService.lambdaQuery()
                .select(AppJwcxModelDataEntity::getTimestamp, AppJwcxModelDataEntity::getBurnState, AppJwcxModelDataEntity::getUfmState, AppJwcxModelDataEntity::getEdgeState,
                        AppJwcxModelDataEntity::getReturnState, AppJwcxModelDataEntity::getAirPermState, AppJwcxModelDataEntity::getCalProFeoState)
                .orderByDesc(AppJwcxModelDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @GetMapping("/jiwei/line")
    @Login
    @ApiOperation("【分析-机尾】图表数据")
    public R jiweiLine(@RequestParam Map<String, Object> params) {
        List<AppJwcxModelDataEntity> list = appJwcxModelDataService.lambdaQuery()
                .select(AppJwcxModelDataEntity::getTimestamp, AppJwcxModelDataEntity::getMaxTemp, AppJwcxModelDataEntity::getMinTemp,
                        AppJwcxModelDataEntity::getAvgTemp, AppJwcxModelDataEntity::getHat, AppJwcxModelDataEntity::getVssC,
                        AppJwcxModelDataEntity::getCbt, AppJwcxModelDataEntity::getRfa, AppJwcxModelDataEntity::getBurn, AppJwcxModelDataEntity::getEdgeAvg)
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppJwcxModelDataEntity::getTimestamp)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
            list.forEach(e -> {
                e.setTimeStr(hdf.format(e.getTimestamp()));
                e.setTimestamp(null);
            });
        }
        return R.ok().put("data", JSONUtil.toJsonStr(list));
    }
}
