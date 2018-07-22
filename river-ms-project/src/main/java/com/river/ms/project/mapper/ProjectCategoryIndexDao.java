package com.river.ms.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.project.entity.ProjectCategoryIndex;

/**
 * <p>
 * 行业公司项目类别对应评价指标 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectCategoryIndexDao extends BaseMapper<ProjectCategoryIndex> {
	List<ProjectCategoryIndex> getProjectCategoryIndex(@Param("entity") ProjectCategoryIndex entity);
}
