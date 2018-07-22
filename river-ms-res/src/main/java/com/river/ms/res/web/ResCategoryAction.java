package com.river.ms.res.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.service.MPResCategoryService;

/**
 * <p>
 * 资源分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resCategory")
public class ResCategoryAction {
	@Autowired
	MPResCategoryService service;
	
	@RequestMapping(value = "/getByitcode", method = RequestMethod.POST)
	public JsonResult getByitcode(@RequestParam(value = "itcode") String itcode) {
		List<ResCategory> res = this.service.getByitCode(itcode);
			return JsonResult.success(res);
	}
}

