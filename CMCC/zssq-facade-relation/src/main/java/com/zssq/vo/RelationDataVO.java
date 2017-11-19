package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationDataVO  
 * @Description: 数据参数  
 * @author ZKZ  
 * @date 2017年3月30日  
 *
 */
public class RelationDataVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String subjectCode; // 内容编号
	/**
	 * 添加量（或减少量：例：-1 or 1）
	 */
	private Integer updateNumber; // 修改数量
	private Long modifyTime;// 修改时间
	/**
	 * 修改班组置精次数:1;
	 * 修改集团级别置精次数:2;
	 * 修改省级别置精次数:3;
	 * 修改市级别置精次数:4;
	 * 修改参与量:5;
	 * 修改浏览量:6;
	 * 修改班点赞量:7;
	 * 修改收藏量:8;
	 * 修改转发量:9;
	 * 修改评论量:10;
	 * 修改分享量:11;
	 */
	private Byte updateClass;// 修改类目
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Integer getUpdateNumber() {
		return updateNumber;
	}

	public void setUpdateNumber(Integer updateNumber) {
		this.updateNumber = updateNumber;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Byte getUpdateClass() {
		return updateClass;
	}

	public void setUpdateClass(Byte updateClass) {
		this.updateClass = updateClass;
	}

	
}
