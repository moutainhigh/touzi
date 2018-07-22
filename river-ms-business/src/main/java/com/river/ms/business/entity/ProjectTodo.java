package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 待办事项：每次处理完更新此表
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
@TableName("project_todo")
public class ProjectTodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 对应资源ID
	 */
	private Long resourceId;
	/**
	 * 对应用户ITCODE
	 */
	private String itcode;
	private Date createTime;
	/**
	 * 截止日期
	 */
	private Date endTime;
	private Date finishTime;
	/**
	 * 0-待处理1-处理完毕
	 */
	private Integer state;
	private String groupCode;

	/**
	 * 0-批量删除，1-单个删除
	 */
	private Integer type;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProjectTodo{" + ", entityId=" + entityId + ", projectId=" + projectId + ", roleId=" + roleId
				+ ", resourceId=" + resourceId + ", itcode=" + itcode + ", createTime=" + createTime + ", endTime="
				+ endTime + ", finishTime=" + finishTime + ", state=" + state + "}";
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
