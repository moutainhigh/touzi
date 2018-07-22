package com.river.ms.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.entity.MenuEntity;
import com.river.core.entity.UserEntity;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.FunctionService;
import com.river.ms.system.service.MenuService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController extends ControllerTreeBase<MenuService, MenuEntity> {
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
	MenuService currentService;
	/**
	 * 功能业务层
	 */
	@Autowired
	FunctionService functionService;

	/**
	 * 
	 * @return
	 */
	public MenuService getService() {
		return currentService;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult menuCreate(@Valid MenuEntity menuEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(menuEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, menuEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		if (functionService.isExistCode(-1, menuEntity.getFunctionCode())) {
			menuEntity.setFunctionId(functionService.getByCode(menuEntity.getFunctionCode()).getEntityId());
		} else {
			System.out.println("功能编码不存在");
			logger.debug("功能编码不存在！");
			return ErrorResult.FUNCTION_NOT_EXIST;
		}
		menuEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(menuEntity);
		logger.debug(menuEntity);
		if (result == 0) {
			logger.debug("menu插入失败");
			return ErrorResult.MENU_INSERT_FAIL;
		} else {
			logger.debug("menu插入成功");
			return Success(menuEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult menuUpdate(@Valid MenuEntity menuEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		MenuEntity entity = currentService.getById(menuEntity.getEntityId());

		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.MENU_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.MENU_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.MENU_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(menuEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(menuEntity.getEntityId(), menuEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		if (functionService.isExistCode(-1, menuEntity.getFunctionCode())) {
			menuEntity.setFunctionId(functionService.getByCode(menuEntity.getFunctionCode()).getEntityId());
		} else {
			System.out.println("功能编码不存在");
			logger.debug("功能编码不存在！");
			return ErrorResult.FUNCTION_NOT_EXIST;
		}
		ObjectHelper.Copy(menuEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("menuEntity更新成功");
			return Success(entity);
		} else {
			logger.debug("menuEntity更新失败");
			return ErrorResult.MENU_UPDATE_FAIL;
		}
	}

	/**
	 * 获取用户有权限的菜单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMenuByUser", method = RequestMethod.POST)
	public JsonResult getMenuByUser(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<MenuEntity> menus = user.getMenus();
		return JsonResult.success(menus);
	}

}