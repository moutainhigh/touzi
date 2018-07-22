package com.river.ms.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目实施评价记录
 * </p>
 *
 * @author zyb
 * @since 2017-12-21
 */
@TableName("project_evaluate")
public class ProjectEvaluate implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 实施评价开始日期
	 */
	private Date startTime;
	/**
	 * 实施评价结束日期
	 */
	private Date endTime;
	/**
	 * 主要问题事项
	 */
	private String content;
	/**
	 * 是否抄送领导
	 */
	private Integer copy;
	private Long projectId;
	/**
	 * 操作用户ITCODE
	 */
	private String itcode;
	/**
	 * 操作用户资源ID
	 */
	private Long resourceId;
	// private Long
	/**
	 * 领导姓名
	 */
	private String leader;
	/**
	 * 领导ITCODE
	 */
	@TableField("leader_itcode")
	private String leaderItcode;
	/**
	 * 领导批示
	 */
	@TableField("leader_view")
	private String leaderView;
	private Date createTime;

	private String PROCESS_INSTANCE_ID_;
	private int state;

	/**
	 * 0-预警纠偏1-投资价值评估
	 */
	private Integer entityType;

	@TableField(exist = false)
	private List<HistoryResult> historyResult;
	
	@TableField(exist = false)
	private Integer processStage = 0;

	public Integer getProcessStage() {
		return processStage;
	}

	public void setProcessStage(Integer processStage) {
		this.processStage = processStage;
	}

	public List<HistoryResult> getHistoryResult() {
		return historyResult;
	}

	public void setHistoryResult(List<HistoryResult> historyResult) {
		this.historyResult = historyResult;
		if(historyResult != null) {
			this.setProcessStage(0);
			for(HistoryResult h : historyResult) {
				Date endTime = h.getEndTime();
				if(endTime == null) {
					h.setHistoryResultType(0);
				}else {
					h.setHistoryResultType(1);
				}
			}
			for(HistoryResult h : historyResult) {
				if (h.getTaskType().equals("startEvent")) {
					this.setProcessStage(1);
					break;
				}
			}
			for(HistoryResult h : historyResult) {
				if (h.getTaskType().equals("endEvent")) {
					this.setProcessStage(2);
					break;
				}
			}
		}
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

	public void setProjectId(Long entityId) {
		this.projectId = entityId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public String getItcode() {
		return itcode;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCopy() {
		return copy;
	}

	public void setCopy(Integer copy) {
		this.copy = copy;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeaderItcode() {
		return leaderItcode;
	}

	public void setLeaderItcode(String leaderItcode) {
		this.leaderItcode = leaderItcode;
	}

	public String getLeaderView() {
		return leaderView;
	}

	public void setLeaderView(String leaderView) {
		this.leaderView = leaderView;
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

	public String getPROCESS_INSTANCE_ID_() {
		return PROCESS_INSTANCE_ID_;
	}

	public void setPROCESS_INSTANCE_ID_(String pROCESS_INSTANCE_ID_) {
		PROCESS_INSTANCE_ID_ = pROCESS_INSTANCE_ID_;
	}

	@Override
	public String toString() {
		return "ProjectEvaluate{" + ", entityId=" + entityId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", content=" + content + ", copy=" + copy + ", leader=" + leader + ", leaderItcode=" + leaderItcode
				+ ", leaderView=" + leaderView + ", createTime=" + createTime + ", entityType=" + entityType + "}";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
