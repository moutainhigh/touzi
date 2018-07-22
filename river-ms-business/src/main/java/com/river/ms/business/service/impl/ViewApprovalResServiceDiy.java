package com.river.ms.business.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.business.entity.ViewApprovalRes;
import com.river.ms.business.mapper.ViewApprovalResDao;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.river.ms.business.service.MPViewApprovalResService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@Service
public class ViewApprovalResServiceDiy extends ServiceImpl<ViewApprovalResDao, ViewApprovalRes>
		implements MPViewApprovalResService {

	@Autowired
	MPProjectOpinionConditionService opinionConditionService;

	@Override
	public List<ViewApprovalRes> selectApproval(Long projectId, List<Long> stageIds, Integer type,String processInstanceId,Long stage,Boolean isAll) {
		List<ViewApprovalRes> selectList = this.baseMapper.selectApproval(projectId,stageIds,type,processInstanceId,stage,isAll);
		return selectList;
	}

	@Override
	public List<ViewApprovalRes> getProjectApproval(Long projectId, Integer type,Long stage) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProjectApproval(projectId, type,stage);
	}

}
