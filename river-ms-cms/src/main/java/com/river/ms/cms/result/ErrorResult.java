package com.river.ms.cms.result;

import com.river.core.result.JsonResult;

public class ErrorResult {
	
	public static JsonResult ARGUMENT_NULL = new JsonResult(021,"参数为传递正�?");
	public static JsonResult AUTHORIZE_FAILURE = new JsonResult(030,"未经授权访问");
	
	/**
	 * Category 模块异常信息
	 */
	public static JsonResult CATEGORY_INSERT_FAIL = new JsonResult(3101,"资源种类添加失败!");
	public static JsonResult CATEGORY_UPDATE_FAIL = new JsonResult(3102,"资源种类更新失败!");
	public static JsonResult CATEGORY_NOT_EXIST = new JsonResult(3103,"资源种类不存在!");
	public static JsonResult CATEGORY_EXIST_ARTICLE = new JsonResult(3104,"该资源种类下存在资源文件，请先删除资源文件!");
	public static JsonResult CATEGORY_EXIST_CHILD = new JsonResult(3105,"该资源种类下存在子资源种类，请先删除相关资源种类!");

	/**
	 * Article 模块异常信息
	 */
	public static JsonResult ARTICLE_INSERT_FAIL = new JsonResult(3201,"资源添加失败!");
	public static JsonResult ARTICLE_UPDATE_FAIL = new JsonResult(3202,"资源更新失败!");
	public static JsonResult ARTICLE_NOT_EXIST = new JsonResult(3203,"资源不存在!");
	
	/**
	 * Content 模块异常信息表
	 */
	public static JsonResult CONTENT_INSERT_FAIL = new JsonResult(3301,"内容添加失败!");
	public static JsonResult CONTENT_UPDATE_FAIL = new JsonResult(3302,"内容更新失败!");
	public static JsonResult CONTENT_NOT_EXIST = new JsonResult(3303,"内容不存在!");
	
	public static JsonResult PV_ARTICLE_ERROR = new JsonResult(3401,"文章不存在!");
}
