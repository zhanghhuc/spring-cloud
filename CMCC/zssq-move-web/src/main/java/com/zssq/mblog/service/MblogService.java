package com.zssq.mblog.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.constants.MblogConstants;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.mblog.vo.Db2AtVo;
import com.zssq.mblog.vo.Db2CommentVo;
import com.zssq.mblog.vo.Db2MblogVo;
import com.zssq.mblog.vo.Db2SubVo;
import com.zssq.mblog.vo.Db2TopicVo;
import com.zssq.mblog.vo.MblogForward;
import com.zssq.mblog.vo.MblogResource;
import com.zssq.mblog.vo.MysqlMblogIdCodeVo;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;

@Service
@DataSource(DataSourceConstants.MYSQL_MBLOG)
public class MblogService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	    * @Title: insertBatchMblog  
	    * @Description: 批量插入微博数据
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchMblog(List<Db2MblogVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2MblogVo> mblogList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_info ")
			.append(" ( ")
			.append("id,")
			.append("mblog_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("publish_time,")
			.append("org_code,")
			.append("user_code,")
			.append("agent_user_code,")
			.append("agent_org_code,")
			.append("team_code,")
			.append("source_mblog_code,")
			.append("source_user_code,")
			.append("mblog_source,")
			.append("mblog_depend,")
			.append("tenant_code,")
			.append("mblog_logo,")
			.append("dynamic_code,")
			
			.append("remark,")
			.append("mblog_visible,")
			.append("is_delete,")
			.append("is_shield,")
			.append("shield_user_code,")
			.append("shield_time")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, '',1,0,0,'',0) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return mblogList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, mblogList.get(i).getId());
				ps.setString(2, mblogList.get(i).getMblogCode());
				ps.setLong(3, mblogList.get(i).getPubTime().getTime());
				ps.setLong(4, mblogList.get(i).getPubTime().getTime());
				ps.setLong(5, mblogList.get(i).getPubTime().getTime());
				ps.setString(6, mblogList.get(i).getOrgCode());
				ps.setString(7, mblogList.get(i).getUserCode());
				ps.setString(8, mblogList.get(i).getAgentUserCode());
				ps.setString(9, mblogList.get(i).getAgentOrgCode());
				ps.setString(10, mblogList.get(i).getTeamCode());
				ps.setString(11, mblogList.get(i).getSourceMblogCode());
				ps.setString(12, mblogList.get(i).getSourceUserCode());
				ps.setByte(13, mblogList.get(i).getActionType());
				ps.setByte(14, mblogList.get(i).getMblogDepend());
				ps.setString(15, mblogList.get(i).getTenantCode());
				ps.setString(16, mblogList.get(i).getLogo());
				ps.setString(17, mblogList.get(i).getDynamicCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchForMblog  
	    * @Description: 批量插入转发的微博信息
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchForMblog(List<Db2MblogVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2MblogVo> mblogList = recordList;
		/*for(Db2MblogVo vo : mblogList){
			MysqlMblogIdCodeVo rootMb = jdbcTemplate.queryForObject(MysqlMblogIdCodeVo.getSelectStatement(vo.getRootTid()), new Object[]{}, new BeanPropertyRowMapper<MysqlMblogIdCodeVo>(MysqlMblogIdCodeVo.class));
			vo.setSourceMblogCode(rootMb.getMblogCode());
			vo.setSourceUserCode(rootMb.getUserCode());
		}*/
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_info ")
			.append(" ( ")
			.append("id,")
			.append("mblog_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("publish_time,")
			.append("org_code,")
			.append("user_code,")
			.append("agent_user_code,")
			.append("agent_org_code,")
			.append("team_code,")
			.append("source_mblog_code,")
			.append("source_user_code,")
			.append("mblog_source,")
			.append("mblog_depend,")
			.append("tenant_code,")
			.append("mblog_logo,")
			.append("dynamic_code,")
			
			.append("remark,")
			.append("mblog_visible,")
			.append("is_delete,")
			.append("is_shield,")
			.append("shield_user_code,")
			.append("shield_time")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, '',1,0,0,'',0) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return mblogList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, mblogList.get(i).getId());
				ps.setString(2, mblogList.get(i).getMblogCode());
				ps.setLong(3, mblogList.get(i).getPubTime().getTime());
				ps.setLong(4, mblogList.get(i).getPubTime().getTime());
				ps.setLong(5, mblogList.get(i).getPubTime().getTime());
				ps.setString(6, mblogList.get(i).getOrgCode());
				ps.setString(7, mblogList.get(i).getUserCode());
				ps.setString(8, mblogList.get(i).getAgentUserCode());
				ps.setString(9, mblogList.get(i).getAgentOrgCode());
				ps.setString(10, mblogList.get(i).getTeamCode());
				ps.setString(11, mblogList.get(i).getSourceMblogCode());
				ps.setString(12, mblogList.get(i).getSourceUserCode());
				ps.setByte(13, mblogList.get(i).getActionType());
				ps.setByte(14, mblogList.get(i).getMblogDepend());
				ps.setString(15, mblogList.get(i).getTenantCode());
				ps.setString(16, mblogList.get(i).getLogo());
				ps.setString(17, mblogList.get(i).getDynamicCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchMblogNum  
	    * @Description: 批量插入微博数目
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchMblogNum(List<Db2MblogVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2MblogVo> mblogList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_num ")
			.append(" ( ")
			.append("mblog_num_code,")
			.append("subject_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("comment_num,")
			.append("forward_num,")
			
			.append("num_source,")
			.append("remark,")
			.append("praise_num,")
			.append("collect_num,")
			.append("recommend_num,")
			.append("report_num,")
			.append("reply_num,")
			.append("team_essence_num,")
			.append("city_essence_num,")
			.append("province_essence_num,")
			.append("group_essence_num")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,1,'',0,0,0,0,0,0,0,0,0) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return mblogList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setString(2, mblogList.get(i).getMblogCode());
				ps.setLong(3, mblogList.get(i).getPubTime().getTime());
				ps.setLong(4, mblogList.get(i).getPubTime().getTime());
				ps.setInt(5, mblogList.get(i).getReplyNum());
				ps.setInt(6, mblogList.get(i).getForwardNum());
			}
			
		});

	}
	
	/**
	 * 
	    * @Title: insertBatchMblogResource  
	    * @Description: 批量插入微博资源信息
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchMblogResource(List<Db2MblogVo> recordList){
		final List<MblogResource> resList = new ArrayList<MblogResource>();
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		for(Db2MblogVo vo : recordList){
			String content = vo.getContentExt();
			String imgs = vo.getImgUrls();
			String video = vo.getVideoUrl();
			String audio = vo.getAudioUrl();
			long time = vo.getPubTime().getTime();
			String mblogCode = vo.getMblogCode();
			String orgCode = vo.getOrgCode();
			String tenatCode = vo.getTenantCode();
			
			// 文本资源
			if(StringTools.isNotEmpty(content)){
				//content = EmojiConvertUtil.emojiConvert(content);
				MblogResource conRes = new MblogResource();
				conRes.setMblogRecourceCode(UUIDHelper.getUUID()); // 资源CODE
				conRes.setContent(content); // 内容
				conRes.setCreateTime(time); // 创建时间
				conRes.setMblogCode(mblogCode); // 微博CODE 
				conRes.setModifyTime(time); // 修改时间
				conRes.setRemark(MblogConstants.MBLOG_BLANK); // 备注
				conRes.setOrgCode(orgCode); // 组织CODE
				conRes.setResType(MblogConstants.MBLOG_RESOURCE_TEXT); // 资源类型
				conRes.setTenantCode(tenatCode); // 租户
				resList.add(conRes);
				
				
				MblogResource sumRes = new MblogResource();
				sumRes.setMblogRecourceCode(UUIDHelper.getUUID());
				sumRes.setContent(content);
				sumRes.setCreateTime(time);
				sumRes.setMblogCode(mblogCode);
				sumRes.setModifyTime(time);
				sumRes.setRemark(MblogConstants.MBLOG_BLANK);
				sumRes.setOrgCode(orgCode);
				sumRes.setResType(MblogConstants.MBLOG_RESOURCE_SUMMARY);
				sumRes.setTenantCode(tenatCode);
				
				resList.add(sumRes);
			}
			
			
			
			// 图片资源
			if(StringTools.isNotEmpty(imgs)){
				String[] imgArr = null;
				System.err.println(imgs);
				/*if(imgs.toString().endsWith(",")){
					imgArr = imgs.substring(0, imgs.length()-1).split(",");
	    		}*/
				imgArr = imgs.split(",");
				for(String img : imgArr){
					if(null != img && !"".equals(img.trim())){
						MblogResource imgRes = new MblogResource();
						imgRes.setMblogRecourceCode(UUIDHelper.getUUID());
						imgRes.setContent(img);
						imgRes.setCreateTime(time);
						imgRes.setMblogCode(mblogCode);
						imgRes.setModifyTime(time);
						imgRes.setRemark(MblogConstants.MBLOG_BLANK);
						imgRes.setOrgCode(orgCode);
						imgRes.setResType(MblogConstants.MBLOG_RESOURCE_IMG);
						imgRes.setTenantCode(tenatCode);
						resList.add(imgRes);
					}
				}
			}
			// 音频资源
			if(StringTools.isNotEmpty(audio)){
				MblogResource audioRes = new MblogResource();
				audioRes.setMblogRecourceCode(UUIDHelper.getUUID());
				audioRes.setContent(audio);
				audioRes.setCreateTime(time);
				audioRes.setMblogCode(mblogCode);
				audioRes.setModifyTime(time);
				audioRes.setRemark(MblogConstants.MBLOG_BLANK);
				audioRes.setOrgCode(orgCode);
				audioRes.setResType(MblogConstants.MBLOG_RESOURCE_AUDIO);
				audioRes.setTenantCode(tenatCode);
				
				resList.add(audioRes);
			}
			// 视频资源
			if(StringTools.isNotEmpty(video)){
				MblogResource videoRes = new MblogResource();
				videoRes.setMblogRecourceCode(UUIDHelper.getUUID());
				videoRes.setContent(video);
				videoRes.setCreateTime(time);
				videoRes.setMblogCode(mblogCode);
				videoRes.setModifyTime(time);
				videoRes.setRemark(MblogConstants.MBLOG_BLANK);
				videoRes.setOrgCode(orgCode);
				videoRes.setResType(MblogConstants.MBLOG_RESOURCE_VIDEO);
				videoRes.setTenantCode(tenatCode);
				resList.add(videoRes);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_resource ")
			.append(" ( ")
			.append("mblog_recource_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("remark,")
			.append("org_code,")
			.append("mblog_code,")
			.append("res_type,")
			.append("tenant_code,")
			.append("content")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,?,?) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return resList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setLong(2, resList.get(i).getCreateTime());
				ps.setLong(3, resList.get(i).getModifyTime());
				ps.setString(4, resList.get(i).getRemark());
				ps.setString(5, resList.get(i).getOrgCode());
				ps.setString(6, resList.get(i).getMblogCode());
				ps.setByte(7, resList.get(i).getResType());
				ps.setString(8, resList.get(i).getTenantCode());
				ps.setString(9, resList.get(i).getContent());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchMblogForward  
	    * @Description: 批量插入微博转发信息
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchMblogForward(List<MblogForward> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<MblogForward> fowList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_forward ")
			.append(" ( ")
			.append("cur_mblog_code,")
			.append("source_mblog_code,")
			.append("user_code")
			.append(" ) ")
			.append(" values (?,?,?) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return fowList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, fowList.get(i).getCurMblogCode());
				ps.setString(2, fowList.get(i).getSourceMblogCode());
				ps.setString(3, fowList.get(i).getUserCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchMblogIdCode  
	    * @Description: 批量插入微博IDCODE数据
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchMblogIdCode(List<MysqlMblogIdCodeVo> recordList){
		final List<MysqlMblogIdCodeVo> mblogIdCodeList = recordList;
		jdbcTemplate.batchUpdate(MysqlMblogIdCodeVo.getInsertStatement(),  new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return mblogIdCodeList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, mblogIdCodeList.get(i).getMblogId());
				ps.setString(2, mblogIdCodeList.get(i).getMblogCode());
				ps.setString(3, mblogIdCodeList.get(i).getUserCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchComment  
	    * @Description: 批量插入评论信息
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchComment(List<Db2CommentVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2CommentVo> commentList = recordList;
		/*for(Db2CommentVo vo : commentList){
			MysqlMblogIdCodeVo mb = jdbcTemplate.queryForObject(MysqlMblogIdCodeVo.getSelectStatement(vo.getMblogId()), new Object[]{}, new BeanPropertyRowMapper<MysqlMblogIdCodeVo>(MysqlMblogIdCodeVo.class));
			if(null != mb){
				vo.setMblogCode(mb.getMblogCode());
			}
		}*/
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_comment ")
			.append(" ( ")
			.append("mblog_comment_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("org_code,")
			.append("user_code,")
			.append("mblog_code,")
			.append("tenant_code,")
			.append("content,")
			
			.append("remark,")
			.append("is_delete,")
			.append("is_shield,")
			.append("shield_user_code,")
			.append("shield_time")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,?,'',0,0,'',0) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return commentList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, commentList.get(i).getCommentCode());
				ps.setLong(2,commentList.get(i).getReplyTime().getTime());
				ps.setLong(3, commentList.get(i).getReplyTime().getTime());
				ps.setString(4, commentList.get(i).getOrgCode());
				ps.setString(5, commentList.get(i).getUserCode());
				ps.setString(6, commentList.get(i).getMblogCode());
				ps.setString(7, commentList.get(i).getTenantCode());
				ps.setString(8, commentList.get(i).getReplyContentext());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchCommentNum  
	    * @Description: 批量插入评论数目
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchCommentNum(List<Db2CommentVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2CommentVo> commentList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_num ")
			.append(" ( ")
			.append("mblog_num_code,")
			.append("subject_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("comment_num,")
			.append("forward_num,")
			
			.append("num_source,")
			.append("remark,")
			.append("praise_num,")
			.append("collect_num,")
			.append("recommend_num,")
			.append("report_num,")
			.append("reply_num,")
			.append("team_essence_num,")
			.append("city_essence_num,")
			.append("province_essence_num,")
			.append("group_essence_num")
			.append(" ) ")
			.append(" values (?,?,?,?,0,0,2,'',0,0,0,0,0,0,0,0,0) ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return commentList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setString(2, commentList.get(i).getCommentCode());
				ps.setLong(3, commentList.get(i).getReplyTime().getTime());
				ps.setLong(4, commentList.get(i).getReplyTime().getTime());
			}
			
		});

	}
	
	/**
	 * 
	    * @Title: insertBatchTopic  
	    * @Description: 批量插入话题类型
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchTopic(List<Db2TopicVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2TopicVo> topicList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_topic ")
			.append(" ( ")
			.append("mblog_topic_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("org_code,")
			.append("topic_name,")
			.append("topic_num,")
			.append("tenant_code,")
			.append("remark")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,'') ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return topicList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setLong(2, topicList.get(i).getCreateTime().getTime());
				ps.setLong(3, topicList.get(i).getCreateTime().getTime());
				ps.setString(4, topicList.get(i).getOrgCode());
				ps.setString(5, topicList.get(i).getTopicName());
				ps.setInt(6, topicList.get(i).getJoinNum());
				ps.setString(7, topicList.get(i).getTenantCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchAt  
	    * @Description: 批量插入AT
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchAt(List<Db2AtVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2AtVo> atList = recordList;
		/*for(Db2AtVo vo : atList){
			MysqlMblogIdCodeVo mb = jdbcTemplate.queryForObject(MysqlMblogIdCodeVo.getSelectStatement(vo.getItemId()), new Object[]{}, new BeanPropertyRowMapper<MysqlMblogIdCodeVo>(MysqlMblogIdCodeVo.class));
			if(null != mb){
				vo.setMblogCode(mb.getMblogCode());
			}
		}*/
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_at ")
			.append(" ( ")
			.append("mblog_at_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("org_code,")
			.append("user_code,")
			.append("mblog_code,")
			.append("tenant_code,")
			
			.append("comment_code,")
			.append("remark,")
			.append("type,")
			.append("at_site,")
			.append("reply_code")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,'','',1,1,'') ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return atList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setLong(2, atList.get(i).getCreateTime().getTime());
				ps.setLong(3, atList.get(i).getCreateTime().getTime());
				ps.setString(4, atList.get(i).getOrgCode());
				ps.setString(5, atList.get(i).getUserCode());
				ps.setString(6, atList.get(i).getMblogCode());
				ps.setString(7, atList.get(i).getTenantCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: insertBatchSub  
	    * @Description: 批量插入订阅信息
	    * @param recordList
		* @return void    返回类型
	 */
	public void insertBatchSub(List<Db2SubVo> recordList){
		if(recordList == null || recordList.size() <= 0){
			return;
		}
		final List<Db2SubVo> subList = recordList;
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_subscribe ")
			.append(" ( ")
			.append("mblog_subscribe_code,")
			.append("create_time,")
			.append("modify_time,")
			.append("org_code,")
			.append("user_code,")
			.append("sub_user_code,")
			.append("sub_org_code,")
			.append("tenant_code,")
			
			.append("sub_type,")
			.append("team_code,")
			.append("remark")
			.append(" ) ")
			.append(" values (?,?,?,?,?,?,?,?,1,'','') ");
		// 批量插入	
		jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return subList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, UUIDHelper.getUUID());
				ps.setLong(2, subList.get(i).getCreateTime().getTime());
				ps.setLong(3, subList.get(i).getCreateTime().getTime());
				ps.setString(4, subList.get(i).getOrgCode());
				ps.setString(5, subList.get(i).getUserCode());
				ps.setString(6, subList.get(i).getSubUserCode());
				ps.setString(7, subList.get(i).getSubOrgCode());
				ps.setString(8, subList.get(i).getTenantCode());
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: createMblogIdCode  
	    * @Description: 创建中间表
		* @return void    返回类型
	 */
	public void createMblogIdCode(){
		jdbcTemplate.batchUpdate(MysqlMblogIdCodeVo.getCreateStatement());
	}
	
	/**
	 * 
	    * @Title: getIdCode  
	    * @Description: 获取微博CODE
	    * @param mblogId
		* @return MysqlMblogIdCodeVo    返回类型
	 */
	public MysqlMblogIdCodeVo getIdCode(String mblogId){
		if(null == mblogId){
			return null;
		}
		try{
			MysqlMblogIdCodeVo mb = jdbcTemplate.queryForObject(MysqlMblogIdCodeVo.getSelectStatement(mblogId), new Object[]{}, new BeanPropertyRowMapper<MysqlMblogIdCodeVo>(MysqlMblogIdCodeVo.class));
			if(null == mb){
				return null;
			}
			return mb;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
}
