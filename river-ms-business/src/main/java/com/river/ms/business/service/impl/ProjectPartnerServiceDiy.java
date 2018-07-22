package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectPartner;
import com.river.ms.business.entity.ProjectPartnerMember;
import com.river.ms.business.mapper.ProjectPartnerDao;
import com.river.ms.business.service.MPProjectPartnerMemberService;
import com.river.ms.business.service.MPProjectPartnerService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 合伙方案 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@Service
public class ProjectPartnerServiceDiy extends ServiceImpl<ProjectPartnerDao, ProjectPartner>
		implements MPProjectPartnerService {

	@Autowired
	MPProjectPartnerMemberService projectPartnerMemberService;

	@Override
	public boolean insertProjectPartner(ProjectPartner projectPartner, String partnerMember, UserEntity user) {
		projectPartner.setCreateTime(new Date());
		projectPartner.setResId(user.getResId());
		projectPartner.setItcode(user.getEntityCode());
		boolean insert = this.insert(projectPartner);
		if (insert) {
			if (partnerMember != null && !partnerMember.equals("")) {
				List<ProjectPartnerMember> entitys = JSONArray.parseArray(partnerMember, ProjectPartnerMember.class);
				if (entitys != null && entitys.size() > 0) {
					for (ProjectPartnerMember pm : entitys) {
						pm.setPartnerId(projectPartner.getEntityId());
						pm.setProjectId(projectPartner.getProjectId());
						pm.setCreateTime(new Date());
					}
					boolean insertBatch = this.projectPartnerMemberService.insertBatch(entitys);
					return insertBatch;
				}
			}
			//更新所有比例
			projectPartnerMemberService.updateAllRateByProjectId(projectPartner.getProjectId());
			return insert;
		}
		return false;
	}

	@Override
	public ProjectPartner selectProjectPartner(Long projectId) {
		return this.baseMapper.selectProjectPartner(projectId);
	}

	@Override
	public boolean updateProjectPartner(String partner, String partnerMember, UserEntity user) {
		boolean updateById = true;
		ProjectPartner projectPartner =null;
		if (partner != null && !partner.equals("")) {
			projectPartner = JSONObject.parseObject(partner, ProjectPartner.class);
			updateById = this.updateById(projectPartner);
		}
		if (partnerMember != null && !partnerMember.equals("")) {
			List<ProjectPartnerMember> entitys = JSONArray.parseArray(partnerMember, ProjectPartnerMember.class);
			if (entitys != null && entitys.size() > 0) {
				updateById = this.projectPartnerMemberService.updateBatchById(entitys);
			}
		}
		//更新所有比例
		if(projectPartner!=null)
		projectPartnerMemberService.updateAllRateByProjectId(projectPartner.getProjectId());

		return updateById;
	}

}
