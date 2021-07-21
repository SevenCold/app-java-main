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
@TableName("APP_RMC_MODEL_DATA")
public class AppRmcModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 原料描述
	 */
	@TableField("mat_desc")
private String matDesc;
	/**
	 * 原料消耗
	 */
	@TableField("mat_value")
private BigDecimal matValue;
	/**
	 * 班别（白班、夜班）
	 */
	@TableField("pop_class")
private String popClass;

	@TableField(exist = false)
	private String timeStr;

}
