package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectOpinionCondition;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 有条件通过时的条件清单 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectOpinionConditionService extends IService<ProjectOpinionCondition> {
	List<ProjectOpinionCondition> getOpinionConditionByProjectId(Long projectId,Integer type);
}
