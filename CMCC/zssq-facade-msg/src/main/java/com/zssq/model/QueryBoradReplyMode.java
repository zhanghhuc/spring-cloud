package com.zssq.model;

import java.io.Serializable;
import java.util.List;


public class QueryBoradReplyMode implements Serializable{

	private int pageSize;
	
	private int pageNo;
	
	private List<String> list;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
}
