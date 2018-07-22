package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectHistory;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目预测数据/历史数据 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectHistoryService extends IService<ProjectHistory> {

	Boolean isExist(ProjectHistory entity);
	List<ProjectHistory> getProjectHistory(Long projectId,Integer entityType);
}
