package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-16 10:35:19
 */
@Data
@TableName("APP_R_T_CURVE")
public class AppRTCurveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 1#主抽频率
	 */
	@TableField("plc_ma_fan_1_sp")
private BigDecimal plcMaFan1Sp;
	/**
	 * 2#主抽频率
	 */
	@TableField("plc_ma_fan_2_sp")
private BigDecimal plcMaFan2Sp;
	/**
	 * 1#主抽温度
	 */
	@TableField("plc_ma_sb_1_flue_te")
private BigDecimal plcMaSb1FlueTe;
	/**
	 * 2#主抽温度
	 */
	@TableField("plc_ma_sb_2_flue_te")
private BigDecimal plcMaSb2FlueTe;
	/**
	 * 1#主抽负压
	 */
	@TableField("plc_ma_sb_1_flue_pt")
private BigDecimal plcMaSb1FluePt;
	/**
	 * 2#主抽负压
	 */
	@TableField("plc_ma_sb_2_flue_pt")
private BigDecimal plcMaSb2FluePt;
	/**
	 * 1#主抽风量
	 */
	@TableField("plc_ma_sb_1_flue_ft")
private BigDecimal plcMaSb1FlueFt;
	/**
	 * 2#主抽风量
	 */
	@TableField("plc_ma_sb_2_flue_ft")
private BigDecimal plcMaSb2FlueFt;
	/**
	 * 终点位置
	 */
	@TableField("btpcal_avg_x_btp")
private BigDecimal btpcalAvgXBtp;
	/**
	 * 布料厚度
	 */
	@TableField("plc_thick_pv")
private BigDecimal plcThickPv;
	/**
	 * 点火温度
	 */
	@TableField("t_ig_avg_te")
private BigDecimal tIgAvgTe;
	/**
	 * 总料量
	 */
	@TableField("plc_t_sp_w")
private BigDecimal plcTSpW;
	/**
	 * 一混加水量
	 */
	@TableField("plc_1m_ft_sp")
private BigDecimal plc1mFtSp;
	/**
	 * 二混加水量
	 */
	@TableField("plc_2m_ft_sp")
private BigDecimal plc2mFtSp;
	/**
	 * 混合料仓位
	 */
	@TableField("plc_blend_level")
private BigDecimal plcBlendLevel;
	/**
	 * 圆辊转速
	 */
	@TableField("plc_stick_sp")
private BigDecimal plcStickSp;
	/**
	 * 烧结机机速
	 */
	@TableField("plc_sin_speed_sp")
private BigDecimal plcSinSpeedSp;
	/**
	 * 环冷机机速
	 */
	@TableField("plc_rc_speed_sp")
private BigDecimal plcRcSpeedSp;
	/**
	 * 漏风率
	 */
	@TableField("cal_air_r_c")
private BigDecimal calAirRC;

	@TableField(exist = false)
	private String timeStr;

}
