package com.river.ms.bbs.result;

import com.river.core.result.JsonResult;

public class ErrorResult {
	public static JsonResult ARGUMENT_NULL = new JsonResult(021,"参数不正确！");
	public static JsonResult AUTHORIZE_FAILURE = new JsonResult(030,"未经授权访问！");
	
	/**
	 * 论坛列表异常信息
	 */
	public static JsonResult FORUM_INSERT_FAIL = new JsonResult(2101,"论坛添加失败!");
	public static JsonResult FORUM_UPDATE_FAIL = new JsonResult(2102,"论坛更新失败!");
	public static JsonResult FORUM_EXIST_TOPIC = new JsonResult(2103,"论坛包含主题不为空，请先删除论坛包含的主题!");
	public static JsonResult FORUM_EXIST_FORUM = new JsonResult(2104,"论坛包含子论坛不为空，请先删除论坛包含的子论坛!");
	public static JsonResult FORUM_NOT_EXIST = new JsonResult(2105,"论坛不存在!");

	/**
	 * 论坛主题异常信息
	 */
	public static JsonResult TOPIC_INSERT_FAIL = new JsonResult(2201,"主题添加失败!");
	public static JsonResult TOPIC_UPDATE_FAIL = new JsonResult(2202,"主题更新失败!");
	public static JsonResult TOPIC_EXIST_REPLY = new JsonResult(2203,"该主题下包含回复信息，请先删除回复信息!");
	public static JsonResult TOPIC_NOT_EXIST = new JsonResult(2204,"主题不存在!");
	
	/**
	 * 回复模块异常信息
	 */
	public static JsonResult REPLY_INSERT_FAIL = new JsonResult(2301,"回复添加失败!");
	public static JsonResult REPLY_UPDATE_FAIL = new JsonResult(2302,"回复更新失败!");
	public static JsonResult REPLY_EXIST_REPLY = new JsonResult(2303,"该回复下包含回复信息，请先删除回复信息!");
	public static JsonResult REPLY_NOT_EXIST = new JsonResult(2304,"回复不存在!");
	public static JsonResult REPLY_TOPICID_ISNOT = new JsonResult(2305,"主题不存在不存在!");
	
	/**
	 * 内容模块异常信息
	 */
	public static JsonResult CONTENT_INSERT_FAIL = new JsonResult(2401,"内容添加失败!");
	public static JsonResult CONTENT_UPDATE_FAIL = new JsonResult(2402,"内容更新失败!");
	public static JsonResult CONTENT_NOT_EXIST = new JsonResult(2403,"内容不存在!");
	public static JsonResult CONTENT_DEL_FAIL = new JsonResult(2404,"删除内容失败!");
	
	/**
	 * 点赞模块异常信息
	 */
	public static JsonResult THUMBUP_INSERT_FAIL = new JsonResult(2501,"点赞添加失败!");
	public static JsonResult THUMBUP_UPDATE_FAIL = new JsonResult(2502,"点赞更新失败!");
	public static JsonResult THUMBUP_NOT_EXIST = new JsonResult(2503,"点赞不存在!");
	public static JsonResult THUMBUP_DEL_FAIL = new JsonResult(2504,"删除点赞失败!");
}
