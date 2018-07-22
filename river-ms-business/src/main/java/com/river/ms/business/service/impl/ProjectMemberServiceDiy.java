package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectMember;
import com.river.ms.business.mapper.ProjectMemberDao;
import com.river.ms.business.service.MPProjectMemberService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目组成员 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectMemberServiceDiy extends ServiceImpl<ProjectMemberDao, ProjectMember>
		implements MPProjectMemberService {

	@Override
	public List<Long> getProjectMember(Long resourceId) {
		List<ProjectMember> result = new ArrayList<>();
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("resourceId", resourceId);
		columnMap.put("state", 1);
		List<ProjectMember> selectByMap = this.baseMapper.selectByMap(columnMap);
		result.addAll(selectByMap);
		List<Long> ids = this.getIds(result);
		return ids;
	}

	private List<Long> getIds(List<ProjectMember> entitys) {
		List<Long> result = new ArrayList<>();
		for (ProjectMember p : entitys) {
			result.add(p.getProjectId());
		}
		return result;
	}

	@Override
	public List<Long> getAllProjectMember(Long resourceId) {
		List<ProjectMember> result = new ArrayList<>();
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("resourceId", resourceId);
		List<ProjectMember> selectByMap = this.baseMapper.selectByMap(columnMap);
		result.addAll(selectByMap);
		List<Long> ids = this.getIds(result);
		return ids;
	}

	@Override
	public List<ProjectMember> isExit(List<ProjectMember> entitys) {
		List<ProjectMember> result = new ArrayList<>();
		Map<Long, ProjectMember> pMap = new HashMap<>();
		if (entitys != null && entitys.size() > 0) {
			Long projectId = null;
			for (ProjectMember p : entitys) {
				projectId = p.getProjectId();
				pMap.put(p.getMemberId(), p);
			}
			if(projectId != null) {
				EntityWrapper<ProjectMember> wrapper = new EntityWrapper<>();
				wrapper.eq("projectId", projectId);
				List<ProjectMember> selectList = this.selectList(wrapper);
				for(ProjectMember p : selectList) {
					pMap.remove(p.getMemberId());
				}
			}
		}
		Set<Entry<Long, ProjectMember>> entrySet = pMap.entrySet();
		for (Entry<Long, ProjectMember> entry : entrySet) {
			result.add(entry.getValue());
		}
		return result;
	}

}
