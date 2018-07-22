package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectPartnerMember;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 合伙人 服务类
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
public interface MPProjectPartnerMemberService extends IService<ProjectPartnerMember> {
	void updateAllRateByProjectId(Long projectId);
}
