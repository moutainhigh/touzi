package com.river.ms.dfs.entity;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 文件表
 * 
 * @author my
 *
 */
@RiverTable(Name = "dfs_document")
public class Document extends EntityBase {

	/**
	 * 文件类型ID
	 */
	@RiverColumn
	public long documentTypeId;

	/**
	 * 文件名称
	 */
	@RiverColumn
	public String name;

	@RiverColumn
	public String fileType;

	/**
	 * 文件保存的组名
	 */
	@RiverColumn
	public String volume;

	/**
	 * 文件的保存路径
	 */
	@RiverColumn
	public String path;

	/**
	 * 文件的保存全路径
	 */
	@RiverColumn
	public String fullPath;
	
	/**
	 * 文件的大小
	 */
	@RiverColumn
	public String size;

	public long getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
