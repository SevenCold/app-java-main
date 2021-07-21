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
 * @date 2021-07-16 10:35:18
 */
@Data
@TableName("APP_FPS_PROD_DATA")
public class AppFpsProdDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 成-1皮带秤
	 */
	@TableField("plc_c1_fq")
private BigDecimal plcC1Fq;
	/**
	 * 成-2皮带秤
	 */
	@TableField("plc_c2_fq")
private BigDecimal plcC2Fq;
	/**
	 * 成-3皮带秤
	 */
	@TableField("plc_c3_fq")
private BigDecimal plcC3Fq;
	/**
	 * 成-4皮带秤
	 */
	@TableField("plc_c4_fq")
private BigDecimal plcC4Fq;
	/**
	 * 成-5皮带秤
	 */
	@TableField("plc_c5_fq")
private BigDecimal plcC5Fq;
	/**
	 * 成-6皮带秤
	 */
	@TableField("plc_c6_fq")
private BigDecimal plcC6Fq;
	/**
	 * 成-7皮带秤
	 */
	@TableField("plc_c7_fq")
private BigDecimal plcC7Fq;
	/**
	 * 铺-3皮带秤
	 */
	@TableField("plc_p1_fq")
private BigDecimal plcP1Fq;
	/**
	 * 
	 */
	@TableField("plc_p2_fq")
private BigDecimal plcP2Fq;
	/**
	 * 
	 */
	@TableField("plc_p3_fq")
private BigDecimal plcP3Fq;
	/**
	 * 返-1皮带秤
	 */
	@TableField("plc_f1_fq")
private BigDecimal plcF1Fq;
	/**
	 * 返-2皮带秤
	 */
	@TableField("plc_f2_fq")
private BigDecimal plcF2Fq;
	/**
	 * 返-3皮带秤
	 */
	@TableField("plc_f3_fq")
private BigDecimal plcF3Fq;

	@TableField(exist = false)
	private String timeStr;

}
