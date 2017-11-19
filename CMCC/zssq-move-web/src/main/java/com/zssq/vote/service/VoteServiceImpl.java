package com.zssq.vote.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;
import com.zssq.vote.vo.CountResult;
import com.zssq.vote.vo.TempVoteComment;
import com.zssq.vote.vo.TempVoteOptions;
import com.zssq.vote.vo.Vote;
import com.zssq.vote.vo.VoteComment;
import com.zssq.vote.vo.VoteCommentReply;
import com.zssq.vote.vo.VoteInfo;
import com.zssq.vote.vo.VoteJoin;
import com.zssq.vote.vo.VoteOptions;
import com.zssq.vote.vo.VoteUser;

@Service("voteService")
@DataSource(DataSourceConstants.MYSQL_VOTE)
public class VoteServiceImpl implements VoteService {

	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private JdbcTemplate jdbcTemplate; // zssq_mblog

	ExecutorService votePool = Executors.newFixedThreadPool(10);
	ExecutorService joinPool = Executors.newFixedThreadPool(10);
	ExecutorService commentPool = Executors.newFixedThreadPool(10);

	@Override
	public void init() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
		
//		String sql = "SELECT * FROM (SELECT B.*, ROWNUMBER() OVER() AS RN FROM(SELECT * FROM vote WHERE DEL_FLAG = 0 order by VOTE_ID ) AS B)AS A WHERE A.RN BETWEEN ? AND ?";
		
