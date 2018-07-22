package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectOpinionCondition;
import com.river.ms.business.mapper.ProjectOpinionConditionDao;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 有条件通过时的条件清单 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectOpinionConditionServiceDiy extends ServiceImpl<ProjectOpinionConditionDao, ProjectOpinionCondition> implements MPProjectOpinionConditionService {

	@Override
	public List<ProjectOpinionCondition> getOpinionConditionByProjectId(Long projectId, Integer type) {
		return this.baseMapper.getOpinionConditionByProjectId(projectId, type);
	}
}
