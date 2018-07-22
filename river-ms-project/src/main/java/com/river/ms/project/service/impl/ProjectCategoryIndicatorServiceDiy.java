package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectCategoryIndicator;
import com.river.ms.project.mapper.ProjectCategoryIndicatorDao;
import com.river.ms.project.service.MPProjectCategoryIndicatorService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 行业公司项目类别对应经营数据：暂时没有考虑预测，实际，可行，投后等 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectCategoryIndicatorServiceDiy
		extends ServiceImpl<ProjectCategoryIndicatorDao, ProjectCategoryIndicator>
		implements MPProjectCategoryIndicatorService {

	@Override
	public List<ProjectCategoryIndicator> getProjectCategoryIndicator(ProjectCategoryIndicator entity) {
		List<ProjectCategoryIndicator> selectList = this.baseMapper.getProjectCategoryIndicator(entity);
		return selectList;
	}

	@Override
	public boolean insertProjectCategoryIndicator(List<ProjectCategoryIndicator> entity) {
		if (entity != null && entity.size() > 0 && entity.get(0).getCategoryId() != null) {
			List<ProjectCategoryIndicator> result = new ArrayList<>();
			EntityWrapper<ProjectCategoryIndicator> wrapper = new EntityWrapper<>();
			wrapper.eq("categoryId", entity.get(0).getCategoryId());
			List<ProjectCategoryIndicator> ProjectCategoryIndicators = this.selectList(wrapper);
			for (ProjectCategoryIndicator p : entity) {
				Boolean bool = true;
				for (ProjectCategoryIndicator projectCategoryIndicator : ProjectCategoryIndicators) {
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
