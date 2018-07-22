package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目成本更新记录
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_milestone_cost")
public class ProjectMilestoneCost implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目分期
	 */
	private Long milestoneId;
	/**
	 * 分期预计投资额（万元）
	 */
	@TableField("expected_investment")
	private BigDecimal expectedInvestment;
	/**
	 * 分期实际总投资额（万元）
	 */
	@TableField("actual_investment")
	private BigDecimal actualInvestment;
	/**
	 * 预计投资额偏差原因
	 */
	private String deviation;
	/**
	 * 偏差百分比
	 */
	@TableField("deviation_degrees")
	private Integer deviationDegrees;
	/**
	 * 项目目前状态
	 */
	private Integer state;
	/**
	 * 分期进度填写时间
	 */
	private Date createTime;
	private BigDecimal f1;
	/**
	 * 付款比例（%）
	 */
	private BigDecimal f2;
	/**
	 * 形象进度投资额（万元）
	 */
	private BigDecimal f3;
	/**
	 * 形象进度投资额/分期预计投资额（%）
	 */
	private BigDecimal f4;
	/**
	 * 预计投资额偏差
	 */
	private BigDecimal f5;
	/**
	 * 预计投资额偏差百分比（%）
	 */
	private BigDecimal f6;
	/**
	 * 已累计支付投资额（万元）
	 */
	private BigDecimal f7;

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

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public BigDecimal getExpectedInvestment() {
		return expectedInvestment;
	}

	public void setExpectedInvestment(BigDecimal expectedInvestment) {
		this.expectedInvestment = expectedInvestment;
	}

	public BigDecimal getActualInvestment() {
		return actualInvestment;
	}

	public void setActualInvestment(BigDecimal actualInvestment) {
		this.actualInvestment = actualInvestment;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public Integer getDeviationDegrees() {
		return deviationDegrees;
	}

	public void setDeviationDegrees(Integer deviationDegrees) {
		this.deviationDegrees = deviationDegrees;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Override
	public String toString() {
		return "ProjectMilestoneCost{" + ", entityId=" + entityId + ", projectId=" + projectId + ", milestoneId="
				+ milestoneId + ", expectedInvestment=" + expectedInvestment + ", actualInvestment=" + actualInvestment
				+ ", deviation=" + deviation + ", deviationDegrees=" + deviationDegrees + ", state=" + state
				+ ", createTime=" + createTime + ", f1=" + f1 + ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", f5="
				+ f5 + ", f6=" + f6 + ", f7=" + f7 + "}";
	}
}
