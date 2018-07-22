package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 前置条件落实反馈
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_condition_feedback")
public class ProjectConditionFeedback implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	@NotNull(message = "项目ID不能为空!")
	private Long projectId;
	/**
	 * 项目节点
	 */
	private String milestone;
	/**
	 * 项目节点ID（先不管）
	 */
	private Long milestoneId;
	/**
	 * 条件ID
	 */
	private Long conditionId;
	/**
	 * 落实情况状态 0-完全落实，1-未完全落实
	 */
	@TableField("fix_state")
	private Integer fixState;
	/**
	 * 落实情况说明
	 */
	private String feedback;
	/**
	 * 落实时间
	 */
	@TableField("fixed_time")
	private Date fixedTime;

	/**
	 * 落实情况反馈操作用户ITCODE
	 */
	private String feedbackItcode;
	/**
	 * 落实情况反馈资源ID
	 */
	private Long feedbackResourceId;

	/**
	 * 偏差结果:0有，1没有
	 */
	private String deviation;
	/**
	 * 偏差原因:
	 */
	@TableField("deviation_cause")
	private String deviationCause;
	/**
	 * 落实审核 1-通过审核，0-不通过审核
	 */
	@TableField("implement_state")
	private Integer implementState;
	/**
	 * 落实审核说明
	 */
	private String implement;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	
	/**
	 * 前置条件名称
	 */
	@TableField(exist = false)
	private String condition;
	/**
	 * 前置条件类型
	 */
	@TableField(exist = false)
	private String conditionType;
	/**
	 * 资源ID
	 */
	private Long resourceId;
	private Date createTime;

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

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Long getConditionId() {
		return conditionId;
	}

	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}

	public Integer getFixState() {
		return fixState;
	}

	public void setFixState(Integer fixState) {
		this.fixState = fixState;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public String getDeviationCause() {
		return deviationCause;
	}

	public void setDeviationCause(String deviationCause) {
		this.deviationCause = deviationCause;
	}

	public Integer getImplementState() {
		return implementState;
	}

	public void setImplementState(Integer implementState) {
		this.implementState = implementState;
	}

	public String getImplement() {
		return implement;
	}

	public void setImplement(String implement) {
		this.implement = implement;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFeedbackItcode() {
		return feedbackItcode;
	}

	public void setFeedbackItcode(String feedbackItcode) {
		this.feedbackItcode = feedbackItcode;
	}

	public Long getFeedbackResourceId() {
		return feedbackResourceId;
	}

	public void setFeedbackResourceId(Long feedbackResourceId) {
		this.feedbackResourceId = feedbackResourceId;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	@Override
	public String toString() {
		return "ProjectConditionFeedback{" + ", entityId=" + entityId + ", projectId=" + projectId + ", milestone="
				+ milestone + ", milestoneId=" + milestoneId + ", conditionId=" + conditionId + ", fixState=" + fixState
				+ ", feedback=" + feedback + ", fixedTime=" + fixedTime + ", deviation=" + deviation
				+ ", deviationCause=" + deviationCause + ", implementState=" + implementState + ", implement="
				+ implement + ", itcode=" + itcode + ", resourceId=" + resourceId + ", createTime=" + createTime + "}";
	}
}
