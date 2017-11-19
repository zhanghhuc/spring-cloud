package com.zssq.relation.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.service.BlogService;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;
import com.zssq.mblog.service.MblogService;
import com.zssq.relation.vo.BlogData;
import com.zssq.relation.vo.BlogInfo;
import com.zssq.relation.vo.MblogInfo;
import com.zssq.relation.vo.MblogNum;
import com.zssq.relation.vo.Recommend;
import com.zssq.relation.vo.RelationRecommend;
import com.zssq.relation.vo.RelationSubjectData;
import com.zssq.relation.vo.RelationSubjectInfo;
import com.zssq.shiro.mysecurity.UUIDHelper;

@Service
@DataSource(DataSourceConstants.MYSQL_RELATION)
public class RelationService {

	@Resource
	private JdbcTemplate jdbcTemplate; // zssq_mblog

	@Autowired
	private MblogService mblogService;
	@Autowired
	private RelationTeamService relationTeamService;
	@Autowired
	private RelationUserService relationUserService;
	@Autowired
	private BlogService blogService;

	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public List<Map<String, Object>> getRecList() {
		// 源表推荐信息
		String sql = "SELECT * FROM `relation_recommend`";
		List<Map<String, Object>> sourceTopicList = jdbcTemplate.queryForList(sql);
		return sourceTopicList;
	}

