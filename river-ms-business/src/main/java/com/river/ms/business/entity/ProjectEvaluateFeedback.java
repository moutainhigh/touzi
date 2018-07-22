package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目评价反馈
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@TableName("project_evaluate_feedback")
public class ProjectEvaluateFeedback implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 实施评价记录ID
	 */
	@TableField("evaluate_id")
	private Long evaluateId;
	/**
	 * 1.反馈说明/2.整改计划报告/3.整改报告
	 */
	private String feedback;
	/**
	 * 反馈时间
	 */
	@TableField("feedback_time")
	private Date feedbackTime;
	/**
	 * 反馈上报用户
	 */
	@TableField("feedback_itcode")
	private String feedbackItcode;
	/**
	 * 整改报告说明
	 */
	private String rectification;
	private Date createTime;
	private Long projectId;
	@TableField(exist = false)
	private ProjectEvaluateRectify rectify;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Long evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackItcode() {
		return feedbackItcode;
	}

	public void setFeedbackItcode(String feedbackItcode) {
		this.feedbackItcode = feedbackItcode;
	}

	public String getRectification() {
		return rectification;
	}

	public void setRectification(String rectification) {
		this.rectification = rectification;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public ProjectEvaluateRectify getRectify() {
		return rectify;
	}

	public void setRectify(ProjectEvaluateRectify rectify) {
		this.rectify = rectify;
	}

	@Override
	public String toString() {
		return "ProjectEvaluateFeedback{" + ", entityId=" + entityId + ", evaluateId=" + evaluateId + ", feedback="
				+ feedback + ", feedbackTime=" + feedbackTime + ", feedbackItcode=" + feedbackItcode
				+ ", rectification=" + rectification + ", createTime=" + createTime + "}";
	}
}
