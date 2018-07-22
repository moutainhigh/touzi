package com.river.ms.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerBase;
import com.river.core.entity.EmployeeEntity;
import com.river.core.helper.DateHelper;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.EmployeeService;
import com.river.ms.system.service.UserService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends ControllerBase<EmployeeService, EmployeeEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 员工业务层
	 */
	@Autowired
	EmployeeService currentService;
	
	@Autowired
	UserService userService;

	/**
	 * 
	 * @return
	 */
	public EmployeeService getService() {
		return currentService;
	}

	/**
	 * 添加员工
	 * 
	 * @param employeeEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult employeeCreate(@Valid EmployeeEntity employeeEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(employeeEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, employeeEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}

		employeeEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(employeeEntity);
		logger.debug(employeeEntity);
		if (result == 0) {
			logger.debug("员工插入失败");
			return ErrorResult.EMPLOYEE_INSERT_FAIL;
		} else {
			logger.debug("员工插入成功");
			return Success(employeeEntity);
		}
	}

	/**
	 * 更新员工
	 * 
	 * @param employeeEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult employeeUpdate(@Valid EmployeeEntity employeeEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		EmployeeEntity entity = currentService.getById(employeeEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.EMPLOYEE_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.EMPLOYEE_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.EMPLOYEE_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(employeeEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(employeeEntity.getEntityId(), employeeEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(employeeEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("员工更新成功");
			return Success(entity);
		} else {
			logger.debug("员工更新失败");
			return ErrorResult.EMPLOYEE_UPDATE_FAIL;
		}

	}

	/**
	 * 搜索员工
	 * 
	 * @param keyWords
	 * @param entityCode
	 * @param idCard
	 * @param mobile
	 * @param email
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult employeeSearch(@RequestParam(value = "keyWords", defaultValue = "") String keyWords,
			@RequestParam(value = "entityCode", defaultValue = "") String entityCode,
			@RequestParam(value = "idCard", defaultValue = "") String idCard,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "beginTime", defaultValue = "") String beginTime,
			@RequestParam(value = "endTime", defaultValue = "") String endTime,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		Date begin = DateHelper.stringToDate(beginTime, "yyyy-MM-dd");
		Date end = DateHelper.stringToDate(endTime, "yyyy-MM-dd");
		PageHelper.startPage(page, pageSize);
		List<EmployeeEntity> employeeList = currentService.search(keyWords, entityCode, idCard, mobile, email, begin,
				end);
		PageInfo pageList = new PageInfo(employeeList, this.getNavigatePages());
		if (pageList != null) {
			logger.debug("员工查询成功");
			return Success(pageList);
		} else {
			logger.debug("不存在要查询的员工");
			return ErrorResult.EMPLOYEE_NOT_EXIST;
		}
	}

	/**
	 * 根据用户itCode获取用户的GUID
	 * 
	 * @param itCodes
	 * @return
	 */
	@RequestMapping(value = "/getGuidByItCodes", method = RequestMethod.POST)
	public JsonResult getGuidByItCodes(@RequestParam(value = "itCodes[]") List<String> itCodes) {
		List<EmployeeEntity> guidByItCodes = new ArrayList<>();
		if (itCodes != null && itCodes.size() > 0) {
			guidByItCodes = currentService.getGuidByItCodes(itCodes);
		}
		return JsonResult.success(guidByItCodes);
	}

	/**
	 * 验证code是否存在
	 * 
	 * @param entityId
	 * @param entityCode
	 * @return 存在 true 不存在 false
	 */
	@RequestMapping(value = "/codeIsExit", method = RequestMethod.POST)
	public Boolean codeIsExit(@RequestParam("entityId") Long entityId, @RequestParam("entityCode") String entityCode) {
		boolean existCode = currentService.isExistCode(entityId, entityCode);
		boolean existCode2 = userService.isExistCode(entityId, entityCode);
		if(existCode || existCode2) {
			return true;
		}else {
			return false;
		}
	}

	@RequestMapping(value = "/getEmp", method = RequestMethod.POST)
	public JsonResult getEmp(@RequestParam(name = "organizationId", required = false) Long organizationId,
			@RequestParam(name = "stationId", required = false) Long stationId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "isExit") boolean isExit,
			@RequestParam(value = "keyword", required = false) String keyword) {
		PageHelper.startPage(page, pageSize);
		List<EmployeeEntity> emp = this.currentService.getEmp(organizationId, stationId, isExit, keyword);
		PageInfo pageList = new PageInfo(emp, this.getNavigatePages());
		return JsonResult.success(pageList);
	}
	
	@RequestMapping(value = "/getEmployeeEntity", method = RequestMethod.POST)
	public JsonResult getEmployeeEntity(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		PageHelper.startPage(page, pageSize);
		List<EmployeeEntity> emp = this.currentService.getEmployeeEntity(keyword);
		PageInfo pageList = new PageInfo(emp, this.getNavigatePages());
		return JsonResult.success(pageList);
	}
}
