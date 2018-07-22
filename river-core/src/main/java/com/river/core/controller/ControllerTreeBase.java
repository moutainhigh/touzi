package com.river.core.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.river.core.entity.TreeEntityBase;
import com.river.core.result.JsonResult;
import com.river.core.service.IServiceTreeBase;

/**
 * 
 * @author Yinovo
 *
 */
public abstract class ControllerTreeBase<S extends IServiceTreeBase<T>, T extends TreeEntityBase> extends ControllerBase<S,T>{

	public abstract S getService();
	/**
	 * 获取所有的子元素
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getchildren/{entityId}", method = RequestMethod.GET)
	public JsonResult getChildren(@PathVariable(value="entityId")long entityId){
		List<T> list = getService().getChildren(entityId);
		return Success(list);
	}
	
	/**
	 * 根据ID获取对象的详细信息
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value="/getTreeEntityById/{entityId}", method = RequestMethod.GET)
	public JsonResult getTreeEntityById(@PathVariable(value="entityId")long entityId){
		T entity = getService().getTreeEntityById(entityId);
		return Success(entity);
	}
	
	/**
	 * 获取所有的下级元素
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/getchild/{entityId}", method = RequestMethod.GET)
	public JsonResult getChild(@PathVariable(value="entityId")long entityId){
		List<T> list=getService().getChild(entityId);
		return Success(list);
	}
	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @return
	 */
	@RequestMapping(value="/getparent/{entityId}", method = RequestMethod.GET)
	public JsonResult getParent(@PathVariable(value="entityId")long entityId){
		T t= getService().getParent(entityId);
		return Success(t);
	}
	
	/**
	 * 将树型的对象中，子集的对象id整理为一个集合
	 * @param entityList
	 * @param ids
	 */
	public void getIds(List<? extends TreeEntityBase> entityList,List<Long> ids) {
		for (TreeEntityBase et : entityList) {
			if (et.getChildren() != null && et.getChildren().size()>0) {
				this.getIds(et.getChildren(), ids);
			}
			ids.add(et.getEntityId());
		}
	}
	
	public void getIds(TreeEntityBase entity , List<Long> ids) {
		if (entity.getChildren() != null && entity.getChildren().size()>0) {
			for(TreeEntityBase en : entity.getChildren()) {
				this.getIds(en, ids);
			}
		}
		ids.add(entity.getEntityId());
	}
}
