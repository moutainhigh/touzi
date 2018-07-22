package com.river.ms.res.management.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPResCategoryService;

/**
 * <p>
 * 资源分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resCategoryManagement")
public class ResCategoryManagementAction {
	@Autowired
	MPResCategoryService service;

	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletRequest request, @Valid ResCategory entity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if(entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.exist(id, entity.getEntityCode());
		if (exist == 0) {
			if(entity.getEntityId() != null) {
				entity.setUpdateTime(new Date());
			}else {
				entity.setCreateTime(new Date());
				entity.setEntityGUID(StringHelper.generateGUID());
			}
			boolean insertOrUpdate = this.service.insertOrUpdate(entity);
			if(insertOrUpdate) {
				return JsonResult.success(entity);
			}else {
				return JsonResult.failure("操作失败！");
			}
		} else {
			return ErrorResult.CODE_EXIST;
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResult delete(HttpServletRequest request,@PathVariable(value = "id") Long id) {
		ResCategory entity = new ResCategory();
		entity.setEntityId(id);
		entity.setIsDelete(1);
		entity.setDeleteTime(new Date());
		boolean updateById = this.service.updateById(entity);
		return JsonResult.success(updateById);
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public JsonResult select(HttpServletRequest request) {
		EntityWrapper<ResCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("isDelete", 0);
		List<ResCategory> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}
}
