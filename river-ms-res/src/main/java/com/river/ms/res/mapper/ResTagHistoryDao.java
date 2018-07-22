package com.river.ms.res.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.res.entity.ResTagHistory;

/**
 * <p>
 * 资源评价标签 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ResTagHistoryDao extends BaseMapper<ResTagHistory> {

	List<ResTagHistory> getResTagHistory(@Param("entityId")Long entityId, @Param("bool")Boolean bool);

}
