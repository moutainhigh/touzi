package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectApproval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目审批 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectApprovalDao extends BaseMapper<ProjectApproval> {

	List<ProjectApproval> getProjectApprovalByCondition(@Param("projectId") Long projectId,
			@Param("stage") String stage, @Param("type") Integer type);

	List<ProjectApproval> getProjectApproval(@Param("projectId") Long projectId, @Param("resourceId") Long resourceId);

	List<ProjectApproval> getProjectApprovalAll(@Param("projectId") Long projectId,
			@Param("resourceId") Long resourceId, @Param("stage") Long stage,
			@Param("processInstanceId") String processInstanceId, @Param("entityId") String entityId);

	/**
	 * 
	 * @param projectId
	 * @param flowType
	 * @return
	 */
	List<ProjectApproval> getProjectExpertApproval(@Param("projectId") Long projectId, @Param("stage") Long stage);

}
