package com.river.ms.res.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资源列表
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("res_list")
public class ResList implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 姓名
	 */
	@NotNull(message = "姓名不能为空!")
	private String entityTitle;
	/**
	 * 备注
	 */
	private String entityDesc;
	/**
	 * 0-内部1-外部
	 */
	@NotNull(message = "资源类型不能为空!")
	private Integer entityType;
	/**
	 * ITCODE
	 */
	@NotNull(message = "itcode不能为空!")
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
	private String teleno;
	private String mobile;
	private String email;
	private String perno;
	@TableField(exist = false)
	private String resRoleType;
	@TableField(exist = false)
	private String resRoleCode;

	// 以下两项为专家使用
	@TableField(exist = false)
	private String stage;
	@TableField(exist = false)
	private String role;
	@TableField(exist = false)
	private String projectId;

	@TableField(exist = false)
	private String employeeId;

	@TableField(exist = false)
	private Integer isExternal;
	@TableField(exist = false)
	private String eId;

	private String specialty;

	/**
	 * 0-专家1-高级2-首席
	 */
	private Integer level;

	@TableField(exist = false)
	private String gongsi;

	@TableField(exist = false)
	private List<ResRefTag> tags;

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the isExternal
	 */
	public Integer getIsExternal() {
		return isExternal;
	}

	/**
	 * @param isExternal
	 *            the isExternal to set
	 */
	public void setIsExternal(Integer isExternal) {
		this.isExternal = isExternal;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<ResRefTag> getTags() {
		return tags;
	}

	public void setTags(List<ResRefTag> tags) {
		this.tags = tags;
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

	@Override
	public String toString() {
		return "ResList{" + ", entityId=" + entityId + ", entityTitle=" + entityTitle + ", entityDesc=" + entityDesc
				+ ", entityType=" + entityType + ", itcode=" + itcode + ", userId=" + userId + ", isDelete=" + isDelete
				+ ", deleteTime=" + deleteTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", isDisable=" + isDisable + ", disableTime=" + disableTime + ", level=" + level + "}";
	}

	public String getResRoleType() {
		return resRoleType;
	}

	public void setResRoleType(String resRoleType) {
		this.resRoleType = resRoleType;
	}

	public String getResRoleCode() {
		return resRoleCode;
	}

	public void setResRoleCode(String resRoleCode) {
		this.resRoleCode = resRoleCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStage() {
		return stage;
	}

	public String getGongsi() {
		return gongsi;
	}

	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}

}
