package com.river.ms.system.entity;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name="sys_config")
public class ConfigEntity extends EntityBase{
	@RiverColumn
	public Integer configType;
	
	@RiverColumn
	@Length(min=0,max=255,message="configValue长度不能超过255")
	public String configValue;

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	

}
