package com.zssq.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogNum;
import com.zssq.dao.pojo.MblogResource;
import com.zssq.qo.MblogInfoAddQO;
import com.zssq.shiro.mysecurity.UUIDHelper;

/**
 * 
    * @ClassName: MblogUtils  
    * @Description: 微博工具  
    * @author Mr.B  
    * @date 2017年6月2日  
    *
 */
public class MblogUtils {

	/** log对象 **/
	private static Logger log = Logger.getLogger(MblogUtils.class);
	
	/**
	 * 
	    * @Title: logInfo  
	    * @Description: 输出日志信息
	    * @param content
		* @return void    返回类型
	 */
	public static void logInfo(String content){
		log.info(content);
	}
	
	/**
	 * 
	    * @Title: ownCreateMblogInfo  
	    * @Description: 创建微博信息
	    * @param mblogCode
	    * @param qo
		* @return MblogInfo    返回类型
	 */
	public static MblogInfo ownCreateMblogInfo(String mblogCode,MblogInfoAddQO qo){
		long time = new Date().getTime();
		// 创建微博信息
		MblogInfo info = new MblogInfo();
		info.setMblogCode(mblogCode); // 微博CODE
		info.setCreateTime(time); // 创建时间
		info.setModifyTime(time); // 修改时间
		info.setRemark(MblogConstants.MBLOG_BLANK); // 备注
		info.setOrgCode(qo.getOrgCode()); // 发布人组织CODE
		info.setUserCode(qo.getUserCode()); // 用户CODE
		info.setAgentOrgCode(qo.getAgentOrgCode()); // 代发人组织CODE
		info.setAgentUserCode(qo.getAgentUserCode()); // 代发人用户CODE
		info.setSourceMblogCode(qo.getSourceMblogCode()); // 微博来源CODE
		info.setSourceUserCode(qo.getSourceUserCode()); // 微博发布人来源CODE
		// 判断是否班组长操作
		if(qo.getIsTeam() != MblogConstants.MBLOG_YES){
			// 不是班组长
			info.setTeamCode(MblogConstants.MBLOG_BLANK); // 班组CODE
			info.setMblogDepend(MblogConstants.MBLOG_DEPEND_SELF); // 微博属于个人
		}else{
			// 是班组长
			info.setTeamCode(qo.getTeamCode()); // 班组CODE
			info.setMblogDepend(MblogConstants.MBLOG_DEPEND_GROUP); // 微博属于班组
		}
		// 判断是否是转发
		if(qo.getMblogSource() != MblogConstants.MBLOG_SOURCE_FORWARD){
			info.setMblogSource(MblogConstants.MBLOG_SOURCE_SELF); // 原创微博
		}else{
			info.setMblogSource(MblogConstants.MBLOG_SOURCE_FORWARD); // 转发微博
		}
		info.setMblogVisible(MblogConstants.MBLOG_VISIBLE_PUBLIC); // 微博公共可见
		info.setIsDelete(MblogConstants.MBLOG_NO); // 是否删除
		info.setIsShield(MblogConstants.MBLOG_NO); // 是否屏蔽
		info.setPublishTime(qo.getTimeSign()); // 发布时间
		info.setTenantCode(qo.getTenatCode()); // 租户
		info.setDynamicCode(qo.getDynamicCode());	// 动态CODE
		if(!qo.getImgs().isEmpty()){
			info.setMblogLogo(qo.getImgs().get(0)); // logo
		}else{
			info.setMblogLogo(MblogConstants.MBLOG_BLANK); // logo
		}
		return info;
	}
	
	/**
	 * 
	    * @Title: ownCreateMblogNum  
	    * @Description: 创建微博数量信息
	    * @param subjectCode
	    * @param numSource
		* @return MblogNum    返回类型
	 */
	public static MblogNum ownCreateMblogNum(String subjectCode,Byte numSource){
		long time = new Date().getTime();
		// 创建微博附属信息
		MblogNum num = new MblogNum();
		num.setMblogNumCode(UUIDHelper.getUUID()); // 微博数量CODE
		num.setSubjectCode(subjectCode); // 主体CODE
		num.setNumSource(numSource); // 数据来源 
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
		return num;
	}
	
