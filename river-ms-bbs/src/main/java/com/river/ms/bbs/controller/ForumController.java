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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.bbs.entity.ForumEntity;
import com.river.ms.bbs.result.ErrorResult;
import com.river.ms.bbs.service.ForumService;
import com.river.ms.bbs.service.TopicService;

/**
 * 
 * @author zhouheng
 *
 */
@RestController
@RequestMapping(value = "/forum")
public class ForumController extends ControllerTreeBase<ForumService, ForumEntity> {

	@Autowired
	ForumService currentService;

	@Autowired
	TopicService topicService;

	@Override
	public ForumService getService() {
		return currentService;
	}

	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@Valid ForumEntity forumEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(forumEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, forumEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		forumEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(forumEntity);
		forumEntity.setEntityId(result);
		logger.debug(forumEntity);
		if (result == 0) {
			return ErrorResult.FORUM_INSERT_FAIL;
		} else {
			return Success(forumEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid ForumEntity forumEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(forumEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, forumEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ForumEntity entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.FORUM_NOT_EXIST;
		}
		ObjectHelper.Copy(forumEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.FORUM_UPDATE_FAIL;
		}
	}

	/**
	 * 删除论坛列表
	 */
	@Override
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("forumId", entityId);
		int topicNum = topicService.countBy(map);

		if (topicNum > 0) {
			return ErrorResult.FORUM_EXIST_TOPIC;
		} else {
			if (currentService.getChild(entityId).size() > 0) {
				return ErrorResult.FORUM_EXIST_FORUM;
			} else {
				return super.remove(entityId);
			}
		}
	}

	/**
	 * business模块调用，获取forumID（添加或者查找）
	 * @param entityCode
	 * @param entityTitle
	 * @return
	 */
	@RequestMapping(value = "/getForumIdByCode", method = RequestMethod.POST)
	public Long getForumIdByCode(@RequestParam(name = "entityCode") String entityCode,
			@RequestParam(name = "entityTitle",required=false) String entityTitle) {
		ForumEntity byCode = currentService.getByCode(entityCode);
		if (byCode != null) {
			return byCode.getEntityId();
		} else {
			byCode = new ForumEntity();
			byCode.setEntityCode(entityCode);
			byCode.setEntityTitle(entityTitle);
			byCode.setEntityDesc(entityTitle);
			byCode.setEntityGUID(StringHelper.generateGUID());
			Long insert = currentService.insert(byCode);
			return insert;
		}
	}
}
