package com.river.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
import com.river.core.validator.Regex;

/**
 * 
 * @author 人员信息表
 *
 */
@RiverTable(Name = "sys_employee")
public class EmployeeEntity extends EntityBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RiverColumn
	@Length(min = 0, max = 50, message = "长度不能超过50")
	@Pattern(regexp = Regex.CARD_NO, message = "身份证格式不正确")
	public String idCard;

	@RiverColumn
	@Length(min = 0, max = 50, message = "长度不能超过50")
	@Pattern(regexp = Regex.EMAIL, message = "邮箱格式不正确")
	public String email;

	@RiverColumn
	@Length(min = 0, max = 50, message = "长度不能超过50")
	@Pattern(regexp = Regex.MOBILE, message = "手机号格式不正确")
	public String mobile;

	@RiverColumn
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date dateOfBirth;

	@RiverColumn
	public int isActive;

	/**
	 * 对应的岗位
	 */
	public List<StationEmployeeEntity> stationEmployees;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public List<StationEmployeeEntity> getStationEmployees() {
		return stationEmployees;
	}

	public void setStationEmployees(List<StationEmployeeEntity> stationEmployees) {
		this.stationEmployees = stationEmployees;
	}

}
