package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectKeypoint;
import com.river.ms.business.mapper.ProjectKeypointDao;
import com.river.ms.business.service.MPProjectKeypointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目关键指标 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectKeypointServiceDiy extends ServiceImpl<ProjectKeypointDao, ProjectKeypoint> implements MPProjectKeypointService {

}
