package com.river.ms.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.bbs.entity.TopicEntity;
/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface ITopicMapper extends IDAOBase<TopicEntity> {

	/**
	 * 查询主題下存在的回复的个数
	 * @param entityId
	 * @return
	 */
    /*int countReply(@Param("entityId")long entityId);*/
	
	List<TopicEntity> queryByTopic(@Param("entity")TopicEntity entity);
	
	List<TopicEntity> getByIds(@Param("ids") List<Long> ids);
}
