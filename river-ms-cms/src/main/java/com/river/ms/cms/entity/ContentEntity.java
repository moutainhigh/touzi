package com.river.ms.cms.entity;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 
 * @author zhouheng 资源内容信息表
 */
@RiverTable(Name="cms_content")
public class ContentEntity extends EntityBase {

	/**
	 * 资源ID
	 */
	@RiverColumn
	@NotNull
	public long articleId;

	/**
	 * 资源内容
	 */
	@RiverColumn
	public String content;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
