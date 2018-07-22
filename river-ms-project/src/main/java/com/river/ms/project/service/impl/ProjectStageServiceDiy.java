package com.river.ms.project.service.impl;

import com.river.core.entity.RoleEntity;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.mapper.ProjectStageDao;
import com.river.ms.project.service.MPProjectStageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目阶段 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectStageServiceDiy extends ServiceImpl<ProjectStageDao, ProjectStage>
		implements MPProjectStageService {

	@Override
	public List<ProjectStage> getProjectStageByRoles(List<RoleEntity> roles) {
		return this.baseMapper.getProjectStageByRoles(roles);
	}

	@Override
	public List<ProjectStage> getProjectStageByNode(Long nodeId) {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("src", nodeId);
		List<ProjectStage> selectByMap = this.baseMapper.selectByMap(columnMap);
		return selectByMap;
	}
	/**
	 * 
	 */
	@Override
	public Long exist(Long id, String entityCode) {
		return this.baseMapper.exist(id, entityCode);
	}
	/**
	 * 
	 * @param stage
	 * @return
	 */
	@Override
	public ProjectStage getNextStage(String stage) {
		return this.baseMapper.getNextStage(stage);
	}

}
