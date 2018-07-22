package com.river.ms.res.result;

import com.river.core.result.JsonResult;

public class ErrorResult {

	//
	public static JsonResult INSERT_ERROR = new JsonResult(1001,"评价失败！");
	
	public static JsonResult CODE_EXIST = new JsonResult(8001,"编码存在！");
	
	public static JsonResult INSERT_PROJECT_FOCUSON = new JsonResult(2001,"关注项目插入失败！");
	public static JsonResult PARAMETER_ERROR = new JsonResult(2002,"参数异常！");
	public static JsonResult PROJECT_FOCUSON_NOT_EXIST = new JsonResult(2003,"关注项目不存在！");
	public static JsonResult QUERY_PROJECT_FOCUSON_ERROR = new JsonResult(2004,"关注项目查找失败！");
	public static JsonResult DELETE_PROJECT_FOCUSON_ERROR = new JsonResult(2005,"取消关注项目失败！");
	
	public static JsonResult RES_CATE_ERROR = new JsonResult(8002,"资源或分类信息异常！");
}
