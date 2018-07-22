package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectEmail;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyb
 * @since 2018-01-26
 */
public interface MPProjectEmailService extends IService<ProjectEmail> {

	List<ProjectEmail> selectProjectEmail();
	
	Boolean updateById(Long entityId);
	
	Boolean updateAll();
}
