package com.river.ms.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerBase;
import com.river.core.entity.ElementEntity;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.FunctionEntity;
import com.river.core.entity.MenuEntity;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;
import com.river.core.helper.DateHelper;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.SecurityHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.core.validator.Regex;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.UserRoleFunctionService;
import com.river.ms.system.service.UserService;
import com.river.ms.system.service.feign.serviceImpl.ToResImpl;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends ControllerBase<UserService, UserEntity> {
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@Autowired
	UserService currentSerivce;

	@Autowired
	UserRoleFunctionService userRoleFunctionService;

	@Autowired
	ToResImpl resService;

	public UserService getService() {
		return currentSerivce;
	}

	/**
	 * 
	 * @param user
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult userCreate(@Valid UserEntity user, BindingResult res, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		// 验证的先后顺序一定要注意，先要验证所有数据是否为空，其次验证数据格式，最后进行数据库验证
		if (StringValidator.isNullOrEmpty(user.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		// 验重
		if (currentSerivce.isExistCode(-1, user.getEntityCode())) {
			return ErrorResult.USER_EXSIST;
		}
		// 最后补充赋值
		user.setUserPass(SecurityHelper.md5(user.getUserPass()));
		user.setEntityGUID(StringHelper.generateGUID());
		long result = currentSerivce.insert(user);
		if (result == 0) {
			logger.debug("user插入失败");
			return ErrorResult.USER_INSERT_FAIL;
		} else {
			logger.debug("user插入成功");
			return Success(user);
		}

	}

	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult userUpdate(@Valid UserEntity user, BindingResult res,
			@PathVariable(value = "entityId") long entityId, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		UserEntity entity = currentSerivce.getById(entityId);
		if (entity == null) {
			return ErrorResult.USER_NOT_EXSIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该用户已被删除");
			return ErrorResult.USER_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该用户已被禁用");
			return ErrorResult.USER_DISABLE;
		}
		if (StringValidator.isEmpty(user.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentSerivce.isExistCode(user.getEntityId(), user.getEntityCode())) {
			return ErrorResult.USER_EXSIST;
		}

		String[] roleIds = request.getParameterValues("roles");
		List<UserRoleEntity> roles = new ArrayList<UserRoleEntity>();
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				UserRoleEntity userRoleEntity = new UserRoleEntity();
				userRoleEntity.setRoleId(Long.parseLong(roleIds[i]));
				userRoleEntity.setUserId(user.getEntityId());
				roles.add(userRoleEntity);
			}
			currentSerivce.addRoles(roles);
		}
		user.setUserPass(SecurityHelper.md5(user.getUserPass()));
		System.out.println("====" + user.getUserPass());
		ObjectHelper.Copy(user, entity);
		boolean result = currentSerivce.update(entity);
		if (result) {
			return Success(user);
		} else {
			return ErrorResult.USER_UPDATE_FAIL;
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserMessage", method = RequestMethod.POST)
	public JsonResult getUserMessage(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		return JsonResult.success(user);
	}

	/**
	 * 这里逻辑暂时这样，待修改调整
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getChiefRole", method = RequestMethod.POST)
	public JsonResult getChiefRole(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.failure("user is null.");
		RoleEntity role = null;
		if (user.roles != null && user.roles.size() > 0) {
			role = contains(user.roles, "ZHUXI");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "CEO");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "ZONGCAI");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "CFO");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "YIBASHOU");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "FUNENGQUN");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "ZHUANJIA");
			if (role != null)
				return JsonResult.success(role);
			role = contains(user.roles, "FENGKONG");
			if (role != null)
				return JsonResult.success(role);
			role = user.roles.get(0);
		}
		return JsonResult.success(role);
	}

	/**
	 * 
	 * @param list
	 * @param roleCode
	 * @return
	 */
	private RoleEntity contains(List<RoleEntity> list, String roleCode) {
		RoleEntity role = null;
		for (int i = 0; i < list.size(); i++) {
			role = list.get(i);
			if (role.entityCode.equals(roleCode)) {
				return role;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult userLogin(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("userInfo");
		if (StringValidator.isNullOrEmpty(userName)) {
			return ErrorResult.USER_NAME_ISEMPTY;
		}
		UserEntity user = userRoleFunctionService.getUserByCode(userName);
		if (user == null) {
			return ErrorResult.USER_NOT_EXSIST;
		}

		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Map<String, String> resAuthentication = resService.getResAuthentication(headerMap, user.getEntityId(),
				user.getEntityCode());
		if (resAuthentication.get("authentication").equals("true")) {
			Map<String, String> userInfo = new HashMap<String, String>();
			userInfo.put("isAuthentication", "true");
			userInfo.put("isLogIn", "true");
			userInfo.put("isFrontstage", "true");
			session.setAttribute("userInfo", userInfo);
			user.setResId(Long.valueOf(resAuthentication.get("resId")));
		} else {
			return ErrorResult.USER_NOT_EXSIST;
		}

		Map<String, List<String>> roleAndFunction = new HashMap<String, List<String>>();
		for (RoleEntity r : user.getRoles()) {
			List<String> functions = new ArrayList<String>();
			for (FunctionEntity f : r.getFunctions()) {
				functions.add(f.getEntityCode());
			}
			roleAndFunction.put(r.getEntityCode(), functions);
		}
		List<MenuEntity> menu = userRoleFunctionService.getMenu(roleAndFunction);
		List<ElementEntity> elements = userRoleFunctionService.getElements(roleAndFunction);
		EmployeeEntity employeeEntity = userRoleFunctionService.getEmployeeEntity(user.getEmployeeId());
		user.setMenus(menu);
		user.setElements(elements);
		user.setEmployee(employeeEntity);
		session.setAttribute("user", user);
		return JsonResult.success(user);
	}

	/**
	 * 后台登陆
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/backstageLogin", method = RequestMethod.POST)
	public JsonResult backstageLogin(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("userInfo");
		if (StringValidator.isNullOrEmpty(userName)) {
			return ErrorResult.USER_NAME_ISEMPTY;
		}
		UserEntity user = userRoleFunctionService.getUserByCode(userName);
		if (user == null) {
			return ErrorResult.USER_NOT_EXSIST;
		}
		if (user.getIsDelete() == 1) {
			logger.debug("该用户已被删除！");
			return ErrorResult.USER_DELETE;
		}
		if (user.getIsDisable() == 1) {
			logger.debug("该用户已被禁用！");
			return ErrorResult.USER_DISABLE;
		}
		if (user.getUserPass() != null && !user.getUserPass().equals("")) {
			if (!SecurityHelper.md5(userPass.trim()).equals(user.getUserPass())) {
				return ErrorResult.USER_ERROR_PASS;
			} else {
				/*
				 * Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
				 * Map<String, String> resAuthentication =
				 * resService.getResAuthentication(headerMap, user.getEntityId(),
				 * user.getEntityCode()); if
				 * (resAuthentication.get("authentication").equals("true")) { Map<String,
				 * String> userInfo = new HashMap<String, String>();
				 * userInfo.put("isAuthentication", "true"); userInfo.put("isLogIn", "true");
				 * userInfo.put("isBackstage", "true"); session.setAttribute("userInfo",
				 * userInfo); user.setResId(Long.valueOf(resAuthentication.get("resId"))); }
				 * else { return ErrorResult.USER_NOT_EXSIST; }
				 */
				Map<String, String> userInfo = new HashMap<String, String>();
				userInfo.put("isAuthentication", "true");
				userInfo.put("isLogIn", "true");
				userInfo.put("isBackstage", "true");
				session.setAttribute("userInfo", userInfo);
				/*
				 * Map<String, List<String>> roleAndFunction = new HashMap<String,
				 * List<String>>(); for (RoleEntity r : user.getRoles()) { List<String>
				 * functions = new ArrayList<String>(); for (FunctionEntity f :
				 * r.getFunctions()) { functions.add(f.getEntityCode()); }
				 * roleAndFunction.put(r.getEntityCode(), functions); } List<MenuEntity> menu =
				 * userRoleFunctionService.getMenu(roleAndFunction); List<ElementEntity>
				 * elements = userRoleFunctionService.getElements(roleAndFunction);
				 * EmployeeEntity employeeEntity =
				 * userRoleFunctionService.getEmployeeEntity(user.getEmployeeId());
				 * user.setMenus(menu); user.setElements(elements);
				 * user.setEmployee(employeeEntity);
				 */
				session.setAttribute("user", user);
				return JsonResult.success(user);
			}
		} else {
			return ErrorResult.USER_ERROR_PASS;
		}
	}

	@RequestMapping(value = "/isBackstageLogin", method = RequestMethod.POST)
	public JsonResult isBackstageLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, String> userInfo = (Map<String, String>) session.getAttribute("userInfo");
		if (userInfo != null) {
			String isBackstage = userInfo.get("isBackstage");
			if (isBackstage != null && !isBackstage.equals("")) {
				if (isBackstage.equals("true")) {
					return JsonResult.SUCCESS;
				} else {
					return JsonResult.FAILURE;
				}
			} else {
				return JsonResult.FAILURE;
			}
		} else {
			return JsonResult.FAILURE;
		}
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.POST)
	public void userLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("userInfo");
		session.invalidate();
	}

	/**
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResult employeeDetail(HttpServletRequest request, @RequestParam(value = "itcode") String itcode) {
		UserEntity user = userRoleFunctionService.getUserByCode(itcode);
		if (user == null)
			return JsonResult.failure("user not exist");
		EmployeeEntity employeeEntity = userRoleFunctionService.getEmployeeEntity(user.getEmployeeId());
		user.setEmployee(employeeEntity);
		return JsonResult.success(user);
	}

	@RequestMapping(value = "/getEmployeeDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResult getEmployeeDetail(HttpServletRequest request, @RequestParam(value = "itcode") String itcode) {
		UserEntity user = userRoleFunctionService.getUserByCode(itcode);
		if (user == null)
			return JsonResult.failure("user not exist");
		EmployeeEntity employeeEntity = userRoleFunctionService.getEmployeeEntity(user.getEmployeeId());
		return JsonResult.success(employeeEntity);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pleaseLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResult pleaseLogin() {
		return JsonResult.NO_JURISDICTION;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonResult userRegister(@Valid UserEntity user, BindingResult res) {
		if (res.hasErrors()) {
			List<ObjectError> list = res.getAllErrors();
			ObjectError error;
			for (int i = 0; i < res.getErrorCount(); i++) {
				error = list.get(i);
				logger.debug(error.getDefaultMessage());
			}
			return Failure(-1, "validate:error");
		}
		if (user.getEntityCode() == null) {
			logger.debug("用户名不能为空");
			return JsonResult.CODE_ISEMPTY;
		} else if (!StringValidator.match(user.getEntityCode(), Regex.CODE)) {
			logger.debug("用户名格式不正确");
			return JsonResult.CODE_SPECAIL_WORD;
		}
		if (currentSerivce.isExistCode(-1, user.getEntityCode())) {
			logger.debug("该用户已存在");
			return ErrorResult.USER_EXSIST;
		}
		user.setUserPass(SecurityHelper.md5(user.getUserPass()));
		user.setEntityGUID(StringHelper.generateGUID());
		long result = currentSerivce.insert(user);
		if (result != 0) {
			System.out.println("注册成功");
			logger.debug("用户注册成功");
			return Success(user);
		} else {
			System.out.println("注册失败");
			logger.debug("用户注册失败");
			return ErrorResult.USER_REGISTER_FAIL;
		}
	}

	// 用户修改密码
	@RequestMapping(value = "/modifyPass/{entityId}", method = RequestMethod.POST)
	public JsonResult modifyPass(@Valid UserEntity user, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		String entityCode = user.getEntityCode();
		String newPass = user.getUserPass();
		UserEntity userValide = currentSerivce.getByCode(entityCode);
		if (!currentSerivce.isExistCode(-1, user.getEntityCode())) {
			logger.debug("该用户不存在");
			return ErrorResult.USER_NOT_EXSIST;
		}
		if (userValide.getIsDelete() == 1) {
			logger.debug("该用户已被删除");
			return ErrorResult.USER_DELETE;
		} else if (userValide.getIsDisable() == 1) {
			logger.debug("该用户已被禁用");
			return ErrorResult.USER_DISABLE;
		} else if (userValide.getUserPass().equals(SecurityHelper.md5(newPass.trim()))) {
			logger.debug("新置密码与原来密码相同");
			System.out.println("新置密码与原来密码相同");
			return ErrorResult.USER_MODIFY_FAIL;
		}
		user.setUserPass(SecurityHelper.md5(newPass.trim()));
		ObjectHelper.Copy(user, userValide);
		boolean result = currentSerivce.update(userValide);
		if (result) {
			logger.debug("修改密码成功！");
			return Success(user);
		} else {
			logger.debug("修改密码失败！");
			return ErrorResult.USER_MODIFY_FAIL;
		}
	}

	// 用户忘记密码，找回
	@RequestMapping(value = "/findPass/{entityId}", method = RequestMethod.POST)
	public JsonResult findPass(@Valid UserEntity user, BindingResult res) {
		if (res.hasErrors()) {
			List<ObjectError> list = res.getAllErrors();
			ObjectError error;
			for (int i = 0; i < res.getErrorCount(); i++) {
				error = list.get(i);
				logger.debug(error.getDefaultMessage());
			}
			return Failure(-1, "validate:error");
		}
		String entityCode = user.getEntityCode();

		UserEntity userValide = currentSerivce.getByCode(entityCode);
		if (!currentSerivce.isExistCode(-1, user.getEntityCode())) {
			logger.debug("该用户不存在");
			return ErrorResult.USER_NOT_EXSIST;
		} else if (userValide.getIsDelete() == 1) {
			logger.debug("该用户已被删除");
			return ErrorResult.USER_DELETE;
		} else if (userValide.getIsDisable() == 1) {
			logger.debug("该用户已被禁用");
			return ErrorResult.USER_DISABLE;
		}
		user.setUserPass(SecurityHelper.md5(user.getUserPass()));
		ObjectHelper.Copy(user, userValide);
		boolean result = currentSerivce.update(userValide);
		if (result) {
			logger.debug("密码找回成功！");
			System.out.println("密码找回成功");
			return Success(user);
		} else {
			System.out.println("密码找回失败");
			logger.debug("密码找回失败！");
			return ErrorResult.USER_MODIFY_FAIL;
		}

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult userSearch(@RequestParam(value = "keyWords", defaultValue = "") String keyWords,
			@RequestParam(value = "entityCode", defaultValue = "") String entityCode,
			@RequestParam(value = "beginTime", defaultValue = "") String beginTime,
			@RequestParam(value = "endTime", defaultValue = "") String endTime,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

		Date begin = DateHelper.stringToDate(beginTime, "yyyy-MM-dd");
		Date end = DateHelper.stringToDate(endTime, "yyyy-MM-dd");
		PageHelper.startPage(page, pageSize);
		List<UserEntity> userList = currentSerivce.search(keyWords, entityCode, begin, end);
		PageInfo pageList = new PageInfo(userList, this.getNavigatePages());
		if (pageList != null) {
			logger.debug("用户查询成功");
			return Success(pageList);
		} else {
			logger.debug("不存在要查询的用户");
			return ErrorResult.USER_NOT_EXSIST;
		}
	}

	@RequestMapping(value = "/listUserRoles", method = RequestMethod.GET)
	public JsonResult listUserRoles(@PathVariable("userId") long userId) {
		List<Long> roleIds = currentSerivce.listUserRoles(userId);
		if (roleIds != null) {
			return Success(roleIds);
		} else {
			return ErrorResult.ROLE_NOT_EXIST;
		}
	}

	/*
	 * //删除roleId下的某个user
	 * 
	 * @RequestMapping(value="/removeUserRole/{roleId}", method =
	 * RequestMethod.POST) public JsonResult removeUserRole(@PathVariable("roleId")
	 * long roleId,
	 * 
	 * @RequestParam(value="userId" ,defaultValue = "") long userId) {
	 * System.out.println("roleId= " + roleId); System.out.println("userId= " +
	 * userId); boolean del = currentSerivce.removeRoleUser(roleId, roleId);
	 * System.out.println(del); if (del) { System.out.println("用户权限删除成功"); return
	 * Success(del); } else { System.out.println("用户权限删除失败"); return
	 * ErrorResult.ROLE_USER_REMOVE_FAIL; } } //禁用roleId下的某个user
	 * 
	 * @RequestMapping(value="/disableUserRole/{roleId}", method =
	 * RequestMethod.POST) public JsonResult disableUserRole(@PathVariable("roleId")
	 * long roleId,
	 * 
	 * @RequestParam(value="userId" ,defaultValue = "") long userId) {
	 * System.out.println("roleId= " + roleId); System.out.println("userId= " +
	 * userId); boolean disable = currentSerivce.disableRoleUser(userId, roleId); if
	 * (disable) { System.out.println("用户权限禁用成功"); return Success(disable); } else {
	 * System.out.println("用户权限禁用失败"); return ErrorResult.ROLE_USER_DISABLE_FAIL; }
	 * }
	 */

	/**
	 * 获取菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public JsonResult getMenu(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		return Success(user.getMenus());
	}

	/**
	 * 获取所有的对话元素
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getElements", method = RequestMethod.GET)
	public JsonResult getElements(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		return Success(user.getElements());
	}

	/**
	 * 根据itCode获取用户信息
	 * 
	 * @param request
	 * @param resid
	 * @return
	 */
	@RequestMapping(value = "/getUserMessageByResId", method = RequestMethod.POST)
	public UserEntity getUserMessageByResId(HttpServletRequest request, @RequestParam("entityCode") String entityCode) {
		UserEntity userByCode = this.userRoleFunctionService.getUserByCode(entityCode);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Map<String, String> resAuthentication = resService.getResAuthentication(headerMap, userByCode.getEntityId(),
				userByCode.getEntityCode());
		if (resAuthentication.get("authentication").equals("true")) {
			Map<String, String> userInfo = new HashMap<String, String>();
			userInfo.put("isAuthentication", "true");
			userInfo.put("isLogIn", "true");
			userByCode.setResId(Long.valueOf(resAuthentication.get("resId")));
		}
		return userByCode;
	}

	@RequestMapping(value = "/isZhuXi", method = RequestMethod.GET)
	public JsonResult isZhuXi(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<RoleEntity> roles = user.getRoles();
		for (RoleEntity roleEntity : roles) {
			if (roleEntity.getEntityCode().equals("ZHUXI")) {
				return JsonResult.success(true);
			}
		}
		return JsonResult.success(false);
	}

	/**
	 * 手机端登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userLoginShouji", method = RequestMethod.POST)
	public JsonResult userLoginShouji(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("userInfo");
		if (StringValidator.isNullOrEmpty(userName)) {
			return ErrorResult.USER_NAME_ISEMPTY;
		}
		UserEntity user = userRoleFunctionService.getUserByCode(userName);
		if (user == null) {
			return ErrorResult.USER_NOT_EXSIST;
		}

		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Map<String, String> resAuthentication = resService.getResAuthentication(headerMap, user.getEntityId(),
				user.getEntityCode());
		if (resAuthentication.get("authentication").equals("true")) {
			Map<String, String> userInfo = new HashMap<String, String>();
			userInfo.put("isAuthentication", "true");
			userInfo.put("isLogIn", "true");
			session.setAttribute("userInfo", userInfo);
			user.setResId(Long.valueOf(resAuthentication.get("resId")));
		} else {
			return ErrorResult.USER_NOT_EXSIST;
		}

		Map<String, List<String>> roleAndFunction = new HashMap<String, List<String>>();
		for (RoleEntity r : user.getRoles()) {
			List<String> functions = new ArrayList<String>();
			for (FunctionEntity f : r.getFunctions()) {
				functions.add(f.getEntityCode());
			}
			roleAndFunction.put(r.getEntityCode(), functions);
		}
		List<MenuEntity> menu = userRoleFunctionService.getMenu(roleAndFunction);
		List<ElementEntity> elements = userRoleFunctionService.getElements(roleAndFunction);
		EmployeeEntity employeeEntity = userRoleFunctionService.getEmployeeEntity(user.getEmployeeId());
		user.setMenus(menu);
		user.setElements(elements);
		user.setEmployee(employeeEntity);
		session.setAttribute("user", user);
		return JsonResult.success(user);
	}

	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public Long insertUser(UserEntity user, HttpServletRequest request) {
		long result = currentSerivce.insert(user);
		return result;
	}

	@RequestMapping(value = "/getGongSi", method = RequestMethod.POST)
	public Map<Long, String> getGongSi(@RequestParam("userId") List<Long> userId) {
		Map<Long, String> result = new HashMap<>();
		List<UserEntity> users = this.currentSerivce.getUser(userId);
		for (UserEntity u : users) {
			EmployeeEntity employeeEntity = userRoleFunctionService.getEmployeeEntity(u.getEmployeeId());
			List<StationEmployeeEntity> stationEmployees = employeeEntity.getStationEmployees();
			for (StationEmployeeEntity stationEmployeeEntity : stationEmployees) {
				if (stationEmployeeEntity.getIsChief().equals(1)) {
					String entityTitle = stationEmployeeEntity.getOrganization().getEntityTitle();
					result.put(u.getEntityId(), entityTitle);
				}
			}
		}
		return result;
	}
}
