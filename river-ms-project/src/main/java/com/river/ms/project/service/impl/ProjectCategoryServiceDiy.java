package com.river.ms.project.service.impl;

import com.river.core.utils.ExcelUtil;
import com.river.ms.project.entity.ProjectCategory;
import com.river.ms.project.mapper.ProjectCategoryDao;
import com.river.ms.project.service.MPProjectCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目分类 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectCategoryServiceDiy extends ServiceImpl<ProjectCategoryDao, ProjectCategory>
		implements MPProjectCategoryService {

	@Override
	public List<ProjectCategory> getProjectCategorys(Long parentId, Boolean isAll) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parentId", parentId);
		List<ProjectCategory> selectList = this.baseMapper.selectByMap(columnMap);
		if (isAll) {
			if (selectList != null && selectList.size() > 0) {
				for (ProjectCategory projectCategory : selectList) {
					projectCategory.setChildProjectCategory(this.getChild(projectCategory.getEntityId()));
				}
			} 
		}
		return selectList;
	}
	
	@Override
	public List<ProjectCategory> getProjectCategoryList(List<ProjectCategory> list) {
		List<ProjectCategory> result = new ArrayList<>();
		for (ProjectCategory p : list) {
			this.getC(p, result);
		}
		return result;
	}
	
	public void getC(ProjectCategory p,List<ProjectCategory> result) {
		result.add(p);
		for(ProjectCategory c : p.getChildProjectCategory()) {
			this.getC(c, result);
		}
	}

	private List<ProjectCategory> getChild(Long parentId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("parentId", parentId);
		List<ProjectCategory> selectList = this.baseMapper.selectByMap(columnMap);
		if (selectList != null && selectList.size() > 0) {
			for (ProjectCategory projectCategory : selectList) {
				projectCategory.setChildProjectCategory(this.getChild(projectCategory.getEntityId()));
			}
		}
		return selectList;
	}

	@Override
	public List<ProjectCategory> getCategorys() {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("entityType", 0);
		columnMap.put("isDelete", 0);
		columnMap.put("isDisable", 0);
		List<ProjectCategory> selectByMap = this.baseMapper.selectByMap(columnMap);
		return selectByMap;
	}

	@Override
	public void writeExcelAll(Map<String, Map<Class, List<List<Object>>>> dataList
			, HttpServletResponse response, String fileName) {
		ExcelUtil excel = new ExcelUtil();
		excel.writeExcelAll(dataList, response, fileName);
	}

	@Override
	public Long exist(Long id, String entityCode) {
		Long exist = this.baseMapper.exist(id, entityCode);
		return exist;
	}
}
