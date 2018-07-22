package com.river.ms.res.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResTagHistory;

/**
 * <p>
 * 资源评价标签 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResTagHistoryService extends IService<ResTagHistory> {

	/**
	 * 插入资源评价
	 * @param entity
	 * @return
	 */
	boolean insertResTagHistory(ResTagHistory entity);

	List<ResTagHistory> getResTagHistory(Long entityId, boolean bool);
	
}
