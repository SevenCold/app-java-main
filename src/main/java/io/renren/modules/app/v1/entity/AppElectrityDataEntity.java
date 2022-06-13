package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-13 08:44:33
 */
@Data
@TableName("app_electrity_data")
public class AppElectrityDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Date timestamp;
	/**
	 * 
	 */
	private String meterId;
	/**
	 * 
	 */
	private String meterName;
	/**
	 * 
	 */
	private BigDecimal meterConsume;
	/**
	 * 
	 */
	private BigDecimal meterSingal;

}
