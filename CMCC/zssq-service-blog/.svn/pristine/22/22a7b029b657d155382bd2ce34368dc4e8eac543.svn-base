package com.zssq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogCollectMapper;
import com.zssq.dao.mapper.BlogForwardMapper;
import com.zssq.dao.mapper.BlogLikeMapper;
import com.zssq.dao.mapper.BlogReadMapper;
import com.zssq.dao.mapper.BlogShareMapper;
import com.zssq.dao.pojo.BlogCollect;
import com.zssq.dao.pojo.BlogForward;
import com.zssq.dao.pojo.BlogLike;
import com.zssq.dao.pojo.BlogRead;
import com.zssq.dao.pojo.BlogShare;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogOperateService;
import com.zssq.vo.BlogOperateVO;

/**
 * 
 * @ClassName: BlogOperateServiceImpl  
 * @Description: 博客操作  
 * @author ZKZ  
 * @date 2017年3月31日  
 *
 */
@Service("blogOperateService")
public class BlogOperateServiceImpl implements BlogOperateService {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogCollectMapper blogCollectMapper;
	@Autowired
	private BlogLikeMapper blogLikeMapper;
	@Autowired
	private BlogShareMapper blogShareMapper;
	@Autowired
	private BlogForwardMapper blogForwardMapper;
	@Autowired
	private BlogReadMapper blogReadMapper;
	
