package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 项目阶段
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_stage")
public class ProjectStage implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 阶段编码
	 */
	@NotNull(message = "编码不能为空!")
	private String entityCode;
	/**
	 * 阶段名称
	 */
	@NotNull(message = "名称不能为空!")
	private String entityTitle;
	/**
	 * 阶段描述
	 */
	private String entityDesc;
	/**
	 * 阶段层级0-大阶段1-二级阶段
	 */
	@NotNull(message = "级别不能为空!")
	private Integer level;
	/**
	 * 上级ID
	 */
	private Long parentId;

	/**
	 * 顺序号
	 */
	private Integer order;

	/**
	 * 关联节点ID
	 */
	private Long src;
	/**
	 * 组织机构信息
	 */
	private String groupCode;
	/**
	 * 是否提醒
	 */
	private Integer remind;

	/**
	 * 流程ID
	 */
	private Long flowId;
	/**
	 * 
	 * @return
	 */
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getSrc() {
		return src;
	}

	public void setSrc(Long src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "ProjectStage{" + ", entityId=" + entityId + ", entityCode=" + entityCode + ", entityTitle="
				+ entityTitle + ", entityDesc=" + entityDesc + ", level=" + level + ", parentId=" + parentId + "}";
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Integer getRemind() {
		return remind;
	}

	public void setRemind(Integer remind) {
		this.remind = remind;
	}
}
