package com.river.ms.project.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectCategoryGroup;
import com.river.ms.project.service.MPProjectCategoryGroupService;

/**
 * <p>
 * 行业公司项目分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCategoryGroup")
public class ProjectCategoryGroupAction {

	@Autowired
	MPProjectCategoryGroupService service;

	/**
	 * 获取项目分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectCategoryGroup", method = RequestMethod.POST)
	public JsonResult getProjectCategoryGroup(HttpServletRequest request,
			@RequestParam(value = "categoryId") Long categoryId) {
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectCategoryGroup> projectCategoryGroup = this.service.getProjectCategoryGroup(user, categoryId);
		return JsonResult.success(projectCategoryGroup);
	}

	/**
	 * 获取全部项目分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectCategoryGroupAll", method = RequestMethod.POST)
	public JsonResult getProjectCategoryGroupAll(HttpServletRequest request,
			@RequestParam(value = "categoryId") Long categoryId, @RequestParam(value = "groupCode" ,required=false,defaultValue="") String groupCode) {
		if(groupCode != null && !groupCode.equals("")) {
		}else {
			UserEntity user = SessionUtils.getUser(request);
			EmployeeEntity employee = user.getEmployee();
			List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
			for (StationEmployeeEntity s : stationEmployees) {
				if (s.getIsChief() == 1) {
					groupCode = s.getOrganization().getEntityCode();
					break;
				}
			}
		}
		EntityWrapper<ProjectCategoryGroup> ew = new EntityWrapper<>();
		ew.eq("groupCode", groupCode);
		ew.eq("categoryId", categoryId);
		List<ProjectCategoryGroup> selectList = this.service.selectList(ew);

		return JsonResult.success(selectList);
	}

}
