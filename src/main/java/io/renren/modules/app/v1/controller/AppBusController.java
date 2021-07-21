package io.renren.modules.app.v1.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.common.utils.SqlUtils;
import io.renren.modules.app.v1.entity.*;
import io.renren.modules.app.v1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 工艺参数状态监控表 采集周期1分钟
 *
 * @author kang
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 13:19:38
 */
@RestController
@RequestMapping("/v1/app/bus")
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


    /**
     * 列表
     */
    @RequestMapping("/art")
    public R list(@RequestParam Map<String, Object> params){
        List<McRealMonitor1minEntity> realArt = appBusService.getRealArt(params);
        return R.ok(realArt);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		McRealMonitor1minEntity mcRealMonitor1min = appBusService.getById(id);
        return R.ok().put("mcRealMonitor1min", mcRealMonitor1min);
    }

    @RequestMapping("/peihun/table")
    public R peiHunTable() {
        List<AppMixProdDataEntity> list = appMixProdDataService.lambdaQuery()
                .orderByDesc(AppMixProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/peihun/line")
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

    @RequestMapping("/shaojie")
    public R shaojie() {
        List<AppSinProdDataEntity> list = appSinProdDataService.lambdaQuery()
                .orderByDesc(AppSinProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/shaojie/line")
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

    @RequestMapping("/shaileng")
    public R shaileng() {
        List<AppSccProdDataEntity> list = appSccProdDataService.lambdaQuery()
                .orderByDesc(AppSccProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/zhuchou/line")
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

    @RequestMapping("/chengpin/line")
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

    @RequestMapping("/huanbao")
    public R huanbao() {
        List<AppEnpProdDataEntity> list = appEnpProdDataService.lambdaQuery()
                .orderByDesc(AppEnpProdDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/tingji")
    public R tingji() {
        // 查询一个月内的停机记录
        String now = DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        String lastMonth = DateUtil.lastMonth().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        List<AppSinRunStopEntity> list = appSinRunStopService.lambdaQuery()
                .apply(SqlUtils.getGtTimeSql("timestamp", lastMonth, Constant.MINUTE_FORMAT))
                .apply(SqlUtils.getLtTimeSql("timestamp", now, Constant.MINUTE_FORMAT))
                .orderByDesc(AppSinRunStopEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @RequestMapping("/shijian")
    public R shijian() {
        // 查询一个月内的事件记录
        String now = DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        String lastMonth = DateUtil.lastMonth().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        List<AppEventInforEntity> list = appEventInforService.lambdaQuery()
                .apply(SqlUtils.getGtTimeSql("timestamp", lastMonth, Constant.MINUTE_FORMAT))
                .apply(SqlUtils.getLtTimeSql("timestamp", now, Constant.MINUTE_FORMAT))
                .orderByDesc(AppEventInforEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @RequestMapping("/zhuangtai")
    public R zhuangtai() {
        List<AppRTCurveEntity> list = appRTCurveService.lambdaQuery()
                .orderByDesc(AppRTCurveEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/changliang/day")
    public R changliangDay() {
        //String nowDay = DateUtil.date().toString(DatePattern.NORM_DATE_FORMAT);
        String nowDay = "2021-07-14";
        List<AppPopcalOutEntity> list = appPopcalOutService.lambdaQuery()
                .apply(SqlUtils.getEtTimeSql("timestamp", nowDay, Constant.DAY_FORMAT))
                .orderByDesc(AppPopcalOutEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/changliang/month")
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

    @RequestMapping("/changliang/year")
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

    @RequestMapping("/yuanliao")
    public R yuanliao(@RequestParam Map<String, Object> params) {
        List<AppRmcModelDataEntity> list = appRmcModelDataService.lambdaQuery()
                .apply(params.containsKey(Constant.START),
                        SqlUtils.getGtTimeSql("timestamp", params.get(Constant.START), Constant.MINUTE_FORMAT))
                .apply(params.containsKey(Constant.END),
                        SqlUtils.getLtTimeSql("timestamp", params.get(Constant.END), Constant.MINUTE_FORMAT))
                .orderByAsc(AppRmcModelDataEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @RequestMapping("/chengfen")
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

    @RequestMapping("/chengpinFx")
    public R chengpinFx() {
        List<AppSinterAnalysisEntity> list = appSinterAnalysisService.lambdaQuery()
                // 至今一个月
                .apply("timestamp between date_sub(now(),interval 1 month) and now()")
                .orderByDesc(AppSinterAnalysisEntity::getTimestamp)
                .list();
        return R.ok(list);
    }

    @RequestMapping("/peiliao")
    public R peiliao() {
        List<AppMatModelDataEntity> list = appMatModelDataService.lambdaQuery()
                .orderByDesc(AppMatModelDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/peiliao/line")
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

    @RequestMapping("/lidu")
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

    @RequestMapping("/taiche")
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
                    .orderByDesc(AppTroModelDataEntity::getTrolleyNum)
                    .last("limit 1")
                    .list();
        }
        return R.ok(list);
    }

    @RequestMapping("/jiwei")
    public R jiwei() {
        List<AppJwcxModelDataEntity> list = appJwcxModelDataService.lambdaQuery()
                .select(AppJwcxModelDataEntity::getTimestamp, AppJwcxModelDataEntity::getBurnState, AppJwcxModelDataEntity::getUfmState, AppJwcxModelDataEntity::getEdgeState,
                        AppJwcxModelDataEntity::getReturnState, AppJwcxModelDataEntity::getAirPermState, AppJwcxModelDataEntity::getCalProFeoState)
                .orderByDesc(AppJwcxModelDataEntity::getTimestamp)
                .last("limit 1")
                .list();
        return R.ok(list);
    }

    @RequestMapping("/jiwei/line")
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
