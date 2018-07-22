package com.river.ms.system.result;

import com.river.core.result.JsonResult;

public class ErrorResult {

	//
	public static JsonResult USER_NOT_EXSIST = new JsonResult(1101,"用户不存在");
	public static JsonResult USER_ERROR_PASS = new JsonResult(1102,"用户密码错误");
	public static JsonResult USER_DISABLE = new JsonResult(1103,"该用户已被禁用");
	public static JsonResult USER_DELETE = new JsonResult(1104,"该用户已被删除");
	public static JsonResult USER_SPECIAL_WORD = new JsonResult(1105,"用户名含特殊字符");
	public static JsonResult USER_LENGTH = new JsonResult(1106,"用户名或密码过长");
	public static JsonResult USER_EMPTY = new JsonResult(1107,"用户名或密码为空");
	public static JsonResult USER_EXSIST = new JsonResult(1108,"用户已存在");

	public static JsonResult USER_INSERT_FAIL = new JsonResult(1109,"用户添加失败");
	public static JsonResult USER_UPDATE_FAIL = new JsonResult(1110,"用户更新失败");
	public static JsonResult USER_LOGIN_FAIL = new JsonResult(1111,"用户登录失败");
	public static JsonResult USER_MODIFY_FAIL = new JsonResult(1112, "用户密码修改失败");
	public static JsonResult USER_REGISTER_FAIL = new JsonResult(1113,"用户注册失败");
	public static JsonResult USER_NAME_ISEMPTY = new JsonResult(1114,"用户姓名不允许为空");

	//config问题
	public static JsonResult CONFIG_INSERT_FAIL = new JsonResult(1201,"config添加失败");
	public static JsonResult CONFIG_UPDATE_FAIL = new JsonResult(1202,"config更新失败");
	public static JsonResult CONFIG_NOT_EXIST = new JsonResult(1203,"该config不存在");
	public static JsonResult CONFIG_DELETE = new JsonResult(1204,"该config已被删除");
	public static JsonResult CONFIG_DISABLE = new JsonResult(1205,"该config已被禁用");
	//doctionary问题
	public static JsonResult DICTIONARY_INSERT_FAIL = new JsonResult(1301,"dictionary添加失败");
	public static JsonResult DICTIONARY_UPDATE_FAIL = new JsonResult(1302,"dictionary更新失败");
	public static JsonResult DICTIONARY_NOT_EXIST = new JsonResult(1303,"该dictionary不存在");
	public static JsonResult DICTIONARY_DELETE = new JsonResult(1304,"该dictionay已被删除");
	public static JsonResult DICTIONARY_DISABLE = new JsonResult(1305,"该dictionay已被禁用");
	//district问题
	public static JsonResult DISTRICT_INSERT_FAIL = new JsonResult(1401,"district添加失败");
	public static JsonResult DISTRICT_UPDATE_FAIL = new JsonResult(1402,"district更新失败");
	public static JsonResult DISTRICT_NOT_EXIST = new JsonResult(1403,"地区不存在");
	public static JsonResult DISTRICT_DELETE = new JsonResult(1404,"该district已被删除");
	public static JsonResult DISTRICT_DISABLE = new JsonResult(1405,"该district已被禁用");
	//element问题
	public static JsonResult ELEMENT_INSERT_FAIL = new JsonResult(1501,"element添加失败");
	public static JsonResult ELEMENT_UPDATE_FAIL = new JsonResult(1502,"element更新失败");
	public static JsonResult ELEMENT_NOT_EXIST = new JsonResult(1503,"该element不存在");
	public static JsonResult ELEMENT_DELETE = new JsonResult(1504,"该element已被删除");
	public static JsonResult ELEMENT_DISABLE = new JsonResult(1505,"该element已被禁用");
	//employee问题
	public static JsonResult EMPLOYEE_INSERT_FAIL = new JsonResult(1601,"employee添加失败");
	public static JsonResult EMPLOYEE_UPDATE_FAIL = new JsonResult(1602,"employee更新失败");
	public static JsonResult EMPLOYEE_NOT_EXIST = new JsonResult(1603,"该employee不存在");
	public static JsonResult EMPLOYEE_DELETE = new JsonResult(1604,"该employee已被删除");
	public static JsonResult EMPLOYEE_DISABLE = new JsonResult(1505,"该employee已被禁用");
	//fuction问题
	public static JsonResult FUNCTION_INSERT_FAIL = new JsonResult(1701,"fuction添加失败");
	public static JsonResult FUNCTION_UPDATE_FAIL = new JsonResult(1702,"fuction更新失败");
	public static JsonResult FUNCTION_NOT_EXIST = new JsonResult(1703,"该fuction不存在");
	public static JsonResult FUNCTION_DELETE = new JsonResult(1704,"该FUNCTION已被删除");
	public static JsonResult FUNCTION_DISABLE = new JsonResult(1705,"该FUNCTION已被禁用");
	//industry问题
	public static JsonResult INDUSTRY_INSERT_FAIL = new JsonResult(1801,"industry添加失败");
	public static JsonResult INDUSTRY_UPDATE_FAIL = new JsonResult(1802,"industry更新失败");
	public static JsonResult INDUSTRY_NOT_EXIST = new JsonResult(1803,"该industry不存在");
	public static JsonResult INDUSTRY_DELETE = new JsonResult(1804,"该INDUSTRY已被删除");
	public static JsonResult INDUSTRY_DISABLE = new JsonResult(1805,"该INDUSTRY已被禁用");
	//Log问题
	public static JsonResult LOG_INSERT_FAIL = new JsonResult(1901,"log添加失败");
	public static JsonResult LOG_UPDATE_FAIL = new JsonResult(1902,"log更新失败");
	public static JsonResult LOG_NOT_EXIST = new JsonResult(1903,"该log不存在");
	public static JsonResult LOG_DELETE = new JsonResult(1904,"该LOG已被删除");
	public static JsonResult LOG_DISABLE = new JsonResult(1905,"该LOG已被禁用");
	//Menu问题
	public static JsonResult MENU_INSERT_FAIL = new JsonResult(11001,"Menu添加失败");
	public static JsonResult MENU_UPDATE_FAIL = new JsonResult(11002,"Menu更新失败");
	public static JsonResult MENU_NOT_EXIST = new JsonResult(11003,"该menu不存在");
	public static JsonResult MENU_PATH_EMPTY = new JsonResult(11004,"menuPath不能为空");
	public static JsonResult MENU_REMOVE_FAIL = new JsonResult(11005,"menu删除失败");
	public static JsonResult MENU_DELETE = new JsonResult(11006,"该MENU已被删除");
	public static JsonResult MENU_DISABLE = new JsonResult(11007,"该MENU已被禁用");
	
