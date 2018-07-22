package com.river.ms.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.bbs.entity.ReplyEntity;
/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface IReplyMapper extends IDAOBase<ReplyEntity> {
	
	ReplyEntity getParentE(@Param("parentId") long parentId);
	
	List<ReplyEntity> queryReply(@Param("entity")ReplyEntity entity);

	List<ReplyEntity> getReply(@Param("topicId")Long topicId);
}
