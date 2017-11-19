package com.zssq.vote.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.service.ITeamElectService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.DateUtils;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationDynamicVO;
import com.zssq.vo.RelationOperateVO;
/**
 * 投票模块动态业务程序
 * @ClassName DynamicVoteProcedure
 * @Description 
 * @author liurong
 * @date 2017年4月20日 上午10:42:34
 * @version 1.0
 * @since JDK 1.7
 */
@Component("dynamicVoteProcedure")
public class DynamicVoteProcedure {
	
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	@Autowired
	private ITeamElectService teamElectService;
	@Autowired
	private RelationThirdOperateService relationThirdOperateService;
	
	/**
	 * 发布投票动态(个人或班组长)
	 * @Function publishDynamicOfVote
	 * @Description 
	 * @param voteInfo
	 * @param isDissolve 班组是否解散0-否  1-是
	 * @throws BusinessException
	 */
	public void publishDynamicOfSendVote(VoteInfo voteInfo, Byte isDissolve) throws BusinessException {
		long curTime = DateUtils.getFormatDateLong();
		// 组织动态表数据
		RelationDynamic rd = new RelationDynamic();
		rd.setTenantCode(voteInfo.getTenantCode());
		rd.setOrgCode(voteInfo.getOrgCode());
		rd.setOperateTime(curTime);
		rd.setCreateTime(curTime);
		rd.setModifyTime(curTime);
		rd.setDynamicCode(voteInfo.getDynamicCode());// 动态编号
		rd.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_VOTE);// 发布投票
		rd.setSubjectCode(voteInfo.getCode());
		rd.setDynamicIsDelete(RelationConstants.RELATION_NO);// 是否删除0.否；1.是
		rd.setDynamicIsShield(RelationConstants.RELATION_NO);// 是否屏蔽0.否；1.是
		rd.setIsSubjectShow(RelationConstants.RELATION_YES);// 是否显示内容
		rd.setIsSubjectDataShow(RelationConstants.RELATION_YES);// 是否显示内容数据
		rd.setUserCode(voteInfo.getSponsorCode());// 所属人编号
		