	@DataSource(DataSourceConstants.DB2_MBLOG)
	public Integer mblogCount() {
		String sql = "SELECT COUNT(*) FROM MBLOG.MBLOG_RECOMMEND";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_MBLOG);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	/**
	 * @Function mBlogRecommend
	 * @Description 导微博推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.DB2_MBLOG)
	public long  mBlogRecommend(Integer pageNo, Integer pageSize) {
		// 源表推荐信息
		String sql = "SELECT * FROM (SELECT ID,MBLOG_ID,PRO_ID,CITY_ID,TEAM_ID,PUB_ID,USER_ID,CREATETIME,STATE,LOGO ,rownumber() over() AS rn FROM MBLOG.MBLOG_RECOMMEND as mbr) AS mbrt  WHERE mbrt.rn BETWEEN "
				+ (pageNo * pageSize + 1) + " AND " + (pageNo * pageSize + pageSize) + " ";
//		String sql = "SELECT * FROM (SELECT ID,MBLOG_ID,PRO_ID,CITY_ID,TEAM_ID,PUB_ID,USER_ID,CREATETIME,STATE,LOGO ,rownumber() over() AS rn FROM MBLOG.MBLOG_RECOMMEND as mbr) AS mbrt  WHERE mbrt.rn BETWEEN 01 and 10";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_MBLOG);
		List<Recommend> sourceTopicList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<Recommend>(Recommend.class));
		List<RelationRecommend> list = new ArrayList<RelationRecommend>();
		
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		for (Recommend mblogRecommend : sourceTopicList) {
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			// 获取最新微博code
			try {
				selectedDataCount++;
				relationRecommend.setSubjectCode(mblogService.getIdCode(mblogRecommend.getMBLOG_ID() + "").getMblogCode());
				relationRecommend.setSubjectClass((byte) 1);
				relationRecommend.setCreateTime(mblogRecommend.getCREATETIME().getTime());
				relationRecommend.setModifyTime(mblogRecommend.getCREATETIME().getTime());
			// 调用授权获取组织信息
			relationRecommend.setRecUserCode(relationUserService.getUserInfo(mblogRecommend.getUSER_ID()).getUserCode());
			// 根据推荐类型 state 1 班组推荐 2 地市推荐 3 省推荐4集团
			if (mblogRecommend.getSTATE() == 1) {
				String teamOrgCode = relationTeamService.getTeamInfo(mblogRecommend.getTEAM_ID()).getOrgCode();
				if("".equals(teamOrgCode)){
					invalidDataCount++;
					continue;
				}
				relationRecommend.setRecOrgCode(teamOrgCode);

				// 查询所属门户
				relationRecommend.setRecToOrgCode(relationUserService.getParentOrgInfo(teamOrgCode));
			} else {
				String orgCode = relationUserService.getUserOrgCode(mblogRecommend.getUSER_ID());
				
				if (mblogRecommend.getSTATE() == 2) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getOrgCodeByOrgID(mblogRecommend.getCITY_ID()));
				} else if (mblogRecommend.getSTATE() == 3) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getOrgCodeByOrgID(mblogRecommend.getPRO_ID()));
				} else if (mblogRecommend.getSTATE() == 4) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getGroupCode());
				}
			}

				relationRecommend.setTenantCode("#############");
				relationRecommend.setRecSource((byte) (mblogRecommend.getSTATE() != 1 ? 2 : 1));
			if (mblogRecommend.getSTATE() == 1) {
				relationRecommend.setTeamCode(relationTeamService.getTeamInfo(mblogRecommend.getTEAM_ID()).getTeamCode());
			}else{
				relationRecommend.setTeamCode("");
			}
				relationRecommend.setQualityCode("");
				relationRecommend.setRecQualityTime(0L);
				relationRecommend.setRecStatus((byte) 1);
				relationRecommend.setRemark("");
				list.add(relationRecommend);
				importedDataCount++;
			} catch (Exception e) {
				System.err.println("导入推荐时，对应微博记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;
			}
		}
		insertRecommend(list);
		System.err.println("#########导入微博推荐   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		// String sql2 = "SELECT * FROM relation_recommend";
		// DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		// List<Map<String, Object>> list = jdbcTemplate.queryForList(sql2);
		// return list;
		return importedDataCount;
	}

	/**
	 * @Function blogCount
	 * @Description 查询源博客总数
	 * @return
	 */
  @DataSource(DataSourceConstants.DB2_BLOG)
	public Integer blogCount() {
		String sql = "SELECT COUNT(*) FROM BLOG.BLOG_RECOMMEND";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_BLOG);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

  
	/**
	 * @Function blogRecommend
	 * @Description 导博客推荐
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public long blogRecommend(Integer pageNo, Integer pageSize) {
		// 源表推荐信息
		String sql = "SELECT * FROM (SELECT RECO_ID,BLOG_ID,PROVINCE_ID,CITY_ID,TEAM_ID,USER_ID,CREATE_TIME,STATE,LOGO ,rownumber() over() AS rn FROM BLOG.BLOG_RECOMMEND as mbr) AS mbrt  WHERE mbrt.rn BETWEEN "
				+ (pageNo * pageSize + 1) + " AND " + (pageNo * pageSize + pageSize) + " ";
		
//		String sql = "SELECT * FROM (SELECT RECO_ID,BLOG_ID,PROVINCE_ID,CITY_ID,TEAM_ID,USER_ID,CREATE_TIME,STATE,LOGO ,rownumber() over() AS rn FROM BLOG.BLOG_RECOMMEND as mbr) AS mbrt  WHERE mbrt.rn BETWEEN  01 AND 010";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_BLOG);
		List<Recommend> sourceTopicList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<Recommend>(Recommend.class));
		List<RelationRecommend> list = new ArrayList<RelationRecommend>();
		
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		for (Recommend mblogRecommend : sourceTopicList) {
			selectedDataCount++;
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			String blogCode = blogService.getBlogCode(mblogRecommend.getBLOG_ID());
			if("".equals(blogCode)){
				invalidDataCount++;
				System.err.println("导入推荐时，对应博客记录获取不到，放弃该条记录");
				continue;
			}
			relationRecommend.setSubjectCode(blogCode);
			
			try {
				
				relationRecommend.setSubjectClass((byte) 2);
				relationRecommend.setCreateTime(mblogRecommend.getCREATE_TIME().getTime());
				relationRecommend.setModifyTime(mblogRecommend.getCREATE_TIME().getTime());
			// 调用授权获取组织信息
			relationRecommend.setRecUserCode(relationUserService.getUserInfo(mblogRecommend.getUSER_ID()).getUserCode());

			// 根据推荐类型 state 1 班组推荐 2 地市推荐 3 省推荐4集团
			if (mblogRecommend.getSTATE() == 1) {
				String teamOrgCode = relationTeamService.getTeamInfo(mblogRecommend.getTEAM_ID()).getOrgCode();
				if("".equals(teamOrgCode)){
					invalidDataCount++;
					continue;
				}
				relationRecommend.setRecOrgCode(teamOrgCode);

				// 查询所属门户
				relationRecommend.setRecToOrgCode(relationUserService.getParentOrgInfo(teamOrgCode));
			} else {
				String orgCode = relationUserService.getUserOrgCode(mblogRecommend.getUSER_ID());
				
				if (mblogRecommend.getSTATE() == 2) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getOrgCodeByOrgID(mblogRecommend.getCITY_ID()));
				} else if (mblogRecommend.getSTATE() == 3) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getOrgCodeByOrgID(mblogRecommend.getPROVINCE_ID()));
				} else if (mblogRecommend.getSTATE() == 4) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(relationUserService.getGroupCode());
				}
			}

				relationRecommend.setTenantCode("#############");
				relationRecommend.setRecSource((byte) (mblogRecommend.getSTATE() != 1 ? 2 : 1));
			if (mblogRecommend.getSTATE() == 1) {
				relationRecommend.setTeamCode(relationTeamService.getTeamInfo(mblogRecommend.getTEAM_ID()).getTeamCode());
			} else {
				relationRecommend.setTeamCode("");
			}
				relationRecommend.setQualityCode("");
				relationRecommend.setRecQualityTime(0L);
				relationRecommend.setRecStatus((byte) 1);
				relationRecommend.setRemark("");
				list.add(relationRecommend);
				importedDataCount++;
			} catch (Exception e) {
				System.err.println("导入推荐时，对应博客记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;
			}
		}
		insertRecommend(list);
		
		System.err.println("#########导入博客推荐   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		// String sql2 = "SELECT * FROM relation_recommend";
		// DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		// List<Map<String, Object>> list = jdbcTemplate.queryForList(sql2);
		// return list;
		return importedDataCount;
	}

	/**
	 * @Function insertRecommend
	 * @Description 插入推荐数据
	 * @param list
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	private void insertRecommend(List<RelationRecommend> recommendList) {
		
		final List<RelationRecommend> list = recommendList;
		String sql1 = "insert into relation_recommend (rec_code, subject_code,subject_class,create_time, modify_time, rec_user_code,rec_org_code,rec_to_org_code,tenant_code,rec_source, team_code,quality_code,rec_quality_time,  rec_status,remark) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		jdbcTemplate.batchUpdate(sql1, new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getRecCode());
				ps.setString(2, list.get(i).getSubjectCode());
				ps.setByte(3, list.get(i).getSubjectClass());
				ps.setLong(4, list.get(i).getCreateTime());
				ps.setLong(5, list.get(i).getModifyTime());
				ps.setString(6, list.get(i).getRecUserCode());
				ps.setString(7, list.get(i).getRecOrgCode());
				ps.setString(8, list.get(i).getRecToOrgCode());
				ps.setString(9, list.get(i).getTenantCode());
				ps.setByte(10, list.get(i).getRecSource());
				ps.setString(11, list.get(i).getTeamCode());
				ps.setString(12, list.get(i).getQualityCode());
				ps.setLong(13, list.get(i).getRecQualityTime());
				ps.setByte(14, list.get(i).getRecStatus());
				ps.setString(15, list.get(i).getRemark());
			}

		});

	}

	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public Integer mBlogSubjectInfoCount() {
		String sql = "SELECT COUNT(1) FROM `relation_recommend` WHERE `subject_class`=1";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	
	/**
	 * @Function mBlogSubjectInfo
	 * @Description 维护内容信息表（微博）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public long mBlogSubjectInfo(Integer pageNo, Integer pageSize) {

		// 获取推荐相关的微博数据
		String sql = "SELECT DISTINCT `subject_code` FROM `relation_recommend` WHERE `subject_class`=1 limit "
				+ pageNo * pageSize + " ," + pageSize + " ";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		List<Recommend> mBlogCodeList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<Recommend>(Recommend.class));
		List<RelationSubjectInfo> list = new ArrayList<RelationSubjectInfo>();
		
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		for (Recommend mBlogCode : mBlogCodeList) {
			selectedDataCount++;
			
			String sql1 = "SELECT * FROM `mblog_info` WHERE mblog_code='" + mBlogCode.getSubject_code() + "'";
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_MBLOG);
			
			try {
				MblogInfo mblogInfo = jdbcTemplate.queryForObject(sql1, new Object[] {},
						new BeanPropertyRowMapper<MblogInfo>(MblogInfo.class));

				RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
				relationSubjectInfo.setSubjectCode(mblogInfo.getMblogCode());
				relationSubjectInfo.setTenantCode("#############");
				relationSubjectInfo.setOrgCode(mblogInfo.getOrgCode()!=null?mblogInfo.getOrgCode():"");
				relationSubjectInfo.setCreateTime(mblogInfo.getCreateTime());
				relationSubjectInfo.setModifyTime(mblogInfo.getModify_time());
				relationSubjectInfo.setRemark("");
				relationSubjectInfo.setDynamicCode("");
				relationSubjectInfo.setSubjectClass((byte) 1);
				relationSubjectInfo.setSubjectDepend(mblogInfo.getMblogDepend());
				relationSubjectInfo.setTeamCode(mblogInfo.getTeamCode());
				relationSubjectInfo.setUserCode(mblogInfo.getUserCode());
				relationSubjectInfo.setSubjectPublishTime(mblogInfo.getPublishTime());
				relationSubjectInfo.setSubjectTitle("");
				relationSubjectInfo.setSubjectDigest("");
				relationSubjectInfo.setSubjectUrl("");
				relationSubjectInfo.setSubjectSource(mblogInfo.getMblogSource());//1.原创；2.转发
				relationSubjectInfo.setSourceCode(mblogInfo.getSourceMblogCode());
				relationSubjectInfo.setSubjectIsDelete(mblogInfo.getIsDelete());
				relationSubjectInfo.setSubjectIsShield(mblogInfo.getIsShield());
				
				MblogInfo mblogInfo2;
				if(mblogInfo.getMblogSource()==2){
					//获取原内容信息
					String sql2 = "SELECT * FROM `mblog_info` WHERE mblog_code='" + mblogInfo.getSourceMblogCode() + "'";
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_MBLOG);
					mblogInfo2 = jdbcTemplate.queryForObject(sql2, new Object[] {},
							new BeanPropertyRowMapper<MblogInfo>(MblogInfo.class));
				}else{
					mblogInfo2=new MblogInfo ();
				}
				
				relationSubjectInfo.setSourceDepend(mblogInfo2.getMblogDepend());
				relationSubjectInfo.setSourceTeamCode(mblogInfo2.getTeamCode());
				relationSubjectInfo.setSourceUserCode(mblogInfo2.getUserCode());
				relationSubjectInfo.setSourcePublishTime(mblogInfo2.getPublishTime());
				relationSubjectInfo.setSourceIsDelete(mblogInfo2.getIsDelete());
				relationSubjectInfo.setSourceIsShield(mblogInfo2.getIsShield());
				relationSubjectInfo.setContentTips("");

				list.add(relationSubjectInfo);
				importedDataCount++;
			} catch (DataAccessException e) {
				System.err.println("导入内容信息表时，对应微博记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;				
			}

		}
		
		insertSubjectInfo(list);
		
		System.err.println("#########导入微博内容信息表   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		// String sql3 = "SELECT * FROM relation_subject_info";
		// DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		// List<Map<String, Object>> list = jdbcTemplate.queryForList(sql3);
		// return list;
		return importedDataCount;

	}

	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public Integer blogSubjectInfoCount() {
		String sql = "SELECT COUNT(1) FROM `relation_recommend` WHERE `subject_class`=2";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	/**
	 * @Function blogSubjectInfo
	 * @Description 维护内容信息表（博客）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public long blogSubjectInfo(Integer pageNo, Integer pageSize) {

		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		
		// 获取推荐相关的微博数据
		String sql = "SELECT DISTINCT `subject_code` FROM `relation_recommend` WHERE `subject_class`=2 limit "
				+ pageNo * pageSize + " ," + pageSize + " ";
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		List<Recommend> blogCodeList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<Recommend>(Recommend.class));
		List<RelationSubjectInfo> list = new ArrayList<RelationSubjectInfo>();
		
		for (Recommend blogCode : blogCodeList) {
			selectedDataCount++;
			
			String sql1 = "SELECT *FROM `blog_info` WHERE `blog_code`='" + blogCode.getSubject_code() + "'";
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_BLOG);
			try {
				BlogInfo blogInfo = jdbcTemplate.queryForObject(sql1, new Object[] {},
						new BeanPropertyRowMapper<BlogInfo>(BlogInfo.class));

				RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
				relationSubjectInfo.setSubjectCode(blogInfo.getBlogCode());
				relationSubjectInfo.setTenantCode("#############");
				relationSubjectInfo.setOrgCode(blogInfo.getOrgCode());
				relationSubjectInfo.setCreateTime(blogInfo.getCreateTime());
				relationSubjectInfo.setModifyTime(blogInfo.getModifyTime());
				relationSubjectInfo.setRemark("");
				relationSubjectInfo.setDynamicCode("");
				relationSubjectInfo.setSubjectClass((byte) 2);
				relationSubjectInfo.setSubjectDepend(blogInfo.getBlogDepend());
				relationSubjectInfo.setTeamCode(blogInfo.getTeamCode());
				relationSubjectInfo.setUserCode(blogInfo.getUserCode());
				relationSubjectInfo.setSubjectPublishTime(blogInfo.getBlogPublishTime());
				relationSubjectInfo.setSubjectTitle(blogInfo.getBlogTitle());
				relationSubjectInfo.setSubjectDigest(blogInfo.getBlogDigest());
				relationSubjectInfo.setSubjectUrl("");
				relationSubjectInfo.setSubjectSource(blogInfo.getBlogSource());
				relationSubjectInfo.setSourceCode(blogInfo.getSourceBlogCode());
				relationSubjectInfo.setSourceDepend(blogInfo.getSourceBlogDepend());
				relationSubjectInfo.setSourceTeamCode(blogInfo.getSourceTeamCode());
				relationSubjectInfo.setSourceUserCode(blogInfo.getSourceUserCode());
				relationSubjectInfo.setContentTips(blogInfo.getBlogTips());
				relationSubjectInfo.setSubjectIsDelete(blogInfo.getBlogIsDelete());
				relationSubjectInfo.setSubjectIsShield(blogInfo.getBlogIsShield());
				
				BlogInfo blogInfo2;
				if(blogInfo.getBlogSource()==2){
					//获取原内容信息
					String sql2 = "SELECT *FROM `blog_info` WHERE `blog_code`='" + blogInfo.getSourceBlogCode() + "'";
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_BLOG);
					blogInfo2 = jdbcTemplate.queryForObject(sql2, new Object[] {},
							new BeanPropertyRowMapper<BlogInfo>(BlogInfo.class));
				}else{
					blogInfo2=new BlogInfo();
				}
				relationSubjectInfo.setSourcePublishTime(blogInfo2.getBlogPlanPublishTime());
				relationSubjectInfo.setSourceIsDelete(blogInfo2.getBlogIsDelete());
				relationSubjectInfo.setSourceIsShield(blogInfo2.getBlogIsShield());

				list.add(relationSubjectInfo);
				importedDataCount++;
			} catch (DataAccessException e) {
				System.err.println("导入内容信息表时，对应博客记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;
			}
		}
		insertSubjectInfo(list);

		System.err.println("#########导入博客内容信息表   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		/*
		 * String sql3 = "SELECT * FROM relation_subject_info";
		 * DynamicDataSourceHolder.setDataSource(DataSourceConstants.
		 * MYSQL_RELATION); List<Map<String, Object>> list =
		 * jdbcTemplate.queryForList(sql3); return list;
		 */
		return importedDataCount;
	}

	/**
	 * @Function insertSubjectInfo
	 * @Description 插入内容数据
	 * @param subjectInfoList
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	private void insertSubjectInfo(List<RelationSubjectInfo> subjectInfoList) {

		final List<RelationSubjectInfo> list = subjectInfoList;
		String sql1 = "INSERT INTO relation_subject_info (subject_code, tenant_code,org_code, create_time, modify_time,remark, dynamic_code,subject_class, subject_depend,team_code, user_code, subject_publish_time,subject_title, source_depend, source_team_code,subject_url, subject_source, source_code,source_user_code, source_publish_time, source_is_delete,source_is_shield, subject_is_delete, subject_is_shield,subject_status, subject_begin_time, subject_end_time,subject_digest, content_tips) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		jdbcTemplate.batchUpdate(sql1, new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getSubjectCode());
				ps.setString(2, list.get(i).getTenantCode());
				ps.setString(3, list.get(i).getOrgCode());
				ps.setLong(4, list.get(i).getCreateTime());
				ps.setLong(5, list.get(i).getModifyTime());

				ps.setString(6, list.get(i).getRemark());
				ps.setString(7, list.get(i).getDynamicCode());
				ps.setByte(8, list.get(i).getSubjectClass());
				ps.setByte(9, list.get(i).getSubjectDepend());
				ps.setString(10, list.get(i).getTeamCode());

				ps.setString(11, list.get(i).getUserCode());
				ps.setLong(12, list.get(i).getSubjectPublishTime());
				ps.setString(13, list.get(i).getSubjectTitle());
				ps.setByte(14, list.get(i).getSourceDepend());
				ps.setString(15, list.get(i).getSourceTeamCode());

				ps.setString(16, list.get(i).getSubjectUrl());
				ps.setByte(17, list.get(i).getSubjectSource());
				ps.setString(18, list.get(i).getSourceCode());
				ps.setString(19, list.get(i).getSourceUserCode());
				ps.setLong(20, list.get(i).getSourcePublishTime());

				ps.setByte(21, list.get(i).getSourceIsDelete());
				ps.setByte(22, list.get(i).getSourceIsShield());
				ps.setByte(23, list.get(i).getSubjectIsDelete());
				ps.setByte(24, list.get(i).getSubjectIsShield());
				ps.setByte(25, list.get(i).getSubjectStatus());

				ps.setLong(26, list.get(i).getSubjectBeginTime());
				ps.setLong(27, list.get(i).getSubjectEndTime());
				ps.setString(28, list.get(i).getSubjectDigest());
				ps.setString(29, list.get(i).getContentTips());
			}

		});

	}

	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public Integer blogSubjectDataCount() {
		String sql = "SELECT COUNT(1) FROM `relation_subject_info` WHERE `subject_class`=2";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	
	/**
	 * @Function blogSubjectData
	 * @Description 维护内容数据表（博客）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public long blogSubjectData(Integer pageNo, Integer pageSize) {


		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		
		
		// 获取推荐相关的微博数据
		String sql = "SELECT *FROM `relation_subject_info` WHERE subject_class =2 limit " + pageNo * pageSize + " ,"
				+ pageSize + " ";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		List<RelationSubjectInfo> subjectInfoList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<RelationSubjectInfo>(RelationSubjectInfo.class));
		List<RelationSubjectData> list = new ArrayList<RelationSubjectData>();
		for (RelationSubjectInfo subjectInfo : subjectInfoList) {
			selectedDataCount++;
			
			RelationSubjectData relationSubjectData = new RelationSubjectData();
			relationSubjectData.setSubjectCode(subjectInfo.getSubjectCode());
			relationSubjectData.setTenantCode("#############");
			relationSubjectData.setCityQualityNum(0);
			relationSubjectData.setGroupQualityNum(0);
			relationSubjectData.setProvinceQualityNum(0);
			relationSubjectData.setTeamQualityNum(0);
			relationSubjectData.setOrgCode(subjectInfo.getOrgCode());
			relationSubjectData.setCollectNum(0);
			relationSubjectData.setShareNum(0);
			relationSubjectData.setRemark("");
			relationSubjectData.setJoinNum(0);
			relationSubjectData.setSubjectDataCode(UUIDHelper.getUUID());
			
			// 获取浏览量，转发量
			String sql1 = "SELECT *FROM `blog_data` WHERE `blog_code`='" + subjectInfo.getSubjectCode() + "'";
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_BLOG);
			BlogData blogData = jdbcTemplate.queryForObject(sql1, new Object[] {},
					new BeanPropertyRowMapper<BlogData>(BlogData.class));
			relationSubjectData.setReadNum(blogData.getReadNum());
			relationSubjectData.setCommentNum(blogData.getCommentNum());
			relationSubjectData.setForwardNum(blogData.getForwardNum());
			relationSubjectData.setLikeNum(blogData.getLikeNum());
			relationSubjectData.setModifyTime(blogData.getCreateTime());
			relationSubjectData.setCreateTime(blogData.getCreateTime());
			
			list.add(relationSubjectData);
			importedDataCount++;
		}
		insertSubjectData(list);
		System.err.println("#########导入博客内容数据表   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		/*
		 * String sql3 = "SELECT * FROM relation_subject_data";
		 * DynamicDataSourceHolder.setDataSource(DataSourceConstants.
		 * MYSQL_RELATION); List<Map<String, Object>> list =
		 * jdbcTemplate.queryForList(sql3); return list;
		 */
		return importedDataCount;
	}

	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public Integer mBlogSubjectDataCount() {
		String sql = "SELECT COUNT(1) FROM `relation_subject_info` WHERE `subject_class`=1";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	
	/**
	 * @Function mBlogSubjectData
	 * @Description 维护内容数据表（微博）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public long mBlogSubjectData(Integer pageNo, Integer pageSize) {
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		
		// 获取推荐相关的微博数据
		String sql = "SELECT *FROM `relation_subject_info`WHERE  subject_class=1 limit " + pageNo * pageSize + " ,"
				+ pageSize + " ";
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		List<RelationSubjectInfo> subjectInfoList = jdbcTemplate.query(sql, new Object[] {},
				new BeanPropertyRowMapper<RelationSubjectInfo>(RelationSubjectInfo.class));
		List<RelationSubjectData> list = new ArrayList<RelationSubjectData>();
		for (RelationSubjectInfo subjectInfo : subjectInfoList) {
			selectedDataCount++;
			RelationSubjectData relationSubjectData = new RelationSubjectData();
			relationSubjectData.setSubjectCode(subjectInfo.getSubjectCode());
			relationSubjectData.setTenantCode("#############");
			relationSubjectData.setCityQualityNum(0);
			relationSubjectData.setTeamQualityNum(0);
			relationSubjectData.setGroupQualityNum(0);
			relationSubjectData.setProvinceQualityNum(0);
			relationSubjectData.setJoinNum(0);
			relationSubjectData.setRemark("");
			relationSubjectData.setShareNum(0);
			relationSubjectData.setSubjectDataCode(UUIDHelper.getUUID());
			relationSubjectData.setOrgCode(subjectInfo.getOrgCode());
			relationSubjectData.setReadNum(0);
			
			// 获取浏览量，转发量
			String sql1 = "SELECT *FROM `mblog_num` WHERE `num_source`=1 AND `subject_code`='"
					+ subjectInfo.getSubjectCode() + "'";
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_MBLOG);
			MblogNum mblogNum = jdbcTemplate.queryForObject(sql1, new Object[] {},
					new BeanPropertyRowMapper<MblogNum>(MblogNum.class));
			relationSubjectData.setModifyTime(mblogNum.getCreateTime());
			relationSubjectData.setCreateTime(mblogNum.getCreateTime());
			relationSubjectData.setCommentNum(mblogNum.getCommentNum());
			relationSubjectData.setForwardNum(mblogNum.getForwardNum());
			relationSubjectData.setLikeNum(mblogNum.getPraise_num());
			relationSubjectData.setCollectNum(mblogNum.getCollect_num());

			list.add(relationSubjectData);
			importedDataCount++;
		}
		insertSubjectData(list);

		System.err.println("#########导入微博内容数据表   本次查询出 : "+selectedDataCount+"  实际导入 : "+importedDataCount+" 放弃  "+invalidDataCount+"");
		// String sql3 = "SELECT * FROM relation_subject_data";
		// DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		// List<Map<String, Object>> list = jdbcTemplate.queryForList(sql3);
		// return list;
		return importedDataCount;
	}

	/**
	 * @Function insertSubjectData
	 * @Description 插入内容数据表
	 * @param list
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	private void insertSubjectData(List<RelationSubjectData> subjectDataList) {

		final List<RelationSubjectData> list = subjectDataList;
		String sql1 = "INSERT INTO relation_subject_data (subject_data_code, tenant_code,org_code,create_time,modify_time, remark, subject_code,team_quality_num, group_quality_num, province_quality_num,city_quality_num, join_num, read_num,like_num, collect_num, forward_num,comment_num, share_num) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_RELATION);
		jdbcTemplate.batchUpdate(sql1, new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, list.get(i).getSubjectDataCode());
				ps.setString(2, list.get(i).getTenantCode());
				ps.setString(3, list.get(i).getOrgCode());
				ps.setLong(4, list.get(i).getCreateTime());
				ps.setLong(5, list.get(i).getModifyTime());

				ps.setString(6, list.get(i).getRemark());
				ps.setString(7, list.get(i).getSubjectCode());
				ps.setInt(8, list.get(i).getTeamQualityNum());
				ps.setInt(9, list.get(i).getGroupQualityNum());
				ps.setInt(10, list.get(i).getProvinceQualityNum());

				ps.setInt(11, list.get(i).getCityQualityNum());
				ps.setLong(12, list.get(i).getJoinNum());
				ps.setInt(13, list.get(i).getReadNum());
				ps.setInt(14, list.get(i).getLikeNum());
				ps.setInt(15, list.get(i).getCollectNum());

				ps.setInt(16, list.get(i).getForwardNum());
				ps.setInt(17, list.get(i).getCommentNum());
				ps.setInt(18, list.get(i).getShareNum());

			}

		});

	}

}
