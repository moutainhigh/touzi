package com.river.activiti.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.river.activiti.entity.business.FlowEntity;

/**
 * 立项实体管理接口
 *
 * @author HenryYan
 */

@Repository
public interface FlowEntityDao extends CrudRepository<FlowEntity, Long> {
}
