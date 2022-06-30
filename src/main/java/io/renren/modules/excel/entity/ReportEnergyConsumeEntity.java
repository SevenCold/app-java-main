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
@TableName("report_energy_consume")
public class ReportEnergyConsumeEntity implements Serializable {
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
	 * CON_ELE_SINTER
	 */
		@ExcelProperty("conEleSinter")
	@TableField(value = "CON_ELE_SINTER")
	private BigDecimal conEleSinter;
	/**
	 * CON_ELE_TLTX
	 */
		@ExcelProperty("conEleTltx")
	@TableField(value = "CON_ELE_TLTX")
	private BigDecimal conEleTltx;
	/**
	 * ELE_COE
	 */
		@ExcelProperty("eleCoe")
	@TableField(value = "ELE_COE")
	private BigDecimal eleCoe;
	/**
	 * CON_ELE_TX
	 */
		@ExcelProperty("conEleTx")
	@TableField(value = "CON_ELE_TX")
	private BigDecimal conEleTx;
	/**
	 * UNIT_ELE_TX
	 */
		@ExcelProperty("unitEleTx")
	@TableField(value = "UNIT_ELE_TX")
	private BigDecimal unitEleTx;
	/**
	 * ELE_TOTAL
	 */
		@ExcelProperty("eleTotal")
	@TableField(value = "ELE_TOTAL")
	private BigDecimal eleTotal;
	/**
	 * UNIT_ELE_SINTER
	 */
		@ExcelProperty("unitEleSinter")
	@TableField(value = "UNIT_ELE_SINTER")
	private BigDecimal unitEleSinter;
	/**
	 * UNIT_ELE_TLTX
	 */
		@ExcelProperty("unitEleTltx")
	@TableField(value = "UNIT_ELE_TLTX")
	private BigDecimal unitEleTltx;
	/**
	 * UNIT_ELE_TOTAL
	 */
		@ExcelProperty("unitEleTotal")
	@TableField(value = "UNIT_ELE_TOTAL")
	private BigDecimal unitEleTotal;
	/**
	 * CON_WIND_SINTER
	 */
		@ExcelProperty("conWindSinter")
	@TableField(value = "CON_WIND_SINTER")
	private BigDecimal conWindSinter;
	/**
	 * CON_WIND_TLTX
	 */
		@ExcelProperty("conWindTltx")
	@TableField(value = "CON_WIND_TLTX")
	private BigDecimal conWindTltx;
	/**
	 * WIND_COE
	 */
		@ExcelProperty("windCoe")
	@TableField(value = "WIND_COE")
	private BigDecimal windCoe;
	/**
	 * CON_WIND_TX
	 */
		@ExcelProperty("conWindTx")
	@TableField(value = "CON_WIND_TX")
	private BigDecimal conWindTx;
	/**
	 * UNIT_WIND_TX
	 */
		@ExcelProperty("unitWindTx")
	@TableField(value = "UNIT_WIND_TX")
	private BigDecimal unitWindTx;
	/**
	 * WIND_TOTAL
	 */
		@ExcelProperty("windTotal")
	@TableField(value = "WIND_TOTAL")
	private BigDecimal windTotal;
	/**
	 * UNIT_WIND_SINTER
	 */
		@ExcelProperty("unitWindSinter")
	@TableField(value = "UNIT_WIND_SINTER")
	private BigDecimal unitWindSinter;
	/**
	 * UNIT_WIND_TLTX
	 */
		@ExcelProperty("unitWindTltx")
	@TableField(value = "UNIT_WIND_TLTX")
	private BigDecimal unitWindTltx;
	/**
	 * UNIT_WIND_TOTAL
	 */
		@ExcelProperty("unitWindTotal")
	@TableField(value = "UNIT_WIND_TOTAL")
	private BigDecimal unitWindTotal;
	/**
	 * CON_COKE_SINTER
	 */
		@ExcelProperty("conCokeSinter")
	@TableField(value = "CON_COKE_SINTER")
	private BigDecimal conCokeSinter;
	/**
	 * CON_COKE_TL
	 */
		@ExcelProperty("conCokeTl")
	@TableField(value = "CON_COKE_TL")
	private BigDecimal conCokeTl;
	/**
	 * CON_COKE_TOTAL
	 */
		@ExcelProperty("conCokeTotal")
	@TableField(value = "CON_COKE_TOTAL")
	private BigDecimal conCokeTotal;
	/**
	 * UNIT_COKE_SINTER
	 */
		@ExcelProperty("unitCokeSinter")
	@TableField(value = "UNIT_COKE_SINTER")
	private BigDecimal unitCokeSinter;
	/**
	 * UNIT_COKE_TL
	 */
		@ExcelProperty("unitCokeTl")
	@TableField(value = "UNIT_COKE_TL")
	private BigDecimal unitCokeTl;
	/**
	 * UNIT_COKE_TOTAL
	 */
		@ExcelProperty("unitCokeTotal")
	@TableField(value = "UNIT_COKE_TOTAL")
	private BigDecimal unitCokeTotal;
	/**
	 * CON_BLAST_TL
	 */
		@ExcelProperty("conBlastTl")
	@TableField(value = "CON_BLAST_TL")
	private BigDecimal conBlastTl;
	/**
	 * UNIT_BLAST_TL
	 */
		@ExcelProperty("unitBlastTl")
	@TableField(value = "UNIT_BLAST_TL")
	private BigDecimal unitBlastTl;
	/**
	 * CON_NH2O_SINTER
	 */
		@ExcelProperty("conNh2oSinter")
	@TableField(value = "CON_NH2O_SINTER")
	private BigDecimal conNh2oSinter;
	/**
	 * CON_NH2O_TL
	 */
		@ExcelProperty("conNh2oTl")
	@TableField(value = "CON_NH2O_TL")
	private BigDecimal conNh2oTl;
	/**
	 * CON_NH2O_TOTAL
	 */
		@ExcelProperty("conNh2oTotal")
	@TableField(value = "CON_NH2O_TOTAL")
	private BigDecimal conNh2oTotal;
	/**
	 * UNIT_NH2O_SINTER
	 */
		@ExcelProperty("unitNh2oSinter")
	@TableField(value = "UNIT_NH2O_SINTER")
	private BigDecimal unitNh2oSinter;
	/**
	 * UNIT_NH2O_TL
	 */
		@ExcelProperty("unitNh2oTl")
	@TableField(value = "UNIT_NH2O_TL")
	private BigDecimal unitNh2oTl;
	/**
	 * UNIT_NH2O_TOTAL
	 */
		@ExcelProperty("unitNh2oTotal")
	@TableField(value = "UNIT_NH2O_TOTAL")
	private BigDecimal unitNh2oTotal;
	/**
	 * CON_SEWAGE_SINTER
	 */
		@ExcelProperty("conSewageSinter")
	@TableField(value = "CON_SEWAGE_SINTER")
	private BigDecimal conSewageSinter;
	/**
	 * UNIT_SEWAGE_SINTER
	 */
		@ExcelProperty("unitSewageSinter")
	@TableField(value = "UNIT_SEWAGE_SINTER")
	private BigDecimal unitSewageSinter;
	/**
	 * CON_STEAM_SINTER
	 */
		@ExcelProperty("conSteamSinter")
	@TableField(value = "CON_STEAM_SINTER")
	private BigDecimal conSteamSinter;
	/**
	 * CON_STEAM_TL
	 */
		@ExcelProperty("conSteamTl")
	@TableField(value = "CON_STEAM_TL")
	private BigDecimal conSteamTl;
	/**
	 * CON_STEAM_TOTAL
	 */
		@ExcelProperty("conSteamTotal")
	@TableField(value = "CON_STEAM_TOTAL")
	private BigDecimal conSteamTotal;
	/**
	 * UNIT_STEAM_SINTER
	 */
		@ExcelProperty("unitSteamSinter")
	@TableField(value = "UNIT_STEAM_SINTER")
	private BigDecimal unitSteamSinter;
	/**
	 * UNIT_STEAM_TL
	 */
		@ExcelProperty("unitSteamTl")
	@TableField(value = "UNIT_STEAM_TL")
	private BigDecimal unitSteamTl;
	/**
	 * UNIT_STEAM_TOTAL
	 */
		@ExcelProperty("unitSteamTotal")
	@TableField(value = "UNIT_STEAM_TOTAL")
	private BigDecimal unitSteamTotal;
	/**
	 * CON_RESTEAM_OUT
	 */
		@ExcelProperty("conResteamOut")
	@TableField(value = "CON_RESTEAM_OUT")
	private BigDecimal conResteamOut;
	/**
	 * CON_RESTEAM_HIGH
	 */
		@ExcelProperty("conResteamHigh")
	@TableField(value = "CON_RESTEAM_HIGH")
	private BigDecimal conResteamHigh;
	/**
	 * CON_RESTEAM_TOTAL
	 */
		@ExcelProperty("conResteamTotal")
	@TableField(value = "CON_RESTEAM_TOTAL")
	private BigDecimal conResteamTotal;
	/**
	 * CON_RESTEAM_RE
	 */
		@ExcelProperty("conResteamRe")
	@TableField(value = "CON_RESTEAM_RE")
	private BigDecimal conResteamRe;
	/**
	 * CON_NITROGEN
	 */
		@ExcelProperty("conNitrogen")
	@TableField(value = "CON_NITROGEN")
	private BigDecimal conNitrogen;
	/**
	 * UNIT_NITROGEN
	 */
		@ExcelProperty("unitNitrogen")
	@TableField(value = "UNIT_NITROGEN")
	private BigDecimal unitNitrogen;

}
