package com.river.ms.bbs.entity;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
/**
 * 
 * @author zhouheng
 *内容
 */
@RiverTable(Name = "bbs_content")
public class ContentEntity extends TreeEntityBase {
	@RiverColumn
	@NotNull
	public long topicId;
	@RiverColumn
	@NotNull
	public long replyId;
	@RiverColumn
	public String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
