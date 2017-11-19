package com.zssq.mblog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.constants.MblogConstants;
import com.zssq.mblog.dao.mapper.MblogAtMapper;
import com.zssq.mblog.dao.mapper.MblogCommentMapper;
import com.zssq.mblog.dao.mapper.MblogForwardMapper;
import com.zssq.mblog.dao.mapper.MblogInfoMapper;
import com.zssq.mblog.dao.mapper.MblogNumMapper;
import com.zssq.mblog.dao.mapper.MblogResourceMapper;
import com.zssq.mblog.dao.mapper.MblogSubscribeMapper;
import com.zssq.mblog.dao.mapper.MblogTopicMapper;
import com.zssq.mblog.dao.mapper.TransferIdCodeMapper;
import com.zssq.mblog.dao.mapper.TransferMblogMapper;
import com.zssq.mblog.dao.mapper.TransferMblogMentionMapper;
import com.zssq.mblog.dao.mapper.TransferMblogReplysMapper;
import com.zssq.mblog.dao.mapper.TransferMblogTopicMapper;
import com.zssq.mblog.dao.mapper.TransferMsSubscribeRelationMapper;
import com.zssq.mblog.dao.mapper.TransferTaskMapper;
import com.zssq.mblog.pojo.MblogAt;
import com.zssq.mblog.pojo.MblogComment;
import com.zssq.mblog.pojo.MblogForward;
import com.zssq.mblog.pojo.MblogInfo;
import com.zssq.mblog.pojo.MblogNum;
import com.zssq.mblog.pojo.MblogResource;
import com.zssq.mblog.pojo.MoveAtVo;
import com.zssq.mblog.pojo.MoveComVo;
import com.zssq.mblog.pojo.MoveMblogVo;
import com.zssq.mblog.pojo.TransferForMblog;
import com.zssq.mblog.pojo.TransferIdCode;
import com.zssq.mblog.pojo.TransferTaskVo;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;

@Service
public class MblogService {
	// 微博AT
	@Resource
	private MblogAtMapper mblogAtMapper;
	// 微博评论
	@Resource
	private MblogCommentMapper mblogCommentMapper;
	// 微博转发
	@Resource
	private MblogForwardMapper mblogForwardMapper;
	// 微博信息
	@Resource
	private MblogInfoMapper mblogInfoMapper;
	// 微博数量
	@Resource
	private MblogNumMapper mblogNumMapper;
	// 微博资源
	@Resource
	private MblogResourceMapper mblogResourceMapper;
	// 微博订阅
	@Resource
	private MblogSubscribeMapper mblogSubscribeMapper;
	// 微博话题
	@Resource
	private MblogTopicMapper mblogTopicMapper;
	// 迁移微博
	@Resource
	private TransferMblogMapper transferMblogMapper;
	// 迁移微博AT
	@Resource
	private TransferMblogMentionMapper transferMblogMentionMapper;
	// 迁移微博评论
	@Resource
	private TransferMblogReplysMapper transferMblogReplysMapper;
	// 迁移微博话题
	@Resource
	private TransferMblogTopicMapper transferMblogTopicMapper;
	// 迁移微博订阅
	@Resource
	private TransferMsSubscribeRelationMapper transferMsSubscribeRelationMapper;
	// 迁移微博任务
	@Resource
	private TransferTaskMapper transferTaskMapper;
	// 中间表
	@Resource
	private TransferIdCodeMapper transferIdCodeMapper;
	
