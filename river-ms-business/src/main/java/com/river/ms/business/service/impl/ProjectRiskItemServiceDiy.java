package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectRiskItem;
import com.river.ms.business.mapper.ProjectRiskItemDao;
import com.river.ms.business.service.MPProjectRiskItemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 风险项 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Service
public class ProjectRiskItemServiceDiy extends ServiceImpl<ProjectRiskItemDao, ProjectRiskItem> implements MPProjectRiskItemService {

}
