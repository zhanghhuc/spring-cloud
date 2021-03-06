package com.zssq.msg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.MblogConstants;
import com.zssq.dao.model.BlogModel;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.UserMsgAT;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageATModel;
import com.zssq.msg.vo.AtMyListVO;
import com.zssq.msg.vo.DeleteATVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.BlogThirdService;
import com.zssq.service.ISysUserService;
import com.zssq.service.MblogThridService;
import com.zssq.service.MessageATService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PropertiesUtil;



/**
 *
 * @ClassName: MsgATController
 * @Description: @我的消息
 * @author YDB
 * @date 2017年3月31日
 *
 */

@Controller
@RequestMapping("/msg")
public class MsgATController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	
	@Autowired
	private MessageATService messageATService;

	@Autowired
	private MblogThridService mblogThridService;
	
	@Autowired
	private ISysUserService userService;
	

	@Autowired
	private BlogThirdService blogThirdService;
	
	/**
	 *
	 * @Title: getMyAtList
	 * @Description: @我查询
	 * @param at
	 * @return
	 * @throws BusinessException    参数
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getMyAtList")
	@ResponseBody
	public ResultJSON getMyAtList(@RequireValid AtMyListVO at) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		MessageATModel messageAT=new MessageATModel();
		JSONObject json=new JSONObject();
		JSONArray jsonArray=new JSONArray();

		try {
			//查询条件
			messageAT.setUserCode(at.getUserCode());
			messageAT.setPageNo(Integer.parseInt(at.getPageNo()));
			messageAT.setPageSize(Integer.parseInt(at.getPageSize()));
			PageBean pageBean=messageATService.getMyAtList(messageAT);
			//返回值封装
			List<UserMsgAT> list=pageBean.getRecordList();

			jsonArray=getATContent(list,at.getUserCode());

			m = PropertiesUtil.getMessage("COMMON_200");

			json.put("total", pageBean.getTotalCount());
			json.put("atList", jsonArray);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			m = PropertiesUtil.getMessage("MSG_26001");
			logger.error("getMyAtList():"+JSONObject.toJSONString(at),e);
			throw new BusinessException().build("MSG_26001","获取列表");
		}

		return resJson;
	}

	/**
	 * 获取At 的原文信息
	 * @return
     */
	private JSONArray getATContent(List<UserMsgAT> list,String userCode) throws BusinessException {
		
		JSONArray jsonList=new JSONArray();
		List<String> codeList=new ArrayList<>();
		List<String> blogCodeList=new ArrayList<>();
		JSONObject json=new JSONObject();
		if(list.size()==0){
			return jsonList;
		}

		for(UserMsgAT at:list){
			
			if(at.getAtType()!=5){
				//微博信息
				codeList.add(at.getOriginalCode());
			}else{
				//博客信息
				blogCodeList.add(at.getOriginalCode());
			}
			
		}
		//获取到微博详细信息
		List<MblogModel> mmList=mblogThridService.getMblogList(codeList,userCode);
		
		//获取博客详情信息
		List<BlogModel> blogList=blogThirdService.getBlogTitleAndDataByCode(blogCodeList);
		
		
		List<String> userCodeList=new ArrayList<>();
		userCodeList.add(userCode);
		//获取用户信息
		for(int i=0;i<list.size();i++){
			userCodeList.add(list.get(i).getAtUserCode());		
			userCodeList.add(list.get(i).getUserCode());
		}
		
		Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);
		
		for(int i=0;i<list.size();i++){
			UserMsgAT temp=list.get(i);
			SysUserInfo user=(SysUserInfo) userMap.get(temp.getUserCode());
			
			//被at人用户code
			json.put("userCode",temp.getUserCode());
			json.put("userName", user.getUserName());
			json.put("orgName",user.getManageOrgInfo().getSysOrgName());
			json.put("createTime",temp.getCreateTime());
			//原文code
			json.put("originalCode", temp.getOriginalCode());
			//动态取
			json.put("atCode", temp.getAtCode());
			json.put("atType", temp.getAtType());
			json.put("content", temp.getContent());
			
			if(temp.getAtType()!=5){
				//添加微博信息
				for(MblogModel mm:mmList){
					if(mm.getMblogCode().equals(temp.getOriginalCode())){
						//获取微博原文内容
						if(mm.getMblogSource() == MblogConstants.MBLOG_SOURCE_FORWARD){
							//转发类型获取原文内容
							MblogInfo mbInfo=mm.getForwardMblog();
							SysUserInfo userInfo=userService.selectByCode(mbInfo.getUserCode());
							
							//原文内容
							json.put("originalContent",mbInfo.getContent());
							json.put("forwardNumber",mbInfo.getForwardNum());
							json.put("commentNumber",mbInfo.getCommentNum());
							json.put("praiseNumber",mbInfo.getPraiseNum());
							json.put("collectNumber",mbInfo.getCollectNum());
							json.put("images",mbInfo.getImgs());
							json.put("videos",mbInfo.getVideos());
							json.put("audios", mbInfo.getAudios());
							json.put("orgUserCode",mbInfo.getUserCode());
							json.put("orgCreateTime", mbInfo.getCreateTime());
							// 判断是否有查询到用户信息
							if(userInfo!=null){
								json.put("orgUserName",userInfo.getUserName());
								json.put("orgOrgName",userInfo.getManageOrgInfo().getSysOrgName() );
								json.put("orgUserHead",userInfo.getHeadPortrait());
							}
						}else{
							//发起时获取原文内容
							SysUserInfo userInfo=userService.selectByCode(mm.getUserCode());
							json.put("originalContent",mm.getContent());
							json.put("forwardNumber",mm.getForwardNum());
							json.put("commentNumber",mm.getCommentNum());
							json.put("praiseNumber",mm.getPraiseNum());
							json.put("collectNumber",mm.getCollectNum());
							json.put("images",mm.getImgs());
							json.put("videos",mm.getVideos());
							json.put("audios", mm.getAudios());
							json.put("orgUserCode",mm.getUserCode());
							json.put("orgCreateTime", mm.getCreateTime());
							// 判断是否有查询到用户信息
							if(userInfo!=null){
								json.put("orgUserName",userInfo.getUserName());
								json.put("orgOrgName",userInfo.getManageOrgInfo().getSysOrgName() );
								json.put("orgUserHead",userInfo.getHeadPortrait());
							}
							
						}
						
					}
				}
			}else{
				//博客类型
				for(BlogModel blog:blogList){
					if(blog.getBlogCode().equals(temp.getOriginalCode())){
						SysUserInfo userInfo=userService.selectByCode(blog.getUserCode());

						json.put("originalContent",blog.getBlogTitle());
						json.put("forwardNumber",blog.getForwardNum());
						json.put("commentNumber",blog.getCommentNum());
						json.put("praiseNumber",blog.getLikeNum());
						json.put("collectNumber",blog.getCollectNum());
						json.put("blogDigest", blog.getBlogDigest());
						json.put("images","");
						json.put("videos","");
						json.put("orgUserCode",blog.getUserCode());
						json.put("orgCreateTime", blog.getBlogPublishTime());
						// 判断是否有查询到用户信息
						if(userInfo!=null){
							json.put("orgUserName",userInfo.getUserName());
							json.put("orgOrgName",userInfo.getManageOrgInfo().getSysOrgName() );
							json.put("orgUserHead",userInfo.getHeadPortrait());
						}
					}
				}
			}
			
			SysUserInfo userInfo=(SysUserInfo) userMap.get(temp.getAtUserCode());
			if(userInfo==null){
				json.put("userName", "");
				json.put("orgName","");
			}else{
				json.put("atUserName", userInfo.getUserName());
				json.put("atOrgName", userInfo.getManageOrgInfo().getSysOrgName());
			}
			json.put("atUserCode",temp.getAtUserCode());
			json.put("atOrgName", userInfo.getManageOrgInfo().getSysOrgName());
			json.put("atUserHead", userInfo.getHeadPortrait());
			
			jsonList.add(json);
			json=new JSONObject();
		}

		return  jsonList;
	}
	

	/**
	 *
	 * @Title:delPrivateLetter
	 * @Description:删除@我的消息
	 * @param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/deleteMyAT")
	@ResponseBody
	public ResultJSON deleteMyAT(@RequireValid DeleteATVO at) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			boolean state=false;
				state=messageATService.delMessageAT(at.getAtCode());
				json.put("result",state?0:1 );
			if(state){
				//成功
				m = PropertiesUtil.getMessage("COMMON_200");
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
			}else{
				throw new BusinessException().build("MSG_26001","操作");
			}
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			
			logger.error("deleteMyAT():"+JSONObject.toJSONString(at),e);
			m = PropertiesUtil.getMessage("MSG_26001");
			throw new BusinessException().build("MSG_26001","操作");
		}
		
		return resJson;
		
	}
	
}
