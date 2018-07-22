package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectOpinion;
import com.river.ms.business.mapper.ProjectOpinionDao;
import com.river.ms.business.service.MPProjectOpinionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 专家意见 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectOpinionServiceDiy extends ServiceImpl<ProjectOpinionDao, ProjectOpinion> implements MPProjectOpinionService {

	@Override
	public List<ProjectOpinion> getProjectOpinions(Long projectId, String stage) {
		List<ProjectOpinion> projectOpinions = this.baseMapper.getProjectOpinions(projectId, stage);
		return projectOpinions;
	}

}
