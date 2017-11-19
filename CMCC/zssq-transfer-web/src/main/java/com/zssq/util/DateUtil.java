package com.zssq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.zssq.utils.StringTools;

public class DateUtil {
	
	public static final SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式时间转换为long毫秒值
	 * @Function convertStrToLong
	 * @Description 
	 * @param strTime
	 * @return
	 */
	public static long convertStrToLong(String strTime){
		long l = 0l;
		if (StringTools.isEmpty(strTime)) {
			return l;
		}
		try {
			l = formatTimestamp.parse(strTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public static void main(String[] args) {
		System.out.println(convertStrToLong("2017-04-18 11:38:57"));
		System.out.println(String.valueOf(null));
	}

}
