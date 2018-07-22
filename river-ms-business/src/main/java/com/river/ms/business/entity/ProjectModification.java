package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目变更
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_modification")
public class ProjectModification implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目节点
	 */
	private Long milestoneId;
	/**
	 * 状态：0-准备资料1-变更申请2-申请完成
	 */
	private Integer state;
	/**
	 * 经办人ITCODE
	 */
	private String itcode;
	/**
	 * 经办人资源ID
	 */
	private Long resourceId;
	@TableField(exist = false)
	private String resourceName;
	private Date createTime;

	/**
	 * 变更主要事项说明
	 */
	private String memo;
	/**
	 * 0-正常变更1-重大变更
	 */
	private Integer major;
	/**
	 * 决策方式:审批制/核准制
	 */
	private Integer entityType;
	/**
	 * 项目评审期限
	 */
	private Date endTime;

	private String PROCESS_INSTANCE_ID_;

	/**
	 * 申报 日期
	 */
	private Date declareDate;
	/**
	 * 评议截止日期
	 */
	private Date approvalDate;

	/**
	 * 0-线上评议会1-线下评议会
	 */
	private Integer online;

	@TableField(exist = false)
	private List<HistoryResult> historyResult;
	
	@TableField(exist = false)
	private Integer processStage = 0;

	public Integer getProcessStage() {
		return processStage;
	}

	public void setProcessStage(Integer processStage) {
		this.processStage = processStage;
	}

	public List<HistoryResult> getHistoryResult() {
		return historyResult;
	}

	public void setHistoryResult(List<HistoryResult> historyResult) {
		this.historyResult = historyResult;
		if(historyResult != null) {
			for(HistoryResult h : historyResult) {
				Date endTime = h.getEndTime();
				if(endTime == null) {
					h.setHistoryResultType(0);
				}else {
					h.setHistoryResultType(1);
				}
			}
			this.setProcessStage(0);
			for(HistoryResult h : historyResult) {
				if (h.getTaskType().equals("startEvent")) {
					this.setProcessStage(1);
					break;
				}
			}
			for(HistoryResult h : historyResult) {
				if (h.getTaskType().equals("endEvent")) {
					this.setProcessStage(2);
					break;
				}
			}
		}
	}

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	public Date getDeclareDate() {
		return declareDate;
	}

	public void setDeclareDate(Date declareDate) {
		this.declareDate = declareDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public String toString() {
		return "ProjectModification{" + ", entityId=" + entityId + ", projectId=" + projectId + ", milestoneId="
				+ milestoneId + ", state=" + state + ", itcode=" + itcode + ", resourceId=" + resourceId
				+ ", createTime=" + createTime + "}";
	}
}
