package com.river.ms.business.service.impl;

import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.feign.serviceImpl.ToSystemImpl;
import com.river.ms.business.mapper.ProjectExpertDao;
import com.river.ms.business.service.MPProjectExpertService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目各阶段专家确定 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectExpertServiceDiy extends ServiceImpl<ProjectExpertDao, ProjectExpert>
		implements MPProjectExpertService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ToSystemImpl toSystem;

	@Override
	public Map<Long, Long> getProjectExpert(Map<String, Object> headerMap, List<Long> stageCode, Long resourceId,
			Long role) {
		List<ProjectExpert> result = new ArrayList<>();
		for (Long entry : stageCode) {
			Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap, entry);
			Set<Long> keySet = projectStageNode.keySet();
			for (Long key : keySet) {
				Map<String, Object> columnMap = new HashMap<>();
				columnMap.put("stage", key);
				columnMap.put("resourceId", resourceId);
				columnMap.put("state", 1);
				columnMap.put("role", role);
				List<ProjectExpert> selectByMap = this.baseMapper.selectByMap(columnMap);
				if (selectByMap != null && selectByMap.size() > 0) {
					result.addAll(selectByMap);
				}
			}
		}
		Map<Long, Long> ids = this.getIds(result);
		return ids;
	}

	private Map<Long, Long> getIds(List<ProjectExpert> entitys) {
		Map<Long, Long> result = new HashMap<>();
		for (ProjectExpert p : entitys) {
			result.put(p.getProjectId(), p.getStage());
		}
		return result;
	}

	@Override
	public List<Long> getAllProjects(Long resourceId) {
		List<Long> allProjectIds = this.baseMapper.getAllProjectIds(resourceId);
		return allProjectIds;
	}

	@Override
	public List<ProjectExpert> selectProjectExpert(HttpServletRequest request, Long projectId, Long stage) {
		List<ProjectExpert> selectProjectExpert = this.baseMapper.selectProjectExpert(projectId, stage);
		if (selectProjectExpert != null && selectProjectExpert.size() > 0) {
			selectProjectExpert = this.isExit(selectProjectExpert);
			this.getGongsi(request, selectProjectExpert);
		}
		return selectProjectExpert;
	}

	public List<ProjectExpert> isExit(List<ProjectExpert> projectExpert) {
		List<ProjectExpert> result = new ArrayList<>();
		Map<Long, ProjectExpert> map = new HashMap<>();
		for (ProjectExpert p : projectExpert) {
			map.put(p.getResourceId(), p);
		}
		Set<Entry<Long, ProjectExpert>> entrySet = map.entrySet();
		for (Entry<Long, ProjectExpert> entry : entrySet) {
			result.add(entry.getValue());
		}
		return result;
	}

	public void getGongsi(HttpServletRequest request, List<ProjectExpert> resList) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		List<Long> userIds = new ArrayList<>();
		for (ProjectExpert r : resList) {
			userIds.add(r.getUserId());
		}
		if (userIds != null && userIds.size() > 0) {
			Map<Long, String> gongSi = this.toSystem.getGongSi(headerMap, userIds);
			for (ProjectExpert r : resList) {
				String string = gongSi.get(r.getUserId());
				if (string != null && !string.equals("")) {
					r.setGongsi(string);
				}
			}
		}
	}

	@Override
	public Boolean insertProjectExpert(List<ProjectExpert> entitys) {
		List<ProjectExpert> result = new ArrayList<>();
		Map<Long,ProjectExpert> map = new HashMap<>();
		Long projectId = null;
		Long stage = null;
		for(ProjectExpert p : entitys) {
			map.put(p.getResourceId(), p);
			projectId = p.getProjectId();
			stage = p.getStage();
		}
		if(projectId != null && projectId != 0 && stage != null && stage != 0) {
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("projectId", projectId);
			columnMap.put("stage", stage);
			List<ProjectExpert> selectByMap = this.selectByMap(columnMap);
			for (ProjectExpert projectExpert : selectByMap) {
				map.remove(projectExpert.getResourceId());
			}
			Set<Entry<Long, ProjectExpert>> entrySet = map.entrySet();
			for (Entry<Long, ProjectExpert> entry : entrySet) {
				result.add(entry.getValue());
			}
			if(result != null && result.size()>0) {
				boolean insertBatch = this.insertBatch(result);
				return insertBatch;
			}
			return true;
		}else {
			return false;
		}
	}

}
