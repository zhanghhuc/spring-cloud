package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
    * @ClassName: Db2SubVo  
    * @Description: 订阅VO  
    * @author Mr.B  
    * @date 2017年5月19日  
    *
 */
public class Db2SubVo implements RowMapper<Db2SubVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	/** ID **/
	private String id;
	/** CREATE_TIME **/
	private Date createTime;
	/** SUBSCRIBE_USER **/
	private Integer subscribeUser;
	/** PUBLISH_USER **/
	private Integer publishUser;
	
	private String userCode = "";
	private String orgCode = "";
	private String subUserCode = "";
	private String subOrgCode = "";
	private String tenantCode = "";
	
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSubUserCode() {
		return subUserCode;
	}

	public void setSubUserCode(String subUserCode) {
		this.subUserCode = subUserCode;
	}

	public String getSubOrgCode() {
		return subOrgCode;
	}

	public void setSubOrgCode(String subOrgCode) {
		this.subOrgCode = subOrgCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSubscribeUser() {
		return subscribeUser;
	}

	public void setSubscribeUser(Integer subscribeUser) {
		this.subscribeUser = subscribeUser;
	}

	public Integer getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(Integer publishUser) {
		this.publishUser = publishUser;
	}

	@Override
	public Db2SubVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Db2SubVo vo = new Db2SubVo();
		vo.setId(rs.getString("ID"));
		vo.setCreateTime(rs.getDate("CREATE_TIME"));
		vo.setPublishUser(rs.getInt("PUBLISH_USER"));
		vo.setSubscribeUser(rs.getInt("SUBSCRIBE_USER"));
		return vo;
	}
	
	
	
	
	/**
	 * 
	    * @Title: getSelectStatement  
	    * @Description: 获取查询语句
	    * @return
		* @return String    返回类型
	 */
	public static String getSelectStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ")
		  .append("ID,CREATE_TIME,SUBSCRIBE_USER,PUBLISH_USER ")
		  .append("FROM ")
		  .append("MS_SUBSCRIBE_RELATION ")
		  .append("WHERE ")
		  .append("TYPE = 13");
		return sb.toString();
		  
	}
	
	/**
	 * 
	    * @Title: getPageStatement  
	    * @Description: 获取分页数据
	    * @param endNum	结束
	    * @param pageSize	每页条数
		* @return String    返回类型
	 */
	public static String getPageStatement(int endNum,int pageSize){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ")
		  .append("ID,CREATE_TIME,SUBSCRIBE_USER,PUBLISH_USER ")
		  .append("FROM ")
		  .append(" ( ")
		  .append("SELECT ")
		  .append(" * ")
		  .append("FROM ")
		  .append("MS_SUBSCRIBE_RELATION ")
		  .append("WHERE ")
		  .append("TYPE = 13 ")
		  .append("ORDER BY CREATE_TIME ")
		  .append("FETCH FIRST ")
		  .append(" "+endNum+" ")
		  .append("ROWS ONLY")
		  .append(" ) ")
		  .append("ORDER BY CREATE_TIME DESC ")
		  .append("FETCH FIRST ")
		  .append(" "+pageSize+" ")
		  .append("ROWS ONLY");
		return sb.toString();
	}

	/**
	 * 
	    * @Title: getRowPageStatement  
	    * @Description: rowNum实现
		* @return String    返回类型
	 */
	public static String getRowPageStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,CREATE_TIME,SUBSCRIBE_USER,PUBLISH_USER ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" ID,CREATE_TIME,SUBSCRIBE_USER,PUBLISH_USER,  ")
		  .append(" rownumber() over(ORDER BY CREATE_TIME) as rn ")
		  .append(" FROM ")
		  .append(" MS_SUBSCRIBE_RELATION ")
		  .append(" WHERE ")
		  .append(" TYPE = 13 ")
		  .append(" ) AS a ")
		  .append(" WHERE ")
		  .append(" a.rn BETWEEN ? AND ? ");
		return sb.toString();
	}
	
	/**
	 * 
	    * @Title: getMySqlStatement  
	    * @Description: mysql查询语句
		* @return String    返回类型
	 */
	public static String getMySqlStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,CREATE_TIME,SUBSCRIBE_USER,PUBLISH_USER ")
		  .append(" FROM ")
		  .append(" ms_subscribe_relation ")
		  .append(" WHERE ")
		  .append(" TYPE = 13 ")
		  .append(" ORDER BY ID ")
		  .append(" LIMIT ?,? ");
		return sb.toString();
	}
	
	/**
	 * 
	    * @Title: getCountStatement  
	    * @Description: 获取查询语句
	    * @return
		* @return String    返回类型
	 */
	public static String getCountStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ")
		  .append("COUNT(ID) ")
		  .append("FROM ")
		  .append("ms_subscribe_relation ")
		  .append("WHERE ")
		  .append("TYPE = 13");
		return sb.toString();
		  
	}
}
