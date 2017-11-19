package com.zssq.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alipay.api.internal.util.StreamUtil;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.codec.Base64;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class CommonUtil {

	
	/**
	 * 
	 * @Title: byteToStr
	 * @Description: 将字节数组转换为十六进制字符串
	 * @param @param byteArray
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	
	/**
	 * 
	 * @Title: byteToHexStr
	 * @Description: 将字节转换为十六进制字符串
	 * @param @param bytes
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String byteToHexStr(byte bytes) {

		StringBuilder stringBuilder = new StringBuilder("");
		int v = bytes & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
		
		return stringBuilder.toString();

	}
	
	
	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	
	/**
	 * 
	 * @Title: payInfoToXML
	 * @Description: 对象转换成xml
	 * @param @param pi
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String payInfoToXML(Object pi) {
		xstream.alias("xml", pi.getClass());
		String xml = xstream.toXML(pi);
		return xml;
	}
	
	
	/**
	 * 
	 * @Title: parseXml
	 * @Description: xml数据转map集合
	 * @param @param xml
	 * @param @return
	 * @param @throws Exception    参数
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xml) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		return map;
	}
	
	
	/**
	 * 
	 * @Title: httpsRequest
	 * @Description: 发起请求
	 * @param @param requestUrl
	 * @param @param requestMethod
	 * @param @param output
	 * @param @return
	 * @param @throws Exception    参数
	 * @return StringBuffer    返回类型
	 * @throws
	 */
	private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output) throws Exception {
		URL url = new URL(requestUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}
		// 从输入流读取返回内容
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	}
	
	
	/**
	 * 
	 * @Title: httpsRequestToXML
	 * @Description: 将请求响应的xml转换为map并返回
	 * @param @param requestUrl
	 * @param @param requestMethod
	 * @param @param outputStr
	 * @param @return    参数
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	public static Map<String, String> httpsRequestToXML(String requestUrl, String requestMethod, String outputStr) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
			result = parseXml(buffer.toString());
		} catch (ConnectException ce) {
			System.err.println("连接超时：" + ce.getMessage());
		} catch (Exception e) {
			System.err.println("https请求异常：" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getIpAddr
	 * @Description: 获取ip地址
	 * @param @param request
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
	
	/**
	 * 
	 * @Title: getFormatedDateString
	 * @Description: 获取标准北京时间，时区为东八区(timeZoneOffset:8)
	 * @param @param timeZoneOffset
	 * @param @return    参数
	 * @return long    返回类型
	 * @throws
	 */
	public static long getFormatedDateString(float timeZoneOffset) {
		try {
			if (timeZoneOffset > 13 || timeZoneOffset < -12) {
				timeZoneOffset = 0;
			}

			int newTime = (int) (timeZoneOffset * 60 * 60 * 1000);
			TimeZone timeZone;
			String[] ids = TimeZone.getAvailableIDs(newTime);
			if (ids.length == 0) {
				timeZone = TimeZone.getDefault();
			} else {
				timeZone = new SimpleTimeZone(newTime, ids[0]);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			sdf.setTimeZone(timeZone);
			
			long millionSeconds = simpleDateFormat.parse(sdf.format(new Date())).getTime() / 1000;
			
			return millionSeconds;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0l;
		}
	}
	
	
	/**
	 * 
	 * @Title: rsaSign
	 * @Description: RSA签名
	 * @param @param content		待签名的字符串
	 * @param @param privateKey		rsa私钥字符串
	 * @param @param charset		字符编码
	 * @param @return
	 * @param @throws SignatureException    签名失败则抛出异常
	 * @return String    签名结果
	 * @throws
	 */
	public static String rsaSign(String content, String privateKey, String charset) throws SignatureException {
		try {

			PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));

			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(priKey);
			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();
			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
			throw new SignatureException("RSAcontent = " + content + "; charset = " + charset, e);
		}
	}

	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if (ins == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		byte[] encodedKey = StreamUtil.readText(ins).getBytes();
		encodedKey = Base64.decodeBase64(encodedKey);
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}
	
    
	public static String getSignContent(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) sortedParams.get(key);
			if (StringUtils.areNotEmpty(new String[] { key, value })) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return content.toString();
	}
	
	
	
	
	/**
	 * 
	 * @Title: parseXml
	 * @Description: 解析request发过来的请求，并转换成一个map集合。
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    参数
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	public static SortedMap<String, String> parseXml(HttpServletRequest request) throws Exception {

		// 解析结果存储在SortedMap(按照参数名ASCII码从小到大排序（字典序）的一个集合)
		SortedMap<String, String> parameterMap = new TreeMap<String, String>();
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			parameterMap.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return parameterMap;
	}
	

	
	
	/**
	 * 返回处理结果给财付通服务器。
	 * @param msg: Success or fail。
	 * @throws IOException 
	 */
	public static void sendToCFT(String msg, HttpServletResponse response) throws IOException {
		String strHtml = msg;
		PrintWriter out = response.getWriter();
		out.println(strHtml);
		out.flush();
		out.close();
	}
}
