package com.river.ms.res.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资源对应分类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("res_ref_category")
public class ResRefCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 资源分类ID
	 */
	private Long categoryId;
	/**
	 * ITCODE
	 */
	private String itcode;
	/**
	 * 资源ID
	 */
	private Long resourceId;
	private Date createTime;

	@TableField(exist = false)
	private ResList res;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public ResList getRes() {
		return res;
	}

	public void setRes(ResList res) {
		this.res = res;
	}

	@Override
	public String toString() {
		return "ResRefCategory{" + ", entityId=" + entityId + ", categoryId=" + categoryId + ", itcode=" + itcode
				+ ", resourceId=" + resourceId + ", createTime=" + createTime + "}";
	}
}
