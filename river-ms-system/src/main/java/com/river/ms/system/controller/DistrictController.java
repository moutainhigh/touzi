package com.river.ms.system.controller;


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.controller.ControllerTreeBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.system.entity.DistrictEntity;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.DistrictService;

/**
 * 
 * @author zh
 *
 */
@RestController
@RequestMapping(value = "/district")
public class DistrictController extends ControllerTreeBase<DistrictService, DistrictEntity> {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 行政划分业务层
	 */
	@Autowired
	DistrictService currentService;

	/**
	 * 
	 * @return
	 */
	public DistrictService getService() {
		return currentService;
	}

	/**
	 * 添加行政划分
	 * @param districtEntity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult districtCreate(@Valid DistrictEntity districtEntity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isNullOrEmpty(districtEntity.getEntityCode())) {
			logger.debug("编码不能为空");
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(-1, districtEntity.getEntityCode())) {
			logger.debug("编码已存在");
			return JsonResult.CODE_ISEXIST;
		}
		districtEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(districtEntity);
		if (result == 0) {
			logger.debug("district插入失败！");
			return ErrorResult.DISTRICT_INSERT_FAIL;
		} else {
			logger.debug("district插入成功");
			return Success(districtEntity);
		}
	}
	/**
	 * 更新行政划分
	 * @param districtEntity
	 * @param res
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult districtUpdate(@Valid DistrictEntity districtEntity, BindingResult res,@PathVariable(value="entityId")long entityId) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		//修改的时候，传递进来的数据允许为NULL，代表不做任何修改，但不能为空
		DistrictEntity entity=currentService.getById(entityId);
		if(entity==null){
			return ErrorResult.DISTRICT_NOT_EXIST;
		}
		if (entity.getIsDelete() == 1) {
			logger.debug("该项已被删除");
			return ErrorResult.DISTRICT_DELETE;
		}
		if (entity.getIsDisable() == 1) {
			logger.debug("该项已被禁用");
			return ErrorResult.DISTRICT_DISABLE;
		}
		if (StringValidator.isEmpty(districtEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, districtEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		ObjectHelper.Copy(districtEntity, entity);
		boolean result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			logger.debug("更新失败:"+entityId);
			return ErrorResult.DISTRICT_UPDATE_FAIL;
		}
	}
}