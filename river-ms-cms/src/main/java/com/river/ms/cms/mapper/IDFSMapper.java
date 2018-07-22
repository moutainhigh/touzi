package com.river.ms.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.ms.cms.entity.DFSEntity;

/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface IDFSMapper {
	List<DFSEntity> selectByArticleId(@Param("articleId") Long articleId);
	
	void insertBitch(@Param("dfsEntitys") List<DFSEntity> parseArray);
}
