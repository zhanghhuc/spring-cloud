package com.zssq.push.bean;

import net.sf.json.JSONObject;
/**
 * Android通知bean
 * @author power
 *
 */
public class AndroidNoticeBean {
	/*
	{  
	    "title" : "hello" ,  
	    "description": "hello world" //必选  
	    "notification_builder_id": 0, //可选  
	    "notification_basic_style": 7, //可选  
	    "open_type":0, //可选  
	    "url": "http://developer.baidu.com", //可选  
	    "pkg_content":"", //可选  
	    "custom_content":{"key":"value"},  
	}*/

	// 通知标题，可以为空；如果为空则设为appid对应的应用名;
	private String title;
	// 通知文本内容，不能为空;
	private String description;
	// android客户端自定义通知样式，如果没有设置默认为0;
	private int notificationBuilderId = 0;
	// 只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：0x01;),
	// 这是一个flag整形，每一位代表一种样式,如果想选择任意两种或三种通知样式，notification_basic_style的值即为对应样式数值相加后的值。
	private int notificationBasicStyle;
	// 点击通知后的行为(1：打开Url; 2：自定义行为；); open_type = 1，url != null：打开网页；
	// open_type = 2，pkg_content = null：直接打开应用； open_type = 2，
	// pkg_content != null：自定义动作打开应用
	private int openType;
	// 需要打开的Url地址，open_type为1时才有效;
	private String url;
	// open_type为2时才有效，Android端SDK会把pkg_content字符串转换成Android
	// Intent,通过该Intent打开对应app组件，
	// 所以pkg_content字符串格式必须遵循Intent uri格式，最简单的方法可以通过Intent方法toURI()获取
	private String pkgContent;
	// 自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递
	private JSONObject customContent;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNotificationBuilderId() {
		return notificationBuilderId;
	}
	public void setNotificationBuilderId(int notificationBuilderId) {
		this.notificationBuilderId = notificationBuilderId;
	}
	public int getNotificationBasicStyle() {
		return notificationBasicStyle;
	}
	public void setNotificationBasicStyle(int notificationBasicStyle) {
		this.notificationBasicStyle = notificationBasicStyle;
	}
	public int getOpenType() {
		return openType;
	}
	public void setOpenType(int openType) {
		this.openType = openType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPkgContent() {
		return pkgContent;
	}
	public void setPkgContent(String pkgContent) {
		this.pkgContent = pkgContent;
	}
	public JSONObject getCustomContent() {
		return customContent;
	}
	public void setCustomContent(JSONObject customContent) {
		this.customContent = customContent;
	}
	
}
