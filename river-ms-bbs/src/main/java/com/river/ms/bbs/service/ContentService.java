package com.river.ms.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.bbs.entity.ContentEntity;
import com.river.ms.bbs.mapper.IContentMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ContentService extends ServiceBase<IContentMapper, ContentEntity> {
	@Autowired
	IContentMapper mapper;

	@Override
	public IContentMapper getDao() {
		return mapper;
	}

	public ContentEntity getByTopicIdAndReplyId(long topicId, long replyId) {
		return mapper.getByTopicIdAndReplyId(topicId, replyId);
	}

}
