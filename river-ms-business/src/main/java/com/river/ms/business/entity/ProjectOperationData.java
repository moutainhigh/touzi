package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目经营数据偏差分析
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_operation_data")
public class ProjectOperationData implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 项目ID
     */
	private Long projectId;
    /**
     * 计划目标
     */
	private String goal;
    /**
     * 偏差原因分析
     */
	private String deviation;
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

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProjectOperationData{" +
			", entityId=" + entityId +
			", projectId=" + projectId +
			", goal=" + goal +
			", deviation=" + deviation +
			", createTime=" + createTime +
			"}";
	}
}
