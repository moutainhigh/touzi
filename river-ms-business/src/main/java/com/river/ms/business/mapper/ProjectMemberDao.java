package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectMember;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目组成员 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectMemberDao extends BaseMapper<ProjectMember> {

}
