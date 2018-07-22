package com.river.ms.system.webService.userWB;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.river.ms.system.webService.entity.User;
import com.river.ms.system.webService.entity.WSOperationRecord;
import com.river.ms.system.webService.mapper.IUserWBMapper;
import com.river.ms.system.webService.mapper.IWSOperationRecordMapper;

@WebService(name = "userWB", targetNamespace = "userWBNamespace", serviceName = "userWBService", portName = "userWBPort")
public class UserWBServiceImpl implements UserWBService {

	@Autowired
	IUserWBMapper mapper;

	@Autowired
	IWSOperationRecordMapper omapper;

	private String tableName = "enn_user";

	@Override
	public void addUserList(List<User> userlist) {
		mapper.insert(userlist);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : userlist) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

	@Override
	public void addUser(User user) {
		List<User> userlist = new ArrayList<>();
		userlist.add(user);
		mapper.insert(userlist);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : userlist) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

	@Override
	public void updateUser(User user) {
		List<User> list = new ArrayList<>();
		list.add(user);
		mapper.insert(list);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

	@Override
	public void updateList(List<User> userlist) {
		mapper.insert(userlist);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : userlist) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

	@Override
	public void delete(String id) {
		List<User> list = new ArrayList<>();
		User u = new User();
		u.setUserId(id);
		list.add(u);
		mapper.insert(list);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

	@Override
	public void batchDeleteUser(List<String> ids) {
		List<User> list = new ArrayList<>();
		for (String s : ids) {
			User user = new User();
			user.setUserId(s);
			list.add(user);
		}
		mapper.insert(list);

		List<WSOperationRecord> olist = new ArrayList<>();
		for (User o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
	}

}
