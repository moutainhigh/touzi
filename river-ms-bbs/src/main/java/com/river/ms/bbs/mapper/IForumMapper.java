package com.river.ms.bbs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.bbs.entity.ForumEntity;
/**
 * 
 * @author zhouheng
 *
 */
@Mapper
public interface IForumMapper extends IDAOBase<ForumEntity> {
	ForumEntity getParentE(@Param("parentId")long parentId);
	
	/**
	 * 查询论坛下存在的主题个数
	 * @param entityId
	 * @return
	 */
    //int countTopic(@Param("entityId")long entityId);
}
