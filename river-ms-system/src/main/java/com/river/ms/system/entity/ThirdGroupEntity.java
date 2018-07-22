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
@RiverTable(Name="sys_thirdGroup")
public class ThirdGroupEntity extends EntityBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn
	@Length(min=0,max=50,message="contact长度不能超过50")
	public String contact;
	@RiverColumn
	@Length(min=0,max=50,message="email长度不能超过50")
	@Pattern(regexp=Regex.EMAIL,message="邮箱格式不正确")
	public String email;
	@RiverColumn
	@Length(min=0,max=50,message="mobile长度不能超过50")
	@Pattern(regexp=Regex.MOBILE,message="手机号格式不正确")
	public String mobile;
	@RiverColumn
	@NotNull
	public int groupType;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	
	
	
}
