package com.river.ms.system.entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
import com.river.core.validator.Regex;
/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name="sys_log")
public class LogEntity extends EntityBase{
	@RiverColumn
	@NotNull
	public Integer logType;
	@RiverColumn
	@Length(min=0,max=50,message="长度不能超过50")
	@Pattern(regexp=Regex.IP_ADDRESS,message="ip地址格式不正确")
	public String ipAddress;
	@RiverColumn
	@NotNull
	public Long userId;

	public String userName;
	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