	/**
	 * 点赞
	 */
	@Override
	public boolean saveLike(BlogLike blogLike) throws BusinessException {
		try {
			// 参数校验
			if (blogLike == null) {
				log.error("BlogOperateServiceImpl.saveLike：参数为空");
				throw BusinessException.build("COMMON_402", "blogLike");
			}
			
			// 获取参数
			Byte subjectClass = blogLike.getSubjectClass(); // 对象类型
			String subjectCode = blogLike.getSubjectCode(); // 操作对象编号
			String userCode = blogLike.getUserCode(); // 人员编号
			
			// 参数校验
			if (subjectClass == null || (!BlogConstants.BLOG_LIKE_SUBJECT_BLOG.equals(subjectClass) && !BlogConstants.BLOG_LIKE_SUBJECT_COMMENT.equals(subjectClass) &&!BlogConstants.BLOG_LIKE_SUBJECT_REPLY.equals(subjectClass))) {
				log.error("BlogOperateServiceImpl.saveLike：subjectClass错误");
				throw BusinessException.build("COMMON_402", "subjectClass");
			}
			if (StringUtils.isBlank(subjectCode)) {
				log.error("BlogOperateServiceImpl.saveLike：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogOperateServiceImpl.saveLike：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectClass", subjectClass); // 对象类型
			paramMap.put("subjectCode", subjectCode); // 操作对象编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询是否已经点赞过
			int count = blogLikeMapper.selectCount(paramMap);
			if (count > 0) {
				log.error("BlogOperateServiceImpl.saveLike：重复点赞");
				throw BusinessException.build("BLOG_13004");
			}
			
			// 插入
			int insertNum = blogLikeMapper.insert(blogLike);
			if (insertNum != 1) {
				log.error("BlogOperateServiceImpl.saveLike：插入" + insertNum + "条点赞信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 取消点赞
	 */
	@Override
	public boolean deleteLike(BlogOperateVO blogOperateVO) throws BusinessException {
		try {
			// 参数校验
			if (blogOperateVO == null) {
				log.error("BlogOperateServiceImpl.deleteLike：参数为空");
				throw BusinessException.build("COMMON_402", "blogOperateVO");
			}
			
			// 获取参数
			Byte subjectClass = blogOperateVO.getSubjectClass(); // 对象类型
			String subjectCode = blogOperateVO.getSubjectCode(); // 操作对象编号
			String userCode = blogOperateVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (subjectClass == null || (!BlogConstants.BLOG_LIKE_SUBJECT_BLOG.equals(subjectClass) && !BlogConstants.BLOG_LIKE_SUBJECT_COMMENT.equals(subjectClass) &&!BlogConstants.BLOG_LIKE_SUBJECT_REPLY.equals(subjectClass))) {
				log.error("BlogOperateServiceImpl.deleteLike：subjectClass错误");
				throw BusinessException.build("COMMON_402", "subjectClass");
			}
			if (StringUtils.isBlank(subjectCode)) {
				log.error("BlogOperateServiceImpl.deleteLike：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogOperateServiceImpl.deleteLike：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectClass", subjectClass); // 对象类型
			paramMap.put("subjectCode", subjectCode); // 操作对象编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 删除评论
			int deleteNum = blogLikeMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogOperateServiceImpl.deleteLike：删除" + deleteNum + "条点赞信息");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 收藏
	 */
	@Override
	public boolean saveCollect(BlogCollect blogCollect) throws BusinessException {
		try {
			// 参数校验
			if (blogCollect == null) {
				log.error("BlogOperateServiceImpl.saveCollect：参数为空");
				throw BusinessException.build("COMMON_402", "blogCollect");
			}
			
			// 获取参数
			String blogCode = blogCollect.getBlogCode(); // 博客编号
			String userCode = blogCollect.getUserCode(); // 人员编号
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询是否已经收藏过
			int count = blogCollectMapper.selectCount(paramMap);
			if (count > 0) {
				log.error("BlogOperateServiceImpl.saveCollect：重复收藏");
				throw BusinessException.build("BLOG_13005");
			}
			
			// 插入
			int insertNum = blogCollectMapper.insert(blogCollect);
			if (insertNum != 1) {
				log.error("BlogOperateServiceImpl.saveCollect：插入" + insertNum + "条收藏信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 取消收藏
	 */
	@Override
	public boolean deleteCollect(BlogOperateVO blogOperateVO) throws BusinessException {
		try {
			// 参数校验
			if (blogOperateVO == null) {
				log.error("BlogOperateServiceImpl.deleteCollect：参数为空");
				throw BusinessException.build("COMMON_402", "blogOperateVO");
			}
			
			// 获取参数
			String blogCode = blogOperateVO.getBlogCode(); // 博客编号
			String userCode = blogOperateVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogOperateServiceImpl.deleteCollect：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogOperateServiceImpl.deleteCollect：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 删除
			int deleteNum = blogCollectMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogOperateServiceImpl.deleteCollect：删除" + deleteNum + "条收藏信息");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 保存分享信息
	 */
	@Override
	public boolean saveShare(BlogShare blogShare) throws BusinessException {
		try {
			// 参数校验
			if (blogShare == null) {
				log.error("BlogOperateServiceImpl.saveShare：参数为空");
				throw BusinessException.build("COMMON_402", "blogShare");
			}
			
			// 插入
			int insertNum = blogShareMapper.insert(blogShare);
			if (insertNum != 1) {
				log.error("BlogOperateServiceImpl.saveShare：插入" + insertNum + "条分享信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除分享信息
	 */
	@Override
	public boolean deleteShare(BlogOperateVO blogOperateVO) throws BusinessException {
		try {
			// 参数校验
			if (blogOperateVO == null) {
				log.error("BlogOperateServiceImpl.deleteShare：参数为空");
				throw BusinessException.build("COMMON_402", "blogOperateVO");
			}
			
			// 获取参数
			String blogCode = blogOperateVO.getBlogCode(); // 博客编号
			String userCode = blogOperateVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (StringUtils.isBlank(blogCode)) {
				log.error("BlogOperateServiceImpl.deleteShare：blogCode为空");
				throw BusinessException.build("COMMON_402", "blogCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogOperateServiceImpl.deleteShare：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("blogCode", blogCode); // 博客编号
			paramMap.put("userCode", userCode); // 人员编号
			
			// 删除
			int deleteNum = blogShareMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogOperateServiceImpl.deleteShare：删除" + deleteNum + "条收藏信息");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 保存转发信息
	 */
	@Override
	public boolean saveForward(BlogForward blogForward) throws BusinessException {
		try {
			// 参数校验
			if (blogForward == null) {
				log.error("BlogOperateServiceImpl.saveForward：参数为空");
				throw BusinessException.build("COMMON_402", "blogForward");
			}
			
			// 插入
			int insertNum = blogForwardMapper.insert(blogForward);
			if (insertNum != 1) {
				log.error("BlogOperateServiceImpl.saveForward：插入" + insertNum + "条转发信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 保存浏览信息
	 */
	@Override
	public boolean saveRead(BlogRead blogRead) throws BusinessException {
		try {
			// 参数校验
			if (blogRead == null) {
				log.error("BlogOperateServiceImpl.saveRead：参数为空");
				throw BusinessException.build("COMMON_402", "blogRead");
			}
			
			// 插入
			int insertNum = blogReadMapper.insert(blogRead);
			if (insertNum != 1) {
				log.error("BlogOperateServiceImpl.saveRead：插入" + insertNum + "条浏览信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
