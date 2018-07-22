package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectFlow;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程定义表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
public interface MPProjectFlowService extends IService<ProjectFlow> {

	Long isExist(Long entityId, String entityCode);
}
