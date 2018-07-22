package com.river.ms.system.webService.organizationWB;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.river.core.result.JsonResult;
import com.river.ms.system.webService.entity.Organization;
import com.river.ms.system.webService.entity.WSOperationRecord;
import com.river.ms.system.webService.mapper.IOraganizationWBMapper;
import com.river.ms.system.webService.mapper.IWSOperationRecordMapper;

@WebService(name = "OrganizationWB", targetNamespace = "OrganizationWBtargetNamespace", serviceName = "OrganizationWBService", portName = "OrganizationWBPort")
public class OrganizationWBServiceImpl implements OrganizationWBService {

	@Autowired
	IOraganizationWBMapper mapper;
	
	@Autowired
	IWSOperationRecordMapper omapper;
	
	private String tableName = "enn_organization";

	@Override
	public JsonResult addUserOrganizationList(List<Organization> userOrganizationList) {
		int insert = mapper.insert(userOrganizationList);
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : userOrganizationList) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		return new JsonResult(0, "addUserOrganizationList", insert);
	}

	@Override
	public JsonResult addUserOrganization(Organization userOrganization) {
		List<Organization> list = new ArrayList<>();
		list.add(userOrganization);
		int insert = mapper.insert(list);
		
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		
		return new JsonResult(0, "addUserOrganization", insert);
	}

	@Override
	public JsonResult updateUserOrganization(Organization userOrganization) {
		List<Organization> list = new ArrayList<>();
		list.add(userOrganization);
		int insert = mapper.insert(list);
		
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		
		return new JsonResult(0, "updateUserOrganization", insert);
	}

	@Override
	public JsonResult updateUserOrganizationList(List<Organization> userOrganizationList) {
		int insert = mapper.insert(userOrganizationList);
		
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : userOrganizationList) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		
		return new JsonResult(0, "updateUserOrganizationList", insert);
	}

	@Override
	public JsonResult deleteUserOrganization(String userOrganizationOrgeh) {
		List<Organization> list = new ArrayList<>();
		Organization userOrganization = new Organization();
		userOrganization.setOrgeh(userOrganizationOrgeh);
		list.add(userOrganization);
		int insert = mapper.insert(list);
		
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		
		return new JsonResult(0, "deleteUserOrganization", insert);
	}

	@Override
	public JsonResult batchDeleteUserOrganization(List<String> userOrganizationOrgehList) {
		List<Organization> list = new ArrayList<>();
		for(String s : userOrganizationOrgehList) {
			Organization userOrganization = new Organization();
			userOrganization.setOrgeh(s);
			list.add(userOrganization);
		}
		int insert = mapper.insert(list);
		
		List<WSOperationRecord> olist = new ArrayList<>();
		for(Organization o : list) {
			if (o.getEntityId() != null && o.getEntityId() != 0) {
				olist.add(new WSOperationRecord(null, tableName, o.getEntityId()));
			}
		}
		omapper.insert(olist);
		
		return new JsonResult(0, "batchDeleteUserOrganization", insert);
	}

}
