package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectRisk;
import com.river.ms.business.mapper.ProjectRiskDao;
import com.river.ms.business.service.MPProjectRiskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 风险评议 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Service
public class ProjectRiskServiceDiy extends ServiceImpl<ProjectRiskDao, ProjectRisk> implements MPProjectRiskService {

	@Override
	public List<ProjectRisk> selectProjectRisk(Long projectId, Long stage) {
		List<ProjectRisk> selectProjectRisk = this.baseMapper.selectProjectRisk(projectId, stage);
		return selectProjectRisk;
	}

}
