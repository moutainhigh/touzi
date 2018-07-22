package com.river.activiti.web.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.activiti.entity.ActivitiResult;
import com.river.activiti.entity.EnumState;
import com.river.activiti.entity.HistoryResult;
import com.river.activiti.entity.JsonResult;
import com.river.activiti.entity.KeyTaskCode;
import com.river.activiti.entity.ProcessResult;
import com.river.activiti.entity.TaskResult;
import com.river.activiti.entity.business.FlowEntity;
import com.river.activiti.service.business.FlowEntityManager;
import com.river.activiti.service.business.FlowEntityWorkflowService;
import com.river.activiti.util.Page;
import com.river.activiti.util.PageUtil;
import com.river.activiti.util.Variable;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected FlowEntityManager flowEntityManager;
    @Autowired
    protected FlowEntityWorkflowService workflowService;
    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;
    

    @Autowired
    ProcessEngineFactoryBean processEngine;
    
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    /**
     * 启动流程
     *
     * @param flowEntity
     */
    @RequestMapping(value = "start", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult startWorkflow(FlowEntity flowEntity) {
        try {
        	if(flowEntity.getUserId().isEmpty()){
        		return JsonResult.Failure("userId is null or empty.");//用户ITCode不允许为空
        	}
        	if(flowEntity.getProjectId().isEmpty()){
        		return JsonResult.Failure("projectId is null or empty.");//项目ID不允许为空
        	}
        	if(flowEntity.getFlowType().isEmpty()){
        		return JsonResult.Failure("flowType is null or empty.");//流程模板标识不允许为空
        	}
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = workflowService.startWorkflow(flowEntity, variables);
            ActivitiResult result=buildActivitiResult(processInstance,0);
            return JsonResult.Success(result);
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                return JsonResult.Failure("没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动流程失败：", e);
                return JsonResult.Failure("系统内部错误:"+e.getMessage());
            }
        } catch (Exception e) {
            logger.error("启动流程失败：", e);
            return JsonResult.Failure("系统内部错误:"+e.getMessage());
        }
    }
    /**
     * 任务列表
     *
     * @param flowEntity
     */
    @RequestMapping(value = "list/task")
    @ResponseBody
    public JsonResult todoTaskList(HttpServletRequest request) {
    	String userId=request.getParameter("userId");
    	if(userId==null || userId.isEmpty()) return JsonResult.Failure("userId is null");
    	List<FlowEntity> list=workflowService.findTodoTasks(userId);
    	return JsonResult.Success(list);
    }
    /**
     * 根据流程实例ID获取所有完成列表
     * @param request
     * @return
     */
    @RequestMapping(value = "tasklist")
    @ResponseBody
    public JsonResult taskList(HttpServletRequest request) {
    	String processInstanceId=request.getParameter("processInstanceId");
    	if(processInstanceId==null || processInstanceId.isEmpty()) return JsonResult.Failure("processInstanceId is null");
        List<Task> tasks= taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        
        return JsonResult.Success(tasks);
        
      
    }
    
    @RequestMapping(value = "history",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonResult historyList(HttpServletRequest request) {
    	String processInstanceId=request.getParameter("processInstanceId");
    	if(processInstanceId==null || processInstanceId.isEmpty()) return JsonResult.Failure("processInstanceId is null");

    	  List<HistoricActivityInstance> list = processEngineConfiguration.getHistoryService()
                  .createHistoricActivityInstanceQuery()
                  .processInstanceId(processInstanceId)
                  .orderByHistoricActivityInstanceStartTime()
                  .asc()
                  .orderByHistoricActivityInstanceEndTime()
                  .asc()
                  .list();
    	  List<HistoryResult> list2=new ArrayList<HistoryResult>();
          if (list != null && list.size() > 0) {
        	  HistoryResult history=null;
              for (HistoricActivityInstance inst : list) {
            	  if("exclusiveGateway".equals(inst.getActivityType())){
            		  continue;
            	  }
            	  history=new HistoryResult();
            	  history.setId(inst.getId());
            	  history.setTaskId(inst.getTaskId());
                  history.setTaskKey(inst.getActivityId());
                  history.setItcode(inst.getAssignee());
                  history.setStartTime(inst.getStartTime());
                  history.setEndTime(inst.getEndTime());
                  history.setTaskTitle(inst.getActivityName());
                  history.setTaskType(inst.getActivityType());
                  list2.add(history);
              }
          }
    	  return JsonResult.Success(list2);
    }
    /**
     * 
     * @param processInstanceId
     */
    public String nextNode(String processInstanceId){
            List<Task> tasks = processEngineConfiguration.getTaskService().createTaskQuery().processInstanceId(processInstanceId).list();
            String nextId = "";
            for (Task task : tasks) {
              /*  RepositoryService rs = processEngineConfiguration.getRepositoryService();
                ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) rs).getDeployedProcessDefinition(task.getProcessDefinitionId());
                List<ActivityImpl> activitiList = def.getActivities(); 
                String excId = task.getExecutionId();
                RuntimeService runtimeService = processEngineConfiguration.getRuntimeService();
                ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId)
                        .singleResult();
                String activitiId = execution.getActivityId();
                for (ActivityImpl activityImpl : activitiList) {
                    String id = activityImpl.getId();
                    if (activitiId.equals(id)) {
                        logger.debug("当前任务：" + activityImpl.getProperty("name")); // 输出某个节点的某种属性
                        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
                        for (PvmTransition tr : outTransitions) {
                            PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
                            logger.debug("下一步任务任务：" + ac.getProperty("name"));
                            nextId = ac.getId();
                        }
                        break;
                    }
                }*/
            }
            return nextId;
    }
    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "list/running")
    @ResponseBody
    public Page<FlowEntity> runningList(HttpServletRequest request) {
        Page<FlowEntity> page = new Page<FlowEntity>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        workflowService.findRunningProcessInstaces(page, pageParams);
        return page;
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "list/finished")
    @ResponseBody
    public Page<FlowEntity> finishedList(HttpServletRequest request) {
        Page<FlowEntity> page = new Page<FlowEntity>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        workflowService.findFinishedProcessInstaces(page, pageParams);
        return page;
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "task/claim/{id}")
    @ResponseBody
    public JsonResult claim(@PathVariable("id") String taskId, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        taskService.claim(taskId, userId);
        return JsonResult.Success("任务已签收");
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}")
    @ResponseBody
    public FlowEntity getFlowEntity(@PathVariable("id") Long id) {
        FlowEntity flowEntity = flowEntityManager.getFlowEntity(id);
        return flowEntity;
    }
    /**
     * 
     * @param processInstanceId
     * @return
     */
    @RequestMapping(value = "getProcess", method ={ RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonResult getProcess(HttpServletRequest request) {
    	String processInstanceId=request.getParameter("processInstanceId");
    	if(processInstanceId==null || processInstanceId.isEmpty()) return JsonResult.Failure("processInstanceId is null");
    	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
          		.processInstanceId(processInstanceId).singleResult();
    	if(processInstance==null) return JsonResult.Failure("processInstance is null");
    	ProcessDefinition pd=workflowService.getProcessDefinition(processInstance.getProcessDefinitionId());
    	if(pd==null) return JsonResult.Failure("processDefinition is null");
    	ProcessResult res=new ProcessResult();
    	res.setProcessId(pd.getId());
    	res.setProcessKey(pd.getKey());
    	res.setProcessType(KeyTaskCode.toFlowType(pd.getKey()));
        return JsonResult.Success(res);
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail-with-vars/{id}/{taskId}")
    @ResponseBody
    public FlowEntity getFlowEntityWithVars(@PathVariable("id") Long id, @PathVariable("taskId") String taskId) {
        FlowEntity flowEntity = flowEntityManager.getFlowEntity(id);
        Map<String, Object> variables = taskService.getVariables(taskId);
        flowEntity.setVariables(variables);
        return flowEntity;
    }

    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{task}", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult complete(@PathVariable("task") String taskCode, Variable var) {
        try {
            Map<String, Object> variables = var.getVariableMap();
            if(!variables.containsKey("userId")) return JsonResult.Failure("userId is null.");
            if(!variables.containsKey("projectId")) return JsonResult.Failure("projectId is null.");
            if(!variables.containsKey("taskKey")) return JsonResult.Failure("taskKey is null.");
            if(!variables.containsKey("processInstanceId")) return JsonResult.Failure("processInstanceId is null.");
            String userId=(String)variables.get("userId");
            String processInstanceId=(String)variables.get("processInstanceId");
            taskCode=(String)variables.get("taskKey");//这里暂时从变量中获取
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
            		.processInstanceId(processInstanceId).singleResult();
            if(processInstance==null) return JsonResult.Failure("no instance");
			List<Task> tasks= taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
			
			
			for (Task task : tasks) {		
				
				if(task.getTaskDefinitionKey().equals(taskCode)){
					//taskService.addCandidateGroup(taskId, groupId);
					//taskService.createAttachment(attachmentType, taskId, processInstanceId, attachmentName, attachmentDescription, content)
					if(task.getAssignee()==null || task.getAssignee().isEmpty()){
						taskService.claim(task.getId(), userId);//签收
					}else if(!userId.equalsIgnoreCase(task.getAssignee())){
						return JsonResult.Failure("任务已被签收："+task.getAssignee());
					}
					taskService.complete(task.getId(), variables);//完成节点操作
				}
			}
            ActivitiResult result=buildActivitiResult(processInstance,1);
            return JsonResult.Success(result);
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskCode, var.getVariableMap(), e});
            return JsonResult.Failure("error:"+e.getMessage());
        }
    }
    /**
     * 构造当前节点的
     * @param processInstance
     * @return
     */
    private ActivitiResult buildActivitiResult(ProcessInstance processInstance,int node){
    	  ActivitiResult result=new ActivitiResult();
          result.setProcessInstanceId(processInstance.getId());
          result.setProcessKey(processInstance.getProcessDefinitionKey());
          List<Task> tasks= taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
          TaskResult tRes;
    	  String processKey=processInstance.getProcessDefinitionKey();
          if(tasks!=null && tasks.size()>0){
	          for (Task task : tasks) {
	          	tRes=new TaskResult();
	          	tRes.setTaskKey(task.getTaskDefinitionKey());
	          	tRes.setTaskId(task.getId());
	          	tRes.setGroups(task.getAssignee());
	          	tRes.setUsers(task.getOwner());
	          	result.addTaskResult(tRes);
	          }
          }else{//结束流程
        	  if(processKey.indexOf("setupApply")>-1){
        		  result.setTaskState(EnumState.FeasibilityApply.getState()+"");
        		  result.setTaskMemo("立项结束");
        	  }else if(processKey.indexOf("feasibilityApply")>-1){
        		  result.setTaskState(EnumState.DecisionApply.getState()+"");
        		  result.setTaskMemo("可研结束");
        	  }else if(processKey.indexOf("decisionApply")>-1 || processKey.indexOf("modificationApply")>-1){
        		  result.setTaskState(EnumState.Building.getState()+"");
        		  result.setTaskMemo("投中建设");
        	  }else if(processKey.indexOf("completeApply")>-1){//退出
        		  result.setTaskState(EnumState.Complete.getState()+"");
        		  result.setTaskMemo("已竣工/结项");
        	  }else if(processKey.indexOf("afterApply")>-1){//投后价值评估
        		  result.setTaskState(EnumState.Complete.getState()+"");
        		  result.setTaskMemo("已投后评估");
        	  }else if(processKey.indexOf("exitApply")>-1){//退出
        		  result.setTaskState(EnumState.Knot.getState()+"");
        		  result.setTaskMemo("已退出");
        	  }
          }
          if(node==0){//发起流程
        	  if(processKey.indexOf("setupApply")>-1){
        		  result.setTaskState(EnumState.Setup.getState()+"");
        		  result.setTaskMemo("立项中");
        	  }else if(processKey.indexOf("feasibilityApply")>-1){
        		  result.setTaskState(EnumState.Feasibility.getState()+"");
        		  result.setTaskMemo("可研中");
        	  }else if(processKey.indexOf("decisionApply")>-1){
        		  result.setTaskState(EnumState.Decision.getState()+"");
        		  result.setTaskMemo("投决中");
        	  }else if(processKey.indexOf("modificationApply")>-1){
        		  result.setTaskState(EnumState.Modification.getState()+"");
        		  result.setTaskMemo("变更中");
        	  }else if(processKey.indexOf("completeApply")>-1){//退出
        		  result.setTaskState(EnumState.Completing.getState()+"");
        		  result.setTaskMemo("竣工/结项申请中");
        	  }else if(processKey.indexOf("afterApply")>-1){//投后价值评估
        		  result.setTaskState(EnumState.Evaluate.getState()+"");
        		  result.setTaskMemo("投后价值评估");
        	  }else if(processKey.indexOf("exitApply")>-1){//退出
        		  result.setTaskState(EnumState.Knoting.getState()+"");
        		  result.setTaskMemo("退出申请中");
        	  }
          }
          return result;
    }
    
    @RequestMapping(value = "completeTask/{taskid}", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult completeTask(@PathVariable("taskid") String taskId, Variable var) {
        try {
            Map<String, Object> variables = var.getVariableMap();
            if(!variables.containsKey("userId")) return JsonResult.Failure("userId is null.");
            if(!variables.containsKey("projectId")) return JsonResult.Failure("projectId is null.");
            String userId=(String)variables.get("userId");
        	taskService.claim(taskId, userId);//签收
            taskService.complete(taskId, variables);
            ActivitiResult result=null;//buildActivitiResult(processInstance);
            return JsonResult.Success(result);
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, var.getVariableMap(), e});
            return JsonResult.Failure("error");
        }
    }

}

