package com.river.ms.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.MenuEntity;
import com.river.core.entity.PageShortcut;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.system.result.ErrorResult;
import com.river.ms.system.service.MenuService;
import com.river.ms.system.service.PageShortcutServiceDiy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 快捷方式定义 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/pageShortcut")
public class PageShortcutAction {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());
	
	
	@Autowired
	PageShortcutServiceDiy pageShortService;
	@Autowired
	MenuService menuService;
	
	/**
	 * 快捷导航批量添加
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insertPageShortcut(HttpServletRequest request,@RequestParam("menuEntityIds") List<Long> menuEntityIds) {
		UserEntity user = SessionUtils.getUser(request);
		List<PageShortcut> pageShortCutList = new ArrayList<>();
		if(menuEntityIds != null && !menuEntityIds.isEmpty()) {
			List<MenuEntity> MenuEntityList = this.menuService.getMenuByIds(menuEntityIds);
			for (MenuEntity s : MenuEntityList) {
				pageShortCutList.add(new PageShortcut(""
						, s.getEntityTitle(), s.getEntityCode(), s.getEntityId()
						, s.getMenuIcon(), user.getEntityCode(), user.getEntityId()
						,s.getMenuPath()));
			}
		}else {
			logger.debug("参数不能为空");
			return ErrorResult.PARAMTER_ERROR;
		}
		try {
			if(pageShortCutList!=null && !pageShortCutList.isEmpty()) {
				this.pageShortService.transactionSave(user, pageShortCutList);
			}
		}catch (Exception e) {
			logger.debug("menu插入失败");
			return ErrorResult.MENU_INSERT_FAIL;
		}
		logger.debug("menu插入成功");
		return JsonResult.success(pageShortCutList);
	}
	
	/**
	 * 快捷导航列表查找
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult selectPageShortcutList(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<PageShortcut> pageShortCutByUser = null;
		try {
			pageShortCutByUser = this.pageShortService.getPageShortCutByUser(user.getEntityId());
		}catch (Exception e) {
			logger.debug("查找快捷菜单列表失败");
			return ErrorResult.QUERY_PAGE_SHORTCUT_ERROR;
		}
		logger.debug("查找快捷菜单列表成功");
		return JsonResult.success(pageShortCutByUser);
	}

}
