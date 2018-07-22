package com.river.ms.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.ElementEntity;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.MenuEntity;
import com.river.core.entity.OrganizationEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.StationEntity;
import com.river.core.entity.UserEntity;
import com.river.ms.system.mapper.IElementMapper;
import com.river.ms.system.mapper.IEmployeeMapper;
import com.river.ms.system.mapper.IMenuMapper;
import com.river.ms.system.mapper.IOrganizationMapper;
import com.river.ms.system.mapper.IStationEmployeeMapper;
import com.river.ms.system.mapper.IStationMapper;
import com.river.ms.system.mapper.IUserRoleFunction;

/**
 * 
 * @author my
 *
 */
@Service
public class UserRoleFunctionService {

	@Autowired
	IUserRoleFunction userRoleFunction;

	@Autowired
	IMenuMapper menuMapper;

	@Autowired
	IElementMapper elementMapper;

	@Autowired
	IEmployeeMapper employeeMapper;

	@Autowired
	IStationEmployeeMapper stationEmployeeMapper;

	@Autowired
	IOrganizationMapper organizationMapper;
	
	@Autowired
	IStationMapper stationMapper;

	/**
	 * 查询员工信息，包含角色和功能
	 * 
	 * @param code
	 * @return
	 */
	public UserEntity getUserByCode(String code) {
		UserEntity user = userRoleFunction.getUserByCode(code);
		return user;
	}

	/**
	 * 根据功能查询菜单
	 * 
	 * @param roleAndFunction
	 * @return
	 */
	public List<MenuEntity> getMenu(Map<String, List<String>> roleAndFunction) {
		List<String> funcitons = this.getFuncitons(roleAndFunction);
		List<MenuEntity> menu = menuMapper.getMenuByFunction(0, funcitons);
		for (MenuEntity c : menu) {
			c.setChildMenu(this.getChildMenu(c, funcitons));
		}
		return menu;
	}

	/**
	 * 将角色中的功能筛选出来，以list形式展示
	 * 
	 * @param roleAndFunction
	 * @return
	 */
	private List<String> getFuncitons(Map<String, List<String>> roleAndFunction) {
		List<String> result = new ArrayList<String>();
		Set<Entry<String, List<String>>> entrySet = roleAndFunction.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			for (String s : entry.getValue()) {
				result.add(s);
			}
		}
		return result;
	}

	private List<MenuEntity> getChildMenu(MenuEntity menu, List<String> funcitons) {
		List<MenuEntity> child = menuMapper.getMenuByFunction(menu.getEntityId(), funcitons);
		if (child != null && child.size() != 0) {
			for (MenuEntity c : child) {
				c.setChildMenu(this.getChildMenu(c, funcitons));
			}
		}
		return child;
	}

	/**
	 * 获取所有的对话元素
	 * 
	 * @param roleAndFunction
	 * @return
	 */
	public List<ElementEntity> getElements(Map<String, List<String>> roleAndFunction) {
		List<String> funcitons = this.getFuncitons(roleAndFunction);
		List<ElementEntity> element = elementMapper.getElementByFunction(0, funcitons);
		for (ElementEntity c : element) {
			c.setChildElements(this.getChildElements(c, funcitons));
		}
		return element;
	}

	private List<ElementEntity> getChildElements(ElementEntity element, List<String> funcitons) {
		List<ElementEntity> child = elementMapper.getElementByFunction(element.getEntityId(), funcitons);
		if (child != null && child.size() != 0) {
			for (ElementEntity c : child) {
				c.setChildElements(this.getChildElements(c, funcitons));
			}
		}
		return child;
	}

	public EmployeeEntity getEmployeeEntity(Long employeeId) {
		EmployeeEntity byId = employeeMapper.getById(employeeId);
		if (byId != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("employeeId", byId.getEntityId());
			List<StationEmployeeEntity> stationEmployees = stationEmployeeMapper.queryBy(map);
			if (stationEmployees != null && stationEmployees.size() > 0) {
				for (StationEmployeeEntity s : stationEmployees) {
					StationEntity stationEntity = new StationEntity();
					if(s.getStationId() != null) {
						stationEntity = stationMapper.getById(s.getStationId());
					}
					s.setStation(stationEntity);
					OrganizationEntity organization = organizationMapper.getById(s.getOrganizationId());
					if (organization != null) {
						s.setOrganization(organization);
						if(organization.getOrganType() == 2) {
							s.setHangyegongsi(organization);
						} else {
							s.setHangyegongsi(this.getHangyegongsi(organization.getParentId()));
						}
					}
				}
			}
			byId.setStationEmployees(stationEmployees);
		}
		return byId;
	}
	
	private OrganizationEntity getHangyegongsi(long id) {
		OrganizationEntity byId = organizationMapper.getById(id);
		if(byId != null) {
			if(byId.getOrganType() == 2) {
				return byId;
			} else {
				return this.getHangyegongsi(byId.getParentId());
			}
		} else {
			return null;
		}
	}
}
