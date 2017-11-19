package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogThirdDataVO
 * @Description: kafka
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class BlogThirdDataVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subjectCode; // 内容编号
	private Integer updateNumber; // 修改数量
	private Byte updateClass; // 操作类型
	private Long modifyTime; // 修改时间

	
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

	public Byte getUpdateClass() {
		return updateClass;
	}

	public void setUpdateClass(Byte updateClass) {
		this.updateClass = updateClass;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

}
