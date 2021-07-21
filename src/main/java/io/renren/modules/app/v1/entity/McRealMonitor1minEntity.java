package io.renren.modules.app.v1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 工艺参数状态监控表 采集周期1分钟
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 13:19:38
 */
@Data
@TableName("mc_real_monitor_1min")
public class McRealMonitor1minEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 1#主抽风机转速（频率）设定值 单位HZ
	 */
	@TableField("t_plc_ma_fan_1_sp_3s")
	@JSONField(name = "tplcMaFan1Sp3s")
	private BigDecimal tPlcMaFan1Sp3s;
	/**
	 * 2#主抽风机转速（频率）设定值 单位HZ
	 */
	@TableField("t_plc_ma_fan_2_sp_3s")
	@JSONField(name = "tplcMaFan2Sp3s")
	private BigDecimal tPlcMaFan2Sp3s;
	/**
	 * 1#主抽大烟道温度 单位℃
	 */
	@TableField("t_ma_sb_1_flue_te_3s")
	@JSONField(name = "tmaSb1FlueTe3s")
	private BigDecimal tMaSb1FlueTe3s;
	/**
	 * 2#主抽大烟道温度 单位℃
	 */
	@TableField("t_ma_sb_2_flue_te_3s")
	@JSONField(name = "tmaSb2FlueTe3s")
	private BigDecimal tMaSb2FlueTe3s;
	/**
	 * 1#主抽大烟道压力 单位KPa
	 */
	@TableField("t_ma_sb_1_flue_pt_3s")
	@JSONField(name = "tmaSb1FluePt3s")
	private BigDecimal tMaSb1FluePt3s;
	/**
	 * 2#主抽大烟道压力 单位KPa
	 */
	@TableField("t_ma_sb_2_flue_pt_3s")
	@JSONField(name = "tmaSb2FluePt3s")
	private BigDecimal tMaSb2FluePt3s;
	/**
	 * 1#主抽大烟道废气流量 单位m3/min
	 */
	@TableField("t_ma_sb_1_flue_ft_3s")
	@JSONField(name = "tmaSb1FlueFt3s")
	private BigDecimal tMaSb1FlueFt3s;
	/**
	 * 2#主抽大烟道废气流量 单位m3/min
	 */
	@TableField("t_ma_sb_2_flue_ft_3s")
	@JSONField(name = "tmaSb2FlueFt3s")
	private BigDecimal tMaSb2FlueFt3s;
	/**
	 * 终点位置 单位m
	 */
	@JSONField(name = "btpcalOutTotalAvgXBtp")
	private BigDecimal btpcalOutTotalAvgXBtp;
	/**
	 * 布料厚度反馈值 单位mm
	 */
	@JSONField(name = "cthickPv3s")
	@TableField("c_thick_pv_3s")
	private BigDecimal cThickPv3s;
	/**
	 * 点火温度 单位℃
	 */
	@JSONField(name = "tigTe3s")
	@TableField("t_ig_te_3s")
	private BigDecimal tIgTe3s;
	/**
	 * 总料量SP 单位t/h
	 */
	@JSONField(name = "ttotalSpW3s")
	@TableField("t_total_sp_w_3s")
	private BigDecimal tTotalSpW3s;
	/**
	 * 一混加水流量设定值 单位t/h
	 */
	@JSONField(name = "t1mFtSp3s")
	@TableField("t_1m_ft_sp_3s")
	private BigDecimal t1mFtSp3s;
	/**
	 * 混合料仓仓位 单位t
	 */
	@JSONField(name = "tblendLevel3s")
	@TableField("t_blend_level_3s")
	private BigDecimal tBlendLevel3s;
	/**
	 * 圆辊给料机设定转速 单位HZ
	 */
	@JSONField(name = "tstickSp3s")
	@TableField("t_stick_sp_3s")
	private BigDecimal tStickSp3s;
	/**
	 * 烧结机机速设定值 单位m/min
	 */
	@JSONField(name = "tsinMsSp3s")
	@TableField("t_sin_ms_sp_3s")
	private BigDecimal tSinMsSp3s;
	/**
	 * 环冷机机速设定值 单位m/min
	 */
	@JSONField(name = "trcSpeedSp3s")
	@TableField("t_rc_speed_sp_3s")
	private BigDecimal tRcSpeedSp3s;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "HH:mm",timezone="Asia/Shanghai")
	@JSONField(format = "HH:mm")
	private Date createTime;

}
