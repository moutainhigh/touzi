package com.river.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.river.core.entity.EntityBase;
import com.river.core.sql.Page;

public interface ISelectDAOBase<T extends EntityBase> {
	/**
	 * 根据ID获取实体
	 * @param entityId
	 * @return
	 */
    @Select("river.getById")  
	public T getById(@Param("entityId")long entityId);
	/**
	 * 根据业务编码获取实体
	 * @param entityCode
	 * @return
	 */
    @Select("river.getByCode")      
	T getByCode(@Param("entityCode")String entityCode);
    /**
     * 根据GUID获取实体
     * @param entityGUID
     * @return
     */
    @Select("river.getByGUID")      
  	T getByGUID(@Param("entityGUID")String entityGUID);
    /**
     * 
     * @return
     */
    @Select("river.getLastInsertId")      
    long getLastInsertId();
    /**
     * 判断是否存在Code
     * @param entityId
     * @return
     */
    @Select("river.isExistCode")
    int isExistCode(@Param("entityId")long entityId,@Param("entityCode")String entityCode);
    /**
     * 判断是否存在Title
     * @param entityId
     * @return
     */
    @Select("river.isExistTitle")
    int isExistTitle(@Param("entityId")long entityId,@Param("entityTitle")String entityTitle);
	/**
	 * 根据业务ID获取单个字段值
	 * @param entityId
	 * @return
	 */
    @Options(useCache = false, flushCache =FlushCachePolicy.TRUE, timeout = 10000)
    @Select("river.getOneFieldById")  
	String getOneFieldById(@Param("entityId")long entityId,@Param("fieldName")String fieldName);
	/**
	 * 根据业务编码获取单个字段取值
	 * @param entityCode
	 * @return
	 */
    @Options(useCache = false, flushCache =FlushCachePolicy.TRUE, timeout = 10000)
    @Select("river.getOneFieldByCode")  
	String getOneFieldByCode(@Param("entityCode")String entityCode,@Param("fieldName")String fieldName);
    /**
     * 获取记录总数
     * @return
     */
    @Select("river.count")
    int count();
    /**
     * 带条件的获取记录总数
     * @return
     */
    @Options(useCache = false, flushCache =FlushCachePolicy.TRUE, timeout = 10000)
    @Select("river.countBy")
    int countBy(Map<String,Object> map);

	/**
	 * 查询
	 * @param map
	 * @return
	 */    
    @Select("river.queryAll")
	List<T> queryAll();
    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("river.queryAll")
    List<T> pageAll(Page<T> page);
    /**
     * 
     * @param map
     * @return
     */
    @Options(useCache = false, flushCache =FlushCachePolicy.TRUE, timeout = 10000)
    @Select("river.queryBy")
	List<T> queryBy(Map<String,Object> map);
}
