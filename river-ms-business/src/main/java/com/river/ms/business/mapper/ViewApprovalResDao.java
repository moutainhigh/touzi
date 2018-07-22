package com.river.ms.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.business.entity.ViewApprovalRes;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@Mapper
public interface ViewApprovalResDao extends BaseMapper<ViewApprovalRes> {
	List<ViewApprovalRes> getProjectApproval(@Param("projectId") Long projectId, @Param("type") Integer type,@Param("stage")Long stage);

	List<ViewApprovalRes> selectApproval(@Param("projectId") Long projectId, @Param("stageIds") List<Long> stageIds,
			@Param("type") Integer type,@Param("processInstanceId")String processInstanceId,@Param("stage")Long stage,@Param("isAll")Boolean isAll);
}
