package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectPartner;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 合伙方案 服务类
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
public interface MPProjectPartnerService extends IService<ProjectPartner> {

	boolean insertProjectPartner(ProjectPartner projectPartner,String partnerMember,UserEntity user);

	ProjectPartner selectProjectPartner(Long projectId);
	
	boolean updateProjectPartner(String partner,String partnerMember,UserEntity user);
}
