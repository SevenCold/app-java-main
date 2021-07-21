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
@TableName("APP_JWCX_MODEL_DATA")
public class AppJwcxModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间戳
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 最高温度
	 */
	@TableField("max_temp")
private BigDecimal maxTemp;
	/**
	 * 最低温度
	 */
	@TableField("min_temp")
private BigDecimal minTemp;
	/**
	 * 平均温度
	 */
	@TableField("avg_temp")
private BigDecimal avgTemp;
	/**
	 * 热峰行程指数
	 */
	@TableField("hat")
private BigDecimal hat;
	/**
	 * 垂直烧结速度
	 */
	@TableField("vss_c")
private BigDecimal vssC;
	/**
	 * 燃烧强度指数
	 */
	@TableField("cbt")
private BigDecimal cbt;
	/**
	 * 料层蓄热指数
	 */
	@TableField("rfa")
private BigDecimal rfa;
	/**
	 * 料层烧透指数
	 */
	@TableField("burn")
private BigDecimal burn;
	/**
	 * 边缘效应指数
	 */
	@TableField("edge_avg")
private BigDecimal edgeAvg;
	/**
	 * 烧透状态
	 */
	@TableField("burn_state")
private String burnState;
	/**
	 * 均匀状态
	 */
	@TableField("ufm_state")
private String ufmState;
	/**
	 * 边缘状态
	 */
	@TableField("edge_state")
private String edgeState;
	/**
	 * 返矿率
	 */
	@TableField("return_state")
private String returnState;
	/**
	 * 透气性
	 */
	@TableField("air_perm_state")
private String airPermState;
	/**
	 * 预测FEO状态
	 */
	@TableField("cal_pro_feo_state")
private String calProFeoState;

	@TableField(exist = false)
	private String timeStr;

}
