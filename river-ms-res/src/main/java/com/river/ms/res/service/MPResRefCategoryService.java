package com.river.ms.res.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefCategory;

/**
 * <p>
 * 资源对应分类 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResRefCategoryService extends IService<ResRefCategory> {

	Boolean MyInsertBatch(List<ResList> resList, List<ResCategory> cateList);
	
	List<ResRefCategory> selectRes(Long categoryId);
}
