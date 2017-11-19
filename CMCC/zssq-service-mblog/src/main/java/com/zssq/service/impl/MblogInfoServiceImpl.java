package com.zssq.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogAt;
import com.zssq.dao.pojo.MblogCollect;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.MblogNum;
import com.zssq.dao.pojo.MblogPraise;
import com.zssq.dao.pojo.MblogResource;
import com.zssq.dao.pojo.MblogSubscribe;
import com.zssq.exceptions.BusinessException;
import com.zssq.qo.AgentMblogQO;
import com.zssq.qo.ForwardWeiboQO;
import com.zssq.qo.MblogInfoAddQO;
import com.zssq.qo.MblogInfoQO;
import com.zssq.qo.PublishWeiboQO;
import com.zssq.qo.UserInfoQO;
import com.zssq.service.MblogInfoService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.MblogUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogInfoServiceImpl  
    * @Description: 微博信息服务层接口实现  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
@Service("mblogInfoService")
public class MblogInfoServiceImpl extends BaseService implements MblogInfoService, Serializable{

    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	    * @Title: addMblogInfo  
	    * @Description: 添加微博信息
	    * @param qo
	    * @throws BusinessException
	 */
	@Override
	public boolean addMblogInfo(PublishWeiboQO qo) throws BusinessException {
		// 返回结果
		boolean result = true;
		try{
			// 创建信息
			MblogInfoAddQO maqo  = new MblogInfoAddQO();
			maqo.setUserCode(qo.getUserCode()); // 用户CODE
			maqo.setMblogSource(MblogConstants.MBLOG_SOURCE_SELF); // 原创微博
			maqo.setTeamCode(qo.getTeamCode()); // 班组CODE
			maqo.setIsTeam(qo.getIsTeam()); // 是否是班组
			maqo.setOrgCode(qo.getOrgCode()); // 组织CODE 
			maqo.setTenatCode(qo.getTenatCode()); // 租户
			maqo.setContent(qo.getContent()); // 内容
			maqo.setSummary(qo.getSummary()); // 摘要
			maqo.setImgs(qo.getImgs()); // 摘要 
			maqo.setAudios(qo.getAudios()); // 音频
			maqo.setVideos(qo.getVideos()); // 视频
			maqo.setTopicCodes(qo.getTopicCodes()); // 主题
			maqo.setAtUserCodes(qo.getAtUserCodes()); // at 用户列表
			maqo.setMblogCode(qo.getMblogCode()); // 微博CODE
			maqo.setDynamicCode(qo.getDynamicCode()); // 动态CODE
			maqo.setTimeSign(qo.getTimeSign());
			// 添加
			result = ownAddInfo(maqo);
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addMblogInfo:发布微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addMblogInfo:发布微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "发布微博失败");
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: addForwardMblogInfo  
	    * @Description: 添加转发微博信息
	    * @param qo
	    * @throws BusinessException
	 */
	@Override
	public boolean addForwardMblogInfo(ForwardWeiboQO qo) throws BusinessException {
		// 返回结果
		boolean result = true;
		try{
			// 创建信息
			MblogInfoAddQO maqo  = new MblogInfoAddQO();
			maqo.setUserCode(qo.getUserCode()); // 用户CODE
			maqo.setOrgCode(qo.getOrgCode()); // 组织CODE
			maqo.setTenatCode(qo.getTenatCode()); // 租户
			maqo.setContent(qo.getContent()); // 内容
			maqo.setSummary(qo.getSummary()); // 摘要
			maqo.setAtUserCodes(qo.getAtUserCodes());  // at 用户列表
			maqo.setSourceMblogCode(qo.getSourceMblogCode()); // 来源微博CODE
			maqo.setSourceUserCode(qo.getSourceUserCode()); // 来源微博发布人CODE
			maqo.setForMblogCode(qo.getForMblogCode());; // 转发微博CODE列表
			maqo.setForUserCode(qo.getForUserCode());; // 转发微博发布人CODE列表
			maqo.setMblogSource(MblogConstants.MBLOG_SOURCE_FORWARD); // 转发微博
			maqo.setMblogCode(qo.getMblogCode()); // 微博CODE
			maqo.setDynamicCode(qo.getDynamicCode()); // 动态CODE
			maqo.setTimeSign(qo.getTimeSign());
			
			// 添加
			result = ownAddInfo(maqo);
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addForwardMblogInfo:转发微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addForwardMblogInfo:转发微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "转发微博失败");
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: addAgentMblogInfo  
	    * @Description: 添加代发微博信息
	    * @param qo
	    * @throws BusinessException
	 */
	@Override
	public boolean addAgentMblogInfo(AgentMblogQO qo) throws BusinessException {
		// 返回结果
		boolean result = true;
		try{
			// 创建信息
			MblogInfoAddQO maqo  = new MblogInfoAddQO();
			maqo.setUserCode(qo.getUserCode()); // 用户CODE
			maqo.setMblogSource(MblogConstants.MBLOG_SOURCE_SELF); // 原创微博
			maqo.setAgentOrgCode(qo.getAgentOrgCode()); // 代发人组织CODE
			maqo.setAgentUserCode(qo.getAgentUserCode()); // 代发人用户CODE
			maqo.setOrgCode(qo.getOrgCode()); // 发布人组织CODE
			maqo.setTenatCode(qo.getTenatCode()); // 租户
			maqo.setContent(qo.getContent()); // 内容
			maqo.setSummary(qo.getSummary()); // 摘要
			maqo.setImgs(qo.getImgs()); // 图片列表
			maqo.setAudios(qo.getAudios()); // 音频列表
			maqo.setVideos(qo.getVideos()); // 视频列表
			maqo.setTopicCodes(qo.getTopicCodes()); // 话题列表
			maqo.setAtUserCodes(qo.getAtUserCodes()); // at用户列表
			maqo.setMblogCode(qo.getMblogCode()); // 微博CODE
			maqo.setDynamicCode(qo.getDynamicCode()); // 动态CODE
			maqo.setTimeSign(qo.getTimeSign());
			
			// 添加
			result = ownAddInfo(maqo);
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addAgentMblogInfo:代发微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addAgentMblogInfo:代发微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "代发微博失败");
		}
		return result;
	}
	
	/**
	 * 
	 * 
	    * @Title: ownAddInfo  
	    * @Description: 微博添加信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddInfo(MblogInfoAddQO qo)throws BusinessException{
		// 返回结果
		boolean result = true;
		try{
			
			// 生成微博基本信息
			String mblogCode = qo.getMblogCode();
			// 插入微博资源信息
			result &=ownAddMblogRescource(mblogCode, qo);
			// 插入微博话题信息
			result &=ownUpdateMblogTopic(qo);
			// 插入微博附属信息
			result &= ownAddMblogNum(mblogCode);
			// 插入微博信息
			result &=ownAddMblogInfo(mblogCode, qo);
			if(result){
				// 插入微博At信息
				result &=ownAddMblogAt(mblogCode, qo);
				// 如果是转发信息 需要更新 所有上级的转发次数
				if(qo.getMblogSource() == MblogConstants.MBLOG_SOURCE_FORWARD){
					//result &= ownAddForNum(qo.getForwardMblogCodes());
					ownSendKafkaForwardMsg(qo.getMblogCode(), qo.getForMblogCode());
				}
			}
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException(e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddForNum  
	    * @Description: 转发微博+1
	    * @param mblogCodes
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	/*private boolean ownAddForNum(List<String> mblogCodes) throws BusinessException{
		// 创建返回值
		boolean result = false;
		try{
			if(null != mblogCodes && !mblogCodes.isEmpty()){
				// 拼接参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				for(String code : mblogCodes){
					paramsMap.put("modifyTime", new Date().getTime()); // 修改时间
					paramsMap.put("subjectCode", code); // 主体CODE
					paramsMap.put("num", MblogConstants.MBLOG_NUM_ADD); // 数量
					result &= mblogNumMapper.updateForNumByCode(paramsMap) > 0;
					if(!result){
						break;
					}
				}
			}
		}catch(Exception e){
			throw new BusinessException("微博转发信息未被加1",e);
		}
		return result;
	}*/
	
	/**
	 * 
	    * @Title: ownAddMblogInfo  
	    * @Description: 添加微博基本信息
	    * @param mblogCode 微博CODE
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddMblogInfo(String mblogCode,MblogInfoAddQO qo) throws BusinessException {
		// 创建返回值
		boolean result = false;
		try{
			// 创建微博信息
			MblogInfo info = MblogUtils.ownCreateMblogInfo(mblogCode, qo);
			// 添加
			result = mblogInfoMapper.insert(info) > 0;
		}catch(Exception e){
			throw new BusinessException("微博基本信息添加失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddMblogNum  
	    * @Description: 添加微博附属数量信息
	    * @param mblogCode 微博CODE
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddMblogNum(String mblogCode) throws BusinessException {
		// 创建返回值
		boolean result = false;
		try{
			// 创建微博附属信息
			MblogNum num = MblogUtils.ownCreateMblogNum(mblogCode, MblogConstants.MBLOG_NUM_MBLOG);
			// 添加
			result = mblogNumMapper.insert(num) > 0;
		}catch(Exception e){
			throw new BusinessException("微博附属信息添加失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddMblogRescource  
	    * @Description: 添加微博资源信息
	    * @param mblogCode
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddMblogRescource(String mblogCode,MblogInfoAddQO qo) throws BusinessException {
		// 创建返回值
		boolean result = true;
		try{
			// 创建资源对象
			List<MblogResource> resList = MblogUtils.ownCreateResList(mblogCode, qo);
			
			// 添加
			if(!resList.isEmpty()){
				result = mblogResourceMapper.batchInsert(resList) > 0;
			}
			
		}catch(Exception e){
			throw new BusinessException("微博资源信息添加失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownUpdateMblogTopic  
	    * @Description: 更新话题
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownUpdateMblogTopic(MblogInfoAddQO qo) throws BusinessException {
		// 创建返回值
		boolean result = true;
		try{
			// 创建topic信息
			List<String> codes = qo.getTopicCodes();
			List<String> topicCodes = new ArrayList<String>();
			if(!codes.isEmpty()){
				for(String code : codes){
					if(null != code && !"".equals(code)){
						if(mblogTopicMapper.checkIsExistByCode(code) > 0){
							topicCodes.add(code);
						}
					}
				}
				if(!topicCodes.isEmpty()){
					// 拼接参数
					Map<String,Object> paramsMap = new HashMap<String,Object>();
					paramsMap.put("modifyTime", new Date().getTime()); 	// 修改时间
					paramsMap.put("codes", topicCodes); // 列表
					// 更新
					result = mblogTopicMapper.batchUpdateNumByCode(paramsMap) > 0;
				}
			}
		}catch(Exception e){
			throw new BusinessException("微博话题信息更新失败",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: ownAddMblogAt  
	    * @Description: 添加at信息
	    * @param mblogCode
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	private boolean ownAddMblogAt(String mblogCode,MblogInfoAddQO qo) throws BusinessException {
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
					at.setMblogAtCode(UUIDHelper.getUUID()); // atCODE
					at.setCreateTime(time); // 创建时间
					at.setModifyTime(time); // 修改时间
					at.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					
					at.setOrgCode(user.getOrgCode()); // 组织CODE
					at.setTenantCode(qo.getTenatCode()); // 租户
					 
					at.setUserCode(user.getUserCode()); // 用户CODE
					at.setType(MblogConstants.MBLOG_AT_PUS); // 类型
					at.setAtSite(MblogConstants.MBLOG_AT_SITE_MBLOG); // 微博
					at.setMblogCode(mblogCode); // 微博CODE
					at.setCommentCode(MblogConstants.MBLOG_BLANK); // 评论CODE
					at.setReplyCode(MblogConstants.MBLOG_BLANK); // 回复CODE
					
					atList.add(at);
				}
			}
			if(!atList.isEmpty()){
				// 插入
				result = mblogAtMapper.batchInsert(atList) > 0;
			}
		}catch(Exception e){
			throw new BusinessException("微博at信息添加失败",e);
		}
		return result;
	}

	/**
	 * 
	    * @Title: queryMblogInfoList  
	    * @Description: 获取微博信息列表
	    * @param qo
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryMblogInfoList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode()); // 我的用户CODE
			paramsMap.put("lineTime", qo.getLineTime()); // 时间线
			paramsMap.put("limitStart", qo.getLimitStart()); // 开始
			paramsMap.put("limitSize", qo.getLimitSize()); // 每页条数
			paramsMap.put("userCodes", qo.getUserCodes()); // 用户CODE列表
			paramsMap.put("mblogCodes", qo.getMblogCodes()); // 微博CODE列表
			paramsMap.put("mblogDepend", qo.getDepend()); // 微博所属
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryInfoByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryInfoByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryMblogInfoList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}
	
	/**
	 * 
	    * @Title: queryMyMblogList  
	    * @Description: 获取我以及我关注的微博信息列表
	    * @param qo
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryMyMblogList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode()); // 我的用户CODE
			paramsMap.put("mblogDepend", MblogConstants.MBLOG_DEPEND_SELF); // 微博所属
			paramsMap.put("lineTime", qo.getLineTime()); // 时间线
			paramsMap.put("limitStart", qo.getLimitStart()); // 开始
			paramsMap.put("limitSize", qo.getLimitSize()); // 每页条数
			paramsMap.put("userCodes", qo.getUserCodes()); // 用户列表CODE
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryInfoByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryInfoByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryMyMblogList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}
	
	
	/**
	 * 
	    * @Title: queryOtherMblogList  
	    * @Description: 获取他人微博信息列表
	    * @param qo
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryOtherMblogList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode()); // 我的用户CODE
			paramsMap.put("mblogDepend", MblogConstants.MBLOG_DEPEND_SELF); // 私有的
			paramsMap.put("lineTime", qo.getLineTime()); //时间线
			paramsMap.put("limitStart", qo.getLimitStart()); // 开始
			paramsMap.put("limitSize", qo.getLimitSize()); // 每页条数
			paramsMap.put("otherUserCode", qo.getOtherUserCode());
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryInfoByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryInfoByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryOtherMblogList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}
	
	/**
	 * 
	    * @Title: queryTeamMblogList  
	    * @Description: 获取班组微博信息列表
	    * @param qo
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryTeamMblogList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode());
			paramsMap.put("mblogDepend", MblogConstants.MBLOG_DEPEND_GROUP);
			paramsMap.put("teamCode", qo.getTeamCode());
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryInfoByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryInfoByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryTeamMblogList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}
	
	/**
	 * 
	    * @Title: queryAgentInfoList  
	    * @Description: 获取代发微博信息列表
	    * @param qo
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryAgentInfoList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode());
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			paramsMap.put("leaderUserCode", qo.getLeaderUserCode());
			
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryAgentList(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryAgentListCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryAgentInfoList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}
	
	/**
	 * 
	    * @Title: getMblogInfo  
	    * @Description: 微博详情
	    * @param qo
		* @return MblogModel    返回类型
	 */
	@Override
	public MblogModel getMblogInfo(MblogInfoQO qo)  throws BusinessException  {
		// 创建返回对象
		MblogModel model = new MblogModel();
		try{
			// 判断微博是否为空
			if(StringTools.isNotEmpty(qo.getMblogCode())){
				// 组装参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("mblogCode", qo.getMblogCode());
				paramsMap.put("myUserCode", qo.getMyUserCode());
				// 查询
				model = mblogInfoMapper.getInfoByCode(paramsMap);
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.getMblogInfo:获取微博详情失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.getMblogInfo:获取微博详情失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博详情失败");
		}
		return model;
	}

	/**
	 * 
	    * @Title: deleteMblogInfo  
	    * @Description: 删除微博信息
	    * @param qo
	    * @throws BusinessException
	 */
	@Override
	public boolean deleteMblogInfo(MblogInfoQO qo)  throws BusinessException  {
		boolean result = true;
		try{
			// 判断微博是否为空
			if(StringTools.isNotEmpty(qo.getMblogCode())){
				// 组装参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("mblogCode", qo.getMblogCode());
				paramsMap.put("userCode", qo.getMyUserCode());
				paramsMap.put("modifyTime", new Date().getTime());
				// 删除信息
				result = mblogInfoMapper.deleteInfoByCode(paramsMap) > 0;
				mblogCommentMapper.batchDeleteInfo(paramsMap);
				mblogReplyMapper.batchDeleteInfo(paramsMap);
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.deleteMblogInfo:删除微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.deleteMblogInfo:删除微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "删除微博失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: deleteMblogInfoGroup  
	    * @Description: 班组长删除微博信息
	    * @param qo
	    * @throws BusinessException
	 */
	@Override
	public boolean deleteMblogInfoGroup(MblogInfoQO qo)  throws BusinessException  {
		boolean result = true;
		try{
			// 判断微博是否为空
			if(StringTools.isNotEmpty(qo.getMblogCode())){
				// 组装参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("mblogCode", qo.getMblogCode());
				paramsMap.put("teamCode", qo.getTeamCode());
				paramsMap.put("modifyTime", new Date().getTime());
				// 删除信息
				result = mblogInfoMapper.deleteInfoByTeamCode(paramsMap) > 0;
				mblogCommentMapper.batchDeleteInfo(paramsMap);
				mblogReplyMapper.batchDeleteInfo(paramsMap);
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.deleteMblogInfo:删除微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.deleteMblogInfo:删除微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "删除微博失败");
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: addOrDelMblogPraise  
	    * @Description: 添加/删除微博点赞
	    * @param qo
		* @return boolean    返回类型
	 */
	@Override
	public boolean addOrDelMblogPraise(MblogInfoQO qo) throws BusinessException  {
		// 创建返回结果
		boolean result = true;
		boolean isPraise = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(qo.getMblogCode()) && null != qo.getIsPraise()){
				// 校验是否点赞
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("subjectCode", qo.getMblogCode());
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
					praise.setMblogPraiseCode(UUIDHelper.getUUID()); // 点赞CODE
					praise.setCreateTime(time); // 创建时间
					praise.setModifyTime(time); // 修改时间
					praise.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					praise.setSubjectCode(qo.getMblogCode()); // 主体CODE
					praise.setType(MblogConstants.MBLOG_PRAISE_MBLOG); // 类 型
					praise.setUserCode(qo.getMyUserCode()); // 用户CODE
					praise.setOrgCode(qo.getOrgCode()); // 组织CODE 
					praise.setTenantCode(qo.getTenantCode()); // 租户
					
					// 添加
					result &= mblogPraiseMapper.insert(praise) > 0;
					
				}else if(MblogConstants.MBLOG_NO == qo.getIsPraise() && isPraise){
					// 取消点赞
					paramsMap = new HashMap<String,Object>();
					paramsMap.put("subjectCode", qo.getMblogCode());
					paramsMap.put("userCode", qo.getMyUserCode());
					result &= mblogPraiseMapper.deleteByCode(paramsMap) > 0;
				}else{
					result = false;
				}
				if(result){
					// 更新点赞数据
					ownSendKafkaNumMsg(qo.getMblogCode(), null, null, MblogConstants.MBLOG_ACTION_MBLOG_PRAISE, isPraise ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					/*paramsMap = new HashMap<String,Object>();
					paramsMap.put("subjectCode", qo.getMblogCode());
					paramsMap.put("modifyTime", time);
					paramsMap.put("num",isPraise ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					result &= mblogNumMapper.updatePraNumByCode(paramsMap) > 0;*/
				}
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addOrDelMblogPraise:点赞/取消点赞微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addOrDelMblogPraise:点赞/取消点赞微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "点赞/取消点赞微博失败");
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: addOrDelMblogCollect  
	    * @Description: 添加/删除微博收藏
	    * @param qo
		* @return boolean    返回类型
	 */
	@Override
	public boolean addOrDelMblogCollect(MblogInfoQO qo) throws BusinessException  {
		// 创建返回结果
		boolean result = true;
		boolean isCollect = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(qo.getMblogCode()) && null != qo.getIsCollect()){
				// 校验是否收藏
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("mblogCode", qo.getMblogCode());
				paramsMap.put("userCode", qo.getMyUserCode());
				isCollect = mblogCollectMapper.checkIsCollect(paramsMap) > 0;
				if(isCollect && qo.getIsCollect() == MblogConstants.MBLOG_YES){
					throw BusinessException.build("MBLOG_12004", "收藏不可重复操作");
				}
				if(!isCollect && qo.getIsCollect() == MblogConstants.MBLOG_NO){
					throw BusinessException.build("MBLOG_12004", "取消收藏不可重复操作");
				}
				
				if(MblogConstants.MBLOG_YES == qo.getIsCollect() && !isCollect){
					// 收藏
					MblogCollect collect = new MblogCollect();
					collect.setMblogCollectCode(UUIDHelper.getUUID()); // 收藏CODE
					collect.setCreateTime(time); // 创建时间
					collect.setModifyTime(time); // 修改时间
					collect.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					collect.setUserCode(qo.getMyUserCode()); // 用户CODE
					collect.setMblogCode(qo.getMblogCode()); // 微博CODE
					collect.setOrgCode(qo.getOrgCode()); // 组织CODE
					collect.setTenantCode(qo.getTenantCode()); // 租户
					
					// 添加
					result &= mblogCollectMapper.insert(collect) > 0;
					
				}else if(MblogConstants.MBLOG_NO == qo.getIsCollect() && isCollect){
					// 取消收藏
					paramsMap = new HashMap<String,Object>();
					paramsMap.put("mblogCode", qo.getMblogCode());
					paramsMap.put("userCode", qo.getMyUserCode());
					result &= mblogCollectMapper.deleteByCode(paramsMap) > 0;
				}else{
					result = false;
				}
				if(result){
					// 更新收藏数据
					ownSendKafkaNumMsg(qo.getMblogCode(), null, null, MblogConstants.MBLOG_ACTION_COLLECT, isCollect ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					/*paramsMap = new HashMap<String,Object>();
					paramsMap.put("subjectCode", qo.getMblogCode());
					paramsMap.put("modifyTime", time);
					paramsMap.put("num",isCollect ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					result &= mblogNumMapper.updateColNumByCode(paramsMap) > 0;*/
				}
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addOrDelMblogCollect:收藏/取消收藏微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addOrDelMblogCollect:收藏/取消收藏微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "收藏/取消收藏微博失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: addOrDelMblogSubscribe  
	    * @Description: 添加/删除微博订阅信息
	    * @param qo
		* @return boolean    返回类型
	 */
	@Override
	public boolean addOrDelMblogSubscribe(MblogInfoQO qo) throws BusinessException  {
		// 创建返回结果
		boolean result = true;
		boolean isSubscribe = true;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(qo.getMyUserCode()) && null != qo.getIsSubscribe()){
				// 校验是否订阅
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("subUserCode", qo.getSubScribeUserCode());
				paramsMap.put("userCode", qo.getMyUserCode());
				paramsMap.put("teamCode", qo.getSubTeamCode());
				isSubscribe = mblogSubscribeMapper.checkIsSub(paramsMap) > 0;
				if(isSubscribe && qo.getIsSubscribe() == MblogConstants.MBLOG_YES){
					throw BusinessException.build("MBLOG_12004", "订阅不可重复操作");
				}
				if(!isSubscribe && qo.getIsSubscribe() == MblogConstants.MBLOG_NO){
					throw BusinessException.build("MBLOG_12004", "取消订阅不可重复操作");
				}
				if(MblogConstants.MBLOG_YES == qo.getIsSubscribe() && !isSubscribe){
					// 订阅信息
					MblogSubscribe sub = new MblogSubscribe();
					sub.setMblogSubscribeCode(UUIDHelper.getUUID()); //订阅CODE
					sub.setCreateTime(time); // 创建时间
					sub.setModifyTime(time); // 修改时间
					sub.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					sub.setUserCode(qo.getMyUserCode()); // 用户CODE
					sub.setTeamCode(qo.getSubTeamCode()); // 班组CODE
					sub.setSubUserCode(qo.getSubScribeUserCode()); // 被订阅CODE
					sub.setSubType(qo.getSubType()); // 被订阅类型 
					sub.setOrgCode(qo.getOrgCode()); // 组织CODE
					sub.setSubOrgCode(qo.getSubOrgCode()); // 被订阅组织CODE
					sub.setTenantCode(qo.getTenantCode()); // 租户CODE
					
					// 添加
					result = mblogSubscribeMapper.insert(sub) > 0;
					
				}else if(MblogConstants.MBLOG_NO == qo.getIsSubscribe() && isSubscribe){
					// 取消订阅
					paramsMap = new HashMap<String,Object>();
					paramsMap.put("subUserCode", qo.getSubScribeUserCode());
					paramsMap.put("userCode", qo.getMyUserCode());
					paramsMap.put("teamCode", qo.getSubTeamCode());
					result = mblogSubscribeMapper.deleteByCode(paramsMap) > 0;
				}else{
					result = false;
				}
			}else{
				throw BusinessException.build("MBLOG_12004", "userCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.addOrDelMblogSubscribe:订阅/取消订阅微博失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.addOrDelMblogSubscribe:订阅/取消订阅微博失败"+e);
			throw BusinessException.build("MBLOG_12004", "订阅/取消订阅微博失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: queryMyCollectList  
	    * @Description: 查询我收藏的微博列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryMyCollectList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode());
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryMyCollectByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryMyCollectByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryMyCollectList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: queryMySubscribeList  
	    * @Description: 查询我订阅的微博列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryMySubscribeList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("myUserCode", qo.getMyUserCode());
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryMySubByPage(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryMySubByPageCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.queryMySubscribeList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: getMblogInfo  
	    * @Description: 获取微博信息
	    * @param mblogCode	微博CODE
	    * @throws BusinessException
		* @return MblogInfo    返回类型
	 */
	@Override
	public MblogInfo getMblogInfo(String mblogCode) throws BusinessException {
		MblogInfo mblogInfo = null;
		try{
			if(StringTools.isNotEmpty(mblogCode)){
				mblogInfo = mblogInfoMapper.getDetailByCode(mblogCode);
			}else{
				throw BusinessException.build("MBLOG_12004", "mblogCode不可以为空");
			}
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.getMblogInfo:获取微博详情失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.getMblogInfo:获取微博详情失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博详情失败");
		}
		return mblogInfo;
	}

	/**
	 * 
	    * @Title: getForwardProcess  
	    * @Description: 获取微博的历程
	    * @param mblogCode 微博CODE
	    * @throws BusinessException
		* @return List<String>    返回类型
	 */
	@Override
	public List<String> getForwardProcess(String mblogCode) throws BusinessException {
		List<String> sourceMblogCodes = null;
		try{
			// 获取某一个微博的的转发历程来源
			String sourceMblogCode = mblogForwardMapper.queryForwardInfo(mblogCode);
			if(StringTools.isNotEmpty(sourceMblogCode)){
				// 有来源
				sourceMblogCodes = Arrays.asList((sourceMblogCode + "," + mblogCode).split(","));
			}else{
				// 无来源
				sourceMblogCodes = Arrays.asList(mblogCode);
			}
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.getForwardProcess:获取转发历程失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取转发历程失败");
		}
		
		return sourceMblogCodes;
	}
	
	/**
	 * 
	    * @Title: getMonitoredList  
	    * @Description: 获取监控的列表 
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean getMonitoredList(MblogInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("lineTime", qo.getLineTime());
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			paramsMap.put("isShield", qo.getIsShield() == 0 ? "0" : qo.getIsShield());
			paramsMap.put("orgCode", qo.getOrgCode());
			// 获取列表
			pageBean.setRecordList(mblogInfoMapper.queryMonitoredList(paramsMap));
			// 获取个数
			pageBean.setTotalCount((int)mblogInfoMapper.queryMonitoredListCount(paramsMap));
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.getMonitoredList:获取微博列表失败"+e);
			throw BusinessException.build("MBLOG_12004", "获取微博列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: modifyShield  
	    * @Description: 修改屏蔽信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean modifyShield(MblogInfoQO qo) throws BusinessException {
		boolean result = true;
		try{
			if(StringTools.isNotEmpty(qo.getMblogCode())){
				Map<String,Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("modifyTime", new Date().getTime());
				paramsMap.put("isShield", qo.getIsShield());
				paramsMap.put("shieldUserCode", qo.getMyUserCode());
				paramsMap.put("mblogCode", qo.getMblogCode());
				result = mblogInfoMapper.shieldInfoByCode(paramsMap) > 0;
			}else{
				result = false;
			}
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.modifyShield:微博屏蔽失败"+e);
			throw BusinessException.build("MBLOG_12004", "微博屏蔽失败");
		}
		return result;
	}

	/**
	 * 
	    * @Title: checkIsFor  
	    * @Description: 校验是否已经对此微博做了首次转发
	    * @param mblogCode	微博CODE
	    * @param userCode	用户CODE
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean checkIsFor(String mblogCode, String userCode) throws BusinessException {
		boolean result = true;
		try{
			// 获取某一个微博的的转发历程来源
			String sourceMblogCode = mblogForwardMapper.queryForwardInfo(mblogCode);
			Map<String,Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userCode", userCode);
			// 如果有被转发的来源
			if(StringTools.isNotEmpty(sourceMblogCode)){
				paramsMap.put("mblogCode", sourceMblogCode+","+mblogCode);
			}else{
			// 如果没有转发来源
				paramsMap.put("mblogCode", mblogCode);
			}
			result = mblogForwardMapper.checkIsFored(paramsMap) > 0;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.checkIsFor:校验失败"+e);
			throw BusinessException.build("MBLOG_12004", "校验失败");
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: checkIsSubscribe  
	    * @Description: 校验是否订阅了 对象
	    * @param userCode		用户CODE
	    * @param subjectCode	校验对象
	    * @param subType		订阅类型：1：个人，2：班组
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	@Override
	public boolean checkIsSubscribe(String userCode, String subjectCode, Byte subType) throws BusinessException {
		try{
			// 创建返回值
			boolean result = true;
			// 判断参数是否为空
			if(StringTools.isEmpty(subjectCode)){
				throw BusinessException.build("MBLOG_12004", "subjectCode不可以为空");
			}
			if(StringTools.isEmpty(userCode)){
				throw BusinessException.build("MBLOG_12004", "userCode不可以为空");
			}
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("userCode", userCode);
			// 校验个人还是班组的
			switch (subType) {
				case MblogConstants.MBLOG_SUB_PER:{ // 个人
					paramsMap.put("subUserCode", subjectCode);
					break;
				}
				case MblogConstants.MBLOG_SUB_TEAM:{ // 班组
					paramsMap.put("teamCode", subjectCode);
					break;
				}
				default:{
					result = false;
					break;
				}
			}
			// 为true 校验
			if(result){
				// 校验
				result = mblogSubscribeMapper.checkIsSub(paramsMap) > 0;
			}
			return result;
		}catch(BusinessException e){
			log.error("MblogInfoServiceImpl.checkIsSubscribe:校验是否订阅失败"+e);
			throw e;
		}catch(Exception e){
			log.error("MblogInfoServiceImpl.checkIsSubscribe:校验是否订阅失败"+e);
			throw BusinessException.build("MBLOG_12004", "校验是否订阅失败");
		}
	}
}
