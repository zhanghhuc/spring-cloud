package com.zssq.utils.excel.poi.util;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zssq.utils.excel.common.AnalyticalData;
import com.zssq.utils.excel.common.Constant;
import com.zssq.utils.excel.exception.ExcelException;
import com.zssq.utils.excel.poi.domain.PoiExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zssq.utils.excel.annotation.AnnotationUtil;

import net.sf.cglib.beans.BeanMap;

@SuppressWarnings("all")
public class PoiUtil implements Constant {
 
    /**
     * 创建Excel XLS支持2003
     * @param sheetName sheet名称
     * @param className 对象名称
     * @param con 数据对象
     * @return 2003 Excel
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static HSSFWorkbook createWorkbook(String sheetName,
            Class className, Collection<Object> con)
            throws IllegalArgumentException, IllegalAccessException,
            InstantiationException {
 
        /* 创建Excel */
        HSSFWorkbook hwb = new HSSFWorkbook();
        createSheet(hwb, sheetName, className, con);
        return hwb;
    }
 
    /**
     * 创建Excle sheet页 XLS支持2003
     * @param book 创建book
     * @param sheetName sheet名称
     * @param className 类模版名称
     * @param con 数据流
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void createSheet(HSSFWorkbook book, String sheetName,
            Class className, Collection<Object> con)
            throws IllegalArgumentException, IllegalAccessException,
            InstantiationException {
        /* 创建工作页 判断标题行 */
        HSSFSheet hSSFSheet = book.createSheet(null != sheetName ? sheetName
                : "sheet");
        Map titleMap = AnnotationUtil.getAnnotationTitleMap(className);
        if (null == titleMap || titleMap.size() == NUMBER_ZERO) {
            throw new ExcelException(
                    "Title can not be empty,Does not contain ExcelAnnotation!");
        }
 
        /* 加载第一行 設置第一行的標題頭 */
        Set<String> key = titleMap.keySet();
        HSSFRow hSSFRow = hSSFSheet.createRow(0);
        hSSFRow.setHeight((short) 350);
 
        Map<Integer, Object> positionMap = new HashMap<Integer, Object>(10);
        HSSFCellStyle titleCellStyle = (HSSFCellStyle) PoiExcel.createTitleCellStyle(book);
        HSSFCell cell = null;
        int column = 0;
        /* 添加标题行 */
        for (Iterator it = key.iterator(); it.hasNext();) {
            String title = (String) it.next();
            hSSFSheet.setColumnWidth(column, 5000);
            positionMap.put(column, title);
            cell = hSSFRow.createCell(column);
            cell.setCellStyle(titleCellStyle);
            setTitleCell(cell, (String) titleMap.get(title));
            column++;
        }
 
        /* 增加数据 */
        if (null != con) {
            /* 解析List为数据对象 */
            List<BeanMap> bodyMap = AnnotationUtil.getAnnotationData(className,
                    con);
            BeanMap beanMap = null;
            /* 从第一行开始生成数据 */
            HSSFRow hSSFRowBody = null;
            for (int i = 0; i < bodyMap.size(); i++) {
                hSSFRowBody = hSSFSheet.createRow(i + 1);
                beanMap = bodyMap.get(i);
                Set<Integer> positionKey = positionMap.keySet();
                for (Iterator it = positionKey.iterator(); it.hasNext();) {
                    int num = (Integer) it.next();
                    cell = hSSFRowBody.createCell(num);
                    setTitleCell(cell, AnalyticalData.analyFieldType(beanMap
                            .get((String) positionMap.get(num))));
                }
            }
        }
    }
 
    /**
     * 设置Excel样式
     * @param cell
     * @param value
     */
    private static void setTitleCell(Cell cell, String value) {
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
    }
 
    /**
     * 生成Excel xlsx支持2007
     * @param sheetName sheet名称
     * @param className 对象模版名称
     * @param con 数据流
     * @return Excel
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static XSSFWorkbook createXSSFWorkbook(String sheetName,
            Class className, Collection<Object> con) throws IllegalArgumentException, IllegalAccessException, InstantiationException  {
 
        /* 创建poi */
        XSSFWorkbook hwb = new XSSFWorkbook();
        createXSSFSheet(hwb, sheetName, className, con);
        return hwb;
    }
 
    /**
     * 创建Excle sheet页 xlsx支持2007
     * @param book 创建book
     * @param sheetName    sheet名称
     * @param className 类模版名称
     * @param con  数据流
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void createXSSFSheet(XSSFWorkbook book, String sheetName,
            Class className, Collection<Object> con)
            throws IllegalArgumentException, IllegalAccessException,
            InstantiationException {
        /* 创建工作页 判断标题行 */
        XSSFSheet hSSFSheet = book.createSheet(null != sheetName ? sheetName
                : "sheet");
        Map titleMap = AnnotationUtil.getAnnotationTitleMap(className);
        if (null == titleMap || titleMap.size() == NUMBER_ZERO) {
            throw new ExcelException(
                    "Title can not be empty,Does not contain ExcelAnnotation!");
        }
 
        /* 加载第一行 設置第一行的標題頭 */
        Set<String> key = titleMap.keySet();
        XSSFRow hSSFRow = hSSFSheet.createRow(0);
        hSSFRow.setHeight((short) 350);
 
        Map<Integer, Object> positionMap = new HashMap<Integer, Object>(10);
        CellStyle titleCellStyle = PoiExcel.createTitleCellStyle(book);
        XSSFCell cell = null;
        int column = 0;
        List<Integer> hiddenColumn = new ArrayList<Integer>();
        /* 添加标题行 */
        for (Iterator it = key.iterator(); it.hasNext();) {
            String title = (String) it.next();
            hSSFSheet.setColumnWidth(column, 5000);
            positionMap.put(column, title);
            cell = hSSFRow.createCell(column);
            cell.setCellStyle(titleCellStyle);
            setTitleCell(cell, (String) titleMap.get(title));
            if((title+HIDDEN_FLAG).equals((String)titleMap.get(title))){
            	hiddenColumn.add(column);
            	System.err.println("=====>>>>>");
            }
            column++;
        }
 
        /* 增加数据 */
        if (null != con) {
            /* 解析List为数据对象 */
            List<BeanMap> bodyMap = AnnotationUtil.getAnnotationData(className,
                    con);
            BeanMap beanMap = null;
            /* 从第一行开始生成数据 */
            XSSFRow hSSFRowBody = null;
            for (int i = 0; i < bodyMap.size(); i++) {
                hSSFRowBody = hSSFSheet.createRow(i + 1);
                beanMap = bodyMap.get(i);
                Set<Integer> positionKey = positionMap.keySet();
                for (Iterator it = positionKey.iterator(); it.hasNext();) {
                    int num = (Integer) it.next();
                    cell = hSSFRowBody.createCell(num);
                    setTitleCell(cell, AnalyticalData.analyFieldType(beanMap
                            .get((String) positionMap.get(num))));
                }
            }
            for(Integer hiddenIdx : hiddenColumn){
            	hSSFSheet.setColumnHidden(hiddenIdx, true);
            	System.err.println("=====>>>>>");
            }
        }
    }
 
    /**
     * 导出Excel
     * @param response 输出流
     * @param excelName excel名称
     * @param className 模版名称
     * @param dateList  数据集合
     * @param var 导出版本 true为2003 false 为2007
     */
    public static void exportExcel(HttpServletResponse response,
            String excelName, Class className, List<Object> dateList,
            Boolean var) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            Workbook book = null;
            if(!var){
            	book = createXSSFWorkbook(excelName, className, dateList);
            }else{
            	book = createWorkbook(excelName, className, dateList);
            }
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\""
                    + excelName + ".xls\"");
            book.write(os);
            os.flush();
        } catch (Exception e) {
            throw new ExcelException("exportExcel method error:"
                    + e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new ExcelException("exportExcel method error:"
                            + e.getMessage());
                }
            }
 
        }
    }
    
    /**
     * 导出Excel
     * @param response 输出流
     * @param request 	输入流
     * @param excelName excel名称
     * @param className 模版名称
     * @param dateList  数据集合
     * @param var 导出版本 true为2003 false 为2007
     */
    public static void exportExcel(HttpServletResponse response,
    		HttpServletRequest request,
            String excelName, Class className, List<Object> dateList,
            Boolean var) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String filename  = processFileName(request,excelName);
            Workbook book = null;
            if(!var){
            	book = createXSSFWorkbook(excelName, className, dateList);
            	filename += ".xlsx";
            }else{
            	book = createWorkbook(excelName, className, dateList);
            	filename += ".xls";
            }
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\""
                    + filename + "\"");
            book.write(os);
            os.flush();
        } catch (Exception e) {
            throw new ExcelException("exportExcel method error:"
                    + e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new ExcelException("exportExcel method error:"
                            + e.getMessage());
                }
            }
 
        }
    }
 
    /** 
     *  
     * @Title: processFileName 
     *  
     * @Description: ie,chrom,firfox下处理文件名显示乱码 
     */  
    public static String processFileName(HttpServletRequest request, String fileNames) {  
        String codedfilename = null;  
        try {  
            String agent = request.getHeader("USER-AGENT"); 
            System.out.println(agent);
            
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
                    && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie  
   
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");  
   
                codedfilename = name;  
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
   
   
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return codedfilename;  
    }
}