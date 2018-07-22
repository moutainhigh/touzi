package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefCategory;
import com.river.ms.res.mapper.ResRefCategoryDao;
import com.river.ms.res.service.MPResRefCategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源对应分类 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResRefCategoryServiceDiy extends ServiceImpl<ResRefCategoryDao, ResRefCategory> implements MPResRefCategoryService {

	@Override
	public Boolean MyInsertBatch(List<ResList> resList, List<ResCategory> cateList) {
		List<ResRefCategory> resRefCate = new ArrayList<>();
		for(ResList r : resList) {
			for(ResCategory c : cateList) {
				ResRefCategory resRefCategory = new ResRefCategory();
				resRefCategory.setCategoryId(c.getEntityId());
				resRefCategory.setCreateTime(new Date());
				resRefCategory.setItcode(r.getItcode());
				resRefCategory.setResourceId(r.getEntityId());
				resRefCate.add(resRefCategory);
			}
		}
		boolean insertBatch = this.insertBatch(resRefCate);
		return insertBatch;
	}
	
	@Override
	public List<ResRefCategory> selectRes(Long categoryId){
		List<ResRefCategory> selectRes = this.baseMapper.selectRes(categoryId);
		return selectRes;
	}

}
