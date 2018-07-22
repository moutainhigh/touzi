package com.river.ms.project.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目编码规则
 * </p>
 *
 * @author zyb
 * @since 2017-12-28
 */
@TableName("project_code")
public class ProjectCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新奥
     */
	private String c1;
    /**
     * 板块编码
     */
	private String c2;
    /**
     * 年份
     */
	private String c3;
    /**
     * 当前顺序号，三位
     */
	private Integer c4;


	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public Integer getC4() {
		return c4;
	}

	public void setC4(Integer c4) {
		this.c4 = c4;
	}

	@Override
	public String toString() {
		return "ProjectCode{" +
			", c1=" + c1 +
			", c2=" + c2 +
			", c3=" + c3 +
			", c4=" + c4 +
			"}";
	}
}
