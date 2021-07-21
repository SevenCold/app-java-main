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
@TableName("APP_ENP_PROD_DATA")
public class AppEnpProdDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 机头除尘NOx浓度
	 */
	@TableField("plc_nose_nox")
private BigDecimal plcNoseNox;
	/**
	 * 机头除尘SOx浓度
	 */
	@TableField("plc_nose_sox")
private BigDecimal plcNoseSox;
	/**
	 * 机头除尘O2浓度
	 */
	@TableField("plc_nose_o2")
private BigDecimal plcNoseO2;
	/**
	 * 1#电除尘出口粉尘浓度
	 */
	@TableField("plc_dust_1_c")
private BigDecimal plcDust1C;
	/**
	 * 1#电除尘进风口压力
	 */
	@TableField("plc_1nose_in_pt")
private BigDecimal plc1noseInPt;
	/**
	 * 1#电除尘出风口压力
	 */
	@TableField("plc_1nose_out_pt")
private BigDecimal plc1noseOutPt;
	/**
	 * 1#电除尘进风口温度
	 */
	@TableField("plc_1nose_in_te")
private BigDecimal plc1noseInTe;
	/**
	 * 1#电除尘出风口温度
	 */
	@TableField("plc_1nose_out_te")
private BigDecimal plc1noseOutTe;
	/**
	 * 2#电除尘出口粉尘浓度
	 */
	@TableField("plc_dust_2_c")
private BigDecimal plcDust2C;
	/**
	 * 2#电除尘进风口压力
	 */
	@TableField("plc_2nose_in_pt")
private BigDecimal plc2noseInPt;
	/**
	 * 2#电除尘出风口压力
	 */
	@TableField("plc_2nose_out_pt")
private BigDecimal plc2noseOutPt;
	/**
	 * 2#电除尘进风口温度
	 */
	@TableField("plc_2nose_in_te")
private BigDecimal plc2noseInTe;
	/**
	 * 2#电除尘出风口温度
	 */
	@TableField("plc_2nose_out_te")
private BigDecimal plc2noseOutTe;
	/**
	 * 1#脱硫风机电流
	 */
	@TableField("plc_fgd1_fan_ec")
private BigDecimal plcFgd1FanEc;
	/**
	 * 1#脱硫风机频率
	 */
	@TableField("plc_fgd1_fan_fq")
private BigDecimal plcFgd1FanFq;
	/**
	 * 1#脱硫塔负压1
	 */
	@TableField("plc_fgd1_p1")
private BigDecimal plcFgd1P1;
	/**
	 * 1#脱硫塔负压2
	 */
	@TableField("plc_fgd1_p2")
private BigDecimal plcFgd1P2;
	/**
	 * 1#脱硫塔差压1
	 */
	@TableField("plc_fgd1_dp1")
private BigDecimal plcFgd1Dp1;
	/**
	 * 1#脱硫塔差压2
	 */
	@TableField("plc_fgd1_dp2")
private BigDecimal plcFgd1Dp2;
	/**
	 * 1#脱硫塔顶温度1
	 */
	@TableField("plc_fgd1_ti1")
private BigDecimal plcFgd1Ti1;
	/**
	 * 1#脱硫塔顶温度2
	 */
	@TableField("plc_fgd1_ti2")
private BigDecimal plcFgd1Ti2;
	/**
	 * 1#脱硫塔入口温度
	 */
	@TableField("plc_fgd1_in_ti")
private BigDecimal plcFgd1InTi;
	/**
	 * 1#脱硫塔出口温度
	 */
	@TableField("plc_fgd1_out_ti")
private BigDecimal plcFgd1OutTi;
	/**
	 * 1#脱硫入口粉尘
	 */
	@TableField("plc_fgd1_in_dt")
private BigDecimal plcFgd1InDt;
	/**
	 * 1#脱硫出口粉尘
	 */
	@TableField("plc_fgd1_out_dt")
