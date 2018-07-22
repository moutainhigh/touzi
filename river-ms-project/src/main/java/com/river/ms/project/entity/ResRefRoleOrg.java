package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyb
 * @since 2018-04-19
 */
@TableName("res_ref_role_org")
public class ResRefRoleOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体ID
     */
	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 资源ID
     */
	private Long resourceId;
    /**
     * 资源itcode
     */
	private String itcode;
    /**
     * 角色ID
     */
	private Long roleId;
    /**
     * 公司编码
     */
	private String groupCode;
    /**
     * 是否包含下级 0-不包含，1-包含
     */
	private Integer isIncludeSubordinate;
    /**
     * 是否是本人所在组织机构 0-不是，1-是
     */
	private Integer isOneselfOrg;
    /**
     * 是否可查看全部 0-不是，1-是
     */
	private Integer isAll;


	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getIsIncludeSubordinate() {
		return isIncludeSubordinate;
	}

	public void setIsIncludeSubordinate(Integer isIncludeSubordinate) {
		this.isIncludeSubordinate = isIncludeSubordinate;
	}

	public Integer getIsOneselfOrg() {
		return isOneselfOrg;
	}

	public void setIsOneselfOrg(Integer isOneselfOrg) {
		this.isOneselfOrg = isOneselfOrg;
	}

	public Integer getIsAll() {
		return isAll;
	}

	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}

	@Override
	public String toString() {
		return "ResRefRoleOrg{" +
			", entityId=" + entityId +
			", resourceId=" + resourceId +
			", itcode=" + itcode +
			", roleId=" + roleId +
			", groupCode=" + groupCode +
			", isIncludeSubordinate=" + isIncludeSubordinate +
			", isOneselfOrg=" + isOneselfOrg +
			", isAll=" + isAll +
			"}";
	}
}
