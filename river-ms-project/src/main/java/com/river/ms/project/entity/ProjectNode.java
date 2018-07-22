package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 流程节点定义
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@TableName("project_node")
public class ProjectNode implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 节点编码
	 */
	@NotNull(message = "编码不能为空!")
	private String entityCode;
	/**
	 * 节点名称
	 */
	private String entityTitle;
	/**
	 * 节点描述
	 */
	private String entityDesc;
	/**
	 * 节点对应对话/url
	 */
	private String flowUrl;
	
	private String nodeIcon;

	/**
	 * 可以操作的项目列表
	 */
	@TableField(exist = false)
	private List<ProjectList> projectList = new ArrayList<>();

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

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getFlowUrl() {
		return flowUrl;
	}

	public void setFlowUrl(String flowUrl) {
		this.flowUrl = flowUrl;
	}

	public List<ProjectList> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectList> projectList) {
		this.projectList = projectList;
	}

	@Override
	public String toString() {
		return "ProjectNode{" + ", entityId=" + entityId + ", entityCode=" + entityCode + ", entityTitle=" + entityTitle
				+ ", entityDesc=" + entityDesc + ", flowUrl=" + flowUrl + "}";
	}

	public String getNodeIcon() {
		return nodeIcon;
	}

	public void setNodeIcon(String nodeIcon) {
		this.nodeIcon = nodeIcon;
	}
}
