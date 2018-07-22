package com.river.ms.business.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectRisk;
import com.river.ms.business.entity.ProjectRiskItem;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectRiskItemService;
import com.river.ms.business.service.MPProjectRiskService;

/**
 * <p>
 * 风险评议 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/projectRisk")
public class ProjectRiskAction {

	@Autowired
	MPProjectRiskService service;

	@Autowired
	MPProjectRiskItemService riskItemService;

	@Autowired
	ToProjectImpl toProject;

	/**
	 * 添加风险评议
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectRisk", method = RequestMethod.POST)
	public JsonResult insertProjectRisk(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage") Long stage, @RequestParam(value = "level") Integer level,
			@RequestParam(value = "desc", required = false) String descs,
			@RequestParam(value = "strategy", required = false) String strategys) {

		if (projectId == null || projectId == 0) {
			return JsonResult.FAILURE;
		}

		String[] desc = null;
		if (descs != null && !descs.equals("")) {
			desc = descs.split("&黄平&");
		}
		String[] strategy = null;
		if (strategys != null && !strategys.equals("")) {
			strategy = strategys.split("&黄平&");
		}

		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String projectProcessInstanceId = toProject.getProjectProcessInstanceId(headerMap, projectId);

		ProjectRisk entity = new ProjectRisk();
		entity.setProjectId(projectId);
		entity.setLevel(level);
		entity.setStage(stage);
		entity.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		boolean insert = this.service.insert(entity);

		if (!insert) {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
		if (desc != null && desc.length > 0) {
			List<ProjectRiskItem> list = new ArrayList<ProjectRiskItem>();
			ProjectRiskItem item = null;
			for (int i = 0; i < desc.length; i++) {
				item = new ProjectRiskItem();
				item.setDesc(desc[i]);
				item.setStrategy(strategy[i]);
				item.setProjectId(projectId);
				item.setRiskId(entity.getEntityId());
				list.add(item);
			}
			boolean insertBatch = this.riskItemService.insertBatch(list);
			if (!insertBatch) {
				return ErrorResult.INSERT_PROJECT_ERROE;
			}
			entity.setRiskItems(list);
		}
		return JsonResult.success(entity);
	}

	/**
	 * 查看风险评议
	 * 
	 * @param request
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectRisk", method = RequestMethod.POST)
	public JsonResult getProjectRisk(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage") Long stage) {
		List<ProjectRisk> selectProjectRisk = this.service.selectProjectRisk(projectId, stage);
		return JsonResult.success(selectProjectRisk);
	}

}
