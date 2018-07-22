package com.river.activiti.entity;

import java.util.Date;

/**
 * 
 * @author Yinovo
 *
 */
public class HistoryResult {
	private String id;
	private String taskId;
	private String taskKey;
	private String taskTitle;
	private String processId;
	private Date startTime;
	private Date endTime;
	private String itcode;
	private String taskType;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the taskTitle
	 */
	public String getTaskTitle() {
		return taskTitle;
	}
	/**
	 * @param taskTitle the taskTitle to set
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the itcode
	 */
	public String getItcode() {
		return itcode;
	}
	/**
	 * @param itcode the itcode to set
	 */
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
}
