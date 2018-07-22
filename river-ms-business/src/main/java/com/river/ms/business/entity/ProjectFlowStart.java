package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 启动流程配置
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_flow_start")
public class ProjectFlowStart implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 流程ID
	 */
	@NotNull(message = "流程不能为空!")
	private Long flowId;
	/**
	 * 投资（项目）类型ID
	 */
	@NotNull(message = "项目类型不能为空!")
	private Long groupCategoryId;
	/**
	 * 投资规模最大值
	 */
	private BigDecimal scaleOfInvest;
	/**
	 * 投资规模最小值
	 */
	private BigDecimal minScaleOfInvest;
	/**
	 * 流程类型 1-立项，2-可研，3-投决，4-投资价值评估，5，变更
	 */
	@NotNull(message = "流程类型不能为空!")
	private Integer type;

	@TableField(exist = false)
	private ProjectFlow flow;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Long getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(Long groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	public BigDecimal getScaleOfInvest() {
		return scaleOfInvest;
	}

	public void setScaleOfInvest(BigDecimal scaleOfInvest) {
		this.scaleOfInvest = scaleOfInvest;
	}

	public BigDecimal getMinScaleOfInvest() {
		return minScaleOfInvest;
	}

	public void setMinScaleOfInvest(BigDecimal minScaleOfInvest) {
		this.minScaleOfInvest = minScaleOfInvest;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ProjectFlow getFlow() {
		return flow;
	}

	public void setFlow(ProjectFlow flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "ProjectFlowStart{" + ", entityId=" + entityId + ", flowId=" + flowId + ", groupCategoryId="
				+ groupCategoryId + ", scaleOfInvest=" + scaleOfInvest + ", minScaleOfInvest=" + minScaleOfInvest + "}";
	}
}