	/**
	 * 
	    * @Title: transferSelfMblogInfo  
	    * @Description: 迁移原创微博
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferSelfMblogInfo(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		
		// 查询需要转发的内容
		List<MoveMblogVo> moveList = transferMblogMapper.selectAllSelfMblogInfo(paramsMap);
		// 插入微博数据
		List<MblogInfo> mblogInfoList = new ArrayList<MblogInfo>();
		// 插入微博资源信息
		List<MblogResource> resList = new ArrayList<MblogResource>();
		// 插入微博数目信息
		//List<MblogNum> numList = new ArrayList<MblogNum>();
		// 插入中间表数据
		List<TransferIdCode> idCodeList = new ArrayList<TransferIdCode>();
		// 遍历数据
		if(null != moveList && !moveList.isEmpty()){
			for(MoveMblogVo vo : moveList){
				String mblogCode = UUIDHelper.getUUID();
				long time = vo.getPubTime();
				if(StringTools.isNotEmpty(vo.getUserCode())){
					MblogInfo info = new MblogInfo();
					//MblogNum num = new MblogNum();
					TransferIdCode idCode = new TransferIdCode();
					
					// ================微博信息==========================
					info.setMblogCode(mblogCode); // 微博CODE
					info.setCreateTime(time); // 创建时间
					info.setModifyTime(time); // 修改时间
					info.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					if(StringTools.isNotEmpty(vo.getCityCode())){
						info.setOrgCode(vo.getCityCode()); // 发布人组织CODE
					}else{
						info.setOrgCode(vo.getProCode());
					}
					info.setUserCode(vo.getUserCode()); // 用户CODE
					//info.setSourceMblogCode(vo.getSourceMblogCode()); // 微博来源CODE
					//info.setSourceUserCode(vo.getSourceUserCode()); // 微博发布人来源CODE
					// 判断是否班组长操作
					if(StringTools.isEmpty(vo.getTeamCode())){
						// 不是班组长
						info.setTeamCode(MblogConstants.MBLOG_BLANK); // 班组CODE
						info.setMblogDepend(MblogConstants.MBLOG_DEPEND_SELF); // 微博属于个人
					}else{
						// 是班组长
						info.setTeamCode(vo.getTeamCode()); // 班组CODE
						info.setMblogDepend(MblogConstants.MBLOG_DEPEND_GROUP); // 微博属于班组
					}
					// 判断是否是转发
					info.setMblogSource(MblogConstants.MBLOG_SOURCE_SELF); // 原创微博
					//info.setMblogSource(MblogConstants.MBLOG_SOURCE_FORWARD); // 转发微博
					info.setMblogVisible(MblogConstants.MBLOG_VISIBLE_PUBLIC); // 微博公共可见
					info.setIsDelete(MblogConstants.MBLOG_NO); // 是否删除
					info.setIsShield(MblogConstants.MBLOG_NO); // 是否屏蔽
					info.setPublishTime(time); // 发布时间
					info.setTenantCode(vo.getTenantCode()); // 租户
					info.setDynamicCode(mblogCode);	// 动态CODE
					if(StringTools.isNotEmpty(vo.getImageUrl())){
						info.setMblogLogo(vo.getImageUrl()); // logo
					}else{
						info.setMblogLogo(MblogConstants.MBLOG_BLANK); // logo
					}
					
					mblogInfoList.add(info);
					
					// =================微博数量=========================
					/*num.setMblogNumCode(UUIDHelper.getUUID()); // 微博数量CODE
				num.setSubjectCode(mblogCode); // 主体CODE
				num.setNumSource(MblogConstants.MBLOG_NUM_MBLOG); // 数据来源 
				num.setCreateTime(time); // 创建时间
				num.setModifyTime(time); // 修改时间
				num.setRemark(MblogConstants.MBLOG_BLANK); // 备注
				num.setPraiseNum(MblogConstants.MBLOG_ZERO); // 点赞数
				num.setCommentNum(vo.getComNum()); // 评论数
				num.setCollectNum(MblogConstants.MBLOG_ZERO); // 收藏数
				num.setRecommendNum(MblogConstants.MBLOG_ZERO); // 推荐数
				num.setReportNum(MblogConstants.MBLOG_ZERO); // 举报数
				num.setForwardNum(vo.getForNum()); // 转发数
				num.setReplyNum(MblogConstants.MBLOG_ZERO); // 回复数
				num.setTeamEssenceNum(MblogConstants.MBLOG_ZERO); // 班组置精数
				num.setCityEssenceNum(MblogConstants.MBLOG_ZERO); // 市级置精数
				num.setProvinceEssenceNum(MblogConstants.MBLOG_ZERO); // 省级置精数 
				num.setGroupEssenceNum(MblogConstants.MBLOG_ZERO); // 集团置精数
				
				numList.add(num);*/
					// =================微博中间表=========================
					idCode.setMblogCode(mblogCode);
					idCode.setMblogId(vo.getId());
					idCode.setUserCode(vo.getUserCode());
					idCode.setPutTime(time);
					idCode.setOrgCode(info.getOrgCode());
					idCode.setTenantCode(vo.getTenantCode());
					idCode.setMusicId(vo.getMusicId());
					idCode.setVideoId(vo.getVideoId());
					idCode.setComNum(vo.getComNum());
					idCode.setForNum(vo.getForNum());
					idCodeList.add(idCode);
					
