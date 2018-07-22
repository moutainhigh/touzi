package com.river.ms.project.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 项目信息列表
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("project_list")
public class ProjectList implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "entityId", type = IdType.AUTO)
	private Long entityId;
	/**
	 * 项目名称
	 */
	@NotNull(message = "项目名称不能为空!")
	private String entityTitle;
	/**
	 * 项目简称
	 */
	@NotNull(message = "项目简称不能为空!")
	private String entityAb;
	/**
	 * 项目代号
	 */
	private String entityMark;
	/**
	 * 项目编号
	 */
	private String entityCode;
	/**
	 * 投资类别
	 */
	@NotNull(message = "请选择项目投资类别!")
	private Long categoryId;
	/**
	 * 项目类型
	 */
	@NotNull(message = "请选择项目类型!")
	private Long categoryId2;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 项目描述
	 */
	private String entityDesc;
	/**
	 * 项目状态:
	 */
	private Integer state;

	/**
	 * 项目阶段
	 */
	private Long stageCode;
	/**
	 * 项目阶段code
	 */
	@TableField(exist = false)
	private String stageCode2;

	/**
	 * 项目阶段名称
	 */
	private String stageName;
	/**
	 * 项目来源 默认为0-从投资系统中录入
	 */
	private String src;
	/**
	 * 项目负责人
	 */
	private String leader;
	/**
	 * 项目负责人ITCODE
	 */
	@TableField("leader_itcode")
	private String leaderItcode;
	/**
	 * 项目发起单位编码
	 */
	@TableField("initiative_group")
	private String initiativeGroup;

	/**
	 * 项目发起单位名称
	 */
	private String initiativeGroupName;
	/**
	 * 项目所属行业公司 编码
	 */
	@TableField("owner_group")
	private String ownerGroup;

	/**
	 * 项目所属行业公司名称
	 */
	private String ownerGroupName;

	/**
	 * 投资主体
	 */
	@TableField("invest_subject")
	private String investSubject;
	/**
	 * 投资主体是否上市公司 0 - 是 ， 1 - 不是
	 */
	@TableField("invest_listed")
	private Integer investListed;
	/**
	 * 项目标的
	 */
	@TableField("invest_target")
	private String investTarget;
	/**
	 * 标的是否上市公司
	 */
	@TableField("target_listed")
	private Integer targetListed;
	/**
	 * 项目启动时间
	 */
	@TableField("start_date")
	private Date startDate;
	/**
	 * 联合投资方
	 */
	private String partner;
	/**
	 * 上市市场
	 */
	@TableField("securities_market")
	private String securitiesMarket;
	private Date createTime;
	/**
	 * 风险等级
	 */
	private Integer risk;
	/**
	 * 风控负责人Itcode
	 */
	@TableField("risk_itcode")
	private String riskItcode;
	/**
	 * 风控负责人ID
	 */
	private Long riskResId;
	/**
	 * 行业公司负责人
	 */
	@TableField("group_leader")
	private String groupLeader;
	/**
	 * 行业公司负责人ID
	 */
	private Long groupLeaderId;

	/**
	 * 延续项目，上期项目ID
	 */
	private Long parentProjectId;
	/**
	 * 上期项目名称
	 */
	private String parentProject;

	/**
	 * 所属行业
	 */
	private Integer industryId;

	/**
	 * 投资类别名称
	 */
	@TableField(exist = false)
	private String categoryTitle;
	/**
	 * 项目类型名称
	 */
	@TableField(exist = false)
	private String categoryTitle1;

	/**
	 * 流程实例ID
	 */
	private String liuchengshiliId;

	/**
	 * 项目简介
	 */
	private String intro;

	/**
	 * 新奥持股比例
	 */
	private BigDecimal f1;

	/**
	 * 币种
	 */
	private String s1;

	/**
	 * 汇率
	 */
	private BigDecimal f2;

	/**
	 * 项目估算总投资额
	 */
	private BigDecimal f3;

	/**
	 * 新奥权益投资额
	 */
	private BigDecimal f4;

	/**
	 * 其他方持股说明
	 */
	private String s2;

	private Long countryId;

	private Long provinceId;

	private Long cityId;

	private String industry;
	/**
	 * 综合评分
	 */
	private BigDecimal score;

	private String projectCategoryTitle1;

	private String projectCategoryTitle3;

	private String initiativeGroupArea;

	private Integer bImport;

	private Integer bImpState;

	/**
	 * 权益投资占比（%）
	 */
	private BigDecimal p1;

	// ************************************************************

	/**
	 * 项目IRR%-内部收益率
	 */
	@TableField(exist = false)
	private BigDecimal f6;
	/**
	 * 静态投资回收期
	 */
	@TableField(exist = false)
	private BigDecimal f7;
	/**
	 * 项目NPV
	 */
	@TableField(exist = false)
	private BigDecimal f9;
	/**
	 * 预计投资时间
	 */
	@TableField(exist = false)
	private Date f10;
	/**
	 * 项目实际投资总额
	 */
	@TableField(exist = false)
	private BigDecimal f15;
	/**
	 * 项目批复总投资额
	 */
	@TableField(exist = false)
	private BigDecimal f21;
	/**
	 * 项目计划总工期/期限（月）
	 */
	@TableField(exist = false)
	private Integer f22;

	@TableField(exist = false)
	private String type;

	/**
	 * 分期
	 */
	@TableField(exist = false)
	private String milestone;
	@TableField(exist = false)
	private BigDecimal deg1;

	@TableField(exist = false)
	private BigDecimal deg2;
	/**
	 * 成本偏差百分比
	 */
	@TableField(exist = false)
	private BigDecimal cost_degree;
	/**
	 * 进度偏差百分比
	 */
	@TableField(exist = false)
	private BigDecimal history_degree;
	@TableField(exist = false)
	private Integer cost_level;
	@TableField(exist = false)
	private Integer history_level;

	public BigDecimal getP1() {
		return p1;
	}

	public void setP1(BigDecimal p1) {
		this.p1 = p1;
	}

	public String getProjectCategoryTitle1() {
		return projectCategoryTitle1;
	}

	public void setProjectCategoryTitle1(String projectCategoryTitle1) {
		this.projectCategoryTitle1 = projectCategoryTitle1;
	}

	public String getProjectCategoryTitle3() {
		return projectCategoryTitle3;
	}

	public void setProjectCategoryTitle3(String projectCategoryTitle3) {
		this.projectCategoryTitle3 = projectCategoryTitle3;
	}

	public String getInitiativeGroupArea() {
		return initiativeGroupArea;
	}

	public void setInitiativeGroupArea(String initiativeGroupArea) {
		this.initiativeGroupArea = initiativeGroupArea;
	}

	public Integer getbImport() {
		return bImport;
	}

	public void setbImport(Integer bImport) {
		this.bImport = bImport;
	}

	public Integer getbImpState() {
		return bImpState;
	}

	public void setbImpState(Integer bImpState) {
		this.bImpState = bImpState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityAb() {
		return entityAb;
	}

	public void setEntityAb(String entityAb) {
		this.entityAb = entityAb;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryId2() {
		return categoryId2;
	}

	public void setCategoryId2(Long categoryId2) {
		this.categoryId2 = categoryId2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public String getInitiativeGroup() {
		return initiativeGroup;
	}

	public void setInitiativeGroup(String initiativeGroup) {
		this.initiativeGroup = initiativeGroup;
	}

	public String getOwnerGroup() {
		return ownerGroup;
	}

	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}

	public String getInvestSubject() {
		return investSubject;
	}

	public void setInvestSubject(String investSubject) {
		this.investSubject = investSubject;
	}

	public Integer getInvestListed() {
		return investListed;
	}

	public void setInvestListed(Integer investListed) {
		this.investListed = investListed;
	}

	public String getInvestTarget() {
		return investTarget;
	}

	public void setInvestTarget(String investTarget) {
		this.investTarget = investTarget;
	}

	public Integer getTargetListed() {
		return targetListed;
	}

	public void setTargetListed(Integer targetListed) {
		this.targetListed = targetListed;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSecuritiesMarket() {
		return securitiesMarket;
	}

	public void setSecuritiesMarket(String securitiesMarket) {
		this.securitiesMarket = securitiesMarket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}

	public String getRiskItcode() {
		return riskItcode;
	}

	public void setRiskItcode(String riskItcode) {
		this.riskItcode = riskItcode;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getOwnerGroupName() {
		return ownerGroupName;
	}

	public void setOwnerGroupName(String ownerGroupName) {
		this.ownerGroupName = ownerGroupName;
	}

	public Long getRiskResId() {
		return riskResId;
	}

	public void setRiskResId(Long riskResId) {
		this.riskResId = riskResId;
	}

	public Long getGroupLeaderId() {
		return groupLeaderId;
	}

	public void setGroupLeaderId(Long groupLeaderId) {
		this.groupLeaderId = groupLeaderId;
	}

	public String getInitiativeGroupName() {
		return initiativeGroupName;
	}

	public void setInitiativeGroupName(String initiativeGroupName) {
		this.initiativeGroupName = initiativeGroupName;
	}

	public Long getStageCode() {
		return stageCode;
	}

	public void setStageCode(Long stageCode) {
		this.stageCode = stageCode;
	}

	public Long getParentProjectId() {
		return parentProjectId;
	}

	public void setParentProjectId(Long parentProjectId) {
		this.parentProjectId = parentProjectId;
	}

	public String getParentProject() {
		return parentProject;
	}

	public void setParentProject(String parentProject) {
		this.parentProject = parentProject;
	}

	public String getEntityMark() {
		return entityMark;
	}

	public void setEntityMark(String entityMark) {
		this.entityMark = entityMark;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryTitle1() {
		return categoryTitle1;
	}

	public void setCategoryTitle1(String categoryTitle1) {
		this.categoryTitle1 = categoryTitle1;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getLiuchengshiliId() {
		return liuchengshiliId;
	}

	public void setLiuchengshiliId(String liuchengshiliId) {
		this.liuchengshiliId = liuchengshiliId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BigDecimal getF1() {
		return f1;
	}

	public void setF1(BigDecimal f1) {
		this.f1 = f1;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public BigDecimal getF2() {
		return f2;
	}

	public void setF2(BigDecimal f2) {
		this.f2 = f2;
	}

	public BigDecimal getF3() {
		return f3;
	}

	public void setF3(BigDecimal f3) {
		this.f3 = f3;
	}

	public BigDecimal getF4() {
		return f4;
	}

	public void setF4(BigDecimal f4) {
		this.f4 = f4;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getStageCode2() {
		return stageCode2;
	}

	public void setStageCode2(String stageCode2) {
		this.stageCode2 = stageCode2;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public BigDecimal getF6() {
		return f6;
	}

	public void setF6(BigDecimal f6) {
		this.f6 = f6;
	}

	public BigDecimal getF7() {
		return f7;
	}

	public void setF7(BigDecimal f7) {
		this.f7 = f7;
	}

	public BigDecimal getF9() {
		return f9;
	}

	public void setF9(BigDecimal f9) {
		this.f9 = f9;
	}

	public Date getF10() {
		return f10;
	}

	public void setF10(Date f10) {
		this.f10 = f10;
	}

	public BigDecimal getF15() {
		return f15;
	}

	public void setF15(BigDecimal f15) {
		this.f15 = f15;
	}

	public BigDecimal getF21() {
		return f21;
	}

	public void setF21(BigDecimal f21) {
		this.f21 = f21;
	}

	public Integer getF22() {
		return f22;
	}

	public void setF22(Integer f22) {
		this.f22 = f22;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public BigDecimal getDeg1() {
		return deg1;
	}

	public void setDeg1(BigDecimal deg1) {
		this.deg1 = deg1;
	}

	public BigDecimal getDeg2() {
		return deg2;
	}

	public void setDeg2(BigDecimal deg2) {
		this.deg2 = deg2;
	}

	public BigDecimal getCost_degree() {
		return cost_degree;
	}

	public void setCost_degree(BigDecimal cost_degree) {
		this.cost_degree = cost_degree;
	}

	public BigDecimal getHistory_degree() {
		return history_degree;
	}

	public void setHistory_degree(BigDecimal history_degree) {
		this.history_degree = history_degree;
	}

	public Integer getCost_level() {
		return cost_level;
	}

	public void setCost_level(Integer cost_level) {
		this.cost_level = cost_level;
	}

	public Integer getHistory_level() {
		return history_level;
	}

	public void setHistory_level(Integer history_level) {
		this.history_level = history_level;
	}

	@Override
	public String toString() {
		return "ProjectList [entityId=" + entityId + ", entityTitle=" + entityTitle + ", entityAb=" + entityAb
				+ ", entityMark=" + entityMark + ", entityCode=" + entityCode + ", categoryId=" + categoryId
				+ ", categoryId2=" + categoryId2 + ", country=" + country + ", province=" + province + ", city=" + city
				+ ", entityDesc=" + entityDesc + ", state=" + state + ", stageCode=" + stageCode + ", src=" + src
				+ ", leader=" + leader + ", leaderItcode=" + leaderItcode + ", initiativeGroup=" + initiativeGroup
				+ ", initiativeGroupName=" + initiativeGroupName + ", ownerGroup=" + ownerGroup + ", ownerGroupName="
				+ ownerGroupName + ", investSubject=" + investSubject + ", investListed=" + investListed
				+ ", investTarget=" + investTarget + ", targetListed=" + targetListed + ", startDate=" + startDate
				+ ", partner=" + partner + ", securitiesMarket=" + securitiesMarket + ", createTime=" + createTime
				+ ", risk=" + risk + ", riskItcode=" + riskItcode + ", riskResId=" + riskResId + ", groupLeader="
				+ groupLeader + ", groupLeaderId=" + groupLeaderId + ", parentProjectId=" + parentProjectId
				+ ", parentProject=" + parentProject + ", industryId=" + industryId + ", categoryTitle=" + categoryTitle
				+ ", categoryTitle1=" + categoryTitle1 + "]";
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

}
