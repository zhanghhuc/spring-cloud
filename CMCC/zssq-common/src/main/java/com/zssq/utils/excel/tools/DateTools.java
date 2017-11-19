package com.zssq.utils.excel.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateTools {
	public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		return DateUtils.isSameDay(cal1, cal2);
	}

	public static Date addYears(Date date, int year) {
		return DateUtils.addYears(date, year);
	}

	public static String format(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	public static String formatTime(Date date) {
		return DateFormatUtils.format(date, DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
	}

	public static Date addMonths(Date date, int month) {
		return DateUtils.addMonths(date, month);
	}

	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	public static Date addHours(Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	public static Date addMinutes(Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	public static Date addSeconds(Date date, int amount) {
		return DateUtils.addSeconds(date, amount);
	}

	public static Date addMilliseconds(Date date, int amount) {
		return DateUtils.addMilliseconds(date, amount);
	}

	public static Date setYears(Date date, int amount) {
		return DateUtils.setYears(date, amount);
	}

	public static Date setMonths(Date date, int amount) {
		return DateUtils.setMonths(date, amount);
	}

	public static Date setDays(Date date, int amount) {
		return DateUtils.setDays(date, amount);
	}

	public static Date setHours(Date date, int amount) {
		return DateUtils.setHours(date, amount);
	}

	public static Date setMinutes(Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	public static Calendar toCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static Date round(Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	public static Date getNowWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(5, -1);
		cal.set(7, 2);

		return cal.getTime();
	}

	public static Date getLastWeekMonday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(3, -1);
		cal.set(7, 2);

		return cal.getTime();
	}

	public static Date getNextWeekMonday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(3, 1);
		cal.set(7, 2);

		return cal.getTime();
	}

	public static Date getNowWeekSunday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(3, 1);
		cal.set(7, 1);

		return cal.getTime();
	}

	public static Date getNowWeekSaturday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(3, 1);
		cal.set(7, 7);

		return cal.getTime();
	}

	public static Date getNextWeekSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, 1);
		cal.set(7, 1);

		return cal.getTime();
	}

	public static Date getNextWeekSunday(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, i);
		cal.set(7, 1);

		return cal.getTime();
	}

	public static Date getNextWeekSaturday(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, i);
		cal.set(7, 7);

		return cal.getTime();
	}

	public static Date getNextWeekSaturday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(3, 1);
		cal.set(7, 7);

		return cal.getTime();
	}

	public static Date getLastWeekSunday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(7, 1);

		return cal.getTime();
	}

	public static Date format(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(date);
	}

	public static Date getNowMonthFirstDay(Date date) {
		return DateUtils.setDays(date, 1);
	}

	public static Date getNowMonthLastDay(Date date) {
		Calendar caleandar = Calendar.getInstance(Locale.CHINA);
		caleandar.setTime(DateUtils.setDays(date, 1));
		int value = caleandar.getActualMaximum(5);
		caleandar.set(5, value);
		return caleandar.getTime();
	}

	public static Date getNowBeginMonth(Date date) {
		Calendar caleandar = Calendar.getInstance(Locale.CHINA);
		caleandar.setTime(DateUtils.setDays(date, 1));
		int value = caleandar.getActualMaximum(5);
		caleandar.set(5, value);
		return caleandar.getTime();
	}
}