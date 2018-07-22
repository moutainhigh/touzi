package com.river.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 树形结构DAO基础操作类
 * @author Yinovo
 *
 * @param <T>
 */
public interface ITreeDAOBase<T> {
	/**
	 * 获取所有下级，包括所有子孙级
	 * @param parentId
	 * @return
	 */
	List<T> getChildren(@Param("parentId")long parentId);
	/**
	 * 获取当前下级
	 * @param parentId
	 * @return
	 */
	@Select("river.getChild")
	List<T> getChild(@Param("parentId")long parentId);
	/**
	 * 根据parentId获取父类信息
	 * @param parentId
	 * @return
	 */
	@Select("river.getParent")
	T getParent(@Param("parentId")long parentId);
	
}
