package com.zssq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogAttachMapper;
import com.zssq.dao.mapper.BlogClassMapper;
import com.zssq.dao.mapper.BlogCommentMapper;
import com.zssq.dao.mapper.BlogDataMapper;
import com.zssq.dao.mapper.BlogReplyMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.KafkaService;
import com.zssq.service.BlogThirdDataService;
import com.zssq.vo.BlogThirdDataVO;

/**
 * 
 * @ClassName: BlogThirdDataServiceImpl
 * @Description: kafka消息
 * @author ZKZ
 * @date 2017年4月22日
 *
 */
@Service("blogThirdDataService")
public class BlogThirdDataServiceImpl implements BlogThirdDataService, KafkaService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogDataMapper blogDataMapper;
	@Autowired
	private BlogAttachMapper blogAttachMapper;
	@Autowired
	private BlogClassMapper blogClassMapper;
	@Autowired
	private BlogCommentMapper blogCommentMapper;
	@Autowired
	private BlogReplyMapper blogReplyMapper;

	/**
	 * 更改数量
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void invokeService(ConsumerRecord consumerRecord) throws Exception {
		try{
			// 获取参数
			Object recordValue = consumerRecord.value();
			
			// 参数校验
			if (recordValue == null) {
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：获取参数失败");
				throw BusinessException.build("COMMON_402", "recordValue");
			}
				
			// 获取参数
			BlogThirdDataVO blogThirdDataVO = JSONObject.toJavaObject(JSON.parseObject((String)recordValue),BlogThirdDataVO.class);
			
			// 参数校验
			if(blogThirdDataVO == null){
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：获取参数失败");
				throw BusinessException.build("COMMON_402", "blogThirdDataVO");
			}
			
			// 获取参数
			Long modifyTime = blogThirdDataVO.getModifyTime();
			Integer updateNumber = blogThirdDataVO.getUpdateNumber();
			Byte updateClass = blogThirdDataVO.getUpdateClass();
			String subjectCode = blogThirdDataVO.getSubjectCode();
			
			// 参数校验
			if (modifyTime == null || modifyTime == 0) {
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：modifyTime为空");
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			if (updateNumber == null || modifyTime == 0) {
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：updateNumber为空");
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if (updateClass == null || updateClass == 0) {
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：updateClass为空");
				throw BusinessException.build("COMMON_402", "updateClass");
			}
			if (StringUtils.isBlank(subjectCode)) {
				log.info("BlogThirdDataServiceImpl.invokeService:kafka处理业务更新数量失败：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
				
			// 执行操作
			switch(updateClass){
				// 博客班组置精次数
				case BlogConstants.BLOG_THIRD_BLOG_QUALITY_TEAM:{
					updateBlogDataNum(blogThirdDataVO, "teamQualityNum");
					break;
				}
				// 修改集团置精次数
				case BlogConstants.BLOG_THIRD_BLOG_QUALITY_GROUP:{
					updateBlogDataNum(blogThirdDataVO, "groupQualityNum");
					break;
				}
				// 修改省置精次数
				case BlogConstants.BLOG_THIRD_BLOG_QUALITY_PROVINCE:{
					updateBlogDataNum(blogThirdDataVO, "provinceQualityNum");
					break;
				}
				// 修改市置精次数
				case BlogConstants.BLOG_THIRD_BLOG_QUALITY_CITY:{
					updateBlogDataNum(blogThirdDataVO, "cityQualityNum");
					break;
				}
				// 修改博客浏览量
				case BlogConstants.BLOG_THIRD_BLOG_READ:{
					updateBlogDataNum(blogThirdDataVO, "readNum");
					break;
				}
				// 修改博客点赞量
				case BlogConstants.BLOG_THIRD_BLOG_LIKE:{
					updateBlogDataNum(blogThirdDataVO, "likeNum");
					break;
				}
				// 修改博客收藏量
				case BlogConstants.BLOG_THIRD_BLOG_COLLECT:{
					updateBlogDataNum(blogThirdDataVO, "collectNum");
					break;
				}
				// 修改博客转发量
				case BlogConstants.BLOG_THIRD_BLOG_FORWARD:{
					updateBlogDataNum(blogThirdDataVO, "forwardNum");
					break;
				}
				// 修改博客评论量
				case BlogConstants.BLOG_THIRD_BLOG_COMMENT:{
					updateBlogDataNum(blogThirdDataVO, "commentNum");
					break;
				}
				// 修改博客分享量
				case BlogConstants.BLOG_THIRD_BLOG_SHARE:{
					updateBlogDataNum(blogThirdDataVO, "shareNum");
					break;
				}
				// 修改博客附件下载量
				case BlogConstants.BLOG_THIRD_BLOG_ATTACH_DOWN:{
					updateBlogAttachDataNum(blogThirdDataVO);
					break;
				}
				// 修改分类下博客量
				case BlogConstants.BLOG_THIRD_CLASS_BLOG:{
					updateBlogClassDataNum(blogThirdDataVO);
					break;
				}
				// 修改评论回复量 
				case BlogConstants.BLOG_THIRD_COMMENT_REPLY:{
					updateCommentDataNum(blogThirdDataVO, "commentReplyNum");
					break;
				}
				// 修改评论点赞量
				case BlogConstants.BLOG_THIRD_COMMENT_LIKE:{
					updateCommentDataNum(blogThirdDataVO, "commentLikeNum");
					break;
				}
				// 修改回复点赞
				case BlogConstants.BLOG_THIRD_REPLY_LIKE:{
					updateReplyDataNum(blogThirdDataVO);
					break;
				}
				// 默认
				default:{
					log.error("BlogThirdDataServiceImpl.invokeService:kafka修改数量操作失败updateClass="+updateClass);
				}
			}
		} catch(Exception e) {
			throw new Exception("BlogThirdDataServiceImpl.invokeService:修改数量操作失败",e);
		}
	}
	
	/**
	 * 
	 * @Title: updateBlogDataNum  
	 * @Description: 修改博客数据表数据
	 * @param blogThirdDataVO
	 * @return: boolean    返回类型
	 */
	public boolean updateBlogDataNum(BlogThirdDataVO blogThirdDataVO, String updateFiled) {
		// 拼接参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("blogCode", blogThirdDataVO.getSubjectCode()); // 博客编号
		paramMap.put("updateField", updateFiled); // 修改字段
		paramMap.put("updateNumber", blogThirdDataVO.getUpdateNumber()); // 修改量
		paramMap.put("modifyTime", blogThirdDataVO.getModifyTime()); // 修改时间
		
		// 修改
		int updateNum = blogDataMapper.update(paramMap);
		if (updateNum != 1) {
			log.error("BlogThirdDataServiceImpl.updateBlogQualityTeamNum：修改" + updateNum + "条博客数据信息 ");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Title: updateBlogAttachDataNum  
	 * @Description: 修改博客附件表数据
	 * @param blogThirdDataVO    参数  
	 * @return: boolean    返回类型
	 */
	private boolean updateBlogAttachDataNum(BlogThirdDataVO blogThirdDataVO) {
		// 拼接参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("attachCode", blogThirdDataVO.getSubjectCode()); // 附件编号
		paramMap.put("updateNumber", blogThirdDataVO.getUpdateNumber()); // 修改量
		paramMap.put("modifyTime", blogThirdDataVO.getModifyTime()); // 修改时间
		
		// 修改
		int updateNum = blogAttachMapper.updateData(paramMap);
		if (updateNum != 1) {
			log.error("BlogThirdDataServiceImpl.updateBlogQualityTeamNum：修改" + updateNum + "条博客附件数据信息 ");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Title: updateBlogClassDataNum  
	 * @Description: 修改博客分类表数据
	 * @param blogThirdDataVO    参数  
	 * @return: boolean    返回类型
	 */
	private boolean updateBlogClassDataNum(BlogThirdDataVO blogThirdDataVO) {
		// 拼接参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("classCode", blogThirdDataVO.getSubjectCode()); // 分类编号
		paramMap.put("updateNumber", blogThirdDataVO.getUpdateNumber()); // 修改量
		paramMap.put("modifyTime", blogThirdDataVO.getModifyTime()); // 修改时间
		
		// 修改
		int updateNum = blogClassMapper.updateData(paramMap);
		if (updateNum != 1) {
			log.error("BlogThirdDataServiceImpl.updateBlogQualityTeamNum：修改" + updateNum + "条博客分类数据信息 ");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Title: updateCommentDataNum  
	 * @Description: 修改评论表数据
	 * @param blogThirdDataVO
	 * @param string    参数  
	 * @return: boolean    返回类型
	 */
	private boolean updateCommentDataNum(BlogThirdDataVO blogThirdDataVO, String updateFiled) {
		// 拼接参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("commentCode", blogThirdDataVO.getSubjectCode()); // 评论编号
		paramMap.put("updateField", updateFiled); // 修改字段
		paramMap.put("updateNumber", blogThirdDataVO.getUpdateNumber()); // 修改量
		paramMap.put("modifyTime", blogThirdDataVO.getModifyTime()); // 修改时间
		
		// 修改
		int updateNum = blogCommentMapper.updateData(paramMap);
		if (updateNum != 1) {
			log.error("BlogThirdDataServiceImpl.updateBlogQualityTeamNum：修改" + updateNum + "条博客评论数据信息 ");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Title: updateReplyDataNum  
	 * @Description: 修改回复表数据
	 * @param blogThirdDataVO    参数  
	 * @return: boolean    返回类型
	 */
	private boolean updateReplyDataNum(BlogThirdDataVO blogThirdDataVO) {
		// 拼接参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("replyCode", blogThirdDataVO.getSubjectCode()); // 回复编号
		paramMap.put("updateNumber", blogThirdDataVO.getUpdateNumber()); // 修改量
		paramMap.put("modifyTime", blogThirdDataVO.getModifyTime()); // 修改时间
		
		// 修改
		int updateNum = blogReplyMapper.updateData(paramMap);
		if (updateNum != 1) {
			log.error("BlogThirdDataServiceImpl.updateBlogQualityTeamNum：修改" + updateNum + "条博客回复数据信息 ");
			return false;
		}
		
		return true;
	}

}
