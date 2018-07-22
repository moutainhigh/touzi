package com.river.activiti.entity;

import java.util.ArrayList;
import java.util.List;

public class ActivitiResult {
	private String processInstanceId;//流程实例ID
	private String processKey;//流程标识
	private String taskState;//节点状态
	private String taskMemo;//说明 
	private List<TaskResult> tasks;
	/**
	 * @return the processInstanceId
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	/**
	 * @return the processKey
	 */
	public String getProcessKey() {
		return processKey;
	}
	/**
	 * @param processKey the processKey to set
	 */
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	/**
	 * @return the tasks
	 */
	public List<TaskResult> getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<TaskResult> tasks) {
		this.tasks = tasks;
	}
	public void addTaskResult(TaskResult task){
		if(this.tasks==null)
			this.tasks=new ArrayList<TaskResult>();
		if(task!=null)
		tasks.add(task);
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getTaskMemo() {
		return taskMemo;
	}
	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}
}
