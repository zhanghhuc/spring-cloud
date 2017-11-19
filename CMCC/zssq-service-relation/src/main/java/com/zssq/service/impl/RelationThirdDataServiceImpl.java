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
import com.zssq.constants.RelationConstants;
import com.zssq.dao.mapper.RelationSubjectDataBySryMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.KafkaService;
import com.zssq.service.RelationThirdDataService;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationDataVO;

/**
 * 
 * @ClassName: RelationThirdDataServiceImpl  
 * @Description: 数据  
 * @author ZKZ  
 * @date 2017年3月30日  
 *
 */
@Service("relationThirdDataService")
public class RelationThirdDataServiceImpl implements RelationThirdDataService,KafkaService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationSubjectDataBySryMapper relationSubjectBySryDataMapper;
	/**
	 * 修改班组置精次数
	 */
	@Override
	public boolean updateTeamQualityNum(RelationDataVO relationDataVO) throws BusinessException {

		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateTeamQualityNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateTeamQualityNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateTeamQualityNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateTeamQualityNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改置精次数
			int updateNum = relationSubjectBySryDataMapper.updateTeamQualityNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateTeamQualityNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改集团级别置精次数
	 */
	@Override
	public boolean updateGroupQualityNum(RelationDataVO relationDataVO) throws BusinessException {
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateGroupQualityNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateGroupQualityNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateGroupQualityNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateGroupQualityNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改置精次数
			int updateNum = relationSubjectBySryDataMapper.updateGroupQualityNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateGroupQualityNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * 修改省级别置精次数
	 */
	@Override
	public boolean updateProvinceQualityNum(RelationDataVO relationDataVO) throws BusinessException {
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateProvinceQualityNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateProvinceQualityNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateProvinceQualityNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateProvinceQualityNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改置精次数
			int updateNum = relationSubjectBySryDataMapper.updateProvinceQualityNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateProvinceQualityNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改市级别置精次数
	 */
	@Override
	public boolean updateCityQualityNum(RelationDataVO relationDataVO) throws BusinessException {
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateCityQualityNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateCityQualityNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateCityQualityNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateCityQualityNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改置精次数
			int updateNum = relationSubjectBySryDataMapper.updateCityQualityNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateCityQualityNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * 修改参与量
	 */
	@Override
	public boolean updateJoinNum(RelationDataVO relationDataVO) throws BusinessException {
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateJoinNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateJoinNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateJoinNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateJoinNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改参与量
			int updateNum = relationSubjectBySryDataMapper.updateJoinNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateJoinNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * 添加浏览量 updateNumber 为 加量
	 */
	@Override
	public boolean updateReadNum(RelationDataVO relationDataVO) throws BusinessException {
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateReadNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateReadNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateReadNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateReadNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改参与量
			int updateNum = relationSubjectBySryDataMapper.updateReadNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateReadNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改点赞量 updateNumber 为 加量
	 */
	@Override
	public boolean updateLikeNum(RelationDataVO relationDataVO) throws BusinessException {
		
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateLikeNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateLikeNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateLikeNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateLikeNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改量
			int updateNum = relationSubjectBySryDataMapper.updateLikeNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateLikeNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改收藏量 updateNumber 为 加量
	 */
	@Override
	public boolean updateCollectNum(RelationDataVO relationDataVO) throws BusinessException {
	
		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateCollectNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateCollectNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateCollectNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateCollectNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改量
			int updateNum = relationSubjectBySryDataMapper.updateCollectNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateCollectNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改转发量 updateNumber 为添加量
	 */
	@Override
	public boolean updateForwardNum(RelationDataVO relationDataVO) throws BusinessException {

		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateForwardNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateForwardNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateForwardNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateForwardNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改量
			int updateNum = relationSubjectBySryDataMapper.updateForwardNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateForwardNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	
	/**
	 * 修改评论量 updateNumber 为添加量
	 */
	@Override
	public boolean updateCommentNum(RelationDataVO relationDataVO) throws BusinessException {

		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateCommentNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateCommentNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateCommentNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateCommentNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改量
			int updateNum = relationSubjectBySryDataMapper.updateCommentNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateCommentNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * 修改分享量
	 */
	@Override
	public boolean updateShareNum(RelationDataVO relationDataVO) throws BusinessException {

		try {
			// 参数校验
			if (relationDataVO == null) {
				log.error("RelationThirdDataServiceImpl.updateShareNum：relationDataVO为空");
				throw BusinessException.build("COMMON_402", "relationDataVO");
			}
			
			// 获取参数
			String subjectCode = relationDataVO.getSubjectCode(); // 内容编号
			Integer updateNumber = relationDataVO.getUpdateNumber(); // 状态
			Long modifyTime = relationDataVO.getModifyTime(); //修改时间
			// 参数校验
			if (StringUtils.isBlank(subjectCode)) {
				log.error("RelationThirdDataServiceImpl.updateShareNum：subjectCode为空");
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if(updateNumber == null||updateNumber==0){
				log.error("RelationThirdDataServiceImpl.updateShareNum：updateNumber="+updateNumber);
				throw BusinessException.build("COMMON_402", "updateNumber");
			}
			if(modifyTime == null){
				log.error("RelationThirdDataServiceImpl.updateShareNum：modifyTime="+modifyTime);
				throw BusinessException.build("COMMON_402", "modifyTime");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subjectCode", subjectCode); // 内容编号
			paramMap.put("modifyTime", modifyTime); //更新时间
			paramMap.put("updateNumber", updateNumber); //更新时间
			// 修改量
			int updateNum = relationSubjectBySryDataMapper.updateShareNum(paramMap);
			if (updateNum != 1) {
				log.error("RelationThirdDataServiceImpl.updateShareNum：更新" + updateNum + "条记录");
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return true;
	}
	/**
	 * kafka 队列修改 数据
	 */
	@Override
	public void invokeService(@SuppressWarnings("rawtypes") ConsumerRecord record) throws Exception {
		try{
			if(null != record.value()){
				// 获取参数
				RelationDataVO relationDataVO = JSONObject.toJavaObject(JSON.parseObject((String)record.value()),RelationDataVO.class);
				if(relationDataVO==null){
					log.info("RelationThirdDataService.invokeService:kafka处理业务更新数量失败：获取参数失败");
					throw BusinessException.build("COMMON_402", "relationDataVO");
				}
				Byte updateClass = relationDataVO.getUpdateClass();
				String subjectCode = relationDataVO.getSubjectCode();
				if(StringTools.isNotEmpty(subjectCode)){
					boolean updateStatus = false;
					switch(updateClass){
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_TEAM:{ //班组
							updateStatus = updateTeamQualityNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_GROUP:{ // 集团
							updateStatus = updateGroupQualityNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_PROVINCE:{ // 省级
							updateStatus = updateProvinceQualityNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_CITY:{ // 市级
							updateStatus = updateCityQualityNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_JOIN:{ //参与
							updateStatus = updateJoinNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_READ:{ //浏览
							updateStatus = updateReadNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE:{ //点赞
							updateStatus = updateLikeNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT:{ //收藏
							updateStatus = updateCollectNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_FORWARD:{ //转发
							updateStatus = updateForwardNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT:{ //评论
							updateStatus = updateCommentNum(relationDataVO);
							break;
						}
						case RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE:{ //分享
							updateStatus = updateShareNum(relationDataVO);
							break;
						}
						default:{
							log.error("relationThirdDataService.invokeService:kafka修改数量操作失败updateClass="+updateClass);
						}
					}
					if(!updateStatus){
						// 各个方法已做处理
						/*log.error("relationThirdDataService.invokeService:kafka修改数量操作失败updateClass="+updateClass);
						throw new Exception("relationThirdDataService.invokeService:修改数量操作失败");*/
					}
				}else{
					log.error("RelationThirdDataServiceImpl.invokeService：subjectCode为空");
					throw BusinessException.build("COMMON_402", "subjectCode");
				}
			}
			
		}catch(Exception e){
			throw new Exception("RelationThirdDataService.invokeService:修改数量操作失败",e);
		}
	}

}
