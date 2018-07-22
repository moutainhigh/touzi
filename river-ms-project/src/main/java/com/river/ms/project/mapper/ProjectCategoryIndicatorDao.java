package com.river.ms.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.project.entity.ProjectCategoryIndicator;

/**
 * <p>
 * 行业公司项目类别对应经营数据：暂时没有考虑预测，实际，可行，投后等 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectCategoryIndicatorDao extends BaseMapper<ProjectCategoryIndicator> {
	List<ProjectCategoryIndicator> getProjectCategoryIndicator(@Param("entity") ProjectCategoryIndicator entity);
}
