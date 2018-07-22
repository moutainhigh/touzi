package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行业公司项目类别对应评价指标
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_category_index")
public class ProjectCategoryIndex implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 参数ID
	 */
	private Long indicatorId;
	/**
	 * 行业公司项目类别ID
	 */
	private Long categoryId;
	/**
	 * 顺序号
	 */
	private Integer order;
	/**
	 * 备注
	 */
	private String memo;
	private Date createTime;
	/**
	 * 可研预测/投后预测 1-可研预测 2-投后预测
	 */
	private Integer feasibility;
	/**
	 * 实际 0 - 是 1 - 不是
	 */
	private Integer actual;
	/**
	 * 默认0,是否展现在项目登记，1展现在立项阶段
	 */
	private Integer keyType;

	@TableField(exist = false)
	private ProjectIndicator projectIndicator;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(Long indicatorId) {
		this.indicatorId = indicatorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(Integer feasibility) {
		this.feasibility = feasibility;
	}

	public Integer getActual() {
		return actual;
	}

	public void setActual(Integer actual) {
		this.actual = actual;
	}

	public ProjectIndicator getProjectIndicator() {
		return projectIndicator;
	}

	public void setProjectIndicator(ProjectIndicator projectIndicator) {
		this.projectIndicator = projectIndicator;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	@Override
	public String toString() {
		return "ProjectCategoryIndex{" + ", entityId=" + entityId + ", indicatorId=" + indicatorId + ", categoryId="
				+ categoryId + ", order=" + order + ", memo=" + memo + ", createTime=" + createTime + ", feasibility="
				+ feasibility + ", actual=" + actual + "}";
	}
}
