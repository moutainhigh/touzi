package com.river.ms.project.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.business.EnumRole;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.PageBean;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectNode;
import com.river.ms.project.entity.ProjectNodeRole;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.entity.ProjectStageUserRole;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectCodeService;
import com.river.ms.project.service.MPProjectListService;
import com.river.ms.project.service.MPProjectNodeRoleService;
import com.river.ms.project.service.MPProjectNodeService;
import com.river.ms.project.service.MPProjectStageService;
import com.river.ms.project.service.MPResRefRoleOrgService;
import com.river.ms.project.service.feign.impl.ToBusinessImpl;
import com.river.ms.project.service.feign.impl.ToResImpl;
import com.river.ms.project.service.feign.impl.ToSystemImpl;

/**
 * <p>
 * 项目信息列表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectList")
public class ProjectListAction {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MPProjectListService service;

	@Autowired
	private MPProjectStageService projectStageService;

	@Autowired
	private ToBusinessImpl toBusiness;

	@Autowired
	private ToSystemImpl toSystemImpl;

	@Autowired
	private ToResImpl toRes;

	@Autowired
	private MPProjectNodeService projectNodeService;

	@Autowired
	private MPProjectNodeRoleService projectNodeRoleService;

	@Autowired
	private MPProjectCodeService projectCodeService;

	@Autowired
	MPResRefRoleOrgService resRefRoleOrgService;

	/**
	 * 项目列表综合查询
	 * 
	 * @param request
	 * @param keyword
	 *            关键字
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页多少条
	 * @param projectStateStart
	 *            项目阶段开始标记
	 * @param projectStateEnd
	 *            项目阶段结束标记
	 * @param createTimeStart
	 *            创建时间-开始时间
	 * @param createTimeEnd
	 *            创建时间-结束时间
	 * @param ownerGroup[]
	 *            行业公司编码数组
	 * @param moneyStart
	 *            投资额-开始数值
	 * @param moneyEnd
	 *            投资额-结束数字
	 * @return
	 */
	@RequestMapping(value = "/getMyProjectList", method = RequestMethod.POST)
	public JsonResult getMyProjectList(HttpServletRequest request,
			@RequestParam(name = "isFocusOn", required = false, defaultValue = "false") Boolean isFocusOn,
			@RequestParam(name = "isParticipation", required = false, defaultValue = "false") Boolean isParticipation,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "projectStateStart", required = false, defaultValue = "0") Integer projectStateStart,
			@RequestParam(value = "projectStateEnd", required = false, defaultValue = "100") Integer projectStateEnd,
			@RequestParam(value = "createTimeStart", required = false) Date createTimeStart,
			@RequestParam(value = "createTimeEnd", required = false) Date createTimeEnd,
			@RequestParam(value = "ownerGroup[]", required = false) List<String> ownerGroup,
			@RequestParam(value = "moneyStart", required = false) BigDecimal moneyStart,
			@RequestParam(value = "moneyEnd", required = false) BigDecimal moneyEnd,
			@RequestParam(value = "orderStr", required = false) String orderStr) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (user == null) {
			return JsonResult.AUTHORIZE_FAILURE;
		}
		Boolean isAll = false;
		Boolean isIncludeOther = false;
		List<String> includeSubordinateGroupCode = new ArrayList<>();
		List<String> notIncludeSubordinateGroupCode = new ArrayList<>();
		if(!isParticipation) {
			Map<String, Boolean> resRefRoleOrg = this.resRefRoleOrgService.getResRefRoleOrg(user);
			if (resRefRoleOrg == null || resRefRoleOrg.isEmpty()) {
				isIncludeOther = false;
			} else {
				isIncludeOther = true;
				if (resRefRoleOrg.isEmpty()) {
					isAll = true;
				} else {
					Set<Entry<String, Boolean>> entrySet = resRefRoleOrg.entrySet();
					for (Entry<String, Boolean> entry : entrySet) {
						if (entry.getValue()) {
							includeSubordinateGroupCode.add(entry.getKey());
						} else {
							notIncludeSubordinateGroupCode.add(entry.getKey());
						}
					}
				}
			}
			if (includeSubordinateGroupCode != null && includeSubordinateGroupCode.size() > 0) {
				List<String> orgCodes = toSystemImpl.getOrgCodes(headerMap, includeSubordinateGroupCode);
				includeSubordinateGroupCode.clear();
				notIncludeSubordinateGroupCode.addAll(orgCodes);
			}
		}
		PageHelper.startPage(page, pageSize);
		List<ProjectList> selectList = this.service.getMyProjectList(isFocusOn, user.getEntityCode(), user.getResId(),
				isAll, isIncludeOther, includeSubordinateGroupCode, notIncludeSubordinateGroupCode, keyword,
				projectStateStart, projectStateEnd, createTimeStart, createTimeEnd, ownerGroup, moneyStart, moneyEnd,
				orderStr);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(selectList, 5);
		return JsonResult.successPage(selectList, pageInfo);
	}

	/**
	 * 查询项目列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectList", method = RequestMethod.POST)
	public JsonResult getProjectList(HttpServletRequest request,
			@RequestParam(name = "keyWord", required = false) String keyWord,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "projectState", required = false) Integer projectState,
			@RequestParam(value = "projectState2", required = false) Integer projectState2) {
		PageHelper.startPage(page, pageSize);
		List<ProjectList> projectList = this.service.getProjectList(keyWord, projectState, projectState2);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(projectList, 5);
		return JsonResult.successPage(projectList, pageInfo);
	}

	/**
	 * 根据项目阶段查找项目
	 * 
	 * @param request
	 * @param projectState
	 * @return
	 */
	@RequestMapping(value = "/getProjectListByState/{projectState}", method = RequestMethod.POST)
	public JsonResult getProjectByState(HttpServletRequest request,
			@PathVariable(value = "projectState") Integer projectState,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		UserEntity user = SessionUtils.getUser(request);
		String state2 = request.getParameter("projectState2");
		Integer projectState2 = null;
		if (state2 != null && !state2.isEmpty())
			projectState2 = Integer.valueOf(state2);
		PageHelper.startPage(page, pageSize);
		List<ProjectList> projectList = this.service.getProjectByState(projectState, user.getEntityCode(),
				projectState2);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(projectList, 5);
		return JsonResult.successPage(projectList, pageInfo);
	}

	/**
	 * 
	 * @param request
	 * @param projectState
	 * @param projectState2
	 * @return
	 */
	@RequestMapping(value = "/getProjectListByStateScope", method = RequestMethod.POST)
	public JsonResult getProjectByStateScope(HttpServletRequest request,
			@RequestParam(value = "projectState") Integer projectState,
			@RequestParam(value = "projectState2") Integer projectState2,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ProjectList> projectList = this.service.getProjectByStateScope(projectState, projectState2);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(projectList, 5);
		return JsonResult.successPage(projectList, pageInfo);
	}

	/**
	 * 
	 * @param request
	 * @param projectState
	 * @param projectState2
	 * @return
	 */
	@RequestMapping(value = "/getProjectListByQZTJLSFKSH", method = RequestMethod.POST)
	public JsonResult getProjectListByQZTJLSFKSH(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null) {
			return JsonResult.FAILURE;
		}
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		String groupCode = "";
		for (StationEmployeeEntity s : stationEmployees) {
			if (s.getIsChief() == 1) {
				groupCode = s.getOrganization().getEntityCode();
				break;
			}
		}
		if (groupCode == null || groupCode.equals("")) {
			return JsonResult.FAILURE;
		}
		PageHelper.startPage(page, pageSize);
		List<ProjectList> projectList = this.service.getProjectListByQZTJLSFKSH(groupCode);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(projectList, 5);
		return JsonResult.successPage(projectList, pageInfo);
	}

	/**
	 * 获取当前用户参与过的项目列表
	 * 
	 * @param request
	 * @param projectState
	 * @param projectState2
	 * @return
	 */
	@RequestMapping(value = "/getMyProjectListByStateScope", method = RequestMethod.POST)
	public JsonResult getMyProjectByStateScope(HttpServletRequest request,
			@RequestParam(value = "projectState") Integer projectState,
			@RequestParam(value = "projectState2") Integer projectState2,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		Integer bPage = 0;
		if ("1".equals(request.getParameter("bPage")))
			bPage = 1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("projectState", projectState);
		map.put("projectState2", projectState2);
		map.put("itcode", user.getEntityCode());
		map.put("bPage", bPage);
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("keyword", request.getParameter("keyword"));
		map.put("total", 0);
		// 这里需要判断用户的身份，根据角色来筛选
		List<ProjectList> projectList = this.service.getMyProjectByStateScope(map);
		PageBean pages = new PageBean(projectList, 5, page, pageSize, Long.parseLong(map.get("total").toString()));
		return JsonResult.successPage(projectList, pages);
	}

	/**
	 * 获取用户参与过的项目
	 * 
	 * @param request
	 * @param projectState
	 * @param projectState2
	 * @return
	 */
	@RequestMapping(value = "/getProjectByStateScopeByResItCode", method = RequestMethod.POST)
	public JsonResult getProjectByStateScopeByResItCode(HttpServletRequest request,
			@RequestParam(value = "projectState") Integer projectState,
			@RequestParam(value = "projectState2") Integer projectState2, @RequestParam(value = "itcode") String itcode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "bPage", defaultValue = "0", required = false) Integer bPage) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("projectState", projectState);
		map.put("projectState2", projectState2);
		map.put("itcode", itcode);
		map.put("bPage", bPage);
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("keyword", request.getParameter("keyword"));
		map.put("total", 0);
		// 这里需要判断用户的身份，根据角色来筛选
		List<ProjectList> projectList = this.service.getMyProjectByStateScope(map);
		PageBean pages = new PageBean(projectList, 5, page, pageSize, Long.parseLong(map.get("total").toString()));
		return JsonResult.successPage(projectList, pages);
	}

	/**
	 * 查询单个项目信息
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getProjectById", method = RequestMethod.POST)
	public JsonResult getProjectById(@RequestParam(value = "entityId") Long entityId) {
		ProjectList selectById = this.service.getProjectListById(entityId);
		return JsonResult.success(selectById);
	}

	/**
	 * 修改项目
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	public JsonResult updateProject(HttpServletRequest request, @Valid ProjectList project, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long existTitle = this.service.existTitle(project.getEntityId(), project.getEntityTitle(),
				project.getEntityAb());
		if (existTitle != null && existTitle > 0) {
			return JsonResult.failure("项目信息重复，请检查后重新提交！");
		}
		boolean updateById = this.service.updateById(project);
		return JsonResult.success(updateById);
	}

	/**
	 * 添加项目
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/insertProject", method = RequestMethod.POST)
	public JsonResult insertProject(HttpServletRequest request, @Valid ProjectList project, BindingResult res) {

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long existTitle = this.service.existTitle(-1l, project.getEntityTitle(), project.getEntityAb());
		if (existTitle != null && existTitle > 0) {
			return JsonResult.failure("项目信息重复，请检查后重新提交！");
		}
		String nextProjectNum = projectCodeService.getNextProjectNum();
		// 设置项目编号
		project.setEntityCode(nextProjectNum);
		// 项目状态
		project.setState(0);
		// 项目来源
		project.setSrc("0");
		project.setStageName("待立项");
		UserEntity user = SessionUtils.getUser(request);
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		for (StationEmployeeEntity s : stationEmployees) {
			if (s.getIsChief() == 1 || stationEmployees.size() == 1) {
				project.setInitiativeGroup(s.getOrganization().getEntityCode());
				project.setInitiativeGroupName(s.getOrganization().getEntityTitle());
				project.setOwnerGroup(s.getHangyegongsi().getEntityCode());
				project.setOwnerGroupName(s.getHangyegongsi().getEntityTitle());
			}
		}
		project.setCreateTime(new Date());
		project.setLeaderItcode(user.getEntityCode());
		project.setLeader(user.getEntityTitle());
		project.setState(1);

		boolean insert = this.service.insert(project);
		if (insert) {
			projectCodeService.saveProjectNum();
		}

		return JsonResult.success(project);
	}

	/**
	 * 设置项目状态
	 * 
	 * @param entityId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/setProjectState", method = RequestMethod.POST)
	public Boolean setProjectState(@RequestParam(value = "entityId") Long entityId,
			@RequestParam(value = "state") Integer state) {
		if (state.equals(0) || state.equals(-99)) {
			return true;
		}
		ProjectList selectById = this.service.selectById(entityId);
		if (selectById != null) {
			selectById.setState(state);
			boolean updateById = this.service.updateById(selectById);
			if (updateById) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获取项目状态
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getProjectState", method = RequestMethod.POST)
	public Integer getProjectState(@RequestParam(value = "entityId") Long entityId) {
		ProjectList selectById = this.service.selectById(entityId);
		return selectById.getState();
	}

	/**
	 * 设置项目阶段
	 * 
	 * @param entityId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/setProjectStage", method = RequestMethod.POST)
	public Boolean setProjectStage(@RequestParam(value = "entityId") Long entityId,
			@RequestParam(value = "stageCode") Long stageCode,
			@RequestParam(value = "stageName", required = false) String stageName) {
		ProjectList selectById = this.service.selectById(entityId);
		if (stageCode != 0) {
			ProjectStage selectById2 = this.projectStageService.selectById(stageCode);
			if (selectById != null) {
				selectById.setStageCode(selectById2.getEntityId());
				selectById.setStageName(selectById2.getEntityTitle());
			}
		} else {
			selectById.setStageCode(0l);
			if (stageName != null && !stageName.equals("")) {
				selectById.setStageName(stageName);
			} else {
				selectById.setStageName("");
			}
		}
		boolean updateById = this.service.updateById(selectById);
		if (updateById) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询单个项目阶段信息
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getProjectStage/{entityId}", method = RequestMethod.POST)
	public JsonResult getProjectStage(@PathVariable(value = "entityId") Long entityId) {
		ProjectList selectById = this.service.selectById(entityId);
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("entityCode", selectById.getStageCode());
		List<ProjectStage> selectByMap = this.projectStageService.selectByMap(columnMap);
		if (selectByMap != null && selectByMap.size() > 0) {
			return JsonResult.success(selectByMap.get(0));
		} else {
			return ErrorResult.PROJECT_STATE_NOT_EXIT;
		}

	}

	/**
	 * 根据项目阶段查找项目ids
	 * 
	 * @param request
	 * @param stageCode
	 * @return
	 */
	@RequestMapping(value = "/getProjectIds", method = RequestMethod.POST)
	public Map<Long, Long> getProjectIds(HttpServletRequest request,
			@RequestParam(value = "stageCode") List<Long> stageCode,
			@RequestParam(value = "roleCode") String roleCode) {
		UserEntity user = SessionUtils.getUser(request);
		Map<Long, Long> result = new HashMap<>();
		List<ProjectList> projectListByStageCodeIds = this.service.getProjectListByStageCodeIds(stageCode, roleCode,
				user);
		for (ProjectList projectList : projectListByStageCodeIds) {
			result.put(projectList.getEntityId(), null);
		}
		return result;
	}

	/**
	 * 获取当前登录用户的待办事项
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMyProjectToDo", method = RequestMethod.POST)
	public JsonResult getMyProjectToDo(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		Integer bPage = 0, page = 1, pageSize = 15;
		if ("1".equals(request.getParameter("bPage")))
			bPage = 1;
		try {
			page = Integer.getInteger(request.getParameter("page"));
			pageSize = Integer.getInteger(request.getParameter("pageSize"));
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itcode", user.getEntityCode());
		map.put("bPage", bPage);
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("total", null);
		map.put("keyword", request.getParameter("keyword") == null ? "" : request.getParameter("keyword"));
		List<ProjectList> todoList = this.service.getProjectTodoList(map);
		return JsonResult.success(todoList);
	}

	/**
	 * 根据当前登录用户获取用户能操作的所有项目列表
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getLeaderProjects", method = RequestMethod.POST)
	public JsonResult getMyTodos(HttpServletRequest request, @RequestParam("itcode") String itcode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ProjectList> selectList = this.service.getLeaderProjects(itcode);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(selectList, 5);
		return JsonResult.successPage(selectList, pageInfo);
	}

	/**
	 * 获取项目经理对应的项目列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectByLeaderItcode", method = RequestMethod.POST)
	public JsonResult getProjectByLeaderItcode(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		wrapper.eq("leaderItcode", user.getEntityCode());
		List<ProjectList> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}

	/**
	 * 项目经理项目统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectByLeaderItcodeCount", method = RequestMethod.POST)
	public Map<String, Object> getProjectByLeaderItcodeCount(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		wrapper.eq("leader_itcode", user.getEntityCode());
		List<ProjectList> selectList = this.service.selectList(wrapper);
		Map<String, Object> projectStatistics = this.service.getProjectStatistics(selectList);
		return projectStatistics;
	}

	/**
	 * 专家项目统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectByZhuanjiacodeCount", method = RequestMethod.POST)
	public Map<String, Object> getProjectByZhuanjiacodeCount(HttpServletRequest request) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		JsonResult projectsCountByResId = toBusiness.getProjectsCountByResId(headerMap);
		List<Long> data = (List<Long>) projectsCountByResId.getData();
		List<ProjectList> projectByProjectIds = new ArrayList<>();
		if (data != null && data.size() > 0) {
			projectByProjectIds = this.service.getProjectByProjectIds(data, null);
		}
		Map<String, Object> projectStatistics = this.service.getProjectStatistics(projectByProjectIds);
		return projectStatistics;
	}

	/**
	 * 获取项目统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectStatistics", method = RequestMethod.POST)
	public Map<String, Object> getProjectStatistics(HttpServletRequest request,
			@RequestParam(name = "hangyegongsi", required = false) String hangyegongsi,
			@RequestParam(name = "year", required = false) String year) {
		Map<String, Object> projectStatistics = this.service.getProjectStatistics(hangyegongsi, year);
		return projectStatistics;
	}

	/**
	 * 获取项目统计根据行业公司
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectStatisticsByOwnerGroup", method = RequestMethod.POST)
	public List<Map<String, Object>> getProjectStatisticsByOwnerGroup(HttpServletRequest request) {
		List<Map<String, Object>> result = new ArrayList<>();
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		wrapper.groupBy("owner_group");
		List<ProjectList> ownerGroupList = this.service.selectList(wrapper);
		if (ownerGroupList != null && !ownerGroupList.isEmpty()) {
			for (ProjectList pl : ownerGroupList) {
				List<Long> projectIdList = this.service.getEntityIdByOwnerGroup(pl.getOwnerGroup());
				Map<String, Object> columnMap = new HashMap<>();
				columnMap.put("ownerGroupName", pl.getOwnerGroupName());
				columnMap.put("projectNum", projectIdList.size());
				columnMap.putAll(
						this.toBusiness.projectKeypointStatistics(SessionUtils.getHeaderMap(request), projectIdList));
				// columnMap.put("projectList", projectList);
				result.add(columnMap);
			}
		}
		return result;
	}

	/**
	 * 查看关注项目列表详情
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectFocusonListDetial", method = RequestMethod.POST)
	public JsonResult getProjectFocusonListDetial(HttpServletRequest request) {
		List<ProjectList> projectList = null;
		try {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			Map<Long, Long> temp = this.toRes.selectProjectIdsFocusonList(SessionUtils.getHeaderMap(request));
			List<Long> ids = new ArrayList<>();
			for (Entry<Long, Long> entry : temp.entrySet()) {
				ids.add(entry.getKey());
			}
			if (ids != null && !ids.isEmpty()) {
				wrapper.in("entityId", ids);
				projectList = this.service.selectList(wrapper);
			}
		} catch (Exception e) {
			logger.debug("Query focus on project list error!");
			return ErrorResult.QUERY_PROJECT_DETIAL_ERROR;
		}

		return JsonResult.success(projectList);
	}

	/**
	 * 根据项目阶段查找项目ids
	 * 
	 * @param request
	 * @param stageCode
	 * @return
	 */
	@RequestMapping(value = "/getProjectIdsByUserIdAndUserRoles", method = RequestMethod.POST)
	public Map<Long, Long> getProjectIdsByUserIdAndUserRoles(HttpServletRequest request,
			@RequestParam(value = "role") String role, @RequestParam(value = "itCode") String itCode,
			@RequestParam(value = "resId") String resId) {
		Map<Long, Long> result = new HashMap<>();
		List<ProjectList> projectListByStageCodeIds = this.service.getProjectListByUserLole(role, itCode, resId);
		for (ProjectList projectList : projectListByStageCodeIds) {
			result.put(projectList.getEntityId(), projectList.getEntityId());
		}
		return result;
	}

	/**
	 * 根据项目id列表，获取项目信息
	 * 
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getProjectByProjectIds", method = RequestMethod.POST)
	public JsonResult getProjectByProjectIds(HttpServletRequest request, @RequestParam("ids[]") List<Long> ids,
			@RequestParam(name = "keyWord", required = false) String keyWord,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		List<ProjectList> selectList = new ArrayList<>();
		PageHelper.startPage(page, pageSize);
		if (ids != null && ids.size() > 0) {
			selectList = this.service.getProjectByProjectIds(ids, keyWord);
		}
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(selectList, 5);
		return JsonResult.successPage(selectList, pageInfo);
	}

	/**
	 * 根据项目id查找项目当前所处在的节点
	 */
	@RequestMapping(value = "/getProjectNodeByProjectId", method = RequestMethod.POST)
	public JsonResult getProjectNodeByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		ProjectList project = this.service.selectById(projectId);
		ProjectStage stage = this.projectStageService.selectById(project.getStageCode());
		ProjectNode node = this.projectNodeService.selectById(stage.getSrc());
		return JsonResult.success(node);
	}

	/**
	 * 根据项目ID和项目阶段获取可操作角色和用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRoleAndUserByProjectIdAndStage", method = RequestMethod.POST)
	public Map<Long, List<Long>> getRoleAndUserByProjectIdAndStage(HttpServletRequest request,
			@RequestParam("projectId") Long projectId) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		UserEntity user = SessionUtils.getUser(request);
		ProjectList project = this.service.selectById(projectId);
		ProjectStage stage = this.projectStageService.selectById(project.getStageCode());
		ProjectNode node = this.projectNodeService.selectById(stage.getSrc());
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("nodeId", node.getEntityId());
		List<ProjectNodeRole> ProjectNodeRole = this.projectNodeRoleService.selectByMap(columnMap);
		Map<Long, List<Long>> users = this.service.getUsers(ProjectNodeRole, projectId, headerMap, user);
		return users;
	}

	/**
	 * 根据项目ID获取项目阶段code
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getStageByProjectId", method = RequestMethod.POST)
	public String getStageByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		// if(projectId==null) return "null";
		ProjectList p = this.service.selectById(projectId);
		// if(p==null) return "project is null";
		ProjectStage stage = this.projectStageService.selectById(p.getStageCode());
		if (stage == null)
			return "0";
		return stage.getEntityCode();
	}

	/**
	 * 根据项目ID获取项目阶段id
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getStageIdByProjectId", method = RequestMethod.POST)
	public Long getStageIdByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		ProjectList p = this.service.selectById(projectId);
		return p.getStageCode();
	}

	/**
	 * 根据项目ID获取流程实例ID
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getLiuchengshiliIdByProjectId", method = RequestMethod.POST)
	public String getLiuchengshiliIdByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		ProjectList p = this.service.selectById(projectId);
		return p.getLiuchengshiliId();
	}

	/**
	 * 根据项目ID获取流程实例ID
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/setLiuchengshiliIdByProjectId", method = RequestMethod.POST)
	public Boolean setLiuchengshiliIdByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId,
			@RequestParam(name = "liuchengshiliId", required = false) String liuchengshiliId) {
		ProjectList p = this.service.selectById(projectId);
		boolean updateAllColumnById = false;
		if (p != null) {
			if (liuchengshiliId != null && !liuchengshiliId.equals("")) {
				p.setLiuchengshiliId(liuchengshiliId);
			} else {
				p.setLiuchengshiliId(null);
			}
			updateAllColumnById = this.service.updateAllColumnById(p);
		}
		return updateAllColumnById;
	}

	/**
	 * 获取专家能操作的项目列表
	 * 
	 * @param request
	 * @param projectId
	 * @param liuchengshiliId
	 * @return
	 */
	@RequestMapping(value = "/getExpertProjectList", method = RequestMethod.POST)
	public JsonResult getExpertProjectList(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "isAll", required = false, defaultValue = "true") Boolean isAll) {
		UserEntity user = SessionUtils.getUser(request);
		PageHelper.startPage(page, pageSize);
		List<ProjectList> expertProjectList = this.service.getExpertProjectList(user.getResId(), isAll);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(expertProjectList, 5);
		return JsonResult.successPage(expertProjectList, pageInfo);
	}

	/**
	 * 判断项目是否需要评审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/judgeProjectNoFollowApproval", method = RequestMethod.POST)
	public JsonResult judgeProjectNoFollowApproval(HttpServletRequest request,
			@RequestParam("projectId") Long projectId) {
		ProjectList project = this.service.selectById(projectId);
		if (project == null)
			return JsonResult.failure("project is null");
		ProjectStage stage = this.projectStageService.selectById(project.getStageCode());
		if (stage == null)
			return JsonResult.failure("stage is null.");
		EntityWrapper<ProjectStage> wrapper = new EntityWrapper<>();
		wrapper.eq("entityId", stage.getParentId());
		ProjectStage parentStage = this.projectStageService.selectOne(wrapper);
		// 以下代码硬编码，需要调整
		if (parentStage.getEntityCode().indexOf("TJSQ") > -1 || parentStage.getEntityCode().indexOf("XMBG") > -1) {
			return JsonResult.success(true);
		} else {
			return JsonResult.success(false);
		}
	}

	/**
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getFocusOn", method = RequestMethod.POST)
	public JsonResult getFocusOnProjectList(HttpServletRequest request, @RequestParam("itcode") String itcode,
			@RequestParam(value = "keyword", required = false) String keyword) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);

		List<ProjectList> list = this.service.getFocusOnProjectList(headerMap, itcode, keyword);

		return JsonResult.success(list);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMyFocusOn", method = RequestMethod.POST)
	public JsonResult getMyFocusOnProjectList(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.FAILURE;
		PageHelper.startPage(page, pageSize);
		List<ProjectList> list = this.service.getFocusOnProjectList(headerMap, user.getEntityCode(), keyword);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(list, 5);
		return JsonResult.successPage(list, pageInfo);
	}

	/**
	 * 根据项目状态，查询项目列表，非流程菜单使用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectListByState", method = RequestMethod.POST)
	public JsonResult getProjectListByState(HttpServletRequest request, @RequestParam("state") Long state) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("state", state);
		List<ProjectList> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 根据项目阶段查找项目（赋能群）
	 * 
	 * @param request
	 * @param projectState
	 * @return
	 */
	@RequestMapping(value = "/getProjectByStateFunengqun/{projectState}", method = RequestMethod.POST)
	public JsonResult getProjectByStateFunengqun(HttpServletRequest request,
			@PathVariable(value = "projectState") Integer projectState) {
		UserEntity user = SessionUtils.getUser(request);
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		String entityCode = new String();
		for (StationEmployeeEntity s : stationEmployees) {
			if (s.getIsChief() == 1) {
				entityCode = s.getHangyegongsi().getEntityCode();
			}
		}
		List<ProjectList> projectList = this.service.getProjectByStatefunengqun(projectState, entityCode);
		return JsonResult.success(projectList);
	}

	/**
	 * 获取我负责的项目数量
	 * 
	 * @param request
	 * @param leaderItcode
	 * @return
	 */
	@RequestMapping(value = "/getProjectCountxmjl", method = RequestMethod.POST)
	public JsonResult getProjectCount(HttpServletRequest request, @RequestParam(name = "itCode") String leaderItcode,
			@RequestParam(value = "projectState", required = false) Integer projectState,
			@RequestParam(value = "projectState2", required = false) Integer projectState2) {
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		wrapper.eq("leader_itcode", leaderItcode);

		if (projectState != null) {
			wrapper.ge("state", projectState);
		}

		if (projectState2 != null) {
			wrapper.le("state", projectState2);
		}

		int selectCount = this.service.selectCount(wrapper);
		return JsonResult.success(selectCount);
	}

	/**
	 * 获取需要上会的项目列表
	 * 
	 * @param request
	 * @param online
	 * @return
	 */
	@RequestMapping(value = "/getProjectListByCouncilType", method = RequestMethod.POST)
	public JsonResult getProjectListByCouncilType(HttpServletRequest request,
			@RequestParam(name = "online", required = false) Integer online) {
		List<ProjectList> projectListByCouncilType = this.service.getProjectListByCouncilType(request, online);
		return JsonResult.success(projectListByCouncilType);
	}

	/**
	 * 获取当前用户在项目中的角色
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getRoleByProjectId", method = RequestMethod.POST)
	public JsonResult getRoleByProjectId(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		if (projectId == null)
			return JsonResult.failure("项目ID不能为空");
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		ProjectList project = this.service.selectById(projectId);
		if (project == null)
			return JsonResult.failure("项目不存在");// 验证项目是否存在
		// 用户在项目中的身份
		ProjectStageUserRole stageRole = new ProjectStageUserRole();
		stageRole.setProjectId(projectId);
		stageRole.setStageId(project.getStageCode());
		stageRole.setStageCode(project.getStageName());// 这个有问题
		if (project.getLeaderItcode() == user.entityCode) {
			stageRole.addRole(EnumRole.Teameader.toString());
			return JsonResult.success(stageRole);// 项目经理
		}
		// 赋能群身份
		List<RoleEntity> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			RoleEntity role = null;
			for (int i = 0; i < roles.size(); i++) {
				role = roles.get(i);
				if (role.entityCode.equals(EnumRole.Thinktank.getRole())) {
					stageRole.addRole(EnumRole.Thinktank.toString());
					return JsonResult.success(stageRole);// 项目经理
				}
			}
		}
		// 专家身份
		// 普通会员身份
		// return JsonResult.success(EnumRole.Member);
		return JsonResult.failure("没有权限访问");
	}

	/**
	 * 验证项目阶段
	 * 
	 * @param entityId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/verifyStage", method = RequestMethod.POST)
	public Boolean verifyStage(HttpServletRequest request, @RequestParam(value = "entityId") Long entityId,
			@RequestParam(value = "stage", required = false, defaultValue = "0") Long stage) {
		Boolean result = false;
		UserEntity user = SessionUtils.getUser(request);
		if (user == null) {
			return result;
		}
		if (stage == 0) {
			ProjectList selectById = this.service.selectById(entityId);
			if (selectById == null) {
				return result;
			}
			if (user.getEntityCode().equals(selectById.getLeaderItcode())) {
				return true;
			}
		} else {
			Map<String, Object> projectRoleByItcode = this.service.getProjectRoleByItcode(entityId,
					user.getEntityCode());
			String isCurrent = projectRoleByItcode.get("isCurrent").toString();
			if (isCurrent.equals("1")) {
				result = true;
			}
		}

		/*
		 * // 获取当前项目可以操作的权限以及人员 Map<String, Object> headerMap =
		 * SessionUtils.getHeaderMap(request); ProjectList selectById =
		 * this.service.selectById(entityId); if (selectById != null &&
		 * selectById.getStageCode() != null && selectById.getStageCode() != 0) {
		 * ProjectStage projectStage =
		 * this.projectStageService.selectById(selectById.getStageCode()); ProjectNode
		 * node = this.projectNodeService.selectById(projectStage.getSrc()); Map<String,
		 * Object> columnMap = new HashMap<>(); columnMap.put("nodeId",
		 * node.getEntityId()); List<ProjectNodeRole> ProjectNodeRole =
		 * this.projectNodeRoleService.selectByMap(columnMap); Map<Long, List<Long>>
		 * users = this.service.getUsers(ProjectNodeRole, entityId, headerMap); //
		 * 验证当前用户有没有操作权限 if (selectById != null && selectById.getStageCode() != null) {
		 * if (selectById.getStageCode() == stage ||
		 * selectById.getStageCode().equals(stage)) { List<RoleEntity> roles =
		 * user.getRoles(); Set<Entry<Long, List<Long>>> entrySet = users.entrySet();
		 * for (Entry<Long, List<Long>> entry : entrySet) { for (RoleEntity r : roles) {
		 * if (r.getEntityId().equals(entry.getKey())) { List<Long> value =
		 * entry.getValue(); if (value != null && value.size() > 0) { for (Long l :
		 * value) { if (l.equals(user.getResId())) { result = true; } } } else { result
		 * = true; } } } } } } }
		 */
		return result;
	}

	/**
	 * 验证项目权限
	 * 
	 * @param request
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/verifyProjectAuthority", method = RequestMethod.POST)
	public Boolean verifyProjectAuthority(HttpServletRequest request,
			@RequestParam(value = "projectId") Long projectId) {
		Boolean result = false;
		UserEntity user = SessionUtils.getUser(request);
		if (user == null) {
			return result;
		}
		ProjectList project = this.service.selectById(projectId);
		if (project == null) {
			return result;
		}
		Map<String, Object> projectRoleByItcode = this.service.getProjectRoleByItcode(projectId, user.getEntityCode());
		String isCurrent = projectRoleByItcode.get("isCurrent").toString();
		if (isCurrent.equals("1")) {
			result = true;
			return result;
		}
		result = this.service.verifyProjectAuthority(request, projectId);
		return result;
	}

	/**
	 * 验证项目是否存在
	 * 
	 * @param value
	 *            内容
	 * @param type
	 *            类型 1-项目名称，2-项目简称 ,3-项目代号
	 * @return
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public JsonResult verify(@RequestParam(value = "value") String value,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "projectId", required = false, defaultValue = "-1") Long projectId) {
		Long existTitle = this.service.existTitle(projectId, value, null);
		if (existTitle != null && existTitle > 0) {
			return JsonResult.success(true);
		} else {
			return JsonResult.success(false);
		}
	}

	/**
	 * 获取项目的相关参数
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getProjectCategoryId2ById", method = RequestMethod.POST)
	public Map<String, Object> getProjectCategoryId2ById(@RequestParam(value = "entityId") Long entityId) {
		Map<String, Object> result = new HashMap<>();
		ProjectList selectById = this.service.getProjectListById(entityId);
		result.put("groupCategoryId", selectById.getCategoryId2());
		return result;
	}

	/**
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getMyProjectRole", method = RequestMethod.POST)
	public JsonResult getMyProjectRole(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		Map<String, Object> map = this.service.getProjectRoleByItcode(projectId, user.getEntityCode());
		return JsonResult.success(map);
	}

	/**
	 * 
	 * @param request
	 * @param keyword
	 *            搜索关键字
	 * @param bPage
	 *            是否分页
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页多少行
	 * @param bState
	 *            是否通过状态筛选
	 * @param state
	 *            起始状态
	 * @param endState
	 *            结束状态
	 * @param bInvest
	 *            是否通过金额筛选
	 * @param minInvest
	 *            最小金额
	 * @param maxInvest
	 *            最大金额
	 * @param groupCode
	 *            行业公司
	 * @param investType
	 *            投资类型
	 * @param projectType
	 *            项目类型
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public JsonResult query(HttpServletRequest request, @RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "bPage", defaultValue = "1") boolean bPage,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "bState", defaultValue = "0") boolean bState,
			@RequestParam(value = "state", defaultValue = "0") Integer state,
			@RequestParam(value = "endState", defaultValue = "100") Integer endState,
			@RequestParam(value = "bInvest", defaultValue = "0") boolean bInvest,
			@RequestParam(value = "minInvest", defaultValue = "0") BigDecimal minInvest,
			@RequestParam(value = "maxInvest", defaultValue = "999999999.99") BigDecimal maxInvest,
			@RequestParam(value = "groupCode", defaultValue = "0") String groupCode,
			@RequestParam(value = "investType", defaultValue = "") String investType,
			@RequestParam(value = "projectType", defaultValue = "") String projectType,
			@RequestParam(value = "year", defaultValue = "0") String year) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		// 分页
		map.put("bPage", bPage);
		map.put("page", page);
		map.put("pageSize", pageSize);
		// 阶段
		map.put("bState", bState);
		map.put("state", state);
		map.put("endState", endState);
		// 投资总额
		map.put("bInvest", bInvest);
		map.put("minInvest", minInvest);
		map.put("maxInvest", maxInvest);
		map.put("groupCode", groupCode);
		map.put("investType", investType);
		map.put("projectType", projectType);
		map.put("total", 0);
		map.put("myear", year);
		List<ProjectList> list = this.service.queryListBy(map);
		PageBean pages = new PageBean(list, 5, page, pageSize, Long.parseLong(map.get("total").toString()));
		return JsonResult.successPage(list, pages);
	}

	@RequestMapping(value = "/getMyProjectCount", method = RequestMethod.POST)
	public Map<String, Object> getMyProjectCount(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> map = new HashMap<>();
		map.put("itcode", user.getEntityCode());
		map.put("cost", null);
		map.put("total", null);
		map.put("totalIng", null);
		map.put("totalFinish", null);
		map.put("IRR", null);
		this.service.getMyProjectCount(map);
		return map;
	}

	/**
	 * 获取项目流程实例ID
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectProcessInstanceId", method = RequestMethod.POST)
	public String getProjectProcessInstanceId(HttpServletRequest request,
			@RequestParam(value = "projectId") Long projectId) {
		String processInstanceId = new String();
		if (projectId != null && projectId != 0) {
			ProjectList project = this.service.selectById(projectId);
			if (project != null && project.getLiuchengshiliId() != null) {
				processInstanceId = project.getLiuchengshiliId();
			}
		}
		return processInstanceId;
	}

	/**
	 * 项目综合评分
	 * 
	 * @param request
	 * @param projectId
	 * @param score
	 * @return
	 */
	@RequestMapping(value = "/setProjectScore", method = RequestMethod.POST)
	public JsonResult setProjectScore(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "score") BigDecimal score) {
		ProjectList project = new ProjectList();
		project.setEntityId(projectId);
		project.setScore(score);
		boolean updateById = this.service.updateById(project);
		return JsonResult.success(updateById);
	}

	/**
	 * 获取项目的节点node
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectNode", method = RequestMethod.POST)
	public JsonResult getProjectNode(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId) {
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目不存在！");
		}
		ProjectList selectById = this.service.selectById(projectId);
		if (selectById == null) {
			return JsonResult.failure("项目不存在！");
		}
		if (selectById.getStageCode() == null || selectById.getStageCode() == 0) {
			return JsonResult.failure("项目阶段不正确！");
		}
		String node = this.service.getProjectNode(projectId);
		return JsonResult.success(node);
	}

	/**
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getMyNoReply", method = RequestMethod.POST)
	public JsonResult getProjectNode(HttpServletRequest request,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		PageHelper.startPage(page, pageSize);
		List<ProjectList> list = this.service.getNoReplyProjects(user.getEntityCode(), keyword);
		PageInfo<ProjectList> pageInfo = new PageInfo<ProjectList>(list, 5);
		return JsonResult.successPage(list, pageInfo);
	}

	/**
	 * 获取偏差预警结果
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDeviation", method = RequestMethod.POST)
	public JsonResult getDeviation(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		List<ProjectList> list = this.service.getDeviation(user.getEntityCode());
		return JsonResult.success(list);
	}
}
