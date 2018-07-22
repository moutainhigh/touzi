package com.river.ms.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.core.entity.OrganizationEntity;
import com.river.core.entity.OrganizationStationEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.OrganizationService;
import com.river.ms.system.service.StationService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/organization")
public class OrganizationController extends ControllerTreeBase<OrganizationService, OrganizationEntity> {
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
	OrganizationService currentService;
	@Autowired
	StationService stationService;

	/**
	 * 
	 * @return
	 */
	public OrganizationService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult organizationCreate(@Valid OrganizationEntity organizationEntity, BindingResult res,
			HttpServletRequest request) {

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(organizationEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, organizationEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		organizationEntity.setEntityGUID(StringHelper.generateGUID());

		/*String stationIds[] = request.getParameterValues("stations");
		String employeeIds[] = request.getParameterValues("employees");*/

		System.out.println("------");
		long result = currentService.insert(organizationEntity);

		/*if (result != 0) {
			System.out.println("resulttrue");

			System.out.println(result);
			List<OrganizationStationEntity> stations = new ArrayList<OrganizationStationEntity>();
			List<StationEmployeeEntity> employees = new ArrayList<StationEmployeeEntity>();
			System.out.println(stationIds);
			// System.out.println(stationIds[0]);
			if (stationIds != null) {
				for (int i = 0; i < stationIds.length; i++) {
					System.out.println(stationIds[i]);
					OrganizationStationEntity organizationStationEntity = new OrganizationStationEntity();
					organizationStationEntity.setStationId(Long.parseLong(stationIds[i]));
					organizationStationEntity.setOrganizationId(result);
					for (int j = 0; j < employeeIds.length; j++) {
						StationEmployeeEntity stationEmployeeEntity = new StationEmployeeEntity();
						stationEmployeeEntity.setOrganizationId(result);
						stationEmployeeEntity.setStationId(Long.parseLong(stationIds[i]));
						stationEmployeeEntity.setEmployeeId(Long.parseLong(employeeIds[j]));
						employees.add(stationEmployeeEntity);
					}
					stationService.addEmployees(employees);
					stations.add(organizationStationEntity);
				}

				currentService.addStations(stations);
			}
		}
		System.out.println("resultfalse");

		logger.debug(organizationEntity);*/
		if (result == 0) {
			logger.debug("组织插入失败");
			return ErrorResult.ORGANIZATION_INSERT_FAIL;
		} else {
			System.out.println(result);
			logger.debug("组织插入成功");
			return Success(organizationEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult organizationUpdate(@Valid OrganizationEntity organizationEntity, BindingResult res,
			HttpServletRequest request) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}

		OrganizationEntity entity = currentService.getById(organizationEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.ORGANIZATION_NOT_EXIST;
		}
		System.out.println("是否被删除：" + entity.getIsDelete());
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.ORGANIZATION_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.ORGANIZATION_DISABLE;
		}
		if (StringValidator.isNullOrEmpty(organizationEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(organizationEntity.getEntityId(), organizationEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		String[] stations = request.getParameterValues("stations");
		List<OrganizationStationEntity> stationIds = new ArrayList<OrganizationStationEntity>();
		if (stations != null) {
			for (int i = 0; i < stations.length; i++) {
				OrganizationStationEntity organizationStationEntity = new OrganizationStationEntity();
				organizationStationEntity.setOrganizationId(organizationEntity.getEntityId());
				organizationStationEntity.setStationId(Long.parseLong(stations[i]));
				stationIds.add(organizationStationEntity);
			}
			currentService.addStations(stationIds);
		}

		ObjectHelper.Copy(organizationEntity, entity);
		boolean result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("组织更新成功");
			return Success(entity);
		} else {
			logger.debug("组织更新失败");
			return ErrorResult.ORGANIZATION_UPDATE_FAIL;
		}
	}

	/**
	 * 获取行业公司
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getHangyegongsi", method = RequestMethod.POST)
	public JsonResult getHangyegongsi() {
		Map<String, Object> map = new HashMap<>();
		map.put("organType", 2);
		map.put("isDelete", 0);
		map.put("isDisable", 0);
		List<OrganizationEntity> queryBy = this.currentService.queryBy(map);
		return JsonResult.success(queryBy);
	}

	@RequestMapping(value = "/insertOrgAndStation", method = RequestMethod.POST)
	public JsonResult insertOrgAndStation(@RequestParam("stationIds[]") List<Long> stationIds,
			@RequestParam("organizationIds[]") List<Long> organizationIds) {
		if (stationIds != null && stationIds.size() > 0 && organizationIds != null && organizationIds.size() > 0) {
			this.currentService.insertOrgAndStation(stationIds, organizationIds);
			return JsonResult.SUCCESS;
		}
		return JsonResult.FAILURE;
	}

	@RequestMapping(value = "/getOrganization", method = RequestMethod.POST)
	public JsonResult getOrganization(@RequestParam("organizationId") Long organizationId) {
		OrganizationEntity organizationEntity = this.currentService.getOrganizationEntity(organizationId);
		return JsonResult.success(organizationEntity);
	}

	@RequestMapping(value = "/insertOrgAndStationAndEmp", method = RequestMethod.POST)
	public JsonResult insertOrgAndStationAndEmp(@RequestParam("stationId") Long stationId,
			@RequestParam("organizationId") Long organizationId, @RequestParam("empIds[]") List<Long> empIds) {
		this.currentService.insertOrgAndStationAndEmp(stationId, organizationId, empIds);
		return JsonResult.SUCCESS;
	}

	@RequestMapping(value = "/deleteOrgAndStationAndEmp", method = RequestMethod.POST)
	public JsonResult deleteOrgAndStationAndEmp(@RequestParam("stationId") Long stationId,
			@RequestParam("organizationId") Long organizationId, @RequestParam("empIds[]") List<Long> empIds) {
		this.currentService.deleteOrgAndStationAndEmp(stationId, organizationId, empIds);
		return JsonResult.SUCCESS;
	}

	@RequestMapping(value = "/setOrgAndStationAndEmpToChief", method = RequestMethod.POST)
	public JsonResult setOrgAndStationAndEmpToChief(@RequestParam("stationId") Long stationId,
			@RequestParam("organizationId") Long organizationId, @RequestParam("empId") Long empId) {
		this.currentService.setOrgAndStationAndEmpToChief(stationId, organizationId, empId);
		return JsonResult.SUCCESS;
	}

	@RequestMapping(value = "/getOrgCodes", method = RequestMethod.POST)
	public List<String> getOrgCodes(HttpServletRequest request,@RequestParam("orgCodes") List<String> orgCodes) {
		List<String> orgCodes2 = this.currentService.getOrgCodes(orgCodes);
		return orgCodes2;
	}
}
