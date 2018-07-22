package com.river.ms.system.controller;



import java.util.ArrayList;
import java.util.Date;
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
import com.river.core.controller.ControllerBase;
import com.river.core.helper.DateHelper;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.StationEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.StationService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/station")
public class StationController extends ControllerBase<StationService, StationEntity> {
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
	StationService currentService;
	/**
	 * 
	 * @return
	 */
	public StationService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult stationCreate(@Valid StationEntity stationEntity, BindingResult res, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(stationEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, stationEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		stationEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(stationEntity);
		String[] employeeIds = request.getParameterValues("employees");
		List<StationEmployeeEntity> employees = new ArrayList<StationEmployeeEntity>();
		if (result != 0) {
			if (employeeIds != null) {
				for (int i = 0; i < employeeIds.length; i ++) {
					StationEmployeeEntity stationEmployeeEntity = new StationEmployeeEntity();
					stationEmployeeEntity.setEmployeeId(Long.parseLong(employeeIds[i]));
					stationEmployeeEntity.setStationId(result);
					employees.add(stationEmployeeEntity);
				}
				currentService.addEmployees(employees);
			}
		
			logger.debug("station插入成功");
			return Success(stationEntity);
		} else {
			logger.debug("station插入失败");
			return ErrorResult.STATION_INSERT_FAIL;
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult stationUpdate(@Valid StationEntity stationEntity, BindingResult res, HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		StationEntity entity = currentService.getById(stationEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.STATION_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.STATION_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.ROLE_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(stationEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(stationEntity.getEntityId(), stationEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		
		String[] employeeIds = request.getParameterValues("employees");
		List<StationEmployeeEntity> employees = new ArrayList<StationEmployeeEntity>();
		if (employeeIds != null) {
			for (int i = 0; i < employeeIds.length; i ++) {
				StationEmployeeEntity stationEmployeeEntity = new StationEmployeeEntity();
				stationEmployeeEntity.setEmployeeId(Long.parseLong(employeeIds[i]));
				stationEmployeeEntity.setStationId(stationEntity.getEntityId());
				employees.add(stationEmployeeEntity);
			}
			currentService.addEmployees(employees);
		}
		ObjectHelper.Copy(stationEntity, entity);
		boolean	result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("station更新成功");
			return Success(entity);
		} else {
			logger.debug("station更新失败");
			return ErrorResult.STATION_UPDATE_FAIL;
		}
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public JsonResult stationSearch(@RequestParam(value="keyWords",defaultValue="") String keyWords,
								   @RequestParam(value="entityCode",defaultValue="") String entityCode,
			                       @RequestParam(value="beginTime",defaultValue="") String beginTime, 
			                       @RequestParam(value="endTime",defaultValue="")String endTime,
			                       @RequestParam(value="page",defaultValue="1") Integer page,
			                       @RequestParam(value="pageSize",defaultValue="10") Integer pageSize) {
		Date begin = DateHelper.stringToDate(beginTime,"yyyy-MM-dd");
		Date end = DateHelper.stringToDate(endTime,"yyyy-MM-dd");
		PageHelper.startPage(page, pageSize);
		List<StationEntity> stationList = currentService.search(keyWords,entityCode,begin, end);
		PageInfo pageList = new PageInfo(stationList, this.getNavigatePages());
		if (pageList != null) {
			logger.debug("岗位查询成功");
			return Success(pageList);
		} else {
			logger.debug("不存在要查询的岗位");
			return ErrorResult.STATION_NOT_EXIST;
		}
	}
	
	//插入角色功能，与表sys_ref_roleFunction关联
	@RequestMapping(value="/{stationId}/employee/insert", method = RequestMethod.POST)
	public JsonResult insertEmployee(@PathVariable("stationId") long stationId,
                                     @RequestParam(value="employeeId[]" ,defaultValue = "") long[] stationEmployeeId) {
		
		System.out.println("stationId= " + stationId);
		if (stationEmployeeId != null) {
			List<StationEmployeeEntity> employees = new ArrayList<StationEmployeeEntity>();
			for (int i = 0; i < stationEmployeeId.length; i++) {
				System.out.println("employeeId: " +stationEmployeeId[i]);
				StationEmployeeEntity stationEmployeeEntity = new StationEmployeeEntity();
				stationEmployeeEntity.setEmployeeId(stationEmployeeId[i]);
				stationEmployeeEntity.setStationId(stationId);
				employees.add(stationEmployeeEntity);
			}
			boolean flag = currentService.addEmployees(employees);
			if (flag) {
				System.out.println("添加权限功能成功");
				return Success(employees);
			} else {
				System.out.println("添加权限功能失败");
				return  ErrorResult.STATION_EMPLOYEE_INSERT_FAIL;
			}
		}
		return ErrorResult.ROLE_FUNCTION_INSERT_FAIL;
	}
	@RequestMapping(value="/{stationId}/employee",method = RequestMethod.GET)
	public JsonResult listEmployees(@PathVariable("stationId") long stationId) {
		List<StationEmployeeEntity> stationEmployee = currentService.listStationEmployees(stationId);
		if (stationEmployee != null) {
			/*for (StationEmployeeEntity stationEmployeeEntity : stationEmployee) {
				System.out.println(stationEmployeeEntity.getEntityTitle());
			}*/
			System.out.println(stationEmployee.size());
			return Success(stationEmployee);
		} else {
			return ErrorResult.STATION_EMPLOYEE_NOT_EXIST;
		}
	}
	//删除stationId下的某个employeeId
	@RequestMapping(value="/{stationId}/employee/remove", method = RequestMethod.POST)
	public JsonResult removeStationEmployee(@PathVariable("stationId") long stationId,
			                         @RequestParam(value="stationEmployeeId[]" ,defaultValue = "") long[] stationEmployeeId) {
		System.out.println("stationId= " + stationId);
		System.out.println("employeeId= " + stationEmployeeId);
		List<Long> employeeIds = new ArrayList<Long>();
		for (long id : stationEmployeeId) {
			employeeIds.add(id);
		}
		boolean del = currentService.removeStationEmployee(stationId, employeeIds);
		System.out.println(del);
		if (del) {
			System.out.println("员工岗位删除成功");
			return Success(del);
		} else {
			System.out.println("员工岗位删除失败");
			return ErrorResult.STATION_EMPLOYEE_REMOVE_FAIL;
		}
	}
		//禁用stationId下的某个employeeId
	@RequestMapping(value="/{stationId}/employee/disable", method = RequestMethod.POST)
	public JsonResult disableStationEmployee(@PathVariable("stationId") long stationId,
                                      @RequestParam(value="stationEmployeeId[]" ,defaultValue = "") long[] stationEmployeeId) {
		System.out.println("stationId= " + stationId);
		System.out.println("employeeId= " + stationEmployeeId);
		List<Long> employeeIds = new ArrayList<Long>();
		for (long id : stationEmployeeId) {
			employeeIds.add(id);
		}
		boolean disable = currentService.disableStationEmployee(stationId,employeeIds);
		if (disable) {
			System.out.println("员工岗位禁用成功");
			return Success(disable);
		} else {
			System.out.println("员工岗位禁用失败");
			return ErrorResult.STATION_EMPLOYEE_DISABLE_FAIL;
		}
	}
	@RequestMapping(value="/{stationId}/selectableEmployee", method = RequestMethod.POST)
	public JsonResult selectableEmployees(@RequestParam(value="page",defaultValue="1") Integer page,
										  @RequestParam(value="pageSize",defaultValue="5") Integer pageSize,
										  @PathVariable("stationId") long stationId,
										  @RequestParam(value="keyWords",defaultValue="") String keyWords) {
		PageHelper.startPage(page, pageSize);
		if (stationId == 0) {
			return ErrorResult.EMPLOYEE_NOT_EXIST;
		}
		List<EmployeeEntity> employees = currentService.selectableEmployees(stationId,keyWords);
		PageInfo pageList = new PageInfo(employees,this.getNavigatePages());
		System.out.println(employees.size());
		if (pageList != null) {
			return Success(pageList);
		} else {
			System.out.println("不存在可选的员工信息");
			return ErrorResult.EMPLOYEE_NOT_EXIST;
		}
	}
}
