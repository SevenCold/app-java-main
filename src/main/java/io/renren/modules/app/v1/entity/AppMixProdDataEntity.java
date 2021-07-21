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
 * @date 2021-07-16 10:35:22
 */
@Data
@TableName("APP_MIX_PROD_DATA")
public class AppMixProdDataEntity implements Serializable {
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
	@TableField("plc_t_sp_w")
private BigDecimal plcTSpW;
	/**
	 * 总料量PV
	 */
	@TableField("plc_t_pv_w")
private BigDecimal plcTPvW;
	/**
	 * 1#仓位
	 */
	@TableField("plc_w_1")
private BigDecimal plcW1;
	/**
	 * 2#仓位
	 */
	@TableField("plc_w_2")
private BigDecimal plcW2;
	/**
	 * 3#仓位
	 */
	@TableField("plc_w_3")
private BigDecimal plcW3;
	/**
	 * 4#仓位
	 */
	@TableField("plc_w_4")
private BigDecimal plcW4;
	/**
	 * 5#仓位
	 */
	@TableField("plc_w_5")
private BigDecimal plcW5;
	/**
	 * 6#仓位
	 */
	@TableField("plc_w_6")
private BigDecimal plcW6;
	/**
	 * 7#仓位
	 */
	@TableField("plc_w_7")
private BigDecimal plcW7;
	/**
	 * 8#仓位
	 */
	@TableField("plc_w_8")
private BigDecimal plcW8;
	/**
	 * 9#仓位
	 */
	@TableField("plc_w_9")
private BigDecimal plcW9;
	/**
	 * 10#仓位
	 */
	@TableField("plc_w_10")
private BigDecimal plcW10;
	/**
	 * 11#仓位
	 */
	@TableField("plc_w_11")
private BigDecimal plcW11;
	/**
	 * 12#仓位
	 */
	@TableField("plc_w_12")
private BigDecimal plcW12;
	/**
	 * 13#仓位
	 */
	@TableField("plc_w_13")
private BigDecimal plcW13;
	/**
	 * 14#仓位
	 */
	@TableField("plc_w_14")
private BigDecimal plcW14;
	/**
	 * 15#仓位
	 */
	@TableField("plc_w_15")
private BigDecimal plcW15;
	/**
	 * 16#仓位
	 */
	@TableField("plc_w_16")
private BigDecimal plcW16;
	/**
	 * 17#仓位
	 */
	@TableField("plc_w_17")
private BigDecimal plcW17;
	/**
	 * 18#仓位
	 */
	@TableField("plc_w_18")
private BigDecimal plcW18;
	/**
	 * 19#仓位
	 */
	@TableField("plc_w_19")
private BigDecimal plcW19;
	/**
	 * 20#仓位
	 */
	@TableField("plc_w_20")
private BigDecimal plcW20;
	/**
	 * 1#下料量
	 */
	@TableField("plc_pv_w_1")
private BigDecimal plcPvW1;
	/**
	 * 2#下料量
	 */
	@TableField("plc_pv_w_2")
private BigDecimal plcPvW2;
	/**
	 * 3#下料量
	 */
	@TableField("plc_pv_w_3")
private BigDecimal plcPvW3;
	/**
	 * 4#下料量
	 */
	@TableField("plc_pv_w_4")
private BigDecimal plcPvW4;
	/**
	 * 5#下料量
	 */
	@TableField("plc_pv_w_5")
private BigDecimal plcPvW5;
	/**
	 * 6#下料量
	 */
	@TableField("plc_pv_w_6")
private BigDecimal plcPvW6;
	/**
	 * 7#下料量
	 */
	@TableField("plc_pv_w_7")
private BigDecimal plcPvW7;
	/**
	 * 8#下料量
	 */
	@TableField("plc_pv_w_8")
private BigDecimal plcPvW8;
	/**
	 * 9#下料量
	 */
	@TableField("plc_pv_w_9")
private BigDecimal plcPvW9;
	/**
	 * 10#下料量
	 */
	@TableField("plc_pv_w_10")
private BigDecimal plcPvW10;
	/**
	 * 11#下料量
	 */
	@TableField("plc_pv_w_11")
private BigDecimal plcPvW11;
	/**
	 * 12#下料量
	 */
	@TableField("plc_pv_w_12")
private BigDecimal plcPvW12;
	/**
	 * 13#下料量
	 */
	@TableField("plc_pv_w_13")
private BigDecimal plcPvW13;
	/**
	 * 14#下料量
	 */
	@TableField("plc_pv_w_14")
private BigDecimal plcPvW14;
	/**
	 * 15#下料量
	 */
	@TableField("plc_pv_w_15")
private BigDecimal plcPvW15;
	/**
	 * 16#下料量
	 */
	@TableField("plc_pv_w_16")
private BigDecimal plcPvW16;
	/**
	 * 17#下料量
	 */
	@TableField("plc_pv_w_17")
private BigDecimal plcPvW17;
	/**
	 * 18#下料量
	 */
	@TableField("plc_pv_w_18")
private BigDecimal plcPvW18;
	/**
	 * 19#下料量
	 */
	@TableField("plc_pv_w_19")
private BigDecimal plcPvW19;
	/**
	 * 20#下料量
	 */
	@TableField("plc_pv_w_20")
private BigDecimal plcPvW20;
	/**
	 * 1#秤累计值
	 */
	@TableField("plc_b_acc_1")
private BigDecimal plcBAcc1;
	/**
	 * 2#秤累计值
	 */
	@TableField("plc_b_acc_2")
private BigDecimal plcBAcc2;
	/**
	 * 3#秤累计值
	 */
	@TableField("plc_b_acc_3")
private BigDecimal plcBAcc3;
	/**
	 * 4#秤累计值
	 */
	@TableField("plc_b_acc_4")
private BigDecimal plcBAcc4;
	/**
	 * 5#秤累计值
	 */
	@TableField("plc_b_acc_5")
private BigDecimal plcBAcc5;
	/**
	 * 6#秤累计值
	 */
	@TableField("plc_b_acc_6")
private BigDecimal plcBAcc6;
	/**
	 * 7#秤累计值
	 */
	@TableField("plc_b_acc_7")
private BigDecimal plcBAcc7;
	/**
	 * 8#秤累计值
	 */
	@TableField("plc_b_acc_8")
private BigDecimal plcBAcc8;
	/**
	 * 9#秤累计值
	 */
	@TableField("plc_b_acc_9")
private BigDecimal plcBAcc9;
	/**
	 * 10#秤累计值
	 */
	@TableField("plc_b_acc_10")
private BigDecimal plcBAcc10;
	/**
	 * 11#秤累计值
	 */
	@TableField("plc_b_acc_11")
private BigDecimal plcBAcc11;
	/**
	 * 12#秤累计值
	 */
	@TableField("plc_b_acc_12")
private BigDecimal plcBAcc12;
	/**
	 * 13#秤累计值
	 */
	@TableField("plc_b_acc_13")
private BigDecimal plcBAcc13;
	/**
	 * 14#秤累计值
	 */
	@TableField("plc_b_acc_14")
private BigDecimal plcBAcc14;
	/**
	 * 15#秤累计值
	 */
	@TableField("plc_b_acc_15")
private BigDecimal plcBAcc15;
	/**
	 * 16#秤累计值
	 */
	@TableField("plc_b_acc_16")
private BigDecimal plcBAcc16;
	/**
	 * 17#秤累计值
	 */
	@TableField("plc_b_acc_17")
private BigDecimal plcBAcc17;
	/**
	 * 18#秤累计值
	 */
	@TableField("plc_b_acc_18")
private BigDecimal plcBAcc18;
	/**
	 * 19#秤累计值
	 */
	@TableField("plc_b_acc_19")
private BigDecimal plcBAcc19;
	/**
	 * 20#秤累计值
	 */
	@TableField("plc_b_acc_20")
private BigDecimal plcBAcc20;
	/**
	 * 配-1 皮带称重
	 */
	@TableField("plc_1m_b_w")
private BigDecimal plc1mBW;
	/**
	 * 一混加水流量
	 */
	@TableField("plc_1m_ft_pv")
private BigDecimal plc1mFtPv;
	/**
	 * 一混水管压力
	 */
	@TableField("plc_1m_pt")
private BigDecimal plc1mPt;
	/**
	 * 一混水分检测
	 */
	@TableField("plc_1m_a_water_pv")
private BigDecimal plc1mAWaterPv;
	/**
	 * 混-2皮带称重
	 */
	@TableField("plc_1m_a_w")
private BigDecimal plc1mAW;
	/**
	 * 混-3皮带称重
	 */
	@TableField("plc_1m_a_b_signal_3")
private BigDecimal plc1mABSignal3;
	/**
	 * 混-4皮带称重
	 */
	@TableField("plc_2m_a_b_w")
private BigDecimal plc2mABW;
	/**
	 * 混-5皮带称重
	 */
	@TableField("plc_2m_a_w")
private BigDecimal plc2mAW;

	@TableField(exist = false)
	private String timeStr;

}