	//organization问题
	public static JsonResult ORGANIZATION_INSERT_FAIL = new JsonResult(11101,"organization添加失败");
	public static JsonResult ORGANIZATION_UPDATE_FAIL = new JsonResult(11102,"organization更新失败");
	public static JsonResult ORGANIZATION_NOT_EXIST = new JsonResult(11103,"该organization不存在");
	public static JsonResult ORGANIZATION_DELETE = new JsonResult(11104,"该组织已被删除");
	public static JsonResult ORGANIZATION_DISABLE = new JsonResult(11105,"该组织已被禁用");
	//role问题
	public static JsonResult ROLE_INSERT_FAIL = new JsonResult(11201,"role添加失败");
	public static JsonResult ROLE_UPDATE_FAIL = new JsonResult(11202,"role更新失败");
	public static JsonResult ROLE_NOT_EXIST = new JsonResult(11203,"该role不存在");
	public static JsonResult ROLE_DELETE = new JsonResult(11204,"该角色已被删除");
	public static JsonResult ROLE_DISABLE = new JsonResult(11205,"该角色已被禁用");
	//station问题
	public static JsonResult STATION_INSERT_FAIL = new JsonResult(11301,"station添加失败");
	public static JsonResult STATION_UPDATE_FAIL = new JsonResult(11302,"station更新失败");
	public static JsonResult STATION_NOT_EXIST = new JsonResult(11303,"该station不存在");
	public static JsonResult STATION_DELETE = new JsonResult(11304,"该station已被删除");
	public static JsonResult STATION_DISABLE = new JsonResult(11305,"该station已被禁用");
	//thirdGroup问题
	public static JsonResult THIRDGROUP_INSERT_FAIL = new JsonResult(11401,"thirdGroup添加失败");
	public static JsonResult THIRDGROUP_UPDATE_FAIL = new JsonResult(11402,"thirdGroup更新失败");
	public static JsonResult THIRDGROUP_NOT_EXIST = new JsonResult(11403,"该thirdGroup不存在");
	public static JsonResult THIRDGROUP_DELETE = new JsonResult(11404,"该thirdGroup已被删除");
	public static JsonResult THIRDGROUP_DISABLE = new JsonResult(11405,"该thirdGroup已被禁用");
	//字典项问题
	public static JsonResult DICTIONARYITEM_INSERT_FAIL = new JsonResult(11501,"dictionaryItem添加失败");
	public static JsonResult DICTIONARYITEM_UPDATE_FAIL = new JsonResult(11502,"dictionaryItem更新失败");
	public static JsonResult DICTIONARYITEM_NOT_EXIST = new JsonResult(11503,"该dictionaryItem不存在");
	public static JsonResult DICTIONARYITEM_DELETE = new JsonResult(11504,"该字典项已被删除");
	public static JsonResult DICTIONARYITEM_DISABLE = new JsonResult(11505,"该字典项已被禁用");
	//角色配置
	public static JsonResult ROLE_FUNCTION_INSERT_FAIL = new JsonResult(11601,"权限功能添加失败");
	public static JsonResult ROLE_USER_INSERT_FAIL = new JsonResult(11602,"用户权限添加失败");
	public static JsonResult ROLE_USER_REMOVE_FAIL = new JsonResult(11603,"用户权限删除失败");
	public static JsonResult ROLE_USER_DISABLE_FAIL = new JsonResult(11604,"用户权限禁用失败");
	public static JsonResult ROLE_USER_NOT_EXIST = new JsonResult(11605,"用户权限不存在");
	public static JsonResult ROLE_USER_DELETE = new JsonResult(11606,"该用户权限已被删除");
	public static JsonResult ROLE_USER_DISABLE = new JsonResult(11607,"该用户权限已被禁用");
	//岗位人员配置
	public static JsonResult STATION_EMPLOYEE_INSERT_FAIL = new JsonResult(11701,"员工岗位添加失败");
	public static JsonResult STATION_EMPLOYEE_REMOVE_FAIL = new JsonResult(11702,"员工岗位删除失败");
	public static JsonResult STATION_EMPLOYEE_DISABLE_FAIL = new JsonResult(11703,"员工岗位禁用失败");
	public static JsonResult STATION_EMPLOYEE_NOT_EXIST = new JsonResult(11704,"员工岗位不存在");
	//参数验证
	public static JsonResult PARAMTER_ERROR = new JsonResult(11801,"参数异常");
	public static JsonResult QUERY_PAGE_SHORTCUT_ERROR = new JsonResult(11802,"查找快捷菜单列表失败");
	public static JsonResult QUERY_PAGE_SHORTCUT_NOT_EXIST = new JsonResult(11803,"快捷菜单列表不存在");
}
