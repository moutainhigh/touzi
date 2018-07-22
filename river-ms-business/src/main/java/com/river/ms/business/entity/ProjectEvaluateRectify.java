package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目反馈/整改审核
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@TableName("project_evaluate_rectify")
public class ProjectEvaluateRectify implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 实施评价记录ID
     */
	@TableField("evaluate_id")
	private Long evaluateId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 反馈记录ID
     */
	@TableField("feedback_id")
	private Long feedbackId;
    /**
     * 反馈/整改审核用户
     */
	@TableField("feedback_auditor")
	private String feedbackAuditor;
    /**
     * 1-通过0-不通过
     */
	private Integer state;
    /**
     * 反馈/整改审核意见
     */
	private String approval;
	private Date createTime;


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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackAuditor() {
		return feedbackAuditor;
	}

	public void setFeedbackAuditor(String feedbackAuditor) {
		this.feedbackAuditor = feedbackAuditor;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectEvaluateRectify{" +
			", entityId=" + entityId +
			", evaluateId=" + evaluateId +
			", projectId=" + projectId +
			", feedbackId=" + feedbackId +
			", feedbackAuditor=" + feedbackAuditor +
			", state=" + state +
			", approval=" + approval +
			", createTime=" + createTime +
			"}";
	}
}
