package com.zssq.fastdfs.server;

import com.zssq.fastdfs.web.vo.FileInfoVo;

public interface FileServer {

	/**
	 * 第一断点续传文件
	 * @param file
	 */
	public int FirstAddFile(FileInfoVo file);
	
	/**
	 * 获取当前文件最后一位id
	 * @param file -1找不到文件，
	 */
	public int getFileIndex(FileInfoVo file);
	
	
	/**
	 * 断点续传记录文件
	 * @param index
	 * @param fileId
	 * @return
	 */
	public int FileAppend(int index,String fileId);
	
	
	/**
	 * 文件完整上传完后修改文件状态
	 * @param fileId
	 * @param fileCode
	 * @return
	 */
	public int UpdateFileState(String fileId);
	
}
