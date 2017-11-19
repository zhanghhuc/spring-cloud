package com.zssq.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.UserMsgBoardMapper;
import com.zssq.dao.mapper.UserMsgBoardReplyMapper;
import com.zssq.dao.pojo.UserBordAndReply;
import com.zssq.model.MessageBoradModel;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageBoardBiz  
 * @Description: 留言墙数据拼装  
 * @author YDB  
 * @date 2017年3月27日  
 *
 */
@Service("messageBoardBiz")
public class MessageBoardBiz {

	@Autowired
	private UserMsgBoardMapper userMsgBoardMapper;
	
	@Autowired
	private UserMsgBoardReplyMapper userMsgBoardReplyMapper;
	
	
	/**
	 * 
	 * @Title: getMyPublishMessageList  
	 * @Description: 获取留言墙列表
	 * @return    参数  
	 * @return: userMsgBoard    返回类型
	 */
	
	public PageBean getMyPublishMessageList(MessageBoradModel msgBoradModel){
		//查询留言信息
		PageBean pageBean=new PageBean();
		msgBoradModel.setPageNo(msgBoradModel.getPageNo()*msgBoradModel.getPageSize());
		int total=userMsgBoardMapper.selectCount(msgBoradModel);
		//List<UserMsgBoard> boradList=userMsgBoardMapper.selectPage(msgBoradModel);
		
		List<UserBordAndReply> boradList=userMsgBoardMapper.selectBordList(msgBoradModel);
		
		//装入list查询评论
		List<String> boradCodeList=new ArrayList<>(); 
		
		List<Map<String, Object>>  replyCountList=new ArrayList<>();
		
		for (UserBordAndReply temp : boradList) {
			boradCodeList.add(temp.getMessageBoardCode());
		}
		
		if(boradCodeList.size()!=0){
			
			 replyCountList=userMsgBoardReplyMapper.selectBoardReplyCount(boradCodeList);
		}
		
		for (UserBordAndReply temp : boradList) {
			if(boradCodeList.size()==0){
				temp.setReplyCount(0);
			}else{
			temp.setReplyCount(this.ownGetReplyCount(temp.getMessageBoardCode(),replyCountList));
			}
		}
		
		pageBean.setRecordList(boradList);
		pageBean.setTotalCount(total);
		
		return pageBean;
		
	}
	
	/**
	 * 
	 * @Title: getReplyJson  
	 * @Description: 获取对应的留言板的回复List
	 * @param code
	 * @param replyContentList
	 * @return    参数  
	 * @return: JSONObject    返回类型
	 */
	private List<JSONObject>  getReplyJson(String code,List<Map<String, Object>> replyContentList){
		List<JSONObject> listJson=new ArrayList<>();
		JSONObject json=new JSONObject();
		
		for (Map<String, Object> map : replyContentList) {
			try {
				if(map.get("board").toString().equals(code)){
					json.put("replContent",map.get("reply_content").toString());
					json.put("userCode",map.get("user_code").toString());
					json.put("creatTime", map.get("creat_time").toString());
					json.put("replyCode", map.get("reply_code").toString());
					//json.put("content", map.get("reply_content").toString());
					listJson.add(json);
					json=new JSONObject();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return listJson;
	}
	
	
	
	
	/**
	 * 
	 * @Title: ownGetReplyCount  
	 * @Description: 获取当前留言评论总数
	 * @return    参数  
	 * @return: int    返回类型
	 */
	private int ownGetReplyCount(String code, List<Map<String, Object>> replyCountList){
		try {
			
			for (Map<String, Object> map : replyCountList) {
				
				if(map.get("board").toString().equals(code)){
					
					return Integer.parseInt(map.get("total").toString());
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
}
