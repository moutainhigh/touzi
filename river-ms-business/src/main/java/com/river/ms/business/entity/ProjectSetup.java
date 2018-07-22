package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目立项申请
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_setup")
public class ProjectSetup implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目申报日期
	 */
	private Date declareDate;
	/**
	 * 项目评审日期
	 */
	private Date approvalDate;
	/**
	 * 决策方式 1-审批制，2-核准制
	 */
	@TableField("decision_mode")
	private Integer decisionMode;
	/**
	 * 申请状态：0-准备资料10-待立项审核20-立项审核通过待可研30-待投决
	 */
	private Integer state;
	private Date createTime;

	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	/**
	 * 立项说明 20180112增加
	 */
	private String memo;
	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;

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

	public Integer getDecisionMode() {
		return decisionMode;
	}

	public void setDecisionMode(Integer decisionMode) {
		this.decisionMode = decisionMode;
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

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	@Override
	public String toString() {
		return "ProjectSetup{" + ", entityId=" + entityId + ", projectId=" + projectId + ", declareDate=" + declareDate
				+ ", approvalDate=" + approvalDate + ", decisionMode=" + decisionMode + ", state=" + state
				+ ", createTime=" + createTime + "}";
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
