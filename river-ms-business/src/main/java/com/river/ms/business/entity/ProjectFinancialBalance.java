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
 * 项目财务数据负债表
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_financial_balance")
public class ProjectFinancialBalance implements Serializable {

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
     * 流动资产：
     */
	@DataInput(columnName="流动资产")
	private BigDecimal f1;
    /**
     * 货币资金
     */
	@DataInput(columnName="货币资金")
	private BigDecimal f2;
    /**
     * 应收票据
     */
	@DataInput(columnName="应收票据")
	private BigDecimal f3;
    /**
     * 应收账款
     */
	@DataInput(columnName="应收账款")
	private BigDecimal f4;
    /**
     * 预付款项
     */
	@DataInput(columnName="预付款项")
	private BigDecimal f5;
    /**
     * 应收利息
     */
	@DataInput(columnName="应收利息")
	private BigDecimal f6;
    /**
     * 其他应收款
     */
	@DataInput(columnName="其他应收款")
	private BigDecimal f7;
    /**
     * 存货
     */
	@DataInput(columnName="存货")
	private BigDecimal f8;
    /**
     * 其他流动资产
     */
	@DataInput(columnName="其他流动资产")
	private BigDecimal f9;
    /**
     * 流动资产合计
     */
	@DataInput(columnName="流动资产合计")
	private BigDecimal f10;
    /**
     * 非流动资产：
     */
	@DataInput(columnName="非流动资产")
	private BigDecimal k1;
    /**
     * 可供出售金融资产
     */
	@DataInput(columnName="可供出售金融资产")
	private BigDecimal k2;
    /**
     * 投资性房地产
     */
	@DataInput(columnName="投资性房地产")
	private BigDecimal k3;
    /**
     * 固定资产
     */
	@DataInput(columnName="固定资产")
	private BigDecimal k4;
    /**
     * 在建工程
     */
	@DataInput(columnName="在建工程")
	private BigDecimal k5;
    /**
     * 工程物资
     */
	@DataInput(columnName="工程物资")
	private BigDecimal k6;
    /**
     * 固定资产清理
     */
	@DataInput(columnName="固定资产清理")
	private BigDecimal k7;
    /**
     * 无形资产
     */
	@DataInput(columnName="无形资产")
	private BigDecimal k8;
    /**
     * 开发支出
     */
	@DataInput(columnName="开发支出")
	private BigDecimal k9;
    /**
     * 长期待摊费用
     */
	@DataInput(columnName="长期待摊费用")
	private BigDecimal k10;
    /**
     * 递延所得税资产
     */
	@DataInput(columnName="递延所得税资产")
	private BigDecimal k11;
    /**
     * 其他非流动资产
     */
	@DataInput(columnName="其他非流动资产")
	private BigDecimal k12;
    /**
     * 非流动资产合计
     */
	@DataInput(columnName="非流动资产合计")
	private BigDecimal k13;
    /**
     * 资产总计
     */
	@DataInput(columnName="资产总计")
	private BigDecimal k14;
    /**
     * 流动负债：
     */
	@DataInput(columnName="流动负债")
	private BigDecimal j1;
    /**
     * 短期借款
     */
	@DataInput(columnName="短期借款")
	private BigDecimal j2;
    /**
     * 应付票据
     */
	@DataInput(columnName="应付票据")
	private BigDecimal j3;
    /**
     * 应付账款
     */
	@DataInput(columnName="应付账款")
	private BigDecimal j4;
    /**
     * 预收款项
     */
	@DataInput(columnName="预收款项")
	private BigDecimal j5;
    /**
     * 应付职工薪酬
     */
	@DataInput(columnName="应付职工薪酬")
	private BigDecimal j6;
    /**
     * 应交税费
     */
	@DataInput(columnName="应交税费")
	private BigDecimal j7;
    /**
     * 应付利息
     */
	@DataInput(columnName="应付利息")
	private BigDecimal j8;
    /**
     * 应付股利
     */
	@DataInput(columnName="应付股利")
	private BigDecimal j9;
    /**
     * 其他应付款
     */
	@DataInput(columnName="其他应付款")
	private BigDecimal j10;
    /**
     * 一年内到期的非流动负债
     */
	@DataInput(columnName="一年内到期的非流动负债")
	private BigDecimal j11;
    /**
     * 流动负债合计
     */
	@DataInput(columnName="流动负债合计")
	private BigDecimal j12;
    /**
     * 非流动负债：
     */
	@DataInput(columnName="非流动负债")
	private BigDecimal a1;
    /**
     * 长期借款
     */
	@DataInput(columnName="长期借款")
	private BigDecimal a2;
    /**
     * 长期应付款
     */
	@DataInput(columnName="长期应付款")
	private BigDecimal a3;
    /**
     * 专项应付款
     */
	@DataInput(columnName="专项应付款")
	private BigDecimal a4;
    /**
     * 预计负债
     */
	@DataInput(columnName="预计负债")
	private BigDecimal a5;
    /**
     * 递延所得税负债
     */
	@DataInput(columnName="递延所得税负债")
	private BigDecimal a6;
    /**
     * 递延收益-非流动负债
     */
	@DataInput(columnName="递延收益-非流动负债")
	private BigDecimal a7;
    /**
     * 其他非流动负债
     */
	@DataInput(columnName="其他非流动负债")
	private BigDecimal a8;
    /**
     * 非流动负债合计
     */
	@DataInput(columnName="非流动负债合计")
	private BigDecimal a9;
    /**
     * 负债合计
     */
	@DataInput(columnName="负债合计")
	private BigDecimal a10;
    /**
     * 所有者权益(或股东权益)：
     */
	@DataInput(columnName="所有者权益(或股东权益)")
	private BigDecimal b1;
    /**
     * 实收资本(或股本)
     */
	@DataInput(columnName="实收资本(或股本)")
	private BigDecimal b2;
    /**
     * 资本公积金
     */
	@DataInput(columnName="资本公积金")
	private BigDecimal b3;
    /**
     * 其它综合收益
     */
	@DataInput(columnName="其它综合收益")
	private BigDecimal b4;
    /**
     * 专项储备
     */
	@DataInput(columnName="专项储备")
	private BigDecimal b5;
    /**
     * 盈余公积金
     */
	@DataInput(columnName="盈余公积金")
	private BigDecimal b6;
    /**
     * 未分配利润
     */
	@DataInput(columnName="未分配利润")
	private BigDecimal b7;
    /**
     * 一般风险准备
     */
	@DataInput(columnName="一般风险准备")
	private BigDecimal b8;
    /**
     * 归属于母公司所有者权益合计
     */
	@DataInput(columnName="归属于母公司所有者权益合计")
	private BigDecimal b9;
    /**
     * 少数股东权益
     */
	@DataInput(columnName="少数股东权益")
	private BigDecimal b10;
    /**
     * 所有者权益合计
     */
	@DataInput(columnName="所有者权益合计")
	private BigDecimal b11;
    /**
     * 负债和所有者权益总计
     */
	@DataInput(columnName="负债和所有者权益总计")
	private BigDecimal b12;
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

