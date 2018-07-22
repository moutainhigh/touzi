package com.river.ms.business.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectData;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectDataService;

/**
 * <p>
 * 可研数据录入 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectData")
public class ProjectDataAction {

	@Autowired
	MPProjectDataService service;

	/**
	 * 可研数据录入添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectData", method = RequestMethod.POST)
	public JsonResult insertProjectData(HttpServletRequest request, @RequestBody List<ProjectData> entitys) {
		UserEntity user = SessionUtils.getUser(request);
		for (ProjectData p : entitys) {
			p.setItcode(user.getEntityCode());
			p.setResourceId(user.getResId());
			p.setCreateTime(new Date());
		}
		boolean insert = this.service.insertBatch(entitys);
		if (insert) {
			return JsonResult.success(entitys);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 可研数据录入查看
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/selectProjectData", method = RequestMethod.POST)
	public JsonResult selectProjectData(HttpServletRequest request, ProjectData entity,
			@RequestParam(name = "ids[]") List<Long> ids) {
		List<ProjectData> selectByMap = this.service.getProjectData(entity, ids);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 导入excel可研预测数据
	 * 
	 * @param request
	 * @param file
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/import/IndexAndIdicatorData", method = RequestMethod.POST)
	public JsonResult IndexAndIdicatorData(HttpServletRequest request,
			@RequestParam("projectDataFile") MultipartFile file, @RequestParam("projectId") Long projectId) {
		try {
			UserEntity user = SessionUtils.getUser(request);
			service.insertBatchByExcelTempl(file, projectId, user);
		} catch (Exception e) {
			return ErrorResult.IMPORT_INDEX_IDICATOR_DATA_ERROE;
		}
		return JsonResult.SUCCESS;
	}

	/**
	 * 导入excel实际运营数据
	 * 
	 * @param request
	 * @param file
	 *            上传的文件
	 * @param projectId
	 *            项目ID
	 * @param type
	 *            1-年度数据，2-季度数据，3-月数据
	 * @param year
	 * entityType
	 * 0-可研预测1投后预测2-计划3-实际运营数据4-投后评价数据5-自评价数据
	 * @return
	 */
	@RequestMapping(value = "/importOperationalData", method = RequestMethod.POST)
	public JsonResult importOperationalData(HttpServletRequest request,
			@RequestParam("projectDataFile") MultipartFile file, @RequestParam("projectId") Long projectId,
			@RequestParam("type") Integer type, @RequestParam("year") Integer year,
			@RequestParam("entityType") Integer entityType) {
		try {
			UserEntity user = SessionUtils.getUser(request);
			service.insertBatchByExcelTempl(file, projectId, user, type, year, entityType);
		} catch (Exception e) {
			return ErrorResult.IMPORT_INDEX_IDICATOR_DATA_ERROE;
		}
		return JsonResult.SUCCESS;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public JsonResult test(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		try {
			String items = String.valueOf(params.get("entitys"));  
			Integer id = Integer.parseInt(params.get("id").toString());  
			ObjectMapper mapper = new ObjectMapper();  
	        List<ProjectData> itemsList = mapper.readValue(items, new TypeReference<List<ProjectData>>(){});  
			System.out.println(itemsList);
			System.out.println(id);
		} catch (Exception e) {
			return ErrorResult.IMPORT_INDEX_IDICATOR_DATA_ERROE;
		}
		return JsonResult.SUCCESS;
	}
	
	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	public JsonResult test1(HttpServletRequest request,
			@RequestParam("entitys") String entitys) {
		try {
			String items = entitys; 
			ObjectMapper mapper = new ObjectMapper();  
	        List<ProjectData> itemsList = mapper.readValue(items, new TypeReference<List<ProjectData>>(){});  
			System.out.println(itemsList);
		} catch (Exception e) {
			return ErrorResult.IMPORT_INDEX_IDICATOR_DATA_ERROE;
		}
		return JsonResult.SUCCESS;
	}
}
