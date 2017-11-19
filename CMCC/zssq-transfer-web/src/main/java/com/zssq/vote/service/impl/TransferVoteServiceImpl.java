package com.zssq.vote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.StringTools;
import com.zssq.vote.VoteTrConstants;
import com.zssq.vote.dao.mapper.BaseSysOrgInfoMapper;
import com.zssq.vote.dao.mapper.BaseTeamInfoMapper;
import com.zssq.vote.dao.mapper.TransVoteCommentMapper;
import com.zssq.vote.dao.mapper.TransVoteMapper;
import com.zssq.vote.dao.mapper.TransVoteOptionsMapper;
import com.zssq.vote.dao.mapper.TransVoteUserMapper;
import com.zssq.vote.dao.mapper.VoteCommentMapper;
import com.zssq.vote.dao.mapper.VoteCommentReplyMapper;
import com.zssq.vote.dao.mapper.VoteInfoMapper;
import com.zssq.vote.dao.mapper.VoteJoinMapper;
import com.zssq.vote.dao.mapper.VoteOptionsMapper;
import com.zssq.vote.pojo.BaseSysOrgInfo;
import com.zssq.vote.pojo.BaseTeamInfo;
import com.zssq.vote.pojo.TransVoteOptions;
import com.zssq.vote.pojo.TransVoteOptionsExample;
import com.zssq.vote.pojo.VoteComment;
import com.zssq.vote.pojo.VoteCommentReply;
import com.zssq.vote.pojo.VoteInfo;
import com.zssq.vote.pojo.VoteJoin;
import com.zssq.vote.pojo.VoteOptions;
import com.zssq.vote.service.ITransferVoteService;
import com.zssq.vote.thread.ThreadPoolVote;

//@Transactional
@Service("transferVoteService")
public class TransferVoteServiceImpl implements ITransferVoteService {

	//private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private TransVoteMapper transVoteMapper;
	@Autowired
	private TransVoteOptionsMapper transVoteOptionsMapper;
	@Autowired
	private TransVoteUserMapper transVoteUserMapper;
	@Autowired
	private VoteInfoMapper voteInfoMapper;
	@Autowired
	private TransVoteCommentMapper transVoteCommentMapper;
	@Autowired
	private VoteCommentMapper voteCommentMapper;
	@Autowired
	private VoteCommentReplyMapper voteCommentReplyMapper;
	@Autowired
	private VoteOptionsMapper voteOptionsMapper;
	@Autowired
	private VoteJoinMapper voteJoinMapper;

	@Autowired
	private BaseSysOrgInfoMapper baseSysOrgInfoMapper;
	@Autowired
	private BaseTeamInfoMapper baseTeamInfoMapper;

	public ConcurrentHashMap<Integer, OptionRefNum> optionsMap = new ConcurrentHashMap<Integer, OptionRefNum>();

	@Override
	public int count() throws Exception {
		return transVoteMapper.queryOriginalVoteOfCount();
	}

	@Override
	public String transfer(int voteId, Logger log) {
		String voteInfoCode = "";
		try {
			log.debug("***###TransferVoteServiceImpl.transfer开始###***voteId=" + voteId);
			Map<String, Object> voteMap = transVoteMapper.queryOriginalVoteOfOne(voteId);
			if (voteMap != null) {
				voteInfoCode = first(voteMap, log);
			}
			log.debug("***###TransferVoteServiceImpl.transfer结束###***voteId=" + voteId);
		} catch (Throwable e) {
			log.error("***###TransferVoteServiceImpl.transfer出错，扔到补发线程池中###***voteId=" + voteId, e);
			ThreadPoolVote.addSingleTask(voteId);
		}
		return voteInfoCode;
	}

