package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectCompletion;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectCompletionDao;
import com.river.ms.business.service.MPProjectCompletionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目竣工/结项申请 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectCompletionServiceDiy extends ServiceImpl<ProjectCompletionDao, ProjectCompletion>
		implements MPProjectCompletionService {

	@Autowired
	ToProjectImpl toProject;

	@Override
	@Transactional
	public Boolean setProjectCompletionState(Long entityId, Integer state, Map<String, Object> headerMap) {
		ProjectCompletion entity = this.baseMapper.selectById(entityId);
		if (entity != null) {
			entity.setState(state);
			Integer updateById = this.baseMapper.updateById(entity);
			if (updateById != null) {
				Boolean setProjectState = toProject.setProjectState(headerMap, entity.getProjectId(),
						entity.getState());
				if (setProjectState) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean insertProjectCompletion(ProjectCompletion entity, Map<String, Object> headerMap) {
		boolean insert = this.insert(entity);
		if (insert) {
			Boolean setProjectState = toProject.setProjectState(headerMap, entity.getProjectId(), 40);
			Boolean setLiuchengshiliIdByProjectId = toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(), null);
			toProject.setProjectStage(headerMap, entity.getProjectId(), 0l,
					"已竣工");
			if (setProjectState && setLiuchengshiliIdByProjectId) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
