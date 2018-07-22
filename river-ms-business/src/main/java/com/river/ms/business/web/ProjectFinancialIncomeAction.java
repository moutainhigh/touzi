package com.river.ms.business.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectFinancialIncome;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectFinancialIncomeService;

/**
 * <p>
 * 项目财务数据利润表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectFinancialIncome")
public class ProjectFinancialIncomeAction {

	@Autowired
	MPProjectFinancialIncomeService service;

	/**
	 * 查看项目财务数据利润表
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectFinancialIncome", method = RequestMethod.POST)
	public JsonResult getProjectFinancialIncome(@RequestParam("projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectFinancialIncome> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 导入excel保存数据
	 * 
	 * @param request
	 * @param file
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/import/financialIncomeData", method = RequestMethod.POST)
	public JsonResult importProjectFinancialIncome(HttpServletRequest request,
			@RequestParam("projectFinancialIncomeFile") MultipartFile file, @RequestParam("projectId") Long projectId) {
		try {
			UserEntity user = SessionUtils.getUser(request);
			service.insertBatchByExcelTempl(file, projectId, user);
		} catch (Exception e) {
			return ErrorResult.IMPORT_FINANCIAL_INCOME_DATA_ERROE;
		}
		return JsonResult.SUCCESS;
	}

	/**
	 * 下载模版
	 * 
	 * @param request
	 * @param file
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/download/financialIncomeTemplate", method = RequestMethod.GET)
	public JsonResult downloadProjectFinancialIncomeTemplate(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取跟目录
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			System.out.println("path:" + path.getAbsolutePath());
			String fileName = "财务数据利润表.xls";
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			// 处理下载弹出框名字的编码问题
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			// 利用输入输出流对文件进行下载
			InputStream inputStream = new FileInputStream(new File(path.getAbsolutePath() + "/" + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (Exception e) {
			return ErrorResult.DOWNLOAD_FINANCIAL_INCOME_TEM_ERROR;
		}
		return JsonResult.SUCCESS;
	}
}
