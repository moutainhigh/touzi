package com.river.ms.business.mapper;

import com.river.ms.business.entity.ViewProjectExpert;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-29
 */
@Mapper
public interface ViewProjectExpertDao extends BaseMapper<ViewProjectExpert> {

	List<ViewProjectExpert> selectViewProjectExpertByProjectId(@Param("projectId") Long projectId);
	
	List<ViewProjectExpert> selectAllViewProjectExpertByProjectId(@Param("projectId") Long projectId,@Param("stage")Long stage,@Param("processInstanceId")String processInstanceId);
}
