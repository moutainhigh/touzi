package com.river.ms.system.webService.userWB;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.river.ms.system.webService.entity.User;

@WebService(name = "userWB", 
			targetNamespace = "userWBNamespace", 
			serviceName = "userWBService", 
			portName = "userWBPort"
			)
public interface UserWBService {

	@WebMethod
	void addUserList(List<User> userlist);
	
	@WebMethod
	void addUser(User user);
	
	@WebMethod
	void updateUser(User user);
	
	@WebMethod
	void updateList(List<User> userlist);
	
	@WebMethod
	void delete(String id);
	
	@WebMethod
	void batchDeleteUser(List<String> ids);
}
