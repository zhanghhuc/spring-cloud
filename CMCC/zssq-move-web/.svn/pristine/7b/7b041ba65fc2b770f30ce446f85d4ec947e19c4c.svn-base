package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
    * @ClassName: Db2AtVo  
    * @Description: AtVo  
    * @author Mr.B  
    * @date 2017年5月19日  
    *
 */
public class Db2AtVo implements RowMapper<Db2AtVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	/** ID **/
	private Integer id;
	/** CREATE_TIME **/
	private Date createTime;
	/** USER_ID **/
	private Integer userId;
	/** MENTION_TYPE **/
	private Byte mentionType;
	/** ITEM_ID **/
	private Integer itemId;
	
	private String orgCode = "";
	private String userCode = "";
	private String mblogCode = "";
	private String tenantCode = "";
	
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMblogCode() {
		return mblogCode;
	}

	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getMentionType() {
		return mentionType;
	}

	public void setMentionType(Byte mentionType) {
		this.mentionType = mentionType;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Override
	public Db2AtVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Db2AtVo vo = new Db2AtVo();
		vo.setId(rs.getInt("ID"));
		vo.setCreateTime(rs.getDate("CREATE_TIME"));
		vo.setMentionType(rs.getByte("MENTION_TYPE"));
		vo.setUserId(rs.getInt("USER_ID"));
		vo.setItemId(rs.getInt("ITEM_ID"));
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
		  .append("ID,CREATE_TIME,USER_ID,MENTION_TYPE,ITEM_ID ")
		  .append("FROM ")
		  .append("mblog.MBLOG_MENTION ")
		  .append("WHERE ")
		  .append("MENTION_TYPE = 1 ");
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
		  .append("ID,CREATE_TIME,USER_ID,MENTION_TYPE,ITEM_ID ")
		  .append("FROM ")
		  .append(" ( ")
		  .append("SELECT ")
		  .append(" * ")
		  .append("FROM ")
		  .append("MBLOG_MENTION ")
		  .append("WHERE ")
		  .append("MENTION_TYPE = 1 ")
		  .append("ORDER BY ID ")
		  .append("FETCH FIRST ")
		  .append(" "+endNum+" ")
		  .append("ROWS ONLY")
		  .append(" ) ")
		  .append("ORDER BY ID DESC")
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
		  .append(" a.ID,a.CREATE_TIME,a.USER_ID,a.MENTION_TYPE,a.ITEM_ID ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" ID,CREATE_TIME,USER_ID,MENTION_TYPE,ITEM_ID, ")
		  .append(" rownumber() over(ORDER BY ID) as rn ")
		  .append(" FROM ")
		  .append(" MBLOG_MENTION ")
		  .append(" WHERE ")
		  .append(" MENTION_TYPE = 1 ")
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
		  .append("mblog_mention ")
		  .append("WHERE ")
		  .append("MENTION_TYPE = 1 ");
		return sb.toString();
		  
	}
	
	/**
	 * 
	    * @Title: getMySqlStatement  
	    * @Description: mysql 查询语句
		* @return String    返回类型
	 */
	public static String getMySqlStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,CREATE_TIME,USER_ID,MENTION_TYPE,ITEM_ID ")
		  .append(" FROM ")
		  .append(" mblog_mention ")
		  .append(" WHERE ")
		  .append("MENTION_TYPE = 1 ")
		  .append(" ORDER BY ID ")
		  .append(" LIMIT ?,? ");
		return sb.toString();
	}

}
