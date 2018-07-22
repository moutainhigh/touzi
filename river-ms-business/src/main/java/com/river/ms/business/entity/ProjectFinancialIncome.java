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
 * 项目财务数据利润表
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_financial_income")
public class ProjectFinancialIncome implements Serializable {

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
     * 营业收入
     */
	@DataInput(columnName="营业收入")
	private BigDecimal f1;
    /**
     * 营业成本
     */
	@DataInput(columnName="营业成本")
	private BigDecimal f2;
    /**
     * 利润总额
     */
	@DataInput(columnName="利润总额")
	private BigDecimal f3;
    /**
     * 净利润
     */
	@DataInput(columnName="净利润")
	private BigDecimal f4;
    /**
     * 税金及附加
     */
	@DataInput(columnName="税金及附加")
	private BigDecimal f5;
    /**
     * 销售费用
     */
	@DataInput(columnName="销售费用")
	private BigDecimal f6;
    /**
     * 管理费用
     */
	@DataInput(columnName="管理费用")
	private BigDecimal f7;
    /**
     * 财务费用
     */
	@DataInput(columnName="财务费用")
	private BigDecimal f8;
    /**
     * 资产减值损失
     */
	@DataInput(columnName="资产减值损失")
	private BigDecimal f9;
    /**
     * 其他经营收益
     */
	@DataInput(columnName="其他经营收益")
	private BigDecimal f10;
    /**
     * 营业利润
     */
	@DataInput(columnName="营业利润")
	private BigDecimal f11;
    /**
     * 加：营业外收入
     */
	@DataInput(columnName="营业外收入")
	private BigDecimal f12;
    /**
     * 减：营业外支出
     */
	@DataInput(columnName="营业外支出")
	private BigDecimal f13;
    /**
     * 其中：非流动资产处置净损失
     */
	@DataInput(columnName="非流动资产处置净损失")
	private BigDecimal f14;
    /**
     * 减：所得税
     */
	@DataInput(columnName="所得税")
	private BigDecimal f15;
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

	public BigDecimal getF12() {
		return f12;
	}

	public void setF12(BigDecimal f12) {
		this.f12 = f12;
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
		return "ProjectFinancialIncome{" +
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
			", f12=" + f12 +
			", f13=" + f13 +
			", f14=" + f14 +
			", f15=" + f15 +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
