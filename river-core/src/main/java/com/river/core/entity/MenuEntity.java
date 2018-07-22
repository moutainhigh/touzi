package com.river.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
import com.river.core.validator.Regex;

/**
 * 
 * @author zhouheng 菜单
 */
@RiverTable(Name = "sys_menu")
public class MenuEntity extends TreeEntityBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 小图标
	 */
	@RiverColumn
	@Length(min = 0, max = 20, message = "menuIcon长度不能超过20")
	public String menuIcon;

	/**
	 * 路径
	 */
	@RiverColumn
	@Length(min = 0, max = 20, message = "menuPath长度不能超过20")
	public String menuPath;

	/**
	 * 功能ID
	 */
	@RiverColumn
	public long functionId;

	/**
	 * 功能code
	 */
	@RiverColumn
	@Length(min = 0, max = 20, message = "functionCode长度不能超过20")
	@Pattern(regexp = Regex.CODE, message = "functionCode的格式不正确")
	public String functionCode;

	/**
	 * 排序字段
	 */
	@RiverColumn
	public int entityOrder;
	
	private List<MenuEntity> childMenu;

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public List<MenuEntity> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<MenuEntity> childMenu) {
		this.childMenu = childMenu;
	}

	public int getEntityOrder() {
		return entityOrder;
	}

	public void setEntityOrder(int entityOrder) {
		this.entityOrder = entityOrder;
	}
	
}
