package com.river.ms.system.controller;



import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.entity.DictionaryEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.DictionaryService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController extends ControllerBase<DictionaryService, DictionaryEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@Autowired
	DictionaryService currentService;
	/**
	 * 
	 * @return
	 */
	public DictionaryService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult dictionaryCreate(@Valid DictionaryEntity dictionaryEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(dictionaryEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, dictionaryEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		
		dictionaryEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(dictionaryEntity);
		logger.debug(dictionaryEntity);
		if (result == 0) {
			return ErrorResult.DICTIONARY_INSERT_FAIL;
		} else {
			return Success(dictionaryEntity);
		}

	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult dictionaryUpdate(@Valid DictionaryEntity dictionaryEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		DictionaryEntity entity = currentService.getById(dictionaryEntity.getEntityId());
		if (entity == null ) {
			logger.debug("该项不存在");
			return ErrorResult.DICTIONARY_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.DICTIONARY_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.DICTIONARY_DISABLE;
		}

		if (StringValidator.isNullOrEmpty(dictionaryEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(dictionaryEntity.getEntityId(), dictionaryEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(dictionaryEntity, entity);
		boolean	result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("dictionary更新成功");
			return Success(entity);
		} else {
			logger.debug("dictionary更新失败");
			return ErrorResult.DICTIONARY_UPDATE_FAIL;
			}
		
	   }
	
}
