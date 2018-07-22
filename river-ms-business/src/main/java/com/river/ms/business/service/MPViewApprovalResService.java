package com.river.ms.business.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.business.entity.ViewApprovalRes;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
public interface MPViewApprovalResService extends IService<ViewApprovalRes> {

	/**
	 * 筛选项目评审
	 * @param projectId
	 * @param type
	 * @return
	 */
	List<ViewApprovalRes> selectApproval(Long projectId,List<Long> stageIds, Integer type,String processInstanceId,Long stage,Boolean isAll);
	List<ViewApprovalRes>	getProjectApproval(Long projectId,Integer type,Long stage);
}
