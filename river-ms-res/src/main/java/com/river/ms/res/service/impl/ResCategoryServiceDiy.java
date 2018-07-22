package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.res.entity.ResCategory;
import com.river.ms.res.mapper.ResCategoryDao;
import com.river.ms.res.service.MPResCategoryService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源分类 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResCategoryServiceDiy extends ServiceImpl<ResCategoryDao, ResCategory> implements MPResCategoryService {

	@Override
	public List<ResCategory> getByitCode(String itcode) {
		return this.baseMapper.getResRoleByitcode(itcode);
	}

	@Override
	public Boolean isExist(ResCategory r) {
		Boolean bool = false;
		EntityWrapper<ResCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("entityCode", r.getEntityCode());
		List<ResCategory> selectList = this.baseMapper.selectList(wrapper);
		if (selectList == null || selectList.size() == 0) {
			bool = true;
		} else {
			if (r.getEntityId() != null) {
				for (ResCategory l : selectList) {
					if(l.getEntityId().equals(r.getEntityId())) {
						bool = true;
					}
				}
			}
		}
		return bool;
	}

	@Override
	public Long exist(Long id, String code) {
		Long exist = this.baseMapper.exist(id, code);
		return exist;
	}
}
