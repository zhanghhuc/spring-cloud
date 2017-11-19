package com.zssq.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 
    * @ClassName: StringTools  
    * @Description: 字符串工具类  
    * @author Mr.K  
    * @date 2016年3月4日  
    *
 */

public final class StringTools {

	/**
	 * 
	    * @Title: isContainsString  
	    * @Description: 判断某个字符串是否包含在某个数组中。如果数组为null则返回false
	    * @param @param str
	    * @param @param array
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isContainsString(String str, String[] array) {
		if (array == null) {
			return false;
		}
		for (String s : array) {
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}

	private StringTools() {
	}

	private static final char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

/**
 * 
    * @Title: subString  
    * @Description: 汉字加英文取整截取 末尾用...填充
    * @param @param t
    * @param @param size
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
 */
	public static String subString(String t, int size) {
		if (t == null)
			return null;
		int hansize = size * 3 / 2;
		int len = hansize;
		if (t.length() > size) {
			int p = 0;
			for (int i = 0; i < hansize && i < t.length(); i++) {
				if (t.charAt(i) > 127)
					p++;
			}
			len -= p * 2 / 3;
			if (len < size)
				len = size;
			if (t.length() <= len)
				return t;
			return t.substring(0, len) + "...";
		}
		return t;
	}

	/**
	 * 
	    * @Title: toHexString  
	    * @Description: 把指定byte数组转换成16进制的字符串
	    * @param @param bytes
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(hex[((b >> 4) & 0xF)]).append(hex[((b >> 0) & 0xF)]);
		}
		return sb.toString();
	}

	/**
	 * 
	    * @Title: hexStringToBytes  
	    * @Description: 把指定16进制的字符串转换成byte数组
	    * @param @param inString
	    * @param @return    参数  
	    * @return byte[]    返回类型  
	    * @throws
	 */
	public static byte[] hexStringToBytes(String inString) {
		int fromLen = inString.length();
		int toLen = (fromLen + 1) / 2;
		final byte[] b = new byte[toLen];
		for (int i = 0; i < toLen; i++) {
			b[i] = (byte) hexPairToInt(inString.substring(i * 2, (i + 1) * 2));
		}
		return b;
	}
	private static int hexPairToInt(String inString) {
		String digits = "0123456789abcdef";
		String s = inString.toLowerCase();
		int n = 0;
		int thisDigit = 0;
		int sLen = s.length();
		if (sLen > 2)
			sLen = 2;
		for (int i = 0; i < sLen; i++) {
			thisDigit = digits.indexOf(s.substring(i, i + 1));
			if (thisDigit < 0)
				throw new NumberFormatException();
			if (i == 0)
				thisDigit *= 0x10;
			n += thisDigit;
		}
		return n;
	}
	
	public static String ArrayToSortString(List<String> totalStringList) {
		StringBuffer str = new StringBuffer("");

		if (totalStringList != null && totalStringList.size() > 0) {
			String[] strs = totalStringList.toArray(new String[totalStringList.size()]);
			Arrays.sort(strs);
			for (String s : strs) {
				str.append(s);
			}
		}
		return str.toString();
	}


	public static int search(String no, String[] noes) {
		for (int i = 0; i < noes.length; i++) {
			if (no.equals(noes[i]))
				return i;
		}
		return -1;
	}

	

