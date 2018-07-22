package com.river.ms.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.project.entity.ProjectCategory;

/**
 * <p>
 * 项目分类 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectCategoryDao extends BaseMapper<ProjectCategory> {

	Long exist(@Param("id") Long id,@Param("code") String code);

}
