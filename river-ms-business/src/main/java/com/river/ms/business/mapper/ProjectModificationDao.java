package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectModification;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目变更 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectModificationDao extends BaseMapper<ProjectModification> {

	List<ProjectModification> getProjectModificationByProjectId(@Param("projectId") Long projectId,@Param("processInstanceId")String processInstanceId);
}
