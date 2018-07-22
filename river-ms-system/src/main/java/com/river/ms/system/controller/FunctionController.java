package com.river.ms.system.controller;



import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.entity.FunctionEntity;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.FunctionService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/function")
public class FunctionController extends ControllerTreeBase<FunctionService,FunctionEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 功能业务层
	 */
	@Autowired
	FunctionService currentService;
	
	/**
	 * 
	 * @return
	 */
	public FunctionService getService() {
		return currentService;
	}

	/**
	 * 添加功能
	 * @param functionEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult functionCreate(@Valid FunctionEntity functionEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(functionEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, functionEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		
		functionEntity.setEntityGUID(StringHelper.generateGUID());
		logger.debug(functionEntity);
		long result = currentService.insert(functionEntity);
		functionEntity.setEntityId(result);
		if (result == 0) {
			logger.debug("function插入失败");
			return ErrorResult.FUNCTION_INSERT_FAIL;
		} else {
			logger.debug("function插入成功");
			return Success(functionEntity);
		}
	}
	/**
	 * 更新功能
	 * @param functionEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult functionUpdate(@Valid FunctionEntity functionEntity, BindingResult res) {
		
		if (res.hasErrors()) {
			
			return JsonResult.BindingError(res);
		}
		FunctionEntity entity = currentService.getById(functionEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.FUNCTION_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.FUNCTION_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.FUNCTION_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(functionEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(functionEntity.getEntityId(), functionEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(functionEntity, entity);
		boolean	result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("functionEntity更新成功");
			return Success(entity);
		} else {
			logger.debug("functionEntity更新失败");
			return ErrorResult.FUNCTION_UPDATE_FAIL;
		} 
	}
}
