package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectTag;
import com.river.ms.project.mapper.ProjectTagDao;
import com.river.ms.project.service.MPProjectTagService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目标签（是否考虑不同角色不同标签） 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectTagServiceDiy extends ServiceImpl<ProjectTagDao, ProjectTag> implements MPProjectTagService {

}
