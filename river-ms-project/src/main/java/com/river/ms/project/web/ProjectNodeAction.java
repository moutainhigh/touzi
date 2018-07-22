package com.river.ms.project.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectNode;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectNodeService;

/**
 * <p>
 * 流程节点定义 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/projectNode")
public class ProjectNodeAction {
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	MPProjectNodeService mPProjectNodeService;
	
	/**
	 * 查找用户对应的项目节点列表，并查找可以操作的项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult selectProjectNodeList(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectNode> projectNodeList = null;
		try {
			List<RoleEntity> roles = user.getRoles();
			if (roles != null && !roles.isEmpty()) {
				Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
				projectNodeList = this.mPProjectNodeService.getProjectNodeByRoles(headerMap, roles,user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查找节点列表失败");
			return ErrorResult.QUERY_USER_NODE_ERROR;
		}
		/*if (projectNodeList == null || projectNodeList.isEmpty()) {
			return ErrorResult.USER_NODE_NOT_EXIT;
		}
		logger.debug("查找用户节点列表成功");*/
		return JsonResult.success(projectNodeList);
	}

}
