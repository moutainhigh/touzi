package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectFlow;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 流程定义表 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Mapper
public interface ProjectFlowDao extends BaseMapper<ProjectFlow> {

	Long isExist(@Param("entityId") Long entityId,@Param("entityCode") String entityCode);

}
