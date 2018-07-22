package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.core.entity.UserEntity;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ResEvaluate;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.entity.ResTagHistory;
import com.river.ms.res.mapper.ResEvaluateDao;
import com.river.ms.res.service.MPResEvaluateService;
import com.river.ms.res.service.MPResRefTagService;
import com.river.ms.res.service.MPResTagHistoryService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源评价 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResEvaluateServiceDiy extends ServiceImpl<ResEvaluateDao, ResEvaluate> implements MPResEvaluateService {

	@Autowired
	MPResTagHistoryService resTagHistoryService;

	@Autowired
	MPResRefTagService resRefTagService;

	@Override
	public boolean insertResEvaluateAndTag(HttpServletRequest request, String itcode, Long resourceId, Long projectId,
			List<String> tags, String content, BigDecimal score) {
		// 获取用户信息
		UserEntity user = SessionUtils.getUser(request);
		Long userResId = user.getResId();
		String userItCode = user.getEntityCode();
		// 添加资源评价
		ResEvaluate resEvaluate = new ResEvaluate();
		if (content != null && !content.equals("")) {
			resEvaluate.setContent(content);
		}
		resEvaluate.setItcode(itcode);
		resEvaluate.setResourceId(resourceId);
		resEvaluate.setProjectId(projectId);
		resEvaluate.setCreateTime(new Date());
		resEvaluate.setEvaluateId(userResId);
		resEvaluate.setEvaluateItcode(userItCode);
		resEvaluate.setScore(score);
		boolean insert = this.insert(resEvaluate);
		// 添加资源标签
		List<ResTagHistory> resTagHistorys = new ArrayList<ResTagHistory>();
		for (String tag : tags) {
			ResTagHistory resTagHistory = new ResTagHistory();
			resTagHistory.setItcode(itcode);
			resTagHistory.setResourceId(resourceId);
			resTagHistory.setTag(tag);
			resTagHistory.setProjectId(projectId);
			resTagHistory.setCreateTime(new Date());
			resTagHistory.setEvaluateId(userResId);
			resTagHistory.setEvaluateItcode(userItCode);
			resTagHistorys.add(resTagHistory);
		}
		boolean insertBatch = true;
		if (resTagHistorys != null && resTagHistorys.size() > 0) {
			insertBatch = this.resTagHistoryService.insertBatch(resTagHistorys);
		}
		// 获取资源现在所拥有的标签
		EntityWrapper<ResRefTag> resRefTagWrapper = new EntityWrapper<>();
		resRefTagWrapper.eq("itcode", itcode);
		resRefTagWrapper.eq("resourceId", resourceId);
		List<ResRefTag> resRefTags = this.resRefTagService.selectList(resRefTagWrapper);
		// 添加或修改标签
		List<ResRefTag> resRefTagList = new ArrayList<>();
		Map<String, ResRefTag> resRefTagMap = new HashMap<String, ResRefTag>();
		for (ResRefTag r : resRefTags) {
			resRefTagMap.put(r.getTag(), r);
		}
		for (ResTagHistory rt : resTagHistorys) {
			ResRefTag resRefTag = resRefTagMap.get(rt.getTag());
			if (resRefTag != null) {
				resRefTag.setNum(resRefTag.getNum() + 1);
			} else {
				resRefTag = new ResRefTag();
				resRefTag.setItcode(itcode);
				resRefTag.setResourceId(resourceId);
				resRefTag.setTag(rt.getTag());
				resRefTag.setTagType(0);
				resRefTag.setCreateTime(new Date());
				resRefTag.setNum(1);
				resRefTagMap.put(resRefTag.getTag(), resRefTag);
			}
		}
		Set<Entry<String, ResRefTag>> entrySet = resRefTagMap.entrySet();
		for (Entry<String, ResRefTag> entry : entrySet) {
			resRefTagList.add(entry.getValue());
		}
		boolean insertOrUpdateBatch = true;
		if (resRefTagList != null && resRefTagList.size() > 0) {
			insertOrUpdateBatch = resRefTagService.insertOrUpdateBatch(resRefTagList);
		}
		if (insert && insertBatch && insertOrUpdateBatch) {
			return true;
		}
		return false;
	}

	@Override
	public List<ResEvaluate> getResEvaluate(Long entityId, Boolean bool) {
		return this.baseMapper.getResEvaluate(entityId,bool);
	}

	@Override
	public List<ResEvaluate> getResReceiveEvaluateAndTagHistory(Long entityId) {
		return this.baseMapper.getResReceiveEvaluateAndTagHistory(entityId);
	}

	@Override
	public List<ResEvaluate> getResSendEvaluateAndTagHistory(Long entityId) {
		return this.baseMapper.getResSendEvaluateAndTagHistory(entityId);
	}

}