					// =================微博资源表=========================
					// 添加文本资源
					if(null != vo.getContent() && !"".equals(vo.getContent())){
						MblogResource conRes = new MblogResource();
						conRes.setMblogRecourceCode(UUIDHelper.getUUID()); // 资源CODE
						conRes.setContent(vo.getContent()); // 内容
						conRes.setCreateTime(time); // 创建时间
						conRes.setMblogCode(mblogCode); // 微博CODE 
						conRes.setModifyTime(time); // 修改时间
						conRes.setRemark(MblogConstants.MBLOG_BLANK); // 备注
						conRes.setOrgCode(info.getOrgCode()); // 组织CODE
						conRes.setResType(MblogConstants.MBLOG_RESOURCE_TEXT); // 资源类型
						conRes.setTenantCode(info.getTenantCode()); // 租户
						
						resList.add(conRes);
						
						
						MblogResource sumRes = new MblogResource();
						sumRes.setMblogRecourceCode(UUIDHelper.getUUID());
						sumRes.setContent(vo.getContent());
						sumRes.setCreateTime(time);
						sumRes.setMblogCode(mblogCode);
						sumRes.setModifyTime(time);
						sumRes.setRemark(MblogConstants.MBLOG_BLANK);
						sumRes.setOrgCode(info.getOrgCode());
						sumRes.setResType(MblogConstants.MBLOG_RESOURCE_SUMMARY);
						sumRes.setTenantCode(info.getTenantCode());
						
						resList.add(sumRes);
					}
					
