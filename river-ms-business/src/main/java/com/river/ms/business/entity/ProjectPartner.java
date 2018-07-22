package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 合伙方案
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@TableName("project_partner")
public class ProjectPartner implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;

	@NotNull(message = "项目不能为空!")
	private Long projectId;
	/**
	 * 合伙投资上限
	 */
	private BigDecimal f1;
	/**
	 * 合伙比例
	 */
	private BigDecimal f2;
	/**
	 * 项目合伙金额
	 */
	private BigDecimal f3;
	/**
	 * 项目退出前一年预计PE倍数
	 */
	private BigDecimal f4;
	/**
	 * 项目退出前一年预计净利润
	 */
	private BigDecimal f5;
	/**
	 * 项目合伙退出预计收益
	 */
	private BigDecimal f6;
	/**
	 * 项目预计投产日期
	 */
	private Date d1;
	/**
	 * 项目预计退出日期
	 */
	private Date d2;
	/**
	 * 创建用户
	 */
	private String itcode;
	/**
	 * 创建用户资源ID
	 */
	private Long resId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@TableField(exist = false)
	private String resName;

	@TableField(exist = false)
	private List<ProjectPartnerMember> projectPartnerMember;

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public List<ProjectPartnerMember> getProjectPartnerMember() {
		return projectPartnerMember;
	}

	public void setProjectPartnerMember(List<ProjectPartnerMember> projectPartnerMember) {
		this.projectPartnerMember = projectPartnerMember;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getF1() {
		return f1;
	}

	public void setF1(BigDecimal f1) {
		this.f1 = f1;
	}

	public BigDecimal getF2() {
		return f2;
	}

	public void setF2(BigDecimal f2) {
		this.f2 = f2;
	}

	public BigDecimal getF3() {
		return f3;
	}

	public void setF3(BigDecimal f3) {
		this.f3 = f3;
	}

	public BigDecimal getF4() {
		return f4;
	}

	public void setF4(BigDecimal f4) {
		this.f4 = f4;
	}

	public BigDecimal getF5() {
		return f5;
	}

	public void setF5(BigDecimal f5) {
		this.f5 = f5;
	}

	public BigDecimal getF6() {
		return f6;
	}

	public void setF6(BigDecimal f6) {
		this.f6 = f6;
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectPartner{" + ", entityId=" + entityId + ", projectId=" + projectId + ", f1=" + f1 + ", f2=" + f2
				+ ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6 + ", d1=" + d1 + ", d2=" + d2 + ", itcode="
				+ itcode + ", resId=" + resId + ", createTime=" + createTime + "}";
	}
}
