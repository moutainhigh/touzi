package com.river.ms.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectListService;
import com.river.ms.project.service.MPProjectStageService;
import com.river.ms.project.service.feign.impl.ToBusinessImpl;
import com.river.ms.project.service.impl.ProjectStageServiceDiy;

/**
 * <p>
 * 项目阶段 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectStage")
public class ProjectStageAction {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	ProjectStageServiceDiy service;
	
	@Autowired
	MPProjectListService projectListService;

	@Autowired
	ToBusinessImpl toBusiness;

	/**
	 * 查找项目阶段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult selectProjectNodeList(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectStage> projectStageList = null;
		try {
			List<RoleEntity> roles = user.getRoles();
			if (roles != null && !roles.isEmpty()) {
				projectStageList = this.service.getProjectStageByRoles(roles);
			}
		} catch (Exception e) {
			System.out.println(e);
			logger.debug("查找项目阶段列表失败");
			return ErrorResult.QUERY_USER_STAGE_ERROR;
		}
		if (projectStageList == null || projectStageList.isEmpty()) {
			return ErrorResult.USER_STAGE_NOT_EXIT;
		}
		logger.debug("查找项目阶段列表成功");
		return JsonResult.success(projectStageList);
	}

	/**
	 * 查找项目阶段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllProjectNode", method = RequestMethod.POST)
	public JsonResult getAllProjectNode() {
		Map<String, Object> columnMap = new HashMap<>();
		List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 查找项目阶段大阶段
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getParentProjectNode", method = RequestMethod.POST)
	public JsonResult getParentProjectNode(@RequestParam(name = "stageCode", required = true) Long stageCode) {
		if (stageCode != null && stageCode != 0) {
			Map<String, Object> columnMap = new HashMap<>();
			ProjectStage selectById = this.service.selectById(stageCode);
			if (selectById.getLevel() == 1) {
				columnMap.put("entityId", selectById.getParentId());
				List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
				selectById = selectByMap.get(0);
			}
			return JsonResult.success(selectById);
		} else {
			return ErrorResult.USER_STAGE_NOT_EXIT;
		}
	}

	@RequestMapping(value = "/getParentProjectNodeByFeign", method = RequestMethod.POST)
	public Map<Long, Long> getParentProjectNodeByFeign(@RequestParam("stageCode") Long stageCode) {
		Map<Long, Long> result = new HashMap<>();
		if (stageCode != null && stageCode != 0) {
			Map<String, Object> columnMap = new HashMap<>();
			ProjectStage selectById = this.service.selectById(stageCode);
			if (selectById.getLevel() == 1) {
				columnMap.put("entityId", selectById.getParentId());
				List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
				selectById = selectByMap.get(0);
			}
			result.put(selectById.getEntityId(), selectById.getEntityId());
		}
		return result;
	}

	/**
	 * 查找项目阶段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectStageIdByCode", method = RequestMethod.POST)
	public Map<Long, Long> getProjectStageIdByCode(@RequestParam("stageCode") String stageCode) {
		Map<Long, Long> result = new HashMap<>();
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("entityCode", stageCode);
		List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
		result.put(new Long(1), selectByMap.get(0).getEntityId());
		return result;
	}

	/**
	 * 查找项目阶段列表
	 * 此方法已废弃不用  20180129
	 * @return
	 */
	@RequestMapping(value = "/getProjectNextStage", method = RequestMethod.POST)
	public String getProjectNextStage(@RequestParam("stageCode") String stageCode) {
		if (stageCode.equals("LX006") || stageCode.equals("fnLX003")) {
			return "10";
		}
		if (stageCode.equals("KY007") || stageCode.equals("fnKY003")) {
			return "15";
		}
		if (stageCode.equals("TJ012") || stageCode.equals("fnTJ011")) {
			return "20";
		}
		if (stageCode.equals("TH004")) {
			return "50";
		}
		if (stageCode.equals("BG013") || stageCode.equals("fnBG011")) {
			return "0";
		}
		return null;
	}
	/**
	 * 
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getNextStage", method = {RequestMethod.POST,RequestMethod.GET})
	public JsonResult getNextStage(@RequestParam("stage") String stage) {
		ProjectStage item=this.service.getNextStage(stage);
		return JsonResult.success(item);
	}

	/**
	 * 查找项目阶段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllChildNodeById", method = RequestMethod.POST)
	public JsonResult getAllChildNodeById(HttpServletRequest request, @RequestParam("entityId") Long entityId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parentId", entityId);
		List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
		List<Long> ids = new ArrayList<>();
		for (ProjectStage s : selectByMap) {
			ids.add(s.getEntityId());
		}
		return JsonResult.success(ids);
	}

	/**
	 * 根据项目阶段ID查询项目阶段的code和项目阶段的title
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCodeAndTitleById", method = RequestMethod.POST)
	public Map<String, String> getCodeAndTitleById(HttpServletRequest request,
			@RequestParam("entityId") Long entityId) {
		Map<String, String> result = new HashMap<>();
		ProjectStage selectById = this.service.selectById(entityId);
		result.put(selectById.getEntityCode(), selectById.getEntityTitle());
		return result;
	}

	@RequestMapping(value = "/getCodeById", method = RequestMethod.POST)
	public JsonResult getCodeById(HttpServletRequest request, @RequestParam("entityId") Long entityId,
			@RequestParam(name = "projectId", required = false) Long projectId,
			@RequestParam(value = "flowType", required = false) Integer flowType) {
		if (entityId == null || entityId == 0) {
			if (projectId == null || projectId == 0) {
				return JsonResult.FAILURE;
			}
			ProjectList project = projectListService.selectById(projectId);
			if (project == null || project.getCategoryId2() == null || project.getCategoryId2() == 0) {
				return JsonResult.FAILURE;
			}
			Long categoryId = project.getCategoryId2();
			if (entityId == null || entityId == 0) {
				Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
				Long flowId = toBusiness.getFlowId(headerMap, categoryId, flowType);
				EntityWrapper<ProjectStage> wrapper = new EntityWrapper<>();
				wrapper.eq("level", 0);
				wrapper.eq("parentId", 0);
				wrapper.eq("flowId", flowId);
				List<ProjectStage> selectList = service.selectList(wrapper);
				if(selectList != null && selectList.size()>0) {
					entityId = selectList.get(0).getEntityId();
				}
			}
		}
		ProjectStage selectById = this.service.selectById(entityId);
		return JsonResult.success(selectById);
	}
}
