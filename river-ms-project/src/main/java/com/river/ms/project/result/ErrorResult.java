package com.river.ms.project.result;

import com.river.core.result.JsonResult;

public class ErrorResult {

	//
	public static JsonResult PROJECT_STATE_NOT_EXIT = new JsonResult(5001,"项目状态不存在！");
	
	public static JsonResult USER_NODE_NOT_EXIT = new JsonResult(6001,"该用户节点不存在！");
	public static JsonResult QUERY_USER_NODE_ERROR = new JsonResult(6002,"查找用户节点失败！");
	
	public static JsonResult USER_STAGE_NOT_EXIT = new JsonResult(6001,"项目阶段不存在！");
	public static JsonResult QUERY_USER_STAGE_ERROR = new JsonResult(6002,"查找项目阶段失败！");
	public static JsonResult QUERY_PROJECT_DETIAL_ERROR = new JsonResult(6003,"查找关注项目列表失败！");
	
	public static JsonResult INDEX_INDICATOR_TEMPLET_ERROR = new JsonResult(7001,"科研预测数据模版导出失败！");
	public static JsonResult INDEX_INDICATOR_NO_EXIST = new JsonResult(7001,"科研预测数据参数不存在！");
	
	public static JsonResult GET_DATA_ERROR = new JsonResult(7001,"科研预测数据参数不存在！");
	
	public static JsonResult CODE_EXIST = new JsonResult(8001,"编码存在！");
	
	public static JsonResult CATEGORY_ERROR = new JsonResult(8002,"项目类型不存在！");
}
