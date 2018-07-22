package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectHistory;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
/**
 * <p>
 * 项目预测数据/历史数据 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectHistoryDao extends BaseMapper<ProjectHistory> {
	List<ProjectHistory> getProjectHistory(@Param("projectId") Long projectId,@Param("entityType") Integer entityType);
}
