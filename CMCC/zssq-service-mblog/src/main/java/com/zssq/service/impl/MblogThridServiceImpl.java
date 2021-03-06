package com.zssq.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogComment;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.MblogReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.RssReturnPo;
import com.zssq.service.MblogThridService;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogThridServiceImpl  
    * @Description: 第三方使用  
    * @author Mr.B  
    * @date 2017年3月24日  
    *
 */
@Service("mblogThridService")
public class MblogThridServiceImpl extends BaseService implements MblogThridService, Serializable {

    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

	/**
	 * 
	    * @Title: modifyShield  
	    * @Description: 屏蔽信息 
	    * @param subjectCode	被屏蔽的主体CODE
	    * @param subjectType	被屏蔽的主体分类：1：微博，2：评论，3：回复
	    * @param isShield  		是否屏蔽：0：取消，1：是
	    * @param userCode 	用户CODE
		* @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean modifyShield(String subjectCode, Byte subjectType,Byte isShield,String userCode) throws BusinessException{
		boolean result = true;
		try{
			if(StringTools.isEmpty(subjectCode)){
				throw new BusinessException("subjectCode参数不可以为空");
			}
			if(StringTools.isEmpty(userCode)){
				throw new BusinessException("userCode参数不可以为空");
			}
			if(null == subjectType || subjectType <= 0){
				throw new BusinessException("subjectType参数不可以为空");
			}
			if(null == isShield || isShield < 0){
				throw new BusinessException("isShield参数不可以为空");
			}
			Map<String,Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("modifyTime", new Date().getTime());
			paramsMap.put("isShield", isShield);
			paramsMap.put("shieldUserCode", userCode);
			switch(subjectType){
				case 1:{ // 微博
					paramsMap.put("mblogCode", subjectCode);
					result = mblogInfoMapper.shieldInfoByCode(paramsMap) > 0;
					break;
				}
				case 2:{ // 评论
					paramsMap.put("commentCode", subjectCode);
					result = mblogCommentMapper.shieldInfoByCode(paramsMap) > 0;
					if(result){
						// 获取评论信息
						MblogComment comment = mblogCommentMapper.getInfoByCode(subjectCode);
						// 更新微博条数
						ownSendKafkaNumMsg(comment.getMblogCode(), comment.getMblogCommentCode(), null, MblogConstants.MBLOG_ACTION_COMMENT, isShield == 1 ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					}
					break;
				}
				case 3:{ // 回复
					paramsMap.put("replyCode", subjectCode);
					result = mblogReplyMapper.shieldInfoByCode(paramsMap) > 0;
					if(result){
						// 更新 微博 与评论 的数目
						MblogReply reply = mblogReplyMapper.getInfoByCode(subjectCode);
						ownSendKafkaNumMsg(reply.getMblogCode(), reply.getCommentCode(), null, MblogConstants.MBLOG_ACTION_REPLY, isShield == 1 ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					}
					break;
				}
				default:{
					result = false;
				}
			}
		}catch(BusinessException e){
			log.error("MblogThridServiceImpl.modifyShield:屏蔽失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogThridServiceImpl.modifyShield:屏蔽失败"+e);
			throw new BusinessException("屏蔽失败",e);
		}
		return result;
	}

	/**
	 * 
	    * @Title: modifyEssenceNum  
	    * @Description: 修改置精次数
	    * @param mblogCode	被置精微博CODE
	    * @param scope		置精的范围 ：1：集团，2：省级，3：市级，4：班组
	    * @param isEssence  置精动作：0：取消，1：是
	    * @param userCode 	用户CODE
		* @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean modifyEssenceNum(String mblogCode, Byte scope,Byte isEssence,String userCode)  throws BusinessException{
		boolean result = true;
		try{
			if(StringTools.isEmpty(mblogCode)){
				throw new BusinessException("mblogCode参数不可以为空");
			}
			if(StringTools.isEmpty(userCode)){
				throw new BusinessException("userCode参数不可以为空");
			}
			if(null == scope || scope <= 0){
				throw new BusinessException("scope参数不可以为空");
			}
			if(null == isEssence || isEssence < 0){
				throw new BusinessException("isEssence参数不可以为空");
			}
			if(StringTools.isNotEmpty(mblogCode)){
				Map<String,Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("modifyTime", new Date().getTime());
				paramsMap.put("num", isEssence == 0 ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
				paramsMap.put("subjectCode", mblogCode);
				switch(scope){
					case MblogConstants.MBLOG_ESSENCE_SCOPE_GROUP:{ // 集团
						mblogNumMapper.updateGessNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ESSENCE_SCOPE_PROVINCE:{ // 省级
						mblogNumMapper.updatePessNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ESSENCE_SCOPE_CITY:{ // 市级
						mblogNumMapper.updateCessNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ESSENCE_SCOPE_TEAM:{ // 班组
						mblogNumMapper.updateTessNumByCode(paramsMap);
						break;
					}
					default:{
						result = false;
					}
				}
			}
		}catch(BusinessException e){
			log.error("MblogThridServiceImpl.modifyEssenceNum:修改置精数目操作失败"+e);
			throw e;	
		}catch(Exception e){
			log.error("MblogThridServiceImpl.modifyEssenceNum:修改置精数目操作失败"+e);
			throw new BusinessException("修改置精数目操作失败",e);
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: getMblogList  
	    * @Description: 获取微博列表
	    * @param mblogCodes	微博CODE列表
	    * @param userCode 	用户CODE
		* @throws BusinessException
		* @return List<MblogModel>    返回类型
	 */
	@Override
	public List<MblogModel> getMblogList(List<String> mblogCodes,String userCode)  throws BusinessException{
		// 创建返回值
		List<MblogModel> mblogList = new ArrayList<MblogModel>();
		try{
			if(StringTools.isNotEmpty(userCode) && null != mblogCodes && !mblogCodes.isEmpty()){
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("myUserCode", userCode);
				paramsMap.put("mblogCodes", mblogCodes);
				// 获取列表
				mblogList = mblogInfoMapper.queryInfoByPage(paramsMap);
			}else{
				throw BusinessException.build("COMMON_402","userCode");
			}
		}catch(BusinessException e){
			log.error("MblogThridServiceImpl.getMblogList:微博列表查询失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogThridServiceImpl.getMblogList:微博列表查询失败"+e);
			throw new BusinessException("微博列表查询失败",e);
		}
		return mblogList;
	}

