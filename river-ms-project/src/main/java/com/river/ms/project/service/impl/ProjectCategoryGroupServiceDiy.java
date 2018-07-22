package com.river.ms.project.service.impl;

import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.helper.StringHelper;
import com.river.ms.project.entity.ProjectCategory;
import com.river.ms.project.entity.ProjectCategoryGroup;
import com.river.ms.project.mapper.ProjectCategoryGroupDao;
import com.river.ms.project.service.MPProjectCategoryGroupService;
import com.river.ms.project.service.MPProjectCategoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行业公司项目分类 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectCategoryGroupServiceDiy extends ServiceImpl<ProjectCategoryGroupDao, ProjectCategoryGroup>
		implements MPProjectCategoryGroupService {

	@Autowired
	MPProjectCategoryService categoryService;

	@Override
	public List<ProjectCategoryGroup> getProjectCategoryGroup(UserEntity user, Long categoryId) {
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		String groupCode = "";
		for (StationEmployeeEntity s : stationEmployees) {
			if (s.getIsChief() == 1) {
				groupCode = s.getOrganization().getEntityCode();
				break;
			}
		}
		List<ProjectCategoryGroup> selectList = new ArrayList<ProjectCategoryGroup>();
		if (groupCode != null) {
			EntityWrapper<ProjectCategoryGroup> ew = new EntityWrapper<>();
			ew.eq("groupCode", groupCode);
			ew.eq("categoryId", categoryId);
			ew.eq("parentId", 0);
			selectList = this.baseMapper.selectList(ew);
			if (selectList != null && selectList.size() > 0) {
				for (ProjectCategoryGroup p : selectList) {
					p.setChildProjectCategoryGroup(this.getChild(p.getEntityId()));
				}
			}
		}
		return selectList;
	}

	private List<ProjectCategoryGroup> getChild(Long parentId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parentId", parentId);
		List<ProjectCategoryGroup> selectList = this.baseMapper.selectByMap(columnMap);
		if (selectList != null && selectList.size() > 0) {
			for (ProjectCategoryGroup projectCategory : selectList) {
				projectCategory.setChildProjectCategoryGroup(this.getChild(projectCategory.getEntityId()));
			}
		}
		return selectList;
	}

	@Override
	public Boolean insertProjectCategoryGroup(List<ProjectCategory> categorys, Long categoryId, String groupCode) {

		EntityWrapper<ProjectCategory> projectCategoryWrapper = new EntityWrapper<>();
		List<ProjectCategory> projectCategoryList = this.categoryService.selectList(projectCategoryWrapper);

		// 获取当前公司，当前投资类别下面的项目类型
		EntityWrapper<ProjectCategoryGroup> projectCategoryGroupWrapper = new EntityWrapper<>();
		projectCategoryGroupWrapper.eq("categoryId", categoryId);
		projectCategoryGroupWrapper.eq("groupCode", groupCode);
		List<ProjectCategoryGroup> nowPcg = this.selectList(projectCategoryGroupWrapper);

		// 添加新的项目类型
		List<ProjectCategoryGroup> projectCategoryGroups = new ArrayList<>();
		for (ProjectCategory c : categorys) {
			Boolean exit = this.isExit(nowPcg, c);
			if (!exit) {
				ProjectCategoryGroup p = new ProjectCategoryGroup();
				p.setEntityCode(c.getEntityCode());
				p.setEntityTitle(c.getEntityTitle());
				p.setEntityGUID(StringHelper.generateGUID());
				if (c.getEntityDesc() != null) {
					p.setEntityDesc(c.getEntityDesc());
				}
				p.setCategoryId(categoryId);
				p.setSrcCategoryId(c.getEntityId());
				p.setCreateTime(new Date());
				p.setGroupCode(groupCode);
				projectCategoryGroups.add(p);
			}
		}
		boolean insertBatch = false;
		if (projectCategoryGroups != null && projectCategoryGroups.size() > 0) {
			insertBatch = this.insertBatch(projectCategoryGroups);
		}
		if (insertBatch) {
			// 修改父级ID
			nowPcg = this.selectList(projectCategoryGroupWrapper);
			this.updateParentId(nowPcg, projectCategoryList);
			boolean updateBatchById = this.updateBatchById(nowPcg);
			return updateBatchById;
		}
		return true;
	}

	public void updateParentId(List<ProjectCategoryGroup> nowPcg, List<ProjectCategory> projectCategoryList) {
		for (ProjectCategoryGroup p : nowPcg) {
			p.setParentId(this.getParentId(p, nowPcg, projectCategoryList));
		}
	}

	public Long getParentId(ProjectCategoryGroup p, List<ProjectCategoryGroup> nowPcg,
			List<ProjectCategory> projectCategoryList) {
		Long result = new Long(0);
		String parentProjectCategoryById = this.getParentProjectCategoryById(projectCategoryList, p);
		if (parentProjectCategoryById != null && !parentProjectCategoryById.equals("")) {
			for (ProjectCategoryGroup pcg : nowPcg) {
				if (pcg.getEntityCode().equals(parentProjectCategoryById)) {
					result = pcg.getEntityId();
				}
			}
		}
		return result;
	}

	public String getParentProjectCategoryById(List<ProjectCategory> projectCategoryList, ProjectCategoryGroup p) {
		String code = "";
		for (ProjectCategory c : projectCategoryList) {
			if (p.getSrcCategoryId().equals(c.getEntityId())) {
				for (ProjectCategory w : projectCategoryList) {
					if (w.getEntityId().equals(c.getParentId())) {
						code = w.getEntityCode();
					}
				}
			}
		}
		return code;
	}

	public Boolean isExit(List<ProjectCategoryGroup> nowPcg, ProjectCategory p) {
		Boolean result = new Boolean(false);
		for (ProjectCategoryGroup c : nowPcg) {
			if (c.getSrcCategoryId().equals(p.getEntityId())) {
				result = true;
				break;
			}
		}
		return result;
	}

}
