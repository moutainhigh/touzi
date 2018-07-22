package com.river.ms.business.service.impl;

import com.river.ms.business.entity.DataPie;
import com.river.ms.business.entity.ProjectListExt;
import com.river.ms.business.mapper.ProjectListExtDao;
import java.math.BigDecimal;
import com.river.ms.business.service.MPProjectListExtService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目信息扩展表（数据） 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectListExtServiceDiy extends ServiceImpl<ProjectListExtDao, ProjectListExt>
		implements MPProjectListExtService {

	@Override
	public DataPie getInvestStatistics(Integer year, Integer type, String categoryId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("type", type);
		map.put("categoryId", categoryId);
		map.put("cost", null);
		map.put("total", null);
		map.put("IRR", null);

		List<Map<String, Object>> list = this.baseMapper.getInvestStatistics(map);
		DataPie data = new DataPie();
		data.setCost((BigDecimal) map.get("cost"));
		data.setIrr((BigDecimal) map.get("IRR"));
		data.setTotal((Integer) map.get("total"));
		data.setTitle("投资总额");
		if (list.size() > 0) {
			Map<String, Object> tmp = null;
			for (int i = 0; i < list.size(); i++) {
				tmp = list.get(i);
				if (tmp.containsKey("投资总额")) {
					data.addSerie(tmp.get("投资总额").toString());
				}
				if (tmp.containsKey("投资类型")) {
					data.addLegend(tmp.get("投资类型").toString());
				} else if (tmp.containsKey("项目类型")) {
					data.addLegend(tmp.get("项目类型").toString());
				} else if (tmp.containsKey("行业板块")) {
					data.addLegend(tmp.get("行业板块").toString());
				} else if (tmp.containsKey("项目状态")) {
					data.addLegend(tmp.get("项目状态").toString());
				} else if (tmp.containsKey("地区")) {
					data.addLegend(tmp.get("地区").toString());
				}
			}
		}
		return data;
	}

	/**
	 * 获取各个阶段的项目数量
	 * 
	 * @param year
	 * @param categoryId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getCountStatisticsBy(Integer year, Integer type, String categoryId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("type", type);
		map.put("categoryId", categoryId);
		map.put("cost", null);
		map.put("total", null);
		map.put("IRR", null);

		return this.baseMapper.getCountStatistics(map);
	}

	@Override
	public DataPie getCountStatistics(Integer year, Integer type, String categoryId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("type", type);
		map.put("categoryId", categoryId);
		map.put("cost", null);
		map.put("total", null);
		map.put("IRR", null);
		List<Map<String, Object>> list = this.baseMapper.getCountStatistics(map);
		DataPie data = new DataPie();
		data.setCost((BigDecimal) map.get("cost"));
		data.setIrr((BigDecimal) map.get("IRR"));
		data.setTotal((Integer) map.get("total"));
		data.setTitle("项目数量");
		if (list.size() > 0) {
			Map<String, Object> tmp = null;
			for (int i = 0; i < list.size(); i++) {
				tmp = list.get(i);
				if (tmp.containsKey("项目数量")) {
					data.addSerie(tmp.get("项目数量").toString());
				}
				if (tmp.containsKey("投资类型")) {
					data.addLegend(tmp.get("投资类型").toString());
				} else if (tmp.containsKey("项目类型")) {
					data.addLegend(tmp.get("项目类型").toString());
				} else if (tmp.containsKey("行业板块")) {
					data.addLegend(tmp.get("行业板块").toString());
				} else if (tmp.containsKey("项目状态")) {
					data.addLegend(tmp.get("项目状态").toString());
				} else if (tmp.containsKey("投资额区间")) {
					data.addLegend(tmp.get("投资额区间").toString());
				} else if (tmp.containsKey("地区")) {
					data.addLegend(tmp.get("地区").toString());
				}
			}
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> getCompany(Integer year) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		return this.baseMapper.getCompany(map);
	}

	/**
	 * 
	 */
	@Override
	public List<Map<String, Object>> getProjectType(Integer year, String groupCode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("groupCode", groupCode);
		return this.baseMapper.getProjectType(map);
	}

	/**
	 * 
	 */
	@Override
	public List<Map<String, Object>> countMytodo(String itcode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itcode", itcode);
		return this.baseMapper.countMytodo(map);
	}

	@Override
	public List<Map<String, Object>> computeGroup(Integer year) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		List<Map<String, Object>> list = this.baseMapper.computeGroup(map);
		return list;
	}
}
