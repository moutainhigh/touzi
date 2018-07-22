package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyb
 * @since 2018-01-29
 */
@TableName("project_flow_variable")
public class ProjectFlowVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 流程ID
	 */
	private Long flowId;
	/**
	 * nodeId
	 */
	private Long nodeId;
	/**
	 * stageID
	 */
	private Long stageId;
	/**
	 * 参数名称
	 */
	@TableField("variable_name")
	private String variableName;
	/**
	 * 参数类型:I,S,B
	 */
	@TableField("variable_type")
	private String variableType;
	/**
	 * 默认值
	 */
	@TableField("default_value")
	private String defaultValue;
	/**
	 * 解析器
	 */
	private String parser;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	@Override
	public String toString() {
		return "ProjectFlowVariable{" + ", entityId=" + entityId + ", flowId=" + flowId + ", nodeId=" + nodeId
				+ ", stageId=" + stageId + ", variableName=" + variableName + ", variableType=" + variableType
				+ ", defaultValue=" + defaultValue + ", parser=" + parser + "}";
	}
}
