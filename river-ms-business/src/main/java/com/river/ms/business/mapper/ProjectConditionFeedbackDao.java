package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectConditionFeedback;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 前置条件落实反馈 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectConditionFeedbackDao extends BaseMapper<ProjectConditionFeedback> {
	List<ProjectConditionFeedback> getConditionFeedbackById(@Param("projectId")Integer projectId,@Param("type")Integer type);
	List<ProjectConditionFeedback> getConditionFeedbackByProjectId(@Param("projectId")Integer projectId,@Param("type")Integer type);

}
