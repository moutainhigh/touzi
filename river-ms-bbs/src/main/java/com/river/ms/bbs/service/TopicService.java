package com.river.ms.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.bbs.entity.ContentEntity;
import com.river.ms.bbs.entity.ForumEntity;
import com.river.ms.bbs.entity.TopicEntity;
import com.river.ms.bbs.mapper.IContentMapper;
import com.river.ms.bbs.mapper.IForumMapper;
import com.river.ms.bbs.mapper.ITopicMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class TopicService extends ServiceBase<ITopicMapper, TopicEntity> {
	@Autowired
	ITopicMapper mapper;

	@Autowired
	IContentMapper contentMapper;
	
	@Autowired
	IForumMapper forumMapper;

	@Override
	public ITopicMapper getDao() {
		return mapper;
	}

	public List<TopicEntity> queryTopic(TopicEntity entity) {
		return mapper.queryByTopic(entity);
	}

	@Override
	public TopicEntity getById(long entityId) {
		TopicEntity topic = super.getById(entityId);
		ContentEntity queryBy = contentMapper.getByTopicIdAndReplyId(entityId, 0);
		topic.setContentEntity(queryBy);
		ForumEntity byId = forumMapper.getById(topic.getForumId());
		topic.setForum(byId);
		return topic;
	}

	/**
	 * 
	 * @param entityId
	 * @return
	 */
	/*
	 * public boolean isExistReply(long entityId) { // TODO Auto-generated method
	 * stub int countReply = mapper.countReply(entityId); if (countReply == 0) {
	 * return false; } else { return true; } }
	 */

	public List<TopicEntity> getByIds(List<Long> ids){
		List<TopicEntity> byIds = this.mapper.getByIds(ids);
		return byIds;
	}
}
