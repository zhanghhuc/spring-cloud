package com.zssq.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.dao.mapper.RepositoryKnowledgeBySryMapper;
import com.zssq.dao.mapper.RepositoryShareBySryMapper;
import com.zssq.dao.pojo.RepositoryShare;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.RepositoryShareService;

/**
 * 
 * @ClassName: RepositoryShareService  
 * @Description: 知识库分享  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Service("repositoryShareService")
public class RepositoryShareServiceImpl implements RepositoryShareService{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryShareBySryMapper repositoryShareBySryMapper;
	@Autowired
	private RepositoryKnowledgeBySryMapper repositoryKnowledgeBySryMapper;
	
	@Override
	public boolean saveShare(RepositoryShare rs) throws BusinessException {
		try {
			// 参数校验
			if (rs == null) {
				log.error("RepositoryShareServiceImpl.saveShare：参数为空");
				throw BusinessException.build("COMMON_402", "RepositoryPraise");
			}
			//更新 知识 分享量
			Map<String, Object> kgMap = new HashMap<String, Object>();
			kgMap.put("shareNum", KnowledgeLibConstants.NUM_ONE);
			kgMap.put("modifyTime", new Date().getTime());
			kgMap.put("knowledgeCode", rs.getKnowledgeCode());
			int updateNum = repositoryKnowledgeBySryMapper.updateNumTime(kgMap);
			if (updateNum != 1) {
				log.error("RepositoryShareServiceImpl.saveShare：修改" + updateNum + "条收藏量记录 ");
				return false;
			}
			// 插入
			
			int insertNum = repositoryShareBySryMapper.insert(rs);
			if (insertNum != 1) {
				log.error("RepositoryShareServiceImpl.saveShare：插入" + insertNum + "条分享信息 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}

}
