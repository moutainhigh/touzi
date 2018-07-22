package com.river.ms.res.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.res.entity.ResTagHistory;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPResTagHistoryService;

/**
 * <p>
 * 资源评价标签 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resTagHistory")
public class ResTagHistoryAction {

	@Autowired
	MPResTagHistoryService service;

	/**
	 * 插入资源标签
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult functionCreate(ResTagHistory entity) {
		boolean insertResTagHistory = this.service.insertResTagHistory(entity);
		if (insertResTagHistory) {
			return new JsonResult(0, "OK", entity);
		} else {
			return ErrorResult.INSERT_ERROR;
		}
	}
}
