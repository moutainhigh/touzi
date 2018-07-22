package com.river.ms.res.service;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResTag;

/**
 * <p>
 * 资源标签 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResTagService extends IService<ResTag> {
	Long exist(Long id,String code);
}
