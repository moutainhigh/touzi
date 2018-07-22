package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 风险项
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_riskItem")
public class ProjectRiskItem implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 对应风险评议ID
     */
	private Long riskId;
    /**
     * 风险描述
     */
	private String desc;
    /**
     * 风险策略
     */
	private String strategy;


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

	public Long getRiskId() {
		return riskId;
	}

	public void setRiskId(Long riskId) {
		this.riskId = riskId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	@Override
	public String toString() {
		return "ProjectRiskItem{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", riskId=" + riskId +
			", desc=" + desc +
			", strategy=" + strategy +
			"}";
	}
}
