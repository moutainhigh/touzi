package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目各阶段专家确定
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_expert")
public class ProjectExpert implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
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
	 * 资源ID
	 */
	private Long resourceId;
	/**
	 * ITCODE
	 */
	private String itcode;
	/**
	 * 项目中担任角色 0-专家 1-风控
	 */
	private Integer role;
	/**
	 * 0-默认1-核准
	 */
	private Integer state;
	private Date createTime;

	/**
	 * 姓名
	 */
	@TableField(exist = false)
	private String entityTitle;
	/**
	 * 备注
	 */
	@TableField(exist = false)
	private String entityDesc;
	/**
	 * 0-内部1-外部
	 */
	@TableField(exist = false)
	private Integer entityType;
	/**
	 * 外部专家必用
	 */
	@TableField(exist = false)
	private Long userId;

	@TableField(exist = false)
	private String teleno;
	@TableField(exist = false)
	private String mobile;
	@TableField(exist = false)
	private String email;
	@TableField(exist = false)
	private String perno;
	@TableField(exist = false)
	private String gongsi;

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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTeleno() {
		return teleno;
	}

	public void setTeleno(String teleno) {
		this.teleno = teleno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerno() {
		return perno;
	}

	public void setPerno(String perno) {
		this.perno = perno;
	}

	public String getGongsi() {
		return gongsi;
	}

	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}
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

	@Override
	public String toString() {
		return "ProjectExpert{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", resourceId=" + resourceId + ", itcode=" + itcode + ", role=" + role + ", state=" + state
				+ ", createTime=" + createTime + "}";
	}
}
