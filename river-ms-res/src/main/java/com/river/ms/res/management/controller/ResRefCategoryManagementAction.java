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
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefCategory;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPResCategoryService;
import com.river.ms.res.service.MPResListService;
import com.river.ms.res.service.MPResRefCategoryService;

/**
 * <p>
 * 资源对应分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resRefCategoryManagement")
public class ResRefCategoryManagementAction {

	@Autowired
	MPResRefCategoryService service;

	@Autowired
	MPResListService resService;

	@Autowired
	MPResCategoryService categoryService;

	/**
	 * 批量添加资源分类
	 * 
	 * @param request
	 * @param resIds
	 * @param categoryIds
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletRequest request, @RequestParam("resIds[]") List<Long> resIds,
			@RequestParam("categoryIds[]") List<Long> categoryIds) {
		// 查询资源
		EntityWrapper<ResList> resWrapper = new EntityWrapper<>();
		resWrapper.in("entityId", resIds);
		List<ResList> resList = this.resService.selectList(resWrapper);
		// 查询分类
		EntityWrapper<ResCategory> catWrapper = new EntityWrapper<>();
		catWrapper.in("entityId", categoryIds);
		List<ResCategory> cateList = this.categoryService.selectList(catWrapper);
		if (resList != null && resList.size() > 0 && cateList != null && cateList.size() > 0) {
			Boolean myInsertBatch = this.service.MyInsertBatch(resList, cateList);
			return JsonResult.success(myInsertBatch);
		} else {
			return ErrorResult.RES_CATE_ERROR;
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param resRefCategoryIds
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request,
			@RequestParam("resRefCategoryIds[]") List<Long> resRefCategoryIds) {
		boolean deleteBatchIds = this.service.deleteBatchIds(resRefCategoryIds);
		return JsonResult.success(deleteBatchIds);
	}
	
	/**
	 * 查询当前资源的人员
	 * @param request
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/selectRes", method = RequestMethod.POST)
	public JsonResult selectRes(HttpServletRequest request,
			@RequestParam("categoryId") Long categoryId) {
		List<ResRefCategory> selectRes = this.service.selectRes(categoryId);
		return JsonResult.success(selectRes);
	}
}
