package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author wangkang
 * @email 784706982@qq.com
 * @date 2021-07-16 10:35:20
 */
@Data
@TableName("APP_TRO_MODEL_DATA")
public class AppTroModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 台车号
	 */
	@TableField("trolley_num")
private BigDecimal trolleyNum;
	/**
	 * 左车轮状态
	 */
	@TableField("wheel_l_state")
private String wheelLState;
	/**
	 * 左车轮偏移量
	 */
	@TableField("wheel_l_test")
private BigDecimal wheelLTest;
	/**
	 * 右车轮状态
	 */
	@TableField("wheel_r_state")
private String wheelRState;
	/**
	 * 右车轮偏移量
	 */
	@TableField("wheel_r_test")
private BigDecimal wheelRTest;
	/**
	 * 左栏板状态
	 */
	@TableField("board_l_state")
private String boardLState;
	/**
	 * 左栏板偏移量
	 */
	@TableField("board_l_test")
private BigDecimal boardLTest;
	/**
	 * 右栏板状态
	 */
	@TableField("board_r_state")
private String boardRState;
	/**
	 * 右栏板偏移量
	 */
	@TableField("board_r_test")
private BigDecimal boardRTest;
	/**
	 * 篦条状态
	 */
	@TableField("gate_defect_state")
private String gateDefectState;
	/**
	 * 篦条缺损数量
	 */
	@TableField("gate_defect_num")
private BigDecimal gateDefectNum;

	/**
	 * 是否处于台车识别摄像头位置 1是0否
	 */
	@TableField("IS_AT_CAMERA_POS")
	private int isAtCameraPos;

	@TableField(exist = false)
	private String timeStr;

}
