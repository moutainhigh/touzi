package com.river.ms.cms.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerBase;
import com.river.core.helper.IpAddress;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.cms.entity.ArticleEntity;
import com.river.ms.cms.entity.CmsPVStatistics;
import com.river.ms.cms.entity.ContentEntity;
import com.river.ms.cms.entity.DFSEntity;
import com.river.ms.cms.result.ErrorResult;
import com.river.ms.cms.service.ArticleService;
import com.river.ms.cms.service.ContentService;
import com.river.ms.cms.service.PVStatisticsService;
import com.river.ms.cms.service.DFSService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController extends ControllerBase<ArticleService, ArticleEntity> {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	ArticleService currentService;

	@Autowired
	ContentService contentService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	PVStatisticsService pvStatisticsService;

	@Autowired
	DFSService dfsService;

	@Override
	public ArticleService getService() {
		return currentService;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ArticleEntity articleEntity, BindingResult res,
			@RequestParam(name = "docStr", required = false) String docStr) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(articleEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, articleEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		articleEntity.setDisplayTime(new Timestamp(new Date().getTime()));
		articleEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(articleEntity);
		articleEntity.setEntityId(result);
		/*ContentEntity contentEntity = new ContentEntity();
		contentEntity.setArticleId(result);
		contentEntity.setContent(articleEntity.getContent());
		long insert = contentService.insert(contentEntity);*/
		if (docStr != null && !docStr.equals("")) {
			List<DFSEntity> parseArray = JSONArray.parseArray(docStr, DFSEntity.class);
			if (parseArray != null && parseArray.size() > 0) {
				for (DFSEntity dfsEntity : parseArray) {
					dfsEntity.setArticleId(result);
				}
				this.dfsService.insertBitch(parseArray);
			}
		}
		return Success(articleEntity);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ArticleEntity articleEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(articleEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, articleEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ArticleEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.ARTICLE_NOT_EXIST;
		}
		ObjectHelper.Copy(articleEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.ARTICLE_UPDATE_FAIL;
		}
	}

	/**
	 * 单个移除
	 */
	@Override
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {
		ArticleEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.ARTICLE_NOT_EXIST;
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("articleId", entityId);
			List<ContentEntity> content = contentService.queryBy(map);
			if (content != null && content.size() > 0) {
				contentService.removeBy(map);
			}
		}
		return super.remove(entityId);
	}

	/**
	 * 查询，条件查询
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	public JsonResult queryAll(ArticleEntity entity, HttpServletRequest request) {

		List<ArticleEntity> queryArticle = currentService.queryArticle(entity, null);

		return Success(queryArticle);
	}

	/**
	 * 根据分类查询文章信息
	 * 
	 * @param category
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryByCategory", method = RequestMethod.POST)
	public JsonResult queryByCategory(String category, HttpServletRequest request) {
		String categoryCode = request.getParameter("categoryCode");
		if (categoryCode.isEmpty())
			return Failure(-1, "categoryCode is null.");
		List<ArticleEntity> queryArticle = currentService.queryByCategory(categoryCode);

		return Success(queryArticle);
	}

	/**
	 * 查询，条件查询
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult search(ArticleEntity entity, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		PageHelper.startPage(page, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<ArticleEntity> queryArticle = currentService.queryArticle(entity, null);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<ArticleEntity> pageInfo = new PageInfo<ArticleEntity>(queryArticle, this.getNavigatePages());

		return Success(pageInfo);
	}

	@Override
	@RequestMapping(value = "/getbyid/{entityId}", method = RequestMethod.GET)
	public JsonResult getById(@PathVariable(value = "entityId") long entityId) {

		ArticleEntity entity = currentService.getById(entityId);

		if (entity == null) {
			return ErrorResult.ARTICLE_NOT_EXIST;
		}

		CmsPVStatistics record = new CmsPVStatistics();
		record.setIpAddr(IpAddress.getIpAddress(request));
		record.setArticleId(entityId);
		pvStatisticsService.insert(record);

		long pvCount = pvStatisticsService.countByArticleId(entityId);
		entity.setPvCount(pvCount);
		long ipCount = pvStatisticsService.countByArticleIdIP(entityId);
		entity.setIpCount(ipCount);

		return Success(entity);
	}

	@RequestMapping(value = "/getArticleByIdToPVCount/{entityId}", method = RequestMethod.GET)
	public JsonResult getArticleByIdToPVCount(@PathVariable(value = "entityId") long entityId) throws Exception {
		ArticleEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.ARTICLE_NOT_EXIST;
		}
		return Success(entity);
	}

	/**
	 * 根据阶段code查询文章列表
	 */
	@RequestMapping(value = "/queryByStages", method = RequestMethod.POST)
	public JsonResult queryByStages(@RequestParam("stageCodes[]") List<String> stageCodes) {
		List<ArticleEntity> queryByStages = new ArrayList<>();
		if (stageCodes != null && stageCodes.size() > 0) {
			queryByStages = currentService.queryByStages(stageCodes);
		}
		return Success(queryByStages);
	}

	/**
	 * 获取文章详情
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getByIdRiver", method = RequestMethod.POST)
	public JsonResult getByIdRiver(@RequestParam(value = "entityId") Long entityId) {

		ArticleEntity entity = currentService.getByIdRiver(entityId);

		if (entity == null) {
			return ErrorResult.ARTICLE_NOT_EXIST;
		}

		CmsPVStatistics record = new CmsPVStatistics();
		record.setIpAddr(IpAddress.getIpAddress(request));
		record.setArticleId(entityId);
		pvStatisticsService.insert(record);

		long pvCount = pvStatisticsService.countByArticleId(entityId);
		entity.setPvCount(pvCount);
		long ipCount = pvStatisticsService.countByArticleIdIP(entityId);
		entity.setIpCount(ipCount);

		return Success(entity);
	}

	@RequestMapping(value = "/insertCategoryArticle", method = RequestMethod.POST)
	public JsonResult insertCategoryArticle(@RequestParam(value = "categoryIds[]") List<Long> categoryIds,
			@RequestParam(value = "articleIds[]") List<Long> articleIds) {
		if (categoryIds != null && categoryIds.size() > 0 && articleIds != null && articleIds.size() > 0) {
			this.currentService.insertCategoryArticle(categoryIds, articleIds);
			return JsonResult.SUCCESS;
		}
		return JsonResult.FAILURE;
	}
}
