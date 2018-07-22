package com.river.ms.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.bbs.entity.ReplyEntity;
import com.river.ms.bbs.mapper.IReplyMapper;
/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ReplyService extends ServiceTreeBase<IReplyMapper, ReplyEntity> {
	@Autowired
	IReplyMapper mapper;
	@Override
	public IReplyMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	public List<ReplyEntity> queryReply(ReplyEntity entity) {
		return mapper.queryReply(entity);
	}
	
}
