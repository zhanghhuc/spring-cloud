package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.RelationConstants;
import com.zssq.dao.mapper.RelationDynamicMapper;
import com.zssq.dao.mapper.RelationShareMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationShareModel;
import com.zssq.service.RelationShareService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationShareVO;

/**
 * @ClassName RelationShareServiceImpl
 * @Description 分享
 * @author LXW
 * @date 2017年6月12日 下午3:41:11
 * @version 1.0
 * @since JDK 1.7
 */
@Service("relationShareService")
public class RelationShareServiceImpl implements RelationShareService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationShareMapper relationShareMapper;
	@Autowired
	private RelationDynamicMapper relationDynamicMapper;

	/**
	 *  获取用户分享列表
	 */
	@Override
	public PageBean getUserShareList(PageParam pageParam, RelationShareVO relationShareVO) throws BusinessException{
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null || relationShareVO == null) {
				log.error("RelationShareServiceImpl.getUserShareList：参数为空");
				throw BusinessException.build("COMMON_402","参数");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			Long queryTime = relationShareVO.getQueryTime(); // 首次查询时间
			String userCode = relationShareVO.getUserCode(); // 人员编号
			
			// 参数校验
			if (pageSize == null || pageNo == null) {
				log.error("RelationShareServiceImpl.getUserShareList：分页信息为空");
				throw BusinessException.build("COMMON_402", "分页信息");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationShareServiceImpl.getUserShareList：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (queryTime == null) {
				log.error("RelationShareServiceImpl.getUserShareList：queryTime为空");
				throw BusinessException.build("COMMON_402", "queryTime");
			}
				
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("queryTime", queryTime); // 首次查询时间
			paramMap.put("userCode", userCode); // 人员编号
			
			// 查询列表总数
			int count = relationShareMapper.selectUserShareCount(paramMap);

			// 查询列表内容
			List<RelationShareModel> shareList =relationShareMapper.selectUserShareList(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, shareList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}

	/**
	 * 删除分享
	 */
	@Override
	public boolean deleteShare(RelationShareVO relationShareVO) throws BusinessException {
		try {
			// 参数校验
			if (relationShareVO == null) {
				log.error("RelationShareServiceImpl.deleteShare：参数为空");
				throw BusinessException.build("COMMON_402","参数");
			}
			
			// 获取参数
			String shareCode = relationShareVO.getShareCode(); // 分项编号
			String userCode = relationShareVO.getUserCode(); // 人员编号
			String dynamicCode = relationShareVO.getDynamicCode(); // 动态编号
			Long modifyTime = relationShareVO.getModifyTime(); // 修改时间
			
			// 参数校验
			if (StringUtils.isBlank(shareCode)) {
				log.error("RelationShareServiceImpl.deleteShare：queryTime为空");
				throw BusinessException.build("COMMON_402","queryTime");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationShareServiceImpl.deleteShare：userCode为空");
				throw BusinessException.build("COMMON_402","userCode");
			}
			if (StringUtils.isBlank(dynamicCode)) {
				log.error("RelationShareServiceImpl.deleteShare：dynamicCode为空");
				throw BusinessException.build("COMMON_402","dynamicCode");
			}
			
			// 删除分享
			Map<String, Object> shareMap= new HashMap<String, Object>();
			shareMap.put("shareCode", shareCode);
			shareMap.put("userCode", userCode);
			int deleteShareNum = relationShareMapper.delete(shareMap);
			if (deleteShareNum != 1) {
				log.error("RelationShareServiceImpl.deleteShare：删除" + deleteShareNum + "条分享数据信息 ");
				return false;
			}
			
			// 删除动态
			Map<String, Object> dynamicMap= new HashMap<String, Object>();
			dynamicMap.put("dynamicCode", dynamicCode);
			dynamicMap.put("dynamicIsDelete", RelationConstants.RELATION_YES);
			dynamicMap.put("modifyTime", modifyTime);
			int deleteDynamicNum = relationDynamicMapper.delete(dynamicMap);
			if (deleteDynamicNum != 1) {
				log.error("RelationShareServiceImpl.deleteShare：删除" + deleteDynamicNum + "条动态数据信息 ");
				throw BusinessException.build("BLOG_13002", "删除");
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
