package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectMilestoneCost;

import feign.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目成本更新记录 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectMilestoneCostDao extends BaseMapper<ProjectMilestoneCost> {
	/**
	 * 根据节点id查找列表
	 */
	List<ProjectMilestoneCost> getProjectMilestoneCostByMilestoneId(@Param("milestoneId") Long milestoneId);

}
