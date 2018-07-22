package com.river.ms.res.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResRefTag;

/**
 * <p>
 * 资源对应标签 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResRefTagDao extends BaseMapper<ResRefTag> {

	List<ResRefTag> selectByResId(@Param("resourceId") Long resourceId);
}
