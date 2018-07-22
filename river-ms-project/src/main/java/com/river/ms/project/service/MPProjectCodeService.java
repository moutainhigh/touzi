package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectCode;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目编码规则 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-28
 */
public interface MPProjectCodeService extends IService<ProjectCode> {
	String getNextProjectNum();
	Boolean saveProjectNum();
}
