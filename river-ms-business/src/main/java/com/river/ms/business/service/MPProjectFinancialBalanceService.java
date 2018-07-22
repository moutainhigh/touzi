package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectFinancialBalance;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目财务数据负债表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectFinancialBalanceService extends IService<ProjectFinancialBalance> {

	void insertBatchByExcelTempl(MultipartFile file,Long projectId,UserEntity user);

}
