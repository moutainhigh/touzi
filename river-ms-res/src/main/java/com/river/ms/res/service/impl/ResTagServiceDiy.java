package com.river.ms.res.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.res.entity.ResTag;
import com.river.ms.res.mapper.ResTagDao;
import com.river.ms.res.service.MPResTagService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源标签 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
@Transactional
public class ResTagServiceDiy extends ServiceImpl<ResTagDao, ResTag> implements MPResTagService {

	@Override
	public Long exist(Long id, String code) {
		Long exist = this.baseMapper.exist(id, code);
		return exist;
	}

}
