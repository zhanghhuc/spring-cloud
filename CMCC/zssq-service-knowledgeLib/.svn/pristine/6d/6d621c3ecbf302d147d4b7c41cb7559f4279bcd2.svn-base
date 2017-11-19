package com.zssq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.dao.mapper.RepositoryKnowledgeAppendBySryMapper;
import com.zssq.dao.mapper.RepositoryKnowledgeAppendMapperByGuoYang;
import com.zssq.dao.mapper.RepositoryKnowledgeBySryMapper;
import com.zssq.dao.pojo.RepositoryKnowledgeAppend;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.GetKnowledgeAppendForGLModel;
import com.zssq.model.RepositoryKnowledgeAppendMH;
import com.zssq.service.RepositoryKnowledgeAppendService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.GetKnowledgeAppendForGLVo;
import com.zssq.vo.RepositoryKnowledgeAppendVo;

/**
 * 
 * @ClassName: RepositoryKnowledgeAppendService  
 * @Description: 知识追加  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Service("repositoryKnowledgeAppendService")
public class RepositoryKnowledgeAppendServiceImpl implements RepositoryKnowledgeAppendService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryKnowledgeAppendBySryMapper repositoryKnowledgeAppendBySryMapper;
	
	@Autowired
	private RepositoryKnowledgeBySryMapper repositoryKnowledgeBySryMapper;
	@Autowired
	RepositoryKnowledgeAppendMapperByGuoYang repositoryKnowledgeAppendMapperByGuoYang;
	/**
	 * 门户获取知识追加
	 */
	@Override
	public PageBean getPortalKnowledgeAppend(PageParam pageParam, RepositoryKnowledgeAppendVo infoVo)
			throws BusinessException {
		// 返回值
		PageBean pageBean = null;
		
		try {
			// 参数校验
			if (pageParam == null || infoVo == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getPortalKnowledgeAppend：参数为空");
				throw BusinessException.build("COMMON_402");
			}
			
			// 获取参数
			Integer pageSize = pageParam.getPageSize(); // 每页记录数
			Integer pageNo = pageParam.getPageNo(); // 当前页数
			
			Byte isSelfAppend = infoVo.getIsSelfAppend(); //0：他人，1，自己 ,null:全部 
			String userCode = infoVo.getUserCode(); // 人员编号
			String knowledgeCode = infoVo.getKnowledgeCode(); // 知识编号
			
			// 参数校验
			if (pageSize == null || pageNo == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getPortalKnowledgeAppend：分页信息为空");
				throw BusinessException.build("COMMON_402");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getPortalKnowledgeAppend：userCode为空");
				throw BusinessException.build("COMMON_402");
			}
			if (StringUtils.isBlank(knowledgeCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getPortalKnowledgeAppend：knowledgeCode为空");
				throw BusinessException.build("COMMON_402");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", pageNo*pageSize); // 查询开始条数
			paramMap.put("limitCount", pageSize); // 查询结果条数
			paramMap.put("isSelfAppend", isSelfAppend); // 是否自己追加
			paramMap.put("userCode", userCode); // 人员编号
			paramMap.put("knowledgeCode", knowledgeCode); // 知识编号
			
			// 查询列表总数
			int count = repositoryKnowledgeAppendBySryMapper.selectCount(paramMap);
			
			// 查询列表内容
			List<RepositoryKnowledgeAppendMH> blogList = repositoryKnowledgeAppendBySryMapper.list(paramMap);
			
			// 返回值
			pageBean = new PageBean(pageNo, pageSize, count, blogList);
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
	}
	/**
	 * 保存追加
	 */
	@Override
	public Boolean addPortalKnowledgeAppendForMH(RepositoryKnowledgeAppend infoVo) throws BusinessException {
		try {
			// 参数校验
			if (infoVo == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：参数为空");
				throw BusinessException.build("COMMON_402","参数为空");
			}
			String userCode = infoVo.getUserCode();
			String orgCode = infoVo.getOrgCode();
			String knowledgeCode = infoVo.getKnowledgeCode();
			if (StringUtils.isBlank(knowledgeCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：knowledgeCode为空");
				throw BusinessException.build("COMMON_402", "knowledgeCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("knowledgeCode", knowledgeCode); // 对象类型
			paramMap.put("orgCode", orgCode); // 操作对象编号
			paramMap.put("userCode", userCode); // 人员编号
			Byte isSelfAppend = KnowledgeLibConstants.REPOSITORY_NO;
			// 查询是自己追加
			int count = repositoryKnowledgeBySryMapper.selectCountForIsSelf(paramMap);
			if (count > 0) {
				isSelfAppend = KnowledgeLibConstants.REPOSITORY_YES;
				paramMap.put("modifyTime", infoVo.getModifyTime());
				paramMap.put("addTime", infoVo.getModifyTime());
				int updateNum = repositoryKnowledgeBySryMapper.updateNumTime(paramMap);
				if (updateNum != 1) {
					log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：修改" + updateNum + "条自己添加加量记录 ");
					return false;
				}
			}else{
				//非自己添加追加量
				paramMap.put("appendNum", KnowledgeLibConstants.NUM_ONE);
				paramMap.put("modifyTime", infoVo.getModifyTime());
				paramMap.put("appendTime", infoVo.getModifyTime());
				int updateNum = repositoryKnowledgeBySryMapper.updateNumTime(paramMap);
				if (updateNum != 1) {
					log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：修改" + updateNum + "条追加量记录 ");
					return false;
				}
			}
			infoVo.setIsSelfAppend(isSelfAppend);
			// 插入
			int insertNum = repositoryKnowledgeAppendBySryMapper.insert(infoVo);
			if (insertNum != 1) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：插入" + insertNum + "条追加 ");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 后台获取知识追加列表
	 */
	@Override
	public PageBean getKnowledgeAppendForGL(PageParam pageParam,
			GetKnowledgeAppendForGLVo getKnowledgeAppendForGLVo) throws BusinessException {
		PageBean pageBean = null;
		try {
			// 参数校验
			if (getKnowledgeAppendForGLVo == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getKnowledgeAppendForGL：参数为空");
				throw BusinessException.build("COMMON_402","参数为空");
			}
			if (pageParam == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getKnowledgeAppendForGL：参数为空");
				throw BusinessException.build("COMMON_402","参数为空");
			}
			
			Integer pageNo = pageParam.getPageNo();
			Integer pageSize = pageParam.getPageSize();
			String knowledgeCode = getKnowledgeAppendForGLVo.getKnowledgeCode();
			if (pageNo == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getKnowledgeAppendForGL：pageNo为空");
				throw BusinessException.build("COMMON_402", "pageNo");
			}
			if (pageSize == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getKnowledgeAppendForGL：pageSize为空");
				throw BusinessException.build("COMMON_402", "pageSize");
			}
			if (StringUtils.isBlank(knowledgeCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.getKnowledgeAppendForGL：knowledgeCode为空");
				throw BusinessException.build("COMMON_402", "knowledgeCode");
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("limitStart", pageNo*pageSize);
			map.put("limitCount", pageSize); 
			map.put("knowledgeCode",knowledgeCode);
			map.put("isSelfAppend",getKnowledgeAppendForGLVo.getIsSelfAppend());
			List<GetKnowledgeAppendForGLModel> list = repositoryKnowledgeAppendMapperByGuoYang.getKnowledgeAppendForGL(map);
			Integer pageCount = repositoryKnowledgeAppendMapperByGuoYang.getCountAppendForGL(map);
			pageBean = new PageBean();
			pageBean.setTotalCount(pageCount);
			pageBean.setRecordList(list);
		} catch (BusinessException e) {
			throw e;
		}
		return pageBean;
	}
	
	@Override
	public int selectCountForIsSelf (RepositoryKnowledgeAppend repositoryKnowledgeAppend) throws BusinessException{
		Integer count = null;
		try {
			// 参数校验
			if (repositoryKnowledgeAppend == null) {
				log.error("RepositoryKnowledgeAppendServiceImpl.selectCountForIsSelf：参数为空");
				throw BusinessException.build("COMMON_402","参数为空");
			}
			String userCode = repositoryKnowledgeAppend.getUserCode();
			String orgCode = repositoryKnowledgeAppend.getOrgCode();
			String knowledgeCode = repositoryKnowledgeAppend.getKnowledgeCode();
			if (StringUtils.isBlank(knowledgeCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：knowledgeCode为空");
				throw BusinessException.build("COMMON_402", "knowledgeCode");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.selectCountForIsSelf：userCode为空");
				throw BusinessException.build("COMMON_402", "userCode");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryKnowledgeAppendServiceImpl.addPortalKnowledgeAppendForMH：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("knowledgeCode", repositoryKnowledgeAppend.getKnowledgeCode()); // 对象类型
			paramMap.put("orgCode", repositoryKnowledgeAppend.getOrgCode()); // 操作对象编号
			paramMap.put("userCode", repositoryKnowledgeAppend.getUserCode()); // 人员编号
			count = repositoryKnowledgeBySryMapper.selectCountForIsSelf(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		return count;
	}

}