private BigDecimal plcFgd1OutDt;
	/**
	 * 1#脱硫除尘器差压
	 */
	@TableField("plc_fgd1_dt_dp")
private BigDecimal plcFgd1DtDp;
	/**
	 * 1#脱硫入口SO2
	 */
	@TableField("plc_fgd1_out_so2")
private BigDecimal plcFgd1OutSo2;
	/**
	 * 1#脱硫出口SO2
	 */
	@TableField("plc_fgd1_in_so2")
private BigDecimal plcFgd1InSo2;
	/**
	 * 1#脱硫入口NOx
	 */
	@TableField("plc_fgd1_in_no")
private BigDecimal plcFgd1InNo;
	/**
	 * 1#脱硫出口NOx
	 */
	@TableField("plc_fgd1_out_no")
private BigDecimal plcFgd1OutNo;
	/**
	 * 1#脱硫出口O2
	 */
	@TableField("plc_fgd1_out_o2")
private BigDecimal plcFgd1OutO2;
	/**
	 * 2#脱硫风机电流
	 */
	@TableField("plc_fgd2_fan_ec")
private BigDecimal plcFgd2FanEc;
	/**
	 * 2#脱硫风机频率
	 */
	@TableField("plc_fgd2_fan_fq")
private BigDecimal plcFgd2FanFq;
	/**
	 * 2#脱硫塔负压1
	 */
	@TableField("plc_fgd2_p1")
private BigDecimal plcFgd2P1;
	/**
	 * 2#脱硫塔负压2
	 */
	@TableField("plc_fgd2_p2")
private BigDecimal plcFgd2P2;
	/**
	 * 2#脱硫塔差压1
	 */
	@TableField("plc_fgd2_dp1")
private BigDecimal plcFgd2Dp1;
	/**
	 * 2#脱硫塔差压2
	 */
	@TableField("plc_fgd2_dp2")
private BigDecimal plcFgd2Dp2;
	/**
	 * 2#脱硫塔顶温度1
	 */
	@TableField("plc_fgd2_ti1")
private BigDecimal plcFgd2Ti1;
	/**
	 * 2#脱硫塔顶温度2
	 */
	@TableField("plc_fgd2_ti2")
private BigDecimal plcFgd2Ti2;
	/**
	 * 2#脱硫塔入口温度
	 */
	@TableField("plc_fgd2_in_ti")
private BigDecimal plcFgd2InTi;
	/**
	 * 2#脱硫塔出口温度
	 */
	@TableField("plc_fgd2_out_ti")
private BigDecimal plcFgd2OutTi;
	/**
	 * 2#脱硫入口粉尘
	 */
	@TableField("plc_fgd2_in_dt")
private BigDecimal plcFgd2InDt;
	/**
	 * 2#脱硫出口粉尘
	 */
	@TableField("plc_fgd2_out_dt")
private BigDecimal plcFgd2OutDt;
	/**
	 * 2#脱硫除尘器差压
	 */
	@TableField("plc_fgd2_dt_dp")
private BigDecimal plcFgd2DtDp;
	/**
	 * 2#脱硫入口SO2
	 */
	@TableField("plc_fgd2_out_so2")
private BigDecimal plcFgd2OutSo2;
	/**
	 * 2#脱硫出口SO2
	 */
	@TableField("plc_fgd2_in_so2")
private BigDecimal plcFgd2InSo2;
	/**
	 * 2#脱硫入口NOx
	 */
	@TableField("plc_fgd2_in_no")
private BigDecimal plcFgd2InNo;
	/**
	 * 2#脱硫出口NOx
	 */
	@TableField("plc_fgd2_out_no")
private BigDecimal plcFgd2OutNo;
	/**
	 * 2#脱硫出口O2
	 */
	@TableField("plc_fgd2_out_o2")
private BigDecimal plcFgd2OutO2;

	@TableField(exist = false)
	private String timeStr;

}
