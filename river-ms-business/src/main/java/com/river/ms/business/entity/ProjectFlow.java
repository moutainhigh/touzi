package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 流程定义表
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_flow")
public class ProjectFlow implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 流程编码
	 */
	@NotNull(message = "流程编码不能为空!")
	private String entityCode;
	/**
	 * 流程名称
	 */
	@NotNull(message = "流程名称不能为空!")
	private String entityTitle;
	/**
	 * 流程模板描述
	 */
	private String entityDesc;

	@NotNull(message = "流程类型不能为空!")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	@Override
	public String toString() {
		return "ProjectFlow{" + ", entityId=" + entityId + ", entityCode=" + entityCode + ", entityTitle=" + entityTitle
				+ ", entityDesc=" + entityDesc + "}";
	}
}
