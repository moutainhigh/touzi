package com.river.ms.project.mapper;

import com.river.ms.project.entity.ResRefRoleOrg;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2018-04-19
 */
@Mapper
public interface ResRefRoleOrgDao extends BaseMapper<ResRefRoleOrg> {

	List<ResRefRoleOrg> getResRefRoleOrgByResIdAndRole(@Param("resId") Long resId, @Param("itcode") String itcode,
			@Param("roleIds") List<Long> roleIds, @Param("organizationCode") String organizationCode);

}
