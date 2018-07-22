package com.river.ms.business.web;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToSSOService;

/**
 * <p>
 * 待办事项：每次处理完更新此表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/projectToActiviti")
public class ProjectToActiviti {

	@Autowired
	ToActivitiService service;
	@Autowired
	ToSSOService sso;
	/**
	 * 筛选用户所能看到的项目ID
	 * 测试用
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public JsonResult getProjectToDo(HttpServletRequest request,@RequestParam("roleId") Long roleId) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		ActivitiResult startActiviti = service.startActiviti(headerMap, "huangping", (long) 9, "setupApply", "test");
		return JsonResult.success(startActiviti);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public String mail(HttpServletRequest request) {
		return sso.mail();
	}
	
}

