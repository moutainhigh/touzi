package com.river.ms.project.service;

import com.river.core.entity.UserEntity;
import com.river.ms.project.entity.ProjectCategory;
import com.river.ms.project.entity.ProjectCategoryGroup;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 行业公司项目分类 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCategoryGroupService extends IService<ProjectCategoryGroup> {

	/**
	 * 获取项目分类
	 * 
	 * @return
	 */
	List<ProjectCategoryGroup> getProjectCategoryGroup(UserEntity user, Long categoryId);

	Boolean insertProjectCategoryGroup(List<ProjectCategory> categorys, Long categoryId, String groupCode);
}
