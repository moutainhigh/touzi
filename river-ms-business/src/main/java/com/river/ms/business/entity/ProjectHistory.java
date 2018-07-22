package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目预测数据/历史数据
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_history")
public class ProjectHistory implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 年份
     */
	private Integer year;
    /**
     * 收入
     */
	private BigDecimal f1;
    /**
     * 毛利
     */
	private BigDecimal f2;
    /**
     * EBITDA税息折旧及摊销前利润
     */
	private BigDecimal f3;
    /**
     * 净利润
     */
	private BigDecimal f4;
    /**
     * FCF自由现金流
     */
	private BigDecimal f5;
    /**
     * ROE净资产收益率
     */
	private BigDecimal f6;
    /**
     * RORAC风险资本回报率
     */
	private BigDecimal f7;
    /**
     * 0-历史数据1-预测数据
     */
	private Integer entityType;
	private Date createTime;
	private Date updateTime;
    /**
     * 净利润率
     */
	private BigDecimal f8;
    /**
     * 利润总额
     */
	private BigDecimal f9;
    /**
     * 总成本（成本+费用）
     */
	private BigDecimal f10;

	/**
	 * 资本金内部收益率
	 * @return
	 */
	private BigDecimal f11;
	
	/**
	 * 静态投资回收期（税后）
	 * @return
	 */
	private BigDecimal f12;
	
	/**
	 *动态投资回收期（税后）
	 * @return
	 */
	private BigDecimal f13;
	
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

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
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

	@Override
	public String toString() {
		return "ProjectHistory [entityId=" + entityId + ", projectId=" + projectId + ", year=" + year + ", f1=" + f1
				+ ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6 + ", f7=" + f7
				+ ", entityType=" + entityType + ", createTime=" + createTime + ", updateTime=" + updateTime + ", f8="
				+ f8 + ", f9=" + f9 + ", f10=" + f10 + ", f11=" + f11 + ", f12=" + f12 + ", f13=" + f13 + "]";
	}
}
