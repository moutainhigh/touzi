package com.river.ms.system.entity;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;

/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name = "sys_industry")
public class IndustryEntity extends TreeEntityBase {
	@RiverColumn
	@Length(min=0,max=50,message="长度不能超过50")
	public String industryEn;
	@RiverColumn
	@Length(min=0, max=20,message="长度不能超过20")
	public String industryAb;

	public String getIndustryEn() {
		return industryEn;
	}

	public void setIndustryEn(String industryEn) {
		this.industryEn = industryEn;
	}

	public String getIndustryAb() {
		return industryAb;
	}

	public void setIndustryAb(String industryAb) {
		this.industryAb = industryAb;
	}

}
