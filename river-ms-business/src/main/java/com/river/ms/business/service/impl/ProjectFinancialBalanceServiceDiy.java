package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectFinancialBalance;
import com.river.ms.business.mapper.ProjectFinancialBalanceDao;
import com.river.ms.business.service.MPProjectFinancialBalanceService;
import com.river.ms.business.utils.ExcelUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.lang.reflect.Field;

/**
 * <p>
 * 项目财务数据负债表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectFinancialBalanceServiceDiy extends 

ServiceImpl<ProjectFinancialBalanceDao, ProjectFinancialBalance> implements 

MPProjectFinancialBalanceService {

	/**
	 * 根据模版数据进行批量保存
	 * @param file
	 * @throws Exception 
	 */
	@Transactional
	public void insertBatchByExcelTempl(MultipartFile file,Long projectId,UserEntity 

user){
		Workbook wb = null;
		Sheet sheet = null;
		List<ProjectFinancialBalance> pfbList = new 

ArrayList<ProjectFinancialBalance>();
		try {
			wb = new HSSFWorkbook(file.getInputStream());  
			sheet = wb.getSheetAt(0);
			//总行数
			int rowIndex = 0;
			int cellIndex = 0;
			Field field = null;
			for (Row row : sheet) {
				field = null;
				for (Cell c : row) {
					rowIndex = c.getRowIndex();
					cellIndex =c.getColumnIndex();
					if(c == null || c.getCellType() == 

HSSFCell.CELL_TYPE_BLANK){  
	                    continue;//当该单元格为空  
	                }
					String str = ExcelUtil.getCellFormatValue(c).trim

();
					if(rowIndex == 0) {
						if(cellIndex!=0) {
							for(int i=0;i<4;i++) {
								ProjectFinancialBalance p = 

new ProjectFinancialBalance();
								p.setYear((Float.valueOf

(str)).intValue());
								p.setProjectId(projectId);
								p.setCreateTime(new Date

());
								p.setItcode

(user.getEntityCode());
								p.setResourceId

(user.getResId());
								pfbList.add(p);
							}
						}
					}else {
						if(cellIndex == 0) {
							field = ExcelUtil.getField

(ExcelUtil.getCellFormatValue(row.getCell(0)).trim(),ProjectFinancialBalance.class);
							continue;
						}
						if(field!=null && cellIndex!=0) {
							System.out.println(str);
							try {
								ExcelUtil.setValue

(pfbList.get(cellIndex-1), field,str,ProjectFinancialBalance.class);
							} catch (Exception e) {
								e.printStackTrace();
								try {
									throw new 

Exception("ProjectFinancialBalance import excel read error!");
								} catch (Exception e1) {
									e1.printStackTrace

();
								}
							}
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(pfbList!=null && !pfbList.isEmpty()) {
			this.insertBatch(pfbList);
		}
		if(wb!=null) {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
