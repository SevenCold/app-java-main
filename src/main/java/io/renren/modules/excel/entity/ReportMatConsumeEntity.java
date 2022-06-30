package io.renren.modules.excel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
 * @author kang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 09:22:55
 */
@Data
@TableName("report_mat_consume")
public class ReportMatConsumeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * TIMESTAMP
	 */
		@TableId
	@ExcelIgnore
		@ExcelProperty("timestamp")
	@TableField(value = "TIMESTAMP")
	private Date timestamp;
	/**
	 * YIELD
	 */
		@ExcelProperty("yield")
	@TableField(value = "YIELD")
	private BigDecimal yield;
	/**
	 * CON_IRON
	 */
		@ExcelProperty("conIron")
	@TableField(value = "CON_IRON")
	private BigDecimal conIron;
	/**
	 * UNIT_IRON
	 */
		@ExcelProperty("unitIron")
	@TableField(value = "UNIT_IRON")
	private BigDecimal unitIron;
	/**
	 * CON_320_FUEL_WET
	 */
		@ExcelProperty("con320FuelWet")
	@TableField(value = "CON_320_FUEL_WET")
	private BigDecimal con320FuelWet;
	/**
	 * CON_240_FUEL_WET
	 */
		@ExcelProperty("con240FuelWet")
	@TableField(value = "CON_240_FUEL_WET")
	private BigDecimal con240FuelWet;
	/**
	 * CON_TOTAL_FUEL_WET
	 */
		@ExcelProperty("conTotalFuelWet")
	@TableField(value = "CON_TOTAL_FUEL_WET")
	private BigDecimal conTotalFuelWet;
	/**
	 * CON_TOTAL_FUEL
	 */
		@ExcelProperty("conTotalFuel")
	@TableField(value = "CON_TOTAL_FUEL")
	private BigDecimal conTotalFuel;
	/**
	 * CON_OUT_FUEL
	 */
		@ExcelProperty("conOutFuel")
	@TableField(value = "CON_OUT_FUEL")
	private BigDecimal conOutFuel;
	/**
	 * CON_PUL_WET
	 */
		@ExcelProperty("conPulWet")
	@TableField(value = "CON_PUL_WET")
	private BigDecimal conPulWet;
	/**
	 * CON_PUL_BILL
	 */
		@ExcelProperty("conPulBill")
	@TableField(value = "CON_PUL_BILL")
	private BigDecimal conPulBill;
	/**
	 * CON_BURNT_BILL
	 */
		@ExcelProperty("conBurntBill")
	@TableField(value = "CON_BURNT_BILL")
	private BigDecimal conBurntBill;
	/**
	 * CON_H2O
	 */
		@ExcelProperty("conH2o")
	@TableField(value = "CON_H2O")
	private BigDecimal conH2o;
	/**
	 * UNIT_FUEL_WET
	 */
		@ExcelProperty("unitFuelWet")
	@TableField(value = "UNIT_FUEL_WET")
	private BigDecimal unitFuelWet;
	/**
	 * UNIT_BURNT_WET
	 */
		@ExcelProperty("unitBurntWet")
	@TableField(value = "UNIT_BURNT_WET")
	private BigDecimal unitBurntWet;
	/**
	 * CON_MH_WET
	 */
		@ExcelProperty("conMhWet")
	@TableField(value = "CON_MH_WET")
	private BigDecimal conMhWet;
	/**
	 * UNIT_FUEL_DRY
	 */
		@ExcelProperty("unitFuelDry")
	@TableField(value = "UNIT_FUEL_DRY")
	private BigDecimal unitFuelDry;
	/**
	 * UNIT_BURNT_DRY
	 */
		@ExcelProperty("unitBurntDry")
	@TableField(value = "UNIT_BURNT_DRY")
	private BigDecimal unitBurntDry;
	/**
	 * CON_PUL_DRY
	 */
		@ExcelProperty("conPulDry")
	@TableField(value = "CON_PUL_DRY")
	private BigDecimal conPulDry;
	/**
	 * LIME_ZF
	 */
		@ExcelProperty("limeZf")
	@TableField(value = "LIME_ZF")
	private BigDecimal limeZf;
	/**
	 * LIME_CPJ
	 */
		@ExcelProperty("limeCpj")
	@TableField(value = "LIME_CPJ")
	private BigDecimal limeCpj;
	/**
	 * LIME_RM
	 */
		@ExcelProperty("limeRm")
	@TableField(value = "LIME_RM")
	private BigDecimal limeRm;
	/**
	 * LIME_BX
	 */
		@ExcelProperty("limeBx")
	@TableField(value = "LIME_BX")
	private BigDecimal limeBx;
	/**
	 * LIME_HG
	 */
		@ExcelProperty("limeHg")
	@TableField(value = "LIME_HG")
	private BigDecimal limeHg;
	/**
	 * LIME_QT1
	 */
		@ExcelProperty("limeQt1")
	@TableField(value = "LIME_QT1")
	private BigDecimal limeQt1;
	/**
	 * LIME_QT2
	 */
		@ExcelProperty("limeQt2")
	@TableField(value = "LIME_QT2")
	private BigDecimal limeQt2;
	/**
	 * LIME_QT3
	 */
		@ExcelProperty("limeQt3")
	@TableField(value = "LIME_QT3")
	private BigDecimal limeQt3;
	/**
	 * TOTAL_LIME
	 */
		@ExcelProperty("totalLime")
	@TableField(value = "TOTAL_LIME")
	private BigDecimal totalLime;
	/**
	 * UNIT_LIME
	 */
		@ExcelProperty("unitLime")
	@TableField(value = "UNIT_LIME")
	private BigDecimal unitLime;
	/**
	 * STONE_XP
	 */
		@ExcelProperty("stoneXp")
	@TableField(value = "STONE_XP")
	private BigDecimal stoneXp;
	/**
	 * STONE_BX
	 */
		@ExcelProperty("stoneBx")
	@TableField(value = "STONE_BX")
	private BigDecimal stoneBx;
	/**
	 * STONE_HG
	 */
		@ExcelProperty("stoneHg")
	@TableField(value = "STONE_HG")
	private BigDecimal stoneHg;
	/**
	 * STONE_FY
	 */
		@ExcelProperty("stoneFy")
	@TableField(value = "STONE_FY")
	private BigDecimal stoneFy;
	/**
	 * STONE_QT1
	 */
		@ExcelProperty("stoneQt1")
	@TableField(value = "STONE_QT1")
	private BigDecimal stoneQt1;
	/**
	 * STONE_QT2
	 */
		@ExcelProperty("stoneQt2")
	@TableField(value = "STONE_QT2")
	private BigDecimal stoneQt2;
	/**
	 * STONE_QT3
	 */
		@ExcelProperty("stoneQt3")
	@TableField(value = "STONE_QT3")
	private BigDecimal stoneQt3;
	/**
	 * TOTAL_STONE
	 */
		@ExcelProperty("totalStone")
	@TableField(value = "TOTAL_STONE")
	private BigDecimal totalStone;
	/**
	 * UNIT_STONE
	 */
		@ExcelProperty("unitStone")
	@TableField(value = "UNIT_STONE")
	private BigDecimal unitStone;
	/**
	 * TL_LIME
	 */
		@ExcelProperty("tlLime")
	@TableField(value = "TL_LIME")
	private BigDecimal tlLime;
	/**
	 * UNIT_TL_LIME
	 */
		@ExcelProperty("unitTlLime")
	@TableField(value = "UNIT_TL_LIME")
	private BigDecimal unitTlLime;
	/**
	 * HEAD_LIME
	 */
		@ExcelProperty("headLime")
	@TableField(value = "HEAD_LIME")
	private BigDecimal headLime;

}
