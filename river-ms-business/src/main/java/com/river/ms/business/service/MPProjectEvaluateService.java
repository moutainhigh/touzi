package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectEvaluate;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目实施评价记录 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-21
 */
public interface MPProjectEvaluateService extends IService<ProjectEvaluate> {
	Boolean insertProjectEvaluate(ProjectEvaluate entity, Map<String, Object> headerMap,String docStr,UserEntity user);
	Boolean setProjectEvaluateState(Long entityId, Integer state, Map<String, Object> headerMap);
}