		/*DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
		String sql = "delete from vote_info";
		int count = jdbcTemplate.update(sql);
		log.info("删除投票数据 条数是 " + count);

		sql = "delete from vote_options";
		count = jdbcTemplate.update(sql);
		log.info("删除选项数据 条数是 " + count);

		sql = "delete from vote_join";
		count = jdbcTemplate.update(sql);
		log.info("删除参与记录数据 条数是 " + count);

		sql = "delete from vote_comment";
		count = jdbcTemplate.update(sql);
		log.info("删除评论数据 条数是 " + count);

		sql = "delete from vote_comment_reply";
		count = jdbcTemplate.update(sql);
		log.info("删除回复数据 条数是 " + count);*/
	}

	@Override
	public void voteInfoParse() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
		try {
				log.info("开始投票数据迁移。。。。。");

				String sqlDel = "delete from vote_info";
				int delVote = jdbcTemplate.update(sqlDel);
				log.info("删除投票数据 条数是 " + delVote);

				sqlDel = "delete from vote_options";
				int delOpt = jdbcTemplate.update(sqlDel);
				log.info("删除选项数据 条数是 " + delOpt);

				DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
				String countSql = "SELECT count(*) FROM vote WHERE DEL_FLAG = 0";
				Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);

				int page = 0;
				final int count = 100;

				int totalPage = total % count == 0 ? total / count : total / count + 1;
				
				while (true) {
					page = page + 1;
					if(page>totalPage){
						break;
					}
					final int thisPage = page;
					log.info("正在处理第 " + thisPage + "頁数据");

					try {
						votePool.execute(new Runnable() {

							@Override
							public void run() {
								// 分页查询投票数据
								DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
							
//							String sql = "SELECT * FROM (SELECT B.*, ROWNUMBER() OVER() AS RN FROM(SELECT * FROM vote WHERE DEL_FLAG = 0 order by VOTE_ID ) AS B)AS A WHERE A.RN BETWEEN ? AND ?";
								
								String sql = "SELECT * FROM vote WHERE DEL_FLAG = 0 ORDER BY VOTE_ID asc LIMIT ?,?";
								
								/*List<Vote> list = jdbcTemplate.query(sql,
										new Object[] { ((thisPage - 1) * count + 1), thisPage * count },
										new BeanPropertyRowMapper<Vote>(Vote.class));*/

								List<Vote> list = jdbcTemplate.query(sql,
										new Object[] { (thisPage - 1) * count, count},
										new BeanPropertyRowMapper<Vote>(Vote.class));
								
								
								log.info("page = " + thisPage);

								ArrayList<VoteInfo> infos = new ArrayList<>();
								ArrayList<VoteOptions> options = new ArrayList<>();
								for (Vote vote : list) {
									VoteInfo info = new VoteInfo();
									info.setCode(vote.getVoteId() + "");
									// TODO 根据vote.getUserId()获取租户和组织机构信息
									info.setTenantCode("");
									info.setOrgCode("");
									info.setCreateTime(vote.getCreateTime().getTime());
									info.setModifyTime(vote.getUpdateTime().getTime());
									info.setSponsorCode(vote.getUserId() + "");
									if (vote.getVoteType() == 1) {
										// 个人发起
										info.setSponsorType("1");
										info.setSponsorOrgCode(null);
									} else if (vote.getVoteType() == 2) {
										// 班组发起
										info.setSponsorType("2");
										info.setSponsorOrgCode(vote.getTeamId() + "");
									} else {
										// 管理员发起
										info.setSponsorType("3");
										if (vote.getVoteType() == 3) {
											// 市发起
											info.setSponsorOrgCode(vote.getCityId() + "");
										} else if (vote.getVoteType() == 4) {
											// 省发起
											info.setSponsorOrgCode(vote.getProvinceId() + "");
										} else {
											// 集团发起
											info.setSponsorOrgCode("");
										}

									}
									info.setVoteStatus(new Byte("5"));
									info.setTitle(this.filterEmoji(vote.getVoteTitle()));
									info.setVoteExplain(vote.getVoteDeclare());
									if (vote.getOptionsType() == null || vote.getOptionsType() == 1) {
										// 单选
										info.setIsMultiOption(new Byte("0"));
									} else {
										// 多选
										info.setIsMultiOption(new Byte("1"));
									}
									info.setIsEnableComment(new Byte("0"));
									info.setStartTime(vote.getVoteStartTime().getTime());
									info.setEndTime(vote.getVoteEndTime().getTime());
									info.setJoinNum(vote.getVoteNum());

									infos.add(info);

									// 处理投票选项数据
									sql = "select * from vote_options where VOTE_ID = ?";

									List<TempVoteOptions> optionsList = jdbcTemplate.query(sql,
											new Object[] { vote.getVoteId() },
											new BeanPropertyRowMapper<TempVoteOptions>(TempVoteOptions.class));
									if (optionsList.size() > 0) {
										for (TempVoteOptions o : optionsList) {
											VoteOptions option = new VoteOptions();
											option.setCode(o.getOptionsId() + "");
											// TODO 直接用投票的数据
											option.setTenantCode("");
											option.setOrgCode("");
											option.setCreateTime(vote.getCreateTime().getTime());
											option.setModifyTime(vote.getUpdateTime().getTime());
											option.setVoteInfoCode(o.getVoteId() + "");
											option.setOrderNumber(o.getPosition());
											option.setContent(o.getOptionsContent());
											option.setSelectedNum(o.getVoteNum());
											options.add(option);
										}
									}
								}

								DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);

								log.info("开始批量插入投票数据。。。。。。" + infos.size());
								sql = "INSERT INTO vote_info ( CODE, tenant_code, org_code, create_time, modify_time, sponsor_code, sponsor_type, sponsor_org_code, vote_status, title, is_multi_option, is_enable_comment, start_time, end_time, join_num, vote_explain ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
								int[] infoResult = jdbcTemplate.batchUpdate(sql, new MyBatchInsertVote(infos));
								infos.clear();
								log.info("结束批量插入投票数据。。。。。。" + infoResult.length);

								if (options.size() > 0) {
									log.info("开始批量插入选项数据。。。。。。" + options.size());
									sql = "INSERT INTO vote_options ( CODE, tenant_code, org_code, create_time, modify_time, vote_info_code, order_number, content, selected_num ) VALUES (?,?,?, ?, ?, ?, ?, ?, ?)";
									int[] optionResult = jdbcTemplate.batchUpdate(sql, new MyBatchInsertOption(options));
									options.clear();
									log.info("结束批量插入选项数据。。。。。。" + optionResult.length);
								}

							}

							private String filterEmoji(String source) {
								if (source != null) {
									Pattern emoji = Pattern.compile(
											"[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
											Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
									Matcher emojiMatcher = emoji.matcher(source);
									if (emojiMatcher.find()) {
										source = emojiMatcher.replaceAll("*");
										return source;
									}
									return source;
								}
								return source;
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
						return ;
					}
				}
			
			votePool.shutdown();
	        while (true) {  
	            if (votePool.isTerminated()) {  
	            	log.info("结束了！");  
	                break;  
	            }  
	            Thread.sleep(200);  
	        }  
	        
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}

	private class MyBatchInsertOption implements BatchPreparedStatementSetter {

		final List<VoteOptions> list;

		public MyBatchInsertOption(List<VoteOptions> list) {
			this.list = list;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			VoteOptions opt = list.get(i);
			ps.setString(1, opt.getCode());
			ps.setString(2, opt.getTenantCode());
			ps.setString(3, opt.getOrgCode());
			ps.setLong(4, opt.getCreateTime());
			ps.setLong(5, opt.getModifyTime());
			ps.setString(6, opt.getVoteInfoCode());
			ps.setInt(7, opt.getOrderNumber());
			ps.setString(8, opt.getContent());
			ps.setInt(9, opt.getSelectedNum());
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}
	}

	private class MyBatchInsertVote implements BatchPreparedStatementSetter {

		final List<VoteInfo> list;

		public MyBatchInsertVote(List<VoteInfo> list) {
			this.list = list;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			VoteInfo info = list.get(i);
			ps.setString(1, info.getCode());
			ps.setString(2, info.getTenantCode());
			ps.setString(3, info.getOrgCode());
			ps.setLong(4, info.getCreateTime());
			ps.setLong(5, info.getModifyTime());
			ps.setString(6, info.getSponsorCode());
			ps.setString(7, info.getSponsorType());
			ps.setString(8, info.getSponsorOrgCode());
			ps.setByte(9, info.getVoteStatus());
			ps.setString(10, info.getTitle());
			ps.setByte(11, info.getIsMultiOption());
			ps.setByte(12, info.getIsEnableComment());
			ps.setLong(13, info.getStartTime());
			ps.setLong(14, info.getEndTime());
			ps.setInt(15, info.getJoinNum());
			ps.setString(16, info.getVoteExplain());
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}

	}

	@Override
	public void voteJoinParse() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
		try {
			log.info("   开始迁移投票参与记录数据。。。。。");

			String sqlDel = "delete from vote_join";
			int delCount = jdbcTemplate.update(sqlDel);
			log.info("删除参与记录数据 条数是 " + delCount);

			String sql = "select vote_id from vote where DEL_FLAG = 0";
			// 获取所有的投票id
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
			List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);

			// 获取投票选项id与该选项的位置对应关系
			sql = "select OPTIONS_ID optionsId,POSITION from vote_options";
			List<TempVoteOptions> idAndPosition = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<TempVoteOptions>(TempVoteOptions.class));
			final Map<Integer, Integer> kv = new HashMap<>();
			for (TempVoteOptions t : idAndPosition) {
				kv.put(t.getOptionsId(), t.getPosition());
			}
			
			for (Integer id : ids) {
				final Integer thisId = id;
				joinPool.execute(new Runnable(){
					@Override
					public void run() {
						boolean flag = true;
						ArrayList<VoteJoin> joins = new ArrayList<>();
						// 获取到一个投票的所有的参与记录
						// id = 714;
						String  sql = "select * from vote_user where VOTE_ID = ? order by USER_ID";
						DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
						List<VoteUser> userList = jdbcTemplate.query(sql, new Object[] { thisId },
								new BeanPropertyRowMapper<VoteUser>(VoteUser.class));
						int countUser = userList.size();
						
						log.info(Thread.currentThread().getName()+"   正在处理  vote_id=" + thisId + "  的参与记录,有 " + countUser + " 条记录。。。。。");

						for (int i = 0; i < countUser; i++) {
							VoteUser u = userList.get(i);

							VoteJoin join = new VoteJoin();
							join.setCode(u.getVoteUserId() + "");
							// TODO
							join.setTenantCode("");
							join.setOrgCode("");
							join.setCreateTime(u.getVoteDate().getTime());
							join.setModifyTime(u.getVoteDate().getTime());
							join.setJoinUserCode(u.getUserId() + "");
							join.setVoteInfoCode(u.getVoteId() + "");
							String selectNum = kv.get(u.getVoteOptions()) + "";
							for (int j = i + 1; j < countUser; j++) {
								VoteUser uu = userList.get(j);
								if (uu.getUserId().equals(u.getUserId())) {
									String[] split = selectNum.split("\\|");
									flag = true;
									for (String s : split) {
										Integer a = Integer.parseInt(s);
										if (kv.get(uu.getVoteOptions()).equals(a)) {
											flag = false;
										}
									}
									if (flag) {
										selectNum = selectNum + "|" + kv.get(uu.getVoteOptions());
									}
									i = j;
								} else {
									i = j - 1;
									break;
								}
							}
							
							join.setSelectedNumber(selectNum);
							joins.add(join);
						}
						if (joins.size() > 0) {
							log.info(Thread.currentThread().getName()+"   开始批量插入数据，有 " + joins.size() + " 条记录。。。。。");
							DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
							sql = "INSERT INTO vote_join ( CODE, tenant_code, org_code, create_time, modify_time, join_user_code, vote_info_code, selected_number ) VALUES (?,?,?,?,?,?,?,?)";
							int[] joinResult = jdbcTemplate.batchUpdate(sql, new MyBatchInsertJoin(joins));
							log.info(Thread.currentThread().getName()+"   结束批量插入数据，成功插入  " + joinResult.length + " 条记录。。。。。");
						}
						joins.clear();
						
					}});
				
			}
			
			joinPool.shutdown();
	        while (true) {  
	            if (joinPool.isTerminated()) {  
	            	log.info("结束了！");  
	                break;  
	            }  
	            Thread.sleep(200);  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}

	private class MyBatchInsertJoin implements BatchPreparedStatementSetter {

		final List<VoteJoin> list;

		public MyBatchInsertJoin(List<VoteJoin> list) {
			this.list = list;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			VoteJoin join = list.get(i);
			ps.setString(1, join.getCode());
			ps.setString(2, join.getTenantCode());
			ps.setString(3, join.getOrgCode());
			ps.setLong(4, join.getCreateTime());
			ps.setLong(5, join.getModifyTime());
			ps.setString(6, join.getJoinUserCode());
			ps.setString(7, join.getVoteInfoCode());
			ps.setString(8, join.getSelectedNumber());
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}

	}

	@Override
	public void voteCommentParse() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
		try {
			log.info("开始处理投票评论");

			String sqlDel = "delete from vote_comment";
			int delCount = jdbcTemplate.update(sqlDel);
			log.info("删除评论数据 条数是 " + delCount);
	
			sqlDel = "delete from vote_comment_reply";
			delCount = jdbcTemplate.update(sqlDel);
			log.info("删除回复数据 条数是 " + delCount);

			String sql = "select vote_id from vote where DEL_FLAG = 0";
			// 获取所有的投票id
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
			List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);
			
			//将ids持久化到数据库（未处理状态）
			for (final Integer id : ids) {
				commentPool.execute(new Runnable(){
					@Override
					public void run() {
						ArrayList<VoteComment> commentList = new ArrayList<>();
						ArrayList<VoteCommentReply> replyList = new ArrayList<>();
						
						// id = 441;
						log.info("*********************************************************");
						String sql = "select * from vote_comment where vote_id = ? and PARENTS_ID = 0 and DEL_FLAG = 0";
						DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
						List<TempVoteComment> comments = jdbcTemplate.query(sql, new Object[] { id },
								new BeanPropertyRowMapper<TempVoteComment>(TempVoteComment.class));
						log.info(Thread.currentThread().getName()+"	正在处理vote_id=" + id + "的评论" + ".有 " + comments.size() + " 条评论");
						for (TempVoteComment comment : comments) {
							VoteComment c = new VoteComment();
							c.setCode(comment.getCommentId() + "");
							// TODO
							c.setTenantCode("");
							c.setOrgCode("");
							c.setCreateTime(comment.getCommentDate().getTime());
							c.setModifyTime(comment.getCommentDate().getTime());
							c.setVoteInfoCode(comment.getVoteId() + "");
							c.setCommenterCode(comment.getUserId() + "");
							c.setContent(comment.getCommentContent());

							// 处理回复数据
							DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_VOTE);
							sql = "select * from vote_comment where PARENTS_ID = ? and DEL_FLAG = 0";
							List<TempVoteComment> replys = jdbcTemplate.query(sql, new Object[] { comment.getCommentId() },
									new BeanPropertyRowMapper<TempVoteComment>(TempVoteComment.class));
							log.info(Thread.currentThread().getName()+"	评论id=" + comment.getCommentId() + "有 " + replys.size() + " 条回复");
							for (TempVoteComment rep : replys) {
								VoteCommentReply reply = new VoteCommentReply();
								reply.setCode(rep.getCommentId() + "");
								// TODO
								reply.setTenantCode("");
								reply.setOrgCode("");
								reply.setCreateTime(rep.getCommentDate().getTime());
								reply.setModifyTime(rep.getCommentDate().getTime());
								reply.setVoteInfoCode(rep.getVoteId() + "");
								reply.setCommentCode(comment.getCommentId() + "");
								reply.setReplierCode(rep.getUserId() + "");
								reply.setQuestionerCode(rep.getCommentuserId() + "");
								reply.setContent(rep.getCommentContent());
								replyList.add(reply);
							}
							if (replyList.size() > 0) {
								log.info(Thread.currentThread().getName()+"	开始批量插入 " + replyList.size() + " 条回复");
								DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
								sql = "INSERT INTO vote_comment_reply ( CODE, tenant_code, org_code, create_time, modify_time, vote_info_code, comment_code, replier_code, questioner_code, content ) VALUES (?,?,?,?,?,?,?,?,?,?)";
								int[] replyResult = jdbcTemplate.batchUpdate(sql, new MyBatchInsertReply(replyList));
								log.info(Thread.currentThread().getName()+"	结束批量插入数据，成功插入  " + replyResult.length + " 条记录");
								replyList.clear();
							}

							c.setReplyCount(replys.size());
							commentList.add(c);
						}
						if (commentList.size() > 0) {
							log.info(Thread.currentThread().getName()+"		开始批量插入 " + commentList.size() + " 条评论");
							DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);
							sql = "INSERT INTO vote_comment ( CODE, tenant_code, org_code, create_time, modify_time, vote_info_code, commenter_code, content ,reply_count) VALUES (?,?,?,?,?,?,?,?,?)";
							int[] commentResult = jdbcTemplate.batchUpdate(sql, new MyBatchInsertComment(commentList));
							log.info(Thread.currentThread().getName()+"		结束批量插入数据，成功插入  " + commentResult.length + " 条记录");
						}
						commentList.clear();
						//将当前的vote id 的状态置为已经处理过的
					}});
			}
			
			commentPool.shutdown();
	        while (true) {  
	            if (commentPool.isTerminated()) {  
	            	log.info("结束了！");  
	                break;  
	            }  
	            Thread.sleep(200);  
	        }  
		
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}

	private class MyBatchInsertComment implements BatchPreparedStatementSetter {
		final List<VoteComment> list;

		public MyBatchInsertComment(List<VoteComment> list) {
			this.list = list;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			VoteComment comment = list.get(i);
			ps.setString(1, comment.getCode());
			ps.setString(2, comment.getTenantCode());
			ps.setString(3, comment.getOrgCode());
			ps.setLong(4, comment.getCreateTime());
			ps.setLong(5, comment.getModifyTime());
			ps.setString(6, comment.getVoteInfoCode());
			ps.setString(7, comment.getCommenterCode());
			ps.setString(8, comment.getContent());
			ps.setInt(9, comment.getReplyCount());
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}
	}

	private class MyBatchInsertReply implements BatchPreparedStatementSetter {
		final List<VoteCommentReply> list;

		public MyBatchInsertReply(List<VoteCommentReply> list) {
			this.list = list;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			VoteCommentReply reply = list.get(i);
			ps.setString(1, reply.getCode());
			ps.setString(2, reply.getTenantCode());
			ps.setString(3, reply.getOrgCode());
			ps.setLong(4, reply.getCreateTime());
			ps.setLong(5, reply.getModifyTime());
			ps.setString(6, reply.getVoteInfoCode());
			ps.setString(7, reply.getCommentCode());
			ps.setString(8, reply.getReplierCode());
			ps.setString(9, reply.getQuestionerCode());
			ps.setString(10, reply.getContent());
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}

	}

	public String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("*");
				return source;
			}
			return source;
		}
		return source;
	}

	@Override
	public void countCommentAndJoinNum() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_VOTE);

		try {
			log.info("开始进行数据统计");
			String sql = "select count(*) count,vote_info_code id from vote_comment GROUP BY vote_info_code";
			List<CountResult> commentList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<CountResult>(CountResult.class));
			sql = "select count(*) count,vote_info_code id from vote_join  GROUP BY vote_info_code";
			List<CountResult> joinList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<CountResult>(CountResult.class));

			if (commentList.size() > 0) {
				sql = "UPDATE vote_info set comment_num = ? where code = ?";
				log.info("批量修改评论数");
				int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new MyBatchUpdate(commentList));
				log.info("成功修改了 " + batchUpdate.length + " 条数据");
			}
			if (joinList.size() > 0) {
				sql = "UPDATE vote_info set join_num = ? where code = ?";
				log.info("批量修改参与数");
				int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new MyBatchUpdate(joinList));
				log.info("成功修改了 " + batchUpdate.length + " 条数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}

	private class MyBatchUpdate implements BatchPreparedStatementSetter {

		final List<CountResult> all;

		public MyBatchUpdate(List<CountResult> all) {
			this.all = all;
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			CountResult result = all.get(i);
			ps.setInt(1, result.getCount());
			ps.setString(2, result.getId());
		}

		@Override
		public int getBatchSize() {
			return all.size();
		}
	}
}
