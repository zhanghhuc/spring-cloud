package com.zssq.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
    * @ClassName: ValidateUtils  
    * @Description: 校验工具类  
    * @author Mr.K  
    * @date 2016年3月3日  
    *
 */
public class ValidateUtils {
	
	/**
	 * 
	    * @Title: isPassword  
	    * @Description: 判断传入参数是否为字母与数字的组合字符串,而不是单一的字母或者数字
	    * @param @param pwd
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isPassword(String pwd){
		if(ValidateUtils.isInteger(pwd)){//是否全部为数字
			return false;//全部为数字
		}else{
			if(ValidateUtils.isLettersOnly(pwd)){
				return false;//全部为字母
			}else{
				if(ValidateUtils.isAlphanumeric(pwd)){
					return true;//正常数据
				}else{
					return false;//包含了非法数据
				}
			}
		}
	}

	/**
	 * 
	    * @Title: isAlphanumeric  
	    * @Description: 判断字母、数字（适用于密码校验）.
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isAlphanumeric(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isLettersOnly  
	    * @Description: 必须为字母.
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isLettersOnly(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isInteger  
	    * @Description: 判断是否为整数
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isDouble  
	    * @Description: 判断是否为浮点数，包括double和float
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isDoubleAnd2decimals  
	    * @Description: 判断是否为有且只有小数点后面包含两位的数
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isDoubleAnd2decimals(String str) {
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))([.](\\d){1,2})?$");
		return pattern.matcher(str).matches();
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
		if (StringUtils.isBlank(str)) {
			return false;
		} else {
			return str.matches("\\d*");
		}
	}

	/**
	 * 
	    * @Title: isEmail  
	    * @Description: 判断输入的字符串是否符合Email样式.
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  （如果是邮箱就返回真，否则返回假）
	    * @throws
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isChinese  
	    * @Description: 判断输入的字符串是否为纯汉字
	    * @param @param str
	    * @param @return    参数  
	    * @return boolean    返回类型  （如果是纯汉字返回true,否则返回false）
	    * @throws
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	    * @Title: isPrime  
	    * @Description: 判断是否为质数
	    * @param @param x
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7)
				return true;
		}
		int c = 7;
		if (x % 2 == 0)
			return false;
		if (x % 3 == 0)
			return false;
		if (x % 5 == 0)
			return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}

	/**
	 * 
	    * @Title: isMobile  
	    * @Description: 判断是不是合法手机号码
	    * @param @param mobile
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isMobile(String mobile) {
		Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
		return pattern.matcher(mobile).matches();

	}

	/**
	 * 
	    * @Title: isPhone  
	    * @Description: 是否为座机 (010-01001010)
	    * @param @param phone
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("^0[0-9]{2,3}[-|－][0-9]{7,8}([-|－][0-9]{1,4})?$");
		return pattern.matcher(phone).matches();
	}

	/**
	 * 
	    * @Title: isPostCode  
	    * @Description: 是否为邮编
	    * @param @param post
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isPostCode(String post) {
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		return pattern.matcher(post).matches();
	}

	/**
	 * 
	    * @Title: isDate  
	    * @Description: 是否为日期格式：yyyy-MM-dd
	    * @param @param dateStr
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return date != null;
	}

	/**
	 * 
	    * @Title: isTime  
	    * @Description: 是否为时间格式：hh:mm:ss
	    * @param @param timeStr
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isTime(String timeStr) {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		Date date = null;
		try {
			date = df.parse(timeStr);
		} catch (ParseException e) {
			return false;
		}
		return date != null;
	}
	
	/**
	 * 
	    * @Title: isDateTime  
	    * @Description: 是否为日期时间格式：yyyy-MM-dd hh:mm:ss or yyyy-MM-dd hh:mm
	    * @param @param dateTime
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isDateTime(String dateTime) {
		int first = dateTime.indexOf(":");
		int last = dateTime.lastIndexOf(":");
		if (first == -1) {
			return false;
		}
		SimpleDateFormat df = null;
		if (first == last) {
			df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		} else {
			df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}
		Date date = null;
		try {
			date = df.parse(dateTime);
		} catch (ParseException e) {
			return false;
		}
		return date == null;
	}
	

	/**
	 * 
	    * @Title: isURL  
	    * @Description: 是否为url
	    * @param @param url
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isURL(String url) {
		Pattern pattern = Pattern
				.compile("^(https?|ftp):\\/\\/(((([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$");
		return pattern.matcher(url).matches();
	}


	/**
	 * 
	    * @Title: isBankCard  
	    * @Description: 是否为合法的银行卡号
	    * @param @param bankCard
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isBankCard(String bankCard) {
		if (!StringUtils.isBlank(bankCard)) {
			String nonCheckCodeCardId = bankCard.substring(0, bankCard.length() - 1);
			if (nonCheckCodeCardId.matches("\\d+")) {
				char[] chs = nonCheckCodeCardId.toCharArray();
				int luhmSum = 0;
				for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
					int k = chs[i] - '0';
					if (j % 2 == 0) {
						k *= 2;
						k = k / 10 + k % 10;
					}
					luhmSum += k;
				}
				char b = (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
				return bankCard.charAt(bankCard.length() - 1) == b;
			}
		}
		return false;
	}


	/**
	 * 
	    * @Title: checkNotNull  
	    * @Description: 验证对象是否为空
	    * @param @param obj　被验证的对象
	    * @param @param message    异常信息
	    * @return void    返回类型  
	    * @throws
	 */
	public static void checkNotNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	    * @Title: checkNotEmpty  
	    * @Description: 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	    * @param @param obj
	    * @param @param message    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static void checkNotEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message);
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message);
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			throw new IllegalArgumentException(message);
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	    * @Title: isNull  
	    * @Description: 判断参数否非空
	    * @param @param obj
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	    * @Title: isEmpty  
	    * @Description: 判断参数是否非NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	    * @param @param obj
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			return true;
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			return true;
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		}
		return false;
	}

}
