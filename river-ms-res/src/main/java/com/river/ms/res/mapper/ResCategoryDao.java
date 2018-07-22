package com.river.ms.res.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResCategory;

/**
 * <p>
 * 资源分类 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResCategoryDao extends BaseMapper<ResCategory> {
	List<ResCategory> getResRoleByitcode(@Param("itcode") String itcode);
	Long exist(@Param("id") Long id,@Param("code") String code);
}
