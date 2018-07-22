package com.river.ms.business.service.impl;

import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectApproval;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.entity.ProjectOpinion;
import com.river.ms.business.entity.ProjectRisk;
import com.river.ms.business.entity.ViewProjectExpert;
import com.river.ms.business.feign.serviceImpl.ToSystemImpl;
import com.river.ms.business.mapper.ViewProjectExpertDao;
import com.river.ms.business.service.MPViewProjectExpertService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-29
 */
@Service
public class ViewProjectExpertServiceDiy extends ServiceImpl<ViewProjectExpertDao, ViewProjectExpert>
		implements MPViewProjectExpertService {
	
	@Autowired
	ToSystemImpl toSystem;

	@Override
	public List<ViewProjectExpert> selectViewProjectExpertByProjectId(Long projectId) {
		List<ViewProjectExpert> selectViewProjectExpertByProjectId = this.baseMapper
				.selectViewProjectExpertByProjectId(projectId);
		return selectViewProjectExpertByProjectId;
	}

	@Override
	public List<ViewProjectExpert> selectAllViewProjectExpertByProjectId(HttpServletRequest request,Long projectId, Long stage,
			String processInstanceId, Boolean isMerge) {
		List<ViewProjectExpert> selectViewProjectExpertByProjectId = this.baseMapper
				.selectAllViewProjectExpertByProjectId(projectId, stage, processInstanceId);
		this.getGongsi(request, selectViewProjectExpertByProjectId);
		if (isMerge) {
			this.merge(selectViewProjectExpertByProjectId);
		}
		return selectViewProjectExpertByProjectId;
	}

	public void getGongsi(HttpServletRequest request, List<ViewProjectExpert> resList) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		List<Long> userIds = new ArrayList<>();
		for (ViewProjectExpert r : resList) {
			userIds.add(r.getUserId());
		}
		if (userIds != null && userIds.size() > 0) {
			Map<Long, String> gongSi = this.toSystem.getGongSi(headerMap, userIds);
			for (ViewProjectExpert r : resList) {
				String string = gongSi.get(r.getUserId());
				if (string != null && !string.equals("")) {
					r.setGongsi(string);
				}
			}
		}
	}
	
	private void merge(List<ViewProjectExpert> projectExpert) {
		if (projectExpert != null && projectExpert.size() > 0) {
			for (ViewProjectExpert viewProjectExpert : projectExpert) {
				List<ProjectApproval> projectApproval = viewProjectExpert.getProjectApproval();
				List<ProjectRisk> projectRisk = viewProjectExpert.getProjectRisk();
				if (projectApproval != null && projectApproval.size() > 0 && projectRisk != null
						&& projectRisk.size() > 0) {
					for(ProjectApproval approval : projectApproval) {
						for(ProjectRisk risk : projectRisk) {
							if(approval.getStage().equals(risk.getStage())) {
								if (risk.getRiskItems() != null) {
									approval.setRiskItems(risk.getRiskItems());
								}
								if(risk.getLevel() != null) {
									approval.setRiskLevel(risk.getLevel());
								}
								if(risk.getMemo() != null) {
									approval.setRiskMemo(risk.getMemo());
								}
							}
						}
					}
					viewProjectExpert.setProjectRisk(null);
				}
			}
			for (ViewProjectExpert viewProjectExpert : projectExpert) {
				List<ProjectOpinion> projectOpinion = viewProjectExpert.getProjectOpinion();
				if (projectOpinion != null && projectOpinion.size() > 0) {
					List<ProjectApproval> projectApproval = new ArrayList<>();
					for (ProjectOpinion o : projectOpinion) {
						ProjectApproval approval = new ProjectApproval(o);
						projectApproval.add(approval);
					}
					viewProjectExpert.getProjectApproval().addAll(projectApproval);
					viewProjectExpert.setProjectOpinion(null);
				}
			}
		}
	}
}
