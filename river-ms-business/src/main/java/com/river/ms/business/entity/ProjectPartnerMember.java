package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 合伙人
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@TableName("project_partner_member")
public class ProjectPartnerMember implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
	
	@NotNull(message = "合伙信息ID不能为空!")
	private Long partnerId;
	
	@NotNull(message = "项目不能为空!")
	private Long projectId;
    /**
     * 合伙人姓名
     */
	private String member;
    /**
     * 合伙百分比
     */
	private BigDecimal rate;
    /**
     * 合伙金额
     */
	private BigDecimal money;
    /**
     * 退出预计收益
     */
	private BigDecimal income;
    /**
     * 合伙人邮箱
     */
	private String memberEmail;
    /**
     * 合伙人资源ID
     */
	private Long memberResId;
    /**
     * 合伙人itcode
     */
	private String memberItcode;
    /**
     * 创建时间
     */
	private Date createTime;


	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Long getMemberResId() {
		return memberResId;
	}

	public void setMemberResId(Long memberResId) {
		this.memberResId = memberResId;
	}

	public String getMemberItcode() {
		return memberItcode;
	}

	public void setMemberItcode(String memberItcode) {
		this.memberItcode = memberItcode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectPartnerMember{" +
			", entityId=" + entityId +
			", partnerId=" + partnerId +
			", projectId=" + projectId +
			", member=" + member +
			", rate=" + rate +
			", money=" + money +
			", income=" + income +
			", memberEmail=" + memberEmail +
			", memberResId=" + memberResId +
			", memberItcode=" + memberItcode +
			", createTime=" + createTime +
			"}";
	}
}
