package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目文档
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_document")
public class ProjectDocument implements Serializable {

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
	 * 显示名称
	 */
	@TableField("display_name")
	private String displayName;
	/**
	 * 文档类型
	 */
	@TableField("doc_type")
	private String docType;
	/**
	 * 关联文档ID
	 */
	@TableField("document_id")
	private Long documentId;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	/**
	 * 上传资源的人员姓名
	 */
	private String resName;
	private Date createTime;
	/**
	 * 大阶段
	 */
	private Long greatStage;

	/**
	 * 关联对象ID:前置条件ID
	 */
	private Integer ref_id;
	/**
	 * 是否是重要归档文件
	 */
	private Integer type;

	private Long stageDocId;

	@TableField(exist = false)
	private String stageTitle;
	@TableField(exist = false)
	private String greatStageTitle;
	/**
	 * 
	 */
	@TableField(exist = false)
	private String documentType;
	

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Long getStageDocId() {
		return stageDocId;
	}

	public void setStageDocId(Long stageDocId) {
		this.stageDocId = stageDocId;
	}

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
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

	public Long getGreatStage() {
		return greatStage;
	}

	public void setGreatStage(Long greatStage) {
		this.greatStage = greatStage;
	}

	public Integer getRef_id() {
		return ref_id;
	}

	public void setRef_id(Integer ref_id) {
		this.ref_id = ref_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * 流程实例ID
	 */
	private String PROCESS_INSTANCE_ID_;

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	public ProjectDocument() {
		super();
	}

	public ProjectDocument(Long entityId, Long projectId, Long stage, String displayName, String docType,
			Long documentId, String itcode, Long resourceId, Date createTime, Long greatStage, Integer ref_id,
			Integer type) {
		super();
		this.entityId = entityId;
		this.projectId = projectId;
		this.stage = stage;
		this.displayName = displayName;
		this.docType = docType;
		this.documentId = documentId;
		this.itcode = itcode;
		this.resourceId = resourceId;
		this.createTime = createTime;
		this.greatStage = greatStage;
		this.ref_id = ref_id;
		this.type = type;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	public String getGreatStageTitle() {
		return greatStageTitle;
	}

	public void setGreatStageTitle(String greatStageTitle) {
		this.greatStageTitle = greatStageTitle;
	}

}
