package com.river.ms.project.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.service.MPResRefRoleOrgService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2018-04-19
 */
@RestController
@RequestMapping("/resRefRoleOrg")
public class ResRefRoleOrgAction {

	@Autowired
	MPResRefRoleOrgService service;

	@RequestMapping(value = "/getResRefRoleOrg", method = RequestMethod.POST)
	public JsonResult getResRefRoleOrg(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Boolean> resRefRoleOrg = this.service.getResRefRoleOrg(user);
		return JsonResult.success(resRefRoleOrg);
	}

}
