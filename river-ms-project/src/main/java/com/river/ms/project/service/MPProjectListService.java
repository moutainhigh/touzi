package com.river.ms.project.service;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectNodeRole;
import com.river.ms.project.entity.ProjectStage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目信息列表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectListService extends IService<ProjectList> {

	/**
	 * 查询项目列表
	 * 
	 * @return
	 */
	List<ProjectList> getProjectList(String keyWord, Integer projectState, Integer projectState2);

	/**
	 * 根据项目状态获取项目列表
	 * 
	 * @return
	 */
	List<ProjectList> getProjectByState(Integer projectState, String leaderItcode, Integer projectState2);

	/**
	 * 获取投前投中投后的项目
	 * 
	 * @param projectState
	 * @return
	 */
	List<ProjectList> getProjectByStateScope(Integer projectState, Integer projectState2);

	List<ProjectList> getProjectListByQZTJLSFKSH(String groupCode);

	/**
	 * 
	 * @param projectState
	 * @param projectState2
	 * @return
	 */
	List<ProjectList> getMyProjectByStateScope(HashMap<String, Object> map);

	/**
	 * 
	 * @param projectId
	 * @param itcode
	 * @return
	 */
	Map<String, Object> getProjectRoleByItcode(Long projectId, String itcode);

	/**
	 * 根据项目阶段查询项目
	 * 
	 * @param stages
	 * @return
	 */
	List<ProjectList> getProjectListByStageCode(List<ProjectStage> stages);

	/**
	 * 根据ID查询项目
	 * 
	 * @param stages
	 * @return
	 */
	List<ProjectList> getProjectListByIds(Map<Long, Long> projectList);

	/**
	 * 根据项目阶段查询项目
	 * 
	 * @param stages
	 * @return
	 */
	List<ProjectList> getProjectListByStageCodeIds(List<Long> stages, String roleCode, UserEntity user);

	List<ProjectList> getProjectTodoList(HashMap<String, Object> map);

	List<ProjectList> getProjectTodoListPC(HashMap<String, Object> map);

	/**
	 * 项目统计
	 * 
	 * @return
	 */
	Map<String, Object> getProjectStatistics(String hangyegongsi, String year);

	Map<String, Object> getProjectStatistics(List<ProjectList> projectList);

	/**
	 * 查找某个行业公司下所有项目的id
	 * 
	 * @param ownerGroup
	 * @return
	 */
	List<Long> getEntityIdByOwnerGroup(String ownerGroup);

	/**
	 * 根据用户itcode和用户的role，查看project
	 * 
	 * @param stages
	 * @return
	 */
	List<ProjectList> getProjectListByUserLole(String role, String itCode, String resId);

	/**
	 * 获取单个项目详细信息
	 * 
	 * @param id
	 * @return
	 */
	ProjectList getProjectListById(Long id);

	/**
	 * 根据角色获取相关用户ids
	 * 
	 * @return
	 */
	Map<Long, List<Long>> getUsers(List<ProjectNodeRole> ProjectNodeRole, Long projectId, Map<String, Object> headerMap,
			UserEntity user);

	/**
	 * 根据专家ID获取所能操作的项目
	 * 
	 * @param resId
	 * @return
	 */
	List<ProjectList> getExpertProjectList(Long resId, Boolean isAll);

	///
	List<ProjectList> getFocusOnProjectList(Map<String, Object> headerMap, String itcode, String keyword);

	//
	List<ProjectList> getFocusOnProjectList(Long resId, String keyword);

	/**
	 * 根据项目状态获取项目列表
	 * 
	 * @return
	 */
	List<ProjectList> getProjectByStatefunengqun(Integer projectState, String entityCode);

	/**
	 * 获取需要上会的项目列表
	 * 
	 * @param request
	 * @param online
	 * @return
	 */
	List<ProjectList> getProjectListByCouncilType(HttpServletRequest request, Integer online);

	/**
	 * 根据项目id列表，获取项目信息
	 * 
	 * @param ids
	 * @param keyWord
	 * @return
	 */
	List<ProjectList> getProjectByProjectIds(List<Long> ids, String keyWord);

	List<ProjectList> getLeaderProjects(String leaderItcode);

	List<ProjectList> getDeviation(String leaderItcode);

	/**
	 * 判断是否存在项目
	 * 
	 * @param projectId
	 *            新增时传-1
	 * @param entityTitle
	 * @param entityAb
	 * @return
	 */
	Long existTitle(Long projectId, String entityTitle, String entityAb);

	/**
	 * 各种条件关键词查询项目
	 * 
	 * @param map
	 * @return
	 */
	List<ProjectList> queryListBy(HashMap<String, Object> map);

	void getMyProjectCount(Map<String, Object> map);

	String getProjectNode(Long projectId);

	List<ProjectList> getNoReplyProjects(String itcode, String keyword);

	/**
	 * 
	 * @param itcode
	 * @param isAll
	 * @param isIncludeOther
	 * @param includeSubordinateGroupCode
	 * @param notIncludeSubordinateGroupCode
	 * @return
	 */
	List<ProjectList> getMyProjectList(Boolean isFocusOn, String itcode,Long resId, Boolean isAll, Boolean isIncludeOther,
			List<String> includeSubordinateGroupCode, List<String> notIncludeSubordinateGroupCode, String keyword,
			Integer projectStateStart, Integer projectStateEnd, Date createTimeStart, Date createTimeEnd,
			List<String> ownerGroup, BigDecimal moneyStart, BigDecimal moneyEnd, String orderStr);

	Boolean verifyProjectAuthority(HttpServletRequest request, Long projectId);

}
