package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 可配置参数
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_indicator")
public class ProjectIndicator implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 参数名称
	 */
	private String indicator;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 参数说明
	 */
	private String memo;
	private Date createTime;
	/**
	 * 0-经营数据1-评价指标
	 */
	private Integer entityType;

	/**
	 * 默认0,是否展现在项目登记，1展现在立项阶段
	 */
	private Integer keyType;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	@Override
	public String toString() {
		return "ProjectIndicator{" + ", entityId=" + entityId + ", indicator=" + indicator + ", unit=" + unit
				+ ", memo=" + memo + ", createTime=" + createTime + ", entityType=" + entityType + "}";
	}
}
