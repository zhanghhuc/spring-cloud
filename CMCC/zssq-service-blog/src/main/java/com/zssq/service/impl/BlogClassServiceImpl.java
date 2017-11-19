package com.zssq.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogClassMapper;
import com.zssq.dao.pojo.BlogClass;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogClassService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.vo.BlogClassVO;

/**
 * 
 * @ClassName: BlogClassServiceImpl  
 * @Description: 博客分类  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
@Service("blogClassService")
public class BlogClassServiceImpl implements BlogClassService {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BlogClassMapper blogClassMapper;
	
	/**
	 * 查询分类列表
	 */
	@Override
	public List<BlogClass> getBlogClassList(BlogClassVO blogClassVO) throws BusinessException {
		// 返回值
		List<BlogClass> blogClassList = null;
		
		try {
			// 参数校验
			if (blogClassVO == null) {
				log.error("BlogClassServiceImpl.getBlogClassList：参数为空");
				throw BusinessException.build("COMMON_402", "blogClassVO");
			}
			
			// 获取参数
			String userCode = blogClassVO.getUserCode(); // 用户编号
			String teamCode = blogClassVO.getTeamCode(); // 班组编号
			Byte classDepend = blogClassVO.getClassDepend(); // 从属关系
			
			// 参数校验
			if (classDepend == null || (!BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend)
					&& !BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend))) {
				log.error("BlogClassServiceImpl.getBlogClassList：分类从属关系错误");
				throw BusinessException.build("COMMON_402", "classDepend");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend) && StringUtils.isBlank(userCode)) {
				log.error("BlogClassServiceImpl.getBlogClassList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogClassServiceImpl.getBlogClassList：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode);
			paramMap.put("teamCode", teamCode);
			paramMap.put("classDepend", classDepend);
			
			// 查询
			blogClassList = blogClassMapper.listAll(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		return blogClassList;
	}
	
	/**
	 * 查询分类个数
	 */
	@Override
	public int getClassNum(BlogClassVO blogClassVO) throws BusinessException {
		// 返回值
		int classNum = 0;
		
		try {
			// 参数校验
			if (blogClassVO == null) {
				log.error("BlogClassServiceImpl.getClassNum：参数为空");
				throw BusinessException.build("COMMON_402", "blogClassVO");
			}
			
			// 获取参数
			String userCode = blogClassVO.getUserCode(); // 用户编号
			String teamCode = blogClassVO.getTeamCode(); // 班组编号
			Byte classDepend = blogClassVO.getClassDepend(); // 从属关系
			
			// 参数校验
			if (classDepend == null || (!BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend)
					&& !BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend))) {
				log.error("BlogClassServiceImpl.getClassNum：分类从属关系错误");
				throw BusinessException.build("COMMON_402", "classDepend");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend) && StringUtils.isBlank(userCode)) {
				log.error("BlogClassServiceImpl.getClassNum：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogClassServiceImpl.getClassNum：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode);
			paramMap.put("teamCode", teamCode);
			paramMap.put("classDepend", classDepend);
			
			// 查询
			classNum = blogClassMapper.getClassNum(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		
		
		return classNum;
	}

	/**
	 * 保存分类信息
	 */
	@Override
	public boolean saveBlogClass(BlogClass blogClass) throws BusinessException {
		try {
			// 参数校验
			if (blogClass == null) {
				log.error("BlogClassServiceImpl.saveBlogClass：blogClass为空");
				throw BusinessException.build("COMMON_402", "blogClass");
			}
			
			// 插入
			int insertNum = blogClassMapper.insert(blogClass);
			if (insertNum != 1) {
				log.error("BlogClassServiceImpl.saveBlogClass：插入" + insertNum + "条分类信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改分类下博客数量
	 */
	@Override
	public boolean updateClassBlogNum(BlogClassVO blogClassVO) throws BusinessException {
		try {
			// 参数校验
			if (blogClassVO == null) {
				log.error("BlogClassServiceImpl.updateClassBlogNum：blogClassVO为空");
				throw BusinessException.build("COMMON_402", "blogClassVO");
			}
			
			// 获取参数
			String classCode = blogClassVO.getClassCode(); // 分类编号
			Integer classBlogNum = blogClassVO.getClassBlogNum(); // 分类下博客数量 
			Long modifyTime = blogClassVO.getModifyTime(); // 修改时间
			
			// 参数校验
			if (StringUtils.isBlank(classCode)) {
				log.error("BlogClassServiceImpl.updateClassBlogNum：classCode为空");
				throw BusinessException.build("COMMON_402", "classCode");
			}
			if (classBlogNum == null) {
				log.error("BlogClassServiceImpl.updateClassBlogNum：classBlogNum为空");
				throw BusinessException.build("COMMON_402", "classBlogNum");
			}
			if (modifyTime == null || modifyTime == 0) {
				log.error("BlogClassServiceImpl.updateClassBlogNum：modifyTime为空");
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("classCode", classCode);
			paramMap.put("classBlogNum", classBlogNum);
			paramMap.put("modifyTime", modifyTime);
			
			// 插入
			int updateNum = blogClassMapper.updateBlogNum(paramMap);
			if (updateNum != 1) {
				log.error("BlogClassServiceImpl.updateClassBlogNum：修改" + updateNum + "条分类信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 删除分类
	 */
	@Override
	public boolean deleteBlogClass(BlogClassVO blogClassVO) throws BusinessException {
		try {
			// 参数校验
			if (blogClassVO == null) {
				log.error("BlogClassServiceImpl.deleteBlogClass：参数为空");
				throw BusinessException.build("COMMON_402", "blogClassVO");
			}
			
			// 获取参数
			String classCode = blogClassVO.getClassCode(); // 分类编号
			String userCode = blogClassVO.getUserCode(); // 用户编号
			String teamCode = blogClassVO.getTeamCode(); // 班组编号
			Byte classDepend = blogClassVO.getClassDepend(); // 从属关系
			
			// 参数校验
			if (StringUtils.isBlank(classCode)) {
				log.error("BlogClassServiceImpl.deleteBlogClass：classCode为空");
				throw BusinessException.build("COMMON_402", "classCode");
			}
			if (classDepend == null || (!BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend)
					&& !BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend))) {
				log.error("BlogClassServiceImpl.deleteBlogClass：分类从属关系错误");
				throw BusinessException.build("COMMON_402", "classDepend");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend) && StringUtils.isBlank(userCode)) {
				log.error("BlogClassServiceImpl.deleteBlogClass：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogClassServiceImpl.deleteBlogClass：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("classCode", classCode);
			paramMap.put("userCode", userCode);
			paramMap.put("teamCode", teamCode);
			paramMap.put("classDepend", classDepend);
			
			// 查询分类是否被占用
			int blogNum = blogClassMapper.getBlogNum(paramMap);
			if (blogNum > 0) {
				log.error("BlogClassServiceImpl.deleteBlogClass：分类被占用，删除失败");
				throw BusinessException.build("BLOG_13003");
			}
			
			// 删除
			int deleteNum = blogClassMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogClassServiceImpl.deleteBlogClass：删除" + deleteNum + "条分类信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 查询默认分类编号
	 */
	@Override
	public String getDefaultClassCode(BlogClassVO blogClassVO) throws BusinessException {
		// 返回值
		String defaultClassCode = null;
		
		try {
			// 参数校验
			if (blogClassVO == null) {
				log.error("BlogClassServiceImpl.getDefaultClassCode：参数为空");
				throw BusinessException.build("COMMON_402", "blogClassVO");
			}
			
			// 获取参数
			String userCode = blogClassVO.getUserCode(); // 用户编号
			String teamCode = blogClassVO.getTeamCode(); // 班组编号
			Byte classDepend = blogClassVO.getClassDepend(); // 从属关系
			
			// 参数校验
			if (classDepend == null || (!BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend)
					&& !BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend))) {
				log.error("BlogClassServiceImpl.getDefaultClassCode：分类从属关系错误");
				throw BusinessException.build("COMMON_402", "classDepend");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_USER.equals(classDepend) && StringUtils.isBlank(userCode)) {
				log.error("BlogClassServiceImpl.getDefaultClassCode：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			} else if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(classDepend) && StringUtils.isBlank(teamCode)) {
				log.error("BlogClassServiceImpl.getDefaultClassCode：teamCode为空");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode);
			paramMap.put("teamCode", teamCode);
			paramMap.put("classDepend", classDepend);
			
			// 查询默认分类编号
			defaultClassCode = blogClassMapper.getDefaultClassCode(paramMap);
			
			if(StringUtils.isBlank(defaultClassCode)){
				// 分类信息
				BlogClass blogClass = new BlogClass();
				defaultClassCode = UUIDHelper.getUUID();
				long time = new Date().getTime();
				
				blogClass.setClassCode(defaultClassCode); // 分类编号
				blogClass.setTenantCode(blogClassVO.getTenantCode()); // 租户编号
				blogClass.setOrgCode(blogClassVO.getOrgCode()); // 组织机构编号
				blogClass.setCreateTime(time); // 创建时间
				blogClass.setModifyTime(time); // 修改时间
				blogClass.setRemark(BlogConstants.BLOG_CLASS_DEFAULT_FLAG);
				blogClass.setClassName(BlogConstants.BLOG_CLASS_DEFAULT); // 分类名称
				blogClass.setUserCode(userCode); // 创建人编号
				blogClass.setClassDepend(classDepend); // 从属关系
				blogClass.setTeamCode(teamCode == null ? "" : teamCode); // 班组编号
				blogClass.setClassBlogNum(0); // 博文数量
				
				// 插入
				int insertNum = blogClassMapper.insert(blogClass);
				if (insertNum != 1) {
					log.error("BlogClassServiceImpl.getClassCode：插入" + insertNum + "条分类信息 ");
				}
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return defaultClassCode;
	}

}
