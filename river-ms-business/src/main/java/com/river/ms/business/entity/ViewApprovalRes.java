package com.river.ms.business.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@TableName("view_approval_res")
public class ViewApprovalRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目阶段
	 */
	private Long stage;
	/**
	 * 审批意见
	 */
	private String memo;
	/**
	 * 是否通过:0-拒绝1-通过2-有条件通过
	 */
	private Integer result;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	private Date createTime;
	/**
	 * 建议
	 */
	private String propose;
	/**
	 * 1-专家，0-其他
	 */
	private Integer type;
	/**
	 * 姓名
	 */
	private String entityTitle;

	private String stageTitle;

	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	/**
	 * 有条件通过时的条件
	 */
	@TableField(exist = false)
	private List<ProjectOpinionCondition> projectOpinionConditions;

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

	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
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

	public String getPropose() {
		return propose;
	}

	public void setPropose(String propose) {
		this.propose = propose;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public List<ProjectOpinionCondition> getProjectOpinionConditions() {
		return projectOpinionConditions;
	}

	public void setProjectOpinionConditions(List<ProjectOpinionCondition> projectOpinionConditions) {
		this.projectOpinionConditions = projectOpinionConditions;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	@Override
	public String toString() {
		return "ViewApprovalRes{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", memo=" + memo + ", result=" + result + ", itcode=" + itcode + ", resourceId=" + resourceId
				+ ", createTime=" + createTime + ", propose=" + propose + ", type=" + type + ", entityTitle="
				+ entityTitle + "}";
	}
}
