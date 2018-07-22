package com.river.ms.dfs.controller;

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
import com.river.ms.dfs.entity.DocumentType;
import com.river.ms.dfs.result.ErrorResult;
import com.river.ms.dfs.service.DocumentTypeService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/documentType")
public class DocumentTypeController extends ControllerTreeBase<DocumentTypeService, DocumentType> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	DocumentTypeService currentService;

	@Override
	public DocumentTypeService getService() {
		return currentService;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid DocumentType entity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(entity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, entity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		entity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(entity);
		entity.setEntityId(result);
		logger.debug(entity);
		if (result == 0) {
			return ErrorResult.DOCUMENT_TYPE_INSERT_FAIL;
		} else {
			return Success(entity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid DocumentType documentTypeEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(documentTypeEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, documentTypeEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		DocumentType entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.DOCUMENT_TYPE_NOT_EXIST;
		}
		ObjectHelper.Copy(documentTypeEntity, entity);
		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.DOCUMENT_TYPE_UPDATE_FAIL;
		}
	}

}
