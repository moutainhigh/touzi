package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectStageDoc;
import com.river.ms.project.mapper.ProjectStageDocDao;
import com.river.ms.project.service.MPProjectStageDocService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目阶段文档匹配 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectStageDocServiceDiy extends ServiceImpl<ProjectStageDocDao, ProjectStageDoc> implements MPProjectStageDocService {

	@Override
	public List<ProjectStageDoc> getProjectStageDocs(Long stageId,Integer type,Long categoryId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("categoryGroupId", categoryId);
		columnMap.put("stageId", stageId);
		if (type != null) {
			columnMap.put("type", type);
		}
		List<ProjectStageDoc> selectByMap = this.baseMapper.selectByMap(columnMap);
		return selectByMap;
	}

}
