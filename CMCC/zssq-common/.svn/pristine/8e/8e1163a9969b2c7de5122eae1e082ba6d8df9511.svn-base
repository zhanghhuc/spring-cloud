package com.zssq.utils.excel.jxl.domain;


import com.zssq.utils.excel.common.Constant;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class JxlExcel implements Constant {
	public static WritableCellFormat titllCellStyle(String fontName, Colour fontColour, Colour borderColour,
			Alignment alignmentName, Integer fotSize) throws WriteException {
		WritableFont.FontName _fontName = WritableFont
				.createFont((fontName != null) && (!"".equals(fontName)) ? fontName : "宋体");

		WritableFont font = new WritableFont(_fontName,
				(fotSize == null) || (fotSize.intValue() == 0) ? 13 : fotSize.intValue(), WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, fontColour != null ? fontColour : Colour.BLACK);

		//创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
		WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
		//设置字体格式
		format.setFont(font);
		//设置背景颜色
		format.setBackground(borderColour != null ? borderColour : Colour.YELLOW);
		//设置邊框
		format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		//设置自动换行
		format.setWrap(true);
		//设置文本水平居中对齐
		format.setAlignment(alignmentName != null ? alignmentName : Alignment.CENTRE);
		//设置文本垂直居中对齐
		format.setVerticalAlignment(VerticalAlignment.CENTRE);
		return format;
	}
}