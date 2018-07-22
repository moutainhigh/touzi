package com.river.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.river.core.entity.EntityBase;

public interface IUpdateDAOBase<T extends EntityBase> {
	/**
	 * 逻辑删除
	 * @param entityId
	 * @return
	 */
    @Update("river.remove")  
	boolean remove(@Param("entityId")long entityId);
    /**
     * 批量逻辑删除
     * @param entityId
     * @return
     */
    @Update("river.batchRemove")  
	boolean batchRemove(@Param("listEntityId")List<Long> listEntityId);
    /**
     * 
     * @param map
     * @return
     */
    @Update("river.removeBy")  
	boolean removeBy(Map<String,Object> map);
	/**
	 * 禁用
	 * @param entityId
	 * @param status
	 * @return
	 */
    @Update("river.disable")
	boolean disable(@Param("entityId")long entityId,@Param("status")int status);
    /**
     * 批量禁用
     * @param listEntityId
     * @return
     */
    @Update("river.batchDisable")  
	boolean batchDisable(@Param("listEntityId")List<Long> listEntityId,@Param("status")int status);
    
	/**
	 * 根据业务编码更新某个字段
	 * @param entityCode 业务编码
	 * @param fieldName 字段名
	 * @param fieldValue 取值
	 * @return
	 */
    @Update("river.updateOneFieldById")
	boolean updateOneFieldByCode(@Param("entityCode")String entityCode,@Param("fieldName")String fieldName,@Param("fieldValue")String fieldValue);
	/**
	 * 根据ID更新某个字段
	 * @param entityId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
    @Update("river.updateOneFieldByCode")
	boolean updateOneFieldById(@Param("entityId")long entityId,@Param("fieldName")String fieldName,@Param("fieldValue")String fieldValue);

	/**
	 * 修改
	 * @param entity
	 * @return
	 */
    @Update("river.update")
	boolean update(T entity);
}
