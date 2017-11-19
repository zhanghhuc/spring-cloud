package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("unused")
public class TransferTaskVo implements RowMapper<TransferTaskVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	private String taskName;
	/** 1：迁移原创微博，2：迁移转发微博，3：迁移评论信息，4：迁移at信息，5：迁移话题信息，6：迁移订阅  7：迁移转发历程信息**/
	private Byte taskType;
	/** 1:未开始，2：进行中，3：完成，4：失败 **/
	private Byte taskStatus;
	private Integer pageNo = 0;
	private Integer totalPage = 0;
	private Integer totalCount = 0;
	private Long createTime = new Date().getTime();
	private Long startTime = 0L;
	private Long endTime = 0L;
	private Long failTime = 0L;
	 
	
	private String taskStatusView;
	
	public String getTaskStatusView(){
		String result = "";
		switch(taskStatus){
			case TransferTaskConstants.TASK_STATUS_NEW:{
				result = "新任务";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_RUNING:{
				result = "进行中";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_END:{
				result = "成功";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_FAIL:{
				result = "失败";
				break;
			}
		}
		return result;
	}
	public void setTaskStatusView(String taskStatusView) {
		this.taskStatusView = taskStatusView;
	}
	
	
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getFailTime() {
		return failTime;
	}
	public void setFailTime(Long failTime) {
		this.failTime = failTime;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public Byte getTaskType() {
		return taskType;
	}

	public void setTaskType(Byte taskType) {
		this.taskType = taskType;
	}

	public Byte getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Byte taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * 
	    * @Title: getInitTaskList  
	    * @Description: 获取初始化列表
		* @return List<TransferTaskVo>    返回类型
	 */
	public static List<TransferTaskVo> getInitTaskList(){
		List<TransferTaskVo> taskList = new ArrayList<TransferTaskVo>();
		
		TransferTaskVo vo1 = new TransferTaskVo();
		vo1.setTaskName("迁移原创微博");
		vo1.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo1.setTaskType(TransferTaskConstants.TASK_TYPE_SELF);
		taskList.add(vo1);
		
		TransferTaskVo vo2 = new TransferTaskVo();
		vo2.setTaskName("迁移转发微博");
		vo2.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo2.setTaskType(TransferTaskConstants.TASK_TYPE_FOR);
		taskList.add(vo2);
		
		
		TransferTaskVo vo3 = new TransferTaskVo();
		vo3.setTaskName("迁移微博评论");
		vo3.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo3.setTaskType(TransferTaskConstants.TASK_TYPE_COMMENT);
		taskList.add(vo3);
		
		TransferTaskVo vo4 = new TransferTaskVo();
		vo4.setTaskName("迁移微博AT");
		vo4.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo4.setTaskType(TransferTaskConstants.TASK_TYPE_AT);
		taskList.add(vo4);
		
		
		TransferTaskVo vo5 = new TransferTaskVo();
		vo5.setTaskName("迁移微博话题");
		vo5.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo5.setTaskType(TransferTaskConstants.TASK_TYPE_TOPIC);
		taskList.add(vo5);
		
		
		TransferTaskVo vo6 = new TransferTaskVo();
		vo6.setTaskName("迁移微博订阅");
		vo6.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo6.setTaskType(TransferTaskConstants.TASK_TYPE_SUB);
		taskList.add(vo6);
		
		TransferTaskVo vo7 = new TransferTaskVo();
		vo7.setTaskName("迁移转发历程");
		vo7.setTaskStatus(TransferTaskConstants.TASK_STATUS_NEW);
		vo7.setTaskType(TransferTaskConstants.TASK_TYPE_FOR_PRO);
		taskList.add(vo7);
		
		return taskList;
	}
	
	/**
	 * 
	    * @Title: getInsertStatement  
	    * @Description: 获取插入语句
		* @return String    返回类型
	 */
	public static String getInsertStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO transfer_task ")
		  .append(" (task_name,task_type,task_status,page_no,total_page,total_count,create_time,start_time,end_time,fail_time) ")
		  .append(" VALUES ")
		  .append(" (?,?,?,?,?,?,?,?,?,?) ");
		return sb.toString();
		  
	}
	
	/**
	 * 
	    * @Title: getSelectStatement  
	    * @Description: 获取查询语句
		* @return String    返回类型
	 */
	public static String getSelectStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" task_name,task_type,task_status,page_no,total_page,total_count,create_time,start_time,end_time,fail_time ")
		  .append(" FROM ")
		  .append(" transfer_task ");
		return sb.toString();
		  
	}
	
	/**
	 * 
	    * @Title: getCreateStatement  
	    * @Description:获取创建语句
		* @return String[]    返回类型
	 */
	public static String[] getCreateStatement(){
		String[] statement = new String[2];
		StringBuilder sb = new StringBuilder();
		sb.append(" CREATE TABLE transfer_task ")
			.append(" ( ")
			.append(" id bigint(20) NOT NULL AUTO_INCREMENT, ")
			.append(" task_name varchar(50) NOT NULL, ")
			.append(" task_type tinyint(2) NOT NULL, ")
			.append(" task_status tinyint(2) NOT NULL, ")
			.append(" page_no int(11) NOT NULL, ")
			.append(" total_page int(11) NOT NULL, ")
			.append(" total_count int(11) NOT NULL, ")
			.append(" create_time bigint(20) NOT NULL, ")
			.append(" start_time bigint(20) NOT NULL, ")
			.append(" end_time bigint(20) NOT NULL, ")
			.append(" fail_time bigint(20) NOT NULL, ")
			.append(" PRIMARY KEY (id) ")
			.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8; ");
		statement[0] = " DROP TABLE IF EXISTS transfer_task ";
		statement[1] = sb.toString();
		return statement;
	}
	
	@Override
	public TransferTaskVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransferTaskVo vo = new TransferTaskVo();
		vo.setTaskName(rs.getString("task_name"));
		vo.setTaskStatus(rs.getByte("task_status"));
		vo.setTaskType(rs.getByte("task_type"));
		vo.setPageNo(rs.getInt("page_no"));
		vo.setTotalPage(rs.getInt("total_page"));
		vo.setTotalCount(rs.getInt("total_count"));
		
		vo.setCreateTime(rs.getLong("create_time"));
		vo.setStartTime(rs.getLong("start_time"));
		vo.setEndTime(rs.getLong("end_time"));
		vo.setFailTime(rs.getLong("fail_time"));
		return vo;
	}

}
