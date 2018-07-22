package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.ThirdGroupEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IThirdGroupMapper extends IDAOBase<ThirdGroupEntity> {
	List<ThirdGroupEntity> search(@Param("keyWords") String keyWords,@Param("entityCode") String entityCode,@Param("contact") String contact,@Param("mobile") String mobile,@Param("email") String email, @Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
	List<ThirdGroupEntity> selectByGroupType(@Param("type") Integer type);
}