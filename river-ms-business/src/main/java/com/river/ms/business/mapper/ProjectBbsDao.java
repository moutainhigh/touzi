package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectBbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目讨论区 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectBbsDao extends BaseMapper<ProjectBbs> {

	List<ProjectBbs> selectProjectBbs(@Param("projectId") Long projectId, @Param("stage")Long stage);
	List<ProjectBbs> getNoReply(@Param("itcode") String itcode);
	List<ProjectBbs> getReplied(@Param("itcode") String itcode);
}
