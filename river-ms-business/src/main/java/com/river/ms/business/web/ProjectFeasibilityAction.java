package com.river.ms.business.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectFeasibility;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectFeasibilityService;

/**
 * <p>
 * 项目可研 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectFeasibility")
public class ProjectFeasibilityAction {

	@Autowired
	MPProjectFeasibilityService service;

	/**
	 * 添加项目可研
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectFeasibility", method = RequestMethod.POST)
	public JsonResult insertProjectFeasibility(HttpServletRequest request, ProjectFeasibility entity,
			@RequestParam(name = "str",required = false) String str,
			@RequestParam(name = "docStr", required = false) String docStr) {
		entity.setState(0);
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		boolean insert = this.service.insertProjectFeasibility(entity, headerMap,str,docStr,user);
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 打回可研资料上传
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectFeasibility", method = RequestMethod.POST)
	public JsonResult updateProjectFeasibility(HttpServletRequest request, ProjectFeasibility entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		Boolean updateProjectFeasibilityState = this.service.updateProjectFeasibilityState(request,
				entity,docStr);
		if (updateProjectFeasibilityState) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}
	
	/**
	 * 获取可研资料
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectFeasibility", method = RequestMethod.POST)
	public JsonResult getProjectFeasibility(HttpServletRequest request, @RequestParam(name = "projectId")Long projectId,
			 @RequestParam(name = "processInstanceId",required = false) String processInstanceId) {
		EntityWrapper<ProjectFeasibility> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		if(processInstanceId != null && !processInstanceId.equals("")) {
			wrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
		}
		List<ProjectFeasibility> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}
}