		// 组织动态班组关系表
		RelationDynamicTeamRel rdt = null;
		if (VoteConstants.SPONSOR_TYPE_PERSON.equals(voteInfo.getSponsorType())) {
			rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);// 从属关系1-个人
		}
		if (VoteConstants.SPONSOR_TYPE_CLASS.equals(voteInfo.getSponsorType())) {
			rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);// 从属关系2-班组
			rd.setTeamCode(voteInfo.getSponsorOrgCode());// 所属班组编号
			
			rdt = new RelationDynamicTeamRel();
			rdt.setRelCode(UUIDHelper.getUUID());
			rdt.setTenantCode(voteInfo.getTenantCode());
			rdt.setOrgCode(voteInfo.getOrgCode());
			rdt.setCreateTime(curTime);
			rdt.setModifyTime(curTime);
			rdt.setDynamicCode(voteInfo.getDynamicCode());
			rdt.setRelIsHomeShow(RelationConstants.RELATION_YES);// 是否首页展示
			rdt.setRelIsQuality(RelationConstants.RELATION_NO);// 是否精华
			rdt.setRelIsRecommend(RelationConstants.RELATION_NO);// 是否推荐
			
			rdt.setTeamCode(voteInfo.getSponsorOrgCode());// 班组编号
			rdt.setTeamIsDissolve(isDissolve);// 是否解散
			boolean isExcelent = teamElectService.isExcelent(voteInfo.getSponsorOrgCode());
			if (isExcelent) {
				rdt.setTeamIsExcellent(RelationConstants.RELATION_YES);// 班组是否百强班组
			} else {
				rdt.setTeamIsExcellent(RelationConstants.RELATION_NO);
			}
		}
		// 组织动态内容信息表
		RelationSubjectInfo rsi = new RelationSubjectInfo();
		rsi.setTenantCode(voteInfo.getTenantCode());
		rsi.setOrgCode(voteInfo.getOrgCode());
		rsi.setCreateTime(curTime);
		rsi.setModifyTime(curTime);
		rsi.setSubjectCode(voteInfo.getCode());// 内容编号(投票)
		rsi.setSubjectClass(RelationConstants.RELATION_SUBJECT_VOTE);// 内容类型3.投票
		rsi.setUserCode(voteInfo.getSponsorCode());// 发布人编号
		rsi.setSubjectPublishTime(voteInfo.getCreateTime());// 发布时间
		rsi.setSubjectTitle(voteInfo.getTitle());// 标题
		if (VoteConstants.SPONSOR_TYPE_PERSON.equals(voteInfo.getSponsorType())) {
			rsi.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);// 从属关系1-个人
		}
		if (VoteConstants.SPONSOR_TYPE_CLASS.equals(voteInfo.getSponsorType())) {
			rsi.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);// 从属关系2-班组
			rsi.setTeamCode(voteInfo.getSponsorOrgCode());
		}
		rsi.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
		rsi.setSubjectDigest(voteInfo.getVoteExplain());
		rsi.setSubjectBeginTime(voteInfo.getStartTime());// 开始时间
		rsi.setSubjectEndTime(voteInfo.getEndTime());// 结束时间
		rsi.setSubjectIsDelete(RelationConstants.RELATION_NO);// 内容是否删除
		rsi.setSubjectIsShield(RelationConstants.RELATION_NO);// 内容是否屏蔽

		
		relationThirdDynamicService.saveDynamic(rd, rdt, rsi, null);
	}
	/**
	 * 参与投票动态
	 * 修改relation中的参与量
	 * @Function publishDynamicOfJoinVote
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param voteInfoCode 投票编码
	 * @throws BusinessException
	 */
	public void publishDynamicOfJoinVote(String tenantCode, String orgCode, String joinUserCode, String voteInfoCode) throws BusinessException {
		// 参与投票动态
		sendJoinAndShareDynamic(tenantCode, orgCode, joinUserCode, voteInfoCode, RelationConstants.RELATION_DYNAMIC_JOIN_VOTE);
		
		// 修改relation中的参与量
		sendUpdNumInfo(voteInfoCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_JOIN, RelationConstants.NUM_ONE);
	}
	/**
	 * 分享投票动态
	 * 动态数据的分享数
	 * @Function publishDynamicOfShareVote
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param voteInfoCode 投票编码
	 * @throws BusinessException
	 */
	public void publishDynamicOfShareVote(String tenantCode, String orgCode, String joinUserCode, String voteInfoCode) throws BusinessException {
		sendJoinAndShareDynamic(tenantCode, orgCode, joinUserCode, voteInfoCode, RelationConstants.RELATION_DYNAMIC_SHARE_VOTE);
		
		// 修改动态数据中的分享数量
		sendUpdNumInfo(voteInfoCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE, RelationConstants.NUM_ONE);
	}
	/**
	 * 删除"发布投票"动态
	 * @Function deleteDynamicOfVote
	 * @Description 
	 * @param dynamicCode   动态编号
	 * @param voteInfoCode  投票CODE
	 * @param userCode      动态所属人CODE
	 * @throws BusinessException
	 */
	public void deleteDynamicOfVote(String dynamicCode, String voteInfoCode, String userCode) throws BusinessException {
		RelationDynamicVO rdv = new RelationDynamicVO();
		rdv.setDynamicCode(dynamicCode);// 动态编号
		rdv.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_VOTE);
		rdv.setSubjectCode(voteInfoCode);
		rdv.setSubjectClass(RelationConstants.RELATION_SUBJECT_VOTE);
		rdv.setUserCode(userCode);
		rdv.setModifyTime(DateUtils.getFormatDateLong());
		
		relationThirdDynamicService.deleteDynamic(rdv);
	}
	
	/**
	 * 发送参与or分享投票动态
	 * @Function sendJoinAndShareDynamic
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param voteInfoCode 投票编码
	 * @param dynamicType  动态类型：7.分享投票；8.参与投票；
	 * @throws BusinessException
	 */
	public void sendJoinAndShareDynamic(String tenantCode, String orgCode, String joinUserCode, String voteInfoCode, Byte dynamicType) throws BusinessException {
		long curTime = DateUtils.getFormatDateLong();
		// 组织动态表数据
		RelationDynamic rd = new RelationDynamic();
		rd.setDynamicCode(UUIDHelper.getUUID());
		rd.setTenantCode(tenantCode);
		rd.setOrgCode(orgCode);
		rd.setOperateTime(curTime);
		rd.setCreateTime(curTime);
		rd.setModifyTime(curTime);
		rd.setDynamicClass(dynamicType);// 动态类型：7.分享投票；8.参与投票；
		rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);// 从属关系1-个人
		rd.setUserCode(joinUserCode);// 所属人编号
		rd.setSubjectCode(voteInfoCode);// 内容编号
		rd.setDynamicIsDelete(RelationConstants.RELATION_NO);// 是否删除0-否；1-是；
		rd.setDynamicIsShield(RelationConstants.RELATION_NO);// 是否屏蔽0-否；1-是；
		rd.setIsSubjectShow(RelationConstants.RELATION_YES);// 是否显示内容(0-否；1-是；注：动态类型为16时该值为0；其余为1)
		rd.setIsSubjectDataShow(RelationConstants.RELATION_NO);// 是否显示内容数据(0-否；1-是；注：动态类型为1/2/3/4/6/12/13时该值为1；其余为0)
		
		
		relationThirdDynamicService.saveDynamic(rd, null, null, null);
	}
	
	
	/**
	 * 点赞操作（动态）
	 * 增加relation中的点赞量
	 * @Function saveVoteLike
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param remark       备注
	 * @param subjectCode  信息Code
	 * @param subjectClass 信息类型
	 * @param userCode     动态所属人CODE
	 * @throws BusinessException
	 */
	public void saveVoteLike(String tenantCode, String orgCode, String remark, String subjectCode, Byte subjectClass,
			String userCode) throws BusinessException {
		RelationLike relationLike = new RelationLike();
		relationLike.setLikeCode(UUIDHelper.getUUID());
		relationLike.setTenantCode(tenantCode);
		relationLike.setOrgCode(orgCode);
		relationLike.setCreateTime(DateUtils.getFormatDateLong());
		relationLike.setModifyTime(DateUtils.getFormatDateLong());
		relationLike.setRemark(remark);
		relationLike.setSubjectCode(subjectCode);
		relationLike.setSubjectClass(subjectClass);
		relationLike.setUserCode(userCode);

		relationThirdOperateService.saveLike(relationLike);
		// 更新投票relation中的点赞量
		sendUpdNumInfo(subjectCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE, RelationConstants.NUM_ONE);
	}
	
	
	/**
	 * 取消点赞操作（动态）
	 * 减少relation中的点赞量
	 * @Function deleteVoteLike
	 * @Description 取消点赞操作（动态）
	 * @param subjectCode  内容编号
	 * @param userCode     人员编号
	 * @throws BusinessException
	 */
	public void deleteVoteLike(String subjectCode, String userCode) throws BusinessException {
		RelationOperateVO relationOperateVO = new RelationOperateVO();
		relationOperateVO.setSubjectCode(subjectCode);
		relationOperateVO.setUserCode(userCode);

		relationThirdOperateService.deleteLike(relationOperateVO);
		// 更新投票relation中的点赞量
		sendUpdNumInfo(subjectCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE,
				RelationConstants.NUM_BELOW_ONE);
	}
	
	
	/**
	 * 收藏操作（动态）
	 * 更新投票relation中的收藏量
	 * @Function saveVoteCollection
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param remark       备注
	 * @param subjectCode  信息Code
	 * @param subjectClass 信息类型
	 * @param userCode     动态所属人CODE
	 * @throws BusinessException
	 */
	public void saveVoteCollection(String tenantCode,String orgCode,String remark,String subjectCode,Byte subjectClass,String userCode)throws BusinessException {
		RelationCollect relationCollect = new RelationCollect();
		relationCollect.setCollectCode(UUIDHelper.getUUID());
		relationCollect.setTenantCode(tenantCode);
		relationCollect.setOrgCode(orgCode);
		relationCollect.setCreateTime(DateUtils.getFormatDateLong());
		relationCollect.setModifyTime(DateUtils.getFormatDateLong());
		relationCollect.setRemark(remark);
		relationCollect.setSubjectCode(subjectCode);
		relationCollect.setSubjectClass(subjectClass);
		relationCollect.setUserCode(userCode);
		
		relationThirdOperateService.saveCollect(relationCollect);
		// 更新投票relation中的收藏量
		sendUpdNumInfo(subjectCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT, RelationConstants.NUM_ONE);
	}
	
	/**
	 * @Function deleteVoteCollection
	 * @Description 取消收藏操作（动态）
	 * @param subjectCode   内容编号
	 * @param userCode      人员编号
	 * @throws BusinessException
	 */
	public void deleteVoteCollection(String subjectCode, String userCode) throws BusinessException {
		RelationOperateVO relationOperateVO = new RelationOperateVO();
		relationOperateVO.setSubjectCode(subjectCode);
		relationOperateVO.setUserCode(userCode);

		relationThirdOperateService.deleteCollect(relationOperateVO);

		// 更新投票relation中的收藏量
		sendUpdNumInfo(subjectCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT,
				RelationConstants.NUM_BELOW_ONE);
	}
	/**
	 * 减少投票relation中的评论量
	 * @Function decreaseCommentNum
	 * @Description 
	 * @param voteInfoCode
	 */
	public void decreaseCommentNum(String voteInfoCode) {
		sendUpdNumInfo(voteInfoCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT, RelationConstants.NUM_BELOW_ONE);
	}
	/**
	 * 增加投票relation中的评论量
	 * @Function decreaseCommentNum
	 * @Description 
	 * @param voteInfoCode
	 */
	public void increaseCommentNum(String voteInfoCode) {
		sendUpdNumInfo(voteInfoCode, RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT, RelationConstants.NUM_ONE);
	}
	
	/**
	 * 发送更新relation数量指令至kafka
	 * @Function sendUpdNumInfo
	 * @Description 
	 * @param voteInfoCode
	 * @param updateClass
	 * @param updateNumber
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void sendUpdNumInfo(String voteInfoCode, Byte updateClass, Integer updateNumber) {
		KafkaProducerTemplate producerTemplate = SpringContextUtil.getBean(KafkaProducerTemplate.class);
		
		RelationDataVO relationDataVO = new RelationDataVO();
		relationDataVO.setModifyTime(DateUtils.getFormatDateLong());
		relationDataVO.setSubjectCode(voteInfoCode);
		relationDataVO.setUpdateClass(updateClass);
		relationDataVO.setUpdateNumber(updateNumber);
		if (null != relationDataVO) {
			String data = JSONObject.toJSONString(relationDataVO);
			producerTemplate.send(RelationConstants.RELATION_TOPIC, data);
		}
	}
	
}
