package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectHistory;
import com.river.ms.business.mapper.ProjectHistoryDao;
import com.river.ms.business.service.MPProjectHistoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目预测数据/历史数据 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectHistoryServiceDiy extends ServiceImpl<ProjectHistoryDao, ProjectHistory> implements MPProjectHistoryService {

	@Override
	public Boolean isExist(ProjectHistory entity) {
		EntityWrapper<ProjectHistory> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", entity.getProjectId());
		wrapper.eq("year", entity.getYear());
		List<ProjectHistory> selectList = this.selectList(wrapper);
		if(selectList != null && selectList.size()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<ProjectHistory> getProjectHistory(Long projectId, Integer entityType) {
		return this.baseMapper.getProjectHistory(projectId, entityType);
	}
}
