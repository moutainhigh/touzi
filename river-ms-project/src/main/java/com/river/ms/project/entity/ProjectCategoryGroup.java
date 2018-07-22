package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行业公司项目分类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_category_group")
public class ProjectCategoryGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目类型编码
	 */
	private String entityCode;
	/**
	 * 项目类型名称
	 */
	private String entityTitle;
	private String entityGUID;
	/**
	 * 备注
	 */
	private String entityDesc;
	/**
	 * 投资类型
	 */
	private Long categoryId;
	/**
	 * 上级分类ID
	 */
	private Long parentId;
	/**
	 * 关联通用项目类型ID
	 */
	@TableField("src_categoryId")
	private Long srcCategoryId;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private Integer isDisable;
	private Date disableTime;
	/**
	 * 行业公司ID
	 */
	private String groupCode;

	@TableField(exist = false)
	private List<ProjectCategoryGroup> childProjectCategoryGroup;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSrcCategoryId() {
		return srcCategoryId;
	}

	public void setSrcCategoryId(Long srcCategoryId) {
		this.srcCategoryId = srcCategoryId;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public List<ProjectCategoryGroup> getChildProjectCategoryGroup() {
		return childProjectCategoryGroup;
	}

	public void setChildProjectCategoryGroup(List<ProjectCategoryGroup> childProjectCategoryGroup) {
		this.childProjectCategoryGroup = childProjectCategoryGroup;
	}

	@Override
	public String toString() {
		return "ProjectCategoryGroup [entityId=" + entityId + ", entityCode=" + entityCode + ", entityTitle="
				+ entityTitle + ", entityGUID=" + entityGUID + ", entityDesc=" + entityDesc + ", categoryId="
				+ categoryId + ", parentId=" + parentId + ", srcCategoryId=" + srcCategoryId + ", isDelete=" + isDelete
				+ ", deleteTime=" + deleteTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", isDisable=" + isDisable + ", disableTime=" + disableTime + ", groupCode=" + groupCode
				+ ", childProjectCategoryGroup=" + childProjectCategoryGroup + "]";
	}
}
