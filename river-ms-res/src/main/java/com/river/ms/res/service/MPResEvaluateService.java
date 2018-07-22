package com.river.ms.res.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResEvaluate;

/**
 * <p>
 * 资源评价 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResEvaluateService extends IService<ResEvaluate> {

	boolean insertResEvaluateAndTag(HttpServletRequest request, String itcode, Long resourceId, Long projectId,
			List<String> tags, String content, BigDecimal score);

	List<ResEvaluate> getResEvaluate(Long entityId, Boolean bool);
	
	List<ResEvaluate> getResReceiveEvaluateAndTagHistory(Long entityId);

	List<ResEvaluate> getResSendEvaluateAndTagHistory(Long entityId);
}
