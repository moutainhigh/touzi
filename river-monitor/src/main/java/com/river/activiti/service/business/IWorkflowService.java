package com.river.activiti.service.business;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.river.activiti.entity.IdEntity;
import com.river.activiti.util.Page;

public interface IWorkflowService<T extends IdEntity> {
    ProcessInstance startWorkflow(T entity, Map<String, Object> variables);
    List<T> findTodoTasks(String userId, Page<T> page, int[] pageParams);
    List<T> findRunningProcessInstaces(Page<T> page, int[] pageParams);
}
