package com.river.ms.business.entity;

import java.io.Serializable;

public class ProcessResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String processId;
	private String processKey;
	private Integer processType;
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	public Integer getProcessType() {
		return processType;
	}
	public void setProcessType(Integer processType) {
		this.processType = processType;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
}
