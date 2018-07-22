package com.river.ms.business.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectMilestone;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectMilestoneService;

/**
 * <p>
 * 项目节点 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectMilestone")
public class ProjectMilestoneAction {

	@Autowired
	MPProjectMilestoneService service;

	/**
	 * 添加项目节点
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectMilestone", method = RequestMethod.POST)
	public JsonResult insertProjectMilestone(HttpServletRequest request, ProjectMilestone entity) {
		if(entity.getMilestone().isEmpty()) {
			return JsonResult.failure("项目分期不允许为空");
		}
		if (entity.getEntityId() != null && entity.getEntityId() != 0) {
			Long exist = this.service.exist(entity.getEntityId(), entity.getProjectId(), entity.getMilestone());
			if (exist == 0) {
				entity.setUpdateTime(new Date());
				boolean updateById = this.service.updateById(entity);
				return JsonResult.success(updateById);
			} else {
				return JsonResult.failure("修改数据异常！");
			}
		} else {
			Long exist = this.service.exist(-1l, entity.getProjectId(), entity.getMilestone());
			if (exist == 0) {
				entity.setCreateTime(new Date());
				boolean insert = this.service.insert(entity);
				return JsonResult.success(insert);
			} else {
				return JsonResult.failure("添加数据异常！");
			}
		}
	}

	/**
	 * 修改项目节点
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectMilestone", method = RequestMethod.POST)
	public JsonResult updateProjectMilestone(HttpServletRequest request, ProjectMilestone entity) {
		entity.setUpdateTime(new Date());
		boolean insert = this.service.updateById(entity);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 根据项目ID和分期信息查询
	 * 
	 * @param request
	 * @param projectId
	 * @param milestone
	 * @return
	 */
	@RequestMapping(value = "/queryProjectMilestone", method = RequestMethod.POST)
	public JsonResult queryProjectMilestone(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId, @RequestParam(name = "milestone") String milestone) {
		EntityWrapper<ProjectMilestone> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("milestone", milestone);
		List<ProjectMilestone> selectList = this.service.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			return JsonResult.success(selectList.get(0));
		} else {
			return JsonResult.SUCCESS;
		}
	}

	/**
	 * 项目节点列表
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult projectMilestoneList(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		List<ProjectMilestone> projectMilestoneList = null;
		try {
			projectMilestoneList = this.service.getProjectMilestoneListByProjectId(projectId);
		} catch (Exception e) {
			return ErrorResult.QUERY_ERROE;
		}
		return JsonResult.success(projectMilestoneList);
	}
}
