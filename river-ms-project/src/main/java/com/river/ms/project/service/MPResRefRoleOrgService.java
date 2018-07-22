package com.river.ms.project.service;

import com.river.core.entity.UserEntity;
import com.river.ms.project.entity.ResRefRoleOrg;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyb
 * @since 2018-04-19
 */
public interface MPResRefRoleOrgService extends IService<ResRefRoleOrg> {
	
	Map<String, Boolean> getResRefRoleOrg(UserEntity user);

}
