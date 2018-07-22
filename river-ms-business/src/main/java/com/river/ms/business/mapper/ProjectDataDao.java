package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectData;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 可研数据录入 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectDataDao extends BaseMapper<ProjectData> {
	List<ProjectData> getProjectData(@Param("entity") ProjectData entity,@Param("ids") List<Long> ids);
}
