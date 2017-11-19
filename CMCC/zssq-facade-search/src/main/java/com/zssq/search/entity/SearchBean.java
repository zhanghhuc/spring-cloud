package com.zssq.search.entity;


import com.zssq.solr.basic.SolrQueryBean;

public class SearchBean extends SolrQueryBean {
	
	private String keyword ;

	/** 查询条件-并且,多个  */
	private String conditionAnd ;
	private String conditionOr ;
	private String conditionIn ;
	private String conditionNotin ;
	private String highlightField ;
	private String coreName ;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getConditionAnd() {
		return conditionAnd;
	}
	public String getConditionOr() {
		return conditionOr;
	}
	public String getConditionIn() {
		return conditionIn;
	}
	public String getConditionNotin() {
		return conditionNotin;
	}
	public String getHighlightField() {
		return highlightField;
	}
	public String getCoreName() {
		return coreName;
	}
	public void setConditionAnd(String conditionAnd) {
		this.conditionAnd = conditionAnd;
	}
	public void setConditionOr(String conditionOr) {
		this.conditionOr = conditionOr;
	}
	public void setConditionIn(String conditionIn) {
		this.conditionIn = conditionIn;
	}
	public void setConditionNotin(String conditionNotin) {
		this.conditionNotin = conditionNotin;
	}
	public void setHighlightField(String highlightField) {
		this.highlightField = highlightField;
	}
	public void setCoreName(String coreName) {
		this.coreName = coreName;
	}
	
	
	
}
