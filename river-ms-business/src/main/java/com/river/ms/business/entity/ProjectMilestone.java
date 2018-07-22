package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目节点
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_milestone")
public class ProjectMilestone implements Serializable {

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
	
	private String milestone;
	/**
	 * 顺序号
	 */
	private Integer sort;
	/**
	 * 项目分期计划实施内容
	 */
	private String memo;
	/**
	 * 分期进度填写时间
	 */
	private Date createTime;
	/**
	 * 分期计划竣工/投产日期
	 */
	@TableField("expected_finish_time")
	private Date expectedFinishTime;
	/**
	 * 分期实际竣工日期
	 */
	@TableField("actual_finish_time")
	private Date actualFinishTime;
	/**
	 * 总体进度偏差百分比
	 */
	@TableField("completion_degrees")
	private Integer completionDegrees;
	/**
	 * 分期总体进度偏差原因
	 */
	private String deviation;
	/**
	 * 上级节点
	 */
	private Long parentId;
	/**
	 * 分期总体进度偏差百分比（%）
	 */
	@TableField("deviation_degrees")
	private Integer deviationDegrees;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 项目目前状态
	 */
	private Integer state;
	/**
	 * 分期批复投资额（万元）
	 */
	@TableField("expected_investment")
	private BigDecimal expectedInvestment;
	/**
	 * 分期实际总投资额
	 */
	@TableField("actual_investment")
	private BigDecimal actualInvestment;
	/**
	 * 项目分期计划工期
	 */
	@TableField("expected_duration")
	private Integer expectedDuration;
	/**
	 * 项目实际签约日期
	 */
	@TableField("actual_signingDate")
	private Date actualSigningDate;
	/**
	 * 项目计划开工日期
	 */
	@TableField("expected_startDate")
	private Date expectedStartDate;
	/**
	 * 项目实际开工日期
	 */
	@TableField("actual_startDate")
	private Date actualStartDate;
	/**
	 * 分期实际投产日期
	 */
	@TableField("actual_productionDate")
	private Date actualProductionDate;
	/**
	 * 分期总体投资额偏差百分比（%）
	 */
	private BigDecimal f1;
	/**
	 * 分期总体投资额是否有偏差
	 */
	private Integer b1;
	/**
	 * 分期总体投资额偏差原因
	 */
	private String s1;
	/**
	 * 分期总体进度是否有偏差 0-没有偏差，1-有偏差
	 */
	private Integer b2;

	@TableField(exist = false)
	private List<ProjectMilestoneCost> projectMilestoneCost;

	@TableField(exist = false)
	private List<ProjectMilestoneHistory> projectMilestoneHistory;

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

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpectedFinishTime() {
		return expectedFinishTime;
	}

	public void setExpectedFinishTime(Date expectedFinishTime) {
		this.expectedFinishTime = expectedFinishTime;
	}

	public Date getActualFinishTime() {
		return actualFinishTime;
	}

	public void setActualFinishTime(Date actualFinishTime) {
		this.actualFinishTime = actualFinishTime;
	}

	public Integer getCompletionDegrees() {
		return completionDegrees;
	}

	public void setCompletionDegrees(Integer completionDegrees) {
		this.completionDegrees = completionDegrees;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getDeviationDegrees() {
		return deviationDegrees;
	}

	public void setDeviationDegrees(Integer deviationDegrees) {
		this.deviationDegrees = deviationDegrees;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(Integer expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public Date getActualSigningDate() {
		return actualSigningDate;
	}

	public void setActualSigningDate(Date actualSigningDate) {
		this.actualSigningDate = actualSigningDate;
	}

	public Date getExpectedStartDate() {
		return expectedStartDate;
	}

	public void setExpectedStartDate(Date expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualProductionDate() {
		return actualProductionDate;
	}

	public void setActualProductionDate(Date actualProductionDate) {
		this.actualProductionDate = actualProductionDate;
	}

	public BigDecimal getF1() {
		return f1;
	}

	public void setF1(BigDecimal f1) {
		this.f1 = f1;
	}

	public Integer getB1() {
		return b1;
	}

	public void setB1(Integer b1) {
		this.b1 = b1;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public Integer getB2() {
		return b2;
	}

	public void setB2(Integer b2) {
		this.b2 = b2;
	}

	public List<ProjectMilestoneCost> getProjectMilestoneCost() {
		return projectMilestoneCost;
	}

	public void setProjectMilestoneCost(List<ProjectMilestoneCost> projectMilestoneCost) {
		this.projectMilestoneCost = projectMilestoneCost;
	}

	public List<ProjectMilestoneHistory> getProjectMilestoneHistory() {
		return projectMilestoneHistory;
	}

	public void setProjectMilestoneHistory(List<ProjectMilestoneHistory> projectMilestoneHistory) {
		this.projectMilestoneHistory = projectMilestoneHistory;
	}

	@Override
	public String toString() {
		return "ProjectMilestone{" + ", entityId=" + entityId + ", projectId=" + projectId + ", milestone=" + milestone
				+ ", sort=" + sort + ", memo=" + memo + ", createTime=" + createTime + ", expectedFinishTime="
				+ expectedFinishTime + ", actualFinishTime=" + actualFinishTime + ", completionDegrees="
				+ completionDegrees + ", deviation=" + deviation + ", parentId=" + parentId + ", deviationDegrees="
				+ deviationDegrees + ", updateTime=" + updateTime + ", state=" + state + ", expectedInvestment="
				+ expectedInvestment + ", actualInvestment=" + actualInvestment + ", expectedDuration="
				+ expectedDuration + ", actualSigningDate=" + actualSigningDate + ", expectedStartDate="
				+ expectedStartDate + ", actualStartDate=" + actualStartDate + ", actualProductionDate="
				+ actualProductionDate + ", f1=" + f1 + ", b1=" + b1 + ", s1=" + s1 + ", b2=" + b2 + "}";
	}
}
