package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目讨论区
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_bbs")
public class ProjectBbs implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 项目阶段
	 */
	private Long stage;
	/**
	 * 板块CODE
	 */
	private String forumCode;
	/**
	 * 讨论主题：根据项目和项目阶段自动生成一个主题
	 */
	private Long topicId;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	private Date createTime;

	/**
	 * 问答名称
	 */
	private String topicTitle;

	/**
	 * 是否已经回复，0-未回复，1-已回复
	 */
	private Integer isReply;

	@TableField(exist = false)
	private String resName;
	@TableField(exist = false)
	private String projectTitle;
	/**
	 * 问答内容
	 */
	@TableField(exist = false)
	private String content;
	/**
	 * 回复内容
	 */
	@TableField(exist = false)
	private String reply;
	
	@TableField(exist = false)
	private String replyUser;
	@TableField(exist = false)
	private Date replyTime;
	
	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}

	public String getForumCode() {
		return forumCode;
	}

	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public Integer getIsReply() {
		return isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}
	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	@Override
	public String toString() {
		return "ProjectBbs{" + ", entityId=" + entityId + ", projectId=" + projectId + ", stage=" + stage
				+ ", forumCode=" + forumCode + ", topicId=" + topicId + ", itcode=" + itcode + ", resourceId="
				+ resourceId + ", createTime=" + createTime + "}";
	}
}
