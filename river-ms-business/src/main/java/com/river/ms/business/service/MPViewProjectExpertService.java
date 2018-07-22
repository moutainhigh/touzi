package com.river.ms.business.service;

import com.river.ms.business.entity.ViewProjectExpert;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-29
 */
public interface MPViewProjectExpertService extends IService<ViewProjectExpert> {

	List<ViewProjectExpert> selectViewProjectExpertByProjectId(Long projectId);
	
	List<ViewProjectExpert> selectAllViewProjectExpertByProjectId(HttpServletRequest request,Long projectId,Long stage,String processInstanceId,Boolean isMerge);
}
