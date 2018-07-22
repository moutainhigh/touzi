package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectPartnerMember;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 合伙人 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@Mapper
public interface ProjectPartnerMemberDao extends BaseMapper<ProjectPartnerMember> {

	List<ProjectPartnerMember> selectProjectPartnerMemberByProjectPartnerId(@Param("partnerId")Long partnerId);
	void updateAllRateByProjectId(@Param("projectId")Long projectId);
}
