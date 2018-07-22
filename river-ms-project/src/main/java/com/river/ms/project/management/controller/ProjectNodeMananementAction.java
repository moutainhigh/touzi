package com.river.ms.project.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectNode;
import com.river.ms.project.entity.ProjectNodeRole;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectNodeRoleService;
import com.river.ms.project.service.MPProjectNodeService;

/**
 * <p>
 * 流程节点定义 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/projectNodeMananement")
public class ProjectNodeMananementAction {

	@Autowired
	MPProjectNodeService service;

	@Autowired
	MPProjectNodeRoleService nodeRoleService;

	/**
	 * 添加或者修改节点
	 * 
	 * @param response
	 * @param entity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletResponse response, @Valid ProjectNode entity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if (entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.exist(id, entity.getEntityCode());
		if (exist == 0) {
			boolean insertOrUpdate = this.service.insertOrUpdate(entity);
			if (insertOrUpdate) {
				return JsonResult.success(entity);
			} else {
				return JsonResult.failure("操作失败！");
			}
		} else {
			return ErrorResult.CODE_EXIST;
		}
	}

	/**
	 * 删除节点
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResult delete(HttpServletRequest request, @PathVariable(value = "id") Long id) {
		boolean deleteById = this.service.deleteById(id);
		return JsonResult.success(deleteById);
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public JsonResult select(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		EntityWrapper<ProjectNode> wrapper = new EntityWrapper<>();
		List<ProjectNode> selectList = this.service.selectList(wrapper);
		PageInfo<ProjectNode> pageInfo = new PageInfo<ProjectNode>(selectList, 5);
		return JsonResult.successPage(selectList,pageInfo);
	}

	/**
	 * 添加节点角色配置
	 * 
	 * @param response
	 * @param nodeId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/insertProjectNodeRole", method = RequestMethod.POST)
	public JsonResult insertProjectNodeRole(HttpServletResponse response, @RequestParam(name = "nodeId") Long nodeId,
			@RequestParam(name = "roleIds[]") List<Long> roleIds) {
		List<ProjectNodeRole> exist = this.nodeRoleService.exist(nodeId, roleIds);
		if (exist != null && exist.size() > 0) {
			boolean insertBatch = this.nodeRoleService.insertBatch(exist);
			return JsonResult.success(insertBatch);
		}
		return JsonResult.success(true);
	}

	/**
	 * 删除节点角色配置
	 * 
	 * @param response
	 * @param nodeId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/deleteProjectNodeRole", method = RequestMethod.POST)
	public JsonResult deleteProjectNodeRole(HttpServletResponse response, @RequestParam(name = "nodeId") Long nodeId,
			@RequestParam(name = "roleIds[]") List<Long> roleIds) {
		EntityWrapper<ProjectNodeRole> wrapper = new EntityWrapper<>();
		wrapper.eq("nodeId", nodeId);
		wrapper.in("roleId", roleIds);
		boolean delete = this.nodeRoleService.delete(wrapper);
		return JsonResult.success(delete);
	}
	
	/**
	 * 获取节点角色配置
	 * 
	 * @param response
	 * @param nodeId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/getProjectNodeRole", method = RequestMethod.POST)
	public JsonResult getProjectNodeRole(HttpServletResponse response, @RequestParam(name = "nodeId") Long nodeId) {
		EntityWrapper<ProjectNodeRole> wrapper = new EntityWrapper<>();
		wrapper.eq("nodeId", nodeId);
		List<ProjectNodeRole> selectList = this.nodeRoleService.selectList(wrapper);
		return JsonResult.success(selectList);
	}
}
