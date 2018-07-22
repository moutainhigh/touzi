package com.river.ms.business.entity;

import java.util.Date;

public class HistoryResult implements Comparable<HistoryResult> {
	//
	private String id;
	// 节点ID
	private String taskId;
	// 节点code
	private String taskKey;
	// 名称
	private String taskTitle;
	//
	private String processId;
	//
	private Date startTime;
	//
	private Date endTime;
	// 操作用户
	private String itcode;
	// 类型：开始节点，结束节点，用户节点
	private String taskType;
	// 用户id
	private Long resId;
	// 用户姓名
	private String userName;
	// 阶段ID
	private Long stageId;
	// 文件数量
	private Integer docNum = 0;

	private Integer historyResultType;

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Integer getDocNum() {
		return docNum;
	}

	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param taskKey
	 *            the taskKey to set
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
	 * @param taskTitle
	 *            the taskTitle to set
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
	 * @param processId
	 *            the processId to set
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
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
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
	 * @param itcode
	 *            the itcode to set
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

	public HistoryResult() {
		super();
	}

	public Integer getHistoryResultType() {
		return historyResultType;
	}

	public void setHistoryResultType(Integer historyResultType) {
		this.historyResultType = historyResultType;
	}

	public HistoryResult(String id, String taskId, String taskKey, String taskTitle, String processId, Date startTime,
			Date endTime, String itcode, String taskType) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.taskKey = taskKey;
		this.taskTitle = taskTitle;
		this.processId = processId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.itcode = itcode;
		this.taskType = taskType;
	}

	@Override
	public String toString() {
		return "HistoryResult [id=" + id + ", taskId=" + taskId + ", taskKey=" + taskKey + ", taskTitle=" + taskTitle
				+ ", processId=" + processId + ", startTime=" + startTime + ", endTime=" + endTime + ", itcode="
				+ itcode + ", taskType=" + taskType + "]";
	}

	@Override
	public int compareTo(HistoryResult o) {
		if (this.getStartTime().before(o.getStartTime())) {
			return -1;
		}
		if (this.getStartTime().equals(o.getStartTime())) {
			if (this.getEndTime() == null) {
				return 1;
			}
			if (o.getEndTime() == null) {
				return -1;
			}
			if (this.getEndTime().before(o.getEndTime())) {
				return -1;
			}
		}
		return 1;
	}

}