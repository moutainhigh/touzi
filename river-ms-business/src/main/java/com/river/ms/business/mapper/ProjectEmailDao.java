package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectEmail;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2018-01-26
 */
@Mapper
public interface ProjectEmailDao extends BaseMapper<ProjectEmail> {

	List<ProjectEmail> selectProjectEmail();

}
