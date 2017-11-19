package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
    * @ClassName: Db2TopicVo  
    * @Description: 话题VO  
    * @author Mr.B  
    * @date 2017年5月19日  
    *
 */
public class Db2TopicVo implements RowMapper<Db2TopicVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;
	/** ID **/
	private Integer id;
	/** TOPIC_NAME **/
	private String topicName;
	/** CREATE_TIME **/
	private Date createTime;
	/** JOIN_NUM **/
	private Integer joinNum;
	/** USER_ID **/
	private Integer userId;
	
	private String orgCode = "";
	private String tenantCode = "";
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	@Override
	public Db2TopicVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Db2TopicVo topic = new Db2TopicVo();
		topic.setId(rs.getInt("ID"));
		topic.setJoinNum(rs.getInt("JOIN_NUM"));
		topic.setCreateTime(rs.getDate("CREATE_TIME"));
		topic.setTopicName(rs.getString("TOPIC_NAME"));
		topic.setUserId(rs.getInt("USER_ID"));
		return topic;
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
		  .append("ID,CREATE_TIME,TOPIC_NAME,JOIN_NUM,USER_ID ")
		  .append("FROM ")
		  .append("mblog.MBLOG_TOPIC ");
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
		  .append("ID,CREATE_TIME,TOPIC_NAME,JOIN_NUM,USER_ID ")
		  .append("FROM ")
		  .append(" ( ")
		  .append("SELECT ")
		  .append(" * ")
		  .append("FROM ")
		  .append("mblog.MBLOG_TOPIC ")
		  .append("ORDER BY ID ")
		  .append("FETCH FIRST ")
		  .append(" "+endNum+" ")
		  .append("ROWS ONLY")
		  .append(" ) ")
		  .append("ORDER BY ID DESC ")
		  .append("FETCH FIRST ")
		  .append(" "+pageSize+" ")
		  .append("ROWS ONLY");
		return sb.toString();
	}

	/**
	 * 
	    * @Title: getRowPageStatement  
	    * @Description: rownum实现
		* @return String    返回类型
	 */
	public static String getRowPageStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,CREATE_TIME,TOPIC_NAME,JOIN_NUM,USER_ID ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" ID,CREATE_TIME,TOPIC_NAME,JOIN_NUM,USER_ID,   ")
		  .append(" rownumber() over(ORDER BY ID) as rn ")
		  .append(" FROM ")
		  .append(" mblog.MBLOG_TOPIC ")
		  .append(" ) AS a ")
		  .append(" WHERE ")
		  .append(" a.rn BETWEEN ? AND ? ");
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
		  .append("mblog_topic ");
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
		  .append(" ID,CREATE_TIME,TOPIC_NAME,JOIN_NUM,USER_ID ")
		  .append(" FROM ")
		  .append(" mblog_topic ")
		  .append(" ORDER BY ID ")
		  .append(" LIMIT ?,? ");
		return sb.toString();
	}
}
