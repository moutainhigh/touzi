package com.river.ms.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerTreeBase;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.RoleFunctionEntity;
import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.FunctionService;
import com.river.ms.system.service.RoleService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends ControllerTreeBase<RoleService, RoleEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 角色业务
	 */
	@Autowired
	RoleService currentService;
	/**
	 * 功能业务层
	 */
	@Autowired
	FunctionService functionService;

	/**
	 * 
	 * @return
	 */
	public RoleService getService() {
		return currentService;
	}

	/**
	 * 插入角色
	 * 
	 * @param roleEntity
	 *            角色实体
	 * @param res
	 *            结果信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult roleCreate(@Valid RoleEntity roleEntity, BindingResult res, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(roleEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, roleEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		roleEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(roleEntity);
		if (result != 0) {
			return Success(roleEntity);
			/*
			 * List<RoleFunctionEntity> functions = new ArrayList<RoleFunctionEntity>();
			 * //获取功能id数组 String[] functionIds = request.getParameterValues("functions[]");
			 * for (int i = 0; i < functionIds.length; i++) { RoleFunctionEntity
			 * roleFunctionEntity = new RoleFunctionEntity();
			 * roleFunctionEntity.setFunctionId(Long.parseLong(functionIds[i]));
			 * roleFunctionEntity.setRoleId(result); functions.add(roleFunctionEntity); }
			 * boolean flag = currentService.addFunctions(functions); if (flag) {
			 * System.out.println("添加角色功能成功"); logger.debug("role插入成功"); return
			 * Success(roleEntity); } else { System.out.println("添加角色功能失败"); return
			 * ErrorResult.FUNCTION_INSERT_FAIL; }
			 */
		} else {
			logger.debug("role插入失败");
			return ErrorResult.ROLE_INSERT_FAIL;
		}

	}

	/**
	 * 更新角色
	 * 
	 * @param roleEntity
	 *            角色实体
	 * @param res
	 *            结果信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult roleUpdate(@Valid RoleEntity roleEntity, BindingResult res, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		RoleEntity entity = currentService.getById(roleEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.ROLE_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.ROLE_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.ROLE_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(roleEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(roleEntity.getEntityId(), roleEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}

		String[] functionIds = request.getParameterValues("functions[]");
		List<RoleFunctionEntity> functions = new ArrayList<RoleFunctionEntity>();
		if (functionIds != null) {
			for (int i = 0; i < functionIds.length; i++) {
				RoleFunctionEntity roleFunctionEntity = new RoleFunctionEntity();
				roleFunctionEntity.setFunctionId(Long.parseLong(functionIds[i]));
				roleFunctionEntity.setRoleId(roleEntity.getEntityId());
				functions.add(roleFunctionEntity);
			}
			currentService.addFunctions(functions);
		}

		ObjectHelper.Copy(roleEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("role更新成功");
			return Success(entity);
		} else {
			logger.debug("role更新失败");
			return ErrorResult.ROLE_UPDATE_FAIL;
		}
	}

	// 插入角色功能，与表sys_ref_roleFunction关联
	@RequestMapping(value = "/{roleId}/function/insert", method = RequestMethod.POST)
	public JsonResult insertRoleFunction(@RequestParam(value = "functionId[]", defaultValue = "") long[] functionId,
			@PathVariable("roleId") long roleId) {
		System.out.println("functionId: " + functionId);
		System.out.println("roleId= " + roleId);
		if (functionId != null) {
			List<RoleFunctionEntity> functions = new ArrayList<RoleFunctionEntity>();

			for (int i = 0; i < functionId.length; i++) {
				System.out.println("functionId: " + functionId[i]);

				RoleFunctionEntity roleFunctionEntity = new RoleFunctionEntity();
				roleFunctionEntity.setFunctionId(functionId[i]);
				roleFunctionEntity.setRoleId(roleId);
				functions.add(roleFunctionEntity);
			}

			boolean del = currentService.deleteRole(roleId);
			System.out.println("del= " + del);

			boolean flag = currentService.addFunctions(functions);
			if (flag) {
				System.out.println("添加权限功能成功");
				return Success(functions);
			} else {
				System.out.println("添加权限功能失败");
				return ErrorResult.ROLE_FUNCTION_INSERT_FAIL;
			}
		}
		return ErrorResult.ROLE_FUNCTION_INSERT_FAIL;
	}

	// 列出roleId对应的所有的功能
	@RequestMapping(value = "/{roleId}/function", method = RequestMethod.GET)
	public JsonResult listRoleFunction(@PathVariable("roleId") long roleId) {
		System.out.println("roleId=" + roleId);
		List<Long> functions = new ArrayList<Long>();
		functions = currentService.listRoleFunction(roleId);
		return Success(functions);
	}

	// 列出roleId下所有的用户
	@RequestMapping(value = "/{roleId}/user", method = RequestMethod.GET)
	public JsonResult listRoleUser(@PathVariable("roleId") long roleId) {
		System.out.println("roleId=" + roleId);
		List<UserRoleEntity> users = new ArrayList<UserRoleEntity>();
		users = currentService.listUsers(roleId);
		return Success(users);
	}

	// 列出roleId下所有的用户
	@RequestMapping(value = "/getRoleUser", method = RequestMethod.POST)
	public JsonResult getRoleUser(HttpServletRequest request,
			@RequestParam(name = "roleId", required = false, defaultValue = "0") Long roleId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UserRoleEntity> users = currentService.getRoleUser(roleId,keyword);
		PageInfo<UserRoleEntity> pageInfo = new PageInfo<UserRoleEntity>(users, 5);
		return JsonResult.successPage(users, pageInfo);
	}

	// 删除roleId下的某个user
	@RequestMapping(value = "/{roleId}/user/remove", method = RequestMethod.POST)
	public JsonResult removeUserRole(@RequestParam(value = "roleUserId[]", defaultValue = "") long[] roleUserId,
			@PathVariable("roleId") long roleId) {
		List<Long> roleUsers = new ArrayList<Long>();
		for (Long id : roleUserId) {

			roleUsers.add(id);
		}
		System.out.println("大小" + roleUsers.size());
		System.out.println(roleUsers);
		boolean del = currentService.removeUserRole(roleId, roleUsers);
		if (del) {
			System.out.println("用户权删除用成功");
			return Success(del);
		} else {
			System.out.println("用户权限删除失败");
			return ErrorResult.ROLE_USER_REMOVE_FAIL;
		}
	}

	// 禁用roleId下的某个user
	@RequestMapping(value = "/{roleId}/user/disable", method = RequestMethod.POST)
	public JsonResult disableUserRole(@RequestParam(value = "roleUserId[]", defaultValue = "") long[] roleUserId,
			@PathVariable("roleId") long roleId) {
		List<Long> roleUsers = new ArrayList<Long>();
		for (Long id : roleUserId) {

			roleUsers.add(id);
		}
		System.out.println("大小" + roleUsers.size());
		System.out.println(roleUsers);
		boolean disable = currentService.disableUserRole(roleId, roleUsers);
		if (disable) {
			System.out.println("用户权限禁用成功");
			return Success(disable);
		} else {
			System.out.println("用户权限禁用失败");
			return ErrorResult.ROLE_USER_DISABLE_FAIL;
		}
	}

	// 插入用户角色
	@RequestMapping(value = "/{roleId}/user/insert", method = RequestMethod.POST)
	public JsonResult insertRoleUser(@PathVariable("roleId") long roleId,
			@RequestParam(value = "userId[]", defaultValue = "") long[] roleUserId) {

		System.out.println("roleId= " + roleId);
		if (roleUserId != null) {
			List<UserRoleEntity> users = new ArrayList<UserRoleEntity>();

			for (int i = 0; i < roleUserId.length; i++) {
				System.out.println("userId: " + roleUserId[i]);

				UserRoleEntity userRoleEntity = new UserRoleEntity();
				userRoleEntity.setUserId(roleUserId[i]);
				userRoleEntity.setRoleId(roleId);
				users.add(userRoleEntity);
			}
			System.out.println("打印" + users);
			boolean flag = currentService.addUsers(users);
			if (flag) {
				System.out.println("添加用户权限成功");
				return Success(users);
			} else {
				System.out.println("添加权限功能失败");
				return ErrorResult.ROLE_USER_INSERT_FAIL;
			}
		}
		return ErrorResult.ROLE_FUNCTION_INSERT_FAIL;
	}

	// 列出可选择的用户
	@RequestMapping(value = "/{roleId}/selectableUser", method = RequestMethod.POST)
	public JsonResult selectableUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "keyWords", defaultValue = "") String keyWords, @PathVariable("roleId") long roleId) {
		PageHelper.startPage(page, pageSize);
		List<UserEntity> selectableUser = currentService.selectableUser(roleId, keyWords);
		System.out.println(selectableUser.size());
		PageInfo pageList = new PageInfo(selectableUser, this.getNavigatePages());
		if (pageList != null) {
			return Success(pageList);
		} else {
			System.out.println("不存在可选的用户");
			return ErrorResult.USER_NOT_EXSIST;
		}
	}

	@RequestMapping(value = "/getRoleName", method = RequestMethod.POST)
	public String getRoleNameById(@RequestParam(value = "roleId") long roleId) {
		RoleEntity byId = this.getService().getById(roleId);
		return byId.getEntityCode();
	}
}
