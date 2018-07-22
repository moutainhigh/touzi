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
 * @since 2017-12-29
 */
@TableName("view_project_expert")
public class ViewProjectExpert implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long entityId;
	/**
	 * 姓名
	 */
	private String entityTitle;
	/**
	 * 备注
	 */
	private String entityDesc;
	/**
	 * 0-内部1-外部
	 */
	private Integer entityType;
	/**
	 * ITCODE
	 */
	private String itcode;
	/**
	 * 外部专家必用
	 */
	private Long userId;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private Integer isDisable;
	private Date disableTime;
	/**
	 * 0-专家1-高级2-首席
	 */
	private Integer level;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目阶段
	 */
	private Long stage;
	/**
	 * 项目中担任角色0-专家 1-风控
	 */
	private Integer role;

	private String stageTitle;

	private Date expertCreateTime;

	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;

	private Long otherTableId;

	@TableField(exist = false)
	private String gongsi;

	private String specialty;

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getGongsi() {
		return gongsi;
	}

	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}

	public Long getOtherTableId() {
		return otherTableId;
	}

	public void setOtherTableId(Long otherTableId) {
		this.otherTableId = otherTableId;
	}

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	@TableField(exist = false)
	private List<ProjectApproval> projectApproval;

	@TableField(exist = false)
	private List<ProjectOpinion> projectOpinion;

	@TableField(exist = false)
	private List<ProjectRisk> projectRisk;

	@TableField(exist = false)
	private Integer flowType;

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public Date getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public List<ProjectApproval> getProjectApproval() {
		return projectApproval;
	}

	public void setProjectApproval(List<ProjectApproval> projectApproval) {
		this.projectApproval = projectApproval;
	}

	public List<ProjectOpinion> getProjectOpinion() {
		return projectOpinion;
	}

	public void setProjectOpinion(List<ProjectOpinion> projectOpinion) {
		this.projectOpinion = projectOpinion;
	}

	public List<ProjectRisk> getProjectRisk() {
		return projectRisk;
	}

	public void setProjectRisk(List<ProjectRisk> projectRisk) {
		this.projectRisk = projectRisk;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	public Date getExpertCreateTime() {
		return expertCreateTime;
	}

	public void setExpertCreateTime(Date expertCreateTime) {
		this.expertCreateTime = expertCreateTime;
	}

	@Override
	public String toString() {
		return "ViewProjectExpert{" + ", entityId=" + entityId + ", entityTitle=" + entityTitle + ", entityDesc="
				+ entityDesc + ", entityType=" + entityType + ", itcode=" + itcode + ", userId=" + userId
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", isDisable=" + isDisable + ", disableTime=" + disableTime
				+ ", level=" + level + ", projectId=" + projectId + ", stage=" + stage + ", role=" + role + "}";
	}
}
