package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectNodeRole;
import com.river.ms.project.mapper.ProjectNodeRoleDao;
import com.river.ms.project.service.MPProjectNodeRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程节点角色 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Service
public class ProjectNodeRoleServiceDiy extends ServiceImpl<ProjectNodeRoleDao, ProjectNodeRole>
		implements MPProjectNodeRoleService {

	@Override
	public List<ProjectNodeRole> exist(Long nodeId, List<Long> roleIds) {
		List<ProjectNodeRole> result = new ArrayList<>();
		if (nodeId != null && nodeId != 0 && roleIds != null && roleIds.size() > 0) {
			Map<Long, ProjectNodeRole> map = new HashMap<>();
			for(Long r : roleIds) {
				ProjectNodeRole p = new ProjectNodeRole();
				p.setNodeId(nodeId);
				p.setRoleId(r);
				map.put(r, p);
			}
			EntityWrapper<ProjectNodeRole> wrapper = new EntityWrapper<>();
			wrapper.eq("nodeId", nodeId);
			List<ProjectNodeRole> selectList = this.selectList(wrapper);
			for (ProjectNodeRole projectNodeRole : selectList) {
				map.remove(projectNodeRole.getRoleId());
			}
			Set<Entry<Long, ProjectNodeRole>> entrySet = map.entrySet();
			for (Entry<Long, ProjectNodeRole> entry : entrySet) {
				result.add(entry.getValue());
			}
		}
		return result;
	}

}
