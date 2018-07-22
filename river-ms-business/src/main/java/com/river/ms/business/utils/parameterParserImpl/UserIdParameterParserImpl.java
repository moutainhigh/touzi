package com.river.ms.business.utils.parameterParserImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.river.core.entity.UserEntity;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectFlowVariable;
import com.river.ms.business.utils.ActivitiParameterParserInterface;

@Component
public class UserIdParameterParserImpl implements ActivitiParameterParserInterface {

	@Override
	public Object getValue(HttpServletRequest request, Long projectId, ProjectFlowVariable projectFlowVariable) {
		UserEntity user = SessionUtils.getUser(request);
		if(user != null && user.getEntityCode() != null) {
			return user.getEntityCode();
		}
		return null;
	}

}
