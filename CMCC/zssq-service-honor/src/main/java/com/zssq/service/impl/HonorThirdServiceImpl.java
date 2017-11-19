package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.HonorConstants;
import com.zssq.dao.mapper.HonorAwardRecordMapper;
import com.zssq.dao.mapper.HonorCommentMapper;
import com.zssq.dao.mapper.HonorCommentReplyMapper;
import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.HonorAwardRecordExample;
import com.zssq.dao.pojo.HonorComment;
import com.zssq.dao.pojo.HonorCommentExample;
import com.zssq.dao.pojo.HonorCommentReply;
import com.zssq.dao.pojo.HonorCommentReplyExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.HonorThirdService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.StringTools;

/**
 * @ClassName HonorThirdServiceImpl
 * @Description 荣誉模块第三方服务
 * @author LiuYunLong
 * @date 2017年4月10日 上午10:13:58
 * @version 1.0
 * @since JDK 1.7
 */
@Service("honorThirdService")
public class HonorThirdServiceImpl implements HonorThirdService {

	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private HonorAwardRecordMapper honorAwardRecordMapper;
	
	@Autowired
	private HonorCommentMapper honorCommentMapper;
	
	@Autowired
	private HonorCommentReplyMapper honorCommentReplyMapper;
	
	
	/**
	 * @Function getHonorCount
	 * @Description 获取荣誉数目 
	 * @param honoreeCode
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public int getHonorCount(String honoreeCode,String tenantCode) throws BusinessException {
		
		try{
			HonorAwardRecordExample example = new HonorAwardRecordExample();
			HonorAwardRecordExample.Criteria criteria = example.createCriteria();
			criteria.andHonoreeCodeEqualTo(honoreeCode);
			// 是否禁用(0-否  1-是)
			criteria.andIsDisableEqualTo(HonorConstants.NO);
			// 是否删除(0-否  1-是)
			criteria.andIsDeleteEqualTo(HonorConstants.NO);
			//设置租户信息 
			criteria.andTenantCodeEqualTo(tenantCode); 
			return  honorAwardRecordMapper.countByExample(example);
		}catch(Exception e){
			log.error("HonorThirdServiceImpl.getHonorCount", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public boolean updateHonorMessage(String subjectCode, Byte subjectType, Byte isDelete,String userCode)throws BusinessException {
			
		Boolean result = true;
		try{
			if(StringTools.isEmpty(subjectCode)){
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(null == isDelete || isDelete < 0 || isDelete > 1){
				throw BusinessException.build("COMMON_402", "isDelete");
			}
			if(null == userCode){
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if(null == subjectCode || subjectType <= 1 || subjectType > 3){
				throw BusinessException.build("COMMON_402", "subjectType");
			}
			
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("modifyTime", DateUtils.getFormatDateLong());
			paramMap.put("isDelete", isDelete);
			
			switch(subjectType){
				//信息类型为 评论
				case 2:{
					paramMap.put("commentCode", subjectCode);
					/** 1、更新荣誉评论表中数据 **/
					result = honorCommentMapper.updateHonorCommentForComplaintByComment(paramMap) > 0;
					if(result){
						if(isDelete == HonorConstants.NO){
							paramMap.put("type", "+1");
						}else if(isDelete == HonorConstants.YES){
							paramMap.put("type", "-1");
						}
						/** 2、更新荣誉授予表中评论数 **/
						honorAwardRecordMapper.updateHonorRecordForComplaintByComment(paramMap);
					}
					break;
				}
				
				//信息类型为 评论回复
				case 3:{
					paramMap.put("replyCode", subjectCode);
					/** 1、更新荣誉评论回复表中数据 **/
					result = honorCommentReplyMapper.updateHonorReplyForComplaintByReply(paramMap) > 0;
					if(result){
						if(isDelete == HonorConstants.NO){
							paramMap.put("type", "+1");
						}else if(isDelete == HonorConstants.YES){
							paramMap.put("type", "-1");
						}
						/** 2、更新荣誉评论表中回复数 **/
						result = honorCommentMapper.updateHonorCommentForComplaintByReply(paramMap) > 0;
					}
					break;
				}
				default:{
					result = false;
				}
			}
			return result;
		}catch(Exception e){
			log.error("HonorThirdServiceImpl.updateHonorMessage", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}


	@Override
	public List<HonorAwardRecord> getHonorListForHonoree(String honoreeCode, String tenantCode)
			throws BusinessException {
		List<HonorAwardRecord> honorList = null;
		try{
			HonorAwardRecordExample example = new HonorAwardRecordExample();
			HonorAwardRecordExample.Criteria criteria = example.createCriteria();
			criteria.andHonoreeCodeEqualTo(honoreeCode);
			// 是否禁用(0-否  1-是)
			criteria.andIsDisableEqualTo(HonorConstants.NO);
			// 是否删除(0-否  1-是)
			criteria.andIsDeleteEqualTo(HonorConstants.NO);
			//设置租户信息 
			criteria.andTenantCodeEqualTo(tenantCode); 
			honorList = honorAwardRecordMapper.selectByExample(example);
			return honorList;
		}catch(Exception e){
			log.error("HonorThirdServiceImpl.getHonorListForHonoree", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public List<HonorAwardRecord> sumupTeamHonor(String teamCode, int startNum, int endNum) throws BusinessException {
		try {
			HonorAwardRecordExample example = new HonorAwardRecordExample();
			HonorAwardRecordExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(HonorConstants.NO);
			criteria.andIsDisableEqualTo(HonorConstants.NO);
			criteria.andIsRevokedEqualTo(HonorConstants.NO);
			criteria.andHonoreeTypeEqualTo(HonorConstants.HONOREE_TYPE_2);
			criteria.andHonoreeCodeEqualTo(teamCode);
			
			example.setOrderByClause("id desc");
			example.setLimitStart(startNum);
			example.setLimitEnd(endNum);
			
			List<HonorAwardRecord> honorList = honorAwardRecordMapper.selectByExample(example);
			for (HonorAwardRecord record : honorList) {
				int commentCount = record.getCommentCount();
				if (commentCount > 0) {
					String awardRecordCode = record.getHonorAwardRecordCode();
					
					HonorCommentExample cExample = new HonorCommentExample();
					HonorCommentExample.Criteria cCriteria = cExample.createCriteria();
					
					cCriteria.andIsDeleteEqualTo(HonorConstants.NO);
					cCriteria.andIsDisableEqualTo(HonorConstants.NO);
					cCriteria.andAwardRecordCodeEqualTo(awardRecordCode);
					
					cExample.setOrderByClause("id desc");
					List<HonorComment> commentList = honorCommentMapper.selectByExampleWithBLOBs(cExample);
					for (HonorComment comment : commentList) {
						int replyCount = comment.getReplyCount();
						if (replyCount > 0 && comment.getIsHide() == HonorConstants.NO) {
							String commentCode = comment.getHonorAwardCommentCode();
							
							HonorCommentReplyExample rExample = new HonorCommentReplyExample();
							HonorCommentReplyExample.Criteria rCriteria = rExample.createCriteria();
							
							rCriteria.andIsDeleteEqualTo(HonorConstants.NO);
							rCriteria.andIsDisableEqualTo(HonorConstants.NO);
							rCriteria.andAwardRecordCodeEqualTo(awardRecordCode);
							rCriteria.andAwardCommentCodeEqualTo(commentCode);
							
							rExample.setOrderByClause("id desc");
							List<HonorCommentReply> replyList = honorCommentReplyMapper.selectByExampleWithBLOBs(rExample);
							comment.setReplyList(replyList);
						}
					}
					record.setCommentList(commentList);
				}
			}
			return honorList;
		} catch (Exception e) {
			log.error("HonorThirdServiceImpl.sumupTeamHonor", e);
			throw BusinessException.build("COMMON_400");
		}
	}

}
