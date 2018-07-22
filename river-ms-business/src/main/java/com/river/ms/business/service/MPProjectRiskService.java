package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectRisk;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 风险评议 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
public interface MPProjectRiskService extends IService<ProjectRisk> {

	List<ProjectRisk> selectProjectRisk(Long projectId, Long stage);

}
