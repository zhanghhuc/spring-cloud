package com.zssq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName: TimingJobUtil
 * @Description: 定时任务相关方法
 * @author ZKZ
 * @date 2017年4月27日
 *
 */
public class TimingJobUtil {

	private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

	/**
	 * 
	 * @Title: getCron  
	 * @Description: 根据Date获取cron
	 * @param date
	 * @return: String    返回类型
	 */
	public static String getCron(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
		String formatTimeStr = "";
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/**
	 * 
	 * @Title: getDate  
	 * @Description: 根据cron获取Date
	 * @param cron
	 * @return: Date    返回类型
	 */
	public static Date getDate(String cron) {
		if (cron == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(cron);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

}
