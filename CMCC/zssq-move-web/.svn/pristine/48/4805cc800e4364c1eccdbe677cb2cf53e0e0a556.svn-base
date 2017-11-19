package com.zssq.mblog.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.mblog.vo.MysqlMblogIdCodeVo;
import com.zssq.mblog.vo.TransferTaskConstants;
import com.zssq.mblog.vo.TransferTaskVo;

@Service
@DataSource(DataSourceConstants.MYSQL_MBLOG)
public class MblogTaskService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	    * @Title: initTask  
	    * @Description: 出事话任务信息
		* @return void    返回类型
	 */
	public void initTask(){
		// 创建中间表
		jdbcTemplate.batchUpdate(MysqlMblogIdCodeVo.getCreateStatement());
		// 创建任务表
		jdbcTemplate.batchUpdate(TransferTaskVo.getCreateStatement());
		// 获取初始化任务列表
		final List<TransferTaskVo> taskList = TransferTaskVo.getInitTaskList();
		// 批量插入	
		jdbcTemplate.batchUpdate(TransferTaskVo.getInsertStatement(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return taskList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, taskList.get(i).getTaskName());
				ps.setByte(2, taskList.get(i).getTaskType());
				ps.setByte(3, taskList.get(i).getTaskStatus());
				ps.setInt(4, taskList.get(i).getPageNo());
				ps.setInt(5, taskList.get(i).getTotalPage());
				ps.setInt(6, taskList.get(i).getTotalCount());
				ps.setLong(7, taskList.get(i).getCreateTime());
				ps.setLong(8, taskList.get(i).getStartTime());
				ps.setLong(9, taskList.get(i).getEndTime());
				ps.setLong(10, taskList.get(i).getFailTime());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: getTaskList  
	    * @Description: 获取任务列表
		* @return List<TransferTaskVo>    返回类型
	 */
	public List<TransferTaskVo> getTaskList(){
		List<TransferTaskVo> taskList = jdbcTemplate.query(TransferTaskVo.getSelectStatement(), new Object[]{}, new BeanPropertyRowMapper<TransferTaskVo>(TransferTaskVo.class));
		return taskList;
	}
	
	/**
	 * 
	    * @Title: updateTask  
	    * @Description: 更新任务 的页码与总数
	    * @param taskType	任务类型
	    * @param totalPage	总页数
	    * @param totalCount	总数
		* @return void    返回类型
	 */
	public void updateTask(Byte taskType,Integer totalPage,Integer totalCount){
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE ")
		  .append(" transfer_task ")
		  .append(" SET ")
		  .append(" total_count = " + totalCount + " ")
	      .append(" ,total_page = " + totalPage + " ")
		  .append(" WHERE ")
		  .append(" task_type = " + taskType );
		jdbcTemplate.update(sb.toString());
	}
	
	/**
	 * 
	    * @Title: updateTask  
	    * @Description: 更新任务已经执行的页码
	    * @param taskType		任务类型
	    * @param pageNo			页码
		* @return void    返回类型
	 */
	public void updateTask(Byte taskType, Integer pageNo){
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE ")
		  .append(" transfer_task ")
		  .append(" SET ")
		  .append(" page_no =  " + pageNo + " ")
		  .append(" WHERE ")
		  .append(" task_type = " + taskType );
		jdbcTemplate.update(sb.toString());
	}
	
	/**
	 * 
	    * @Title: updateTask  
	    * @Description: 更新任务的状态
	    * @param taskType	任务类型
	    * @param taskStatus	任务状态
		* @return void    返回类型
	 */
	public void updateTask(Byte taskType, Byte taskStatus){
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE ")
		  .append(" transfer_task ")
		  .append(" SET ")
		  .append(" task_status = " + taskStatus);
		switch(taskStatus){
			case TransferTaskConstants.TASK_STATUS_RUNING:{
				sb.append(",start_time = " + new Date().getTime() + " ");
				sb.append(",fail_time = 0 ");
				break;
			}
			case TransferTaskConstants.TASK_STATUS_END:{
				sb.append(",end_time = " + new Date().getTime() + " ");
				break;
			}
			case TransferTaskConstants.TASK_STATUS_FAIL:{
				sb.append(",fail_time = " + new Date().getTime() + " ");
				break;
			}
		}
		sb.append(" ")
		  .append(" WHERE ")
		  .append(" task_type = " + taskType );
		jdbcTemplate.update(sb.toString());
	}
}
