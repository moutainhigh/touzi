package com.river.activiti.entity.business;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import com.river.activiti.entity.IdEntity;

@Entity
@Table(name = "business_flow")
public class FlowEntity extends IdEntity implements Serializable  {
	 private static final long serialVersionUID = 1L;
	    private String processInstanceId;
	    private String userId;//用户itcode
	    private String projectId;//项目ID
	    private String projectSize;//项目规模
	    private String projectType;//项目类型（包含投资类型）
	    private String flowType;//流程类型
	    /**
		 * @return the projectSize
		 */
		public String getProjectSize() {
			return projectSize;
		}

		/**
		 * @param projectSize the projectSize to set
		 */
		public void setProjectSize(String projectSize) {
			this.projectSize = projectSize;
		}

		/**
		 * @return the projectType
		 */
		public String getProjectType() {
			return projectType;
		}

		/**
		 * @param projectType the projectType to set
		 */
		public void setProjectType(String projectType) {
			this.projectType = projectType;
		}

		/**
		 * @return the flowNode
		 */
		public String getFlowNode() {
			return flowNode;
		}

		/**
		 * @param flowNode the flowNode to set
		 */
		public void setFlowNode(String flowNode) {
			this.flowNode = flowNode;
		}

		private String flowNode;//当前流程节点
	    private Date applyTime;
	    private String reason;//描述说明
	    // 流程任务
	    private Task task;

	    private Map<String, Object> variables;

	    // 运行中的流程实例
	    private ProcessInstance processInstance;

	    // 历史的流程实例
	    private HistoricProcessInstance historicProcessInstance;

	    // 流程定义
	    private ProcessDefinition processDefinition;

	    @Column(name="PROCESS_INSTANCE_ID_")
	    public String getProcessInstanceId() {
	        return processInstanceId;
	    }

	    public void setProcessInstanceId(String processInstanceId) {
	        this.processInstanceId = processInstanceId;
	    }

	    @Column
	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }
	   /**
		 * @return the projectId
		 */
	    @Column
		public String getProjectId() {
			return projectId;
		}

		/**
		 * @param projectId the projectId to set
		 */
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

		@Column
	    @Temporal(TemporalType.TIMESTAMP)
	    public Date getApplyTime() {
	        return applyTime;
	    }

	    public void setApplyTime(Date applyTime) {
	        this.applyTime = applyTime;
	    }

	    @Column
	    public String getReason() {
	        return reason;
	    }

	    public void setReason(String reason) {
	        this.reason = reason;
	    }

	   
	    @Transient
	    public Task getTask() {
	        return task;
	    }

	    public void setTask(Task task) {
	        this.task = task;
	    }

	    @Transient
	    public Map<String, Object> getVariables() {
	        return variables;
	    }

	    public void setVariables(Map<String, Object> variables) {
	        this.variables = variables;
	    }

	    @Transient
	    public ProcessInstance getProcessInstance() {
	        return processInstance;
	    }

	    public void setProcessInstance(ProcessInstance processInstance) {
	        this.processInstance = processInstance;
	    }

	    @Transient
	    public HistoricProcessInstance getHistoricProcessInstance() {
	        return historicProcessInstance;
	    }

	    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
	        this.historicProcessInstance = historicProcessInstance;
	    }

	    @Transient
	    public ProcessDefinition getProcessDefinition() {
	        return processDefinition;
	    }

	    public void setProcessDefinition(ProcessDefinition processDefinition) {
	        this.processDefinition = processDefinition;
	    }

		public String getFlowType() {
			return flowType;
		}

		public void setFlowType(String flowType) {
			this.flowType = flowType;
		}
}
