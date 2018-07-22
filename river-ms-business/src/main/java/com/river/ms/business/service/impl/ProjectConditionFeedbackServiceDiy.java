package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectConditionFeedback;
import com.river.ms.business.mapper.ProjectConditionFeedbackDao;
import com.river.ms.business.service.MPProjectConditionFeedbackService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 前置条件落实反馈 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectConditionFeedbackServiceDiy
		extends ServiceImpl<ProjectConditionFeedbackDao, ProjectConditionFeedback>
		implements MPProjectConditionFeedbackService {

	@Override
	public List<ProjectConditionFeedback> getConditionFeedbackById(Integer projectId, Integer type) {
		return this.baseMapper.getConditionFeedbackById(projectId, type);
	}
	@Override
	public List<ProjectConditionFeedback> getConditionFeedbackByProjectId(Integer projectId, Integer type) {
		return this.baseMapper.getConditionFeedbackByProjectId(projectId, type);
	}
}
