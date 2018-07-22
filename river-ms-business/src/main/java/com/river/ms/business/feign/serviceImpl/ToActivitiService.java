package com.river.ms.business.feign.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ActivitiTasks;
import com.river.ms.business.entity.HistoryResult;
import com.river.ms.business.entity.ProcessResult;
import com.river.ms.business.feign.service.ToActiviti;

@Service
public class ToActivitiService {

	@Autowired
	private ToActiviti toActiviti;

	/**
	 * 
	 * @param headerMap
	 * @param itCode
	 * @param projectId
	 * @param flowType
	 *            setupApply,feasibilityApply,decisionApply,modificationApply
	 * @param reason
	 * @return
	 */
	public ActivitiResult startActiviti(Map<String, Object> headerMap, String itCode, Long projectId, String flowType,
			String reason) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("userId", itCode);
		queryMap.put("projectId", projectId);
		queryMap.put("flowType", flowType);
		queryMap.put("reason", reason);
		JsonResult start = this.toActiviti.start(headerMap, queryMap);
		ActivitiResult activitiResult = this.toActivitiResult(start);
		return activitiResult;
	}
	/**
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	public ActivitiResult nodeEnd(Map<String, Object> headerMap, Map<String, Object> queryMap) {
		JsonResult nodeEnd = this.toActiviti.nodeEnd(headerMap, queryMap);
		ActivitiResult activitiResult = this.toActivitiResult(nodeEnd);
		return activitiResult;
	}
	/**
	 * 
	 * @param jsonResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ActivitiResult toActivitiResult(JsonResult jsonResult) {
		ActivitiResult result = null;
		if (jsonResult != null) {
			int status = jsonResult.getStatus();
			result = new ActivitiResult();
			result.setStatus(status);
			if (status == 0) {
				result.setMessage(jsonResult.getMessage());
				Map<String, Object> data = (Map<String, Object>) jsonResult.getData();
				result.setProcessInstanceId((String) data.get("processInstanceId"));
				result.setProcessKey((String) data.get("processKey"));
				if(data.get("taskMemo") != null && !data.get("taskMemo").equals("") && !data.get("taskMemo").equals("null")) {
					result.setTaskMemo(data.get("taskMemo").toString());
				}else {
					result.setTaskMemo("");
				}
				if(data.get("taskState") != null && !data.get("taskState").equals("") && !data.get("taskState").equals("null")) {
					result.setTaskState(data.get("taskState").toString());
				}else {
					result.setTaskState("-99");
				}
				if(data.get("tasks") == null) {
					result.setTasks(null);
				}else {
					List<Map<String, Object>> tasks = (List<Map<String, Object>>) data.get("tasks");
					List<ActivitiTasks> tasksList = new ArrayList<>();
					for (Map<String, Object> t : tasks) {
						ActivitiTasks task = new ActivitiTasks();
						task.setUsers((String) t.get("users"));
						task.setTaskKey((String) t.get("taskKey"));
						task.setGroups((String) t.get("groups"));
						task.setTaskId((String) t.get("taskId"));
						tasksList.add(task);
					}
					result.setTasks(tasksList);
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据流程实例ID获取流程相关信息 processInstanceId
	 * @param headerMap
	 * @param processInstanceId
	 * @return
	 */
	public ProcessResult getProcess(Map<String, Object> headerMap,String processInstanceId) {
		ProcessResult result = new ProcessResult();
		if (processInstanceId != null && !processInstanceId.equals("")) {
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("processInstanceId", processInstanceId);
			JsonResult process = this.toActiviti.getProcess(headerMap, queryMap);
			if (process != null && process.getStatus() != -1) {
				Map<String, Object> data = (Map<String, Object>) process.getData();
				Object processId = data.get("processId");
				if(processId != null) {
					result.setProcessId(processId.toString());
				}
				Object processKey = data.get("processKey");
				if(processKey != null) {
					result.setProcessKey(processKey.toString());
				}
				Object processType = data.get("processType");
				if(processType != null) {
					result.setProcessType(Integer.valueOf(processType.toString()));
				}
			} 
		}
		return result;
	}
	/**
	 * 获取历史信息
	 * @param headerMap
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoryResult> getHistory(Map<String, Object> headerMap,String processInstanceId) {
		List<HistoryResult> result = new ArrayList<>();
		if (processInstanceId != null && !processInstanceId.equals("")) {
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("processInstanceId", processInstanceId);
			JsonResult history = this.toActiviti.getHistory(headerMap, queryMap);
			if (history != null && history.getStatus() != -1) {
				String jsonString = JSONArray.toJSONString(history.getData());
				result = JSONArray.parseArray(jsonString, HistoryResult.class);
			} 
		}
		Collections.sort(result);
		return result;
	}
}
