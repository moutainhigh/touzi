package com.river.ms.res.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.feign.service.impl.ToSystemImpl;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPResListService;

/**
 * <p>
 * 资源列表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resListManagement")
public class ResListManagementAction {

	@Autowired
	MPResListService service;

	@Autowired
	ToSystemImpl toSystem;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(HttpServletRequest request, @Valid ResList resEntity, BindingResult res,
			@RequestParam(value = "employeeId", required = false, defaultValue = "-1") Long employeeId,
			@RequestParam(value = "eId", required = false) String eId) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Boolean exist = this.toSystem.codeIsExit(headerMap, employeeId, resEntity.getItcode());
		Long exist2 = this.service.exist(-1l, resEntity.getItcode());
		if (!exist && exist2 == 0) {
			Boolean insertRes = this.service.insertRes(headerMap, resEntity, employeeId, eId);
			return JsonResult.success(insertRes);
		} else {
			return ErrorResult.CODE_EXIST;
		}
	}
}
