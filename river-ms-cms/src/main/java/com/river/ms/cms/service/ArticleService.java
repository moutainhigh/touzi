package com.river.ms.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.river.core.service.ServiceBase;
import com.river.ms.cms.entity.ArticleEntity;
import com.river.ms.cms.entity.CategoryEntity;
import com.river.ms.cms.entity.ContentEntity;
import com.river.ms.cms.mapper.IArticleMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ArticleService extends ServiceBase<IArticleMapper, ArticleEntity> {
	@Autowired
	IArticleMapper mapper;

	@Autowired
	ContentService contentService;
	
	@Autowired
	CategoryService categoryService;

	@Override
	public IArticleMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

	/**
	 * 根据条件查询
	 * 
	 * @param entity
	 * @param categoryIds
	 *            查询包含某些主题的资料时，传入主题ID的list。
	 * @return
	 */
	public List<ArticleEntity> queryArticle(ArticleEntity entity, List<Long> categoryIds) {
		return mapper.queryByArticle(entity, categoryIds);
	}

	/**
	 * 
	 * @param categoryCode
	 * @return
	 */
	public List<ArticleEntity> queryByCategory(String categoryCode) {
		return mapper.queryByCateogryCode(categoryCode);
	}

	/**
	 * 批量插入ArticleEntity和CategoryEntity的关联关系 可以批量给同一篇文章赋多个主题 可以给多篇文章赋同一个主题
	 * 可以给多篇文章赋多个主题
	 * 
	 * @param categoryIds
	 * @param articleIds
	 */
	@Transactional
	public void insertCategoryArticle(List<Long> categoryIds, List<Long> articleIds) {
		this.mapper.insertCategoryArticle(categoryIds, articleIds);
	}

	@Override
	@Transactional
	public long insert(ArticleEntity entity) {
		long insert = super.insert(entity);
		ContentEntity content = new ContentEntity();
		content.setContent(entity.getContent());
		content.setArticleId(insert);
		contentService.insert(content);
		return insert;
	}

	@Override
	@Transactional
	public boolean update(ArticleEntity entity) {
		boolean update = super.update(entity);
		if (update) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("articleId", entity.getEntityId());
			List<ContentEntity> queryBy = contentService.queryBy(map);
			if (queryBy != null && !queryBy.isEmpty()) {
				queryBy.get(0).setContent(entity.getContent());
				contentService.update(queryBy.get(0));
			}
		}
		return update;
	}

	@Override
	public ArticleEntity getById(long entityId) {
		// TODO Auto-generated method stub
		ArticleEntity entity = super.getById(entityId);
		if (entity != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("articleId", entityId);
			List<ContentEntity> queryBy = contentService.queryBy(map);
			if (queryBy != null && !queryBy.isEmpty()) {
				entity.setContentEntity(queryBy.get(0));
				entity.setContent(queryBy.get(0).getContent());
			} 
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("entityId", entity.getCategoryId());
			List<CategoryEntity> queryBy2 = categoryService.queryBy(map1);
			if(queryBy2 != null && queryBy2.size() >0 ) {
				entity.setCategoryTitle(queryBy2.get(0).getEntityTitle());
			}
		}
		return entity;
	}

	public List<ArticleEntity> queryByStages(List<String> stageCodes) {
		List<ArticleEntity> queryByStages = this.mapper.queryByStages(stageCodes,stageCodes.size());
		return queryByStages;
	}
	
	public ArticleEntity getByIdRiver(Long entityId) {
		 ArticleEntity byIdRiver = this.mapper.getByIdRiver(entityId);
		return byIdRiver;
	}
}
