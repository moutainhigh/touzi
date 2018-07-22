package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目前置条件（赋能群确认)
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_condition")
public class ProjectCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 意见ID
	 */
	@TableField("opinion_conditionId")
	private Long opinionConditionId;
	/**
	 * 来源：0-专家1-其他
	 */
	private Integer src;
	/**
	 * 条件描述
	 */
	private String condition;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 资源ID
	 */
	private Long resourceId;
	private Date createTime;
	/**
	 * 0-默认1-A级2-B级
	 */
	private Integer type;
	/**
	 * 落实时间
	 */
	private Date d1;

	/**
	 * 落实内容
	 */
	private String s1;

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

	public Long getOpinionConditionId() {
		return opinionConditionId;
	}

	public void setOpinionConditionId(Long opinionConditionId) {
		this.opinionConditionId = opinionConditionId;
	}

	public Integer getSrc() {
		return src;
	}

	public void setSrc(Integer src) {
		this.src = src;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	@Override
	public String toString() {
		return "ProjectCondition{" + ", entityId=" + entityId + ", projectId=" + projectId + ", opinionConditionId="
				+ opinionConditionId + ", src=" + src + ", condition=" + condition + ", itcode=" + itcode
				+ ", resourceId=" + resourceId + ", createTime=" + createTime + ", type=" + type + "}";
	}
}
