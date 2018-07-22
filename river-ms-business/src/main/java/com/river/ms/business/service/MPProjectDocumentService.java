package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectDocument;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目文档 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectDocumentService extends IService<ProjectDocument> {
	List<ProjectDocument> getByProjectId(Long projectId,Long stage,
			Long greatStage,Long type,String processInstanceId,Boolean isAll);
	//List<ProjectDocument> selectProjectDocuments(Long projectId, Long stage, Long greatStage, Long type);

}
