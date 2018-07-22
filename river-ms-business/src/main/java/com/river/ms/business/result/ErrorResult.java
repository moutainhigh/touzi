package com.river.ms.business.result;

import com.river.core.result.JsonResult;

public class ErrorResult {

	//
	public static JsonResult UPDATE_ERROR = new JsonResult(6001,"更新数据失败！");
	public static JsonResult INSERT_PROJECT_ERROE = new JsonResult(6002,"数据添加失败！");
	public static JsonResult QUERY_ERROE = new JsonResult(6003,"数据查询失败！");
	
	public static JsonResult DATA_EXIST = new JsonResult(6003,"数据已经存在！");
	
	public static JsonResult IMPORT_FINANCIAL_BALANCE_DATA_ERROE = new JsonResult(7001,"导入财务数据负债表数据失败！");
	public static JsonResult IMPORT_FINANCIAL_CASH_DATA_ERROE = new JsonResult(7002,"导入财务数据现金表数据失败！");
	public static JsonResult IMPORT_FINANCIAL_INCOME_DATA_ERROE = new JsonResult(7003,"导入财务数据利润表数据失败！");
	
	public static JsonResult DOWNLOAD_FINANCIAL_BALANCE_TEM_ERROR = new JsonResult(7004,"下载财务数据负债表模版失败！");
	public static JsonResult DOWNLOAD_FINANCIAL_CASH_TEM_ERROR = new JsonResult(7005,"下载财务数据现金表模版失败！");
	public static JsonResult DOWNLOAD_FINANCIAL_INCOME_TEM_ERROR = new JsonResult(7006,"下载财务数据利润表模版失败！");
	
	public static JsonResult IMPORT_INDEX_IDICATOR_DATA_ERROE = new JsonResult(7001,"导入数据失败！");
	
	public static JsonResult ACTIVITI_ERROR = new JsonResult(7001,"导入数据失败！");
	
	public static JsonResult PROJECT_STAGE_ERROR = new JsonResult(10001,"项目阶段异常！");
	
	public static JsonResult CODE_EXIST = new JsonResult(8001,"编码存在！");
}
