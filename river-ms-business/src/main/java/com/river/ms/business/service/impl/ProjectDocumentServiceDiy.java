package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.mapper.ProjectDocumentDao;
import com.river.ms.business.service.MPProjectDocumentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目文档 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectDocumentServiceDiy extends ServiceImpl<ProjectDocumentDao, ProjectDocument>
		implements MPProjectDocumentService {
	/**
	 * 
	 */
	@Override
	public	List<ProjectDocument> getByProjectId(Long projectId,Long stage,
			Long greatStage,Long type,String processInstanceId,Boolean isAll){
		return this.baseMapper.getByProjectId(projectId, stage, greatStage, type,processInstanceId,isAll);
	}
	/*@Override
	public List<ProjectDocument> selectProjectDocuments(Long projectId, Long stage, Long greatStage, Long type) {
		return this.baseMapper.selectProjectDocuments(projectId,stage, greatStage, type);
	}*/

}
