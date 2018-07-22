package com.river.ms.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.cms.entity.ArticleEntity;
import com.river.ms.cms.entity.CategoryEntity;
import com.river.ms.cms.result.ErrorResult;
import com.river.ms.cms.service.ArticleService;
import com.river.ms.cms.service.CategoryService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController extends ControllerTreeBase<CategoryService, CategoryEntity> {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	CategoryService currentService;

	@Autowired
	ArticleService articleService;

	@Override
	public CategoryService getService() {
		return currentService;
	}

	/**
	 * 插入
	 * 
	 * @param categoryEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid CategoryEntity categoryEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(categoryEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, categoryEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		categoryEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(categoryEntity);
		categoryEntity.setEntityId(result);
		logger.debug(categoryEntity);
		if (result == 0) {
			return ErrorResult.CATEGORY_INSERT_FAIL;
		} else {
			return Success(categoryEntity);
		}
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid CategoryEntity categoryEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(categoryEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, categoryEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		CategoryEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.CATEGORY_NOT_EXIST;
		}
		ObjectHelper.Copy(categoryEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.CATEGORY_UPDATE_FAIL;
		}
	}

	@Override
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {
		CategoryEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.CATEGORY_NOT_EXIST;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("categoryId", entityId);
		List<ArticleEntity> articleList = articleService.queryBy(map);
		if (articleList != null && articleList.size() > 0) {
			return ErrorResult.CATEGORY_EXIST_ARTICLE;
		} else {
			List<CategoryEntity> child = currentService.getChild(entityId);
			if (child != null && child.size() > 0) {
				return ErrorResult.CATEGORY_EXIST_CHILD;
			}
		}
		return super.remove(entityId);
	}

}
