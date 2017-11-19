package com.zssq.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogTopic;
import com.zssq.exceptions.BusinessException;
import com.zssq.qo.TopicInfoQO;
import com.zssq.service.MblogTopicService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogTopicServiceImpl  
    * @Description: 微博话题操作实现类  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
@Service("mblogTopicService")
public class MblogTopicServiceImpl extends BaseService implements MblogTopicService, Serializable{

    /**  
    * @Fields serialVersionUID 
    */  
	private static final long serialVersionUID = 1L;

	/**
	 * 
	    * @Title: queryTopicList  
	    * @Description: 查询 话题列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	@Override
	public PageBean queryTopicList(TopicInfoQO qo) throws BusinessException {
		// 创建返回值
		PageBean pageBean = new PageBean();
		
		List<MblogTopic> topicList = new ArrayList<MblogTopic>();
		try{
			// 创建参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("limitStart", qo.getLimitStart());
			paramsMap.put("limitSize", qo.getLimitSize());
			
			//查询
			topicList = mblogTopicMapper.queryTopicListByPage(paramsMap);
			long count = mblogTopicMapper.queryTopicListByPageCount(paramsMap);
			
			pageBean.setRecordList(topicList);
			pageBean.setTotalCount((int)count);
			
		}catch(Exception e){
			log.error("MblogTopicServiceImpl.queryTopicList:失败"+e);
			throw BusinessException.build("MBLOG_12004", "查询主题列表失败");
		}
		return pageBean;
	}

	/**
	 * 
	    * @Title: addTopicInfo  
	    * @Description: 添加话题信息
	    * @param qo
	    * @throws BusinessException
		* @return String    返回类型
	 */
	@Override
	public String addTopicInfo(TopicInfoQO qo) throws BusinessException {
		// 创建返回值
		String result = null;
		long time = new Date().getTime();
		try{
			if(StringTools.isNotEmpty(qo.getTopicName())){
				// 校验是否存在
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("topicName", qo.getTopicName());
				MblogTopic topic = mblogTopicMapper.checkIsExist(paramsMap);
				if(null != topic && StringTools.isNotEmpty(topic.getMblogTopicCode())){
					// 存在
					result = topic.getMblogTopicCode();
				}else{
					// 不存在
					topic = new MblogTopic();
					String topicCode = UUIDHelper.getUUID();
					topic.setMblogTopicCode(topicCode);
					topic.setCreateTime(time);
					topic.setModifyTime(time);
					topic.setRemark(MblogConstants.MBLOG_BLANK);
					topic.setTopicName(qo.getTopicName());
					topic.setTopicNum(MblogConstants.MBLOG_ZERO);
					
					topic.setOrgCode(qo.getOrgCode());
					topic.setTenantCode(qo.getTenantCode());
					// 插入
					if(mblogTopicMapper.insert(topic) > 0){
						result = topicCode;
					}
				}
			}
			
		}catch(Exception e){
			log.error("MblogTopicServiceImpl.addTopicInfo:添加主题失败"+e);
			throw BusinessException.build("MBLOG_12004", "添加主题失败");
		}
		return result;
	}

}
