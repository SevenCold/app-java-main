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
@TableName("APP_MAT_MODEL_DATA")
public class AppMatModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 总料量SP
	 */
	@TableField("l2_zll_sp")
private BigDecimal l2ZllSp;
	/**
	 * 总料量PV
	 */
	@TableField("l2_zll_pv")
private BigDecimal l2ZllPv;
	/**
	 * 总干料量
	 */
	@TableField("l2_zgll")
private BigDecimal l2Zgll;
	/**
	 * 理论产量
	 */
	@TableField("l2_llcl")
private BigDecimal l2Llcl;
	/**
	 * 配料C
	 */
	@TableField("l2_mb_c")
private BigDecimal l2MbC;
	/**
	 * 配料R
	 */
	@TableField("l2_mb_r")
private BigDecimal l2MbR;
	/**
	 * 配料Mg
	 */
	@TableField("l2_mb_mg")
private BigDecimal l2MbMg;
	/**
	 * 高返湿配比(湿)
	 */
	@TableField("l2_pb_wet_1")
private BigDecimal l2PbWet1;
	/**
	 * 铁料湿配比(湿)
	 */
	@TableField("l2_pb_wet_2")
private BigDecimal l2PbWet2;
	/**
	 * 石灰石湿配比(湿)
	 */
	@TableField("l2_pb_wet_3")
private BigDecimal l2PbWet3;
	/**
	 * 白云石湿配比(湿)
	 */
	@TableField("l2_pb_wet_4")
private BigDecimal l2PbWet4;
	/**
	 * 生石灰湿配比(湿)
	 */
	@TableField("l2_pb_wet_5")
private BigDecimal l2PbWet5;
	/**
	 * 燃料湿配比(湿)
	 */
	@TableField("l2_pb_wet_6")
private BigDecimal l2PbWet6;
	/**
	 * 烧返湿配比(湿)
	 */
	@TableField("l2_pb_wet_7")
private BigDecimal l2PbWet7;
	/**
	 * 除尘灰湿配比(湿)
	 */
	@TableField("l2_pb_wet_8")
private BigDecimal l2PbWet8;

	@TableField(exist = false)
	private String timeStr;

}
