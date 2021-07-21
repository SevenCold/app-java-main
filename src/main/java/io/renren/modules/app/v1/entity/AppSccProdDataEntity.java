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
 * @date 2021-07-16 10:35:21
 */
@Data
@TableName("APP_SCC_PROD_DATA")
public class AppSccProdDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 环冷机速(HZ)
	 */
	@TableField("plc_rc_speed_pv_hz")
private BigDecimal plcRcSpeedPvHz;
	/**
	 * 环冷机速(m/min)
	 */
	@TableField("plc_rc_speed_pv")
private BigDecimal plcRcSpeedPv;
	/**
	 * 环冷机入口温度
	 */
	@TableField("plc_rc_in_te")
private BigDecimal plcRcInTe;
	/**
	 * 环冷机出口温度
	 */
	@TableField("plc_rc_out_te")
private BigDecimal plcRcOutTe;
	/**
	 * 环冷机烟罩温度1
	 */
	@TableField("plc_rc_hood_te1")
private BigDecimal plcRcHoodTe1;
	/**
	 * 环冷机烟罩温度2
	 */
	@TableField("plc_rc_hood_te2")
private BigDecimal plcRcHoodTe2;
	/**
	 * 1#鼓风机开度
	 */
	@TableField("plc_rc_b_zt_1_pv")
private BigDecimal plcRcBZt1Pv;
	/**
	 * 2#鼓风机开度
	 */
	@TableField("plc_rc_b_zt_2_pv")
private BigDecimal plcRcBZt2Pv;
	/**
	 * 3#鼓风机开度
	 */
	@TableField("plc_rc_b_zt_3_pv")
private BigDecimal plcRcBZt3Pv;
	/**
	 * 4#鼓风机开度
	 */
	@TableField("plc_rc_b_zt_4_pv")
private BigDecimal plcRcBZt4Pv;
	/**
	 * 卸矿漏斗称重
	 */
	@TableField("plc_rc_io_w")
private BigDecimal plcRcIoW;
	/**
	 * 板式给矿机转速
	 */
	@TableField("plc_pf_speed_pv")
private BigDecimal plcPfSpeedPv;

	@TableField(exist = false)
	private String timeStr;

}
