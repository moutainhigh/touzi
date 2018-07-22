package com.river.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.entity.EntityBase;
import com.river.core.result.JsonResult;
import com.river.core.service.IServiceBase;

/**
 * 
 * @author Yinovo
 *
 */
public abstract class ControllerBase<S extends IServiceBase<T>, T extends EntityBase> {

	public abstract S getService();

	/**
	 * 分页参数，连续显示多少页的页码
	 */
	private int navigatePages = 5;

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getbyid/{entityId}", method = RequestMethod.GET)
	public JsonResult getById(@PathVariable(value = "entityId") long entityId) {
		T t = getService().getById(entityId);
		return Success(t);
	}

	/**
	 * 根据业务编码获取
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getbycode/{entityCode}", method = RequestMethod.GET)
	public JsonResult getByCode(@PathVariable(value = "entityCode") String entityCode) {
		T t = getService().getByCode(entityCode);
		return Success(t);
	}

	/**
	 * 
	 * @param entityGUID
	 * @return
	 */
	@RequestMapping(value = "/getbyguid/{entityGUID}", method = RequestMethod.GET)
	public JsonResult getByGUID(@PathVariable(value = "entityGUID") String entityGUID) {
		T t = getService().getByGUID(entityGUID);
		return Success(t);
	}

	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @return
	 */
	@RequestMapping(value = "/getonefieldbyid/{entityId}/{fieldName}", method = RequestMethod.GET)
	public JsonResult getOneFieldById(@PathVariable(value = "entityId") long entityId,
			@PathVariable(value = "fieldName") String fieldName) {
		String fieldValue = getService().getOneFieldById(entityId, fieldName);
		return Success(fieldValue);
	}

	/**
	 * 
	 * @param entityCode
	 * @param fieldName
	 * @return
	 */
	@RequestMapping(value = "/getonefieldbycode/{entityCode}/{fieldName}", method = RequestMethod.GET)
	public JsonResult getOneFieldByCode(@PathVariable(value = "entityCode") String entityCode,
			@PathVariable(value = "fieldName") String fieldName) {
		String fieldValue = getService().getOneFieldByCode(entityCode, fieldName);
		return Success(fieldValue);
	}

	/**
	 * 查询记录总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public JsonResult count() {
		int count = getService().count();
		return Success(count);
	}

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public JsonResult queryAll() {
		List<T> list = getService().queryAll();
		return Success(list);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pageAll", method = RequestMethod.POST)
	public JsonResult pageAll( @RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
	
		PageHelper.startPage(page, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<T> list = getService().queryAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo pageInfo = new PageInfo(list, this.getNavigatePages());
		return Success(pageInfo);
	}

	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/remove/{entityId}", method = RequestMethod.POST)
	public JsonResult remove(@PathVariable(value = "entityId") long entityId) {
		boolean res = getService().remove(entityId);
		return Success(res);
	}

	/**
	 * 
	 * @param listEntityId
	 * @return
	 */
	@RequestMapping(value = "/batchRemove", method = RequestMethod.POST)
	boolean batchRemove(@RequestParam(value="IDs") List<Long> listEntityId) {
		return getService().batchRemove(listEntityId);
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	boolean removeBy(Map<String, Object> map) {
		return getService().removeBy(map);
	}

	/**
	 * 禁用
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/disable/{entityId}", method = RequestMethod.POST)
	public JsonResult disable(@PathVariable(value = "entityId") long entityId) {
		boolean res = getService().disable(entityId, 1);
		return Success(res);
	}

	/**
	 * 启用
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/enable/{entityId}", method = RequestMethod.POST)
	public JsonResult enable(@PathVariable(value = "entityId") long entityId) {
		boolean res = getService().disable(entityId, 0);
		return Success(res);
	}

	/**
	 * 
	 * @param entityId
	 * @param entityCode
	 * @return
	 */
	@RequestMapping(value = "/existCode/{entityCode}/{entityId}", method = RequestMethod.GET)
	public JsonResult isExistCode(@PathVariable(value = "entityId") long entityId,
			@PathVariable(value = "entityCode") String entityCode) {
		boolean res = getService().isExistCode(entityId, entityCode);
		return Success(res);
	}

	/**
	 * 
	 * @param entityId
	 * @param entityTitle
	 * @return
	 */
	@RequestMapping(value = "/existTitle/{entityTitle}/{entityId}", method = RequestMethod.GET)
	public JsonResult isExistTitle(@PathVariable(value = "entityId") long entityId,
			@PathVariable(value = "entityTitle") String entityTitle) {
		boolean res = getService().isExistTitle(entityId, entityTitle);
		return Success(res);
	}

	/**
	 * 批量禁用
	 * 
	 * @param listEntityId
	 * @return
	 */
	boolean batchDisable(List<Long> listEntityId, int status/* =1 */) {
		return getService().batchDisable(listEntityId, status);
	}

	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean updateOneFieldById(long entityId, String fieldName, String fieldValue) {
		return getService().updateOneFieldById(entityId, fieldName, fieldValue);
	}

	/**
	 * 
	 * @param entityId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean updateOneFieldByCode(String entityCode, String fieldName, String fieldValue) {
		return getService().updateOneFieldByCode(entityCode, fieldName, fieldValue);
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public JsonResult Success(Object object) {
		return new JsonResult(0, "OK", object);
	}

	/**
	 * 失败
	 * 
	 * @return
	 */
	public JsonResult Failure(int status, String message) {
		// return new ResponseEntity<Long>(1);
		return new JsonResult(status, message);
	}

	/**
	 * 捕捉所有异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public JsonResult HandleException(Exception ex) {
		return Failure(-1, ex.getLocalizedMessage());
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

}
