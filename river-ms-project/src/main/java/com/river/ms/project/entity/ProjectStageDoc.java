package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目阶段文档匹配
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_stage_doc")
public class ProjectStageDoc implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目类型
     */
	private Long categoryGroupId;
    /**
     * 投资流程阶段
     */
	private Long stageId;
    /**
     * 小阶段
     */
	private String node;
    /**
     * 文档类型
     */
	private String document;
    /**
     * 0-默认1-节点文档
     */
	private Integer type;
    /**
     * 文档说明
     */
	private String content;
	private Date createTime;
    /**
     * 0-可选1-必选
     */
	private Integer required;


	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}


	public Long getCategoryGroupId() {
		return categoryGroupId;
	}

	public void setCategoryGroupId(Long categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRequired() {
		return required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	@Override
	public String toString() {
		return "ProjectStageDoc [entityId=" + entityId + ", categoryGroupId=" + categoryGroupId + ", stageId=" + stageId
				+ ", node=" + node + ", document=" + document + ", type=" + type + ", content=" + content
				+ ", createTime=" + createTime + ", required=" + required + "]";
	}
}
