package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.entity.ResTag;
import com.river.ms.res.mapper.ResRefTagDao;
import com.river.ms.res.service.MPResRefTagService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源对应标签 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResRefTagServiceDiy extends ServiceImpl<ResRefTagDao, ResRefTag> implements MPResRefTagService {

	@Override
	public Boolean insertResRefTag(List<ResTag> tags, ResList res, Integer tagType) {
		EntityWrapper<ResRefTag> wrapper = new EntityWrapper<>();
		wrapper.eq("resourceId", res.getEntityId());
		wrapper.eq("itcode", res.getItcode());
		List<ResRefTag> resTags = this.selectList(wrapper);
		List<ResRefTag> resRefTag = new ArrayList<>();
		for (ResTag t : tags) {
			Boolean bool = true;
			for (ResRefTag r : resTags) {
				if (t.getEntityTitle().equals(r.getTag()) && r.getTagType().equals(tagType)) {
					bool = false;
					break;
				}
			}
			if (bool) {
				ResRefTag rrt = new ResRefTag();
				rrt.setCreateTime(new Date());
				rrt.setItcode(res.getItcode());
				rrt.setNum(0);
				rrt.setResourceId(res.getEntityId());
				rrt.setTag(t.getEntityTitle());
				rrt.setTagType(tagType);
				resRefTag.add(rrt);
			}
		}
		if (resRefTag != null && resRefTag.size() > 0) {
			boolean insertBatch = this.insertBatch(resRefTag);
			return insertBatch;
		}
		return true;
	}

}
