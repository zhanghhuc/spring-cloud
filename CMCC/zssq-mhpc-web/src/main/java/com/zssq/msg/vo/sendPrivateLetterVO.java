package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

public class sendPrivateLetterVO {
	
		//发送人Code
		@NotBlank(message = "{empty.message}")
		private String userCode;
		//接收人Code
		@NotBlank(message = "{empty.message}")
		private String receiveUserCode;
		//发送内容，如果是图片则是url地址	
		@NotBlank(message = "{empty.message}")
		private String content;
		//内容类型 1文本私信,2图片私信
		@NotBlank(message = "{empty.message}")
		private String contentType;
		
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
		public String getReceiveUserCode() {
			return receiveUserCode;
		}
		public void setReceiveUserCode(String receiveUserCode) {
			this.receiveUserCode = receiveUserCode;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		
}
