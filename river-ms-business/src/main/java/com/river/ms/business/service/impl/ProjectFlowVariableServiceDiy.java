package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectFlowVariable;
import com.river.ms.business.mapper.ProjectFlowVariableDao;
import com.river.ms.business.service.MPProjectFlowVariableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyb
 * @since 2018-01-29
 */
@Service
public class ProjectFlowVariableServiceDiy extends ServiceImpl<ProjectFlowVariableDao, ProjectFlowVariable> implements MPProjectFlowVariableService {

	@Override
	public List<ProjectFlowVariable> getProjectFlowVariable(Long flowId, Long nodeId, Long stageId) {
		return this.baseMapper.getProjectFlowVariable(flowId, nodeId, stageId);
	}

}
