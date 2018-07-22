package com.river.ms.system.webService.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Organization {

	@XmlTransient
	private Long entityId;

	@XmlElement
	private String orgeh;// 组织编号(主键）

	@XmlElement
	private String descr;// 组织描述

	@XmlElement
	private String pcjdm;// portal层级代码

	@XmlElement
	private String pcjmc;// portal层级全称

	@XmlElement
	private String sobidcj;// 所属层级的编码

	@XmlElement
	private String stext;// 组织名称

	@XmlElement
	private String up_orgeh;// 上级组织编号

	@XmlElement
	private String PRIOX;// 优先级

	@XmlElement
	private String updatestatus;// 更新状态，1：正常，2：停用

	@XmlElement
	private String createdate;// 创建日期

	@XmlElement
	private int operaction;// 操作类型，1：新增，2：更新，3：删除

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getOrgeh() {
		return orgeh;
	}

	public void setOrgeh(String orgeh) {
		this.orgeh = orgeh;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPcjdm() {
		return pcjdm;
	}

	public void setPcjdm(String pcjdm) {
		this.pcjdm = pcjdm;
	}

	public String getPcjmc() {
		return pcjmc;
	}

	public void setPcjmc(String pcjmc) {
		this.pcjmc = pcjmc;
	}

	public String getSobidcj() {
		return sobidcj;
	}

	public void setSobidcj(String sobidcj) {
		this.sobidcj = sobidcj;
	}

	public String getStext() {
		return stext;
	}

	public void setStext(String stext) {
		this.stext = stext;
	}

	public String getUp_orgeh() {
		return up_orgeh;
	}

	public void setUp_orgeh(String up_orgeh) {
		this.up_orgeh = up_orgeh;
	}

	public String getPRIOX() {
		return PRIOX;
	}

	public void setPRIOX(String pRIOX) {
		PRIOX = pRIOX;
	}

	public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public int getOperaction() {
		return operaction;
	}

	public void setOperaction(int operaction) {
		this.operaction = operaction;
	}

	public Organization(Long entityId, String orgeh, String descr, String pcjdm, String pcjmc, String sobidcj,
			String stext, String up_orgeh, String pRIOX, String updatestatus, String createdate, int operaction) {
		super();
		this.entityId = entityId;
		this.orgeh = orgeh;
		this.descr = descr;
		this.pcjdm = pcjdm;
		this.pcjmc = pcjmc;
		this.sobidcj = sobidcj;
		this.stext = stext;
		this.up_orgeh = up_orgeh;
		PRIOX = pRIOX;
		this.updatestatus = updatestatus;
		this.createdate = createdate;
		this.operaction = operaction;
	}

	public Organization() {
		super();
	}

	@Override
	public String toString() {
		return "UserOrganization [entityId=" + entityId + ", orgeh=" + orgeh + ", descr=" + descr + ", pcjdm=" + pcjdm
				+ ", pcjmc=" + pcjmc + ", sobidcj=" + sobidcj + ", stext=" + stext + ", up_orgeh=" + up_orgeh
				+ ", PRIOX=" + PRIOX + ", updatestatus=" + updatestatus + ", createdate=" + createdate + ", operaction="
				+ operaction + "]";
	}

}
