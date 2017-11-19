package com.zssq.utils.excel.jxl.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.zssq.utils.excel.annotation.AnnotationUtil;
import com.zssq.utils.excel.common.Constant;
import com.zssq.utils.excel.exception.ExcelException;
import com.zssq.utils.excel.jxl.domain.JxlExcel;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class JxlUtil implements Constant {

	/**
	 * 创建Excel
	 * 
	 * @param sheetName
	 *            工作页名称 默认 sheet
	 * @param className
	 *            生成对象名称class
	 * @param con
	 *            excel数据
	 * @param stream
	 *            输入流
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static WritableWorkbook createWorkbook(String sheetName, Class className, Collection<Object> con,
			OutputStream stream) throws IOException, RowsExceededException, WriteException, IllegalArgumentException,
					IllegalAccessException, InstantiationException {

		// 创建Excel
		WritableWorkbook book = Workbook.createWorkbook(stream);
		createSheet(book, sheetName, className, con);
		return book;
	}

	/**
	 * 创建工作页
	 * 
	 * @param book
	 *            Excel
	 * @param sheetName
	 *            工作页名称 默认 sheet
	 * @param className
	 *            生成对象名称class
	 * @param bodyList
	 *            excel数据
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void createSheet(WritableWorkbook book, String sheetName, Class className,
			Collection<Object> bodyList) throws RowsExceededException, WriteException, IllegalArgumentException,
					IllegalAccessException, InstantiationException {

		WritableSheet sheet = book.createSheet((null != sheetName ? sheetName : "sheet"), 0);
		
		
		// 获取当前类中注解
		Map titleMap = AnnotationUtil.getAnnotationTitleMap(className);

		// 判断标题行
		if (null == titleMap || titleMap.size() == NUMBER_ZERO) {
			throw new ExcelException("Title can not be empty,Does not contain ExcelAnnotation!");
		}
		Set<String> key = titleMap.keySet();
		
		
		Label label = null;
		Label rowlabel = null;
		int column = 0;
		
		//把单元格（column, row）到单元格（column1, row1）进行合并。
	    //mergeCells(column, row, column1, row1);
	    sheet.mergeCells(0,0, key.size(),0);//单元格合并方法
	    
	    rowlabel = new Label(0, 0, sheetName,
				JxlExcel.titllCellStyle(null, null, Colour.RED, null, 16));
		sheet.addCell(rowlabel);
		
		// 添加文件头
		for (Iterator it = key.iterator(); it.hasNext();) {
			String title = (String) it.next();
			label = new Label(column, 1, (String) titleMap.get(title),
					JxlExcel.titllCellStyle(null, null, null, null, null));
			sheet.addCell(label);

			if (null != bodyList && bodyList.size() > NUMBER_ZERO) {
				List<BeanMap> bodyMap = AnnotationUtil.getAnnotationData(className, bodyList);
				int row = 2;
				for (BeanMap bean : bodyMap) {
					rowlabel = new Label(column, row, JxlAnalyticalData.analyFieldType(bean.get(title)),
							JxlExcel.titllCellStyle(null, null, Colour.WHITE, null, 10));
					sheet.addCell(rowlabel);
					row++;
				}
			}
			// 设置列的宽度
			sheet.setColumnView(column, 28);
			column++;
		}
	}

	/**
	 * 导出Excel数据
	 * 
	 * @param response
	 *            响应流
	 * @param excelName
	 *            excel名称
	 * @param className
	 *            对象名称
	 * @param dateList
	 *            excel数据
	 */
	public static void exportExcel(HttpServletResponse response, String excelName, Class className,
			List<Object> dateList) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			WritableWorkbook book = createWorkbook(null, className, dateList, os);

			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + excelName + ".xls\"");
			book.write();
			book.close();
		} catch (Exception e) {
			throw new ExcelException("exportExcel method error:" + e.getMessage());
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					throw new ExcelException("exportExcel method error:" + e.getMessage());
				}
			}

		}
	}
}