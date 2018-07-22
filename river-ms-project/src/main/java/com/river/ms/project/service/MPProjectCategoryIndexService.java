package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectCategoryIndex;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 行业公司项目类别对应评价指标 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCategoryIndexService extends IService<ProjectCategoryIndex> {
	
	List<ProjectCategoryIndex> getProjectCategoryIndex(ProjectCategoryIndex entity);
	
	boolean insertProjectCategoryIndex(List<ProjectCategoryIndex> entity);
}
