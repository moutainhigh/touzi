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
 * 项目投决评议会，变更评审会
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_council")
public class ProjectCouncil implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;

	private String entityCode;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 0-线上评议会1-线下评议会
	 */
	private Integer online;
	/**
	 * 评议会时间
	 */
	@TableField("council_time")
	private Date councilTime;
	/**
	 * 会议内容
	 */
	private String content;
	/**
	 * 会议结论
	 */
	private String result;
	/**
	 * 会议建议
	 */
	private String view;
	/**
	 * 会议结果状态（保留）
	 */
	private Integer state;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 资源ID
	 */
	private Long resourceId;
	private Date createTime;
	/**
	 * 1投决会2变更评议会
	 */
	private Integer entityType;
	/**
	 * 申请ID
	 */
	private Long applicationId;

	/**
	 * 项目阶段
	 */
	private Long stage;

	/**
	 * 评议会停止时间
	 */
	private Date endTime;

	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;

	@TableField(exist = false)
	List<ProjectCouncilMember> projectCouncilMemberList;

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
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

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Date getCouncilTime() {
		return councilTime;
	}

	public void setCouncilTime(Date councilTime) {
		this.councilTime = councilTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
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

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public List<ProjectCouncilMember> getProjectCouncilMemberList() {
		return projectCouncilMemberList;
	}

	public void setProjectCouncilMemberList(List<ProjectCouncilMember> projectCouncilMemberList) {
		this.projectCouncilMemberList = projectCouncilMemberList;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ProjectCouncil [entityId=" + entityId + ", entityCode=" + entityCode + ", projectId=" + projectId
				+ ", online=" + online + ", councilTime=" + councilTime + ", content=" + content + ", result=" + result
				+ ", view=" + view + ", state=" + state + ", itcode=" + itcode + ", resourceId=" + resourceId
				+ ", createTime=" + createTime + ", entityType=" + entityType + ", applicationId=" + applicationId
				+ ", projectCouncilMemberList=" + projectCouncilMemberList + "]";
	}
}
