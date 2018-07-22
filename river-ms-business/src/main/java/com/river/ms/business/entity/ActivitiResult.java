package com.river.ms.business.entity;

import java.util.List;

public class ActivitiResult {

	/**
	 * 0=正常 -1=失败
	 */
	private int status;
	private String message;
	private String taskMemo;
	private String taskState;
	/**
	 * 流程实例ID
	 */
	private String processInstanceId;
	/**
	 * 流程类型
	 */
	private String processKey;
	private List<ActivitiTasks> tasks;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public List<ActivitiTasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<ActivitiTasks> tasks) {
		this.tasks = tasks;
	}

	public ActivitiResult(int status, String message, String processInstanceId, String processKey,
			List<ActivitiTasks> tasks) {
		super();
		this.status = status;
		this.message = message;
		this.processInstanceId = processInstanceId;
		this.processKey = processKey;
		this.tasks = tasks;
	}

	public ActivitiResult() {
		super();
	}

	public String getTaskMemo() {
		return taskMemo;
	}

	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

}
