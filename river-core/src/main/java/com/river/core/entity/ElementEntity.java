package com.river.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;

/**
 * 
 * @author zhouheng 对话元素
 */
@RiverTable(Name = "sys_element")
public class ElementEntity extends TreeEntityBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 1：模块 2 ：功能
	 */
	@RiverColumn
	@NotNull
	public Integer elementType;

	@RiverColumn
	public Long functionId;
	@RiverColumn
	@Length(min = 0, max = 20, message = "functionCode长度不能超过20")
	public String functionCode;

	private List<ElementEntity> childElements;

	/**
	 * @return the elementType
	 */
	public Integer getElementType() {
		return elementType;
	}

	/**
	 * @param elementType
	 *            the elementType to set
	 */
	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	/**
	 * @return the functionId
	 */
	public Long getFunctionId() {
		return functionId;
	}

	/**
	 * @param functionId
	 *            the functionId to set
	 */
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	/**
	 * @return the functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * @param functionCode
	 *            the functionCode to set
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public List<ElementEntity> getChildElements() {
		return childElements;
	}

	public void setChildElements(List<ElementEntity> childElements) {
		this.childElements = childElements;
	}

}
