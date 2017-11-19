package com.zssq.vote.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CountResult implements RowMapper<CountResult>, Serializable{

	private static final long serialVersionUID = -8410286511275642762L;
	private Integer count;
	private String id;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public CountResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		CountResult result = new CountResult();
		result.setCount(rs.getInt("count"));
		result.setId(rs.getString("id"));
		return result;
	}
	
}
