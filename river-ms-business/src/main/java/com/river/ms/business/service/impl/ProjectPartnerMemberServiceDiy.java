package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectPartnerMember;
import com.river.ms.business.mapper.ProjectPartnerMemberDao;
import com.river.ms.business.service.MPProjectPartnerMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 合伙人 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@Service
public class ProjectPartnerMemberServiceDiy extends ServiceImpl<ProjectPartnerMemberDao, ProjectPartnerMember> implements MPProjectPartnerMemberService {

	@Override
	public void updateAllRateByProjectId(Long projectId) {
		this.baseMapper.updateAllRateByProjectId(projectId);
	}

}
