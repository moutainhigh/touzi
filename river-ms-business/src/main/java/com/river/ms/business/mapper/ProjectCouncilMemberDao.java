package com.river.ms.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.business.entity.ProjectCouncilMember;

/**
 * <p>
 * 项目投决评议会、变更评议会等参会人员 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectCouncilMemberDao extends BaseMapper<ProjectCouncilMember> {

}
