package com.river.ms.res.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResTag;
import com.river.ms.res.service.MPResListService;
import com.river.ms.res.service.MPResRefTagService;
import com.river.ms.res.service.MPResTagService;

/**
 * <p>
 * 资源对应标签 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resRefTagManagement")
public class ResRefTagManagementAction {

	@Autowired
	MPResListService resService;

	@Autowired
	MPResTagService resTagService;

	@Autowired
	MPResRefTagService resRefTagService;

	/**
	 * 给用户添加标签
	 * 
	 * @param request
	 * @param resId
	 * @param tagIds
	 * @param tagType
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletRequest request, @RequestParam("resId") Long resId,
			@RequestParam("tagIds[]") List<Long> tagIds, @RequestParam("tagType") Integer tagType) {
		ResList res = this.resService.selectById(resId);
		if (res == null) {
			return JsonResult.failure("资源不存在！");
		}
		EntityWrapper<ResTag> resTagWrapper = new EntityWrapper<>();
		resTagWrapper.in("entityId", tagIds);
		List<ResTag> tags = this.resTagService.selectList(resTagWrapper);
		if (tags == null || tags.size() == 0) {
			return JsonResult.SUCCESS;
		}
		this.resRefTagService.insertResRefTag(tags, res, tagType);
		return JsonResult.SUCCESS;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request,
			@RequestParam("resRefTagIds[]") List<Long> resRefTagIds) {
		boolean deleteBatchIds = this.resRefTagService.deleteBatchIds(resRefTagIds);
		return JsonResult.success(deleteBatchIds);
	}
}
