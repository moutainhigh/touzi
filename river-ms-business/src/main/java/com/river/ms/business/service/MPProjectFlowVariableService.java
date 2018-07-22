package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectFlowVariable;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyb
 * @since 2018-01-29
 */
public interface MPProjectFlowVariableService extends IService<ProjectFlowVariable> {

	List<ProjectFlowVariable> getProjectFlowVariable(Long flowId,Long nodeId,Long stageId);
}
