package com.river.ms.bbs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.bbs.entity.ThumbupEntity;
import com.river.ms.bbs.result.ErrorResult;
import com.river.ms.bbs.service.ThumbupService;
@RestController
@RequestMapping(value="/thumbup")
public class ThumbupController extends ControllerBase<ThumbupService, ThumbupEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	ThumbupService currentService;
	@Override
	public ThumbupService getService() {
		return currentService;
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ThumbupEntity thumbupEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(thumbupEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, thumbupEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		thumbupEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(thumbupEntity);
		thumbupEntity.setEntityId(result);
		logger.debug(thumbupEntity);
		if (result == 0) {
			return ErrorResult.THUMBUP_INSERT_FAIL;
		} else {
			return Success(thumbupEntity);
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ThumbupEntity thumbupEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {
		
		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(thumbupEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, thumbupEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ThumbupEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.THUMBUP_NOT_EXIST;
		}
		ObjectHelper.Copy(thumbupEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.THUMBUP_UPDATE_FAIL;
		} 	
	}

	/**
	 * 查询主题下共有人点赞
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/countThumbupByTopic/{topicId}", method = RequestMethod.GET)
	public JsonResult countThumbupByTopic(@PathVariable(value = "topicId") long topicId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("topicId",topicId);
		int count = currentService.countBy(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", count);
		return Success(result);
	}
	
	/**
	 * 查询回复共有多少人点赞
	 * @param replyId
	 * @return
	 */
	@RequestMapping(value = "/countThumbupByReply/{replyId}", method = RequestMethod.GET)
	public JsonResult countThumbupByReply(@PathVariable(value = "replyId") long replyId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("replyId",replyId);
		int count = currentService.countBy(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", count);
		return Success(result);
	}
	
	/**
	 * 查询当前用户是否已经对本主题点赞
	 * @param userId
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/isExitUserThumbupTopic/{userId}/{topicId}", method = RequestMethod.GET)
	public JsonResult isExitUserThumbupTopic(@PathVariable(value = "userId") long userId,
			@PathVariable(value = "topicId") long topicId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId",userId);
		map.put("topicId",topicId);
		int count = currentService.countBy(map);
		Map<String, Object> result = new HashMap<String, Object>();
		if (count == 0) {
			result.put("isExit", false);
		} else {
			result.put("isExit", true);
		}
		return Success(result);
	}
	
	/**
	 * 查询当前用户是否已经对回复题点赞
	 * @param userId
	 * @param replyId
	 * @return
	 */
	@RequestMapping(value = "/isExitUserThumbupReply/{userId}/{replyId}", method = RequestMethod.GET)
	public JsonResult isExitUserThumbupReply(@PathVariable(value = "userId") long userId,
			@PathVariable(value = "replyId") long replyId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId",userId);
		map.put("replyId",replyId);
		int count = currentService.countBy(map);
		Map<String, Object> result = new HashMap<String, Object>();
		if (count == 0) {
			result.put("isExit", false);
		} else {
			result.put("isExit", true);
		}
		return Success(result);
	}
}
