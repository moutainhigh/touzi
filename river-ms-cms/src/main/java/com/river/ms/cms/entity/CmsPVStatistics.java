package com.river.ms.cms.entity;

import java.sql.Timestamp;

/**
 * 文章PV统计表
 * @author my
 *
 */
public class CmsPVStatistics {

	private Long entityId;

	private Timestamp createTime;

	private String ipAddr;

	private long articleId;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

}
