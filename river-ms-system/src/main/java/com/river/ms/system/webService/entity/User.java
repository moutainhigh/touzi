package com.river.ms.system.webService.entity;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class User {

	@XmlTransient
	private Long entityId;

	@XmlElement
	private String userId;// 用户id（必填）

	@XmlElement
	private String userName;// 姓名（必填）

	@XmlElement
	private String userPost;// 用户职位必填（）

	@XmlTransient
	private Timestamp createAt;// 创建时间（不用推送）

	@XmlElement
	private String userSex;// 性别（必填）

	@XmlElement
	private int userAge;// 年龄（必填）

	@XmlElement
	private String department;// 用户部门（必填）

	@XmlElement
	private String jobNumber;// 工号（必填并保证唯一，登录账号应该就是工号吧）

	@XmlElement
	private String orgeh;// 组织号（必填）

	@XmlElement
	private String plans;// 职位编码 来源职位表

	@XmlElement
	private String mobile;// 手机号码

	@XmlElement
	private String email;// 电子邮件

	@XmlElement
	private String type;// 是否兼职 来源职位表

	@XmlElement
	private String manag;// 是否主管 来源职位表

	@XmlElement
	private String stell;// 职务 来源职位表

	@XmlElement
	private int isOnJob;// 在职状态：是否离职

	@XmlElement
	private int operaction;// 操作类型，1：新增，2：更新，3：删除

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getOrgeh() {
		return orgeh;
	}

	public void setOrgeh(String orgeh) {
		this.orgeh = orgeh;
	}

	public String getPlans() {
		return plans;
	}

	public void setPlans(String plans) {
		this.plans = plans;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManag() {
		return manag;
	}

	public void setManag(String manag) {
		this.manag = manag;
	}

	public String getStell() {
		return stell;
	}

	public void setStell(String stell) {
		this.stell = stell;
	}

	public int getIsOnJob() {
		return isOnJob;
	}

	public void setIsOnJob(int isOnJob) {
		this.isOnJob = isOnJob;
	}

	public int getOperaction() {
		return operaction;
	}

	public void setOperaction(int operaction) {
		this.operaction = operaction;
	}

	public User() {
		super();
	}

	public User(Long entityId, String userId, String userName, String userPost, Timestamp createAt, String userSex,
			int userAge, String department, String jobNumber, String orgeh, String plans, String mobile, String email,
			String type, String manag, String stell, int isOnJob, int operaction) {
		super();
		this.entityId = entityId;
		this.userId = userId;
		this.userName = userName;
		this.userPost = userPost;
		this.createAt = createAt;
		this.userSex = userSex;
		this.userAge = userAge;
		this.department = department;
		this.jobNumber = jobNumber;
		this.orgeh = orgeh;
		this.plans = plans;
		this.mobile = mobile;
		this.email = email;
		this.type = type;
		this.manag = manag;
		this.stell = stell;
		this.isOnJob = isOnJob;
		this.operaction = operaction;
	}

	@Override
	public String toString() {
		return "User [entityId=" + entityId + ", userId=" + userId + ", userName=" + userName + ", userPost=" + userPost
				+ ", createAt=" + createAt + ", userSex=" + userSex + ", userAge=" + userAge + ", department="
				+ department + ", jobNumber=" + jobNumber + ", orgeh=" + orgeh + ", plans=" + plans + ", mobile="
				+ mobile + ", email=" + email + ", type=" + type + ", manag=" + manag + ", stell=" + stell
				+ ", isOnJob=" + isOnJob + ", operaction=" + operaction + "]";
	}

}
