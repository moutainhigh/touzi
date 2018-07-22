package com.river.ms.system.controller;



import java.util.Date;
import java.util.List;

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
import com.river.core.helper.DateHelper;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.entity.ThirdGroupEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.ThirdGroupService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/thirdgroup")
public class ThirdGroupController extends ControllerBase<ThirdGroupService, ThirdGroupEntity> {
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
	ThirdGroupService currentService;
	/**
	 * 
	 * @return
	 */
	public ThirdGroupService getService() {
		return currentService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult thirdGroupCreate(@Valid ThirdGroupEntity thirdGroupEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(thirdGroupEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, thirdGroupEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		thirdGroupEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(thirdGroupEntity);
		logger.debug(thirdGroupEntity);
		if (result == 0) {
			logger.debug("THIRDGROUP插入失败");
			return ErrorResult.THIRDGROUP_INSERT_FAIL;
		} else {
			logger.debug("THIRDGROUP插入成功");
			return Success(thirdGroupEntity);
		}
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult thirdGroupUpdate(@Valid ThirdGroupEntity thirdGroupEntity,BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		ThirdGroupEntity entity = currentService.getById(thirdGroupEntity.getEntityId());
		if (entity == null) {
			logger.debug("该项不存在！");
			return ErrorResult.THIRDGROUP_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.THIRDGROUP_DELETE;
		}
		if (StringValidator.isNullOrEmpty(thirdGroupEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(thirdGroupEntity.getEntityId(), thirdGroupEntity.getEntityCode())) {
			System.out.println("该编码已存在！");
			logger.debug("该编码已存在！");
			return JsonResult.CODE_ISEXIST;
		}
		
		ObjectHelper.Copy(thirdGroupEntity, entity);
		boolean	result = currentService.update(entity);
		logger.debug(entity);
		if (result) {
			logger.debug("THIRDGROUP更新成功");
			return Success(entity);
		} else {
			logger.debug("THIRDGROUP更新失败");
			return ErrorResult.THIRDGROUP_UPDATE_FAIL;
		}
	}
	/**
	 * 搜索三方机构
	 * @param keyWords
	 * @param entityCode
	 * @param contact
	 * @param mobile
	 * @param email
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public JsonResult thirdGroupSearch(@RequestParam(value="keyWords",defaultValue="") String keyWords,
			                       @RequestParam(value="entityCode",defaultValue="") String entityCode,
			 					   @RequestParam(value="contact",defaultValue="") String contact,
			 					   @RequestParam(value="mobile",defaultValue="") String mobile,
			 					   @RequestParam(value="email",defaultValue="") String email, 
			                       @RequestParam(value="beginTime",defaultValue="") String beginTime, 
			                       @RequestParam(value="endTime",defaultValue="") String endTime,
			                       @RequestParam(value="page",defaultValue="1") Integer page,
			                       @RequestParam(value="pageSize",defaultValue="5") Integer pageSize) {
		Date begin = DateHelper.stringToDate(beginTime,"yyyy-MM-dd");
		Date end = DateHelper.stringToDate(endTime,"yyyy-MM-dd");
		PageHelper.startPage(page, pageSize);
		List<ThirdGroupEntity> thirdGroupList = currentService.search(keyWords,entityCode,contact,mobile,email, begin, end);
		PageInfo pageList = new PageInfo(thirdGroupList, this.getNavigatePages());
		if (pageList != null) {
			logger.debug("三方机构查询成功");
			return Success(pageList);
		} else {
			logger.debug("不存在要查询的三方机构");
			return ErrorResult.THIRDGROUP_NOT_EXIST;
		}
	}
	/**
	 * 根据机构类型查询三方机构
	 * @param groupType 机构类型 0-证券公司1-会计事务所2-律师事务所3-企业法人4-上市市场
	 * @return
	 */
	@RequestMapping(value="/selectByGroupType/{groupType}",method=RequestMethod.GET)
	public JsonResult selectByGroupType(@PathVariable Integer groupType) {
		List<ThirdGroupEntity> thirdGroupList = currentService.selectByGroupType(groupType);
		if (thirdGroupList != null && thirdGroupList.size() > 0) {
			logger.debug("根据类型查询三方机构成功");
			return Success(thirdGroupList);
		} else{
			logger.debug("不存在要查询的三方机构");
			return ErrorResult.THIRDGROUP_NOT_EXIST;
		}
		
	}
}
