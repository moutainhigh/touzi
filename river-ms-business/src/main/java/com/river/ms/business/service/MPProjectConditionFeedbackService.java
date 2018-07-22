package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectConditionFeedback;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 前置条件落实反馈 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectConditionFeedbackService extends IService<ProjectConditionFeedback> {
	List<ProjectConditionFeedback> getConditionFeedbackById(Integer projectId,Integer type);
	List<ProjectConditionFeedback> getConditionFeedbackByProjectId(Integer projectId,Integer type);

}
