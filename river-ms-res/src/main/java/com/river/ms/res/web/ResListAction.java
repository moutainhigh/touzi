package com.river.ms.res.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.entity.PageBean;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ResEvaluate;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.feign.service.impl.ToProjectImpl;
import com.river.ms.res.service.MPResEvaluateService;
import com.river.ms.res.service.MPResListService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.entity.ResTagHistory;
import com.river.ms.res.service.MPResRefTagService;
import com.river.ms.res.service.MPResTagHistoryService;

/**
 * <p>
 * 资源列表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/resList")
public class ResListAction {

	@Autowired
	MPResListService service;

	@Autowired
	ToProjectImpl toProject;
	@Autowired
	MPResRefTagService resRefTagService;

	@Autowired
	MPResEvaluateService resEvaluateService;

	@Autowired
	MPResTagHistoryService resTagHistoryService;

	/**
	 * 查询资源所拥有的标签
	 */
	@RequestMapping(value = "/getResAndTag", method = RequestMethod.POST)
	public JsonResult getResAndTag(HttpServletRequest request, @RequestParam(value = "itcode") String itcode,
			@RequestParam(value = "tagType",required=false) Integer tagType) {
		ResList selectResAndTag = service.selectResAndTag(itcode,tagType);
		return new JsonResult(0, "OK", selectResAndTag);
	}

	/**
	 * 根据资源ID获取资源详情
	 */
	@RequestMapping(value = "/getResById", method = RequestMethod.POST)
	public JsonResult getResById(HttpServletRequest request, @RequestParam(value = "entityId") Long entityId) {
		ResList res = this.service.getResById(entityId);
		if (res != null) {
			EntityWrapper<ResRefTag> wrapper = new EntityWrapper<ResRefTag>();
			wrapper.eq("itcode", res.getItcode());
			wrapper.orderBy("num", false);
			List<ResRefTag> tags = resRefTagService.selectList(wrapper);
			res.setTags(tags);
		}
		return new JsonResult(0, "OK", res);
	}

	@RequestMapping(value = "/setResSpecialty", method = RequestMethod.POST)
	public JsonResult setResSpecialty(HttpServletRequest request,
			@RequestParam(value = "entityId") Long entityId,
			@RequestParam(value = "specialty") String specialty) {
		ResList entity = new ResList();
		entity.setEntityId(entityId);
		entity.setSpecialty(specialty);
		boolean updateById = this.service.updateById(entity);
		return JsonResult.success(updateById);
	}
	
	
	/**
	 * 根据资源类别查询资源列表
	 */
	@RequestMapping(value = "/getRes", method = RequestMethod.POST)
	public JsonResult getRes(HttpServletRequest request,
			@RequestParam(value = "categoryType", required = false) Integer categoryType,
			@RequestParam(value = "projectId", defaultValue = "0") Long projectId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "keyword", required = false) String keyWord,
			@RequestParam(value = "stage", required = false, defaultValue = "0") Long stage,
			@RequestParam(value = "isExpert", required = false, defaultValue = "false") Boolean isExpert,
			@RequestParam(value = "isExcludeExpert", required = false, defaultValue = "false") Boolean isExcludeExpert,
			@RequestParam(value = "isExcludeOneself", required = false, defaultValue = "true") Boolean isExcludeOneself) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null) {
			return JsonResult.failure("用户信息不存在！");
		}
		if (stage != null && stage != 0) {
			if (projectId != 0) {
				Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
				Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap, stage);
				Set<Entry<Long, Long>> entrySet = projectStageNode.entrySet();
				for (Entry<Long, Long> e : entrySet) {
					stage = e.getKey();
				}
			}
		}
		String itcode = null;
		if (isExcludeOneself) {
			itcode = user.getEntityCode();
		}
		PageHelper.startPage(page, pageSize);
		List<ResList> resByCategoryType = service.getResByCategoryType(request, categoryType, projectId, stage, keyWord,
				isExpert, isExcludeExpert, isExcludeOneself, itcode);
		PageInfo<ResList> pageInfo = new PageInfo<ResList>(resByCategoryType, 5);
		return JsonResult.successPage(resByCategoryType, pageInfo);
	}

	/**
	 * 获取用户的认证信息，以及resId
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getResAuthentication", method = RequestMethod.POST)
	public Map<String, String> getResAuthentication(@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "itcode") String itcode) {
		Map<String, String> result = new HashMap<>();
		List<ResList> res = this.service.getRes(userId, itcode);
		if (res != null && res.size() > 0) {
			result.put("authentication", "true");
			result.put("resId", res.get(0).getEntityId().toString());
		} else {
			result.put("authentication", "false");
		}
		return result;
	}

	/**
	 * 获取资源角色
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getResRole", method = RequestMethod.POST)
	public JsonResult getResRole(@RequestParam(value = "itcode") String itcode) {
		List<ResList> res = this.service.selectResRoleByItcode(itcode);
		return JsonResult.success(res);
	}

	/**
	 * 根据项目id，获取专家信息
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getExpertByProjectId", method = RequestMethod.POST)
	public JsonResult getExpertByProjectId(HttpServletRequest request,
			@RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage", required = false) String stage) {
		List<ResList> res = this.service.getExpertByProjectId(request, projectId, stage);
		return JsonResult.success(res);
	}

	/**
	 * 根据资源id获取资源详细信息
	 * 
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getResById/{entityId}", method = RequestMethod.GET)
	public JsonResult getResById(@PathVariable(value = "entityId") Long entityId) {
		ResList res = this.service.selectById(entityId);
		if (res == null)
			return JsonResult.failure("resources is null");
		ResList selectResAndTag = service.selectResAndTag(res.getItcode(),null);
		return JsonResult.success(selectResAndTag);
	}

	/**
	 * 
	 * @param itCode
	 * @return
	 */
	@RequestMapping(value = "/getResId", method = RequestMethod.POST)
	public Long getResId(@RequestParam("itCode") String itCode) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("itcode", itCode);
		List<ResList> selectByMap = this.service.selectByMap(columnMap);
		if(selectByMap != null && selectByMap.size()>0) {
			return selectByMap.get(0).getEntityId();
		}else {
			return -1l;
		}
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public JsonResult getAll(@RequestParam(name = "keyword", required = false) String keyword) {
		List<ResList> selectByMap = this.service.getAll(keyword);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 获取行业公司一把手
	 * 
	 * @param groupCode
	 * @return
	 */
	@RequestMapping(value = "/getComLeader", method = RequestMethod.POST)
	public JsonResult getComLeader(@RequestParam(name = "groupCode") String groupCode) {
		List<ResList> selectByMap = this.service.getComLeader(groupCode);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectMember", method = RequestMethod.POST)
	public JsonResult getProjectMember(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "memberType", required = false, defaultValue = "-1") Integer memberType) {
		List<ResList> selectByMap = this.service.getProjectMember(request, projectId, memberType);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getDecisionLayer", method = RequestMethod.POST)
	public JsonResult getDecisionLayer(@RequestParam(name = "projectId") Long projectId) {
		List<ResList> selectByMap = this.service.getDecisionLayer(projectId);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllTeamLeader", method = RequestMethod.POST)
	public JsonResult getTeamLeader(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		PageHelper.startPage(page, pageSize);
		List<ResList> selectByMap = this.service.getAllTeamLeader(keyword);
		PageInfo<ResList> pageInfo = new PageInfo<ResList>(selectByMap, 5);
		return JsonResult.successPage(selectByMap, pageInfo);
	}

	/**
	 * 获取行业公司一把手id
	 * 
	 * @param groupCode
	 * @return
	 */
	@RequestMapping(value = "/getComLeaderIds", method = RequestMethod.POST)
	public Map<String, List<Long>> getComLeaderIds(@RequestParam(name = "groupCode") String groupCode) {
		Map<String, List<Long>> result = new HashMap<>();
		List<ResList> selectByMap = this.service.getComLeader(groupCode);
		List<Long> ids = new ArrayList<>();
		for (ResList r : selectByMap) {
			ids.add(r.getEntityId());
		}
		result.put("ids", ids);
		return result;
	}

	/**
	 * 获取公司赋能群IDs
	 * 
	 * @param groupCode
	 * @return
	 */
	@RequestMapping(value = "/getFuNengQunIds", method = RequestMethod.POST)
	public Map<String, List<Long>> getFuNengQunIds(@RequestParam(name = "groupCode") String groupCode) {
		Map<String, List<Long>> result = new HashMap<>();
		List<ResList> selectByMap = this.service.getFuNengQun(groupCode);
		List<Long> ids = new ArrayList<>();
		for (ResList r : selectByMap) {
			ids.add(r.getEntityId());
		}
		result.put("ids", ids);
		return result;
	}

	/**
	 * 获取关注资源列表
	 * 
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getFollowMember", method = RequestMethod.POST)
	public JsonResult getFollowMember(@RequestParam(name = "itcode") String itcode,
			@RequestParam(value = "bPage", defaultValue = "0") int bPage,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "keyword", required = false) String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bPage", bPage);
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("keyword", keyword);
		map.put("itcode", itcode);
		map.put("total", 0);
		List<ResList> selectByMap = this.service.getFollowMember(map);

		PageBean pages = new PageBean(selectByMap, 5, page, pageSize, Long.parseLong(map.get("total").toString()));
		return JsonResult.successPage(selectByMap, pages);
	}

	/**
	 * 获取用户收到的评价
	 * 
	 * @param request
	 * @param entityId
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getResReceiveEvaluateAndTagHistory", method = RequestMethod.POST)
	public JsonResult getResReceiveEvaluateAndTagHistory(HttpServletRequest request,
			@RequestParam(name = "resId", required = false) Long entityId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		if (entityId == null) {
			UserEntity user = SessionUtils.getUser(request);
			if (user == null) {
				return JsonResult.failure("用户信息异常!");
			}
			entityId = user.getResId();
		}
		if (entityId == null) {
			return JsonResult.failure("用户信息异常!");
		}
		ResList selectById = this.service.selectById(entityId);
		if (selectById == null) {
			return JsonResult.failure("用户信息异常!");
		}
		PageHelper.startPage(page, pageSize);
		List<ResEvaluate> resReceiveEvaluateAndTagHistory = this.resEvaluateService
				.getResReceiveEvaluateAndTagHistory(entityId);
		PageInfo<ResEvaluate> pageInfo = new PageInfo<ResEvaluate>(resReceiveEvaluateAndTagHistory, 5);
		return JsonResult.successPage(resReceiveEvaluateAndTagHistory, pageInfo);
	}

	/**
	 * 获取用户发出的评价
	 * 
	 * @param request
	 * @param entityId
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getResSendEvaluateAndTagHistory", method = RequestMethod.POST)
	public JsonResult getResSendEvaluateAndTagHistory(HttpServletRequest request,
			@RequestParam(name = "resId", required = false) Long entityId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		if (entityId == null) {
			UserEntity user = SessionUtils.getUser(request);
			if (user == null) {
				return JsonResult.failure("用户信息异常!");
			}
			entityId = user.getResId();
		}
		if (entityId == null) {
			return JsonResult.failure("用户信息异常!");
		}
		ResList selectById = this.service.selectById(entityId);
		if (selectById == null) {
			return JsonResult.failure("用户信息异常!");
		}
		PageHelper.startPage(page, pageSize);
		List<ResEvaluate> resSendEvaluateAndTagHistory = this.resEvaluateService
				.getResSendEvaluateAndTagHistory(entityId);
		PageInfo<ResEvaluate> pageInfo = new PageInfo<ResEvaluate>(resSendEvaluateAndTagHistory, 5);
		return JsonResult.successPage(resSendEvaluateAndTagHistory, pageInfo);
	}
}
