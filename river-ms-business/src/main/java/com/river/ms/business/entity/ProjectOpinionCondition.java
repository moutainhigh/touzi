package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 有条件通过时的条件清单
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_opinion_condition")
public class ProjectOpinionCondition implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 意见ID
     */
	private Long opinionId;
    /**
     * 项目阶段
     */
	private Long stage;
    /**
     * 条件描述
     */
	private String condition;
    /**
     * 0-默认1-前置条件
     */
	private Integer type;
	/**
	 * 提出人
	 */
	@TableField(exist=false)
	private String operator;
	@TableField(exist=false)
	private String itcode;
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

	public Long getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}


	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}
	/**
	 * 提出人
	 * @return
	 */
	public String getOperator(){
		return this.operator;
	}
	/**
	 * 提出人
	 * @param _operator
	 */
	public void setOperator(String _operator){
		this.operator=_operator;
	}
	public String getItcode() {
		return itcode;
	}
	/**
	 * 
	 * @param _itcode
	 */
	public void setItcode(String _itcode) {
		this.itcode = _itcode;
	}
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectOpinionCondition{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", opinionId=" + opinionId +
			", stage=" + stage +
			", condition=" + condition +
			", type=" + type +
			", createTime=" + createTime +
			"}";
	}
}
