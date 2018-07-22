package com.river.ms.system.entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name="sys_dictionaryItem",isTree=true)
public class DictionaryItemEntity extends TreeEntityBase{
	
	@RiverColumn
	@NotNull
	public Integer dictItemType;
	@RiverColumn
	@Length(min=0,max=20,message="字典项取值长度不能超过20")
	public String dictItemValue;
	public Integer getDictItemType() {
		return dictItemType;
	}
	public void setDictItemType(Integer dictItemType) {
		this.dictItemType = dictItemType;
	}
	public String getDictItemValue() {
		return dictItemValue;
	}
	public void setDictItemValue(String dictItemValue) {
		this.dictItemValue = dictItemValue;
	}
	
	
	

}
