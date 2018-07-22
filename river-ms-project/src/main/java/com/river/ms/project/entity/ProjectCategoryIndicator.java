package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行业公司项目类别对应经营数据：暂时没有考虑预测，实际，可行，投后等
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_category_indicator")
public class ProjectCategoryIndicator implements Serializable {

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
	 * 排序
	 */
	private Integer order;
	/**
	 * 0-非年报 1按年报
	 */
	private Integer year;
	/**
	 * 0-不按季度1-按季度
	 */
	private Integer quarter;
	/**
	 * 备注
	 */
	private String memo;
	private Date createTime;
	/**
	 * 可研预测
	 */
	private Integer feasibility;
	/**
	 * 计划
	 */
	private Integer plan;
	/**
	 * 实际
	 */
	private Integer actual;

	/**
	 * 0-不按月1-按月
	 */
	private Integer month;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
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

	public Integer getPlan() {
		return plan;
	}

	public void setPlan(Integer plan) {
		this.plan = plan;
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	@Override
	public String toString() {
		return "ProjectCategoryIndicator{" + ", entityId=" + entityId + ", indicatorId=" + indicatorId + ", categoryId="
				+ categoryId + ", order=" + order + ", year=" + year + ", quarter=" + quarter + ", memo=" + memo
				+ ", createTime=" + createTime + ", feasibility=" + feasibility + ", plan=" + plan + ", actual="
				+ actual + "}";
	}
}
