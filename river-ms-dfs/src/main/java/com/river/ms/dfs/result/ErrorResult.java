package com.river.ms.dfs.result;

import com.river.core.result.JsonResult;

/**
 * 文件异常信息列表 以5开头
 * 
 * @author my
 *
 */
public class ErrorResult {

	// 文件类型异常 51开头
	public static JsonResult DOCUMENT_TYPE_INSERT_FAIL = new JsonResult(5101, "文件类型插入失败！");
	public static JsonResult DOCUMENT_TYPE_NOT_EXIST = new JsonResult(5102, "文件类型不存在！");
	public static JsonResult DOCUMENT_TYPE_UPDATE_FAIL = new JsonResult(5103, "文件类型更新失败！");

	// 文件类型异常 51开头
	public static JsonResult DOCUMENT_INSERT_FAIL = new JsonResult(5201, "文件插入失败！");
	public static JsonResult DOCUMENT_NOT_EXIST = new JsonResult(5202, "文件不存在！");
	public static JsonResult DOCUMENT_UPDATE_FAIL = new JsonResult(5203, "文件更新失败！");
	public static JsonResult DOCUMENT_FILE_NOT_EXIST = new JsonResult(5204, "文件找不到！");
	public static JsonResult DOCUMENT_FILE_SAVE_FAIL = new JsonResult(5205, "文件保存失败！");
}
