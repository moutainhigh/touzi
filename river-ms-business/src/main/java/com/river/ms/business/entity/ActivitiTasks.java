package com.river.ms.business.entity;

public class ActivitiTasks {

	/**
	 * 节点ID
	 */
	private String taskId;
	/**
	 * 代表节点
	 */
	private String taskKey;
	/**
	 * 分组
	 */
	private String groups;
	/**
	 * 用户
	 */
	private String users;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public ActivitiTasks(String taskId, String taskKey, String groups, String users) {
		super();
		this.taskId = taskId;
		this.taskKey = taskKey;
		this.groups = groups;
		this.users = users;
	}

	public ActivitiTasks() {
		super();
	}

}
