package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectOpinion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 专家意见 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectOpinionDao extends BaseMapper<ProjectOpinion> {

	public List<ProjectOpinion> getProjectOpinions(@Param("projectId") Long projectId, @Param("stage") String stage);

	List<ProjectOpinion> selectProjectOpinions(@Param("projectId") Long projectId,
			@Param("resourceId") Long resourceId);
	
	List<ProjectOpinion> selectProjectOpinionsAll(@Param("projectId") Long projectId,
			@Param("resourceId") Long resourceId,@Param("stage") Long stage,@Param("processInstanceId")String processInstanceId);
}
