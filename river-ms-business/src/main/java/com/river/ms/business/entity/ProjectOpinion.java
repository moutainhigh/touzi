package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 专家意见
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_opinion")
public class ProjectOpinion implements Serializable {

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
	 * 意见
	 */
	private String content;
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
	 * 建议
	 */
	private String propose;
	/**
	 * 结论
	 */
	private String conclusion;

	/**
	 * 推荐度
	 */
	private Double recommendationLevel;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public Double getRecommendationLevel() {
		return recommendationLevel;
	}

	public void setRecommendationLevel(Double recommendationLevel) {
		this.recommendationLevel = recommendationLevel;
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
		return "ProjectOpinion{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", content=" + content + ", itcode=" + itcode + ", resourceId=" + resourceId + ", createTime="
				+ createTime + ", propose=" + propose + ", conclusion=" + conclusion + "}";
	}
}
