package com.river.ms.cms.entity;

public class DFSEntity {

	private Long id;

	private String entityTitle;

	private Long dfsId;

	private Long articleId;

	private String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public Long getDfsId() {
		return dfsId;
	}

	public void setDfsId(Long dfsId) {
		this.dfsId = dfsId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public DFSEntity(Long id, String entityTitle, Long dfsId) {
		super();
		this.id = id;
		this.entityTitle = entityTitle;
		this.dfsId = dfsId;
	}

	public DFSEntity() {
		super();
	}

}
