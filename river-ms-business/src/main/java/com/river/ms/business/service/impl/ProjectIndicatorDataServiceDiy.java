package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectIndicatorData;
import com.river.ms.business.mapper.ProjectIndicatorDataDao;
import com.river.ms.business.service.MPProjectIndicatorDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目财务评价 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectIndicatorDataServiceDiy extends ServiceImpl<ProjectIndicatorDataDao, ProjectIndicatorData> implements MPProjectIndicatorDataService {

}
