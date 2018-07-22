package com.river.ms.business.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectMemberFollow;
import com.river.ms.business.entity.ViewResFollow;
import com.river.ms.business.service.MPProjectMemberFollowService;
import com.river.ms.business.service.MPViewResFollowService;

/**
 * <p>
 * 关注的人/专家 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/projectMemberFollow")
public class ProjectMemberFollowAction {

	@Autowired
	private MPProjectMemberFollowService service;

	@Autowired
	private MPViewResFollowService viewService;

	/**
	 * 添加关注/取消关注
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/insertProjectMemberFollow", method = RequestMethod.POST)
	public JsonResult insertProjectMemberFollow(HttpServletRequest request,
			@RequestParam(name = "resourceId", required = false) Long resourceId,
			@RequestParam(name = "itcode") String itcode) {
		if (resourceId == null) {
			UserEntity user = SessionUtils.getUser(request);
			resourceId = user.getResId();
		}

		EntityWrapper<ProjectMemberFollow> wrapper = new EntityWrapper<>();
		wrapper.eq("itcode", itcode);
		wrapper.eq("resourceId", resourceId);
		List<ProjectMemberFollow> selectList = this.service.selectList(wrapper);

		if (selectList != null && selectList.size() > 0) {
			for (ProjectMemberFollow p : selectList) {
				this.service.deleteById(p.getEntityId());
			}
			return JsonResult.success("取消关注成功！");
		} else {
			ProjectMemberFollow follow = new ProjectMemberFollow();
			follow.setCreateTime(new Date());
			follow.setItcode(itcode);
			follow.setResourceId(resourceId);
			this.service.insert(follow);
			return JsonResult.success("关注成功！");
		}

	}

	/**
	 * 关注人员列表
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/selectProjectMemberFollow", method = RequestMethod.POST)
	public JsonResult selectProjectMemberFollow(HttpServletRequest request,
			@RequestParam(name = "resourceId", required = false) Long resourceId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value="keyword" ,required = false)String keyword) {
		if (resourceId == null) {
			UserEntity user = SessionUtils.getUser(request);
			resourceId = user.getResId();
		}
		PageHelper.startPage(page, pageSize);
		EntityWrapper<ViewResFollow> wrapper = new EntityWrapper<>();
		wrapper.eq("resourceId", resourceId);
		if(keyword != null && !keyword.equals("")) {
			wrapper.and().like("entityTitle", keyword).or().like("itcode", keyword);
		}
		List<ViewResFollow> selectList = this.viewService.selectList(wrapper);
		PageInfo<ViewResFollow> pageInfo = new PageInfo<ViewResFollow>(selectList, 5);
		return JsonResult.successPage(selectList, pageInfo);
	}

	/**
	 * 获取用户对某个资源的关注信息
	 * 
	 * @param request
	 * @param resourceId
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/getResVerifyMessage", method = RequestMethod.POST)
	public JsonResult getResVerifyMessage(HttpServletRequest request,
			@RequestParam(name = "resourceId", required = false) Long resourceId,
			@RequestParam(name = "itcode") String itcode) {
		if (resourceId == null) {
			UserEntity user = SessionUtils.getUser(request);
			resourceId = user.getResId();
		}
		EntityWrapper<ProjectMemberFollow> wrapper = new EntityWrapper<>();
		wrapper.eq("itcode", itcode);
		wrapper.eq("resourceId", resourceId);
		List<ProjectMemberFollow> selectList = this.service.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			return JsonResult.success(true);
		} else {
			return JsonResult.success(false);
		}
	}
}
