package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectMilestone;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目节点 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectMilestoneDao extends BaseMapper<ProjectMilestone> {

	/**
	 * 根据项目id查找节点列表
	 */
	List<ProjectMilestone> getProjectMilestoneListByProjectId(@Param("projectId") Long projectId);

	Long exist(@Param("id") Long entityId, @Param("projectId") Long projectId,
			@Param("milestone") String milestone);

}
