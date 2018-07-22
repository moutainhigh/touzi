package com.river.ms.system.webService.organizationWB;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.river.core.result.JsonResult;
import com.river.ms.system.webService.entity.Organization;

@WebService(name = "OrganizationWB", 
			targetNamespace = "OrganizationWBtargetNamespace", 
			serviceName = "OrganizationWBService", 
			portName = "OrganizationWBPort"
			)
public interface OrganizationWBService {

	/**
	 * 批量增加用户组织
	 * 
	 * @param userOrganizationList
	 * @return
	 */
	@WebMethod
	JsonResult addUserOrganizationList(List<Organization> userOrganizationList);

	/**
	 * 增加用户组织
	 * 
	 * @param userOrganization
	 * @return
	 */
	@WebMethod
	JsonResult addUserOrganization(Organization userOrganization);

	/**
	 * 更新用户组织
	 * 
	 * @param userOrganization
	 * @return
	 */
	@WebMethod
	JsonResult updateUserOrganization(Organization userOrganization);

	/**
	 * 批量更新用户组织
	 * 
	 * @param userOrganizationList
	 * @return
	 */
	@WebMethod
	JsonResult updateUserOrganizationList(List<Organization> userOrganizationList);

	/**
	 * 删除用户组织
	 * 
	 * @param userOrganizationOrgeh
	 * @return
	 */
	@WebMethod
	JsonResult deleteUserOrganization(String userOrganizationOrgeh);

	/**
	 * 批量删除用户组织
	 * 
	 * @param userOrganizationOrgehList
	 * @return
	 */
	@WebMethod
	JsonResult batchDeleteUserOrganization(List<String> userOrganizationOrgehList);

}
