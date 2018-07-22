package com.river.ms.business.service.impl;

import com.river.ms.business.entity.ProjectEmail;
import com.river.ms.business.mapper.ProjectEmailDao;
import com.river.ms.business.service.MPProjectEmailService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyb
 * @since 2018-01-26
 */
@Service
//@Transactional
public class ProjectEmailServiceDiy extends ServiceImpl<ProjectEmailDao, ProjectEmail> implements MPProjectEmailService {

	@Override
	public List<ProjectEmail> selectProjectEmail() {
		return this.baseMapper.selectProjectEmail();
	}

	@Override
	public Boolean updateById(Long entityId) {
		ProjectEmail entity = this.selectById(entityId);
		entity.setState(1);
		entity.setUpdateTime(new Date());
		boolean updateById = this.updateById(entity);
		return updateById;
	}

	@Override
	public Boolean updateAll() {
		ProjectEmail entity = new ProjectEmail();
		entity.setState(1);
		entity.setUpdateTime(new Date());
		EntityWrapper<ProjectEmail> wrapper = new EntityWrapper<>();
		wrapper.eq("state", 0);
		boolean update = this.update(entity, wrapper);
		return update;
	}

}
