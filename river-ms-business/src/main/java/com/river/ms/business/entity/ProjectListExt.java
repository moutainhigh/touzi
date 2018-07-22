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
 * 项目信息扩展表（数据）
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_list_ext")
public class ProjectListExt implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 项目估算投资总额
     */
	@TableField("estimated_total_investment")
	private BigDecimal estimatedTotalInvestment;
    /**
     * 投资额偏差
     */
	@TableField("deviation_investment")
	private BigDecimal deviationInvestment;
    /**
     * 投资额偏差百分比
     */
	@TableField("deviation_degrees_investment")
	private BigDecimal deviationDegreesInvestment;
    /**
     * 实际总投资额
     */
	@TableField("actual_investment")
	private BigDecimal actualInvestment;
    /**
     * 已投资总额
     */
	@TableField("total_investment")
	private BigDecimal totalInvestment;
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

	public BigDecimal getEstimatedTotalInvestment() {
		return estimatedTotalInvestment;
	}

	public void setEstimatedTotalInvestment(BigDecimal estimatedTotalInvestment) {
		this.estimatedTotalInvestment = estimatedTotalInvestment;
	}

	public BigDecimal getDeviationInvestment() {
		return deviationInvestment;
	}

	public void setDeviationInvestment(BigDecimal deviationInvestment) {
		this.deviationInvestment = deviationInvestment;
	}

	public BigDecimal getDeviationDegreesInvestment() {
		return deviationDegreesInvestment;
	}

	public void setDeviationDegreesInvestment(BigDecimal deviationDegreesInvestment) {
		this.deviationDegreesInvestment = deviationDegreesInvestment;
	}

	public BigDecimal getActualInvestment() {
		return actualInvestment;
	}

	public void setActualInvestment(BigDecimal actualInvestment) {
		this.actualInvestment = actualInvestment;
	}

	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectListExt{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", estimatedTotalInvestment=" + estimatedTotalInvestment +
			", deviationInvestment=" + deviationInvestment +
			", deviationDegreesInvestment=" + deviationDegreesInvestment +
			", actualInvestment=" + actualInvestment +
			", totalInvestment=" + totalInvestment +
			", createTime=" + createTime +
			"}";
	}
}
