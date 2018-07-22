package com.river.ms.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.ms.business.entity.HistoryResult;

/**
 * <p>
 * 项目信息扩展表（数据） Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectListDao {
	
	/**
	 * 获取用户姓名
	 * @param itcode
	 * @return
	 */
	List<HistoryResult> getResName(@Param("itcodes")List<String> itcode);
	
	/**
	 * 获取节点ID
	 * @param stageCode
	 * @return
	 */
	List<HistoryResult> getStageId(@Param("stageCodes")List<String> stageCode);
	
	/**
	 * 获取文件数量
	 * @param stageIds
	 * @return
	 */
	List<HistoryResult> getStageDocNum(@Param("stageIds")List<Long> stageIds,@Param("projectId") Long projectId);
	
	/**
	 * 获取文件数量
	 * @param stageIds
	 * @return
	 */
	List<HistoryResult> getBigStageDocNum(@Param("processInstanceId")String processInstanceId,@Param("projectId") Long projectId);
}
