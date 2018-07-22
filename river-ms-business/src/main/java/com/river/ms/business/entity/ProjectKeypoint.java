package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目关键指标
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_keypoint")
public class ProjectKeypoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目总投资额
	 */
	private BigDecimal f1;
	/**
	 * 我方持股比例
	 */
	private BigDecimal f2;
	/**
	 * 我方投资额
	 */
	private BigDecimal f3;
	/**
	 * 我方投资额（股权部分）
	 */
	private BigDecimal f4;
	/**
	 * 我方投资额（债券部分）
	 */
	private BigDecimal f5;
	/**
	 * 项目IRR%-内部收益率
	 */
	private BigDecimal f6;
	/**
	 * 静态投资回收期
	 */
	private BigDecimal f7;
	/**
	 * 折现率
	 */
	private BigDecimal f8;
	/**
	 * 项目NPV
	 */
	private BigDecimal f9;
	/**
	 * 预计投资时间
	 */
	private Date f10;
	/**
	 * 预计动工时间
	 */
	private Date f11;
	/**
	 * 预计投产时间
	 */
	private Date f12;
	private Date createTime;
	private Date updateTime;
	/**
	 * 资本金内部收益率
	 */
	private BigDecimal f13;
	/**
	 * 动态投资回收期
	 */
	private BigDecimal f14;

	/**
	 * 项目实际投资总额
	 */
	private BigDecimal f15;
	/**
	 * 项目实际签约日期
	 */
	private Date f16;
	/**
	 * 项目实际开工日期
	 */
	private Date f17;
	/**
	 * 当前已投资额
	 */
	private BigDecimal f18;
	/**
	 * 投资额偏差
	 */
	private BigDecimal f19;
	/**
	 * 投资额偏差百分比
	 */
	private BigDecimal f20;
	/**
	 * 项目批复总投资额
	 */
	private BigDecimal f21;
	/**
	 * 项目计划总工期/期限（月）
	 */
	private BigDecimal f22;

	private BigDecimal f23;

	public BigDecimal getF23() {
		return f23;
	}

	public void setF23(BigDecimal f23) {
		this.f23 = f23;
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

	public BigDecimal getF7() {
		return f7;
	}

	public void setF7(BigDecimal f7) {
		this.f7 = f7;
	}

	public BigDecimal getF8() {
		return f8;
	}

	public void setF8(BigDecimal f8) {
		this.f8 = f8;
	}

	public BigDecimal getF9() {
		return f9;
	}

	public void setF9(BigDecimal f9) {
		this.f9 = f9;
	}

	public Date getF10() {
		return f10;
	}

	public void setF10(Date f10) {
		this.f10 = f10;
	}

	public Date getF11() {
		return f11;
	}

	public void setF11(Date f11) {
		this.f11 = f11;
	}

	public Date getF12() {
		return f12;
	}

	public void setF12(Date f12) {
		this.f12 = f12;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getF13() {
		return f13;
	}

	public void setF13(BigDecimal f13) {
		this.f13 = f13;
	}

	public BigDecimal getF14() {
		return f14;
	}

	public void setF14(BigDecimal f14) {
		this.f14 = f14;
	}

	public BigDecimal getF15() {
		return f15;
	}

	public void setF15(BigDecimal f15) {
		this.f15 = f15;
	}

	public Date getF16() {
		return f16;
	}

	public void setF16(Date f16) {
		this.f16 = f16;
	}

	public Date getF17() {
		return f17;
	}

	public void setF17(Date f17) {
		this.f17 = f17;
	}

	public BigDecimal getF18() {
		return f18;
	}

	public void setF18(BigDecimal f18) {
		this.f18 = f18;
	}

	public BigDecimal getF19() {
		return f19;
	}

	public void setF19(BigDecimal f19) {
		this.f19 = f19;
	}

	public BigDecimal getF20() {
		return f20;
	}

	public void setF20(BigDecimal f20) {
		this.f20 = f20;
	}

	public BigDecimal getF21() {
		return f21;
	}

	public void setF21(BigDecimal f21) {
		this.f21 = f21;
	}

	public BigDecimal getF22() {
		return f22;
	}

	public void setF22(BigDecimal f22) {
		this.f22 = f22;
	}

	@Override
	public String toString() {
		return "ProjectKeypoint{" + ", entityId=" + entityId + ", projectId=" + projectId + ", f1=" + f1 + ", f2=" + f2
				+ ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6 + ", f7=" + f7 + ", f8=" + f8 + ", f9=" + f9
				+ ", f10=" + f10 + ", f11=" + f11 + ", f12=" + f12 + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", f13=" + f13 + ", f14=" + f14 + "}";
	}
}
