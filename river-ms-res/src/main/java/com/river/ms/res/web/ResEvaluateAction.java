package com.river.ms.res.web;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ResEvaluate;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPResEvaluateService;

/**
 * <p>
 * 资源评价 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resEvaluate")
public class ResEvaluateAction {

	@Autowired
	MPResEvaluateService service;

	/**
	 * 插入资源评价
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertResEvaluate", method = RequestMethod.POST)
	public JsonResult insertResEvaluate(HttpServletRequest request, ResEvaluate entity) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.failure("用户信息失效，请重新登录");
		entity.setEvaluateId(user.getResId());
		entity.setEvaluateItcode(user.getEntityCode());
		boolean insertResTagHistory = this.service.insert(entity);
		if (insertResTagHistory) {
			return new JsonResult(0, "OK", entity);
		} else {
			return ErrorResult.INSERT_ERROR;
		}
	}

	@RequestMapping(value = "/insertResEvaluateAndTag", method = RequestMethod.POST)
	public JsonResult insertResEvaluateAndTag(HttpServletRequest request, @RequestParam(name = "itcode") String itcode,
			@RequestParam(name = "resourceId") Long resourceId, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "tags[]", required = false) List<String> tags,
			@RequestParam(name = "content", required = false) String content,
			@RequestParam(name = "score") BigDecimal score) {
		boolean insertResEvaluateAndTag = this.service.insertResEvaluateAndTag(request, itcode, resourceId, projectId,
				tags, content, score);
		return JsonResult.success(insertResEvaluateAndTag);
	}
}
