package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectMilestone;
import com.river.ms.business.mapper.ProjectMilestoneDao;
import com.river.ms.business.service.MPProjectMilestoneService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目节点 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectMilestoneServiceDiy extends ServiceImpl<ProjectMilestoneDao, ProjectMilestone>
		implements MPProjectMilestoneService {

	@Override
	@Transactional
	public Boolean updateProjectMilestoneState(Long entityId, Integer state) {
		ProjectMilestone entity = this.selectById(entityId);
		entity.setState(state);
		boolean updateById = this.updateById(entity);
		return updateById;
	}

	@Override
	public List<ProjectMilestone> getProjectMilestoneListByProjectId(Long projectId) {
		return this.baseMapper.getProjectMilestoneListByProjectId(projectId);
	}

	@Override
	public Long exist(Long entityId, Long projectId, String milestone) {
		Long exist = this.baseMapper.exist(entityId, projectId, milestone);
		return exist;
	}

}