	/**
	 * 
	    * @Title: getRssMblogList  
	    * @Description: 获取订阅的微博列表
	    * @param userCode	用户CODE
	    * @param teamCode	班组CODE
	    * @param depend		所属类型：1：个人，2：班组
	    * @param pageSize	条数
	    * @throws BusinessException
		* @return List<RssReturnPo>    返回类型
	 */
	@Override
	public List<RssReturnPo> getRssMblogList(String userCode, String teamCode, Byte depend, Integer pageSize)
			throws BusinessException {
		// 创建返回值
		List<RssReturnPo> mblogList = new ArrayList<RssReturnPo>();
		try{
			if(depend == 1 || depend == 2){
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("mblogDepend", depend);
				paramsMap.put("limitSize", pageSize);
				switch(depend){
					case MblogConstants.MBLOG_DEPEND_SELF:{ // 个人的
						if(StringTools.isNotEmpty(userCode)){
							paramsMap.put("userCode", userCode);
						}else{
							throw BusinessException.build("COMMON_402","userCode");
						}
						break;
					}
					case MblogConstants.MBLOG_DEPEND_GROUP:{ // 班组的
						if(StringTools.isNotEmpty(teamCode)){
							paramsMap.put("teamCode", teamCode);
						}else{
							throw BusinessException.build("COMMON_402","teamCode");
						}
						break;
					}
				}
				// 查询数据
				mblogList = mblogInfoMapper.queryRssMblogList(paramsMap);
			}else{
				throw BusinessException.build("COMMON_402","depend");
			}
		}catch(BusinessException e){
			log.error("MblogThridServiceImpl.getMblogList:微博列表查询失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogThridServiceImpl.getMblogList:微博列表查询失败"+e);
			throw new BusinessException("微博列表查询失败",e);
		}
		return mblogList;
	}

