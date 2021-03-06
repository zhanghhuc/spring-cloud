package com.zssq.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogNum;
import com.zssq.dao.pojo.MblogPraise;
import com.zssq.dao.pojo.MblogReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.qo.ReplyInfoQO;
import com.zssq.service.MblogReplyService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.MblogUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogReplyServiceImpl  
    * @Description: 微博回复信息操作接口实现类
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
@Service("mblogReplyService")
public class MblogReplyServiceImpl extends BaseService implements MblogReplyService, Serializable{

    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	    * @Title: addReplyInfo  
	    * @Description: 添加回复信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean addReplyInfo(ReplyInfoQO qo) throws BusinessException {
		// 创建返回信息
		boolean result = true;
		try{
			// 获取回复CODE
			String replyCode = qo.getReplyCode();
			//qo.setReplyCode(replyCode);
			// 添加回复信息
			result &= ownAddReplyInfo(qo);
			// 添加回复附属信息
			result &= ownAddMblogNum(replyCode);
			if(result){
				// 添加AT列表
				//result &= ownAddAtInfo(qo);
				ownSendKafkaNumMsg(qo.getMblogCode(), qo.getCommentCode(), null, MblogConstants.MBLOG_ACTION_REPLY, MblogConstants.MBLOG_NUM_ADD);
				/*// 更新评论信息
				result &= ownUpdateComRepNum(qo.getCommentCode(), false);
				// 更新微博信息
				result &= ownUpdateMblogComNum(qo.getMblogCode(), false);*/
			}
		}catch(BusinessException e){
			log.error("MblogReplyServiceImpl.addReplyInfo:添加回复失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.addReplyInfo:添加回复失败"+e);
			throw BusinessException.build("MBLOG_12004", "添加回复失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: ownAddReplyInfo  
	    * @Description: 添加回复信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddReplyInfo(ReplyInfoQO qo) throws BusinessException{
		boolean result = true;
		long time = new Date().getTime();
		try{
			// 创建回复信息
			MblogReply reply = new MblogReply();
			reply.setMblogReplyCode(qo.getReplyCode()); // 回复CODE
			reply.setCreateTime(time); // 创建时间
			reply.setModifyTime(time); // 修改时间
			reply.setRemark(MblogConstants.MBLOG_BLANK); // 备注
			reply.setUserCode(qo.getMyUserCode()); // 用户CODE
			reply.setCommentCode(qo.getCommentCode()); // 评论CODE
			reply.setMblogCode(qo.getMblogCode()); // 微博CODE
			reply.setContent(qo.getContent()); // 内容
			reply.setIsDelete(MblogConstants.MBLOG_NO); // 是否删除
			reply.setIsShield(MblogConstants.MBLOG_NO); // 是否屏蔽
			/*reply.setReportNum(MblogConstants.MBLOG_ZERO);
			reply.setPraiseNum(MblogConstants.MBLOG_ZERO);*/
			reply.setReplyedUserCode(qo.getReplyedUserCode()); // 被回复人CODE
			
			reply.setOrgCode(qo.getOrgCode()); // 组织CODE
			reply.setReplyedOrgCode(qo.getReplyedOrgCode()); // 被回复组织CODE
			reply.setTenantCode(qo.getTenantCode()); // 租户
			reply.setReplyType(qo.getReplyType()); // 回复类型
			
			// 插入
			result = mblogReplyMapper.insert(reply) > 0;
		}catch(Exception e){
			throw new BusinessException("添加回复信息失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddMblogNum  
	    * @Description: 添加回复附属信息
	    * @param replyCode
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddMblogNum(String replyCode) throws BusinessException {
		// 创建返回值
		boolean result = false;
		try{
			// 创建微博附属信息
			MblogNum num = MblogUtils.ownCreateMblogNum(replyCode, MblogConstants.MBLOG_NUM_REPLY);
			// 添加
			result = mblogNumMapper.insert(num) > 0;
		}catch(Exception e){
			throw new BusinessException("更新数量失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddAtInfo  
	    * @Description: 添加At信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	/*private boolean ownAddAtInfo(ReplyInfoQO qo)throws BusinessException{
		// 创建返回值
		boolean result = true;
		long time = new Date().getTime();
		try{
			// 创建AT列表
			List<MblogAt> atList = new ArrayList<MblogAt>();
			if(!qo.getAtUserCodes().isEmpty()){
				for(UserInfoQO user : qo.getAtUserCodes()){
					// 创建AT临时对象
					MblogAt at = new MblogAt();
					at.setMblogAtCode(UUIDHelper.getUUID());
					at.setCreateTime(time);
					at.setModifyTime(time);
					at.setRemark(MblogConstants.MBLOG_BLANK);
					
					at.setOrgCode(user.getOrgCode());
					at.setOrgLevel(user.getOrgLevel());
					at.setTenantCode(qo.getTenantCode());
					
					at.setUserCode(user.getUserCode());
					at.setType(MblogConstants.MBLOG_AT_REP);
					at.setAtSite(MblogConstants.MBLOG_AT_SITE_REP);
					at.setMblogCode(qo.getMblogCode());
					at.setCommentCode(qo.getCommentCode());
					at.setReplyCode(qo.getReplyCode());
					
					atList.add(at);
				}
				// 插入
				result = mblogAtMapper.batchInsert(atList) > 0;
			}
		}catch(Exception e){
			throw new BusinessException("微博at信息添加失败",e);
		}
		return result;
	}*/
	
	/**
	 * 
	    * @Title: ownUpdateComRepNum  
	    * @Description: 更新评论回复数
	    * @param commentCode
	    * @param isDelete
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	/*private boolean ownUpdateComRepNum(String commentCode,boolean isDelete)throws BusinessException{
		// 创建返回值
		boolean result = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(commentCode)){
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("subjectCode", commentCode);
				paramsMap.put("modifyTime", time);
				paramsMap.put("num", isDelete ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
				// 更新
				result = mblogNumMapper.updateReplyNumByCode(paramsMap) > 0;
			}
		}catch(Exception e){
			throw new BusinessException("评论回复数修改失败",e);
		}
		return result;
	}*/
	
	/**
	 * 
	    * @Title: ownUpdateMblogComNum  
	    * @Description: 更新微博评论数
	    * @param mblogCode
	    * @param isDelete
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	/*private boolean ownUpdateMblogComNum(String mblogCode,boolean isDelete)throws BusinessException{
		// 创建返回值
		boolean result = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(mblogCode)){
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("subjectCode", mblogCode);
				paramsMap.put("modifyTime", time);
				paramsMap.put("num", isDelete ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
				// 更新
				result = mblogNumMapper.updateComNumByCode(paramsMap) > 0;
			}
		}catch(Exception e){
			throw new BusinessException("微博评论数修改失败",e);
		}
		return result;
	}*/
	
	/**
	 * 
	    * @Title: queryReplyList  
	    * @Description: 查询分页数据
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryReplyList(ReplyInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			if(StringTools.isEmpty(qo.getCommentCode())){
				throw BusinessException.build("MBLOG_12004", "commentCode不可以为空");
			}
			// 获取评论列表
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("commentCode",qo.getCommentCode());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			paramsMap.put("myUserCode", qo.getMyUserCode());
			
			// 查询数据
			pageBean.setRecordList(mblogReplyMapper.queryReplyListByPage(paramsMap));
			// 查询数量
			pageBean.setTotalCount((int)mblogReplyMapper.queryReplyListByPageCount(paramsMap));
		}catch(BusinessException e){
			log.error("MblogReplyServiceImpl.queryReplyList:查询回复列表失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.queryReplyList:查询回复列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "查询回复列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: addOrDelReplyPraise  
	    * @Description: 点赞/取消点赞
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean addOrDelReplyPraise(ReplyInfoQO qo) throws BusinessException {
		// 创建返回值
		boolean result = true;
		boolean isPraise = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(qo.getReplyCode()) && null != qo.getIsPraise()){
				// 校验是否点赞
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("subjectCode", qo.getReplyCode());
				paramsMap.put("userCode", qo.getMyUserCode());
				isPraise = mblogPraiseMapper.checkIsPraise(paramsMap) > 0;
				if(isPraise && qo.getIsPraise() == MblogConstants.MBLOG_YES){
					throw BusinessException.build("MBLOG_12004", "点赞不可重复操作");
				}
				if(!isPraise && qo.getIsPraise() == MblogConstants.MBLOG_NO){
					throw BusinessException.build("MBLOG_12004", "取消点赞不可重复操作");
				}
				if(MblogConstants.MBLOG_YES == qo.getIsPraise() && !isPraise){
					// 点赞
					MblogPraise praise = new MblogPraise();
					praise.setMblogPraiseCode(UUIDHelper.getUUID());
					praise.setCreateTime(time);
					praise.setModifyTime(time);
					praise.setRemark(MblogConstants.MBLOG_BLANK);
					praise.setSubjectCode(qo.getReplyCode());
					praise.setType(MblogConstants.MBLOG_PRAISE_REP);
					praise.setUserCode(qo.getMyUserCode());
					
					praise.setOrgCode(qo.getOrgCode());
					praise.setTenantCode(qo.getTenantCode());
					
					// 添加
					result &= mblogPraiseMapper.insert(praise) > 0;
					
				}else if(MblogConstants.MBLOG_NO == qo.getIsPraise() && isPraise){
					// 取消点赞
					paramsMap = new HashMap<String,Object>();
					paramsMap.put("subjectCode", qo.getReplyCode());
					paramsMap.put("userCode", qo.getMyUserCode());
					result &= mblogPraiseMapper.deleteByCode(paramsMap) > 0;
				}else{
					result = false;
				}
				if(result){
					ownSendKafkaNumMsg(null, null, qo.getReplyCode(), MblogConstants.MBLOG_ACTION_REPLY_PRAISE, isPraise ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					// 更新点赞数据
					/*paramsMap = new HashMap<String,Object>();
					paramsMap.put("subjectCode", qo.getReplyCode());
					paramsMap.put("modifyTime", time);
					paramsMap.put("num",isPraise ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					result &= mblogNumMapper.updatePraNumByCode(paramsMap) > 0;*/
				}
			}else{
				throw BusinessException.build("MBLOG_12004", "参数有问题");
			}
		}catch(BusinessException e){
			log.error("MblogReplyServiceImpl.addOrDelReplyPraise:点赞/取消点赞操作失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.addOrDelReplyPraise:点赞/取消点赞操作失败"+e);
			throw BusinessException.build("MBLOG_12004", "点赞/取消点赞操作失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: deleteReplyInfo  
	    * @Description: 删除回复
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean deleteReplyInfo(ReplyInfoQO qo) throws BusinessException {
		// 创建返回值
		boolean result = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isEmpty(qo.getReplyCode())){
				throw BusinessException.build("MBLOG_12004", "replyCode不可以为空");
			}
			// 删除评论
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("replyCode", qo.getReplyCode());
			paramsMap.put("userCode", qo.getMyUserCode());
			paramsMap.put("modifyTime", time);
			result = mblogReplyMapper.deleteInfoByCode(paramsMap) > 0;
			if(result){
				ownSendKafkaNumMsg(qo.getMblogCode(), qo.getCommentCode(), null, MblogConstants.MBLOG_ACTION_REPLY, MblogConstants.MBLOG_NUM_SUB);
				/*// 删除成功更新评论回复数
				result &= ownUpdateComRepNum(qo.getCommentCode(), true);
				// 删除成功更新微博评论数
				result &= ownUpdateMblogComNum(qo.getMblogCode(), true);*/
			}
		}catch(BusinessException e){
			log.error("MblogReplyServiceImpl.deleteReplyInfo:删除回复失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.deleteReplyInfo:删除回复失败"+e);
			throw BusinessException.build("MBLOG_12004", "删除回复失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: gotoReplyList  
	    * @Description: 定位到回复列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean gotoReplyList(ReplyInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			if(StringTools.isNotEmpty(qo.getCommentCode()) && StringTools.isNotEmpty(qo.getReplyCode())){
				MblogReply reply = mblogReplyMapper.getInfoByCode(qo.getReplyCode());
				if(reply.getIsDelete() == MblogConstants.MBLOG_NO && reply.getIsShield() == MblogConstants.MBLOG_NO){
					// 获取评论列表
					Map<String,Object> paramsMap = new HashMap<String,Object>();
					// 查询所在页
					paramsMap.put("replyCode", qo.getReplyCode());
					paramsMap.put("commentCode",qo.getCommentCode());
					long pageCount = mblogReplyMapper.queryCountByCode(paramsMap);
					int pageNo = -1;
					if(pageCount >= 1){
						pageNo = (int)(pageCount-1)/qo.getLimitSize();
					}
					if(pageNo >= 0){
						paramsMap = new HashMap<String,Object>();
						paramsMap.put("lineTime", qo.getLineTime());
						paramsMap.put("commentCode",qo.getCommentCode());
						paramsMap.put("limitStart", pageNo*qo.getLimitSize());
						paramsMap.put("limitSize", qo.getLimitSize());
						paramsMap.put("myUserCode", qo.getMyUserCode());
						
						// 查询数据
						pageBean.setRecordList(mblogReplyMapper.queryReplyListByPage(paramsMap));
						// 查询数量
						pageBean.setTotalCount((int)mblogReplyMapper.queryReplyListByPageCount(paramsMap));
						pageBean.setCurrentPage(pageNo);
					}
				}else{
					// 获取评论列表
					Map<String,Object> paramsMap = new HashMap<String,Object>();
					paramsMap.put("lineTime", qo.getLineTime());
					paramsMap.put("commentCode",qo.getCommentCode());
					paramsMap.put("limitStart", 0);
					paramsMap.put("limitSize", qo.getLimitSize());
					paramsMap.put("myUserCode", qo.getMyUserCode());
					
					// 查询数据
					pageBean.setRecordList(mblogReplyMapper.queryReplyListByPage(paramsMap));
					// 查询数量
					pageBean.setTotalCount((int)mblogReplyMapper.queryReplyListByPageCount(paramsMap));
					pageBean.setCurrentPage(0);
				}
			}
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.gotoReplyList:查询回复列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "查询回复列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: getReplyInfo  
	    * @Description: 获取回复信息
	    * @param replyCode 	回复CODE
	    * @throws BusinessException
		* @return MblogReply    返回类型
	 */
	@Override
	public MblogReply getReplyInfo(String replyCode) throws BusinessException {
		MblogReply reply = null;
		try{
			if(StringTools.isNotEmpty(replyCode)){
				reply = mblogReplyMapper.getInfoByCode(replyCode);
			}
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.getReplyInfo:查询回复信息失败"+e);
			throw BusinessException.build("MBLOG_12004", "查询回复信息失败");
		}
		return reply;
	}

	/**
	 * 
	    * @Title: gotoReplyListByPage  
	    * @Description: 定位回复列表可分页
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean gotoReplyListByPage(ReplyInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			if(StringTools.isNotEmpty(qo.getCommentCode()) && StringTools.isNotEmpty(qo.getReplyCode())){
				// 获取评论列表
				List<MblogReply> dataList = new ArrayList<MblogReply>();
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("lineTime", qo.getLineTime());
				paramsMap.put("commentCode",qo.getCommentCode());
				paramsMap.put("limitStart", qo.getLimitStart());
				paramsMap.put("limitSize", qo.getLimitSize());
				paramsMap.put("myUserCode", qo.getMyUserCode());
				paramsMap.put("replyCode", qo.getReplyCode());

				Map<String,Object> paramsMap1 = new HashMap<String,Object>();
				paramsMap1.put("replyCode",qo.getReplyCode());
				paramsMap1.put("myUserCode", qo.getMyUserCode());
				MblogReply reply = mblogReplyMapper.getDetailByCode(paramsMap1);
				
				if(null != reply && qo.getCommentCode().equals(reply.getCommentCode())){
					if(0 == qo.getPageNo()){
						dataList.add(0, reply);
						paramsMap.put("limitSize", qo.getLimitSize()-1);
					}else{
						paramsMap.put("limitStart", qo.getLimitStart()-1);
					}
				}
				
				List<MblogReply> repList = mblogReplyMapper.queryReplyListByPage(paramsMap);
				dataList.addAll(repList);
				// 查询数据
				pageBean.setRecordList(dataList);
				// 查询数量
				pageBean.setTotalCount((int)mblogReplyMapper.queryReplyListByPageCount(paramsMap));
			}
		}catch(Exception e){
			log.error("MblogReplyServiceImpl.gotoReplyList:查询回复列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "查询回复列表失败");
		}
		return pageBean;
	}
}
