package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.mapper.ProjectFlowStartDao;
import com.river.ms.business.service.MPProjectFlowStartService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 启动流程配置 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Service
//@Transactional
public class ProjectFlowStartServiceDiy extends ServiceImpl<ProjectFlowStartDao, ProjectFlowStart>
		implements MPProjectFlowStartService {

	@Override
	public List<ProjectFlowStart> getProjectFlowStart(Long entityId, Long groupCategoryId, Integer type) {
		List<ProjectFlowStart> selectProjectFlowStart = new ArrayList<>();
		selectProjectFlowStart = this.baseMapper.selectProjectFlowStart(entityId, groupCategoryId, type);
		if (selectProjectFlowStart == null || selectProjectFlowStart.size() == 0) {
			Long parentCategroyId = this.baseMapper.getParentCategroyId(groupCategoryId);
			if (parentCategroyId != null && parentCategroyId != 0) {
				selectProjectFlowStart = this.baseMapper.selectProjectFlowStart(entityId, parentCategroyId, type);
				if (selectProjectFlowStart == null || selectProjectFlowStart.size() == 0) {
					selectProjectFlowStart = this.baseMapper.selectProjectFlowStart(entityId, null, type);
				}
			} else {
				selectProjectFlowStart = this.baseMapper.selectProjectFlowStart(entityId, null, type);
			}
		}
		return selectProjectFlowStart;
	}

	@Override
	public Long isExist(Long entityId, Long flowId, Long groupCategoryId, Integer type) {
		return this.baseMapper.isExist(entityId,flowId,groupCategoryId,type);
	}

	@Override
	public List<ProjectFlowStart> selectProjectFlowStart(Long groupCategoryId, Integer type) {
		List<ProjectFlowStart> selectProjectFlowStart = new ArrayList<>();
		selectProjectFlowStart = this.baseMapper.selectProjectFlowStart(null, groupCategoryId, type);
		return selectProjectFlowStart;
	}

}
