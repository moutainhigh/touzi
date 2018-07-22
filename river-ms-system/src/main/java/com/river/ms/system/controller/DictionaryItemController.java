package com.river.ms.system.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.entity.DictionaryItemEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.DictionaryItemService;
/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value="/dictionaryItem")
public class DictionaryItemController extends ControllerTreeBase<DictionaryItemService, DictionaryItemEntity> {
	@Autowired
	DictionaryItemService currentService;
	@Override
	public DictionaryItemService getService() {
		return currentService;
	}
	private final Logger logger = Logger.getLogger(getClass());
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid DictionaryItemEntity dictionaryItemEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(dictionaryItemEntity.getEntityCode())) {
			System.out.println("编码不能为空"); 
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		} 
		if (currentService.isExistCode(-1, dictionaryItemEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		dictionaryItemEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(dictionaryItemEntity);
		logger.debug(dictionaryItemEntity);
		if (result == 0) {
			logger.debug("字典项插入失败");
			return ErrorResult.DICTIONARYITEM_INSERT_FAIL;
		} else {
			logger.debug("字典项插入成功");
			return Success(dictionaryItemEntity);
		}

	}
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid DictionaryItemEntity dictionaryItemEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		DictionaryItemEntity entity=currentService.getById(dictionaryItemEntity.getEntityId());
		if(entity==null){
			logger.debug("该项不存在");
			return ErrorResult.DICTIONARYITEM_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.DICTIONARYITEM_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.DICTIONARYITEM_DISABLE;
		}

		 
		if (StringValidator.isEmpty(dictionaryItemEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(dictionaryItemEntity.getEntityId(), dictionaryItemEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("编码已存在");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(dictionaryItemEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(dictionaryItemEntity);
			if (result) {
				logger.debug("字典项更新成功");
				return Success(dictionaryItemEntity);
			} else {
				logger.debug("字典项更新失败");
				return ErrorResult.DICTIONARYITEM_UPDATE_FAIL;
			}
	}
}
