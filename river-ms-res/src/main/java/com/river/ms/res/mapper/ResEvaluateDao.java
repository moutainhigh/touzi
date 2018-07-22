package com.river.ms.res.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResEvaluate;

/**
 * <p>
 * 资源评价 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResEvaluateDao extends BaseMapper<ResEvaluate> {

	List<ResEvaluate> getResEvaluate(@Param("entityId")Long entityId, @Param("bool")Boolean bool);

	List<ResEvaluate> getResReceiveEvaluateAndTagHistory(@Param("entityId")Long entityId);

	List<ResEvaluate> getResSendEvaluateAndTagHistory(@Param("entityId")Long entityId);
}
