package com.river.ms.res.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResList;

/**
 * <p>
 * 资源列表 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResListDao extends BaseMapper<ResList> {

	/**
	 * 根据categoryType查询res
	 * 
	 * @param categoryType
	 * @return
	 */
	List<ResList> selectResPageByCategoryType(@Param("categoryType") Integer categoryType,
			@Param("projectId") Long projectId, @Param("stage") Long stage, @Param("keyWord") String keyWord,
			@Param("isExpert") Boolean isExpert, @Param("isExcludeExpert") Boolean isExcludeExpert,
			@Param("isExcludeOneself")Boolean isExcludeOneself, @Param("itcode")String itcode);

	List<ResList> selectResRoleByItcode(@Param("itcode") String itcode);

	List<ResList> getExpertByProjectId(@Param("projectId") Long projectId, @Param("stage") String stage);

	List<ResList> getComLeader(@Param("groupCode") String groupCode);

	List<ResList> getProjectMember(@Param("projectId") Long projectId, @Param("memberType") int memberType);

	List<ResList> getDecisionLayer(@Param("projectId") Long projectId);

	/**
	 * 获取所有项目经理（角色为项目经理的）
	 * 
	 * @return
	 */
	List<ResList> getAllTeamLeader(@Param("keyword") String keyword);

	List<ResList> getFuNengQun(@Param("groupCode") String groupCode);

	ResList getResById(@Param("entityId") Long entityId);

	Long exist(@Param("id") Long id, @Param("code") String code);

	/**
	 * 
	 * @param keyword
	 * @Param itcode
	 * @return
	 */
	List<ResList> getFollowMember(HashMap<String, Object> map);
}