					// 添加图片资源
					/*if(StringTools.isNotEmpty(vo.getImgUrls())){
					String[] imgs = vo.getImgUrls().split(",");
					for(String img : imgs){
						if(null != img && !"".equals(img.trim())){
							MblogResource imgRes = new MblogResource();
							imgRes.setMblogRecourceCode(UUIDHelper.getUUID());
							imgRes.setContent(img);
							imgRes.setCreateTime(time);
							imgRes.setMblogCode(mblogCode);
							imgRes.setModifyTime(time);
							imgRes.setRemark(MblogConstants.MBLOG_BLANK);
							imgRes.setOrgCode(info.getOrgCode());
							imgRes.setResType(MblogConstants.MBLOG_RESOURCE_IMG);
							imgRes.setTenantCode(info.getTenantCode());
							
							resList.add(imgRes);
						}
					}
				}*/
					// 添加音频资源
					/*if(StringTools.isNotEmpty(vo.getMusicUrl())){
					MblogResource audioRes = new MblogResource();
					audioRes.setMblogRecourceCode(UUIDHelper.getUUID());
					audioRes.setContent(vo.getMusicUrl());
					audioRes.setCreateTime(time);
					audioRes.setMblogCode(mblogCode);
					audioRes.setModifyTime(time);
					audioRes.setRemark(MblogConstants.MBLOG_BLANK);
					audioRes.setOrgCode(info.getOrgCode());
					audioRes.setResType(MblogConstants.MBLOG_RESOURCE_AUDIO);
					audioRes.setTenantCode(info.getTenantCode());
					
					resList.add(audioRes);
				}*/
					// 添加视频资源
					/*if(StringTools.isNotEmpty(vo.getVideoUrl())){
					MblogResource videoRes = new MblogResource();
					videoRes.setMblogRecourceCode(UUIDHelper.getUUID());
					videoRes.setContent(vo.getVideoUrl());
					videoRes.setCreateTime(time);
					videoRes.setMblogCode(mblogCode);
					videoRes.setModifyTime(time);
					videoRes.setRemark(MblogConstants.MBLOG_BLANK);
					videoRes.setOrgCode(info.getOrgCode());
					videoRes.setResType(MblogConstants.MBLOG_RESOURCE_VIDEO);
					videoRes.setTenantCode(info.getTenantCode());
					resList.add(videoRes);
						
				}*/
				}
			}
			if(!mblogInfoList.isEmpty()){
				// 插入微博数据
				mblogInfoMapper.batchInsert(mblogInfoList);
			}
			if(!resList.isEmpty()){
				// 插入微博资源信息
				mblogResourceMapper.batchInsert(resList);
			}
			// 插入微博数目信息
			//mblogNumMapper.batchInsert(numList);
			if(!idCodeList.isEmpty()){
				// 插入中间表数据
				transferIdCodeMapper.batchInsert(idCodeList);
			}
			
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferForMblogInfo  
	    * @Description: 迁移转发微博
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferForMblogInfo(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		
		// 查询需要转发的内容
		List<MoveMblogVo> moveList = transferMblogMapper.selectAllForMblogInfo(paramsMap);
		// 插入微博数据
		List<MblogInfo> mblogInfoList = new ArrayList<MblogInfo>();
		// 插入微博资源信息
		List<MblogResource> resList = new ArrayList<MblogResource>();
		// 插入微博数目信息
		//List<MblogNum> numList = new ArrayList<MblogNum>();
		// 插入中间表数据
		List<TransferIdCode> idCodeList = new ArrayList<TransferIdCode>();
		// 遍历数据
		if(null != moveList && !moveList.isEmpty()){
			for(MoveMblogVo vo : moveList){
				String mblogCode = UUIDHelper.getUUID();
				long time = vo.getPubTime();
				if(StringTools.isNotEmpty(vo.getUserCode())){
					MblogInfo info = new MblogInfo();
					//MblogNum num = new MblogNum();
					TransferIdCode idCode = new TransferIdCode();
					
					// ================微博信息==========================
					info.setMblogCode(mblogCode); // 微博CODE
					info.setCreateTime(time); // 创建时间
					info.setModifyTime(time); // 修改时间
					info.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					if(StringTools.isNotEmpty(vo.getCityCode())){
						info.setOrgCode(vo.getCityCode()); // 发布人组织CODE
					}else{
						info.setOrgCode(vo.getProCode());
					}
					info.setUserCode(vo.getUserCode()); // 用户CODE
					info.setSourceMblogCode(vo.getSourceMblogCode()); // 微博来源CODE
					info.setSourceUserCode(vo.getSourceUserCode()); // 微博发布人来源CODE
					// 判断是否班组长操作
					//if(StringTools.isEmpty(vo.getTeamCode())){
					// 不是班组长
					info.setTeamCode(MblogConstants.MBLOG_BLANK); // 班组CODE
					info.setMblogDepend(MblogConstants.MBLOG_DEPEND_SELF); // 微博属于个人
					//}else{
					// 是班组长
					//	info.setTeamCode(vo.getTeamCode()); // 班组CODE
					//	info.setMblogDepend(MblogConstants.MBLOG_DEPEND_GROUP); // 微博属于班组
					//}
					// 判断是否是转发
					//info.setMblogSource(MblogConstants.MBLOG_SOURCE_SELF); // 原创微博
					info.setMblogSource(MblogConstants.MBLOG_SOURCE_FORWARD); // 转发微博
					info.setMblogVisible(MblogConstants.MBLOG_VISIBLE_PUBLIC); // 微博公共可见
					info.setIsDelete(MblogConstants.MBLOG_NO); // 是否删除
					info.setIsShield(MblogConstants.MBLOG_NO); // 是否屏蔽
					info.setPublishTime(time); // 发布时间
					info.setTenantCode(vo.getTenantCode()); // 租户
					info.setDynamicCode(mblogCode);	// 动态CODE
					//if(StringTools.isNotEmpty(vo.getImageUrl())){
					//	info.setMblogLogo(vo.getImageUrl()); // logo
					//}else{
					info.setMblogLogo(MblogConstants.MBLOG_BLANK); // logo
					//}
					
					mblogInfoList.add(info);
					
					// =================微博数量=========================
					/*num.setMblogNumCode(UUIDHelper.getUUID()); // 微博数量CODE
					num.setSubjectCode(mblogCode); // 主体CODE
					num.setNumSource(MblogConstants.MBLOG_NUM_MBLOG); // 数据来源 
					num.setCreateTime(time); // 创建时间
					num.setModifyTime(time); // 修改时间
					num.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					num.setPraiseNum(MblogConstants.MBLOG_ZERO); // 点赞数
					num.setCommentNum(vo.getComNum()); // 评论数
					num.setCollectNum(MblogConstants.MBLOG_ZERO); // 收藏数
					num.setRecommendNum(MblogConstants.MBLOG_ZERO); // 推荐数
					num.setReportNum(MblogConstants.MBLOG_ZERO); // 举报数
					num.setForwardNum(vo.getForNum()); // 转发数
					num.setReplyNum(MblogConstants.MBLOG_ZERO); // 回复数
					num.setTeamEssenceNum(MblogConstants.MBLOG_ZERO); // 班组置精数
					num.setCityEssenceNum(MblogConstants.MBLOG_ZERO); // 市级置精数
					num.setProvinceEssenceNum(MblogConstants.MBLOG_ZERO); // 省级置精数 
					num.setGroupEssenceNum(MblogConstants.MBLOG_ZERO); // 集团置精数
					
					numList.add(num);*/
					// =================微博中间表=========================
					idCode.setMblogCode(mblogCode);
					idCode.setMblogId(vo.getId());
					idCode.setUserCode(vo.getUserCode());
					idCode.setPutTime(time);
					idCode.setOrgCode(info.getOrgCode());
					idCode.setTenantCode(vo.getTenantCode());
					idCode.setMusicId(null);
					idCode.setVideoId(null);
					idCode.setComNum(vo.getComNum());
					idCode.setForNum(vo.getForNum());
					idCodeList.add(idCode);
					
					// =================微博资源表=========================
					// 添加文本资源
					if(null != vo.getContent() && !"".equals(vo.getContent())){
						MblogResource conRes = new MblogResource();
						conRes.setMblogRecourceCode(UUIDHelper.getUUID()); // 资源CODE
						conRes.setContent(vo.getContent()); // 内容
						conRes.setCreateTime(time); // 创建时间
						conRes.setMblogCode(mblogCode); // 微博CODE 
						conRes.setModifyTime(time); // 修改时间
						conRes.setRemark(MblogConstants.MBLOG_BLANK); // 备注
						conRes.setOrgCode(info.getOrgCode()); // 组织CODE
						conRes.setResType(MblogConstants.MBLOG_RESOURCE_TEXT); // 资源类型
						conRes.setTenantCode(info.getTenantCode()); // 租户
						
						resList.add(conRes);
						
						
						MblogResource sumRes = new MblogResource();
						sumRes.setMblogRecourceCode(UUIDHelper.getUUID());
						sumRes.setContent(vo.getContent());
						sumRes.setCreateTime(time);
						sumRes.setMblogCode(mblogCode);
						sumRes.setModifyTime(time);
						sumRes.setRemark(MblogConstants.MBLOG_BLANK);
						sumRes.setOrgCode(info.getOrgCode());
						sumRes.setResType(MblogConstants.MBLOG_RESOURCE_SUMMARY);
						sumRes.setTenantCode(info.getTenantCode());
						
						resList.add(sumRes);
					}
				}
			}
			
			if(!mblogInfoList.isEmpty()){
				// 插入微博数据
				mblogInfoMapper.batchInsert(mblogInfoList);
			}
			if(!resList.isEmpty()){
				// 插入微博资源信息
				mblogResourceMapper.batchInsert(resList);
			}
			// 插入微博数目信息
			//mblogNumMapper.batchInsert(numList);
			if(!idCodeList.isEmpty()){
				// 插入中间表数据
				transferIdCodeMapper.batchInsert(idCodeList);
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferForPro  
	    * @Description: 迁移转发历程
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferForPro(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		
		// 查询需要转发的内容
		List<TransferForMblog> moveList = transferMblogMapper.selectForPro(paramsMap);
		// 插入微博历程数据
		List<MblogForward> forList = new ArrayList<MblogForward>();
		// 遍历数据
		if(null != moveList && !moveList.isEmpty()){
			for(TransferForMblog vo : moveList){
				if(null != vo){
					if(StringTools.isNotEmpty(vo.getCurMblogCode()) && StringTools.isNotEmpty(vo.getSourceMblogCode())){
						MblogForward forward = new MblogForward();
						forward.setUserCode(vo.getUserCode());
						forward.setCurMblogCode(vo.getCurMblogCode());
						forward.setSourceMblogCode(vo.getSourceMblogCode());
						if(StringTools.isNotEmpty(vo.getForMblogCode())){
							if(!vo.getForMblogCode().equals(vo.getSourceMblogCode())){
								forward.setSourceMblogCode(vo.getSourceMblogCode()+","+vo.getForMblogCode());
							}
						}
						forList.add(forward);
					}
				}
			}
			if(!forList.isEmpty()){
				// 插入数据
				mblogForwardMapper.batchInsert(forList);
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferComment  
	    * @Description: 迁移评论
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferComment(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		
		// 查询需要转发的内容
		List<MoveComVo> moveList = transferMblogReplysMapper.selectAllComment(paramsMap);
		// 插入评论内容
		List<MblogComment> comList = new ArrayList<MblogComment>();
		// 插入评论数量
		List<MblogNum> numList = new ArrayList<MblogNum>();
		
		// 遍历数据
		if(null != moveList && !moveList.isEmpty()){
			for(MoveComVo vo : moveList){
				String commentCode = UUIDHelper.getUUID();
				long time = vo.getReplyTime();
				if(StringTools.isNotEmpty(vo.getUserCode())){
					// 创建评论信息
					MblogComment comment = new MblogComment();
					comment.setMblogCommentCode(commentCode); // 评论CODE
					comment.setCreateTime(time); // 创建时间
					comment.setModifyTime(time); // 修改时间
					comment.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					comment.setUserCode(vo.getUserCode()); // 用户CODE
					comment.setMblogCode(vo.getMblogCode()); // 微博CODE
					comment.setContent(vo.getContent()); // 内容
					comment.setIsDelete(MblogConstants.MBLOG_NO); // 是否删除
					comment.setIsShield(MblogConstants.MBLOG_NO); // 是否屏蔽
					if(StringTools.isNotEmpty(vo.getCityCode())){
						comment.setOrgCode(vo.getCityCode()); // 发布人组织CODE
					}else{
						comment.setOrgCode(vo.getProCode());
					}
					comment.setTenantCode(vo.getTenantCode()); // 租户
					
					comList.add(comment);
					
					// =================微博数量=========================
					MblogNum num = new MblogNum();
					num.setMblogNumCode(UUIDHelper.getUUID()); // 微博数量CODE
					num.setSubjectCode(commentCode); // 主体CODE
					num.setNumSource(MblogConstants.MBLOG_NUM_COMMENT); // 数据来源 
					num.setCreateTime(time); // 创建时间
					num.setModifyTime(time); // 修改时间
					num.setRemark(MblogConstants.MBLOG_BLANK); // 备注
					num.setPraiseNum(MblogConstants.MBLOG_ZERO); // 点赞数
					num.setCommentNum(MblogConstants.MBLOG_ZERO); // 评论数
					num.setCollectNum(MblogConstants.MBLOG_ZERO); // 收藏数
					num.setRecommendNum(MblogConstants.MBLOG_ZERO); // 推荐数
					num.setReportNum(MblogConstants.MBLOG_ZERO); // 举报数
					num.setForwardNum(MblogConstants.MBLOG_ZERO); // 转发数
					num.setReplyNum(MblogConstants.MBLOG_ZERO); // 回复数
					num.setTeamEssenceNum(MblogConstants.MBLOG_ZERO); // 班组置精数
					num.setCityEssenceNum(MblogConstants.MBLOG_ZERO); // 市级置精数
					num.setProvinceEssenceNum(MblogConstants.MBLOG_ZERO); // 省级置精数 
					num.setGroupEssenceNum(MblogConstants.MBLOG_ZERO); // 集团置精数
					
					numList.add(num);
				}
			}
			
			if(!comList.isEmpty()){
				// 插入数据
				mblogCommentMapper.batchInsert(comList);
				mblogNumMapper.batchInsert(numList);
				
			}
			
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferAt  
	    * @Description: 插入数据
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferAt(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		
		// 查询At列表
		List<MoveAtVo> moveList = transferMblogMentionMapper.selectAtList(paramsMap);
		// 插入AT信息
		List<MblogAt> atList = new ArrayList<MblogAt>();
		
		// 遍历数据
		if(null != moveList && !moveList.isEmpty()){
			for(MoveAtVo vo : moveList){
				long time = vo.getCreateTime();
				// 创建AT临时对象
				MblogAt at = new MblogAt();
				at.setMblogAtCode(UUIDHelper.getUUID()); // atCODE
				at.setCreateTime(time); // 创建时间
				at.setModifyTime(time); // 修改时间
				at.setRemark(MblogConstants.MBLOG_BLANK); // 备注
				
				at.setOrgCode(vo.getOrgCode()); // 组织CODE
				at.setTenantCode(vo.getTenantCode()); // 租户
				 
				at.setUserCode(vo.getUserCode()); // 用户CODE
				at.setType(MblogConstants.MBLOG_AT_PUS); // 类型
				at.setAtSite(MblogConstants.MBLOG_AT_SITE_MBLOG); // 微博
				at.setMblogCode(vo.getMblogCode()); // 微博CODE
				at.setCommentCode(MblogConstants.MBLOG_BLANK); // 评论CODE
				at.setReplyCode(MblogConstants.MBLOG_BLANK); // 回复CODE
				
				atList.add(at);
			}
			// 插入数据
			mblogAtMapper.batchInsert(atList);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferSub  
	    * @Description: 迁移订阅信息
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferSub(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogSubscribeMapper.insertIntoSelect(paramsMap);
		return true;
	}
	
	/**
	 * 
	    * @Title: transferTopic  
	    * @Description: 迁移话题
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferTopic(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogTopicMapper.insertIntoSelect(paramsMap);
		return true;
	}
	
	/**
	 * 
	    * @Title: transferImg  
	    * @Description: 迁移图片
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferImg(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogResourceMapper.insertIntoImg(paramsMap);
		return true;
	}
	
	/**
	 * 
	    * @Title: transferVideo  
	    * @Description: 迁移视频
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferVideo(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogResourceMapper.insertIntoVideo(paramsMap);
		return true;
	}
	
	/**
	 * 
	    * @Title: transferMusic  
	    * @Description: 迁移音频
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferMusic(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogResourceMapper.insertIntoMusic(paramsMap);
		return true;
	}
	
	/**
	 * 
	    * @Title: transferNum  
	    * @Description: 迁移微博数量
	    * @param pageNo
	    * @param pageSize
		* @return boolean    返回类型
	 */
	@Transactional
	public boolean transferNum(Integer pageNo,Integer pageSize){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("startPage", pageNo*pageSize);
		paramsMap.put("pageSize", pageSize);
		// 插入数据
		mblogNumMapper.insertIntoSelect(paramsMap);
		return true;
	}
	
	public List<TransferTaskVo> getTaskList(){
		return transferTaskMapper.selectAllTask();
	}
	
	public boolean updateTaskStatus(Byte taskType,Byte taskStatus){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("taskType", taskType);
		paramsMap.put("taskStatus", taskStatus);
		paramsMap.put("curTime", new Date().getTime());
		return transferTaskMapper.updateTaskStatus(paramsMap) > 0;
	}
	
	public boolean updateTaskTotal(Byte taskType,Integer totalPage,Integer totalCount){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("taskType", taskType);
		paramsMap.put("totalCount", totalCount);
		paramsMap.put("totalPage", totalPage);
		return transferTaskMapper.updateTaskTotal(paramsMap) > 0;
	}
	
	public boolean updateTaskPage(Byte taskType,Integer pageNo){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("taskType", taskType);
		paramsMap.put("pageNo", pageNo);
		return transferTaskMapper.updateTaskPage(paramsMap) > 0;
	}
	
	
	public int getSelfMblogCount(){
		return transferMblogMapper.selectAllSelfMblogInfoCount();
	}
	
	public int getForMblogCount(){
		return transferMblogMapper.selectAllForMblogInfoCount();
	}
	
	public int getCommentCount(){
		return transferMblogReplysMapper.selectAllCommentCount();
	}
	
	public int getSubCount(){
		return transferMsSubscribeRelationMapper.selectSubCount();
	}
	
	public int getTopicCount(){
		return transferMblogTopicMapper.selectTopicCount();
	}
	
	public int getAtCount(){
		return transferMblogMentionMapper.selectAtListCount();
	}
	
	public int getImgCount(){
		return mblogResourceMapper.selectImgCount();
	}
	
	public int getVideoCount(){
		return mblogResourceMapper.selectVideoCount();
	}
	
	public int getMusicCount(){
		return mblogResourceMapper.selectMusicCount();
	}
	
	public int getNumCount(){
		return mblogNumMapper.selectNumCount();
	}
}
