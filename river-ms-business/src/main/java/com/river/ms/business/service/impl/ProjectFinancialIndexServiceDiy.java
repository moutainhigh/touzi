package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectFinancialIndex;
import com.river.ms.business.mapper.ProjectFinancialIndexDao;
import com.river.ms.business.service.MPProjectFinancialIndexService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目财务指标 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectFinancialIndexServiceDiy extends ServiceImpl<ProjectFinancialIndexDao, ProjectFinancialIndex> implements MPProjectFinancialIndexService {

}
