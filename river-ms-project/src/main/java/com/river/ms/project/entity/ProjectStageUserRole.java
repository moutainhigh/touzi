package com.river.ms.project.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户在项目中的身份信息
 * @author Yinovo
 *
 */
public class ProjectStageUserRole {
	private Long projectId;//项目ID
	private Long stageId;//项目阶段ID
	private String stageCode;//项目阶段编码
	private List<String> roles;
	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the stageId
	 */
	public Long getStageId() {
		return stageId;
	}
	/**
	 * @param stageId the stageId to set
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	/**
	 * @return the stageCode
	 */
	public String getStageCode() {
		return stageCode;
	}
	/**
	 * @param stageCode the stageCode to set
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		if(this.roles==null)
			this.roles=new ArrayList<String>();
		this.roles.add(role);
	}
}
