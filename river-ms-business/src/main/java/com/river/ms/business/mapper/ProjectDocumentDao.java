package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectDocument;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目文档 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectDocumentDao extends BaseMapper<ProjectDocument> {
	/**
	 * 
	 * @param projectId
	 * @param stage
	 * @param greatStage
	 * @param type
	 * @return
	 */
	List<ProjectDocument> getByProjectId(@Param("projectId") Long projectId, @Param("stage") Long stage,
			@Param("greatStage") Long greatStage, @Param("type") Long type,
			@Param("processInstanceId") String processInstanceId, @Param("isAll") Boolean isAll);
	// List<ProjectDocument> selectProjectDocuments(Long projectId, Long stage, Long
	// greatStage, Long type);

}
