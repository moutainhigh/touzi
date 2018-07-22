package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectEvaluateFeedback;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目评价反馈 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
public interface MPProjectEvaluateFeedbackService extends IService<ProjectEvaluateFeedback> {

	boolean insertEvaluateFeedback(ProjectEvaluateFeedback entity, Map<String, Object> headerMap,HttpServletRequest request, String docStr);

}
