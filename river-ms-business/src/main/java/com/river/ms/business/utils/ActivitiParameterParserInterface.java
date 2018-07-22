package com.river.ms.business.utils;

import javax.servlet.http.HttpServletRequest;

import com.river.ms.business.entity.ProjectFlowVariable;

public interface ActivitiParameterParserInterface {

	Object getValue(HttpServletRequest request,Long projectId,ProjectFlowVariable projectFlowVariable);
}
