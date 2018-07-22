package com.river.core.service;

import java.util.List;

import com.river.core.entity.TreeEntityBase;

public interface IServiceTreeBase<T extends TreeEntityBase> extends IServiceBase<T> {
	
	/**
	 * 递归获取所有子级
	 * @param entityId
	 * @return
	 */
	List<T> getChildren(long entityId);
	/**
	 * 获取下级元素
	 * @param entityId
	 * @return
	 */
	List<T> getChild(long entityId);
	/**
	 * 获取父级
	 * @param entityId
	 * @return
	 */
	T getParent(long entityId);
	
	/**
	 * 根据ID查找对象，包括子所有的子元素
	 * @param entityId
	 * @return
	 */
	T getTreeEntityById(long entityId);

}
