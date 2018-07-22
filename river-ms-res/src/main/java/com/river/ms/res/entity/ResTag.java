package com.river.ms.res.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资源标签
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("res_tag")
public class ResTag implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 标签代码
     */
	@NotNull(message = "编码不能为空!")
	private String entityCode;
    /**
     * 标签名称
     */
	@NotNull(message = "标题不能为空!")
	private String entityTitle;
	private String entityGUID;
    /**
     * 备注
     */
	private String entityDesc;
    /**
     * 上级标签ID
     */
	private Long parentId;
	private Date createTime;
	private Date updateTime;


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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	@Override
	public String toString() {
		return "ResTag{" +
			", entityId=" + entityId +
			", entityCode=" + entityCode +
			", entityTitle=" + entityTitle +
			", entityGUID=" + entityGUID +
			", entityDesc=" + entityDesc +
			", parentId=" + parentId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
