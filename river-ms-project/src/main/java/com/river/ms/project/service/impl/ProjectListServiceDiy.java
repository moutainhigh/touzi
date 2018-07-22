package com.river.ms.project.service.impl;

import com.river.core.business.EnumProjectState;
import com.river.core.business.EnumRole;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectNodeRole;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.mapper.ProjectListDao;
import com.river.ms.project.service.MPProjectListService;
import com.river.ms.project.service.MPResRefRoleOrgService;
import com.river.ms.project.service.feign.ToSystem;
import com.river.ms.project.service.feign.impl.ToBusinessImpl;
import com.river.ms.project.service.feign.impl.ToResImpl;
import com.river.ms.project.service.feign.impl.ToSystemImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目信息列表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectListServiceDiy extends ServiceImpl<ProjectListDao, ProjectList> implements MPProjectListService {

	@Autowired
	ProjectStageServiceDiy service;

	@Autowired
	ToSystem toSystem;

	@Autowired
	ToResImpl toRes;

	@Autowired
	ToBusinessImpl toBusines;

	@Autowired
	MPResRefRoleOrgService resRefRoleOrgService;

	@Autowired
	ToSystemImpl toSystemImpl;

	@Override
	public List<ProjectList> getProjectList(String keyWord, Integer projectState, Integer projectState2) {
		return this.baseMapper.getProjectList(null, keyWord, projectState, projectState2);
	}

	@Override
	public List<ProjectList> getProjectListByStageCode(List<ProjectStage> stages) {
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		List<String> value = new ArrayList<>();
		for (ProjectStage s : stages) {
			value.add(s.getEntityCode());
		}
		wrapper.in("stageCode", value);
		List<ProjectList> selectList = this.selectList(wrapper);
		return selectList;
	}

	@Override
	public List<ProjectList> getProjectListByIds(Map<Long, Long> projectList) {
		List<ProjectList> selectList = new ArrayList<>();
		List<ProjectList> result = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		Set<Entry<Long, Long>> entrySet = projectList.entrySet();
		for (Entry<Long, Long> e : entrySet) {
			ids.add(e.getKey());
		}
		if (ids != null && ids.size() > 0) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("entityId", ids);
			selectList = this.selectList(wrapper);
		}
		for (Entry<Long, Long> e : entrySet) {
			if (e.getValue() != null) {
				result.addAll(this.getProjectLists(selectList, e.getValue()));
			} else {
				result = selectList;
				break;
			}
		}
		return result;
	}

	private List<ProjectList> getProjectLists(List<ProjectList> selectList, Long stageId) {
		List<ProjectList> result = new ArrayList<>();
		for (ProjectList l : selectList) {
			ProjectStage selectById = this.service.selectById(l.getStageCode());
			if (selectById.getLevel() == 1) {
				Map<String, Object> columnMap = new HashMap<>();
				columnMap.put("entityId", selectById.getParentId());
				List<ProjectStage> selectByMap = this.service.selectByMap(columnMap);
				selectById = selectByMap.get(0);
			}
			if (selectById.getEntityId().equals(stageId)) {
				result.add(l);
			}
		}
		return result;
	}

	@Override
	public List<ProjectList> getProjectListByStageCodeIds(List<Long> stages, String roleCode, UserEntity user) {
		List<ProjectList> selectList = new ArrayList<ProjectList>();
		if (roleCode.equals("ZHUXI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("YIBASHOU")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			/* wrapper.and().eq("groupLeaderId", user.getResId()); */
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("XIANGMUJINGLI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			wrapper.and();
			wrapper.eq("leader_itcode", user.getEntityCode());
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("FUNENGQUN")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("CFO")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("CEO")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			selectList = this.selectList(wrapper);
		}
		if (roleCode.equals("ZONGCAI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.in("stageCode", stages);
			selectList = this.selectList(wrapper);
		}
		return selectList;
	}

	/**
	 * 判断项目名称或者简称是否存在
	 * 
	 * @see com.river.ms.project.service.MPProjectListService#existTitle(java.lang.Long,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Long existTitle(Long projectId, String entityTitle, String entityAb) {
		return this.baseMapper.existTitle(projectId, entityTitle, entityAb);
	}

	@Override
	public Map<String, Object> getProjectStatistics(String hangyegongsi, String year) {
		EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
		if (hangyegongsi != null && !hangyegongsi.equals("")) {
			wrapper.eq("owner_group", hangyegongsi);
		}
		if (year != null && !year.equals("")) {

		}
		List<ProjectList> projectList = this.baseMapper.selectList(wrapper);
		Map<String, Object> projectStatistics = this.getProjectStatistics(projectList);
		return projectStatistics;
	}

	public Map<String, Object> getProjectStatistics(List<ProjectList> projectList) {
		Map<String, Object> result = new HashMap<>();
		Long money = new Long(0);
		Long num = new Long(0);
		Long runNum = new Long(0);
		Long endNum = new Long(0);
		for (ProjectList p : projectList) {
			num++;
			if (p.getState() != null) {
				if (p.getState().equals(EnumProjectState.Knot.getState())) {
					endNum++;
				} else {
					runNum++;
				}
			}
		}
		result.put("projectCost", money);// 投资总额
		result.put("projectTotal", num);// 项目数量
		result.put("projectDoing", runNum);// 进行中的项目
		result.put("projectfinish", endNum);// 已完结的项目
		result.put("ROE", 11.5);// 收益率
		return result;
	}

	@Override
	public List<Long> getEntityIdByOwnerGroup(String ownerGroup) {
		return this.baseMapper.getEntityIdByOwnerGroup(ownerGroup);
	}

	@Override
	public List<ProjectList> getProjectTodoList(HashMap<String, Object> map) {
		return this.baseMapper.getProjectTodoList(map);
	}

	@Override
	public List<ProjectList> getProjectListByUserLole(String role, String itCode, String resId) {
		List<ProjectList> selectList = new ArrayList<ProjectList>();
		if (role.equals("ZHUXI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			selectList = this.selectList(wrapper);
		}
		if (role.equals("YIBASHOU")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			/* wrapper.and().eq("groupLeaderId", resId); */
			selectList = this.selectList(wrapper);
		}
		if (role.equals("XIANGMUJINGLI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			wrapper.and();
			wrapper.eq("leader_itcode", itCode);
			selectList = this.selectList(wrapper);
		}
		if (role.equals("FUNENGQUN")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			selectList = this.selectList(wrapper);
		}
		if (role.equals("CFO")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			selectList = this.selectList(wrapper);
		}
		if (role.equals("CEO")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			selectList = this.selectList(wrapper);
		}
		if (role.equals("ZONGCAI")) {
			EntityWrapper<ProjectList> wrapper = new EntityWrapper<>();
			selectList = this.selectList(wrapper);
		}
		return selectList;
	}

	@Override
	public ProjectList getProjectListById(Long id) {
		ProjectList projectListById = this.baseMapper.getProjectListById(id);
		return projectListById;
	}

	@Override
	public Map<Long, List<Long>> getUsers(List<ProjectNodeRole> ProjectNodeRole, Long projectId,
			Map<String, Object> headerMap, UserEntity user) {
		ProjectList project = this.selectById(projectId);
		Map<Long, List<Long>> result = new HashMap<>();

		String groupCode = new String();
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		for (StationEmployeeEntity s : stationEmployees) {
			if (s.getIsChief() == 1) {
				groupCode = s.getOrganization().getEntityCode();
				break;
			}
		}

		for (ProjectNodeRole nr : ProjectNodeRole) {
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("roleId", nr.getRoleId());
			String role = toSystem.getRoleName(headerMap, queryMap);
			if (role.equals(EnumRole.Chairman.getRole())) {
				result.put(nr.getRoleId(), new ArrayList<>());
			}

			if (role.equals(EnumRole.Groupleader.getRole())) {
				/*
				 * List<Long> users = new ArrayList<>(); users.add(project.getGroupLeaderId());
				 */
				// List<Long> comLeaderIds = this.toRes.getComLeaderIds(headerMap, groupCode);
				result.put(nr.getRoleId(), new ArrayList<>());
			}
			if (role.equals(EnumRole.Teameader.getRole())) {
				Long resId = this.toRes.getResId(headerMap, project.getLeaderItcode());
				List<Long> users = new ArrayList<>();
				users.add(resId);
				result.put(nr.getRoleId(), users);
			}
			if (role.equals(EnumRole.Thinktank.getRole())) {
				// List<Long> fuNengQunIds = this.toRes.getFuNengQunIds(headerMap, groupCode);
				result.put(nr.getRoleId(), new ArrayList<>());
			}

			if (role.equals(EnumRole.CFO.getRole())) {
				result.put(nr.getRoleId(), new ArrayList<>());
			}
			if (role.equals(EnumRole.CEO.getRole())) {
				result.put(nr.getRoleId(), new ArrayList<>());
			}
			if (role.equals(EnumRole.President.getRole())) {
				result.put(nr.getRoleId(), new ArrayList<>());
			}
		}
		return result;
	}

	@Override
	public List<ProjectList> getProjectByState(Integer projectState, String leaderItcode, Integer projectState2) {
		return this.baseMapper.getProjectListByState(projectState, leaderItcode, projectState2);
	}

	@Override
	public List<ProjectList> getExpertProjectList(Long resId, Boolean isAll) {
		List<ProjectList> expertProjectList = this.baseMapper.getExpertProjectList(resId, isAll);
		return expertProjectList;
	}

	@Override
	public List<ProjectList> getFocusOnProjectList(Map<String, Object> headerMap, String itcode, String keyword) {
		Long resId = toRes.getResId(headerMap, itcode);
		return this.baseMapper.getFocusOnProjects(resId, keyword);
	}

	@Override
	public List<ProjectList> getFocusOnProjectList(Long resId, String keyword) {
		return this.baseMapper.getFocusOnProjects(resId, keyword);
	}

	@Override
	public List<ProjectList> getProjectByStateScope(Integer projectState, Integer projectState2) {
		return this.baseMapper.getProjectListByStateScope(projectState, projectState2);
	}

	@Override
	public List<ProjectList> getMyProjectByStateScope(HashMap<String, Object> map) {
		return this.baseMapper.getMyProjectListByStateScope(map);
	}

	@Override
	public List<ProjectList> getProjectByStatefunengqun(Integer projectState, String entityCode) {
		return this.baseMapper.getProjectByStatefunengqun(projectState, entityCode);
	}

	@Override
	public List<ProjectList> getProjectListByCouncilType(HttpServletRequest request, Integer online) {

		List<ProjectList> result = new ArrayList<>();
		// 查询专家所能操作的项目
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectList> expertProjectList = this.baseMapper.getExpertProjectList(user.getResId(), true);
		List<Long> toujueProjectList = new ArrayList<>();
		List<Long> biangengProjectList = new ArrayList<>();
		for (ProjectList p : expertProjectList) {
			if (p.getState() >= EnumProjectState.Decision.getState()
					&& p.getState() < EnumProjectState.Building.getState()) {
				toujueProjectList.add(p.getEntityId());
			}
			if (p.getState() >= EnumProjectState.Building.getState()
					&& p.getState() < EnumProjectState.Complete.getState()) {
				biangengProjectList.add(p.getEntityId());
			}
		}
		if (biangengProjectList != null && biangengProjectList.size() > 0) {
			List<Long> biangengxiangmuid = this.baseMapper.getProjectListByCouncilType(online, biangengProjectList, 2);
			for (Long l : biangengxiangmuid) {
				for (ProjectList p : expertProjectList) {
					if (p.getEntityId().equals(l)) {
						result.add(p);
					}
				}
			}
		}
		if (toujueProjectList != null && toujueProjectList.size() > 0) {
			List<Long> toujuexiangmuids = this.baseMapper.getProjectListByCouncilType(online, toujueProjectList, 1);
			for (Long l : toujuexiangmuids) {
				for (ProjectList p : expertProjectList) {
					if (p.getEntityId().equals(l)) {
						result.add(p);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getProjectRoleByItcode(Long projectId, String itcode) {
		return this.baseMapper.getProjectRoleByItcode(projectId, itcode);
	}

	@Override
	public List<ProjectList> getProjectByProjectIds(List<Long> ids, String keyWord) {
		List<ProjectList> projectList = this.baseMapper.getProjectList(ids, keyWord, null, null);
		return projectList;
	}

	@Override
	public List<ProjectList> getLeaderProjects(String leaderItcode) {
		return this.baseMapper.getLeaderProjects(leaderItcode);
	}

	@Override
	public List<ProjectList> getDeviation(String leaderItcode) {
		return this.baseMapper.getDeviation(leaderItcode);
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<ProjectList> queryListBy(HashMap<String, Object> map) {
		return this.baseMapper.queryListBy(map);
	}

	@Override
	public void getMyProjectCount(Map<String, Object> map) {
		this.baseMapper.getMyProjectCount(map);
	}

	@Override
	public List<ProjectList> getProjectTodoListPC(HashMap<String, Object> map) {
		return this.baseMapper.getProjectTodoListPC(map);
	}

	@Override
	public String getProjectNode(Long projectId) {
		return this.baseMapper.getProjectNode(projectId);
	}

	@Override
	public List<ProjectList> getNoReplyProjects(String itcode, String keyword) {
		return this.baseMapper.getNoReplyProjects(itcode, keyword);
	}

	@Override
	public List<ProjectList> getProjectListByQZTJLSFKSH(String groupCode) {
		return this.baseMapper.getProjectListByQZTJLSFKSH(groupCode);
	}

	/**
	 * 
	 * @param itcode
	 *            用户itcode
	 * @param isAll
	 *            是否可查看全部
	 * @param isIncludeOther
	 *            是否包含角色授权数据
	 * @param includeSubordinateGroupCode
	 *            角色授权数据中可查看子公司的groupCode
	 * @param notIncludeSubordinateGroupCode
	 *            角色授权数据中可查看当前公司的groupCode
	 * @return
	 */
	@Override
	public List<ProjectList> getMyProjectList(Boolean isFocusOn, String itcode, Long resId, Boolean isAll,
			Boolean isIncludeOther, List<String> includeSubordinateGroupCode,
			List<String> notIncludeSubordinateGroupCode, String keyword, Integer projectStateStart,
			Integer projectStateEnd, Date createTimeStart, Date createTimeEnd, List<String> ownerGroup,
			BigDecimal moneyStart, BigDecimal moneyEnd, String orderStr) {
		List<ProjectList> myProjectList = this.baseMapper.getMyProjectList(isFocusOn, itcode, resId, isAll,
				isIncludeOther, includeSubordinateGroupCode, notIncludeSubordinateGroupCode, keyword, projectStateStart,
				projectStateEnd, createTimeStart, createTimeEnd, ownerGroup, moneyStart, moneyEnd, orderStr);
		return myProjectList;
	}

	@Override
	public Boolean verifyProjectAuthority(HttpServletRequest request, Long projectId) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (user == null) {
			return false;
		}
		Boolean isAll = false;
		Boolean isIncludeOther = true;
		List<String> includeSubordinateGroupCode = new ArrayList<>();
		List<String> notIncludeSubordinateGroupCode = new ArrayList<>();
		Map<String, Boolean> resRefRoleOrg = this.resRefRoleOrgService.getResRefRoleOrg(user);
		if (resRefRoleOrg == null) {
			isIncludeOther = false;
		} else {
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
		List<ProjectList> myProjectList = this.baseMapper.verifyProjectAuthority(user.getEntityCode(), isAll,
				isIncludeOther, includeSubordinateGroupCode, notIncludeSubordinateGroupCode, projectId);
		if (myProjectList != null && myProjectList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
