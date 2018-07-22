package com.river.core.dao;

import org.apache.ibatis.annotations.Insert;

import com.river.core.entity.EntityBase;

public interface IInsertDAOBase<T extends EntityBase> {
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
    @Insert("river.insert")
	long insert(T entity);
}
