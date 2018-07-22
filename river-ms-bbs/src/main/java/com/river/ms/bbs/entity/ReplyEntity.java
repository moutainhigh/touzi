package com.river.ms.bbs.entity;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;

/**
 * 
 * @author zhouheng 回复
 */
@RiverTable(Name = "bbs_reply")
public class ReplyEntity extends TreeEntityBase {
	@RiverColumn
	@NotNull
	public long topicId;

	@RiverColumn
	@NotNull
	public long userId;

	@RiverColumn
	public String userName;

	public ContentEntity contentEntity;

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

	public ContentEntity getContentEntity() {
		return contentEntity;
	}

	public void setContentEntity(ContentEntity contentEntity) {
		this.contentEntity = contentEntity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
