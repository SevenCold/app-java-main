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
@TableName("APP_POPCAL_OUT")
public class AppPopcalOutEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 全天计划产量
	 */
	@TableField("a_p_al_output")
private BigDecimal aPAlOutput;
	/**
	 * 全天实际产量
	 */
	@TableField("a_a_al_output")
private BigDecimal aAAlOutput;
	/**
	 * 本月计划产量
	 */
	@TableField("a_p_al_output_m")
private BigDecimal aPAlOutputM;
	/**
	 * 本月实际产量
	 */
	@TableField("a_a_al_output_m")
private BigDecimal aAAlOutputM;
	/**
	 * 当前上料量
	 */
	@TableField("a_t_sp_w")
private BigDecimal aTSpW;
	/**
	 * 计划完成进度
	 */
	@TableField("a_plan")
private BigDecimal aPlan;

	@TableField(exist = false)
	private String timeStr;

}
