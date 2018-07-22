package com.river.ms.business.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@TableName("view_opinion_res")
public class ViewOpinionRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目阶段
	 */
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
	/**
	 * 姓名
	 */
	private String entityTitle;

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

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	@Override
	public String toString() {
		return "ViewOpinionRes{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", content=" + content + ", itcode=" + itcode + ", resourceId=" + resourceId + ", createTime="
				+ createTime + ", propose=" + propose + ", conclusion=" + conclusion + ", recommendationLevel="
				+ recommendationLevel + ", entityTitle=" + entityTitle + "}";
	}
}
