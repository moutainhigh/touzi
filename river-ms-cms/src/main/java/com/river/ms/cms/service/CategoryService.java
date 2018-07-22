package com.river.ms.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.cms.entity.CategoryEntity;
import com.river.ms.cms.mapper.ICategoryMapper;
/**
 * 
 * @author zhouheng
 *
 */
@Service
public class CategoryService extends ServiceTreeBase<ICategoryMapper, CategoryEntity> {
	@Autowired
	ICategoryMapper mapper;

	@Override
	public ICategoryMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	/**
	 * 获取某article下的所有的category，查询cms_ref_categoryArticle表
	 * @param articleId
	 * @return List<CategoryEntity>
	 */
	public List<CategoryEntity> getCategoryEntityByArticleId(@Param("articleId") long articleId){
		return mapper.getCategoryEntityByArticleId(articleId);
	}
}
