package com.river.ms.bbs.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 
 * @author zhouheng 主题列表
 */
@RiverTable(Name = "bbs_topic")
public class TopicEntity extends EntityBase {
	@RiverColumn
	@NotNull
	public Long forumId;

	public ForumEntity forum;

	@RiverColumn
	@NotNull
	public Long userId;

	@RiverColumn
	public String userName;

	/**
	 * 主题类型：0 回复式，1 一问一答式
	 */
	@RiverColumn
	public int topicType;

	public String keyWords;

	public ContentEntity contentEntity;

	public List<ReplyEntity> replyEntity;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public ForumEntity getForum() {
		return forum;
	}

	public void setForum(ForumEntity forum) {
		this.forum = forum;
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

	public List<ReplyEntity> getReplyEntity() {
		return replyEntity;
	}

	public void setReplyEntity(List<ReplyEntity> replyEntity) {
		this.replyEntity = replyEntity;
	}

}
