package com.river.ms.cms.entity;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
/**
 * 
 * @author zhouheng
 * 资源种类表
 */
@RiverTable(Name="cms_category")
public class CategoryEntity extends TreeEntityBase {
	
	/**
	 * 0-文章分类 1-页面
	 */
	@RiverColumn
	public int entityType;

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "CategoryEntity [entityType=" + entityType + ", entityId=" + entityId + ", entityCode=" + entityCode
				+ ", entityTitle=" + entityTitle + ", entityGUID=" + entityGUID + ", entityDesc=" + entityDesc
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + ", isDisable=" + isDisable + ", parentId=" + parentId
				+ ", disableTime=" + disableTime + "]";
	}
	
}
