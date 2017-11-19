package com.zssq.utils;

import java.security.MessageDigest;

/**
 * 
 * @ClassName: MD5Util
 * @Description: MD5加密工具类
 * @author ChengXiaoQiang
 * @date 2016年4月22日
 *
 */
public class MD5Util {
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 
	 * @Title: byteArrayToHexString
	 * @Description: 将字节数组转换为十六进制字符串
	 * @param @param b
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	
	/**
	 * 
	 * @Title: byteToHexString
	 * @Description: 将字节转换为十六进制字符串
	 * @param @param b
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	
	/**
	 * 
	 * @Title: md5Sign
	 * @Description: MD5加密方法
	 * @param @param origin
	 * @param @param charset
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String md5Sign(String origin, String charset) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charset == null || "".equals(charset))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return resultString;
	}

}