	// @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false,
	// rollbackFor = Throwable.class)
	// @Transactional
	@Override
	public void transferBatch(boolean pageFlag, int startNum, int endNum, Logger log) {
		int voteId = 0;
		try {
			log.debug("***###Thread:" + Thread.currentThread().getName() + ",transferBatch开始###***pageFlag=" + pageFlag
					+ ",startNum=" + startNum + ",endNum=" + endNum);
			// 组装查询参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (pageFlag) {
				paramMap.put("limitStart", startNum);
				paramMap.put("limitEnd", endNum);
			}
			// 创建一个计时器
			// StopWatch watch = new StopWatch();
			// 计时器开始
			// watch.start();

			List<Map<String, Object>> voteList = transVoteMapper.queryOriginalVote(paramMap);
			if (voteList != null && voteList.size() > 0) {
				log.debug("本次程序待处理源数据的vote数量：" + voteList.size());

				for (int i = 0; i < voteList.size(); i++) {
					log.debug("执行第" + (i + 1) + "次循环程序");

					// 源数据投票ID
					voteId = Integer.parseInt(voteList.get(i).get("voteId").toString());
					try {
						first(voteList.get(i), log);
					} catch (Throwable e) {
						ThreadPoolVote.addSingleTask(voteId);
						log.error("***###Thread:" + Thread.currentThread().getName() + ",迁移" + voteId + "数据失败，将此条数据扔到补发线程池中###***", e);
					}
				}
			} else {
				log.debug("Thread:" + Thread.currentThread().getName() + ",没有需要待迁移的源投票数据^-^。。。。");
			}
			// 计时器停止
			// watch.stop();
			// 获取计时器计时时间
			// Long time = watch.getTime();
			log.debug("***###Thread:" + Thread.currentThread().getName() + ",transferBatch结束###***");

		} catch (Throwable e) {
			log.error("***###Thread:" + Thread.currentThread().getName() + ",transferBatch出错###***voteId=" + voteId, e);
			//ThreadPoolVote.addSingleTask(voteId);
		}
	}

	/**
	 * 处理投票主表信息
	 * 返回生成的voteInfoCode
	 * @Function transferFirst
	 * @Description
	 * @param voteMap
	 * @throws Exception
	 */
	// @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false,
	// rollbackFor = Throwable.class)
	public String first(Map<String, Object> voteMap, Logger log) throws Exception {
		// 开启事物
		DataSourceTransactionManager tm = SpringContextUtil.getBean("transactionManagerVote",
				DataSourceTransactionManager.class);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus ts = tm.getTransaction(def);
		StringBuffer debugInfo = new StringBuffer();
		VoteInfo voteInfo = new VoteInfo();
		// 源数据投票ID
		Integer voteId = Integer.parseInt(voteMap.get("voteId").toString());
		debugInfo.append("源数据投票ID：voteId=").append(voteId).append(",");
		
		// 用源投票ID，保证id和createTime都是正向增长的
		voteInfo.setId(voteId.longValue());

		// 数据对比用，记录vote_id
		//voteInfo.setRemark(voteId + "");

		// 生成投票code
		String voteInfoCode = UUIDHelper.getUUID();
		voteInfo.setCode(voteInfoCode);
		debugInfo.append("voteInfoCode=").append(voteInfoCode).append(",");

		log.debug(debugInfo);
		try {
			// 租户标识
			String tenantCode = (String) voteMap.get("saasTenantCode");
			if (StringTools.isEmpty(tenantCode)) {
				tenantCode = VoteTrConstants.TENANT_CODE;
			}
			voteInfo.setTenantCode(tenantCode);
			// 组织机构编码
			String orgCode = (String) voteMap.get("manOrgCode");
			if (StringTools.isEmpty(orgCode)) {
				//orgCode = VoteTrConstants.NO_DATA;
				log.debug("XXXXXXXXXXXX源投票voteId="+voteId+"找不到orgCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
				// 回滚事务
				tm.rollback(ts);
				return null;
			}
			voteInfo.setOrgCode(orgCode);
			// 创建时间
			Date cTime = (Date) voteMap.get("createTime");
			long createTime = cTime.getTime();
			voteInfo.setCreateTime(createTime);
			// 修改时间
			Date uTime = (Date) voteMap.get("updateTime");
			long modifyTime = uTime.getTime();
			voteInfo.setModifyTime(modifyTime);
			// 发起人
			String sponsorCode = (String) voteMap.get("userCode");
			if (StringTools.isEmpty(sponsorCode)) {
				//sponsorCode = VoteTrConstants.NO_DATA;
				log.debug("XXXXXXXXXXXX源投票voteId="+voteId+"找不到sponsorCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
				// 回滚事务
				tm.rollback(ts);
				return null;
			}
			voteInfo.setSponsorCode(sponsorCode);
			debugInfo.append("sponsorCode=").append(sponsorCode).append(",");

			// 发起人类型(1-个人 2-班组 3-门户管理员)
			// 1-个人投票;2-班组投票;3-地区投票;4-省投票;5-集团投票;
			String voteType = String.valueOf(voteMap.get("voteType") == null ? "" : voteMap.get("voteType"));
			String sponsorType = voteType;
			if ("3".equals(voteType) || "4".equals(voteType) || "5".equals(voteType)) {
				sponsorType = "3";
			}
			voteInfo.setSponsorType(sponsorType);
			debugInfo.append("sponsorType=").append(sponsorType).append(",");

			// 发起机构CODE
			String sponsorOrgCode = "";
			if ("2".equals(voteType)) {
				// 传入teamId返回teamCode
				String teamId = String.valueOf(voteMap.get("teamId") == null ? "" : voteMap.get("teamId"));
				debugInfo.append("源数据teamId=").append(teamId).append(",");
				BaseTeamInfo baseTeamInfo = baseTeamInfoMapper.queryTeamInfoByTeamId(Integer.parseInt(teamId));
				if (baseTeamInfo == null) {
					//sponsorOrgCode = VoteTrConstants.NO_DATA;
					log.debug("XXXXXXXXXXXX源投票voteId="+voteId+"找不到sponsorOrgCode(班组发起)，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
					// 回滚事务
					tm.rollback(ts);
					return null;
				} else {
					sponsorOrgCode = baseTeamInfo.getTeamCode();
				}
				debugInfo.append("sponsorOrgCode=teamCode").append(sponsorOrgCode).append(",");
			} else if ("3".equals(voteType)) {
				String cityId = String.valueOf(voteMap.get("cityId") == null ? "" : voteMap.get("cityId"));
				debugInfo.append("源数据cityId=").append(cityId).append(",");
				BaseSysOrgInfo sysOrgInfo = baseSysOrgInfoMapper.queryOrgInfoByOrgId(Integer.valueOf(cityId));
				if (sysOrgInfo == null) {
					//sponsorOrgCode = VoteTrConstants.NO_DATA;
					log.debug("XXXXXXXXXXXX源投票voteId="+voteId+"找不到sponsorOrgCode(市级发起)，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
					// 回滚事务
					tm.rollback(ts);
					return null;
				} else {
					sponsorOrgCode = sysOrgInfo.getManOrgCode();
				}
				debugInfo.append("sponsorOrgCode=manOrgCode").append(sponsorOrgCode).append(",");
			} else if ("4".equals(voteType)) {
				String provinceId = String.valueOf(voteMap.get("provinceId") == null ? "" : voteMap.get("provinceId"));
				debugInfo.append("源数据provinceId=").append(provinceId).append(",");
				BaseSysOrgInfo sysOrgInfo = baseSysOrgInfoMapper.queryOrgInfoByOrgId(Integer.valueOf(provinceId));
				if (sysOrgInfo == null) {
					//sponsorOrgCode = VoteTrConstants.NO_DATA;
					log.debug("XXXXXXXXXXXX源投票voteId="+voteId+"找不到sponsorOrgCode(省级)，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
					// 回滚事务
					tm.rollback(ts);
					return null;
				} else {
					sponsorOrgCode = sysOrgInfo.getManOrgCode();
				}
				debugInfo.append("sponsorOrgCode=manOrgCode").append(sponsorOrgCode).append(",");
			} else if ("5".equals(voteType)) {
				sponsorOrgCode = VoteTrConstants.GROUP_CODE;
				debugInfo.append("发起机构：集团sponsorOrgCode=manOrgCode").append(sponsorOrgCode).append(",");
			}
			if (StringTools.isNotEmpty(sponsorOrgCode)) {
				voteInfo.setSponsorOrgCode(sponsorOrgCode);
			}
			// 状态:5-结束
			voteInfo.setVoteStatus((byte) 5);
			// 投票主题
			String title = (String) voteMap.get("voteTitle");
			if (StringTools.isEmpty(title)) {
				title = VoteTrConstants.NO_DATA;
			}
			voteInfo.setTitle(title);
			// 说明
			String voteExplain = (String) voteMap.get("voteDeclare");
			if (StringTools.isNotEmpty(voteExplain)) {
				voteInfo.setVoteExplain(voteExplain);
			}
			// 是否可多选
			Integer optionsType = Integer.valueOf(String.valueOf(voteMap.get("optionsType")));
			byte isMultiOption = 0;
			if (optionsType > 1) {
				isMultiOption = 1;
			}
			voteInfo.setIsMultiOption(isMultiOption);
			debugInfo.append("isMultiOption").append(isMultiOption).append(",");

			// 是否启用评论
			voteInfo.setIsEnableComment((byte) 1);
			// 开始时间
			Date voteStartTime = (Date) voteMap.get("voteStartTime");
			voteInfo.setStartTime(voteStartTime.getTime());
			// 结束时间
			Date voteEndTime = (Date) voteMap.get("voteEndTime");
			voteInfo.setEndTime(voteEndTime.getTime());

			log.debug(debugInfo);

			// 进行第二步
			int joinNum = second(voteId, voteInfoCode, tenantCode, orgCode, createTime, modifyTime, log);
			voteInfo.setJoinNum(joinNum);

			// 进行第三步
			int commentNum = third(voteId, voteInfoCode, log);
			voteInfo.setCommentNum(commentNum);

			// insert投票主表数据
			int result = voteInfoMapper.insertSelective(voteInfo);
			log.debug("&&&&源投票ID=" + voteId + ",迁移后投票CODE=" + voteInfoCode + "数据整理成功，进行下一条投票数据迁移整理操作&&&&");
			// 提交事务
			if (!ts.isCompleted()) {
				tm.commit(ts);
			}
			return voteInfoCode;
		} catch (Exception e) {
			// 回滚事务
			if (!ts.isCompleted()) {
				tm.rollback(ts);
			}
			log.error("%%%%%%%TransferVoteServiceImpl.first()出错啦！,voteId=" + voteId + ",voteInfoCode=" + voteInfoCode);
			throw e;
		}

	}

	// 迁移投票选项数据，用户参与投票数据，返回此投票的参与数量
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
	// rollbackFor = Throwable.class)
	public int second(final int voteId, final String voteInfoCode, String tenantCode, String orgCode, long createTime,
			long modifyTime, Logger log) throws Exception {
		int joinNum = 0;
		try {
			// 1.查询此投票的所有选项
			TransVoteOptionsExample example = new TransVoteOptionsExample();
			TransVoteOptionsExample.Criteria criteria = example.createCriteria();

			criteria.andVoteIdEqualTo(voteId);
			example.setOrderByClause("POSITION asc");

			List<VoteOptions> voteOptionsList = new ArrayList<VoteOptions>();
			
			List<TransVoteOptions> optionsList = transVoteOptionsMapper.selectByExample(example);
			if (optionsList != null && optionsList.size() > 0) {
				log.debug("投票voteId=" + voteId + ",voteInfoCode=" + voteInfoCode + ",选项数据迁移开始。。");
				for (TransVoteOptions trOptions : optionsList) {
					VoteOptions vo = new VoteOptions();
					// 用源投票选项ID，保证id和createTime都是正向增长的
					vo.setId(trOptions.getOptionsId().longValue());
					// CODE值
					vo.setCode(UUIDHelper.getUUID());
					// 租户标识
					vo.setTenantCode(tenantCode);
					// 组织机构code
					vo.setOrgCode(orgCode);
					// 创建时间&修改时间
					vo.setCreateTime(createTime);
					vo.setModifyTime(modifyTime);
					// 投票主表CODE
					vo.setVoteInfoCode(voteInfoCode);
					// 选项序号
					vo.setOrderNumber(trOptions.getPosition());
					// 选项内容
					vo.setContent(trOptions.getOptionsContent());
					// 选择人数，ps：直接拿源数据可能造成不准确，此处应该按实际情况统计
					//vo.setSelectedNum(trOptions.getVoteNum());

					// 选项id与选项序号集合，现有的设计是存的选项序号
					OptionRefNum orn = new OptionRefNum();
					orn.setOrderNumber(trOptions.getPosition());
					
					optionsMap.put(trOptions.getOptionsId(), orn);
					
					voteOptionsList.add(vo);
				}
				log.debug("voteId=" + voteId + ",voteInfoCode=" + voteInfoCode + ",选项数据迁移结束。。");
			} else {
				log.debug("投票voteId=" + voteId + ",voteInfoCode=" + voteInfoCode + ",没有需要待迁移的选项数据！");
			}

			// 2.查询此投票的所有参与记录
			List<Map<String, Object>> joinList = transVoteUserMapper.queryVoteUserByUnion(voteId);
			if (joinList != null && joinList.size() > 0) {
				log.debug("投票voteId=" + voteId + ",voteInfoCode=" + voteInfoCode + ",参与记录数据迁移开始。。");

				VoteJoin vj = new VoteJoin();
				for (int i = 0; i < joinList.size(); i++) {
					StringBuffer debugInfo = new StringBuffer();

					Map<String, Object> joinRecord = joinList.get(i);

					Integer voteUserId = (Integer) joinRecord.get("voteUserId");

					debugInfo.append("源数据参与记录ID(voteUserId)=").append(voteUserId).append(",");

					// 租户标识
					String joinTenantCode = (String) joinRecord.get("saasTenantCode");
					if (StringTools.isEmpty(joinTenantCode)) {
						joinTenantCode = VoteTrConstants.TENANT_CODE;
					}
					// 组织机构编码
					String joinOrgCode = (String) joinRecord.get("manOrgCode");
					if (StringTools.isEmpty(joinOrgCode)) {
						//joinOrgCode = VoteTrConstants.NO_DATA;
						log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据参与记录ID(voteUserId)=" + voteUserId
								+ "找不到orgCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
						continue;
					}
					// 创建时间(参与时间)&修改时间
					Date cTime = (Date) joinRecord.get("voteDate");
					long joinTime = cTime.getTime();
					// 参与用户CODE
					String joinUserCode = (String) joinRecord.get("userCode");
					if (StringTools.isEmpty(joinUserCode)) {
						//joinUserCode = VoteTrConstants.NO_DATA;
						log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据参与记录ID(voteUserId)=" + voteUserId
								+ "找不到joinUserCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
						continue;
					}
					// 选项序号
					Integer voteOptions = Integer.parseInt(joinRecord.get("voteOptions").toString());

					OptionRefNum orn = optionsMap.get(voteOptions);
					// 对应的选项序号
					String selectedNumber = String.valueOf(orn.getOrderNumber());
					// 此选项被选择的次数
					int choseNum = orn.getSelectedNum();

					if (StringTools.isEmpty(vj.getCode())) {
						// 用源投票参与记录ID，保证id和createTime都是正向增长的
						vj.setId(voteUserId.longValue());
						vj.setCode(UUIDHelper.getUUID());
						vj.setTenantCode(joinTenantCode);
						vj.setOrgCode(joinOrgCode);
						vj.setCreateTime(joinTime);
						vj.setModifyTime(joinTime);
						vj.setJoinUserCode(joinUserCode);
						vj.setVoteInfoCode(voteInfoCode);
						vj.setSelectedNumber(selectedNumber);
						// 选中次数+1
						orn.setSelectedNum(choseNum + 1);

					} else if (i > 0 && joinUserCode.equals(vj.getJoinUserCode())) {
						boolean flag = true;
						String[] strs = vj.getSelectedNumber().split("\\|");
						for (String s : strs) {
							if (s.equals(selectedNumber)) {
								flag = false;
								break;
							}
						}
						if (flag) {
							vj.setSelectedNumber(new StringBuffer(vj.getSelectedNumber()).append("|")
									.append(selectedNumber).toString());
							// 选中次数+1
							orn.setSelectedNum(choseNum + 1);
						}
					} else {
						debugInfo.append("selectedNumber=").append(vj.getSelectedNumber()).append(",");
						log.debug(debugInfo);
						voteJoinMapper.insertSelective(vj);
						++joinNum;

						vj = new VoteJoin();
						// 用源投票参与记录ID，保证id和createTime都是正向增长的
						vj.setId(voteUserId.longValue());
						vj.setCode(UUIDHelper.getUUID());
						vj.setTenantCode(joinTenantCode);
						vj.setOrgCode(joinOrgCode);
						vj.setCreateTime(joinTime);
						vj.setModifyTime(joinTime);
						vj.setJoinUserCode(joinUserCode);
						vj.setVoteInfoCode(voteInfoCode);
						vj.setSelectedNumber(selectedNumber);
						// 选中次数+1
						orn.setSelectedNum(choseNum + 1);
					}
					if (i == joinList.size() - 1) {
						debugInfo.append("selectedNumber=").append(vj.getSelectedNumber()).append(",");
						log.debug(debugInfo);
						voteJoinMapper.insertSelective(vj);
						++joinNum;
					}
					optionsMap.put(voteOptions, orn);
				}
			} else {
				log.debug("投票voteId=" + voteId + ",voteInfoCode=" + voteInfoCode + ",没有需要待迁移的参与记录数据！");
			}
			
			// 由于要统计每个选项的被选中次数，故此操作要等参与记录执行完
			for (VoteOptions voteOptions : voteOptionsList) {
				OptionRefNum orn = optionsMap.get(voteOptions.getId().intValue());
				
				voteOptions.setSelectedNum(orn.getSelectedNum());
				
				voteOptionsMapper.insertSelective(voteOptions);
			}
		} catch (Exception e) {
			throw e;
		}
		return joinNum;
	}

	// 迁移投票评论回复数据
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
	// rollbackFor = Throwable.class)
	public int third(int voteId, String voteInfoCode, Logger log) throws Exception {
		log.debug("************* 投票voteId=" + voteId + "评论回复数据迁移*begin*************");
		int commentNum = 0;
		int replyNum = 0;

		// 查询投票的评论
		List<Map<String, Object>> cs = transVoteCommentMapper.selectComment(voteId);
		for (Map<String, Object> c : cs) {
			//log.debug("处理commentId=" + c.get("COMMENT_ID"));
			VoteComment comment = new VoteComment();
			Long cid = (Long) c.get("COMMENT_ID");
			// 使用源表的评论ID，保证id和createTime都是正向增长的
			comment.setId(cid);
			// 评论code
			String commentCode = UUIDHelper.getUUID();
			comment.setCode(commentCode);
			String tenantCode = (String) c.get("saasTenantCode");
			if (StringTools.isEmpty(tenantCode)) {
				comment.setTenantCode(VoteTrConstants.TENANT_CODE);
			} else {
				comment.setTenantCode(tenantCode);
			}

			String manOrgCode = (String) c.get("manOrgCode");
			if (StringTools.isEmpty(manOrgCode)) {
				//comment.setOrgCode(VoteTrConstants.NO_DATA);
				log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据评论ID" + cid
						+ ",找不到orgCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
				continue;
			} else {
				comment.setOrgCode(manOrgCode);
			}

			Date cDate = (Date) c.get("COMMENT_DATE");
			comment.setCreateTime(cDate.getTime());
			comment.setModifyTime(cDate.getTime());
			comment.setVoteInfoCode(voteInfoCode);

			String userCode = (String) c.get("userCode");
			if (StringTools.isEmpty(userCode)) {
				//comment.setCommenterCode(VoteTrConstants.NO_DATA);
				log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据评论ID" + cid
						+ ",找不到commenterCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
				continue;
			} else {
				comment.setCommenterCode(userCode);
			}
			String cont = (String) c.get("COMMENT_CONTENT");
			if (StringTools.isNotEmpty(cont)) {
				comment.setContent(cont);
			} else {
				comment.setContent(VoteTrConstants.NO_DATA);
			}

			// 查询评论的回复
			List<Map<String, Object>> rs = transVoteCommentMapper.selectReply(cid.intValue());
			if (rs != null) {
				for (Map<String, Object> r : rs) {
					VoteCommentReply reply = new VoteCommentReply();
					// 回复code
					String replyCode = UUIDHelper.getUUID();
					// 使用源数据表的回复ID，保证id和createTime是正向增长的
					Long rid = (Long) r.get("COMMENT_ID");
					reply.setId(rid);
					reply.setCode(replyCode);
					//log.debug("处理replyId=" + r.get("COMMENT_ID"));
					String rTenantCode = (String) r.get("saasTenantCode");
					if (StringTools.isEmpty(rTenantCode)) {
						reply.setTenantCode(VoteTrConstants.TENANT_CODE);
					} else {
						reply.setTenantCode(rTenantCode);
					}
					
					String rManOrgCode = (String) r.get("manOrgCode");
					if (StringTools.isEmpty(rManOrgCode)) {
						//reply.setOrgCode(VoteTrConstants.NO_DATA);
						log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据评论ID" + cid + ",源数据回复ID" + rid
								+ "找不到orgCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
						continue;
					} else {
						reply.setOrgCode(rManOrgCode);
					}
					
					Date rDate = (Date) r.get("COMMENT_DATE");
					reply.setCreateTime(rDate.getTime());
					reply.setModifyTime(rDate.getTime());
					reply.setVoteInfoCode(voteInfoCode);
					reply.setCommentCode(commentCode);
					
					String rUserCode = (String) r.get("userCode");
					if (StringTools.isEmpty(rUserCode)) {
						//reply.setReplierCode(VoteTrConstants.NO_DATA);
						log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据评论ID" + cid + ",源数据回复ID" + rid
								+ "找不到replierCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
						continue;
					} else {
						reply.setReplierCode(rUserCode);
					}
					String questCode = (String) r.get("questCode");
					if (StringTools.isEmpty(questCode)) {
						//reply.setQuestionerCode(VoteTrConstants.NO_DATA);
						log.debug("XXXXXXXXXXXX源投票voteId=" + voteId + "源数据评论ID" + cid + ",源数据回复ID" + rid
								+ "找不到questionerCode，与现有业务代码冲突，无法insert，舍弃XXXXXXXXX");
						continue;
					} else {
						reply.setQuestionerCode(questCode);
					}
					String rCon = (String) r.get("COMMENT_CONTENT");
					if (StringTools.isNotEmpty(rCon)) {
						reply.setContent(rCon);
					} else {
						reply.setContent(VoteTrConstants.NO_DATA);
					}
					
					voteCommentReplyMapper.insertSelective(reply);
					++replyNum;
				}
				comment.setReplyCount(replyNum);
			} else {
				log.debug("投票voteId="+voteId+",commentId=" + cid + " 没有回复数据");
			}

			voteCommentMapper.insertSelective(comment);
			// 评论计数器+1
			++commentNum;
		}
		log.debug("投票voteId=" + voteId + "共有 " + commentNum + "条评论，" + replyNum + "条回复");
		log.debug("************* 投票voteId=" + voteId + "评论回复数据迁移*end*************");
		return commentNum;
	}
	
}

class OptionRefNum {
	// 选项序号
	private int orderNumber;
	// 选择人数
	private int selectedNum = 0;
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getSelectedNum() {
		return selectedNum;
	}
	public void setSelectedNum(int selectedNum) {
		this.selectedNum = selectedNum;
	}
}
