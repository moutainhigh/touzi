package com.river.ms.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.bbs.entity.ForumEntity;
import com.river.ms.bbs.mapper.IForumMapper;
/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ForumService extends ServiceTreeBase<IForumMapper, ForumEntity> {
	@Autowired
	IForumMapper mapper;
	@Override
	public IForumMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

	/**
	 * 判断该论坛下是否存在主题
	 * @param entityId
	 * @return
	 */
	/*public Boolean isExistTopic(long entityId) {
		int existTopic = mapper.countTopic(entityId);
		if (existTopic == 0) {
			return false;
		} else {
			return true;
		}
	}*/
	
}
