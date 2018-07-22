package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目标签（是否考虑不同角色不同标签）
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_tag")
public class ProjectTag implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 标签代码
     */
	private String entityCode;
    /**
     * 标签名称
     */
	private String entityTitle;
    /**
     * 备注
     */
	private String entityDesc;
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

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectTag{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", entityCode=" + entityCode +
			", entityTitle=" + entityTitle +
			", entityDesc=" + entityDesc +
			", createTime=" + createTime +
			"}";
	}
}
