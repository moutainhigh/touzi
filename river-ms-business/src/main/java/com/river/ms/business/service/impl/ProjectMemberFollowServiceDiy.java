package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectMemberFollow;
import com.river.ms.business.mapper.ProjectMemberFollowDao;
import com.river.ms.business.service.MPProjectMemberFollowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 关注的人/专家 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-26
 */
@Service
public class ProjectMemberFollowServiceDiy extends ServiceImpl<ProjectMemberFollowDao, ProjectMemberFollow> implements MPProjectMemberFollowService {

}
