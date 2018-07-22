package com.river.core.service;

import java.util.List;
import java.util.Map;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.EntityBase;
import com.river.core.sql.Page;

/**
 * 
 * @author Yinovo
 *
 */
public abstract class ServiceBase<D extends IDAOBase<T>,T extends EntityBase> implements IServiceBase<T> {
	/*
	 * 基类必须实现该方法
	 * @return
	 */
	public abstract D getDao();  
	
	/* 根据entityId获取单个字段的取值(暂时存在预编译问题，尚未解决)
	 * @see com.river.core.service.IServiceBase#getOneFieldById(long, java.lang.String)
	 */
	
	public String getOneFieldById(long entityId, String fieldName) {
		return getDao().getOneFieldById(entityId,fieldName);
	}
	/* 根据业务编码获取单个字段的取值(暂时存在预编译问题，尚未解决)
	 * @see com.river.core.service.IServiceBase#getOneFieldByCode(java.lang.String, java.lang.String)
	 */
	
	public String getOneFieldByCode(String entityCode, String fieldName) {
		return getDao().getOneFieldByCode(entityCode, fieldName);
	}
	/* 获取记录总数
	 * @see com.river.core.service.IServiceBase#count()
	 */
	
	public int count() {
		return getDao().count();
	}
	/**
	 * 
	 */
	public boolean isExistCode(long entityId,String entityCode) {
		int count= getDao().isExistCode(entityId, entityCode);
		return count>0;
	}
	/**
	 * 
	 */
	public boolean isExistTitle(long entityId,String entityTitle) {
		int count=getDao().isExistTitle(entityId, entityTitle);
		return count>0;
	}
	/* 根据条件获取记录总数
	 * @see com.river.core.service.IServiceBase#countBy(java.util.Map)
	 */
	
	public int countBy(Map<String,Object> map) {
		return getDao().countBy(map);
	}
	
	/* 查询所有记录
	 * @see com.river.core.service.IServiceBase#queryAll()
	 */
	
	public List<T> queryAll() {
		return getDao().queryAll();
	}
	/**
	 * 
	 */
	public List<T> pageAll(Page<T> page) {
		return getDao().pageAll(page);
	}
	/* 批量删除
	 * @see com.river.core.service.IServiceBase#batchRemove(java.util.List)
	 */
	
	public boolean batchRemove(List<Long> listEntityId) {
		return getDao().batchRemove(listEntityId);
	}
	/* 有条件删除
	 * @see com.river.core.service.IServiceBase#removeBy(java.util.Map)
	 */
	
	public boolean removeBy(Map<String,Object> map) {
		return getDao().removeBy(map);
	}
	/* 禁用
	 * @see com.river.core.service.IServiceBase#disable(long)
	 */
	
	public boolean disable(long entityId,int status) {
		return getDao().disable(entityId, status);
	}
	/* 批量禁用
	 * @see com.river.core.service.IServiceBase#batchDisable(java.util.List)
	 */
	
	public boolean batchDisable(List<Long> listEntityId,int status) {
		return getDao().batchDisable(listEntityId, status);
	}
	/* 根据entityId更新某个字段
	 * @see com.river.core.service.IServiceBase#updateOneFieldById(long, java.lang.String, java.lang.String)
	 */
	
	public boolean updateOneFieldById(long entityId, String fieldName, String fieldValue) {
		return getDao().updateOneFieldById(entityId, fieldName, fieldValue);
	}
	/* 根据业务编码更新单个字段的值
	 * @see com.river.core.service.IServiceBase#updateOneFieldByCode(java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public boolean updateOneFieldByCode(String entityCode, String fieldName, String fieldValue) {
		return getDao().updateOneFieldByCode(entityCode, fieldName, fieldValue);
	}
	/* 根据entityId获取单个业务实体
	 * @see com.river.core.service.IServiceBase#getById(long)
	 */
	public T getById(long entityId) {
		return getDao().getById(entityId);
	}

	/* 根据业务编码获取单个业务实体
	 * @see com.river.core.service.IServiceBase#getByCode(java.lang.String)
	 */
	public T getByCode(String entityCode) {
		return getDao().getByCode(entityCode);
	}
	/**
	 * 根据GUID获取单个业务实体
	 */
	public T getByGUID(String entityGUID) {
		return getDao().getByGUID(entityGUID);
	}
	
	/* 条件查询
	 * @see com.river.core.service.IServiceBase#query(java.util.Map)
	 */
	public List<T> queryBy(Map<String,Object> map) {
		return getDao().queryBy(map);
	}

	/* 新增实体
	 * @see com.river.core.service.IServiceBase#insert(com.river.core.entity.EntityBase)
	 */
	public long insert(T entity) {
		long result= getDao().insert(entity);
		if(result>0){
			return getDao().getLastInsertId();
		}
		return result;
	}

	/* 修改实体
	 * @see com.river.core.service.IServiceBase#update(com.river.core.entity.EntityBase)
	 */
	public boolean update(T entity) {
		return getDao().update(entity);
	}

	/* 根据entityId删除实体
	 * @see com.river.core.service.IServiceBase#remove(long)
	 */
	public boolean remove(long entityId) {
		return getDao().remove(entityId);
	}
	
}
