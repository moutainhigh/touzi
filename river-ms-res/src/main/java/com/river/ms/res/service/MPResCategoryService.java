package com.river.ms.res.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResCategory;

/**
 * <p>
 * 资源分类 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResCategoryService extends IService<ResCategory> {
	List<ResCategory> getByitCode(String itcode);
	
	/**
	 * 验证编码是否已经存在
	 * @param r
	 * @return
	 */
	Boolean isExist(ResCategory r);
	
	Long exist(Long id,String code);
}
