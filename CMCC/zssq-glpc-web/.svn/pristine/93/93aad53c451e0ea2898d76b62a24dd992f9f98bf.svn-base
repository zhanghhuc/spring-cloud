package com.zssq.filing.tools;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    
/**  
 * @ClassName: ExcelReader  
 * @Description: 读取Excel表格的功能类,默认excel格式(首行为title行,不允许为空)
 * @author JiPengChun  
 * @date 2017年1月3日  
 *    
 */  
 
public class ExcelReader {

	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";
	
	/**
	 * 
	 * @Title: preReadCheck  
	 * @Description: 检查文件流是否存在
	 * @param @param file
	 * @param @throws Exception    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	private void preReadCheck(InputStream file) throws Exception {
		// 常规检查
		if (file==null) {
			throw new FileNotFoundException("传入的文件不存在：");
		}
	}
	/**
	 * 
	 * @Title: getWorkbook  
	 * @Description: 判断excel类型
	 * @param @param is
	 * @param @param fileType
	 * @param @return
	 * @param @throws Exception    参数  
	 * @return Workbook    返回类型  
	 * @throws
	 */
	public Workbook getWorkbook(InputStream is,String fileType) throws Exception {
		// 检查
		this.preReadCheck(is);
		Workbook workbook = null;
		if (fileType.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (fileType.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}
	/**
	 * @Title: getFileExt  
	 * @Description: 文件名获取文件后缀
	 * @param @param fileName
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public static String getFileExt(String fileName) {
		if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
		}
	}
}