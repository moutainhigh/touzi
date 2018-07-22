package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectBbs;
import com.river.ms.business.feign.serviceImpl.ToBbsImpl;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectBbsDao;
import com.river.ms.business.service.MPProjectBbsService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目讨论区 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectBbsServiceDiy extends ServiceImpl<ProjectBbsDao, ProjectBbs> implements MPProjectBbsService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ToBbsImpl toBbs;

	@Override
	@Transactional
	public boolean insertBbs(Map<String, Object> headerMap, ProjectBbs pb, String content, String title,
			UserEntity user) {

		// 获取项目大阶段
		Map<Long, Long> projectBigNode = toProject.getProjectStageNode(headerMap, pb.getStage());
		Set<Entry<Long, Long>> entrySet = projectBigNode.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			pb.setStage(entry.getKey());
		}
		// 获取阶段的code和title
		Map<String, String> codeAndTitleById = toProject.getCodeAndTitleById(headerMap, pb.getStage());
		// 获取forumID
		Set<Entry<String, String>> entrySet2 = codeAndTitleById.entrySet();
		for (Entry<String, String> entry : entrySet2) {
			Long forumId = toBbs.getForumIdByCode(headerMap, entry.getKey(), entry.getValue());
			Long insertTopicEntity = toBbs.insertTopicEntity(headerMap, title, user.getResId(), forumId, content,
					user.getEntityTitle());
			pb.setTopicId(insertTopicEntity);
			pb.setForumCode(entry.getKey());
			pb.setTopicTitle(title);
			boolean insert = this.insert(pb);
			return insert;
		}
		return false;
	}

	@Override
	public List<ProjectBbs> selectByProjectIdAndStageId(Map<String, Object> headerMap, Long projectId, Long stage,
			Boolean isAll) {
		if (!isAll) {
			// 设置项目阶段
			if (stage == null) {
				stage = toProject.getStageIdByProjectId(headerMap, projectId);
			}
			if (stage != null && stage != 0) {
				// 获取项目阶段大阶段
				Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap, stage);
				Set<Entry<Long, Long>> entrySet = projectStageNode.entrySet();
				for (Entry<Long, Long> entry : entrySet) {
					stage = entry.getKey();
				}
			}
		}else {
			stage = null;
		}
		// 查找问答列表
		List<ProjectBbs> selectList = this.baseMapper.selectProjectBbs(projectId, stage);
		return selectList;
	}

	@Override
	public List<ProjectBbs> getNoReply(String itcode) {
		return this.baseMapper.getNoReply(itcode);
	}
	@Override
	public List<ProjectBbs> getReplied(String itcode) {
		return this.baseMapper.getReplied(itcode);
	}
}
