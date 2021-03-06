package com.river.ms.project.entity;

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
 * 项目分类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_category")
public class ProjectCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目类型编码
	 */
	@NotNull(message = "编码不能为空!")
	private String entityCode;
	/**
	 * 项目类型名称
	 */
	@NotNull(message = "名称不能为空!")
	private String entityTitle;
	private String entityGUID;
	/**
	 * 备注
	 */
	private String entityDesc;
	/**
	 * 0-投资类型1-项目类别
	 */
	@NotNull(message = "类型不能为空!")
	private Integer entityType;
	/**
	 * 上级分类ID
	 */
	private Long parentId;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private Integer isDisable;
	private Date disableTime;

	@TableField(exist=false)
	private List<ProjectCategory> childProjectCategory;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityGUID() {
		return entityGUID;
	}

	public void setEntityGUID(String entityGUID) {
		this.entityGUID = entityGUID;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public List<ProjectCategory> getChildProjectCategory() {
		return childProjectCategory;
	}

	public void setChildProjectCategory(List<ProjectCategory> childProjectCategory) {
		this.childProjectCategory = childProjectCategory;
	}

	@Override
	public String toString() {
		return "ProjectCategory{" + ", entityId=" + entityId + ", entityCode=" + entityCode + ", entityTitle="
				+ entityTitle + ", entityGUID=" + entityGUID + ", entityDesc=" + entityDesc + ", entityType="
				+ entityType + ", parentId=" + parentId + ", isDelete=" + isDelete + ", deleteTime=" + deleteTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", isDisable=" + isDisable
				+ ", disableTime=" + disableTime + "}";
	}
}
