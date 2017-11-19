package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogDraftContentMapper;
import com.zssq.dao.mapper.BlogDraftMapper;
import com.zssq.dao.model.BlogDraftModel;
import com.zssq.dao.pojo.BlogDraft;
import com.zssq.dao.pojo.BlogDraftContent;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogDraftService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.BlogDraftVO;

/**
 * 
 * @ClassName: BlogDraftServiceImpl  
 * @Description: 博客草稿  
 * @author ZKZ  
 * @date 2017年3月22日  
 *
 */
@Service("blogDraftService")
public class BlogDraftServiceImpl implements BlogDraftService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogDraftMapper blogDraftMapper;
	@Autowired
	private BlogDraftContentMapper blogDraftContentMapper;
//	@Autowired
//	private BlogDraftAttachMapper blogDraftAttachMapper;

	/**
	 * 获取草稿列表
	 */
	@Override
	public PageBean getDraftList(PageParam pageParam, BlogDraftVO blogDraftVO) throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null) {
				log.error("BlogDraftServiceImpl.getDraftList：参数为空");
				throw BusinessException.build("COMMON_402", "pageParam");
			}
			if (blogDraftVO == null) {
				log.error("BlogDraftServiceImpl.getDraftList：参数为空");
				throw BusinessException.build("COMMON_402", "blogDraftVO");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = blogDraftVO.getQueryTime(); // 首次查询时间
			String userCode = blogDraftVO.getUserCode(); // 人员编号
			String teamCode = blogDraftVO.getTeamCode(); // 班组编号
			Byte draftDepend = blogDraftVO.getDraftDepend(); // 从属关系
			
			// 参数校验
			if (pageSize == null) {
				log.error("BlogDraftServiceImpl.getDraftList：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (pageNo == null) {
				log.error("BlogDraftServiceImpl.getDraftList：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (queryTime == null || queryTime == 0) {
				log.error("BlogDraftServiceImpl.getDraftList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogDraftServiceImpl.getDraftList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (draftDepend == null || (!BlogConstants.BLOG_DRAFT_DEPEND_USER.equals(draftDepend)
					&& !BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend))) {
				log.error("BlogDraftServiceImpl.getDraftList：草稿从属关系错误");
				throw BusinessException.build("COMMON_402", "draftDepend");
			} else if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogDraftServiceImpl.getDraftList：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("draftDepend", draftDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			
			// 查询列表总数
			int count = blogDraftMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<BlogDraft> blogDraftList = blogDraftMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogDraftList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 获取草稿详情
	 */
	@Override
	public BlogDraftModel getDraftInfo(BlogDraftVO blogDraftVO) throws BusinessException {
		// 返回值
		BlogDraftModel blogDraftModel = null;
		
		try {
			// 参数校验
			if (blogDraftVO == null) {
				log.error("BlogDraftServiceImpl.getDraftInfo：参数为空");
				throw BusinessException.build("COMMON_402", "blogDraftVO");
			}
			
			// 获取参数
			String draftCode = blogDraftVO.getDraftCode(); // 草稿编号
			String userCode = blogDraftVO.getUserCode(); // 人员编号
			String teamCode = blogDraftVO.getTeamCode(); // 班组编号
			Byte draftDepend = blogDraftVO.getDraftDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(draftCode)) {
				log.error("BlogDraftServiceImpl.getDraftInfo：draftCode为空");
				throw BusinessException.build("COMMON_402", "draftCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogDraftServiceImpl.getDraftInfo：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (draftDepend == null || (!BlogConstants.BLOG_DRAFT_DEPEND_USER.equals(draftDepend)
					&& !BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend))) {
				log.error("BlogDraftServiceImpl.getDraftInfo：草稿从属关系错误");
				throw BusinessException.build("COMMON_402", "draftDepend");
			} else if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogDraftServiceImpl.getDraftInfo：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("draftCode", draftCode); // 草稿编号
			paramMap.put("draftDepend", draftDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			
			// 查询
			blogDraftModel = blogDraftMapper.select(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogDraftModel;
	}

	/**
	 * 保存草稿信息
	 */
	@Override
	public boolean saveDraft(BlogDraft blogDraft, BlogDraftContent blogDraftContent) throws BusinessException {
		try {
			// 参数校验
			if (blogDraft == null) {
				log.error("BlogDraftServiceImpl.saveDraft：blogDraft为空");
				throw BusinessException.build("COMMON_402", "blogDraft");
			}
			if (blogDraftContent == null) {
				log.error("BlogDraftServiceImpl.saveDraft：blogDraftContent为空");
				throw BusinessException.build("COMMON_402", "blogDraftContent");
			}
			
			// 保存草稿信息
			int insertDraftNum = blogDraftMapper.insert(blogDraft);
			if (insertDraftNum != 1) {
				log.error("BlogDraftServiceImpl.saveDraft：插入" + insertDraftNum + "条博客草稿信息");
				return false;
			}
			
			// 保存草稿正文信息
			int insertDraftContentNum = blogDraftContentMapper.insert(blogDraftContent);
			if (insertDraftContentNum != 1) {
				log.error("BlogDraftServiceImpl.saveDraft：插入" + insertDraftContentNum + "条博客草稿正文信息");
				throw BusinessException.build("BLOG_13002", "保存");
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改草稿信息
	 */
	@Override
	public boolean updateDraft(BlogDraftVO blogDraftVO) throws BusinessException {
		try {
			// 参数校验
			if (blogDraftVO == null) {
				log.error("BlogDraftServiceImpl.updateDraft：参数为空");
				throw BusinessException.build("COMMON_402", "blogDraftVO");
			}
			
			// 获取参数
			String draftCode = blogDraftVO.getDraftCode(); // 草稿编号
			String userCode = blogDraftVO.getUserCode(); // 人员编号
			String teamCode = blogDraftVO.getTeamCode(); // 班组编号
			Byte draftDepend = blogDraftVO.getDraftDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(draftCode)) {
				log.error("BlogDraftServiceImpl.updateDraft：draftCode为空");
				throw BusinessException.build("COMMON_402", "draftCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogDraftServiceImpl.updateDraft：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (draftDepend == null || (!BlogConstants.BLOG_DRAFT_DEPEND_USER.equals(draftDepend)
					&& !BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend))) {
				log.error("BlogDraftServiceImpl.updateDraft：草稿从属关系错误");
				throw BusinessException.build("COMMON_402", "draftDepend");
			} else if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogDraftServiceImpl.updateDraft：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 修改草稿信息
			Map<String, Object> draftMap = new HashMap<String, Object>();
			draftMap.put("draftCode", draftCode); // 草稿编号
			draftMap.put("draftTitle", blogDraftVO.getDraftTitle()); // 标题
			draftMap.put("classCode", blogDraftVO.getClassCode()); // 分类编号
			draftMap.put("draftTags", blogDraftVO.getDraftTags()); // 标签
			draftMap.put("draftPlanPublishTime", blogDraftVO.getDraftPlanPublishTime()); // 定时发布时间
			draftMap.put("draftDepend", draftDepend); // 从属关系
			draftMap.put("userCode", userCode); // 人员编号
			draftMap.put("teamCode", teamCode); // 班组编号
			draftMap.put("modifyTime", blogDraftVO.getModifyTime()); // 修改时间
			int updateDraftNum = blogDraftMapper.update(draftMap);
			if (updateDraftNum != 1) {
				log.error("BlogDraftServiceImpl.updateDraft：修改" + updateDraftNum + "条博客草稿信息");
				return false;
			}
			
			// 修改草稿正文信息
			Map<String, Object> draftContentMap = new HashMap<String, Object>();
			draftContentMap.put("draftCode", draftCode);
			draftContentMap.put("draftContentInfo", blogDraftVO.getDraftContentInfo());
			int updateDraftContentNum = blogDraftContentMapper.update(draftContentMap);
			if (updateDraftContentNum != 1) {
				log.error("BlogDraftServiceImpl.updateDraft：修改" + updateDraftContentNum + "条博客草稿正文信息");
				throw BusinessException.build("BLOG_13002", "保存");
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除草稿
	 */
	@Override
	public boolean deleteDraft(BlogDraftVO blogDraftVO) throws BusinessException {
		try {
			// 参数校验
			if (blogDraftVO == null) {
				log.error("BlogDraftServiceImpl.deleteDraft：参数为空");
				throw BusinessException.build("COMMON_402", "blogDraftVO");
			}
			
			// 获取参数
			String draftCode = blogDraftVO.getDraftCode(); // 草稿编号
			String userCode = blogDraftVO.getUserCode(); // 人员编号
			String teamCode = blogDraftVO.getTeamCode(); // 班组编号
			Byte draftDepend = blogDraftVO.getDraftDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(draftCode)) {
				log.error("BlogDraftServiceImpl.deleteDraft：draftCode为空");
				throw BusinessException.build("COMMON_402", "draftCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogDraftServiceImpl.getDraftList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (draftDepend == null || (!BlogConstants.BLOG_DRAFT_DEPEND_USER.equals(draftDepend)
					&& !BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend))) {
				log.error("BlogDraftServiceImpl.getDraftList：草稿从属关系错误");
				throw BusinessException.build("COMMON_402", "draftDepend");
			} else if (BlogConstants.BLOG_DRAFT_DEPEND_TEAM.equals(draftDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogDraftServiceImpl.getDraftList：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("draftCode", draftCode); // 草稿编号
			paramMap.put("draftDepend", draftDepend); // 从属关系
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("teamCode", teamCode); // 班组编号
			
			// 删除草稿
			int deleteDraftNum = blogDraftMapper.delete(paramMap);
			if (deleteDraftNum != 1) {
				log.error("BlogDraftServiceImpl.deleteDraft：删除" + deleteDraftNum + "条博客草稿信息");
				return false;
			}
			
			// 删除草稿正文
			blogDraftContentMapper.delete(draftCode);
			
			// 删除草稿附件
//			blogDraftAttachMapper.delete(draftCode);
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
