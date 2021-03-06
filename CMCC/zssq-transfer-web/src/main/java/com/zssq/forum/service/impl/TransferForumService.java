package com.zssq.forum.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zssq.forum.dao.mapper.ForumPlatesMapper;
import com.zssq.forum.dao.mapper.ForumPlatesMemberMapper;
import com.zssq.forum.dao.mapper.ForumTeamInfoMapper;
import com.zssq.forum.dao.mapper.ForumTeamMemberMapper;
import com.zssq.forum.pojo.ForumPlates;
import com.zssq.forum.pojo.ForumPlatesMember;
import com.zssq.forum.pojo.ForumTeamMember;
import com.zssq.forum.pojo.ForumTeamMemberExample;
import com.zssq.forum.service.ITransferForumService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.SpringContextUtil;

@Service("transferForumService")
public class TransferForumService implements ITransferForumService {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ForumTeamInfoMapper forumTeamInfoMapper;

	@Autowired
	private ForumTeamMemberMapper forumTeamMemberMapper;

	@Autowired
	private ForumPlatesMapper forumPlatesMapper;

	@Autowired
	private ForumPlatesMemberMapper forumPlatesMemberMapper;

	@Override
	public void transfer(boolean pageFlag, int startNum, int endNum) throws Exception {

		try {
			// 组装查询参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (pageFlag) {
				paramMap.put("limitStart", startNum);
				paramMap.put("limitEnd", endNum);
			}
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();

			List<Map<String, Object>> forumList = forumTeamInfoMapper.queryOriginalForum(paramMap);
			if (forumList != null && forumList.size() > 0) {
				log.info("**transfer开始**");

				for (Map<String, Object> forumMap : forumList) {
					first(forumMap);
				}
			}
			// 计时器停止
			watch.stop();
			// 获取计时器计时时间
			Long time = watch.getTime();
			log.debug("################执行时间：##################" + time);

		} catch (Exception e) {
			log.error("*****transferStepOfOne出错*****", e);
			throw e;
		}

	}

	public void first(Map<String, Object> forumMap) throws Exception {
		// 开启事务
		DataSourceTransactionManager tm = SpringContextUtil.getBean("transactionManagerForum",
				DataSourceTransactionManager.class);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus ts = tm.getTransaction(def);
		try {
			ForumPlates forumPlates = new ForumPlates();
			// 生成论坛版块
			String forumPlatesCode = UUIDHelper.getUUID();
			forumPlates.setForumPlatesCode(forumPlatesCode);
			// 租户标识
			String tenantCode = (String) forumMap.get("tenantCode");
			forumPlates.setTenantCode(tenantCode);
			// 组织机构编码
			String orgCode = (String) forumMap.get("orgCode");
			forumPlates.setOrgCode(orgCode);
			// 创建时间
			forumPlates.setCreateTime((Long) forumMap.get("createTime"));
			// 修改时间
			forumPlates.setModifyTime((Long) forumMap.get("createTime"));
			// 论坛名称
			forumPlates.setPlatesName((String) forumMap.get("teamName"));
			// 班组Code
			String belongCode = (String) forumMap.get("teamCode");
			forumPlates.setBelongCode(belongCode);
			
			int result = forumPlatesMapper.insertSelective(forumPlates);

			log.info("** 班组论坛版块信息插入 id = "+ forumMap.get("id") + " 数据成功**");
			if (result > 0) {
				// 进行第二步
				second(belongCode, forumPlatesCode, tenantCode, orgCode);
			}

			// 提交事务
			if(!ts.isCompleted()){
				tm.commit(ts);
			}
		} catch (Exception e) {
			// 回滚事务
			if(!ts.isCompleted()){
				tm.rollback(ts);
			}
			throw e;
		}

	}

	
	public void second(String belongCode,String forumPlatesCode,String tenantCode, String orgCode) throws Exception {
		
		try{
			// 查询班组中所有成员
			ForumTeamMemberExample example = new ForumTeamMemberExample();
			ForumTeamMemberExample.Criteria criteria = example.createCriteria();
			
			criteria.andTeamCodeEqualTo(belongCode);
			example.setOrderByClause("id asc");
			
			List<ForumTeamMember> memberList = forumTeamMemberMapper.selectByExample(example);
			for (ForumTeamMember member : memberList) {
				ForumPlatesMember forumPlatesMember = new ForumPlatesMember();
				
				//生成论坛成员Code
				String forumPlatesMemberCode = UUIDHelper.getUUID();
				forumPlatesMember.setForumPlatesMemberCode(forumPlatesMemberCode);
				forumPlatesMember.setTenantCode(tenantCode);
				forumPlatesMember.setOrgCode(orgCode);
				forumPlatesMember.setCreateTime(member.getCreateTime());
				forumPlatesMember.setModifyTime(member.getCreateTime());
				
				//论坛版块Code
				forumPlatesMember.setForumPlatesCode(forumPlatesCode);
				forumPlatesMember.setMemberCode(member.getUserCode());
				forumPlatesMember.setIsModerator(member.getIsLeader());
				log.info("** 班组论坛成员插入 id = "+ member.getId()+ " 数据成功 **");
				forumPlatesMemberMapper.insertSelective(forumPlatesMember);
				
			}
		}catch(Exception e){
			log.error("*****transferStepOfTwo出错*****", e);
			throw e;
		}

		
	}

}
