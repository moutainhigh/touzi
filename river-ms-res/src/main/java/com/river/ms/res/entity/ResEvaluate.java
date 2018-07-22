package com.river.ms.res.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 资源评价
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("res_evaluate")
public class ResEvaluate implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * ITCODE
	 */
	private String itcode;
	/**
	 * 资源ID
	 */
	private Long resourceId;

	@TableField(exist = false)
	private String resName;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 项目ID
	 */
	private Long projectId;
	private Date createTime;

	/**
	 * 评价人id
	 */
	private Long evaluateId;

	/**
	 * 评价人Itcode
	 */
	private String evaluateItcode;

	@TableField(exist = false)
	private String evaluateResName;

	@TableField(exist = false)
	private String projectTitle;

	@TableField(exist = false)
	private String projectCode;

	@TableField(exist = false)
	private String tags;

	@TableField(exist = false)
	private Long plProjectid;

	@TableField(exist = false)
	private Long projectStageCode;

	public Long getPlProjectid() {
		return plProjectid;
	}

	public void setPlProjectid(Long plProjectid) {
		this.plProjectid = plProjectid;
	}

	public Long getProjectStageCode() {
		return projectStageCode;
	}

	public void setProjectStageCode(Long projectStageCode) {
		this.projectStageCode = projectStageCode;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	private BigDecimal score;

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getEvaluateResName() {
		return evaluateResName;
	}

	public void setEvaluateResName(String evaluateResName) {
		this.evaluateResName = evaluateResName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Long evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getEvaluateItcode() {
		return evaluateItcode;
	}

	public void setEvaluateItcode(String evaluateItcode) {
		this.evaluateItcode = evaluateItcode;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ResEvaluate [entityId=" + entityId + ", itcode=" + itcode + ", resourceId=" + resourceId + ", content="
				+ content + ", projectId=" + projectId + ", createTime=" + createTime + ", evaluateId=" + evaluateId
				+ ", evaluateItcode=" + evaluateItcode + "]";
	}

}
