package com.zssq.dao.pojo;

import java.io.Serializable;

public class UserInfoByName implements Serializable{
	
	private static final long serialVersionUID = 1L;
		private String userCode;
		private String userName;
		private String headPortrait;
		private String introduce;
		private String fullName;
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getHeadPortrait() {
			return headPortrait;
		}
		public void setHeadPortrait(String headPortrait) {
			this.headPortrait = headPortrait;
		}
		public String getIntroduce() {
			return introduce;
		}
		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		
	

}
