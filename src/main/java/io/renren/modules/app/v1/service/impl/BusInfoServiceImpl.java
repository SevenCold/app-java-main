package io.renren.modules.app.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.v1.service.BusInfoService;
import io.renren.modules.sys.dao.BusInfoDao;
import io.renren.modules.sys.entity.BusInfoEntity;
import io.renren.modules.sys.entity.EchartsInfoEntity;
import io.renren.modules.sys.entity.EchartsSeries;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * APP查询曲线信息服务
 * @author kang
 */
@Service
public class BusInfoServiceImpl extends ServiceImpl<BusInfoDao, BusInfoEntity> implements BusInfoService {

    @Override
    public EchartsInfoEntity getBusInfoByCondition(Map<String, String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QueryWrapper<BusInfoEntity> queryWrapper = new QueryWrapper<>();
        String startTime = map.get("startTime");
        if (StringUtils.isNotEmpty(startTime)) {
            queryWrapper.ge("tag_time", sdf.parse(startTime));
        }
        String endTime = map.get("endTime");
        if (StringUtils.isNotEmpty(endTime)) {
            queryWrapper.le("tag_time", sdf.parse(endTime));
        }
        String tagName = map.get("name");
        if (StringUtils.isNotEmpty(tagName)) {
            queryWrapper.eq("tag_name", tagName);
        }
        // X轴刻度跨度，单位为秒
        int gap = Integer.parseInt(map.get("gap"));
        List<BusInfoEntity> busList = this.baseMapper.selectList(queryWrapper);
        // 根据标签名分组
        Map<String, List<BusInfoEntity>> groupMap = busList.stream().collect(Collectors.groupingBy(BusInfoEntity::getTagName));
        List<String> categories = new ArrayList<>();    // 横坐标轴数据
        List<EchartsSeries> series = new ArrayList<>();    // 图表series数据
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        for (String tag : groupMap.keySet()) {
            List<BusInfoEntity> list = groupMap.get(tag);
            List<String> dataList = new ArrayList<>();
            int len = Math.min(gap, list.size());
            for (int i = 0; i < len; i++) {
                BusInfoEntity entity = list.get(i);
                // 只收集一次横坐标轴时间信息， 且默认所有标签的时间轴数据一样
                if (categories.size() < len) {
                    categories.add(sdf2.format(entity.getTagTime()));
                }
                dataList.add(entity.getTagValue());
            }
            EchartsSeries echartsSeries = new EchartsSeries();
            echartsSeries.setData(dataList);
            echartsSeries.setName(tag);
            series.add(echartsSeries);
        }
        return new EchartsInfoEntity(categories, series);
    }
}
