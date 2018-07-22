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
 * 项目审批
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_approval")
public class ProjectApproval implements Serializable {

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
	 * 是否通过:0-拒绝1-通过2-有条件通过
	 */
	private Integer result;
	/**
	 * 审批理由
	 */
	private String memo;
	/**
	 * 建议
	 */
	private String propose;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	private Date createTime;

	/**
	 * 评审来源：1-专家，0-其他
	 */
	private Integer type;

	/**
	 * 0 - 评审，1 - 评议
	 */
	@TableField(exist = false)
	private Integer approvalType = 0;
	/**
	 * 流程类型
	 */
	@TableField(exist = false)
	private Integer flowType;
	/**
	 * 有条件通过时的条件
	 */
	@TableField(exist = false)
	private List<ProjectOpinionCondition> projectOpinionConditions;
	
	@TableField(exist = false)
	private String stageTitle;

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * 结论
	 */
	@TableField(exist = false)
	private String conclusion;

	/**
	 * 推荐度
	 */
	@TableField(exist = false)
	private Double recommendationLevel;
	
	@TableField(exist = false)
	private String condition;
	@TableField(exist = false)
	private String risk;
	@TableField(exist = false)
	private String resName;
	@TableField(exist = false)
	private String conditionType;
	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * @return the flowType
	 */
	public Integer getFlowType() {
		return flowType;
	}

	/**
	 * @param flowType the flowType to set
	 */
	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the risk
	 */
	public String getRisk() {
		return risk;
	}

	/**
	 * @param risk the risk to set
	 */
	public void setRisk(String risk) {
		this.risk = risk;
	}

	/**
	 * 风险等级:高中低
	 */
	@TableField(exist = false)
	private Integer riskLevel;
	/**
	 * 备注
	 */
	@TableField(exist = false)
	private String riskMemo;
	/**
	 * 风险项
	 */
	@TableField(exist = false)
	private List<ProjectRiskItem> riskItems;

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskMemo() {
		return riskMemo;
	}

	public void setRiskMemo(String riskMemo) {
		this.riskMemo = riskMemo;
	}

	public List<ProjectRiskItem> getRiskItems() {
		return riskItems;
	}

	public void setRiskItems(List<ProjectRiskItem> riskItems) {
		this.riskItems = riskItems;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
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

	public String getPropose() {
		return propose;
	}

	public void setPropose(String propose) {
		this.propose = propose;
	}

	public List<ProjectOpinionCondition> getProjectOpinionConditions() {
		return projectOpinionConditions;
	}

	public void setProjectOpinionConditions(List<ProjectOpinionCondition> projectOpinionConditions) {
		this.projectOpinionConditions = projectOpinionConditions;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}

	public Double getRecommendationLevel() {
		return recommendationLevel;
	}

	public void setRecommendationLevel(Double recommendationLevel) {
		this.recommendationLevel = recommendationLevel;
	}

	public ProjectApproval(ProjectOpinion opinion) {
		this.setEntityId(opinion.getEntityId());
		this.setApprovalType(1);
		this.setProjectId(opinion.getProjectId());
		this.setStage(opinion.getStage());
		this.setMemo(opinion.getContent());
		this.setItcode(opinion.getItcode());
		this.setResourceId(opinion.getResourceId());
		this.setCreateTime(opinion.getCreateTime());
		this.setPropose(opinion.getPropose());
		this.setConclusion(opinion.getConclusion());
		this.setRecommendationLevel(opinion.getRecommendationLevel());
		this.setStageTitle(opinion.getStageTitle());
	}

	public ProjectApproval(Long entityId, @NotNull(message = "项目id不能为空!") Long projectId,
			@NotNull(message = "项目阶段不能为空!") Long stage, Integer result, String memo, String propose, String itcode,
			Long resourceId, Date createTime, Integer type, List<ProjectOpinionCondition> projectOpinionConditions,
			String stageTitle, String pROCESS_INSTANCE_ID_) {
		super();
		this.entityId = entityId;
		this.projectId = projectId;
		this.stage = stage;
		this.result = result;
		this.memo = memo;
		this.propose = propose;
		this.itcode = itcode;
		this.resourceId = resourceId;
		this.createTime = createTime;
		this.type = type;
		this.projectOpinionConditions = projectOpinionConditions;
		this.stageTitle = stageTitle;
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	public ProjectApproval() {
		super();
	}

	@Override
	public String toString() {
		return "ProjectApproval{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", memo=" + memo + ", result=" + result + ", itcode=" + itcode + ", resourceId=" + resourceId
				+ ", createTime=" + createTime + "}";
	}
}
