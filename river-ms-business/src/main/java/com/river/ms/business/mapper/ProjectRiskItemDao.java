package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectRiskItem;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 风险项 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Mapper
public interface ProjectRiskItemDao extends BaseMapper<ProjectRiskItem> {

	List<ProjectRiskItem> getProjectRiskItemByRiskId(@Param(value = "riskId") Long riskId);
}
