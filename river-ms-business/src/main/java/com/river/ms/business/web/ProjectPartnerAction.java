package com.river.ms.business.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectPartner;
import com.river.ms.business.entity.ProjectPartnerMember;
import com.river.ms.business.service.MPProjectPartnerMemberService;
import com.river.ms.business.service.MPProjectPartnerService;

/**
 * <p>
 * 合伙方案 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@RestController
@RequestMapping("/projectPartner")
public class ProjectPartnerAction {

	@Autowired
	MPProjectPartnerService projectPartnerService;

	@Autowired
	MPProjectPartnerMemberService projectPartnerMemberService;

	@RequestMapping(value = "/insertProjectPartner", method = RequestMethod.POST)
	public JsonResult insertProjectPartner(HttpServletRequest request, @Valid ProjectPartner projectPartner,
			BindingResult res, @RequestParam(name = "partnerMember", required = false) String partnerMember) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		UserEntity user = SessionUtils.getUser(request);
		if (user == null) {
			return JsonResult.failure("用户信息异常！");
		}
		boolean insertProjectPartner = this.projectPartnerService.insertProjectPartner(projectPartner, partnerMember,
				user);
		return JsonResult.success(insertProjectPartner);
	}

	@RequestMapping(value = "/getProjectPartner", method = RequestMethod.POST)
	public JsonResult getProjectPartner(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId) {
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目信息异常！");
		}
		ProjectPartner projectPartner = this.projectPartnerService.selectProjectPartner(projectId);
		return JsonResult.success(projectPartner);
	}

	@RequestMapping(value = "/insertProjectPartnerMember", method = RequestMethod.POST)
	public JsonResult insertProjectPartnerMember(HttpServletRequest request,
			@Valid ProjectPartnerMember projectPartnerMember, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		projectPartnerMember.setCreateTime(new Date());
		boolean insert = this.projectPartnerMemberService.insert(projectPartnerMember);
		//更新比例
		projectPartnerMemberService.updateAllRateByProjectId(projectPartnerMember.getProjectId());

		return JsonResult.success(insert);
	}

	@RequestMapping(value = "/removeProjectPartnerMember", method = RequestMethod.POST)
	public JsonResult removeProjectPartnerMember(HttpServletRequest request,
			@RequestParam(name = "entityId") Long entityId) {
		ProjectPartnerMember selectById = this.projectPartnerMemberService.selectById(entityId);
		boolean deleteById = this.projectPartnerMemberService.deleteById(entityId);
		
		projectPartnerMemberService.updateAllRateByProjectId(selectById.getProjectId());

		return JsonResult.success(deleteById);
	}

	@RequestMapping(value = "/updateProjectPartnerAndMember", method = RequestMethod.POST)
	public JsonResult updateProjectPartnerAndMember(HttpServletRequest request, 
			@RequestParam(name = "partner", required = false) String partner,
			@RequestParam(name = "partnerMember", required = false) String partnerMember) {
		UserEntity user = SessionUtils.getUser(request);
		boolean updateProjectPartner = this.projectPartnerService.updateProjectPartner(partner, partnerMember, user);

		return JsonResult.success(updateProjectPartner);
	}
	
	@RequestMapping(value = "/updateProjectPartner", method = RequestMethod.POST)
	public JsonResult updateProjectPartner(HttpServletRequest request, @Valid ProjectPartner projectPartner,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		boolean updateById = this.projectPartnerService.updateById(projectPartner);
		return JsonResult.success(updateById);
	}
	
	@RequestMapping(value = "/updateProjectPartnerMember", method = RequestMethod.POST)
	public JsonResult updateProjectPartnerMember(HttpServletRequest request, @Valid ProjectPartnerMember projectPartnerMember,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		boolean updateById = this.projectPartnerMemberService.updateById(projectPartnerMember);
		return JsonResult.success(updateById);
	}

}
