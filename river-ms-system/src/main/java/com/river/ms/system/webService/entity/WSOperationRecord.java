package com.river.ms.system.webService.entity;

/**
 * webservice操作记录表
 * 
 * @author my
 *
 */
public class WSOperationRecord {

	/**
	 * id
	 */
	private Long entityId;

	/**
	 * 操作的表名
	 */
	private String tableName;

	/**
	 * 对应的数据ID
	 */
	private Long dataId;

	public WSOperationRecord() {
		super();
	}

	public WSOperationRecord(Long entityId, String tableName, Long dataId) {
		super();
		this.entityId = entityId;
		this.tableName = tableName;
		this.dataId = dataId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	@Override
	public String toString() {
		return "WSOperationRecord [entityId=" + entityId + ", tableName=" + tableName + ", dataId=" + dataId + "]";
	}

}