	/**
	 * 
	    * @Title: updateUserSubscribe  
	    * @Description: 更新订阅关系
	    * @param userCode	用户CODE
	    * @param otherUserCode	被订阅用户CODE
		* @return boolean    返回类型
	 */
	@Override
	public boolean updateUserSubscribe(String userCode, String otherUserCode) throws BusinessException{
		// 创建返回结果
		boolean result = true;
		try{
			if(StringTools.isEmpty(userCode)){
				result = false; 
				throw BusinessException.build("COMMON_402", "userCode不可以为空");
			}
			if(StringTools.isEmpty(otherUserCode)){
				result = false; 
				throw BusinessException.build("COMMON_402", "otherUserCode不可以为空");
			}
			// 校验是否订阅
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			// 取消订阅
			paramsMap = new HashMap<String,Object>();
			paramsMap.put("subUserCode", otherUserCode);
			paramsMap.put("userCode", userCode);
			result = mblogSubscribeMapper.checkIsSub(paramsMap) > 0;
			if(result){
				result = mblogSubscribeMapper.deleteByCode(paramsMap) > 0;
			}else{
				result = true;
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addOrDelMblogSubscribe:取消订阅微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addOrDelMblogSubscribe:订阅/取消订阅微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "取消订阅微博失败");
		}
		return result;
	}
	
	
	
	/**
	 * 
	    * @Title: getMblogList  
	    * @Description: 获取班组微博列表
	    * @param mblogCodes	微博CODE列表
	    * @param userCode 	用户CODE
		* @throws BusinessException
		* @return List<MblogModel>    返回类型
	 */
	@Override
	public List<MblogModel> getTeamMblogList(String teamCode,Integer limitStart,Integer limitSize)  throws BusinessException{
		// 创建返回值
		List<MblogModel> mblogList = new ArrayList<MblogModel>();
		try{
			if(StringTools.isNotEmpty(teamCode) && limitStart != null && limitSize != null){
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("teamCode", teamCode);
				paramsMap.put("limitStart", limitStart);
				paramsMap.put("limitSize", limitSize);
				// 获取列表
				mblogList = mblogInfoMapper.queryInfoByPage(paramsMap);
				for (MblogModel mblogModel : mblogList) {
					List<MblogComment> mblogCommentList = new ArrayList<MblogComment>();
					if(StringTools.isNotEmpty(mblogModel.getMblogCode()) ){
						// 创建参数
						Map<String,Object> paramsMap2 = new HashMap<String,Object>();
						paramsMap2.put("mblogCode", mblogModel.getMblogCode());
						// 获取列表
						mblogCommentList = mblogCommentMapper.queryCommentListByPage(paramsMap2);
						
						for (MblogComment mblogComment : mblogCommentList) {
							Map<String,Object> paramsMap3 = new HashMap<String,Object>();
							paramsMap3.put("commentCode",mblogComment.getMblogCommentCode());
							// 查询数据
							List<MblogReply> mblogReplyList = mblogReplyMapper.queryReplyListByPage(paramsMap3);
							mblogComment.setReplyList(mblogReplyList);
						}
					}
					mblogModel.setCommentList(mblogCommentList);
					
				}
			}else{
				throw BusinessException.build("COMMON_402","teamCode");
			}
			
		}catch(BusinessException e){
			log.error("MblogThridServiceImpl.getTeamMblogList:班组微博列表查询失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogThridServiceImpl.getTeamMblogList:班组微博列表查询失败"+e);
			throw new BusinessException("班组微博列表查询失败",e);
		}
		return mblogList;
	}
	
	
}
