package com.zssq.utils.excel.poi.domain;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiExcel {
	public static CellStyle createTitleCellStyle(Workbook wb) {
		CellStyle titleCellStyle = wb.createCellStyle();
		Font titleFont = wb.createFont();

		titleFont.setFontHeightInPoints((short) 14);
		titleFont.setFontName("宋体");
		titleFont.setColor((short) 9);
		titleFont.setBoldweight((short) 700);

		titleCellStyle.setFont(titleFont);
		titleCellStyle.setAlignment((short) 2);

		titleCellStyle.setFillForegroundColor((short) 17);
		titleCellStyle.setFillPattern((short) 1);

		titleCellStyle.setBorderBottom((short) 1);
		titleCellStyle.setBorderLeft((short) 1);
		titleCellStyle.setBorderRight((short) 1);
		titleCellStyle.setBorderTop((short) 1);

		return titleCellStyle;
	}
}