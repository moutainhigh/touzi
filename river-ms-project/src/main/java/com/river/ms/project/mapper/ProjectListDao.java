package com.river.ms.project.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.core.entity.RoleEntity;
import com.river.ms.project.entity.ProjectList;

/**
 * <p>
 * 项目信息列表 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectListDao extends BaseMapper<ProjectList> {

	/**
	 * 查找项目列表
	 * 
	 * @return
	 */
	public List<ProjectList> getProjectList(@Param("ids") List<Long> ids, @Param("keyWord") String keyWord,
			@Param("projectState") Integer projectState, @Param("projectState2") Integer projectState2);

	public List<ProjectList> queryListBy(HashMap<String, Object> map);

	/**
	 * 查找某个行业公司所有项目的id集合
	 * 
	 * @param ownerGroup
	 *            行业公司编码
	 * @return
	 */
	List<Long> getEntityIdByOwnerGroup(String ownerGroup);

	/**
	 * 根据项目ID和用户ITCODE获取该用户在项目当前阶段的角色
	 * 
	 * @param projectId
	 * @param itcode
	 * @return
	 */
	Map<String, Object> getProjectRoleByItcode(@Param("projectId") Long projectId, @Param("itcode") String itcode);

	/**
	 * 查看项目详细信息
	 * 
	 * @return
	 */
	ProjectList getProjectListById(@Param("id") Long id);

	/**
	 * 根据项目ID和stage查询项目
	 * 
	 * @param projectList
	 * @return
	 */
	List<ProjectList> getProjectListByIdsAndStageIds(@Param("projectList") Map<Long, Long> projectList);

	/**
	 * 
	 * @param state
	 * @return
	 */
	List<ProjectList> getProjectListByState(@Param("projectState") Integer projectState,
			@Param("leaderItcode") String leaderItcode, @Param("projectState2") Integer projectState2);

	/**
	 * 获取待办事项
	 * 
	 * @param ids
	 * @param resId
	 * @return
	 */
	List<ProjectList> getProjectTodoList(HashMap<String, Object> map);

	List<ProjectList> getProjectTodoListPC(HashMap<String, Object> map);

	/**
	 * 
	 * @param projectState
	 * @return
	 */
	List<ProjectList> getProjectListByStateScope(@Param("projectState") Integer projectState,
			@Param("projectState2") Integer projectState2);

	List<ProjectList> getMyProjectListByStateScope(HashMap<String, Object> map);

	/**
	 * 根据专家ID获取所能操作的项目
	 * 
	 * @param resId
	 * @return
	 */
	List<ProjectList> getExpertProjectList(@Param("resId") Long resId, @Param("isAll") Boolean isAll);

	/**
	 * 获取担任项目经理的项目
	 * 
	 * @param itcode
	 * @return
	 */
	List<ProjectList> getLeaderProjects(@Param("itcode") String itcode);

	List<ProjectList> getDeviation(@Param("itcode") String itcode);

	/**
	 * 获取用户关注的项目
	 * 
	 * @param resId
	 * @return
	 */
	List<ProjectList> getFocusOnProjects(@Param("resId") Long resId, @Param("keyword") String keyword);

	List<ProjectList> getProjectByStatefunengqun(@Param("projectState") Integer projectState,
			@Param("entityCode") String entityCode);

	List<Long> getProjectListByCouncilType(@Param("online") Integer online, @Param("ids") List<Long> ids,
			@Param("entityType") Integer entityType);

	Long existTitle(@Param("projectId") Long projectId, @Param("entityTitle") String entityTitle,
			@Param("entityAb") String entityAb);

	void getMyProjectCount(Map<String, Object> map);

	public String getProjectNode(@Param("projectId") Long projectId);

	/**
	 * 查询项目经理未回复的项目
	 * 
	 * @param itcode
	 * @return
	 */
	List<ProjectList> getNoReplyProjects(@Param("itcode") String itcode, @Param("keyword") String keyword);

	List<ProjectList> getProjectListByQZTJLSFKSH(@Param("groupCode") String groupCode);

	List<ProjectList> getMyProjectList(@Param("isFocusOn") Boolean isFocusOn, @Param("itcode") String itcode,
			@Param("resId") Long resId, @Param("isAll") Boolean isAll, @Param("isIncludeOther") Boolean isIncludeOther,
			@Param("includeSubordinateGroupCode") List<String> includeSubordinateGroupCode,
			@Param("notIncludeSubordinateGroupCode") List<String> notIncludeSubordinateGroupCode,
			@Param("keyword") String keyword, @Param("projectStateStart") Integer projectStateStart,
			@Param("projectStateEnd") Integer projectStateEnd, @Param("createTimeStart") Date createTimeStart,
			@Param("createTimeEnd") Date createTimeEnd, @Param("ownerGroup") List<String> ownerGroup,
			@Param("moneyStart") BigDecimal moneyStart, @Param("moneyEnd") BigDecimal moneyEnd,
			@Param("orderStr") String orderStr);

	List<ProjectList> verifyProjectAuthority(@Param("itcode") String itcode, @Param("isAll") Boolean isAll,
			@Param("isIncludeOther") Boolean isIncludeOther,
			@Param("includeSubordinateGroupCode") List<String> includeSubordinateGroupCode,
			@Param("notIncludeSubordinateGroupCode") List<String> notIncludeSubordinateGroupCode,
			@Param("projectId") Long projectId);
}
