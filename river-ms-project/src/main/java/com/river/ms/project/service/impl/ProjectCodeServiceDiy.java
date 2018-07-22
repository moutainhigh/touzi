package com.river.ms.project.service.impl;

import com.river.ms.project.entity.ProjectCode;
import com.river.ms.project.mapper.ProjectCodeDao;
import com.river.ms.project.service.MPProjectCodeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目编码规则 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-28
 */
@Service
public class ProjectCodeServiceDiy extends ServiceImpl<ProjectCodeDao, ProjectCode> implements MPProjectCodeService {

	@Override
	public String getNextProjectNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String year = sdf.format(date);
		EntityWrapper<ProjectCode> wrapper = new EntityWrapper<>();
		wrapper.eq("c3", year);
		List<ProjectCode> selectList = this.selectList(wrapper);
		int selectCount = 0;
		if (selectList != null && selectList.size() > 0) {
			selectCount = selectList.get(0).getC4();
		}
		selectCount++;
		String num = String.format("%05d", selectCount);
		StringBuffer str = new StringBuffer();
		str.append("XA").append(year).append(num);
		return str.toString();
	}

	@Override
	@Transactional
	public Boolean saveProjectNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String year = sdf.format(date);

		EntityWrapper<ProjectCode> wrapper = new EntityWrapper<>();
		wrapper.eq("c3", year);
		List<ProjectCode> selectList = this.selectList(wrapper);

		if (selectList != null && selectList.size() > 0) {
			selectList.get(0).setC4(selectList.get(0).getC4() + 1);
			boolean updateById = this.update(selectList.get(0), wrapper);
			return updateById;
		} else {
			ProjectCode p = new ProjectCode();
			p.setC1("XA");
			p.setC2("");
			p.setC3(year);
			p.setC4(1);
			boolean insert = this.insert(p);
			return insert;
		}
	}

}
