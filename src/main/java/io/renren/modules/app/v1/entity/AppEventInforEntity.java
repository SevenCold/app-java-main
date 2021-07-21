package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("APP_EVENT_INFOR")
public class AppEventInforEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 作业班次
	 */
	@TableField("a_work_shift")
private String aWorkShift;
	/**
	 * 作业班别
	 */
	@TableField("a_work_team")
private String aWorkTeam;
	/**
	 * 事件信息
	 */
	@TableField("a_remark_desc")
private String aRemarkDesc;

	@TableField(exist = false)
	private String timeStr;

}
