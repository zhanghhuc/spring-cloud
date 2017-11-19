package com.zssq.utils.excel.jxl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zssq.utils.excel.annotation.AnnotationUtil;
import com.zssq.utils.excel.common.AnalyticalData;
import com.zssq.utils.excel.common.Constant;
import com.zssq.utils.excel.exception.ExcelException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class JxlAnalyticalData extends AnalyticalData implements Constant {
	 
    /**
     * 解析Excel
     * 
     * @param book
     * @param className
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static List<Object> AnalyticJxlExcel(Workbook book, Class className)
            throws IllegalArgumentException, IllegalAccessException,
            InstantiationException {
 
        if (null == book) {
            throw new ExcelException("Workbook can not be empty!");
        }
        Map<String, Object> titleMap = AnnotationUtil
                .getTitleValueMap(className);
 
        // 判断标题行
        if (null == titleMap || titleMap.size() == NUMBER_ZERO) {
            throw new ExcelException("Title does not contain ExcelAnnotation!");
        }
        Sheet[] sheets = book.getSheets();
        // 创建存储所有数据的List
        List<Object> allList = new ArrayList<Object>(1000);
        if (sheets != null) {
 
            for (Sheet sheet : sheets) {
                List<Object> list = analyticJxlExcel(sheet, className, titleMap);
                if (list.size() > NUMBER_ZERO) {
                    allList.addAll(list);
                }
            }
        }
        return allList;
    }
 
    /**
     * 按工作页对数据进行解析
     * 
     * @param sheet
     *            工作页
     * @param className
     * @param titleMap
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static List<Object> analyticJxlExcel(Sheet sheet, Class className,
            Map<String, Object> titleMap) throws InstantiationException,
            IllegalAccessException {
 
        List<Object> list = new ArrayList<Object>(100);
        /* 获取第一行标题栏进行验证 */
        Cell[] cells = sheet.getRow(NUMBER_ZERO);
        Map<Integer, Object> positionMap = new HashMap<Integer, Object>(10);
 
        /* 循环判断标题行是否正确 */
        for (Cell cell : cells) {
            if (null != cell) {
                String cellValue = cell.getContents().trim();
                if (null == cellValue || BLANK.equals(cellValue)) {
                    throw new ExcelException(sheet.getName()
                            + "Header line not empty cell!");
                }
                if (null == titleMap.get(cellValue)) {
                    throw new ExcelException(className.getName()
                            + "Object does not contain" + cell.getContents()
                            + "!");
                }
                /* 按顺序存放对应属性 */
                positionMap.put(cell.getColumn(), titleMap.get(cell
                        .getContents()));
            }
        }
 
        /* 循环解析每行数据 */
        for (int i = 1; i < sheet.getRows(); i++) {
            Cell[] rowCell = sheet.getRow(i);
            if (null != rowCell) {
                BeanMap beanMap = BeanMap.create(className.newInstance());
                for (Cell cell : rowCell) {
                    /* 此处对属性类型进行判断 属性如果错误则会抛出异常 */
                    String title = (String) positionMap.get(cell.getColumn());
                    if (null != title) { /* 避免空单元格 */
                        beanMap.put(title, getAttribute(className, title,
                                cell.getContents()));
                    }
                }
                if (null != beanMap) {
                    list.add(beanMap.getBean());
                }
            }
        }
        return list;
    }
 
}