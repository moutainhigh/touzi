package com.river.ms.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.entity.PageShortcut;

/**
 * <p>
 * 快捷方式定义 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Mapper
public interface IPageShortcutDao{
	
	/**
	 * 根据用户id查找菜单列表,如果useId=0,查询全部
	 * @param useId ：用户主键
	 * @return ：用户菜单列表
	 */
	List<PageShortcut> getPageShortCutByUser(@Param("useId") Long useId);
	
	/**
	 * 根据用户id删除所有相关信息
	 * @param useId
	 */
	void deletePageShortCutByUser(@Param("useId") Long useId);
	
	/**
	 * 批量插入数据
	 * @param pageShortCutList 
	 */
	void batchInsertion(@Param("pageShortCutList") List<PageShortcut> pageShortCutList);

}
