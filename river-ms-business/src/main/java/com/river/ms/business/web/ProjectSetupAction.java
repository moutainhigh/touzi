package com.river.ms.business.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectSetup;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectSetupService;

/**
 * <p>
 * 项目立项申请 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectSetup")
public class ProjectSetupAction {

	@Autowired
	MPProjectSetupService service;

	/**
	 * 项目立项
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectSetup", method = RequestMethod.POST)
	public JsonResult insertProjectSetup(HttpServletRequest request, ProjectSetup entity,
			@RequestParam(name = "str", required = false) String str,
			@RequestParam(name = "docStr", required = false) String docStr) {
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean insertProjectSetup = this.service.insertProjectSetup(entity, headerMap, str, docStr,user);
		if (insertProjectSetup) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}
	/**
	 * 打回立项资料上传
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectSetup", method = RequestMethod.POST)
	public JsonResult updateProjectSetup(HttpServletRequest request, ProjectSetup entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		System.out.println(docStr);
		Boolean updateProjectSetup = this.service.updateProjectSetup(request, entity,docStr);
		if (updateProjectSetup) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	/**
	 * 获取立项资料
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectSetup", method = RequestMethod.POST)
	public JsonResult getProjectSetup(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		EntityWrapper<ProjectSetup> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		if (processInstanceId != null && !processInstanceId.equals("")) {
			wrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
		}
		List<ProjectSetup> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}

}
