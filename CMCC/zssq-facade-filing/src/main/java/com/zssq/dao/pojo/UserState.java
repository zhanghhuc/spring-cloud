package com.zssq.dao.pojo;

import java.io.Serializable;

public class UserState implements Serializable{

	private static final long serialVersionUID = 1L;

	private Byte isConcerns;//是否关注
	
	private Byte isBlack;//是否拉黑
	
	private Byte isFriend;//是否好友
	
	public Byte getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Byte isFriend) {
		this.isFriend = isFriend;
	}

	public Byte getIsConcerns() {
		return isConcerns;
	}

	public void setIsConcerns(Byte isConcerns) {
		this.isConcerns = isConcerns;
	}

	public Byte getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Byte isBlack) {
		this.isBlack = isBlack;
	}

}
