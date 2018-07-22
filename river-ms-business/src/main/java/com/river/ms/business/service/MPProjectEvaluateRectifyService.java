package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectEvaluateRectify;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目反馈/整改审核 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
public interface MPProjectEvaluateRectifyService extends IService<ProjectEvaluateRectify> {

	boolean insertProjectEvaluateRectify(ProjectEvaluateRectify entity, Map<String, Object> headerMap);
}
