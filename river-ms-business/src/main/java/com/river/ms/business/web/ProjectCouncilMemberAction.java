package com.river.ms.business.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectCouncilMember;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectCouncilMemberService;

/**
 * <p>
 * 项目投决评议会、变更评议会等参会人员 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCouncilMember")
public class ProjectCouncilMemberAction {
	
	@Autowired
	MPProjectCouncilMemberService service;

	/**
	 * 添加项目投决评议会，变更评审会人员
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCouncilMember", method = RequestMethod.POST)
	public JsonResult insertProjectCouncilMember(HttpServletRequest request, @RequestParam(name="entitys") List<ProjectCouncilMember> entitys) {
		for (ProjectCouncilMember p : entitys) {
			p.setCreateTime(new Date());
		}
		boolean insert = this.service.insertBatch(entitys);
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

}
