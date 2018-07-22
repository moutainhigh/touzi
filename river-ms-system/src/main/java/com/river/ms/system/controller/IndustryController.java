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
import com.river.ms.system.entity.IndustryEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.IndustryService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/industry")
public class IndustryController extends ControllerTreeBase<IndustryService,IndustryEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 行业业务层
	 */
	@Autowired
	IndustryService currentService;
	/**
	 * 
	 * @return
	 */
	public IndustryService getService() {
		return currentService;
	}

	/**
	 * 插入行业
	 * @param industryEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult industryCreate(@Valid IndustryEntity industryEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(industryEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, industryEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		industryEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(industryEntity);
		logger.debug(industryEntity);
		if (result == 0) {
			logger.debug("industry插入失败");
			return ErrorResult.INDUSTRY_INSERT_FAIL;
		} else {
			logger.debug("industry插入成功");
			return Success(industryEntity);
		}
	}
	/**
	 * 更新行业
	 * @param industryEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult industryUpdate(@Valid IndustryEntity industryEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		IndustryEntity entity = currentService.getById(industryEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.INDUSTRY_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.INDUSTRY_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.INDUSTRY_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(industryEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(industryEntity.getEntityId(), industryEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(industryEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(industryEntity);
		if (result) {
			logger.debug("industry更新成功");
			return Success(entity);
		} else {
			logger.debug("industry更新失败");
			return ErrorResult.INDUSTRY_UPDATE_FAIL;
		} 
			
	}
}
