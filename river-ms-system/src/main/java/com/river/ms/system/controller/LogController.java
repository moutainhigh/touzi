package com.river.ms.system.controller;



import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.controller.ControllerBase;
import com.river.core.helper.DateHelper;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.entity.LogEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.LogService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/log")
public class LogController extends ControllerBase<LogService, LogEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@Autowired
	LogService currentService;
	/**
	 * 
	 * @return
	 */
	public LogService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult logCreate(@Valid LogEntity logEntity,BindingResult res) {

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(logEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, logEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		logEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(logEntity);
		logger.debug(logEntity);
		if (result == 0) {
			logger.debug("log插入失败");
			return ErrorResult.LOG_INSERT_FAIL;
		} else {
			logger.debug("log插入成功");
			return Success(logEntity);
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult logUpdate(@Valid LogEntity logEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		LogEntity entity = currentService.getById(logEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.LOG_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.LOG_DELETE;
		}

		if (StringValidator.isNullOrEmpty(logEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(logEntity.getEntityId(), logEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(logEntity, entity);
		boolean	result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("logEntity更新成功");
			return Success(entity);
		} else {
			logger.debug("logEntity更新失败");
			return ErrorResult.LOG_UPDATE_FAIL;
			
		} 
	}
	@RequestMapping(value="/queryByPage",method=RequestMethod.POST)
	public JsonResult queryByPage( @RequestParam(value="page",defaultValue="1")Integer page) {
		PageHelper.startPage(page, 2);
		List<LogEntity> logList = currentService.querryAll();
		PageInfo pageList = new PageInfo(logList, 5);
		if (pageList != null) {
			logger.debug("日志查询成功");
			return Success(pageList);
		} else {
			return ErrorResult.LOG_NOT_EXIST;
		}
	}
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public JsonResult logSearch(@RequestParam(value="keyWords",defaultValue="") String keyWords,
			                       @RequestParam(value="beginTime",defaultValue="") String beginTime, 
			                       @RequestParam(value="endTime",defaultValue="")String endTime,
			                       @RequestParam(value="page",defaultValue="1") int page,
			                       @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		Date begin = DateHelper.stringToDate(beginTime,"yyyy-MM-dd");
		Date end = DateHelper.stringToDate(endTime,"yyyy-MM-dd");
		
		
		PageHelper.startPage(page, pageSize);
		List<LogEntity> logList = currentService.queryByLimit(keyWords, begin, end);
		PageInfo pageList = new PageInfo(logList, this.getNavigatePages());
		if (pageList != null) {
			logger.debug("日志查询成功");
			
			return Success(pageList);
		} else {
			logger.debug("不存在要查询的日志记录");
			return ErrorResult.LOG_NOT_EXIST;
		}
	}
}
