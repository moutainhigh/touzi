package com.river.ms.business.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.river.core.utils.ListToString;
import com.river.ms.business.entity.ProjectFlowVariable;

@Component
public class ActivitiParameterHandle {

	public Map<String, Object> getactivitiQueryMap(HttpServletRequest request, Long projectId,
			List<ProjectFlowVariable> projectFlowVariable) {

		ServletContext servletContext = request.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

		Map<String, Object> activitiQueryMap = new HashMap<>();
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		List<String> types = new ArrayList<>();
		for (ProjectFlowVariable variable : projectFlowVariable) {
			keys.add(variable.getVariableName());
			String parser = variable.getParser();
			Object value = null;
			if (parser != null && !parser.equals("")) {
				Object bean = ac.getBean(parser);
				boolean assignableFrom = ActivitiParameterParserInterface.class.isAssignableFrom(bean.getClass());
				if (assignableFrom) {
					ActivitiParameterParserInterface parameterParser = (ActivitiParameterParserInterface) bean;
					value = parameterParser.getValue(request, projectId, variable);
				}
			}
			if (value != null) {
				values.add(value);
			} else if (variable.getDefaultValue() != null) {
				values.add(variable.getDefaultValue());
			} else {
				values.add("");
			}
			types.add(variable.getVariableType());
		}
		activitiQueryMap.put("keys", ListToString.toString(keys));
		activitiQueryMap.put("values", ListToString.toString(values));
		activitiQueryMap.put("types", ListToString.toString(types));
		return activitiQueryMap;
	}
}
