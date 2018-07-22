package com.river.ms.dfs.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.river.core.controller.ControllerBase;
import com.river.core.helper.ObjectHelper;
import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.core.validator.StringValidator;
import com.river.ms.dfs.entity.Document;
import com.river.ms.dfs.helper.FastDFSUtils;
import com.river.ms.dfs.result.ErrorResult;
import com.river.ms.dfs.service.DocumentService;

/**
 * 
 * @author my
 *
 */
@RestController
@RequestMapping(value = "/document")
public class DocumentController extends ControllerBase<DocumentService, Document> {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	DocumentService currentService;

	@Autowired
	FastDFSUtils fastDFSUtils;

	@Override
	public DocumentService getService() {
		return currentService;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(Document documentEntity, BindingResult res, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * if (res.hasErrors()) { return JsonResult.BindingError(res); } if
		 * (StringValidator.isNullOrEmpty(documentEntity.getEntityCode())) { return
		 * JsonResult.CODE_ISEMPTY; } if (currentService.isExistCode(-1,
		 * documentEntity.getEntityCode())) { return JsonResult.CODE_ISEXIST; }
		 */
		Part file = request.getPart("upload");
		if (file != null && file.getSize() > 0) {
			documentEntity.setSize(String.valueOf(file.getSize()));
			String contentType = file.getContentType();
			documentEntity.setFileType(contentType);
			String submittedFileName = file.getSubmittedFileName();
			documentEntity.setName(submittedFileName);
			StorePath uploadFile = fastDFSUtils.uploadFile(file, null);
			if (uploadFile != null) {
				documentEntity.setVolume(uploadFile.getGroup());
				documentEntity.setPath(uploadFile.getPath());
				documentEntity.setFullPath(uploadFile.getFullPath());
			} else {
				// return ErrorResult.DOCUMENT_FILE_NOT_EXIST;
			}
		} else {
			// return ErrorResult.DOCUMENT_FILE_NOT_EXIST;
		}
		documentEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(documentEntity);
		documentEntity.setEntityId(result);
		logger.debug(documentEntity);
		PrintWriter writer = response.getWriter();
		String callback = request.getParameter("CKEditorFuncNum");
		writer.println("<script type=\"text/javascript\">");
		writer.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "/document/getDocumentById/"
				+ result + "','')");
		writer.println("</script>");
		writer.flush();
		writer.close();
		if (result == 0) {
			// return ErrorResult.DOCUMENT_INSERT_FAIL;
		} else {
			// return Success(documentEntity);
		}
	}

	@RequestMapping(value = "/insertDocument", method = RequestMethod.POST)
	public JsonResult insertDocument(Document documentEntity, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Part file = request.getPart("upload");
		if (file != null && file.getSize() > 0) {
			documentEntity.setSize(String.valueOf(file.getSize()));
			String contentType = file.getContentType();
			documentEntity.setFileType(contentType);
			String submittedFileName = URLDecoder.decode(file.getSubmittedFileName(), "UTF-8");
			documentEntity.setName(submittedFileName);
			StorePath uploadFile = fastDFSUtils.uploadFile(file, null);
			if (uploadFile != null) {
				documentEntity.setVolume(uploadFile.getGroup());
				documentEntity.setPath(uploadFile.getPath());
				documentEntity.setFullPath(uploadFile.getFullPath());
			} else {
				return ErrorResult.DOCUMENT_FILE_NOT_EXIST;
			}
		} else {
			return ErrorResult.DOCUMENT_FILE_NOT_EXIST;
		}
		documentEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(documentEntity);
		documentEntity.setEntityId(result);
		if (result == 0) {
			return ErrorResult.DOCUMENT_INSERT_FAIL;
		} else {
			return Success(documentEntity);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public JsonResult update(@Valid Document documentEntity, BindingResult res,
			@PathVariable(value = "entityId") long entityId) {

		boolean result;
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		if (StringValidator.isEmpty(documentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEMPTY;
		}
		if (currentService.isExistCode(entityId, documentEntity.getEntityCode())) {
			return JsonResult.CODE_ISEXIST;
		}
		Document entity = currentService.getById(entityId);
		if (entity == null) {
			return ErrorResult.DOCUMENT_NOT_EXIST;
		}
		ObjectHelper.Copy(documentEntity, entity);

		result = currentService.update(entity);
		if (result) {
			return Success(entity);
		} else {
			return ErrorResult.DOCUMENT_UPDATE_FAIL;
		}
	}

	/**
	 * 下载
	 * 
	 * @param response
	 * @param entityId
	 * @throws IOException
	 */
	@RequestMapping(value = { "getDocumentById/{entityId}" }, method = { RequestMethod.GET })
	public void download(HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "entityId") long entityId) throws IOException {

		HttpSession session = request.getSession();
		String userAgent = request.getHeader("User-Agent");
		session.setAttribute("documentId", entityId);

		Document document = currentService.getById(entityId);
		DownloadByteArray callback = new DownloadByteArray();
		byte[] downloadFile = fastDFSUtils.downloadFile(document.getVolume(), document.getPath(), callback);
		// response.setContentType("application/force-download");
		// response.addHeader("Content-Disposition","attachment;fileName=" + new
		// String(document.getName().getBytes("utf-8"), "ISO8859-1"));
		String oraFileName = document.getName();
		String formFileName = oraFileName;
		// 针对IE或者以IE为内核的浏览器：
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");
		} else {
			// 非IE浏览器的处理：
			formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + formFileName);
		response.addHeader("Content-Length", "" + downloadFile.length);
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		response.setContentType(document.getFileType());
		OutputStream stream = response.getOutputStream();
		stream.write(downloadFile);
		stream.flush();
		stream.close();
	}

	/**
	 * feign插入数据
	 * 
	 * @param documentEntity
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertDocumentFeign", method = RequestMethod.POST)
	public Long insertDocumentFeign(Document documentEntity, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		documentEntity.setEntityGUID(StringHelper.generateGUID());
		long result = currentService.insert(documentEntity);
		documentEntity.setEntityId(result);
		return result;
	}
}
