package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectData;
import com.river.ms.business.mapper.ProjectDataDao;
import com.river.ms.business.service.MPProjectDataService;
import com.river.ms.business.utils.ExcelUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 可研数据录入 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectDataServiceDiy extends ServiceImpl<ProjectDataDao, ProjectData> implements MPProjectDataService {

	@Override
	public List<ProjectData> getProjectData(ProjectData entity, List<Long> ids) {
		List<ProjectData> projectData = this.baseMapper.getProjectData(entity, ids);
		return projectData;
	}

	/**
	 * 根据模版数据进行批量保存
	 * 
	 * @param file
	 * @throws Exception
	 */
	@Transactional
	public void insertBatchByExcelTempl(MultipartFile file, Long projectId, UserEntity user) {
		Workbook wb = null;
		List<ProjectData> ProjectDataList = new ArrayList<>();
		try {
			wb = new HSSFWorkbook(file.getInputStream());
			for (Sheet sheet : wb) {
				if (sheet != null) {
					int rowIndex = 0;
					int cellIndex = 0;
					if ("评价指标数据".equals(sheet.getSheetName())) {
						for (Row row : sheet) {
							ProjectData ProjectData = new ProjectData();
							for (Cell c : row) {
								rowIndex = c.getRowIndex();
								cellIndex = c.getColumnIndex();
								if (rowIndex != 0) {
									String s = ExcelUtil.getCellFormatValue(row.getCell(0)).trim();
									String s1[] = s.split("\\.");
									Long indicatorId = Long.parseLong(s1.length > 0 ? s1[0] : s);
									if(row.getCell(3) == null) {
										break;
									}
									String itemValue = ExcelUtil.getCellFormatValue(row.getCell(3)).trim();
									ProjectData.setProjectId(projectId);
									ProjectData.setIndicatorId(indicatorId);
									ProjectData.setItemValue(itemValue);
									ProjectData.setEntityType(0);
									ProjectData.setItcode(user.getEntityCode());
									ProjectData.setResourceId(user.getResId());
									ProjectData.setCreateTime(new Date());
									ProjectDataList.add(ProjectData);
									break;
								}
							}
						}
					}
					if ("经营数据".equals(sheet.getSheetName())) {
						List<ProjectData> ProjectDataList1 = new ArrayList<>();
						for (Row row : sheet) {
							for (Cell c : row) {
								rowIndex = c.getRowIndex();
								cellIndex = c.getColumnIndex();
								if (rowIndex == 0 && cellIndex > 2) {
									ProjectData ProjectData = new ProjectData();
									String s = ExcelUtil.getCellFormatValue(c).trim();
									String s1[] = s.split("\\.");
									ProjectData.setYear(Integer.parseInt(s1.length > 0 ? s1[0] : s));
									ProjectDataList1.add(ProjectData);
								}
								if (rowIndex != 0 && cellIndex > 2) {
									ProjectData ProjectData = new ProjectData(ProjectDataList1.get(cellIndex - 3));
									String s = ExcelUtil.getCellFormatValue(row.getCell(0)).trim();
									String s1[] = s.split("\\.");
									Long indicatorId = Long.parseLong(s1.length > 0 ? s1[0] : s);
									String itemValue = ExcelUtil.getCellFormatValue(c).trim();
									ProjectData.setProjectId(projectId);
									ProjectData.setIndicatorId(indicatorId);
									ProjectData.setItemValue(itemValue);
									ProjectData.setEntityType(0);
									ProjectData.setItcode(user.getEntityCode());
									ProjectData.setResourceId(user.getResId());
									ProjectData.setCreateTime(new Date());
									ProjectDataList.add(ProjectData);
								}
							}

						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ProjectDataList != null && !ProjectDataList.isEmpty()) {
			this.insertBatch(ProjectDataList);
		}
		if (wb != null) {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional
	public void insertBatchByExcelTempl(MultipartFile file, Long projectId, UserEntity user, Integer type, Integer year,
			Integer entityType) {
		Workbook wb = null;
		List<ProjectData> ProjectDataList = new ArrayList<>();
		try {
			wb = new HSSFWorkbook(file.getInputStream());
			for (Sheet sheet : wb) {
				if (sheet != null) {
					int rowIndex = 0;
					int cellIndex = 0;
					for (Row row : sheet) {
						for (Cell c : row) {
							rowIndex = c.getRowIndex();
							cellIndex = c.getColumnIndex();
							if (rowIndex != 0 && cellIndex > 2) {
								ProjectData projectData = new ProjectData();
								// 获取数据项ID
								String s = ExcelUtil.getCellFormatValue(row.getCell(0)).trim();
								String s1[] = s.split("\\.");
								Long indicatorId = Long.parseLong(s1.length > 0 ? s1[0] : s);
								// 获取值
								String itemValue = ExcelUtil.getCellFormatValue(c).trim();

								// 设置属性
								projectData.setProjectId(projectId);
								projectData.setIndicatorId(indicatorId);
								projectData.setItemValue(itemValue);
								// 1-年度数据，2-季度数据，3-月数据
								if (type == 1) {
									String trim = ExcelUtil.getCellFormatValue(sheet.getRow(0).getCell(cellIndex))
											.trim();
									String s11[] = trim.split("\\.");
									projectData.setYear(Integer.parseInt(s11.length > 0 ? s11[0] : s));
								}
								if (type == 2) {
									String trim = ExcelUtil.getCellFormatValue(sheet.getRow(0).getCell(cellIndex))
											.trim();
									projectData.setYear(year);
									String s11[] = trim.split("\\.");
									projectData.setQuarter(Integer.parseInt(s11.length > 0 ? s11[0] : s));
								}
								if (type == 3) {
									String trim = ExcelUtil.getCellFormatValue(sheet.getRow(0).getCell(cellIndex))
											.trim();
									projectData.setYear(year);
									String s11[] = trim.split("\\.");
									projectData.setMonth(Integer.parseInt(s11.length > 0 ? s11[0] : s));
								}
								projectData.setEntityType(entityType);
								projectData.setItcode(user.getEntityCode());
								projectData.setResourceId(user.getResId());
								projectData.setCreateTime(new Date());
								ProjectDataList.add(projectData);
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ProjectDataList != null && !ProjectDataList.isEmpty()) {
			this.insertBatch(ProjectDataList);
		}
		if (wb != null) {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
