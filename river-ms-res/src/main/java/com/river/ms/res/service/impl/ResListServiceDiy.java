package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.feign.service.impl.ToSystemImpl;
import com.river.ms.res.mapper.ResListDao;
import com.river.ms.res.service.MPResCategoryService;
import com.river.ms.res.service.MPResListService;
import com.river.ms.res.service.MPResRefCategoryService;
import com.river.ms.res.service.MPResRefTagService;

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

/**
 * <p>
 * 资源列表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ResListServiceDiy extends ServiceImpl<ResListDao, ResList> implements MPResListService {

	@Autowired
	MPResRefTagService resRefTagService;

	@Autowired
	MPResCategoryService resCategoryService;

	@Autowired
	MPResRefCategoryService resRefCategoryService;
	
	@Autowired
	MPResListService resListService;

	@Autowired
	ToSystemImpl toSystem;

	public void getGongsi(HttpServletRequest request, List<ResList> resList) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		List<Long> userIds = new ArrayList<>();
		for (ResList r : resList) {
			userIds.add(r.getUserId());
		}
		if (userIds != null && userIds.size() > 0) {
			Map<Long,String> gongSi = this.toSystem.getGongSi(headerMap, userIds);
			for(ResList r : resList) {
				String string = gongSi.get(r.getUserId());
				if(string != null && !string.equals("")) {
					r.setGongsi(string);
				}
			}
		}
	}

	@Override
	public ResList selectResAndTag(String itcode,Integer tagType) {
		EntityWrapper<ResRefTag> wrapper = new EntityWrapper<ResRefTag>();
		wrapper.eq("itcode", itcode);
		if(tagType != null) {
			wrapper.eq("tagType", tagType);
		}
		wrapper.orderBy("num", false);
		ResList entity=resListService.getResByItcode(itcode);
		List<ResRefTag> selectList = resRefTagService.selectList(wrapper);
		entity.setTags(selectList);
		return entity;
	}

	@Override
	public List<ResList> getResByCategoryType(HttpServletRequest request, Integer categoryType, Long projectId,
			Long stage, String keyWord, Boolean isExpert,Boolean isExcludeExpert,Boolean isExcludeOneself,String itcode) {
		List<ResList> selectResPageByCategoryType = this.baseMapper.selectResPageByCategoryType(categoryType, projectId,
				stage, keyWord,isExpert,isExcludeExpert,isExcludeOneself,itcode);
		this.getGongsi(request, selectResPageByCategoryType);
		return selectResPageByCategoryType;
	}
	/**
	 * 
	 */
	@Override
	public List<ResList> getRes(Long userId, String itcode) {
		Wrapper<ResList> wrapper = new EntityWrapper<ResList>();
		wrapper.eq("isDelete", 0);
		wrapper.eq("isDisable", 0);
		wrapper.and().eq("userId", userId).or().eq("itcode", itcode);
		List<ResList> selectList = this.baseMapper.selectList(wrapper);
		return selectList;
	}
	/**
	 * 根据itcode获取资源信息
	 */
	@Override
	public ResList getResByItcode(String itcode) {
		Wrapper<ResList> wrapper = new EntityWrapper<ResList>();
		wrapper.eq("isDelete", 0);
		wrapper.eq("isDisable", 0);
		wrapper.and().eq("itcode", itcode);
		List<ResList> selectList = this.baseMapper.selectList(wrapper);
		if(selectList==null || selectList.isEmpty()) return null;
		return selectList.get(0);
	}
	/**
	 * 
	 * @param itcode
	 * @return
	 */
	@Override
	public List<ResList> selectResRoleByItcode(String itcode) {
		return this.baseMapper.selectResRoleByItcode(itcode);
	}

	@Override
	public List<ResList> getExpertByProjectId(HttpServletRequest request,Long projectId, String stage) {
		List<ResList> expertByProjectId = this.baseMapper.getExpertByProjectId(projectId, stage);
		expertByProjectId = this.isExist(expertByProjectId);
		this.getGongsi(request, expertByProjectId);
		return expertByProjectId;
	}

	public List<ResList> isExist(List<ResList> expertByProjectId){
		List<ResList> result = new ArrayList<>();
		Map<Long, ResList> map = new HashMap<>();
		for(ResList r : expertByProjectId) {
			map.put(r.getEntityId(), r);
		}
		Set<Entry<Long, ResList>> entrySet = map.entrySet();
		for (Entry<Long, ResList> entry : entrySet) {
			result.add(entry.getValue());
		}
		return result;
	}
	
	@Override
	public List<ResList> getAll(String keyWord) {
		Wrapper<ResList> wrapper = new EntityWrapper<ResList>();
		if (keyWord != null && !keyWord.equals("")) {
			wrapper.like("entityTitle", keyWord).or();
			wrapper.like("itcode", keyWord);
		}
		List<ResList> selectList = this.baseMapper.selectList(wrapper);
		return selectList;
	}

	/**
	 * 获取一把手
	 */
	@Override
	public List<ResList> getComLeader(String groupCode) {
		return this.baseMapper.getComLeader(groupCode);
	}

	@Override
	public List<ResList> getProjectMember(HttpServletRequest request,Long projectId,int memberType) {
		List<ResList> projectMember = this.baseMapper.getProjectMember(projectId,memberType);
		this.getGongsi(request, projectMember);
		return projectMember;
	}

	@Override
	public List<ResList> getDecisionLayer(Long projectId) {
		return this.baseMapper.getDecisionLayer(projectId);
	}

	@Override
	public List<ResList> getFuNengQun(String groupCode) {
		return this.baseMapper.getFuNengQun(groupCode);
	}

	@Override
	public List<ResList> getAllTeamLeader(String keyword) {
		return this.baseMapper.getAllTeamLeader(keyword);
	}

	@Override
	public ResList getResById(Long entityId) {
		ResList resById = this.baseMapper.getResById(entityId);
		return resById;
	}

	@Override
	public Long exist(Long id, String code) {
		Long exist = this.baseMapper.exist(id, code);
		return exist;
	}

	@Override
	public Boolean insertRes(Map<String, Object> headerMap, ResList resEntity, Long employeeId, String eId) {
		resEntity.setCreateTime(new Date());
		Long insertUser = this.toSystem.insertUser(headerMap, resEntity, employeeId, eId);
		if (insertUser != 0) {
			resEntity.setUserId(insertUser);
			boolean insert = this.insert(resEntity);
			if (insert) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取关注的资源列表
	 */
	@Override
	public List<ResList> getFollowMember(HashMap<String,Object> map) {
		return this.baseMapper.getFollowMember(map);
	}
}
