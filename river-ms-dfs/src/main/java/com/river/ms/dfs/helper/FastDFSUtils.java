package com.river.ms.dfs.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultAppendFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.GenerateStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.river.core.helper.FileUtil;

@Component
public class FastDFSUtils {

	@Autowired
	private FastFileStorageClient fastFileStorageClient;

	private TrackerClient trackerClient = new DefaultTrackerClient();

	/**
	 * 断点续传
	 */
	private AppendFileStorageClient appendFileStorageClient = new DefaultAppendFileStorageClient();

	private GenerateStorageClient generateStorageClient = new DefaultGenerateStorageClient();

	/**
	 * 文件上传
	 * 
	 * @param inputStream
	 * @param fileSize
	 * @param fileExtName
	 * @param metaDataSet
	 * @return
	 */
	public StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName, Set<MateData> metaDataSet) {
		StorePath uploadFile = new StorePath();
		synchronized (fastFileStorageClient) {
			uploadFile = fastFileStorageClient.uploadFile(inputStream, fileSize, fileExtName, metaDataSet);
		}
		return uploadFile;
	}

	public StorePath uploadFile(File file, Set<MateData> metaDataSet) throws IOException {
		return uploadFile(new FileInputStream(file), file.length(), FileUtil.getFileLastName(file.getName()),
				metaDataSet);
	}

	public StorePath uploadFile(Part part, Set<MateData> metaDataSet) throws IOException {
		return uploadFile(part.getInputStream(), part.getSize(), FileUtil.getFileLastName(part.getSubmittedFileName()),
				metaDataSet);
	}

	/**
	 * 上传图片并且生成缩略图
	 * 
	 * <pre>
	 * 支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
	 * </pre>
	 * 
	 * @param inputStream
	 * @param fileSize
	 * @param fileExtName
	 * @param metaDataSet
	 * @return
	 */
	public StorePath uploadImageAndCrtThumbImage(InputStream inputStream, long fileSize, String fileExtName,
			Set<MateData> metaDataSet) {
		StorePath uploadFile = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, fileSize, fileExtName,
				metaDataSet);
		return uploadFile;
	}

	public StorePath uploadImageAndCrtThumbImage(File file, Set<MateData> metaDataSet) throws Exception {
		return uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(),
				FileUtil.getFileLastName(file.getName()), null);
	}

	public StorePath uploadImageAndCrtThumbImage(Part part, Set<MateData> metaDataSet) throws Exception {
		return uploadImageAndCrtThumbImage(part.getInputStream(), part.getSize(),
				FileUtil.getFileLastName(part.getSubmittedFileName()), null);
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件路径(groupName/path)
	 */
	public void deleteFile(String fullPath) {
		fastFileStorageClient.deleteFile(fullPath);
	};

	/**
	 * 删除文件
	 * 
	 * @param volume
	 *            组名
	 * @param path
	 *            文件路径
	 */
	public void deleteFile(String volume, String path) {
		fastFileStorageClient.deleteFile(volume, path);
	};
	
	/**
     * 获取文件元信息
     * 
     * @param volume
     * @param path
     * @return
     */
	public Set<MateData> getMetadata(String volume, String path){
		Set<MateData> metadata = fastFileStorageClient.getMetadata(volume, path);
		return metadata;
	};
	
	/**
     * 查看文件的信息
     * 
     * @param volume
     * @param path
     * @return
     */
    public FileInfo queryFileInfo(String volume, String path) {
    	FileInfo queryFileInfo = fastFileStorageClient.queryFileInfo(volume, path);
    	return queryFileInfo;
    };
    
    /**
     * 下载整个文件
     * 
     * @param volume
     * @param path
     * @param callback
     * @return
     */
    public <T> T downloadFile(String volume, String path, DownloadCallback<T> callback) {
    	T downloadFile = fastFileStorageClient.downloadFile(volume, path, callback);
    	return downloadFile;
    };
    
    /**
     * 下载文件片段
     * 
     * @param volume
     * @param path
     * @param fileOffset
     * @param fileSize
     * @param callback
     * @return
     */
    public <T> T downloadFile(String volume, String path, long fileOffset, long fileSize, DownloadCallback<T> callback) {
    	T downloadFile = fastFileStorageClient.downloadFile(volume, path, fileOffset, fileSize, callback);
    	return downloadFile;
    };

}
