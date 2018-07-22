package com.river.ms.ribbon;

public class JsonResult {
	String message;
	int status;
	Object data;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	public JsonResult(int _status,String _message){
		status=_status;
		message=_message;
	}
	public JsonResult(int status,String message,Object data){
		this.status=status;
		this.message=message;
		this.data=data;
	}
}
