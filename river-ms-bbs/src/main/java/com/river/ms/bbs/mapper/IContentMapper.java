package com.river.ms.bbs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.bbs.entity.ContentEntity;

/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface IContentMapper extends IDAOBase<ContentEntity> {

	ContentEntity getByTopicIdAndReplyId(@Param("topicId") long topicId, @Param("replyId") long replyId);

	ContentEntity getByTopicIdAndReplyId2(@Param("topicId") long topicId);
}