	public BigDecimal getK14() {
		return k14;
	}

	public void setK14(BigDecimal k14) {
		this.k14 = k14;
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

	public BigDecimal getJ12() {
		return j12;
	}

	public void setJ12(BigDecimal j12) {
		this.j12 = j12;
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

	public BigDecimal getA5() {
		return a5;
	}

	public void setA5(BigDecimal a5) {
		this.a5 = a5;
	}

	public BigDecimal getA6() {
		return a6;
	}

	public void setA6(BigDecimal a6) {
		this.a6 = a6;
	}

	public BigDecimal getA7() {
		return a7;
	}

	public void setA7(BigDecimal a7) {
		this.a7 = a7;
	}

	public BigDecimal getA8() {
		return a8;
	}

	public void setA8(BigDecimal a8) {
		this.a8 = a8;
	}

	public BigDecimal getA9() {
		return a9;
	}

	public void setA9(BigDecimal a9) {
		this.a9 = a9;
	}

	public BigDecimal getA10() {
		return a10;
	}

	public void setA10(BigDecimal a10) {
		this.a10 = a10;
	}

	public BigDecimal getB1() {
		return b1;
	}

	public void setB1(BigDecimal b1) {
		this.b1 = b1;
	}

	public BigDecimal getB2() {
		return b2;
	}

	public void setB2(BigDecimal b2) {
		this.b2 = b2;
	}

	public BigDecimal getB3() {
		return b3;
	}

	public void setB3(BigDecimal b3) {
		this.b3 = b3;
	}

	public BigDecimal getB4() {
		return b4;
	}

	public void setB4(BigDecimal b4) {
		this.b4 = b4;
	}

	public BigDecimal getB5() {
		return b5;
	}

	public void setB5(BigDecimal b5) {
		this.b5 = b5;
	}

	public BigDecimal getB6() {
		return b6;
	}

	public void setB6(BigDecimal b6) {
		this.b6 = b6;
	}

	public BigDecimal getB7() {
		return b7;
	}

	public void setB7(BigDecimal b7) {
		this.b7 = b7;
	}

	public BigDecimal getB8() {
		return b8;
	}

	public void setB8(BigDecimal b8) {
		this.b8 = b8;
	}

	public BigDecimal getB9() {
		return b9;
	}

	public void setB9(BigDecimal b9) {
		this.b9 = b9;
	}

	public BigDecimal getB10() {
		return b10;
	}

	public void setB10(BigDecimal b10) {
		this.b10 = b10;
	}

	public BigDecimal getB11() {
		return b11;
	}

	public void setB11(BigDecimal b11) {
		this.b11 = b11;
	}

	public BigDecimal getB12() {
		return b12;
	}

	public void setB12(BigDecimal b12) {
		this.b12 = b12;
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
		return "ProjectFinancialBalance{" +
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
			", k14=" + k14 +
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
			", j12=" + j12 +
			", a1=" + a1 +
			", a2=" + a2 +
			", a3=" + a3 +
			", a4=" + a4 +
			", a5=" + a5 +
			", a6=" + a6 +
			", a7=" + a7 +
			", a8=" + a8 +
			", a9=" + a9 +
			", a10=" + a10 +
			", b1=" + b1 +
			", b2=" + b2 +
			", b3=" + b3 +
			", b4=" + b4 +
			", b5=" + b5 +
			", b6=" + b6 +
			", b7=" + b7 +
			", b8=" + b8 +
			", b9=" + b9 +
			", b10=" + b10 +
			", b11=" + b11 +
			", b12=" + b12 +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
