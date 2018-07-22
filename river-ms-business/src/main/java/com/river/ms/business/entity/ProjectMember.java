package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目组成员
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_member")
public class ProjectMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 操作用户资源ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	/**
	 * 成员ITCODE
	 */
	private String memberitcode;
	/**
	 * 成员ID
	 */
	private Long memberId;
	/**
	 * 成员姓名
	 */
	private String entityTitle;
	/**
	 * 项目角色
	 */
	private String projectRole;
	/**
	 * 0-待定1-在项目组2-离开项目组
	 */
	private Integer state;
	private Date createTime;
	private Date updateTime;

	/**
	 * 成员标识：0-普通成员，1-技术负责人
	 */
	private Integer memberType;

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

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

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getMemberitcode() {
		return memberitcode;
	}

	public void setMemberitcode(String memberitcode) {
		this.memberitcode = memberitcode;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "ProjectMember{" + ", entityId=" + entityId + ", projectId=" + projectId + ", itcode=" + itcode
				+ ", entityTitle=" + entityTitle + ", projectRole=" + projectRole + ", state=" + state + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "}";
	}
}
