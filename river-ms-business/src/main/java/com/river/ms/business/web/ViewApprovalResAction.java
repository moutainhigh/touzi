package com.river.ms.business.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ViewApprovalRes;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.service.MPViewApprovalResService;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@RestController
@RequestMapping("/viewApprovalRes")
public class ViewApprovalResAction {

	@Autowired
	MPViewApprovalResService service;

	@Autowired
	ToProjectImpl toProject;

	/**
	 * 查看项目审批结果
	 * 
	 * @param request
	 * @param projectId
	 *            项目ID
	 * @param stage
	 *            项目阶段
	 * @param type
	 *            1-专家，0-其他
	 * @return
	 */
	@RequestMapping(value = "/getProjectApproval", method = RequestMethod.POST)
	public JsonResult getProjectApproval(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage", required = false,defaultValue = "0") Long stage,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(name = "isAll", required = false,defaultValue = "true") Boolean isAll) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (type != null && type == -1) {
			List<ViewApprovalRes> selectApproval = this.service.getProjectApproval(projectId, type,stage);
			return JsonResult.success(selectApproval);
		}
		List<Long> childNodeIdList = new ArrayList<>();
		// 获取项目阶段ID
		/*if (stage == null) {
			stage = this.toProject.getStageIdByProjectId(headerMap, projectId);
		}*/
		if (stage != null && stage != 0) {
			if (isAll) {
				// 获取项目大阶段ID
				Long bigStage = new Long(0);
				Map<Long, Long> projectStageNode = this.toProject.getProjectStageNode(headerMap, stage);
				Set<Entry<Long, Long>> entrySet = projectStageNode.entrySet();
				for (Entry<Long, Long> entry : entrySet) {
					bigStage = entry.getKey();
				}
				// 获取项目所有小阶段IDs
				JsonResult childNodeIds = this.toProject.getAllChildNodeById(headerMap, bigStage);
				childNodeIdList = (List<Long>) childNodeIds.getData();
			}
		}
		// 查询
		List<ViewApprovalRes> selectApproval = this.service.selectApproval(projectId, childNodeIdList, type,processInstanceId,stage,isAll);
		return JsonResult.success(selectApproval);
	}

}
