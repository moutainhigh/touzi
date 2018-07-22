package com.river.ms.business.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectKeypoint;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectKeypointService;

/**
 * <p>
 * 项目关键指标 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectKeypoint")
public class ProjectKeypointAction {

	@Autowired
	private MPProjectKeypointService service;

	/**
	 * 项目关键指标添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectKeypoint", method = RequestMethod.POST)
	public JsonResult insertProjectKeypoint(HttpServletRequest request, ProjectKeypoint projectKeypoint) {
		EntityWrapper<ProjectKeypoint> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectKeypoint.getProjectId());
		List<ProjectKeypoint> selectList = this.service.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			if (projectKeypoint.getEntityId() != null && projectKeypoint.getEntityId() != 0) {
				Boolean b = false;
				for (ProjectKeypoint pk : selectList) {
					if(pk.getEntityId().equals(projectKeypoint.getEntityId())) {
						b = true;
					}
				}
				if(b) {
					projectKeypoint.setUpdateTime(new Date());
					boolean updateById = this.service.updateById(projectKeypoint);
					return JsonResult.success(updateById);
				}else {
					return JsonResult.failure("更新数据异常！");
				}
			} else {
				return JsonResult.failure("更新数据异常！");
			}
		}else {
			projectKeypoint.setCreateTime(new Date());
			boolean insert = this.service.insert(projectKeypoint);
			return JsonResult.success(insert);
		}
	}

	/**
	 * 项目关键指标数据查看
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/selectProjectKeypoint", method = RequestMethod.POST)
	public JsonResult selectProjectKeypoint(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectKeypoint> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 项目关键指标修改
	 * 
	 * @param request
	 * @param projectKeypoint
	 * @return
	 */
	@RequestMapping(value = "/updateProjectKeypoint", method = RequestMethod.POST)
	public JsonResult updateProjectKeypoint(HttpServletRequest request, ProjectKeypoint projectKeypoint) {
		boolean updateById = this.service.updateById(projectKeypoint);
		if (updateById) {
			return JsonResult.success(projectKeypoint);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 项目关键指标统计
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/projectKeypointStatistics", method = RequestMethod.POST)
	public Map<String, Object> projectKeypointStatistics(HttpServletRequest request,
			@RequestParam(name = "projectIds",required=false) List<Long> projectIds) {
		Map<String, Object> result = new HashMap<>();
		if (projectIds != null && projectIds.size() > 0) {
			EntityWrapper<ProjectKeypoint> wrapper = new EntityWrapper<>();
			wrapper.in("projectId", projectIds);
			BigDecimal cost = new BigDecimal(0);
			List<ProjectKeypoint> selectList = service.selectList(wrapper);
			for (ProjectKeypoint p : selectList) {
				if (p.getF1() != null) {
					cost = cost.add(p.getF1());
				}
			}
			result.put("cost", cost);
		} else {
			result.put("tzze", null);
		}
		return result;
	}
}
