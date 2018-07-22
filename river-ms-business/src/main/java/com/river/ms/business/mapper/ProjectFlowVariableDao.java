package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectFlowVariable;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2018-01-29
 */
@Mapper
public interface ProjectFlowVariableDao extends BaseMapper<ProjectFlowVariable> {

	List<ProjectFlowVariable> getProjectFlowVariable(@Param("flowId") Long flowId, @Param("nodeId") Long nodeId,
			@Param("stageId") Long stageId);

}
