package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目进度更新记录
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_milestone_history")
public class ProjectMilestoneHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目分期
	 */
	private Long milestoneId;
	/**
	 * 分期预计竣工/投产日期
	 */
	@TableField("expected_finish_time")
	private Date expectedFinishTime;
	@TableField("actual_finish_time")
	private Date actualFinishTime;
	/**
	 * 分期进度完成率（%）
	 */
	@TableField("completion_degrees")
	private Integer completionDegrees;
	/**
	 * 预计进度偏差原因
	 */
	private String deviation;
	/**
	 * 预计进度偏差百分比（%）
	 */
	@TableField("deviation_degrees")
	private Integer deviationDegrees;
	/**
	 * 项目目前状态
	 */
	private Integer state;
	/**
	 * 分期进度填写时间
	 */
	private Date createTime;
	/**
	 * 项目总体进展情况
	 */
	@TableField("current_situation")
	private String currentSituation;
	/**
	 * 下月计划
	 */
	@TableField("plan_nextMonth")
	private String planNextMonth;
	/**
	 * 预计进度是否有偏差
	 */
	private Integer b1;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Date getExpectedFinishTime() {
		return expectedFinishTime;
	}

	public void setExpectedFinishTime(Date expectedFinishTime) {
		this.expectedFinishTime = expectedFinishTime;
	}

	public Date getActualFinishTime() {
		return actualFinishTime;
	}

	public void setActualFinishTime(Date actualFinishTime) {
		this.actualFinishTime = actualFinishTime;
	}

	public Integer getCompletionDegrees() {
		return completionDegrees;
	}

	public void setCompletionDegrees(Integer completionDegrees) {
		this.completionDegrees = completionDegrees;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public Integer getDeviationDegrees() {
		return deviationDegrees;
	}

	public void setDeviationDegrees(Integer deviationDegrees) {
		this.deviationDegrees = deviationDegrees;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCurrentSituation() {
		return currentSituation;
	}

	public void setCurrentSituation(String currentSituation) {
		this.currentSituation = currentSituation;
	}

	public String getPlanNextMonth() {
		return planNextMonth;
	}

	public void setPlanNextMonth(String planNextMonth) {
		this.planNextMonth = planNextMonth;
	}

	public Integer getB1() {
		return b1;
	}

	public void setB1(Integer b1) {
		this.b1 = b1;
	}

	@Override
	public String toString() {
		return "ProjectMilestoneHistory{" + ", entityId=" + entityId + ", projectId=" + projectId + ", milestoneId="
				+ milestoneId + ", expectedFinishTime=" + expectedFinishTime + ", actualFinishTime=" + actualFinishTime
				+ ", completionDegrees=" + completionDegrees + ", deviation=" + deviation + ", deviationDegrees="
				+ deviationDegrees + ", state=" + state + ", createTime=" + createTime + ", currentSituation="
				+ currentSituation + ", planNextMonth=" + planNextMonth + "}";
	}
}
