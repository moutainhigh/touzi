package com.river.ms.system.entity;
import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name="sys_dictionary")
public class DictionaryEntity extends EntityBase{
	@RiverColumn
	@NotNull
	public Integer dictionaryType;

	public Integer getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(Integer dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
	
	
	

}
