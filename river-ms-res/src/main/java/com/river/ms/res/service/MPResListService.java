package com.river.ms.res.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResList;

/**
 * <p>
 * 资源列表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResListService extends IService<ResList> {

	Long exist(Long id, String code);

	/**
	 * 根据资源类型获取资源列表
	 * 
	 * @param categoryType
	 * @return
	 */
	List<ResList> getResByCategoryType(HttpServletRequest request, Integer categoryType, Long projectId, Long stage,
			String keyWord, Boolean isExpert,Boolean isExcludeExpert,Boolean isExcludeOneself,String itcode);

	List<ResList> getRes(Long userId, String itcode);

	List<ResList> getAll(String keyWord);

	List<ResList> selectResRoleByItcode(String itcode);

	List<ResList> getExpertByProjectId(HttpServletRequest request, Long projectId, String stage);

	/**
	 * 获取公司一把手
	 * 
	 * @param groupCode
	 * @return
	 */
	List<ResList> getComLeader(String groupCode);

	/**
	 * 获取项目成员
	 * 
	 * @param projectId
	 * @return
	 */
	List<ResList> getProjectMember(HttpServletRequest request, Long projectId, int memberType);

	/**
	 * 获取决策层
	 * 
	 * @param projectId
	 * @return
	 */
	List<ResList> getDecisionLayer(Long projectId);

	List<ResList> getAllTeamLeader(String keyword);

	/**
	 * 获取公司赋能群
	 * 
	 * @param groupCode
	 * @return
	 */
	List<ResList> getFuNengQun(String groupCode);

	/**
	 * 获取资源详情
	 * 
	 * @param entityId
	 * @return
	 */
	ResList getResById(Long entityId);

	Boolean insertRes(Map<String, Object> headerMap, ResList resEntity, Long employeeId, String eId);

	ResList getResByItcode(String itcode);

	ResList selectResAndTag(String itcode,Integer tagType);
	List<ResList> getFollowMember(HashMap<String,Object> map);

}
