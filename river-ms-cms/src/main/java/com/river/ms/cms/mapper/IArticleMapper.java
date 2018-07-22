package com.river.ms.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.cms.entity.ArticleEntity;

/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface IArticleMapper extends IDAOBase<ArticleEntity> {
	
	/**
	 * 根据条件查询
	 * @param entity
	 * @param categoryIds
	 * @return
	 */
	List<ArticleEntity> queryByArticle(@Param("entity")ArticleEntity entity,@Param("categoryIds") List<Long> categoryIds);
	//根据
	List<ArticleEntity> queryByCateogryCode(@Param("entityCode")String entityCode);
	/**
	 * 批量插入
	 * @param categoryIds
	 * @param articleIds
	 */
	void insertCategoryArticle(@Param("categoryIds")List<Long> categoryIds , @Param("articleIds")List<Long> articleIds);
	
	List<ArticleEntity> queryByStages(@Param("stageCodes") List<String> stageCodes,@Param("stageCodesNum") Integer stageCodesNum);
	
	ArticleEntity getByIdRiver(@Param("entityId") Long entityId);
}
