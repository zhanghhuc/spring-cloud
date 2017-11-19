package com.zssq.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * 
    * @ClassName: DataGridResultInfo  
    * @Description: 数据查询列表结果
    * @author Mr.K  
    * @date 2016年3月23日  
    *
 */
public class DataGridResultInfo {
	
	public DataGridResultInfo(){}
	
	//总条数
	private Integer total;
	
	//结果集
	private List rows = new ArrayList();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
