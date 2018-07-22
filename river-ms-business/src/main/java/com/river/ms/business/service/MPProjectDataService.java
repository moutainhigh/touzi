package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectData;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 可研数据录入 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectDataService extends IService<ProjectData> {

	List<ProjectData> getProjectData(ProjectData entity, List<Long> ids);

	/**
	 * 根据模版导入数据
	 * 
	 * @param file
	 * @param projectId
	 * @param user
	 */
	void insertBatchByExcelTempl(MultipartFile file, Long projectId, UserEntity user);

	/**
	 * 根据模版导入数据
	 * 
	 * @param file
	 * @param projectId
	 * @param user
	 */
	void insertBatchByExcelTempl(MultipartFile file, Long projectId, UserEntity user, Integer type, Integer year,Integer entityType);
}
