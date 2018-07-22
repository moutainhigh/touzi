package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectCategoryIndex;
import com.river.ms.project.mapper.ProjectCategoryIndexDao;
import com.river.ms.project.service.MPProjectCategoryIndexService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 行业公司项目类别对应评价指标 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectCategoryIndexServiceDiy extends ServiceImpl<ProjectCategoryIndexDao, ProjectCategoryIndex> implements MPProjectCategoryIndexService {

	@Override
	public List<ProjectCategoryIndex> getProjectCategoryIndex(ProjectCategoryIndex entity) {
		List<ProjectCategoryIndex> selectList = this.baseMapper.getProjectCategoryIndex(entity);
		return selectList;
	}

	@Override
	public boolean insertProjectCategoryIndex(List<ProjectCategoryIndex> entity) {
		if (entity != null && entity.size() > 0 && entity.get(0).getCategoryId() != null) {
			List<ProjectCategoryIndex> result = new ArrayList<>();
			EntityWrapper<ProjectCategoryIndex> wrapper = new EntityWrapper<>();
			wrapper.eq("categoryId", entity.get(0).getCategoryId());
			List<ProjectCategoryIndex> ProjectCategoryIndicators = this.selectList(wrapper);
			for (ProjectCategoryIndex p : entity) {
				Boolean bool = true;
				for (ProjectCategoryIndex projectCategoryIndicator : ProjectCategoryIndicators) {
					if (p.getCategoryId().equals(projectCategoryIndicator.getCategoryId())
							&& p.getIndicatorId().equals(projectCategoryIndicator.getIndicatorId())) {
						bool = false;
						break;
					}
				}
				if (bool) {
					result.add(p);
				}
			}
			if (result != null && result.size() > 0) {
				boolean insertBatch = this.insertBatch(result);
				return insertBatch;
			}
		}
		return true;
	}

}
