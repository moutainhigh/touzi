package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectCouncilMember;
import com.river.ms.business.mapper.ProjectCouncilMemberDao;
import com.river.ms.business.service.MPProjectCouncilMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目投决评议会、变更评议会等参会人员 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectCouncilMemberServiceDiy extends ServiceImpl<ProjectCouncilMemberDao, ProjectCouncilMember> implements MPProjectCouncilMemberService {

}
