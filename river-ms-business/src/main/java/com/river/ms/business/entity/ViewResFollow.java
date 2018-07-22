package com.river.ms.business.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author zyb
 * @since 2017-12-26
 */
@TableName("view_res_follow")
public class ViewResFollow implements Serializable {

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
     * 当前用户
     */
	private Long resourceId;


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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return "ViewResFollow{" +
			", entityId=" + entityId +
			", entityTitle=" + entityTitle +
			", entityDesc=" + entityDesc +
			", entityType=" + entityType +
			", itcode=" + itcode +
			", userId=" + userId +
			", isDelete=" + isDelete +
			", deleteTime=" + deleteTime +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", isDisable=" + isDisable +
			", disableTime=" + disableTime +
			", level=" + level +
			", resourceId=" + resourceId +
			"}";
	}
}
