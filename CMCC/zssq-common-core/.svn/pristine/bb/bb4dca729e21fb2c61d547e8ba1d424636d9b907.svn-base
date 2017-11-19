package com.zssq.push.bean;

import java.util.Map;

public class IOSNoticeBean {

	// 其内容可以为字符串或者字典，如果是字符串，那么将会在通知中显示这条内容
	private String alert;
	// 指定通知展现时伴随的提醒音文件名。如果找不到指定的文件或者值为 default，
	// 那么默认的系统音将会被使用。如果为空，那么将没有声音;
	private String sound;
	// 其值为数字，表示当通知到达设备时，应用的角标变为多少。如果没有使用这个字段，
	// 那么应用的角标将不会改变。设置为 0 时，会清除应用的角标;
	private String badge;
	// 用户自定义参数
	private Map<String, String> customParam;
	
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public Map<String, String> getCustomParam() {
		return customParam;
	}
	public void setCustomParam(Map<String, String> customParam) {
		this.customParam = customParam;
	}
	
}
