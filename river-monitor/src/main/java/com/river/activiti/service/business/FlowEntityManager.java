package com.river.activiti.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.river.activiti.dao.FlowEntityDao;
import com.river.activiti.entity.business.FlowEntity;

import java.util.Date;

/**
 * 请假实体管理
 *
 * @author HenryYan
 */
@Component
@Transactional(readOnly = true)
public class FlowEntityManager {

	@Autowired
    FlowEntityDao flowEntityDao;

    public FlowEntity getFlowEntity(Long id) {
        //return leaveDao.findById(id).get();
        return 		flowEntityDao.findOne(id);

    }

    @Transactional(readOnly = false)
    public void saveFlowEntity(FlowEntity entity) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
        }
        flowEntityDao.save(entity);
    }
}
