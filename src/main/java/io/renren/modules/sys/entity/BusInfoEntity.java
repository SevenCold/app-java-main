package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务数据
 * 
 */
@Data
@TableName("bus_info")
public class BusInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 标签名称
	 */
	private String tagName;


	private String tagValue;

	private Date tagTime;

	private long tagLongTime;
}
