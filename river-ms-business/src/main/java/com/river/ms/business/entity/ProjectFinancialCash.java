package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.river.core.annotation.DataInput;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目财务数据现金表
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_financial_cash")
public class ProjectFinancialCash implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 年度
     */
	private Integer year;
    /**
     * 季度
     */
	@DataInput(columnName="季度")
	private String quarter;
    /**
     * 经营活动产生的现金流量：
     */
	@DataInput(columnName="经营活动产生的现金流量")
	private BigDecimal f1;
    /**
     * 销售商品、提供劳务收到的现金
     */
	@DataInput(columnName="销售商品、提供劳务收到的现金")
	private BigDecimal f2;
    /**
     * 收到的税费返还
     */
	@DataInput(columnName="收到的税费返还")
	private BigDecimal f3;
    /**
     * 收到其他与经营活动有关的现金
     */
	@DataInput(columnName="收到其他与经营活动有关的现金")
	private BigDecimal f4;
    /**
     * 经营活动现金流入小计
     */
	@DataInput(columnName="经营活动现金流入小计")
	private BigDecimal f5;
    /**
     * 购买商品、接受劳务支付的现金
     */
	@DataInput(columnName="购买商品、接受劳务支付的现金")
	private BigDecimal f6;
    /**
     * 支付给职工以及为职工支付的现金
     */
	@DataInput(columnName="支付给职工以及为职工支付的现金")
	private BigDecimal f7;
    /**
     * 支付的各项税费
     */
	@DataInput(columnName="支付的各项税费")
	private BigDecimal f8;
    /**
     * 支付其他与经营活动有关的现金
     */
	@DataInput(columnName="支付其他与经营活动有关的现金")
	private BigDecimal f9;
    /**
     * 经营活动现金流出小计
     */
	@DataInput(columnName="经营活动现金流出小计")
	private BigDecimal f10;
    /**
     * 经营活动产生的现金流量净额
     */
	@DataInput(columnName="经营活动产生的现金流量净额")
	private BigDecimal f11;
    /**
     * 投资活动产生的现金流量：
     */
	@DataInput(columnName="投资活动产生的现金流量")
	private BigDecimal k1;
    /**
     * 收回投资收到的现金
     */
	@DataInput(columnName="收回投资收到的现金")
	private BigDecimal k2;
    /**
     * 取得投资收益收到的现金
     */
	@DataInput(columnName="取得投资收益收到的现金")
	private BigDecimal k3;
    /**
     * 处置固定资产、无形资产和其他长期资产收回的现金净额
     */
	@DataInput(columnName="处置固定资产、无形资产和其他长期资产收回的现金净额")
	private BigDecimal k4;
    /**
     * 处置子公司及其他营业单位收到的现金净额
     */
	@DataInput(columnName="处置子公司及其他营业单位收到的现金净额")
	private BigDecimal k5;
    /**
     * 收到其他与投资活动有关的现金
     */
	@DataInput(columnName="收到其他与投资活动有关的现金")
	private BigDecimal k6;
    /**
     * 投资活动现金流入小计
     */
	@DataInput(columnName="投资活动现金流入小计")
	private BigDecimal k7;
    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金
     */
	@DataInput(columnName="购建固定资产、无形资产和其他长期资产支付的现金")
	private BigDecimal k8;
    /**
     * 投资支付的现金
     */
	@DataInput(columnName="投资支付的现金")
	private BigDecimal k9;
    /**
     * 取得子公司及其他营业单位支付的现金净额
     */
	@DataInput(columnName="取得子公司及其他营业单位支付的现金净额")
	private BigDecimal k10;
    /**
     * 支付其他与投资活动有关的现金
     */
	@DataInput(columnName="支付其他与投资活动有关的现金")
	private BigDecimal k11;
    /**
     * 投资活动现金流出小计
     */
	@DataInput(columnName="投资活动现金流出小计")
	private BigDecimal k12;
    /**
     * 投资活动产生的现金流量净额
     */
	@DataInput(columnName="投资活动产生的现金流量净额")
	private BigDecimal k13;
    /**
     * 筹资活动产生的现金流量：
     */
	@DataInput(columnName="筹资活动产生的现金流量")
	private BigDecimal j1;
    /**
     * 吸收投资收到的现金
     */
	@DataInput(columnName="吸收投资收到的现金")
	private BigDecimal j2;
    /**
     * 取得借款收到的现金
     */
	@DataInput(columnName="取得借款收到的现金")
	private BigDecimal j3;
    /**
     * 收到其他与筹资活动有关的现金
     */
	@DataInput(columnName="收到其他与筹资活动有关的现金")
	private BigDecimal j4;
    /**
     * 发行债券收到的现金
     */
	@DataInput(columnName="发行债券收到的现金")
	private BigDecimal j5;
    /**
     * 筹资活动现金流入小计
     */
	@DataInput(columnName="筹资活动现金流入小计")
	private BigDecimal j6;
    /**
     * 偿还债务支付的现金
     */
	@DataInput(columnName="偿还债务支付的现金")
	private BigDecimal j7;
    /**
     * 分配股利、利润或偿付利息支付的现金
     */
	@DataInput(columnName="分配股利、利润或偿付利息支付的现金")
	private BigDecimal j8;
    /**
     * 支付其他与筹资活动有关的现金
     */
	@DataInput(columnName="支付其他与筹资活动有关的现金")
	private BigDecimal j9;
    /**
     * 筹资活动现金流出小计
     */
	@DataInput(columnName="筹资活动现金流出小计")
	private BigDecimal j10;
    /**
     * 筹资活动产生的现金流量净额
     */
	@DataInput(columnName="筹资活动产生的现金流量净额")
	private BigDecimal j11;
    /**
     * 汇率变动对现金的影响
     */
	@DataInput(columnName="汇率变动对现金的影响")
	private BigDecimal a1;
    /**
     * 现金及现金等价物净增加额
     */
	@DataInput(columnName="现金及现金等价物净增加额")
	private BigDecimal a2;
    /**
     * 期初现金及现金等价物余额
     */
	@DataInput(columnName="期初现金及现金等价物余额")
	private BigDecimal a3;
    /**
     * 期末现金及现金等价物余额
     */
	@DataInput(columnName="期末现金及现金等价物余额")
	private BigDecimal a4;
    /**
     * 经办人ITCODE
     */
	private String itcode;
    /**
     * 经办人资源ID
     */
	private Long resourceId;
	private Date createTime;


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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
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

	public BigDecimal getF10() {
		return f10;
	}

	public void setF10(BigDecimal f10) {
		this.f10 = f10;
	}

	public BigDecimal getF11() {
		return f11;
	}

	public void setF11(BigDecimal f11) {
		this.f11 = f11;
	}

	public BigDecimal getK1() {
		return k1;
	}

	public void setK1(BigDecimal k1) {
		this.k1 = k1;
	}

	public BigDecimal getK2() {
		return k2;
	}

	public void setK2(BigDecimal k2) {
		this.k2 = k2;
	}

	public BigDecimal getK3() {
		return k3;
	}

	public void setK3(BigDecimal k3) {
		this.k3 = k3;
	}

	public BigDecimal getK4() {
		return k4;
	}

	public void setK4(BigDecimal k4) {
		this.k4 = k4;
	}

	public BigDecimal getK5() {
		return k5;
	}

	public void setK5(BigDecimal k5) {
		this.k5 = k5;
	}

	public BigDecimal getK6() {
		return k6;
	}

	public void setK6(BigDecimal k6) {
		this.k6 = k6;
	}

	public BigDecimal getK7() {
		return k7;
	}

	public void setK7(BigDecimal k7) {
		this.k7 = k7;
	}

	public BigDecimal getK8() {
		return k8;
	}

	public void setK8(BigDecimal k8) {
		this.k8 = k8;
	}

	public BigDecimal getK9() {
		return k9;
	}

	public void setK9(BigDecimal k9) {
		this.k9 = k9;
	}

	public BigDecimal getK10() {
		return k10;
	}

	public void setK10(BigDecimal k10) {
		this.k10 = k10;
	}

	public BigDecimal getK11() {
		return k11;
	}

	public void setK11(BigDecimal k11) {
		this.k11 = k11;
	}

	public BigDecimal getK12() {
		return k12;
	}

	public void setK12(BigDecimal k12) {
		this.k12 = k12;
	}

	public BigDecimal getK13() {
		return k13;
	}

	public void setK13(BigDecimal k13) {
		this.k13 = k13;
	}

	public BigDecimal getJ1() {
		return j1;
	}

	public void setJ1(BigDecimal j1) {
		this.j1 = j1;
	}

	public BigDecimal getJ2() {
		return j2;
	}

	public void setJ2(BigDecimal j2) {
		this.j2 = j2;
	}

	public BigDecimal getJ3() {
		return j3;
	}

	public void setJ3(BigDecimal j3) {
		this.j3 = j3;
	}

	public BigDecimal getJ4() {
		return j4;
	}

	public void setJ4(BigDecimal j4) {
		this.j4 = j4;
	}

	public BigDecimal getJ5() {
		return j5;
	}

	public void setJ5(BigDecimal j5) {
		this.j5 = j5;
	}

	public BigDecimal getJ6() {
		return j6;
	}

	public void setJ6(BigDecimal j6) {
		this.j6 = j6;
	}

	public BigDecimal getJ7() {
		return j7;
	}

	public void setJ7(BigDecimal j7) {
		this.j7 = j7;
	}

	public BigDecimal getJ8() {
		return j8;
	}

	public void setJ8(BigDecimal j8) {
		this.j8 = j8;
	}

	public BigDecimal getJ9() {
		return j9;
	}

	public void setJ9(BigDecimal j9) {
		this.j9 = j9;
	}

	public BigDecimal getJ10() {
		return j10;
	}

	public void setJ10(BigDecimal j10) {
		this.j10 = j10;
	}

	public BigDecimal getJ11() {
		return j11;
	}

	public void setJ11(BigDecimal j11) {
		this.j11 = j11;
	}

	public BigDecimal getA1() {
		return a1;
	}

	public void setA1(BigDecimal a1) {
		this.a1 = a1;
	}

	public BigDecimal getA2() {
		return a2;
	}

	public void setA2(BigDecimal a2) {
		this.a2 = a2;
	}

	public BigDecimal getA3() {
		return a3;
	}

	public void setA3(BigDecimal a3) {
		this.a3 = a3;
	}

	public BigDecimal getA4() {
		return a4;
	}

	public void setA4(BigDecimal a4) {
		this.a4 = a4;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectFinancialCash{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", year=" + year +
			", quarter=" + quarter +
			", f1=" + f1 +
			", f2=" + f2 +
			", f3=" + f3 +
			", f4=" + f4 +
			", f5=" + f5 +
			", f6=" + f6 +
			", f7=" + f7 +
			", f8=" + f8 +
			", f9=" + f9 +
			", f10=" + f10 +
			", f11=" + f11 +
			", k1=" + k1 +
			", k2=" + k2 +
			", k3=" + k3 +
			", k4=" + k4 +
			", k5=" + k5 +
			", k6=" + k6 +
			", k7=" + k7 +
			", k8=" + k8 +
			", k9=" + k9 +
			", k10=" + k10 +
			", k11=" + k11 +
			", k12=" + k12 +
			", k13=" + k13 +
			", j1=" + j1 +
			", j2=" + j2 +
			", j3=" + j3 +
			", j4=" + j4 +
			", j5=" + j5 +
			", j6=" + j6 +
			", j7=" + j7 +
			", j8=" + j8 +
			", j9=" + j9 +
			", j10=" + j10 +
			", j11=" + j11 +
			", a1=" + a1 +
			", a2=" + a2 +
			", a3=" + a3 +
			", a4=" + a4 +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
