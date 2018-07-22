package com.river.ms.project.service.impl;

import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.ms.project.entity.ResRefRoleOrg;
import com.river.ms.project.mapper.ResRefRoleOrgDao;
import com.river.ms.project.service.MPResRefRoleOrgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2018-04-19
 */
@Service
public class ResRefRoleOrgServiceDiy extends ServiceImpl<ResRefRoleOrgDao, ResRefRoleOrg>
		implements MPResRefRoleOrgService {

	@Override
	public Map<String, Boolean> getResRefRoleOrg(UserEntity user) {
		Map<String, Boolean> groupCode = null;
		// 获取用户资源ID
		Long resId = user.getResId();
		// 获取用户itcode
		String itcode = user.getEntityCode();
		// 获取用户的角色ID
		List<Long> roleIds = new ArrayList<>();
		List<RoleEntity> roles = user.getRoles();
		for (RoleEntity role : roles) {
			roleIds.add(role.getEntityId());
		}
		String organizationCode = null;
		EmployeeEntity employee = user.getEmployee();
		List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
		for (StationEmployeeEntity emp : stationEmployees) {
			if (emp.getIsChief().equals(1)) {
				organizationCode = emp.getOrganization().getEntityCode();
				break;
			}
		}
		if (resId != null && resId != 0 && itcode != null && !itcode.equals("") && roleIds != null
				&& roleIds.size() > 0) {
			// 查询用户的数据权限
			List<ResRefRoleOrg> resRefRoleOrgs = this.baseMapper.getResRefRoleOrgByResIdAndRole(resId, itcode, roleIds,
					organizationCode);
			if (!resRefRoleOrgs.isEmpty()) {
				groupCode = this.getGroupCode(user, resRefRoleOrgs);
			}
		}
		return groupCode;
	}

	/**
	 * 处理用户的数据权限
	 * 
	 * @param user
	 * @param resRefRoleOrgs
	 */
	public Map<String, Boolean> getGroupCode(UserEntity user, List<ResRefRoleOrg> resRefRoleOrgs) {
		Map<String, Boolean> result = null;
		// 处理数据权限
		if (resRefRoleOrgs != null && !resRefRoleOrgs.isEmpty()) {
			List<ResRefRoleOrg> validResRefRoleOrg = new ArrayList<>();
			validResRefRoleOrg.addAll(resRefRoleOrgs);
			
			/*// 数据权限根据角色分类
			Map<Long, List<ResRefRoleOrg>> resRefRoleOrgMap = new HashMap<>();
			for (ResRefRoleOrg resRefRoleOrg : resRefRoleOrgs) {
				Long roleId = resRefRoleOrg.getRoleId();
				if (roleId != null && roleId != 0) {
					boolean containsKey = resRefRoleOrgMap.containsKey(roleId);
					if (!containsKey) {
						List<ResRefRoleOrg> list = new ArrayList<>();
						resRefRoleOrgMap.put(roleId, list);
					}
					resRefRoleOrgMap.get(roleId).add(resRefRoleOrg);
				}
			}
			// 提取有效的数据权限
			List<ResRefRoleOrg> validResRefRoleOrg = new ArrayList<>();
			if (!resRefRoleOrgMap.isEmpty()) {
				Set<Entry<Long, List<ResRefRoleOrg>>> resRefRoleOrgMapEntrySet = resRefRoleOrgMap.entrySet();
				for (Entry<Long, List<ResRefRoleOrg>> entry : resRefRoleOrgMapEntrySet) {
					Integer type = 3;
					List<ResRefRoleOrg> value = entry.getValue();
					List<ResRefRoleOrg> validResRefRoleOrgValue = new ArrayList<>();
					for (ResRefRoleOrg resRefRoleOrg : value) {
						validResRefRoleOrgValue.add(resRefRoleOrg);
						Integer resRefRoleOrgType = 3;
						if (resRefRoleOrg.getResourceId() != null && resRefRoleOrg.getResourceId() != 0
								&& resRefRoleOrg.getItcode() != null && !resRefRoleOrg.getItcode().equals("")) {
							resRefRoleOrgType = 1;
						}
						if (resRefRoleOrg.getResourceId() == null && resRefRoleOrg.getItcode() == null
								&& resRefRoleOrg.getGroupCode() != null && !resRefRoleOrg.getGroupCode().equals("")) {
							resRefRoleOrgType = 2;
						}
						if (resRefRoleOrg.getResourceId() == null && resRefRoleOrg.getItcode() == null
								&& resRefRoleOrg.getGroupCode() == null) {
							resRefRoleOrgType = 3;
						}
						if (type > resRefRoleOrgType) {
							type = resRefRoleOrgType;
							validResRefRoleOrgValue.clear();
						}
						if (type == resRefRoleOrgType) {
							validResRefRoleOrgValue.add(resRefRoleOrg);
						}
	
					}
					if (!validResRefRoleOrgValue.isEmpty()) {
						validResRefRoleOrg.addAll(validResRefRoleOrgValue);
					}
				}
			}*/
			// 提取权限数据
			if (!validResRefRoleOrg.isEmpty()) {
				for (ResRefRoleOrg resRefRoleOrg : validResRefRoleOrg) {
					if (resRefRoleOrg.getIsAll() != null && resRefRoleOrg.getIsAll().equals(1)) {
						if (result == null) {
							result = new HashMap<>();
						}
						result.clear();
						break;
					} else if (resRefRoleOrg.getIsOneselfOrg() != null && resRefRoleOrg.getIsOneselfOrg().equals(1)) {
						Boolean bool = false;
						if (resRefRoleOrg.getIsIncludeSubordinate() != null
								&& resRefRoleOrg.getIsIncludeSubordinate().equals(1)) {
							bool = true;
						}
						EmployeeEntity employee = user.getEmployee();
						List<StationEmployeeEntity> stationEmployees = employee.getStationEmployees();
						for (StationEmployeeEntity emp : stationEmployees) {
							if (emp.getIsChief().equals(1)) {
								if (result == null) {
									result = new HashMap<>();
								}
								this.putValue(result, emp.getOrganization().getEntityCode(), bool);
								break;
							}
						}
					} else if (resRefRoleOrg.getGroupCode() != null) {
						Boolean bool = false;
						if (resRefRoleOrg.getIsIncludeSubordinate() != null
								&& resRefRoleOrg.getIsIncludeSubordinate().equals(1)) {
							bool = true;
						}
						if (result == null) {
							result = new HashMap<>();
						}
						this.putValue(result, resRefRoleOrg.getGroupCode(), bool);
					}
					/*
					 * if (resRefRoleOrg.getGroupCode() != null) { if (resRefRoleOrg.getResourceId()
					 * != null && resRefRoleOrg.getResourceId() != 0 && resRefRoleOrg.getItcode() !=
					 * null && !resRefRoleOrg.getItcode().equals("")) { Boolean bool = false; if
					 * (resRefRoleOrg.getIsIncludeSubordinate() != null &&
					 * resRefRoleOrg.getIsIncludeSubordinate().equals(1)) { bool = true; } if
					 * (result == null) { result = new HashMap<>(); } this.putValue(result,
					 * resRefRoleOrg.getGroupCode(), bool); } else { if
					 * (resRefRoleOrg.getIsOneselfOrg() != null &&
					 * resRefRoleOrg.getIsOneselfOrg().equals(1)) { Boolean bool = false; if
					 * (resRefRoleOrg.getIsIncludeSubordinate() != null &&
					 * resRefRoleOrg.getIsIncludeSubordinate().equals(1)) { bool = true; }
					 * EmployeeEntity employee = user.getEmployee(); List<StationEmployeeEntity>
					 * stationEmployees = employee.getStationEmployees(); for (StationEmployeeEntity
					 * emp : stationEmployees) { if (emp.getIsChief().equals(1)) { if (result ==
					 * null) { result = new HashMap<>(); } this.putValue(result,
					 * emp.getOrganization().getEntityCode(), bool); break; } } } else { if
					 * (resRefRoleOrg.getIsAll() != null && resRefRoleOrg.getIsAll().equals(1)) { if
					 * (result == null) { result = new HashMap<>(); } result.clear(); break; } } } }
					 * else { if (resRefRoleOrg.getIsOneselfOrg() != null &&
					 * resRefRoleOrg.getIsOneselfOrg().equals(1)) { Boolean bool = false; if
					 * (resRefRoleOrg.getIsIncludeSubordinate() != null &&
					 * resRefRoleOrg.getIsIncludeSubordinate().equals(1)) { bool = true; }
					 * EmployeeEntity employee = user.getEmployee(); List<StationEmployeeEntity>
					 * stationEmployees = employee.getStationEmployees(); for (StationEmployeeEntity
					 * emp : stationEmployees) { if (emp.getIsChief().equals(1)) { if (result ==
					 * null) { result = new HashMap<>(); } this.putValue(result,
					 * emp.getOrganization().getEntityCode(), bool); break; } } } else { if
					 * (resRefRoleOrg.getIsAll() != null && resRefRoleOrg.getIsAll().equals(1)) { if
					 * (result == null) { result = new HashMap<>(); } result.clear(); break; } } }
					 */
				}
			}
		}
		return result;
	}

	public void putValue(Map<String, Boolean> result, String key, Boolean value) {
		if (result.containsKey(key)) {
			if (value) {
				result.put(key, value);
			}
		} else {
			result.put(key, value);
		}
	}
}
