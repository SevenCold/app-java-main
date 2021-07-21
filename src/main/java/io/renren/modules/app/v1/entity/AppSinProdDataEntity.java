package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-16 10:35:21
 */
@Data
@TableName("APP_SIN_PROD_DATA")
public class AppSinProdDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 混合料仓料位
	 */
	@TableField("plc_blend_level")
private BigDecimal plcBlendLevel;
	/**
	 * 混合料温度
	 */
	@TableField("plc_blend_te")
private BigDecimal plcBlendTe;
	/**
	 * 圆辊反馈转速
	 */
	@TableField("plc_stick_pv")
private BigDecimal plcStickPv;
	/**
	 * 烧结机速(HZ)
	 */
	@TableField("plc_sin_speed_pv_hz")
private BigDecimal plcSinSpeedPvHz;
	/**
	 * 烧结机速(m/min)
	 */
	@TableField("plc_sin_speed_pv")
private BigDecimal plcSinSpeedPv;
	/**
	 * 铺底料仓料位
	 */
	@TableField("plc_bed_materal_w")
private BigDecimal plcBedMateralW;
	/**
	 * 总料厚度
	 */
	@TableField("plc_thick_pv")
private BigDecimal plcThickPv;
	/**
	 * 煤气流量
	 */
	@TableField("plc_ig_gas_pv")
private BigDecimal plcIgGasPv;
	/**
	 * 空气流量
	 */
	@TableField("plc_ig_air_pv")
private BigDecimal plcIgAirPv;
	/**
	 * 点火温度1
	 */
	@TableField("plc_ig_01_te")
private BigDecimal plcIg01Te;
	/**
	 * 点火温度2
	 */
	@TableField("plc_ig_02_te")
private BigDecimal plcIg02Te;
	/**
	 * TRP实际位置
	 */
	@TableField("plc_x_trp_out")
private BigDecimal plcXTrpOut;
	/**
	 * TRP位置风箱
	 */
	@TableField("plc_trp_box_num_out")
private BigDecimal plcTrpBoxNumOut;
	/**
	 * TRP位置温度
	 */
	@TableField("plc_trp_te_out")
private BigDecimal plcTrpTeOut;
	/**
	 * BRP目标位置
	 */
	@TableField("plc_aim_brp_out")
private BigDecimal plcAimBrpOut;
	/**
	 * BRP实际位置
	 */
	@TableField("plc_x_brp_out")
private BigDecimal plcXBrpOut;
	/**
	 * BRP位置风箱
	 */
	@TableField("plc_brp_box_num_out")
private BigDecimal plcBrpBoxNumOut;
	/**
	 * BRP位置温度
	 */
	@TableField("plc_brp_te_out")
private BigDecimal plcBrpTeOut;
	/**
	 * BTP目标位置
	 */
	@TableField("plc_aim_btp_out")
private BigDecimal plcAimBtpOut;
	/**
	 * BTP实际位置
	 */
	@TableField("plc_x_btp_out")
private BigDecimal plcXBtpOut;
	/**
	 * BTP位置风箱
	 */
	@TableField("plc_btp_box_num_out")
private BigDecimal plcBtpBoxNumOut;
	/**
	 * BTP位置温度
	 */
	@TableField("plc_btp_te_out")
private BigDecimal plcBtpTeOut;

	@TableField(exist = false)
	private String timeStr;

}
