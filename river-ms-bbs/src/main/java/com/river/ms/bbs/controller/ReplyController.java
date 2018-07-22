package com.river.ms.bbs.controller;

import java.util.ArrayList;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.bbs.entity.ContentEntity;
import com.river.ms.bbs.entity.ReplyEntity;
import com.river.ms.bbs.result.ErrorResult;
import com.river.ms.bbs.service.ContentService;
import com.river.ms.bbs.service.ReplyService;
import com.river.ms.bbs.service.ThumbupService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/reply")
public class ReplyController extends ControllerTreeBase<ReplyService, ReplyEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	ReplyService currentService;

	@Autowired
	ContentService contentService;

	@Autowired
	ThumbupService thumbupService;

	@Override
	public ReplyService getService() {
		return currentService;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ReplyEntity replyEntity, BindingResult res,
			@RequestParam(value = "content") String content) {

		if (replyEntity.getTopicId() == 0) {
			return ErrorResult.REPLY_TOPICID_ISNOT;
		}

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(replyEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, replyEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		replyEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(replyEntity);
		replyEntity.setEntityId(result);

		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setContent(content);
		contentEntity.setTopicId(replyEntity.getTopicId());
		contentEntity.setReplyId(result);
		long contentId = contentService.insert(contentEntity);

		logger.debug(replyEntity);
		if (result == 0) {
			return ErrorResult.REPLY_INSERT_FAIL;
		} else {
			return Success(replyEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ReplyEntity replyEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(replyEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, replyEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ReplyEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.REPLY_NOT_EXIST;
		}
		ObjectHelper.Copy(replyEntity, entity);
		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.REPLY_UPDATE_FAIL;
		}
	}

	/**
	 * 删除回复
	 */
	@Override
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {
		/**
		 * 先查找是否存在回复，若存在需要先删除回复。
		 */
		/*
		 * if (currentService.getChild(entityId).size() > 0) { return
		 * ErrorResult.REPLY_EXIST_REPLY; } else { Map<String, Object> map = new
		 * HashMap<>(); map.put("replyId", entityId); boolean removeContent =
		 * contentService.removeBy(map); if (removeContent) { map.clear();
		 * map.put("replyId", entityId); thumbupService.removeBy(map); return
		 * super.remove(entityId); } else { return ErrorResult.CONTENT_DEL_FAIL; } }
		 */
		/**
		 * 直接删除，将回复也全部删除
		 */
		List<ReplyEntity> replyChildren = currentService.getChildren(entityId);
		List<Long> ids = new ArrayList<>();
		this.getIds(replyChildren, ids);
		ids.add(entityId);
		boolean batchRemove = currentService.batchRemove(ids);
		return Success(batchRemove);
	}

	/**
	 * 查询，条件查询
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult search(ReplyEntity entity, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		PageHelper.startPage(page, pageSize);

		if (entity.getTopicId() == 0) {
			return ErrorResult.REPLY_TOPICID_ISNOT;
		}

		// startPage后面紧跟的这个查询就是一个分页查询
		List<ReplyEntity> queryTopic = currentService.queryReply(entity);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<ReplyEntity> pageInfo = new PageInfo<ReplyEntity>(queryTopic, this.getNavigatePages());

		return Success(pageInfo);
	}

	@RequestMapping(value = "/insertReply", method = RequestMethod.POST)
	public JsonResult insertReply(ReplyEntity replyEntity, BindingResult res,
			@RequestParam(value = "content") String content) {

		replyEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(replyEntity);
		replyEntity.setEntityId(result);

		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setContent(content);
		contentEntity.setTopicId(replyEntity.getTopicId());
		contentEntity.setReplyId(result);
		long contentId = contentService.insert(contentEntity);

		logger.debug(replyEntity);
		if (result == 0) {
			return ErrorResult.REPLY_INSERT_FAIL;
		} else {
			return Success(replyEntity);
		}
	}

	/**
	 * 根据topicID获取回复内容
	 * @param request
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/getReply", method = RequestMethod.POST)
	public JsonResult getReply(HttpServletRequest request, @RequestParam(name = "topicId") Long topicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topicId", topicId);
		List<ReplyEntity> queryBy = this.currentService.queryBy(map);
		for (ReplyEntity replyEntity : queryBy) {
			ContentEntity byTopicIdAndReplyId = contentService.getByTopicIdAndReplyId(replyEntity.getTopicId(),
					replyEntity.getEntityId());
			replyEntity.setContentEntity(byTopicIdAndReplyId);
		}
		if(queryBy != null && queryBy.size()>0) {
			return JsonResult.success(queryBy.get(0));
		} else {
			return JsonResult.FAILURE;
		}
	}
}
