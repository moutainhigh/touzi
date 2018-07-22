package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectOperationData;
import com.river.ms.business.mapper.ProjectOperationDataDao;
import com.river.ms.business.service.MPProjectOperationDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目经营数据偏差分析 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectOperationDataServiceDiy extends ServiceImpl<ProjectOperationDataDao, ProjectOperationData> implements MPProjectOperationDataService {

}
