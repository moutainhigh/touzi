package com.river.ms.business.service;

import java.util.List;
import java.util.Map;

import com.river.ms.business.entity.HistoryResult;

/**
 * <p>
 * 项目列表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectListService {

	/**
	 * 根据角色，判断哪个项目可以操作
	 * @param project
	 * @param roleCode
	 * @return
	 */
	Map<Long, Long> getProjectList(Map<String, Object> headerMap , List<Long> stageCode , String roleCode , Long resourceId);
	
	/**
	 * 根据角色，查看参与过的项目
	 * @param project
	 * @param roleCode
	 * @return
	 */
	List<Long> getProjectList(Map<String, Object> headerMap ,List<String> roleCode,Long resourceId,String itCode);
	
	Boolean updateProjectMessage(Integer flowType,String projectMessage);
	
	void getHistoryResultMessage(List<HistoryResult> history,Long projectId,String processInstanceId);
	
}
