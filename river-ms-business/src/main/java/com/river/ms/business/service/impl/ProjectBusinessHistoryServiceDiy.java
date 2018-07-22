package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectBusinessHistory;
import com.river.ms.business.mapper.ProjectBusinessHistoryDao;
import com.river.ms.business.service.MPProjectBusinessHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目业务操作日志 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectBusinessHistoryServiceDiy extends ServiceImpl<ProjectBusinessHistoryDao, ProjectBusinessHistory> implements MPProjectBusinessHistoryService {

}
