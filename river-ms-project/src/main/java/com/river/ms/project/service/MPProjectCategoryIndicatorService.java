package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectCategoryIndicator;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 行业公司项目类别对应经营数据：暂时没有考虑预测，实际，可行，投后等 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCategoryIndicatorService extends IService<ProjectCategoryIndicator> {

	List<ProjectCategoryIndicator> getProjectCategoryIndicator(ProjectCategoryIndicator entity);
	
	boolean insertProjectCategoryIndicator(List<ProjectCategoryIndicator> entity);

}
