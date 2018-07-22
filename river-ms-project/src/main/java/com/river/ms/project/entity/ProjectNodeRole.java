package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 流程节点角色
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@TableName("project_node_role")
public class ProjectNodeRole implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
	private Long nodeId;
	private Long roleId;


	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "ProjectNodeRole{" +
			", entityId=" + entityId +
			", nodeId=" + nodeId +
			", roleId=" + roleId +
			"}";
	}
}
