package com.river.ms.cms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.cms.entity.ContentEntity;
import com.river.ms.cms.result.ErrorResult;
import com.river.ms.cms.service.ContentService;
@RestController
@RequestMapping(value="/content")
public class ContentController extends ControllerBase<ContentService, ContentEntity> {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	ContentService currentService;
	@Override
	public ContentService getService() {
		// TODO Auto-generated method stub
		return currentService;
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ContentEntity contentEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(contentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, contentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		contentEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(contentEntity);
		contentEntity.setEntityId(result);
		logger.debug(contentEntity);
		if (result == 0) {
			return ErrorResult.CONTENT_INSERT_FAIL;
		} else {
			return Success(contentEntity);
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ContentEntity contentEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {
		
		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(contentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, contentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ContentEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.CONTENT_NOT_EXIST;
		}
		ObjectHelper.Copy(contentEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.CONTENT_UPDATE_FAIL;
		} 	
	}


}
