package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectMilestoneHistory;
import com.river.ms.business.mapper.ProjectMilestoneHistoryDao;
import com.river.ms.business.service.MPProjectMilestoneHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目进度更新记录 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectMilestoneHistoryServiceDiy extends ServiceImpl<ProjectMilestoneHistoryDao, ProjectMilestoneHistory> implements MPProjectMilestoneHistoryService {

}
