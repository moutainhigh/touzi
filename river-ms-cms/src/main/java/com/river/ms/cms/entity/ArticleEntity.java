package com.river.ms.cms.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.DataOutputColumn;
import com.river.core.annotation.DataOutputEntity;
import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
import com.river.core.helper.DataOutputColumnOrder;

/**
 * 
 * @author zhouheng 资源详细信息表
 */
@DataOutputEntity(titleName = "资源详细信息表", isNum = true)
@RiverTable(Name = "cms_article")
public class ArticleEntity extends EntityBase {

	/**
	 * 关键字
	 */
	@RiverColumn
	@Length(min = 0, max = 100, message = "keywords长度不能超过100")
	public String keywords;

	/**
	 * 添加时间
	 */
	@RiverColumn
	public Timestamp displayTime;

	/**
	 * 作者
	 */
	@RiverColumn
	@Length(min = 0, max = 100, message = "author长度不能超过100")
	public String author;

	/**
	 * 文章主分类ID
	 */
	@RiverColumn
	public long categoryId;

	/**
	 * 文章主分类名称
	 */
	public String categoryTitle;

	/**
	 * 文章的关联分类
	 */
	public List<CategoryEntity> categoryList;

	/**
	 * 文章內容
	 */
	public String content;
	
	public ContentEntity contentEntity;
	
	public Long pvCount;
	
	public Long ipCount;
	
	public List<DFSEntity> dfsList;

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Timestamp getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(Timestamp displayTime) {
		this.displayTime = displayTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public List<CategoryEntity> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryEntity> categoryList) {
		this.categoryList = categoryList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ContentEntity getContentEntity() {
		return contentEntity;
	}

	public void setContentEntity(ContentEntity contentEntity) {
		this.contentEntity = contentEntity;
	}

	public Long getPvCount() {
		return pvCount;
	}

	public void setPvCount(Long pvCount) {
		this.pvCount = pvCount;
	}

	public Long getIpCount() {
		return ipCount;
	}

	public void setIpCount(Long ipCount) {
		this.ipCount = ipCount;
	}
	
}
