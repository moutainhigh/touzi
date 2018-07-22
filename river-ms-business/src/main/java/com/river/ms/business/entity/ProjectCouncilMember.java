package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目投决评议会、变更评议会等参会人员
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_council_member")
public class ProjectCouncilMember implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 评议会ID
     */
	@TableField("council_id")
	private Long councilId;
    /**
     * 参会人员ITCODE
     */
	private String itcode;
    /**
     * 参会资源ID
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

	public Long getCouncilId() {
		return councilId;
	}

	public void setCouncilId(Long councilId) {
		this.councilId = councilId;
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
		return "ProjectCouncilMember{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", councilId=" + councilId +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
