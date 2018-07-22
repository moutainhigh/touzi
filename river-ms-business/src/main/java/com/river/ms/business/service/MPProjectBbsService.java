package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectBbs;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目讨论区 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectBbsService extends IService<ProjectBbs> {

	boolean insertBbs(Map<String, Object> headerMap, ProjectBbs pb, String content, String title, UserEntity user);

	List<ProjectBbs> selectByProjectIdAndStageId(Map<String, Object> headerMap, Long projectId, Long stage,
			Boolean isAll);
	
	List<ProjectBbs> getNoReply(String itcode);
	List<ProjectBbs> getReplied(String itcode);
}
