package com.river.ms.bbs.entity;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 
 * @author zhouheng 点赞
 */
@RiverTable(Name = "bbs_thumbup")
public class ThumbupEntity extends EntityBase {
	
	@RiverColumn
	@NotNull
	public long topicId;
	
	@RiverColumn
	@NotNull
	public long replyId;
	
	@RiverColumn
	@NotNull
	public long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public long getReplyId() {
		return replyId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

}
