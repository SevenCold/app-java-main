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
@TableName("APP_PSM_MODEL_DATA")
public class AppPsmModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * < 5mm
	 */
	@TableField("sin_grit_5_d")
private BigDecimal sinGrit5D;
	/**
	 * 5 ～10 mm
	 */
	@TableField("sin_grit_5_10")
private BigDecimal sinGrit510;
	/**
	 * 10 ～16 mm
	 */
	@TableField("sin_grit_10_16")
private BigDecimal sinGrit1016;
	/**
	 * 16 ～25 mm
	 */
	@TableField("sin_grit_16_25")
private BigDecimal sinGrit1625;
	/**
	 * 25 ～40 mm
	 */
	@TableField("sin_grit_25_40")
private BigDecimal sinGrit2540;
	/**
	 * > 40 mm
	 */
	@TableField("sin_grit_40_u")
private BigDecimal sinGrit40U;
	/**
	 * 平均值
	 */
	@TableField("sin_grit_avg")
private BigDecimal sinGritAvg;
	/**
	 * > 3 mm
	 */
	@TableField("mix_grit_3_u")
private BigDecimal mixGrit3U;
	/**
	 * < 3 mm
	 */
	@TableField("mix_grit_3_d")
private BigDecimal mixGrit3D;

	@TableField(exist = false)
	private String timeStr;

}
