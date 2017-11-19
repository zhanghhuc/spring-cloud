package com.zssq.utils.excel.poi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zssq.utils.excel.common.Constant;
import com.zssq.utils.excel.exception.ExcelException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zssq.utils.excel.annotation.AnnotationUtil;
import com.zssq.utils.excel.common.AnalyticalData;

import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class PoiAnalyticalData extends AnalyticalData implements Constant {
	public static List<Object> AnalyticPoiExcel(Workbook book, Class className)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		if (book == null) {
			throw new ExcelException("Workbook can not be empty!");
		}
		Map titleMap = AnnotationUtil.getTitleValueMap(className);

		if ((titleMap == null) || (titleMap.size() == 0)) {
			throw new ExcelException("Title does not contain ExcelAnnotation!");
		}

		List allList = new ArrayList(1000);

		int sheetSize = book.getNumberOfSheets();
		if (sheetSize > 0) {
			for (int i = 0; i < sheetSize; i++) {
				List sheetList = analyticPoiExcel(book.getSheetAt(i), className, titleMap);
				allList.addAll(sheetList);
			}
		}

		return allList;
	}

	public static List<Object> analyticPoiExcel(Sheet sheet, Class className, Map<String, Object> titleMap)
			throws InstantiationException, IllegalAccessException {
		List list = new ArrayList(100);

		Row row = sheet.getRow(0);
		Map positionMap = new HashMap(10);

		for (Cell cell : row) {
			if (cell != null) {
				String cellValue = cell.getStringCellValue();
				if ((cellValue == null) || ("".equals(cellValue))) {
					throw new ExcelException(sheet.getSheetName() + "Header line not empty cell!");
				}
				if (titleMap.get(cellValue) == null) {
					throw new ExcelException(className.getName() + "Object does not contain" + cellValue + "!");
				}

				positionMap.put(Integer.valueOf(cell.getColumnIndex()), titleMap.get(cellValue));
			}

		}

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			Row rowCell = sheet.getRow(i);
			if (rowCell != null) {
				BeanMap beanMap = BeanMap.create(className.newInstance());
				for (Cell cell : rowCell) {
					String title = (String) positionMap.get(Integer.valueOf(cell.getColumnIndex()));
					if (title != null) {
						String content = "";
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							if(HSSFDateUtil.isCellDateFormatted(cell)){
								SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
								content = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
							}else{
								HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
								content = dataFormatter.formatCellValue(cell);
							}
						}else{
							content = cell.getStringCellValue();
						}
						beanMap.put(title, getAttribute(className, title, content));
					}
				}
				if (beanMap != null) {
					list.add(beanMap.getBean());
				}
			}
		}
		return list;
	}
}