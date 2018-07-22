package com.river.ms.system.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.river.ms.system.entity.ConfigEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.ConfigService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import io.swagger.annotations.*;


/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController extends ControllerBase<ConfigService, ConfigEntity> {

	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 系统参数业务层
	 */
	@Autowired
	ConfigService currentService;

	/**
	 * 
	 * @return
	 */
	public ConfigService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "新增系统参数", notes = "新增系统参数API")
	@ApiImplicitParams({
         @ApiImplicitParam(name = "ConfigEntity", value = "系统参数对象", required = true, paramType = "ConfigEntity", dataType = "ConfigEntity"),
    })
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult configCreate(@Valid ConfigEntity configEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(configEntity.getEntityCode())) {
			System.out.println("编码不能为空"); 
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		} 
		if (currentService.isExistCode(-1, configEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		configEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(configEntity);
		logger.debug(configEntity);
		if (result == 0) {
			return ErrorResult.CONFIG_INSERT_FAIL;
		} else {
			return Success(configEntity);
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult configUpdate(@Valid ConfigEntity configEntity, BindingResult res,HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		ConfigEntity entity = currentService.getById(configEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.CONFIG_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.CONFIG_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.CONFIG_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(configEntity.getEntityCode())) {
			System.out.println("编码不能为空"); 
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		} 
		if (currentService.isExistCode(configEntity.getEntityId(), configEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		String configData = request.getParameter("configData");
		JSONArray json = JSONArray.fromObject(configData);
		JSONObject configJson;
		System.out.println(json.getJSONObject(1));
		List<ConfigEntity> configs = new ArrayList<ConfigEntity>();
		for (int i = 0; i < json.size();i++) {
			configJson = json.getJSONObject(i);
			String entityCode = (String) configJson.get("entityCode");
			String configValue = (String) configJson.get("configValue");
			ConfigEntity config = new ConfigEntity();
			config.setConfigValue(configValue);
			config.setEntityCode(entityCode);
			configs.add(config);
			System.out.println("====");
			System.out.println(entityCode+"=====" +configValue);
		}
		currentService.updateConfig(configs);
		ObjectHelper.Copy(configEntity, entity);
		logger.debug(entity);
		boolean result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.CONFIG_UPDATE_FAIL;
		}
	}
	/**
	 * 获取系统当前时间
	 * @return
	 */
	@RequestMapping(value = "/now", method = RequestMethod.GET)
	public JsonResult getNow(){
		return JsonResult.success(currentService.getNow());
		//return JsonResult.success(new Date());
	} 
}
