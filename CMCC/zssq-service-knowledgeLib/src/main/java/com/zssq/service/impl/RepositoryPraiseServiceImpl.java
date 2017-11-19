package com.zssq.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.dao.mapper.RepositoryKnowledgeAppendBySryMapper;
import com.zssq.dao.mapper.RepositoryKnowledgeBySryMapper;
import com.zssq.dao.mapper.RepositoryPraiseBySryMapper;
import com.zssq.dao.pojo.RepositoryPraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.RepositoryPraiseService;
import com.zssq.vo.RepositoryPraiseVo;

/**
 * 
 * @ClassName: RepositoryPraiseService  
 * @Description: 知识库点赞  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Service("repositoryPraiseService")
public class RepositoryPraiseServiceImpl implements RepositoryPraiseService{

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private RepositoryPraiseBySryMapper repositoryPraiseBySryMapper;
	@Autowired
	private RepositoryKnowledgeBySryMapper repositoryKnowledgeBySryMapper;
	@Autowired
	private RepositoryKnowledgeAppendBySryMapper repositoryKnowledgeAppendBySryMapper;
	/**
	 * 保存点赞 及点赞量
	 */
	@Override
	public boolean savePraise(RepositoryPraise infoVo) throws BusinessException{
		try {
			// 参数校验
			if (infoVo == null) {
				log.error("RepositoryPraiseServiceImpl.savePraise：参数为空");
				throw BusinessException.build("COMMON_402", "RepositoryPraise");
			}
			String userCode = infoVo.getUserCode();
			String orgCode = infoVo.getOrgCode();
			String objectCode = infoVo.getObjectCode();
			Byte praiseType = infoVo.getPraiseType();
			if (StringUtils.isBlank(objectCode)) {
				log.error("RepositoryPraiseServiceImpl.doPraise：objectCode为空");
				throw BusinessException.build("COMMON_402", "objectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RepositoryPraiseServiceImpl.doPraise：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryPraiseServiceImpl.doPraise：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("objectCode", objectCode); // 对象类型
			paramMap.put("orgCode", orgCode); // 操作对象编号
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("praiseType", praiseType); // 点赞类型
			// 查询是否已经收藏
			int count = repositoryPraiseBySryMapper.selectCount(paramMap);
			if (count > 0) {
				log.error("RepositoryPraiseServiceImpl.doPraise：重复点赞");
				throw BusinessException.build("KNOWLEDGELIB_27003");
			}
			if(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KGAPPEND.equals(praiseType)){
				Map<String, Object> kgAppendMap = new HashMap<String, Object>();
				kgAppendMap.put("praiseNum", KnowledgeLibConstants.NUM_ONE);
				kgAppendMap.put("modifyTime", new Date().getTime());
				kgAppendMap.put("appendCode", objectCode);
				int updateNum = repositoryKnowledgeAppendBySryMapper.updateNumTime(kgAppendMap);
				if (updateNum != 1) {
					log.error("RepositoryPraiseServiceImpl.deletePraise：修改" + updateNum + "条点赞量记录 ");
					return false;
				}
			}else{
				Map<String, Object> kgMap = new HashMap<String, Object>();
				kgMap.put("praiseNum", KnowledgeLibConstants.NUM_ONE);
				kgMap.put("modifyTime", new Date().getTime());
				kgMap.put("knowledgeCode", objectCode);
				int updateNum = repositoryKnowledgeBySryMapper.updateNumTime(kgMap);
				if (updateNum != 1) {
					log.error("RepositoryPraiseServiceImpl.doPraise：修改" + updateNum + "条收藏量记录 ");
					return false;
				}
			}
			
			// 插入
			int insertNum = repositoryPraiseBySryMapper.insert(infoVo);
			if (insertNum != 1) {
				log.error("RepositoryPraiseServiceImpl.doPraise：插入" + insertNum + "条收藏信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * 删除点赞 及 修改点赞量
	 * @throws BusinessException 
	 */
	@Override
	public boolean deletePraise(RepositoryPraiseVo rv) throws BusinessException {
		try {
			// 参数校验
			if (rv == null) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：参数为空");
				throw BusinessException.build("COMMON_402", "RepositoryPraiseVo");
			}
			String userCode = rv.getUserCode();
			String objectCode = rv.getObjectCode();
			Byte praiseType = rv.getPraiseType();
			if (StringUtils.isBlank(objectCode)) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：objectCode为空");
				throw BusinessException.build("COMMON_402", "objectCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (praiseType==null||!(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KGAPPEND.equals(praiseType)||(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KG.equals(praiseType)))) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：praiseType不合法");
				throw BusinessException.build("COMMON_402", "praiseType");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("objectCode", objectCode); // 对象类型
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("praiseType", praiseType); // 点赞类型
			// 查询是否已经收藏
			int count = repositoryPraiseBySryMapper.selectCount(paramMap);
			if (count < 1) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：重复取消点赞");
				throw BusinessException.build("KNOWLEDGELIB_27005");
			}
			// 删除点赞
			int deleteNum = repositoryPraiseBySryMapper.delete(paramMap);
			if (deleteNum != 1) {
				log.error("RepositoryPraiseServiceImpl.deletePraise：删除" + deleteNum + "条点赞信息 ");
				return false;
			}
			
			if(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KGAPPEND.equals(praiseType)){
				//知识追加
				Map<String, Object> kgAppendMap = new HashMap<String, Object>();
				kgAppendMap.put("praiseNum", KnowledgeLibConstants.NUM_BELOW_ONE);
				kgAppendMap.put("modifyTime", new Date().getTime());
				kgAppendMap.put("appendCode", objectCode);
				int updateNum = repositoryKnowledgeAppendBySryMapper.updateNumTime(kgAppendMap);
				if (updateNum != 1) {
					log.error("RepositoryPraiseServiceImpl.deletePraise：修改" + updateNum + "条点赞量记录 ");
					return false;
				}
			}else{
				//知识
				Map<String, Object> kgMap = new HashMap<String, Object>();
				kgMap.put("praiseNum", KnowledgeLibConstants.NUM_BELOW_ONE);
				kgMap.put("modifyTime", new Date().getTime());
				kgMap.put("knowledgeCode", objectCode);
				int updateNum = repositoryKnowledgeBySryMapper.updateNumTime(kgMap);
				if (updateNum != 1) {
					log.error("RepositoryPraiseServiceImpl.deletePraise：修改" + updateNum + "条点赞量记录 ");
					return false;
				}
			}
			
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
