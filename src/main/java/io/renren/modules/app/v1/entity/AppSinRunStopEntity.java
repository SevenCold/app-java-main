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
@TableName("APP_SIN_RUN_STOP")
public class AppSinRunStopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 班别
	 */
	@TableField("a_work_shift")
private String aWorkShift;
	/**
	 * 班次
	 */
	@TableField("a_work_team")
private String aWorkTeam;
	/**
	 * 停机开始时间
	 */
	@TableField("a_stop_begintime")
private Date aStopBegintime;
	/**
	 * 停机结束时间
	 */
	@TableField("a_stop_endtime")
private Date aStopEndtime;
	/**
	 * 停机时间
	 */
	@TableField("a_interval_time")
private BigDecimal aIntervalTime;

	@TableField(exist = false)
	private String timeStr;

}
