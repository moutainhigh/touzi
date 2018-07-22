package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectFlowStart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 启动流程配置 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Mapper
public interface ProjectFlowStartDao extends BaseMapper<ProjectFlowStart> {

	/**
	 * 查询流程实例
	 * 
	 * @return
	 */
	List<ProjectFlowStart> selectProjectFlowStart(@Param("entityId") Long entityId,
			@Param("groupCategoryId") Long groupCategoryId, @Param("type") Integer type);

	Long getParentCategroyId(@Param("categroyId") Long categroyId);

	Long isExist(@Param("entityId") Long entityId, @Param("flowId") Long flowId,
			@Param("groupCategoryId") Long groupCategoryId, @Param("type") Integer type);
}