	/**
	 * 
	    * @Title: encodeURL  
	    * @Description: 对字符进行URL编码。客户端使用js的decodeURIComponent进行解码
	    * @param @param str
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String encodeURL(String str) {
		try {
			return java.net.URLEncoder.encode(str, "utf-8").replaceAll("\\+", "%20");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 
	    * @Title: decodeURL  
	    * @Description: 对url进行解码
	    * @param @param str
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String decodeURL(String str) {
		try {
			return java.net.URLDecoder.decode(str, "utf-8");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 
	    * @Title: escapeHtml  
	    * @Description: 转换成html代码
	    * @param @param unicode
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static final String escapeHtml(String unicode) {
		return org.apache.commons.lang.StringEscapeUtils.escapeHtml(unicode);
	}

	/**
	 * 
	    * @Title: isEmpty  
	    * @Description: 判断字符串是否为空
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * 
	    * @Title: isNotEmpty  
	    * @Description: 判断字符串是否为非空
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().isEmpty();
	}


	/**
	 * 
	    * @Title: safeToInt  
	    * @Description: 将对象安全转换成int类型，失败时返回0
	    * @param @param o
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	public static int safeToInt(Object o) {
		int rs = 0;
		try {
			rs = Integer.parseInt(o.toString());
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}

	/**
	 * 
	    * @Title: safeToShort  
	    * @Description: 将对象安全转换成short类型
	    * @param @param o
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	public static int safeToShort(Object o) {
		short rs = 0;
		try {
			rs = Short.parseShort(o.toString());
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}

	/**
	 * 
	    * @Title: safeToLong  
	    * @Description: 将对象安全转换成long类型
	    * @param @param o
	    * @param @return    参数  
	    * @return long    返回类型  
	    * @throws
	 */
	public static long safeToLong(Object o) {
		long rs = 0;
		try {
			rs = Long.parseLong(o.toString());
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}

	/**
	 * 
	    * @Title: safeToDouble  
	    * @Description: 将对象安全转换成double类型
	    * @param @param o
	    * @param @return    参数  
	    * @return double    返回类型  
	    * @throws
	 */
	public static double safeToDouble(Object o) {
		double rs = 0;
		try {
			rs = Double.parseDouble(o.toString());
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}

	/**
	 * 
	    * @Title: getTradeSn  
	    * @Description: 得到系统的时间戳
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static Long getTradeSn() {
		return  new java.util.Date().getTime();
	}

	/**
	 * 
	    * @Title: tryParseDouble  
	    * @Description: 尝试将对象转换成double类型，如果失败时也不抛出异常而返回0
	    * @param @param fieldValue
	    * @param @return    参数  
	    * @return double    返回类型  
	    * @throws
	 */
	public static double tryParseDouble(Object fieldValue) {
		try {
			double rs = (Double) fieldValue;
			return rs;
		} catch (Exception ex) {
			try {
				return Double.parseDouble(fieldValue.toString());
			} catch (Exception exx) {
				return 0;
			}
		}
	}


	/**
	 * 
	    * @Title: phoneChange  
	    * @Description: 将手机号码中的中间四位转换成*
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String phoneChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		StringBuffer tempStr = new StringBuffer();
		int srcLength = src.length();
		for (int i = 0; i < srcLength; i++) {
			if (i > 2 && i < 7) {
				tempStr.append("*");
			} else {
				tempStr.append(src.charAt(i));
			}
		}
		return tempStr.toString();
	}

	/**
	 * 
	    * @Title: bankNoChange  
	    * @Description: 将银行卡号限前4后3中间用****填充
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String bankNoChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 3, src.length());
	}

	/**
	 * 
	    * @Title: realNameChange  
	    * @Description: 将真实姓名限前**后1个名字
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String realNameChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return "**" + src.charAt(src.length() - 1);
	}

	/**
	 * 
	    * @Title: PayeeNameChange  
	    * @Description: 将收款人按长度前面用**显示
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String PayeeNameChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		StringBuffer mark = new StringBuffer("");
		if (src.trim().length() > 7) {
			for (int i = 0; i < src.trim().length() - 4; i++) {
				mark.append("*");
			}
			return src.substring(0, 4) + mark;
		}
		if (src.trim().length() > 3) {
			for (int i = 0; i < src.trim().length() - 2; i++) {
				mark.append("*");
			}
			return src.substring(0, 2) + mark;
		}
		if (src.trim().length() > 1) {
			for (int i = 0; i < src.trim().length() - 1; i++) {
				mark.append("*");
			}
			return src.substring(0, 1) + mark;
		}
		return src.substring(0, 1) + "*";
	}

	/**
	 * 
	    * @Title: idCardChange  
	    * @Description:  将身份证号限前4后4中间用****填充
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String idCardChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 4, src.length());
	}

	/**
	 * 
	    * @Title: emailChange  
	    * @Description: 将email限前4后4中间用****填充
	    * @param @param src
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String emailChange(String src) {
		if (src == null || src.trim().length() <= 0) {
			return "";
		}
		return src.substring(0, 4) + "****" + src.substring(src.length() - 4, src.length());
	}

	/**
	 * 
	    * @Title: stringToTrim  
	    * @Description: 去空格
	    * @param @param param
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	public static String stringToTrim(String param) {
		return ValidateUtils.isEmpty(param) ? "" : param.trim();

	}
	/**
	 * 
	    * @Title: isNumeric  
	    * @Description: 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)){
			return false;
		}else{
			return str.matches("\\d*");
		}
	}
	
	/**
	 * 
	    * @Title: strLength  
	    * @Description:  获取字符串长度，当字符串为空时返回0.
	    * @param @param str
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	public static int strLength(String str){
		if (StringUtils.isBlank(str)){
			return 0;
		}else{
			return str.length();
		}
	}
	/**
	 * 
	    * @Title: removeDuplicate  
	    * @Description: 去重
	    * @param @param list
	    * @param @return    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	public static List<Object> removeDuplicate(List<Object> list) {
		HashSet<Object> h = new HashSet<Object>(list);
		list.clear();
		list.addAll(h);
		return list;
	}
	/**
	 * 
	    * @Title: strToObject  
	    * @param     参数  
	    * @return void    返回类型  
	    * @throws
	 */
	public static List<Object> strToObject(List<String> List) {
	    return convert(List, Object.class);
	}
	
	/**
	 * 
	    * @Title: convert  
	    * @param @param list
	    * @param @param c
	    * @param @return    参数  
	    * @return List<T>    返回类型  
	    * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convert(List<?> list, Class<T> c) {
	    return (List<T>)list;
	}
	/**
	 * 
	    * @Title: strLengthCn  
	    * @Description: 获取字符串的长度，如果有中文，则每个中文字符计为3位 ，当字符串为空时返回0.
	    * @param @param str
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	public static int strLengthCn(String str)
	{
		if (StringUtils.isBlank(str)){
			return 0;
		}
		int valueLength = 0;
		final String chinese = "[\u0391-\uFFE5]";
		for (int num = 0; num < str.length(); num++){
			final String temp = str.substring(num, num + 1);
			if (temp.matches(chinese)){
				valueLength += 3;
			} else{
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 
	 * @Title: numRandom
	 * @Description: 生成16位随机数
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String numRandom(){
		String randomStr = System.nanoTime()+"";
		if(randomStr.length()<16){
			randomStr = (int)(Math.random()*10)+""+randomStr;
		}else if(randomStr.length()>16){
			randomStr = randomStr.substring(0, 16);
		}
		return randomStr;
	}

	
	/**
	 * 
	 * @Title: getNumVerificationCode
	 * @Description: 随机生成指定位数的数字验证码
	 * @param @param passwordLength
	 * @param @return
	 * @param @throws Exception    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String getNumVerificationCode(int codeLength) throws Exception {
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		
		int length = array.length;
		if (codeLength <= 0 || codeLength > length) {
			throw new  Exception("system.err.unkown");
		}
		Random random = new Random();
		Integer n = null;
		StringBuilder verificationCode = new StringBuilder();
		for(int i = 0; i < codeLength; i++) {
			n = random.nextInt(length);
			verificationCode.append(array[n]);
		}
		return verificationCode.toString();
	}
	
	
	/**
	 * 
	 * @Title: getStartTime
	 * @Description: 获取系统当天开始时间的时间毫秒值
	 * @param @return    参数
	 * @return Long    返回类型
	 * @throws
	 */
	public static Long getStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

	
	/**
	 * 
	 * @Title: getEndTime
	 * @Description: 获取系统当天结束时间的时间毫秒值
	 * @param @return    参数
	 * @return Long    返回类型
	 * @throws
	 */
	public static Long getEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }
	
	/**
	 * 
	    * @Title: toDate  
	    * @Description: 日期字符串转换成日期
	    * @param @param dateStr
	    * @param @return    参数  
	    * @return Date    返回类型  
	    * @throws
	 */
	public static Date toDate(String dateStr,String pStr){
		Date date= null;
		if(dateStr == null || "".equals(dateStr)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(pStr);//小写的mm表示的是分钟  
		try {
			date=sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	    * @Title: dateToLong  
	    * @Description: 时间装换成long
	    * @param @param date
	    * @param @return    参数  
	    * @return Long    返回类型  
	    * @throws
	 */
	public static Long dateToLong(Date date){
		if(date == null){
			return 0L;
		}
		return date.getTime();
	}
	
	/**
	 * 
	 * @Function formatToString
	 * @Description 传null 返回空串，传基本类型返回字符串形式
	 * @param t
	 * @return
	 */
	public static <T> String formatToString(T t){
		if(t == null){
			return "";
		}
		return t+"";
	}
	
}
