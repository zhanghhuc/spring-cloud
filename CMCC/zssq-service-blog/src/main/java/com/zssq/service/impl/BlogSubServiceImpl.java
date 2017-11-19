package com.zssq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.BlogConstants;
import com.zssq.dao.mapper.BlogSubscribeMapper;
import com.zssq.dao.pojo.BlogSubscribe;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.BlogSubService;
import com.zssq.vo.BlogSubVO;

/**
 * 
 * @ClassName: BlogSubServiceImpl  
 * @Description: 订阅  
 * @author ZKZ  
 * @date 2017年3月27日  
 *
 */
@Service("blogSubService")
public class BlogSubServiceImpl implements BlogSubService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogSubscribeMapper blogSubscribeMapper;

	/**
	 * 查询订阅状态
	 */
	@Override
	public boolean getSubStatus(BlogSubVO blogSubVO) throws BusinessException {
		try {
			// 参数校验
			if (blogSubVO == null) {
				log.error("BlogSubServiceImpl.getSubStatus：参数为空");
				throw BusinessException.build("COMMON_402", "blogSubVO");
			}
			
			// 获取参数
			String userCode = blogSubVO.getUserCode(); // 人员编号
			Byte subClass = blogSubVO.getSubClass(); // 订阅类型
			String teamCode = blogSubVO.getTeamCode(); // 被订阅班组编号
			String subUserCode = blogSubVO.getSubUserCode(); // 被订阅人员编号
			
			// 参数校验
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogSubServiceImpl.getSubStatus：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (subClass == null || subClass == 0) {
				log.error("BlogSubServiceImpl.getSubStatus：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			}
			if (BlogConstants.BLOG_SUB_USER.equals(subClass) && StringUtils.isBlank(subUserCode)) {
				log.error("BlogSubServiceImpl.getSubStatus：subUserCode错误");
				throw BusinessException.build("COMMON_402", "subUserCode");
			} else if (BlogConstants.BLOG_SUB_TEAM.equals(subClass) && StringUtils.isBlank(teamCode)) {
				log.error("BlogSubServiceImpl.getSubStatus：teamCode错误");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("subClass", subClass); // 订阅类型
			paramMap.put("teamCode", teamCode); // 被订阅班组编号
			paramMap.put("subUserCode", subUserCode); // 被订阅人员编号
			
			// 查询是否已经订阅过
			int count = blogSubscribeMapper.selectCount(paramMap);
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
	}
	
	@Override
	public boolean saveSubscribe(BlogSubscribe blogSubscribe) throws BusinessException {
		try {
			// 参数校验
			if (blogSubscribe == null) {
				log.error("BlogSubServiceImpl.saveSubscribe：参数为空");
				throw BusinessException.build("COMMON_402", "blogSubscribe");
			}
			
			// 获取参数
			String userCode = blogSubscribe.getUserCode(); // 人员编号
			Byte subClass = blogSubscribe.getSubClass(); // 订阅类型
			String teamCode = blogSubscribe.getTeamCode(); // 被订阅班组编号
			String subUserCode = blogSubscribe.getSubUserCode(); // 被订阅人员编号
			
			// 参数校验
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogSubServiceImpl.saveSubscribe：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (subClass == null || subClass == 0) {
				log.error("BlogSubServiceImpl.saveSubscribe：subClass错误");
				throw BusinessException.build("COMMON_402", "subClass");
			}
			if (BlogConstants.BLOG_SUB_USER.equals(subClass) && StringUtils.isBlank(subUserCode)) {
				log.error("BlogSubServiceImpl.saveSubscribe：subUserCode错误");
				throw BusinessException.build("COMMON_402", "subUserCode");
			} else if (BlogConstants.BLOG_SUB_TEAM.equals(subClass) && StringUtils.isBlank(teamCode)) {
				log.error("BlogSubServiceImpl.saveSubscribe：teamCode错误");
				throw BusinessException.build("COMMON_402", "teamCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("subClass", subClass); // 订阅类型
			paramMap.put("teamCode", teamCode); // 被订阅班组编号
			paramMap.put("subUserCode", subUserCode); // 被订阅人员编号
			
			// 查询是否已经订阅过
			int count = blogSubscribeMapper.selectCount(paramMap);
			if (count > 0) {
				log.error("BlogSubServiceImpl.saveSubscribe：重复订阅");
				throw BusinessException.build("BLOG_13011");
			}
			
			// 插入
			int insertNum = blogSubscribeMapper.insert(blogSubscribe);
			if (insertNum != 1) {
				log.error("BlogSubServiceImpl.saveSubscribe：插入" + insertNum + "条订阅信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

	/**
	 * 取消订阅
	 */
	@Override
	public boolean deleteSubscribe(BlogSubVO blogSubVO) throws BusinessException {
		try {
			// 参数校验
			if (blogSubVO == null) {
				log.error("BlogSubServiceImpl.deleteSubscribe：参数为空");
				throw BusinessException.build("COMMON_402", "blogSubVO");
			}
			
			// 获取参数
			String userCode = blogSubVO.getUserCode(); // 订阅人编号
			Byte subClass = blogSubVO.getSubClass(); // 被订阅类型
			String teamCode = blogSubVO.getTeamCode(); // 被订阅班组编号
			String subUserCode = blogSubVO.getSubUserCode(); // 被订阅人编号
			
			// 参数校验
			if (StringUtils.isBlank(userCode)) {
				log.error("BlogSubServiceImpl.deleteSubscribe：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userCode", userCode); // 订阅人编号
			paramMap.put("subClass", subClass); // 被订阅类型
			
			if (BlogConstants.BLOG_SUB_USER.equals(subClass) && !StringUtils.isBlank(subUserCode)) {
				paramMap.put("subUserCode", subUserCode); // 被订阅人编号
			} else if (BlogConstants.BLOG_SUB_TEAM.equals(subClass) && !StringUtils.isBlank(teamCode)) {
				paramMap.put("teamCode", teamCode); // 被订阅班组编号
			} else {
				log.error("BlogSubServiceImpl.deleteSubscribe：被订阅关系错误");
				throw BusinessException.build("COMMON_402", "subClass");
			}
			
			// 删除
			int deleteNum = blogSubscribeMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("BlogSubServiceImpl.deleteSubscribe：删除" + deleteNum + "条订阅信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
