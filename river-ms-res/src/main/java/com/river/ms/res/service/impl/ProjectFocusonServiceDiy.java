package com.river.ms.res.service.impl;

import com.river.ms.res.entity.ProjectFocuson;
import com.river.ms.res.mapper.ProjectFocusonDao;
import com.river.ms.res.service.MPProjectFocusonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-27
 */
@Service
@Transactional
public class ProjectFocusonServiceDiy extends ServiceImpl<ProjectFocusonDao, ProjectFocuson> implements MPProjectFocusonService {

}
