package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.MenuEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IMenuMapper extends IDAOBase<MenuEntity> {
	MenuEntity getParentE(@Param("parentId")long parentId);
	
	List<MenuEntity> getMenuByFunction(@Param("parentId")long parentId,@Param("functions")List<String> functions);
	
	List<MenuEntity> getMenuByIds(@Param("ids")List<Long> ids);
}