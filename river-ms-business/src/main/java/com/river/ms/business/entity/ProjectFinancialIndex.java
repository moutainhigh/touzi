package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目财务指标
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_financial_index")
public class ProjectFinancialIndex implements Serializable {

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
	private String quarter;
    /**
     * 营业收入增长率
     */
	private BigDecimal a1;
    /**
     * 净利润增长率
     */
	private BigDecimal a2;
    /**
     * 总资产增长率
     */
	private BigDecimal a3;
    /**
     * 净资产增长率
     */
	private BigDecimal a4;
    /**
     * 毛利率
     */
	private BigDecimal b1;
    /**
     * 净利率
     */
	private BigDecimal b2;
    /**
     * EBITDA
     */
	private BigDecimal b3;
    /**
     * EBIT
     */
	private BigDecimal b4;
    /**
     * ROA
     */
	private BigDecimal b5;
    /**
     * ROE
     */
	private BigDecimal b6;
    /**
     * 流动比率
     */
	private BigDecimal c1;
    /**
     * 资产负债率
     */
	private BigDecimal c2;
    /**
     * 利息保障倍数
     */
	private BigDecimal c3;
    /**
     * 应收账款周转率
     */
	private BigDecimal d1;
    /**
     * 应收账款周转天数
     */
	private BigDecimal d2;
    /**
     * 总资产周转率
     */
	private BigDecimal d3;
    /**
     * 净资产周转率
     */
	private BigDecimal d4;
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

	public BigDecimal getC1() {
		return c1;
	}

	public void setC1(BigDecimal c1) {
		this.c1 = c1;
	}

	public BigDecimal getC2() {
		return c2;
	}

	public void setC2(BigDecimal c2) {
		this.c2 = c2;
	}

	public BigDecimal getC3() {
		return c3;
	}

	public void setC3(BigDecimal c3) {
		this.c3 = c3;
	}

	public BigDecimal getD1() {
		return d1;
	}

	public void setD1(BigDecimal d1) {
		this.d1 = d1;
	}

	public BigDecimal getD2() {
		return d2;
	}

	public void setD2(BigDecimal d2) {
		this.d2 = d2;
	}

	public BigDecimal getD3() {
		return d3;
	}

	public void setD3(BigDecimal d3) {
		this.d3 = d3;
	}

	public BigDecimal getD4() {
		return d4;
	}

	public void setD4(BigDecimal d4) {
		this.d4 = d4;
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
		return "ProjectFinancialIndex{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", year=" + year +
			", quarter=" + quarter +
			", a1=" + a1 +
			", a2=" + a2 +
			", a3=" + a3 +
			", a4=" + a4 +
			", b1=" + b1 +
			", b2=" + b2 +
			", b3=" + b3 +
			", b4=" + b4 +
			", b5=" + b5 +
			", b6=" + b6 +
			", c1=" + c1 +
			", c2=" + c2 +
			", c3=" + c3 +
			", d1=" + d1 +
			", d2=" + d2 +
			", d3=" + d3 +
			", d4=" + d4 +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			"}";
	}
}
