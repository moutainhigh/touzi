package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.entity.ResTag;
import com.river.ms.res.entity.ResTagHistory;
import com.river.ms.res.mapper.ResTagHistoryDao;
import com.river.ms.res.service.MPResRefTagService;
import com.river.ms.res.service.MPResTagHistoryService;
import com.river.ms.res.service.MPResTagService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源评价标签 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResTagHistoryServiceDiy extends ServiceImpl<ResTagHistoryDao, ResTagHistory> implements MPResTagHistoryService {

	@Autowired
	MPResTagService resTagService;

	@Autowired
	MPResRefTagService resRefTagService;
	
	@Override
	@Transactional
	public boolean insertResTagHistory(ResTagHistory entity) {
		entity.setCreateTime(new Date());
		boolean insert = this.insert(entity);
		if (insert) {
			ResRefTag refTag = new ResRefTag();
			Map<String, Object> resRefTagMap = new HashMap<>();
			resRefTagMap.put("tag", entity.getTag());
			resRefTagMap.put("itcode", entity.getItcode());
			List<ResRefTag> resRefTag = resRefTagService.selectByMap(resRefTagMap);
			if (resRefTag != null && resRefTag.size() != 0) {
				refTag = resRefTag.get(0);
				refTag.setNum(refTag.getNum() + 1);
			} else {
				refTag.setItcode(entity.getItcode());
				refTag.setTag(entity.getTag());
				refTag.setCreateTime(new Date());
				refTag.setNum(1);
			}
			Map<String, Object> resTagMap = new HashMap<>();
			resTagMap.put("entityTitle", entity.getTag());
			List<ResTag> selectByMap = resTagService.selectByMap(resTagMap);
			if (selectByMap != null && selectByMap.size() != 0) {
				refTag.setTagType(1);
			} else {
				refTag.setTagType(0);
			}
			resRefTagService.insertOrUpdate(refTag);
		}
		return insert;
	}

	@Override
	public List<ResTagHistory> getResTagHistory(Long entityId, boolean bool) {
		return this.baseMapper.getResTagHistory(entityId,bool);
	}

}
