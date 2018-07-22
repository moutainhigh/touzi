package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectOpinion;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 专家意见 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectOpinionService extends IService<ProjectOpinion> {

	/**
	 * 获取专家意见
	 * @param projectId
	 * @param stage
	 * @return
	 */
	public List<ProjectOpinion> getProjectOpinions(Long projectId, String stage);

}
