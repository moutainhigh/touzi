package com.river.ms.res.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资源对应标签
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@TableName("res_ref_tag")
public class ResRefTag implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="entityId", type= IdType.AUTO)
	private Long entityId;
    /**
     * 标签
     */
	private String tag;
    /**
     * 0-业务标签1-技能标签
     */
	private Integer tagType;
    /**
     * ITCODE
     */
	private String itcode;
    /**
     * 资源ID
     */
	private Long resourceId;
	private Date createTime;
    /**
     * 计数
     */
	private Integer num;


	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getTagType() {
		return tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "ResRefTag{" +
			", entityId=" + entityId +
			", tag=" + tag +
			", tagType=" + tagType +
			", itcode=" + itcode +
			", resourceId=" + resourceId +
			", createTime=" + createTime +
			", num=" + num +
			"}";
	}
}
