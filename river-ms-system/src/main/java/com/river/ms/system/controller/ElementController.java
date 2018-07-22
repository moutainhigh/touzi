package com.river.ms.system.controller;


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.entity.ElementEntity;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.ElementService;
import com.river.ms.system.service.FunctionService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/element")
public class ElementController extends ControllerTreeBase<ElementService, ElementEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 元素业务层
	 */
	@Autowired
	ElementService currentService;
	/**
	 * 功能业务层
	 */
	@Autowired
	FunctionService functionService;

	/**
	 * 
	 * @return
	 */
	public ElementService getService() {
		return currentService;
	}
	

	public FunctionService getFunctionService() {
		return functionService;
	}


	/**
	 * 添加元素
	 * @param elementEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ElementEntity elementEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(elementEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, elementEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("编码已存在");
			return JsonResult.CODE_ISEXIST;
		}
		if (functionService.isExistCode(-1, elementEntity.getFunctionCode())) {
			elementEntity.setFunctionId(functionService.getByCode(elementEntity.getFunctionCode()).getEntityId());
		} else {
			System.out.println("功能编码不存在");
			logger.debug("功能编码不存在！");
			return ErrorResult.FUNCTION_NOT_EXIST;
		}
		elementEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(elementEntity);
		logger.debug(elementEntity);
		if (result == 0) {
			return ErrorResult.ELEMENT_INSERT_FAIL;
			} else {
			return Success(elementEntity);
		}
	}

	/**
	 * 更新元素
	 * @param elementEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ElementEntity elementEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		ElementEntity entity=currentService.getById(elementEntity.getEntityId());
		if(entity==null){
			logger.debug("该项不已存在");
			return ErrorResult.ELEMENT_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.ELEMENT_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.ELEMENT_DISABLE;
		}
		if (StringValidator.isEmpty(elementEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(elementEntity.getEntityId(), elementEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("编码已存在");
			return JsonResult.CODE_ISEXIST;
		}
		if (functionService.isExistCode(-1, elementEntity.getFunctionCode())) {
			elementEntity.setFunctionId(functionService.getByCode(elementEntity.getFunctionCode()).getEntityId());
		} else {
			System.out.println("功能编码不存在");
			logger.debug("功能编码不存在！");
			return ErrorResult.FUNCTION_NOT_EXIST;
		}
		ObjectHelper.Copy(elementEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(elementEntity);
			if (result) {
				return Success(elementEntity);
			} else {
				return ErrorResult.ELEMENT_UPDATE_FAIL;
			}
	}
}
