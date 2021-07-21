package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * 分组管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-24 14:22:39
 */
@Data
@TableName("sys_group")
public class SysGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long groupId;
	/**
	 * 分组名称
	 */
	private String name;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 标签名称，用,分割多个标签名称
	 */
	@Expose(deserialize = false)
	private String tagName;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 标签名称数组
	 */
	@TableField(exist=false)
	private List<String> tagList;

}
