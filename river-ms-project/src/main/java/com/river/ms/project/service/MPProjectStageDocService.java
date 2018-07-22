package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectStageDoc;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目阶段文档匹配 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectStageDocService extends IService<ProjectStageDoc> {

	/**
	 * 获取项目阶段文档
	 * @return
	 */
	List<ProjectStageDoc> getProjectStageDocs(Long stageId,Integer type,Long categoryId);
	
}
