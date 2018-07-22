package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目业务操作日志
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_business_history")
public class ProjectBusinessHistory implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 阶段说明
     */
	private Long stage;
    /**
     * 操作用户
     */
	private String itcode;
    /**
     * 资源ID
     */
	private Long resourceId;
    /**
     * 处理状态：0-等待处理1-处理完毕
     */
	private Integer state;
    /**
     * 操作说明
     */
	private String memo;
    /**
     * 操作时间
     */
	private Date createTime;


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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	@Override
	public String toString() {
		return "ProjectBusinessHistory{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", stage=" + stage +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", state=" + state +
			", memo=" + memo +
			", createTime=" + createTime +
			"}";
	}
}
