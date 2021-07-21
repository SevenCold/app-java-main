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
@TableName("APP_MATERIAL_ANALYSIS")
public class AppMaterialAnalysisEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 接收时间
	 */
	@TableId
	@TableField("timestamp")
private Date timestamp;
	/**
	 * 取样时间
	 */
	@TableField("sampletime")
private Date sampletime;
	/**
	 * 报样时间
	 */
	@TableField("reopttime")
private Date reopttime;
	/**
	 * 物料名
	 */
	@TableField("mat_name")
private String matName;
	/**
	 * 批号
	 */
	@TableField("batch_num")
private String batchNum;
	/**
	 * TFe
	 */
	@TableField("c_tfe")
private BigDecimal cTfe;
	/**
	 * FeO
	 */
	@TableField("c_feo")
private BigDecimal cFeo;
	/**
	 * CaO
	 */
	@TableField("c_cao")
private BigDecimal cCao;
	/**
	 * SiO2
	 */
	@TableField("c_sio2")
private BigDecimal cSio2;
	/**
	 * Al2O3
	 */
	@TableField("c_al2o3")
private BigDecimal cAl2o3;
	/**
	 * MgO
	 */
	@TableField("c_mgo")
private BigDecimal cMgo;
	/**
	 * S
	 */
	@TableField("c_s")
private BigDecimal cS;
	/**
	 * P
	 */
	@TableField("c_p")
private BigDecimal cP;
	/**
	 * C
	 */
	@TableField("c_c")
private BigDecimal cC;
	/**
	 * MnO
	 */
	@TableField("c_mno")
private BigDecimal cMno;
	/**
	 * 烧损
	 */
	@TableField("c_lot")
private BigDecimal cLot;
	/**
	 * R
	 */
	@TableField("c_r")
private BigDecimal cR;
	/**
	 * H2O
	 */
	@TableField("c_h2o")
private BigDecimal cH2o;
	/**
	 * 灰分
	 */
	@TableField("c_ash")
private BigDecimal cAsh;
	/**
	 * 挥发分
	 */
	@TableField("c_volatiles")
private BigDecimal cVolatiles;
	/**
	 * K2O
	 */
	@TableField("c_k2o")
private BigDecimal cK2o;
	/**
	 * Na2O
	 */
	@TableField("c_na2o")
private BigDecimal cNa2o;
	/**
	 * ZnO
	 */
	@TableField("c_zno")
private BigDecimal cZno;

	@TableField(exist = false)
	private String timeStr;

}
