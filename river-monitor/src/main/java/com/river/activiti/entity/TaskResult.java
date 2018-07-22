package com.river.activiti.entity;

public class TaskResult {
	private String taskId;
	private String taskKey;
	private String groups;
	private String users;
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the taskKey
	 */
	public String getTaskKey() {
		return taskKey;
	}
	/**
	 * @param taskKey the taskKey to set
	 */
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	/**
	 * @return the groups
	 */
	public String getGroups() {
		return groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(String groups) {
		this.groups = groups;
	}
	/**
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}
	
}
