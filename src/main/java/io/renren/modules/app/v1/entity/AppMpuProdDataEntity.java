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
 * @date 2021-07-16 10:35:20
 */
@Data
@TableName("APP_MPU_PROD_DATA")
public class AppMpuProdDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 1#主抽开度反馈
	 */
	@TableField("plc_ma_ve_1_pv_od")
private BigDecimal plcMaVe1PvOd;
	/**
	 * 1#主抽烟道压力
	 */
	@TableField("plc_ma_sb_1_flue_pt")
private BigDecimal plcMaSb1FluePt;
	/**
	 * 1#主抽烟道温度
	 */
	@TableField("plc_ma_sb_1_flue_te")
private BigDecimal plcMaSb1FlueTe;
	/**
	 * 1#主抽烟气流量
	 */
	@TableField("plc_ma_sb_1_flue_ft")
private BigDecimal plcMaSb1FlueFt;
	/**
	 * 1#主抽入口压力
	 */
	@TableField("plc_ma_in_1_flue_pt")
private BigDecimal plcMaIn1FluePt;
	/**
	 * 1#主抽出口压力
	 */
	@TableField("plc_ma_out_1_flue_pt")
private BigDecimal plcMaOut1FluePt;
	/**
	 * 1#主抽入口氧含量
	 */
	@TableField("plc_ma_out_1_flue_o2")
private BigDecimal plcMaOut1FlueO2;
	/**
	 * 1#主抽出口粉尘浓度
	 */
	@TableField("plc_ma_out_1_flue_dt")
private BigDecimal plcMaOut1FlueDt;
	/**
	 * 1#主抽出口温度
	 */
	@TableField("plc_ma_out_1_flue_ti")
private BigDecimal plcMaOut1FlueTi;
	/**
	 * 1#主抽电机电流
	 */
	@TableField("plc_ma_1_ec")
private BigDecimal plcMa1Ec;
	/**
	 * 1#主抽电机功率
	 */
	@TableField("plc_ma_1_w")
private BigDecimal plcMa1W;
	/**
	 * 2#主抽开度反馈
	 */
	@TableField("plc_ma_ve_2_pv_od")
private BigDecimal plcMaVe2PvOd;
	/**
	 * 2#主抽烟道压力
	 */
	@TableField("plc_ma_sb_2_flue_pt")
private BigDecimal plcMaSb2FluePt;
	/**
	 * 2#主抽烟道温度
	 */
	@TableField("plc_ma_sb_2_flue_te")
private BigDecimal plcMaSb2FlueTe;
	/**
	 * 2#主抽烟气流量
	 */
	@TableField("plc_ma_sb_2_flue_ft")
private BigDecimal plcMaSb2FlueFt;
	/**
	 * 2#主抽入口压力
	 */
	@TableField("plc_ma_in_2_flue_pi")
private BigDecimal plcMaIn2FluePi;
	/**
	 * 2#主抽出口压力
	 */
	@TableField("plc_ma_out_2_flue_pi")
private BigDecimal plcMaOut2FluePi;
	/**
	 * 2#主抽入口氧含量
	 */
	@TableField("plc_ma_out_2_flue_o2")
private BigDecimal plcMaOut2FlueO2;
	/**
	 * 2#主抽出口粉尘浓度
	 */
	@TableField("plc_ma_out_2_flue_dt")
private BigDecimal plcMaOut2FlueDt;
	/**
	 * 2#主抽出口温度
	 */
	@TableField("plc_ma_out_2_flue_ti")
private BigDecimal plcMaOut2FlueTi;
	/**
	 * 2#主抽电机电流
	 */
	@TableField("plc_ma_2_ec")
private BigDecimal plcMa2Ec;
	/**
	 * 2#主抽电机功率
	 */
	@TableField("plc_ma_2_w")
private BigDecimal plcMa2W;

	@TableField(exist = false)
	private String timeStr;

}
