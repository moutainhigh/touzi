package com.river.ms.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.bbs.entity.ContentEntity;
import com.river.ms.bbs.entity.TopicEntity;
import com.river.ms.bbs.result.ErrorResult;
import com.river.ms.bbs.service.ContentService;
import com.river.ms.bbs.service.ReplyService;
import com.river.ms.bbs.service.ThumbupService;
import com.river.ms.bbs.service.TopicService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/topic")
public class TopicController extends ControllerBase<TopicService, TopicEntity> {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	TopicService currentService;

	@Autowired
	ReplyService replyService;

	@Autowired
	ContentService contentService;

	@Autowired
	ThumbupService thumbupService;

	@Override
	public TopicService getService() {
		return currentService;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid TopicEntity topicEntity, BindingResult res,
			@RequestParam(value = "content") String content) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(topicEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, topicEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		topicEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(topicEntity);

		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setContent(content);
		contentEntity.setTopicId(result);
		long contentId = contentService.insert(contentEntity);

		topicEntity.setEntityId(result);
		logger.debug(topicEntity);
		if (result == 0) {
			return ErrorResult.TOPIC_INSERT_FAIL;
		} else {
			return Success(topicEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid TopicEntity topicEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId, @RequestParam(value = "content") String content) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(topicEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, topicEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		TopicEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.TOPIC_NOT_EXIST;
		}
		ObjectHelper.Copy(topicEntity, entity);

		result = currentService.update(entity);

		ContentEntity contente = contentService.getByTopicIdAndReplyId(entityId, 0);
		contente.setContent(content);
		boolean update = contentService.update(contente);

		if (result && update) {
			return Success(entity);
		} else {
			return ErrorResult.TOPIC_UPDATE_FAIL;
		}
	}

	/**
	 * 查询某论坛下共有多少主题
	 * 
	 * @param forumId
	 * @return
	 */
	@RequestMapping(value = "/countTopicByForum/{forumId}", method = RequestMethod.GET)
	public JsonResult countTopicByForum(@PathVariable(value = "forumId") long forumId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("forumId", forumId);
		int count = currentService.countBy(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", count);
		return Success(result);
	}

	/**
	 * 删除论坛主题
	 */
	@Override
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {

		/**
		 * 不分开删除回复，直接全删
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("topicId", entityId);
		map.put("isDelete", 0);

		replyService.removeBy(map);
		contentService.removeBy(map);
		thumbupService.removeBy(map);
		return super.remove(entityId);

		/**
		 * 分开删，先验证是否可以删除
		 */
		/*
		 * Map<String, Object> map = new HashMap<>(); map.put("topicId", entityId);
		 * 
		 * int replyNum = replyService.countBy(map);
		 * 
		 * if (replyNum > 0) { return ErrorResult.TOPIC_EXIST_REPLY; } else {
		 * map.clear(); map.put("topicId", entityId); boolean removeContent =
		 * contentService.removeBy(map); if (removeContent) { map.clear();
		 * map.put("topicId", entityId); thumbupService.removeBy(map); return
		 * super.remove(entityId); } else { return ErrorResult.CONTENT_DEL_FAIL; } }
		 */
	}

	/**
	 * 查询，条件查询
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult search(TopicEntity entity, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		PageHelper.startPage(page, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<TopicEntity> queryTopic = currentService.queryTopic(entity);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<TopicEntity> pageInfo = new PageInfo<TopicEntity>(queryTopic, this.getNavigatePages());

		return Success(pageInfo);
	}

	@Override
	@RequestMapping(value = "/getbyid/{entityId}", method = RequestMethod.GET)
	public JsonResult getById(@PathVariable(value = "entityId") long entityId) {
		TopicEntity entity = currentService.getById(entityId);
		return Success(entity);
	}

	/**
	 * 插入问答
	 * @param topicEntity
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/insertTopicEntity", method = RequestMethod.POST)
	public Long insertTopicEntity(TopicEntity topicEntity, @RequestParam(value = "content") String content) {
		topicEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(topicEntity);

		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setContent(content);
		contentEntity.setTopicId(result);
		long contentId = contentService.insert(contentEntity);

		topicEntity.setEntityId(result);
		return result;
	}
	/**
	 * 根据问答ID列表获取问答详情
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getByIds", method = RequestMethod.POST)
	public JsonResult getByIds(@RequestParam(name="ids[]") List<Long> ids) {
		if(ids != null && ids.size() >0) {
			List<TopicEntity> byIds = currentService.getByIds(ids);
			return Success(byIds);
		}
		return JsonResult.failure("参数异常！");
	}
}
