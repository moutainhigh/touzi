package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectPartner;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 合伙方案 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2018-01-21
 */
@Mapper
public interface ProjectPartnerDao extends BaseMapper<ProjectPartner> {

	ProjectPartner selectProjectPartner(@Param("projectId")Long projectId);

}
