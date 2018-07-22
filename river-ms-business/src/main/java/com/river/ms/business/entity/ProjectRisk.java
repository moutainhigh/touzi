package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 风险评议
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@TableName("project_risk")
public class ProjectRisk implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	@NotNull(message = "项目id不能为空!")
	private Long projectId;
	/**
	 * 项目阶段
	 */
	@NotNull(message = "项目阶段不能为空!")
	private Long stage;
	/**
	 * 风险等级:高中低
	 */
	private Integer level;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	/**
	 * 评议时间
	 */
	private Date createTime;

	@TableField(exist = false)
	private List<ProjectRiskItem> riskItems;

	@TableField(exist = false)
	private String stageTitle;

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

	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public List<ProjectRiskItem> getRiskItems() {
		return riskItems;
	}

	public void setRiskItems(List<ProjectRiskItem> riskItems) {
		this.riskItems = riskItems;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}
	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;
	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	@Override
	public String toString() {
		return "ProjectRisk{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage + ", level="
				+ level + ", memo=" + memo + ", itcode=" + itcode + ", resourceId=" + resourceId + ", createTime="
				+ createTime + "}";
	}
}
