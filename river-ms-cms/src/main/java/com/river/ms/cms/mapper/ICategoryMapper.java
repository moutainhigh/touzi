package com.river.ms.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.cms.entity.CategoryEntity;
/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface ICategoryMapper extends IDAOBase<CategoryEntity> {
	CategoryEntity getParentE(@Param("parentId") long parentId);
	
	List<CategoryEntity> getCategoryEntityByArticleId(@Param("articleId") long articleId);
}
