package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectCategory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目分类 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCategoryService extends IService<ProjectCategory> {

	/**
	 * 获取项目分类
	 * 
	 * @return
	 */
	List<ProjectCategory> getProjectCategorys(Long parentId, Boolean isAll);
	
	List<ProjectCategory> getProjectCategoryList(List<ProjectCategory> list);

	/**
	 * 获取投资类别
	 * 
	 * @return
	 */
	List<ProjectCategory> getCategorys();

	/**
	 * 导出评价指标和经营数据模版
	 */
	public void writeExcelAll(Map<String, Map<Class, List<List<Object>>>> dataList, HttpServletResponse response,
			String fileName);

	Long exist(Long id, String entityCode);

}
