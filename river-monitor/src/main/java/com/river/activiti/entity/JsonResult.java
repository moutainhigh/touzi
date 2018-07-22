package com.river.activiti.entity;

import java.io.Serializable;

public class JsonResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2637535003881853731L;
	protected int status;
	protected String message;
	protected Object data;

	public JsonResult(int _status,String _message){
		status=_status;
		message=_message;
	}
	public JsonResult(int _status,String _message,Object _data){
		status=_status;
		message=_message;
		data=_data;
	}
	
	public static JsonResult Success(Object data){
		return new JsonResult(0,"success",data);
	}
	
	public static JsonResult Failure(String message){
		return new JsonResult(-1,message);
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
}
