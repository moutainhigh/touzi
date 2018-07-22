package com.river.ms.res.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResTag;

/**
 * <p>
 * 资源标签 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResTagDao extends BaseMapper<ResTag> {
	Long exist(@Param("id") Long id,@Param("code") String code);
}
