package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目竣工/结项申请
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_completion")
public class ProjectCompletion implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 状态：0-准备资料1-提出申请2-申请完成
     */
	private Integer state;
    /**
     * 0-竣工申请1-结项申请
     */
	private Integer entityType;
    /**
     * 经办人ITCODE
     */
	private String itcode;
    /**
     * 经办人资源ID
     */
	private Long resourceId;
	private Date createTime;


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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	@Override
	public String toString() {
		return "ProjectCompletion{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", state=" + state +
			", entityType=" + entityType +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
