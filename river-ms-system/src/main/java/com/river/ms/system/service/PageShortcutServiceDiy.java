package com.river.ms.system.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.PageShortcut;
import com.river.core.entity.UserEntity;
import com.river.ms.system.mapper.IPageShortcutDao;


/**
 * <p>
 * 快捷方式定义 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Service
public class PageShortcutServiceDiy {
	@Autowired
	IPageShortcutDao pageShortCutDao;
	
	/**
	 * 根据用户id查找菜单列表,如果useId=0,查询全部
	 * @param useId ：用户主键
	 * @return ：用户菜单列表
	 */
	public List<PageShortcut> getPageShortCutByUser(Long useId){
		return pageShortCutDao.getPageShortCutByUser(useId);
	}
	
	/**
	 * 根据用户id删除所有相关信息
	 * @param useId
	 */
	public void deletePageShortCutByUser(Long useId) {
		this.pageShortCutDao.deletePageShortCutByUser(useId);
	};
	
	/**
	 * 批量插入数据
	 * @param pageShortCutList 
	 */
	public void batchInsertion(List<PageShortcut> pageShortCutList) {
		this.pageShortCutDao.batchInsertion(pageShortCutList);
	}
	
	public void transactionSave(UserEntity user,List<PageShortcut> pageShortCutList) {
		//删除原有关系
		this.deletePageShortCutByUser(user.getEntityId());
		//保存新关系
		this.batchInsertion(pageShortCutList);
	}

}
