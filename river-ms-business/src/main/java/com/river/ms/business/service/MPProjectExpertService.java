package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectExpert;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目各阶段专家确定 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectExpertService extends IService<ProjectExpert> {

	/**
	 * 返回人员有操作权限的项目id列表
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	Map<Long, Long> getProjectExpert(Map<String, Object> headerMap, List<Long> stageCode, Long resourceId, Long role);

	/**
	 * 查询专家参与过的项目
	 * 
	 * @param resourceId
	 * @return
	 */
	List<Long> getAllProjects(Long resourceId);

	List<ProjectExpert> selectProjectExpert(HttpServletRequest request,Long projectId, Long stage);

	Boolean insertProjectExpert(List<ProjectExpert> entitys);
}