	/**
	 * 
	    * @Title: ownCreateResList  
	    * @Description: 创建资源信息
	    * @param mblogCode
	    * @param qo
		* @return List<MblogResource>    返回类型
	 */
	public static List<MblogResource> ownCreateResList(String mblogCode,MblogInfoAddQO qo){
		
		long time = new Date().getTime();
		// 创建资源对象
		List<MblogResource> resList = new ArrayList<MblogResource>();
		// 添加文本资源
		if(null != qo.getContent() && !"".equals(qo.getContent())){
			MblogResource conRes = new MblogResource();
			conRes.setMblogRecourceCode(UUIDHelper.getUUID()); // 资源CODE
			conRes.setContent(qo.getContent()); // 内容
			conRes.setCreateTime(time); // 创建时间
			conRes.setMblogCode(mblogCode); // 微博CODE 
			conRes.setModifyTime(time); // 修改时间
			conRes.setRemark(MblogConstants.MBLOG_BLANK); // 备注
			conRes.setOrgCode(qo.getOrgCode()); // 组织CODE
			conRes.setResType(MblogConstants.MBLOG_RESOURCE_TEXT); // 资源类型
			conRes.setTenantCode(qo.getTenatCode()); // 租户
			
			resList.add(conRes);
		}

		// 添加摘要资源
		if(null != qo.getSummary() && !"".equals(qo.getSummary())){
			MblogResource sumRes = new MblogResource();
			sumRes.setMblogRecourceCode(UUIDHelper.getUUID());
			sumRes.setContent(qo.getSummary());
			sumRes.setCreateTime(time);
			sumRes.setMblogCode(mblogCode);
			sumRes.setModifyTime(time);
			sumRes.setRemark(MblogConstants.MBLOG_BLANK);
			sumRes.setOrgCode(qo.getOrgCode());
			sumRes.setResType(MblogConstants.MBLOG_RESOURCE_SUMMARY);
			sumRes.setTenantCode(qo.getTenatCode());
			
			resList.add(sumRes);
		}
		// 添加图片资源
		if(!qo.getImgs().isEmpty()){
			for(String img : qo.getImgs()){
				if(null != img && !"".equals(img.trim())){
					MblogResource imgRes = new MblogResource();
					imgRes.setMblogRecourceCode(UUIDHelper.getUUID());
					imgRes.setContent(img);
					imgRes.setCreateTime(time);
					imgRes.setMblogCode(mblogCode);
					imgRes.setModifyTime(time);
					imgRes.setRemark(MblogConstants.MBLOG_BLANK);
					imgRes.setOrgCode(qo.getOrgCode());
					imgRes.setResType(MblogConstants.MBLOG_RESOURCE_IMG);
					imgRes.setTenantCode(qo.getTenatCode());
					
					resList.add(imgRes);
				}
			}
		}
		// 添加音频资源
		if(!qo.getAudios().isEmpty()){
			for(String audio : qo.getAudios()){
				if(null != audio && !"".equalsIgnoreCase(audio.trim())){
					MblogResource audioRes = new MblogResource();
					audioRes.setMblogRecourceCode(UUIDHelper.getUUID());
					audioRes.setContent(audio);
					audioRes.setCreateTime(time);
					audioRes.setMblogCode(mblogCode);
					audioRes.setModifyTime(time);
					audioRes.setRemark(MblogConstants.MBLOG_BLANK);
					audioRes.setOrgCode(qo.getOrgCode());
					audioRes.setResType(MblogConstants.MBLOG_RESOURCE_AUDIO);
					audioRes.setTenantCode(qo.getTenatCode());
					
					resList.add(audioRes);
				}
			}
		}
		// 添加视频资源
		if(!qo.getVideos().isEmpty()){
			for(String video : qo.getVideos()){
				if(null != video && !"".equals(video.trim())){
					MblogResource videoRes = new MblogResource();
					videoRes.setMblogRecourceCode(UUIDHelper.getUUID());
					videoRes.setContent(video);
					videoRes.setCreateTime(time);
					videoRes.setMblogCode(mblogCode);
					videoRes.setModifyTime(time);
					videoRes.setRemark(MblogConstants.MBLOG_BLANK);
					videoRes.setOrgCode(qo.getOrgCode());
					videoRes.setResType(MblogConstants.MBLOG_RESOURCE_VIDEO);
					videoRes.setTenantCode(qo.getTenatCode());
					resList.add(videoRes);
				}
				
			}
		}
		
		return resList;
	}
}
