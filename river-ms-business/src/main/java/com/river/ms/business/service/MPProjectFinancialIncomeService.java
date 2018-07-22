package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectFinancialIncome;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目财务数据利润表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectFinancialIncomeService extends IService<ProjectFinancialIncome> {
	
	/**
	 * 根据模版数据进行批量保存
	 * @param file
	 * @throws Exception 
	 */
	void insertBatchByExcelTempl(MultipartFile file,Long projectId,UserEntity user);

}
