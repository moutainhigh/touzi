package com.river.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.river.core.entity.EntityBase;
import com.river.core.sql.Page;

public interface IServiceBase<T extends EntityBase> {
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	T getById(long entityId);
	/**
	 * 根据业务编码获取
	 * @param code
	 * @return
	 */
	T getByCode(String entityCode);
	/**
	 * 根据GUID获取
	 * @param entityGUID
	 * @return
	 */
	T getByGUID(String entityGUID);
	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @return
	 */
	String getOneFieldById(long entityId,String fieldName);
	
	/**
	 * 
	 * @param entityCode
	 * @param fieldName
	 * @return
	 */
	String getOneFieldByCode(String entityCode,String fieldName);
	/**
	 * 查询记录总数
	 * @return
	 */
	int count();
	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	int countBy(Map<String,Object> map);
	/**
	 * 
	 * @param entityId
	 * @param entityCode
	 * @return
	 */
	boolean isExistCode(long entityId,String entityCode);
	    /**
	     * 判断是否存在Title
	     * @param entityId
	     * @return
	     */
	boolean isExistTitle(long entityId,String entityTitle);
	/**
	 * 查询所有记录
	 * @return
	 */
	List<T> queryAll();
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	List<T> pageAll(Page<T> page);
	/**
	 * 根据条件查询
	 * @return
	 */
	List<T> queryBy(Map<String,Object> map);
	/**
	 * 
	 * @param entity
	 */
	long insert(T entity);
	/**
	 * 
	 * @param entity
	 */
	boolean update(T entity);
	/**
	 * 
	 * @param id
	 */
	boolean remove(long entityId);
	/**
	 * 
	 * @param listEntityId
	 * @return
	 */
	boolean batchRemove(List<Long> listEntityId);
	/**
	 * 
	 * @param map
	 * @return
	 */
	boolean removeBy(Map<String,Object> map);
	/**
	 * 
	 * @param entityId
	 * @return
	 */
	boolean disable(long entityId,int status/*=1*/);
	/**
	 * 批量禁用
	 * @param listEntityId
	 * @return
	 */
	boolean batchDisable(List<Long> listEntityId,int status/*=1*/);
	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean updateOneFieldById(long entityId,String fieldName,String fieldValue);
	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean updateOneFieldByCode(String entityCode,String fieldName,String fieldValue);
}
