package com.river.ms.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.project.entity.ProjectTag;

/**
 * <p>
 * 项目标签（是否考虑不同角色不同标签） Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectTagDao extends BaseMapper<ProjectTag> {

}
