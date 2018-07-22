package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.EmployeeEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IEmployeeMapper extends IDAOBase<EmployeeEntity> {
	List<EmployeeEntity> search(@Param("keyWords") String keyWords, @Param("entityCode") String entityCode,
			@Param("idCard") String idCard, @Param("mobile") String mobile, @Param("email") String email,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	List<EmployeeEntity> getGuidByItCodes(@Param("itCodes") List<String> itCodes);

	List<EmployeeEntity> getEmp(@Param("organizationId") Long organizationId, @Param("stationId") Long stationId,
			@Param("isExit") boolean isExit, @Param("keyword") String keyword);

	List<EmployeeEntity> getEmployeeEntity(@Param("keyword")String keyword);
}