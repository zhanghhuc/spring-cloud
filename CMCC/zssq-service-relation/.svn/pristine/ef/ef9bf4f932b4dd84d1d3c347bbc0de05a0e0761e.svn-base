package com.zssq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.RelationCollectMapper;
import com.zssq.dao.mapper.RelationLikeMapper;
import com.zssq.dao.mapper.RelationShareMapper;
import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RelationShare;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.vo.RelationOperateVO;

/**
 * 
 * @ClassName: RelationThirdOperateServiceImpl  
 * @Description: 操作  
 * @author ZKZ  
 * @date 2017年3月18日  
 *
 */
@Service("relationThirdOperateService")
public class RelationThirdOperateServiceImpl implements RelationThirdOperateService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationLikeMapper relationLikeMapper;
	
	@Autowired
	private RelationCollectMapper relationCollectMapper;
	
	@Autowired
	private RelationShareMapper relationShareMapper;
	
	/**
	 * 点赞
	 */
	@Override
	public boolean saveLike(RelationLike relationLike) throws BusinessException {
		try {
			// 参数校验
			if (relationLike == null) {
				log.error("RelationThirdOperateServiceImpl.saveLike：relationLike为空");
				throw BusinessException.build("COMMON_402", "relationLike");
			}
			
			// 插入
			int insertNum = relationLikeMapper.insert(relationLike);
			if (insertNum != 1) {
				
				log.error("RelationThirdOperateServiceImpl.saveLike：插入" + insertNum + "条点赞信息 ");
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
	public boolean deleteLike(RelationOperateVO relationOperateVO) throws BusinessException {
		try {
			// 参数校验
			if (relationOperateVO == null) {
				log.error("RelationThirdOperateServiceImpl.deleteLike：relationOperateVO为空");
				throw BusinessException.build("COMMON_402", "relationOperateVO");
			}
			String subjectCode = relationOperateVO.getSubjectCode();
			String userCode = relationOperateVO.getUserCode();
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdOperateServiceImpl.deleteLike：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationThirdOperateServiceImpl.deleteLike：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("userCode", userCode); // 人员编号
			int deleteNum = relationLikeMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("RelationThirdOperateServiceImpl.deleteLike：取消" + deleteNum + "条点赞信息 ");
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
	public boolean saveCollect(RelationCollect relationCollect) throws BusinessException {
		try {
			// 参数校验
			if (relationCollect == null) {
				log.error("RelationThirdOperateServiceImpl.saveCollect：relationLike为空");
				throw BusinessException.build("COMMON_402", "relationCollect");
			}
			
			// 插入
			int insertNum = relationCollectMapper.insert(relationCollect);
			if (insertNum != 1) {
				log.error("RelationThirdOperateServiceImpl.saveCollect：插入" + insertNum + "条收藏信息 ");
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
	public boolean deleteCollect(RelationOperateVO relationOperateVO) throws BusinessException {
		try {
			// 参数校验
			if (relationOperateVO == null) {
				log.error("RelationThirdOperateServiceImpl.deleteCollect：relationOperateVO为空");
				throw BusinessException.build("COMMON_402", "relationOperateVO");
			}
			String subjectCode = relationOperateVO.getSubjectCode();
			String userCode = relationOperateVO.getUserCode();
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdOperateServiceImpl.deleteCollect：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationThirdOperateServiceImpl.deleteCollect：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("userCode", userCode); // 人员编号
			int deleteNum = relationCollectMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("RelationThirdOperateServiceImpl.deleteCollect：取消" + deleteNum + "条点赞信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return true;
	}
	
	/**
	 * 分享
	 */
	@Override
	public boolean saveShare(RelationShare relationShare) throws BusinessException {
		try {
			// 参数校验
			if (relationShare == null) {
				log.error("RelationThirdOperateServiceImpl.saveShare：relationShare为空");
				throw BusinessException.build("COMMON_402", "relationShare");
			}
			
			// 插入
			int insertNum = relationShareMapper.insert(relationShare);
			if (insertNum != 1) {
				log.error("RelationThirdOperateServiceImpl.saveShare：插入" + insertNum + "条分享信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return true;
	}

}
