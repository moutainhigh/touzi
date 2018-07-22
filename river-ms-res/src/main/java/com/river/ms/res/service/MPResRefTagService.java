package com.river.ms.res.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.river.ms.res.entity.ResList;
import com.river.ms.res.entity.ResRefTag;
import com.river.ms.res.entity.ResTag;

/**
 * <p>
 * 资源对应标签 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPResRefTagService extends IService<ResRefTag> {

	Boolean insertResRefTag(List<ResTag> tags, ResList res,Integer tagType);
}
