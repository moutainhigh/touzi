package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectFlow;
import com.river.ms.business.mapper.ProjectFlowDao;
import com.river.ms.business.service.MPProjectFlowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 流程定义表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Service
//@Transactional
public class ProjectFlowServiceDiy extends ServiceImpl<ProjectFlowDao, ProjectFlow> implements MPProjectFlowService {

	@Override
	public Long isExist(Long entityId, String entityCode) {
		return this.baseMapper.isExist(entityId,entityCode);
	}

}
