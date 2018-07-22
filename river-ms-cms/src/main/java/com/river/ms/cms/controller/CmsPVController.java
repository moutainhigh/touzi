package com.river.ms.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.result.JsonResult;
import com.river.ms.cms.entity.ArticleEntity;
import com.river.ms.cms.entity.CmsPVStatistics;
import com.river.ms.cms.result.ErrorResult;
import com.river.ms.cms.service.PVStatisticsService;

@RestController
@RequestMapping(value = "/cmspv")
public class CmsPVController {

	@Autowired
	PVStatisticsService pvStatisticsService;

	/**
	 * 分页参数，连续显示多少页的页码
	 */
	private int navigatePages = 5;

	/**
	 * 查询，条件查询
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult search(CmsPVStatistics entity, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		if (entity.getArticleId() == 0) {
			return ErrorResult.PV_ARTICLE_ERROR;
		}
		
		PageHelper.startPage(page, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<CmsPVStatistics> queryArticle = pvStatisticsService.queryPV(entity);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<CmsPVStatistics> pageInfo = new PageInfo<CmsPVStatistics>(queryArticle, this.getNavigatePages());

		return Success(pageInfo);
	}

	public JsonResult Success(Object object) {
		return new JsonResult(0, "OK", object);
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

}
