package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.dao.mapper.MsgPrivateLetterMapper;
import com.zssq.dao.pojo.MsgPrivateLetter;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.DelPrivateLetterVO;
import com.zssq.model.MessagePrivateInfoModel;
import com.zssq.model.MessagePrivateLetterModel;
import com.zssq.model.SendMsgPrivateLetterModel;
import com.zssq.service.MessagePrivateLetterService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.PageBean;

@Service("messagePrivateLetterService")
public class MsgPrivateletterServiceImpl implements MessagePrivateLetterService{

	@Autowired
	private MsgPrivateLetterMapper msgPrivateLetterMapper; 
	@Autowired
	private RedisUtil redisUtil;
	
	
	
	/**
	 * 
	 * @Title: sendPrivateLetter  
	 * @Description: 发送私信
	 * @param userCode 
	 * @param ReceiveUserCode 接收人userCode
	 * @param content
	 * @return    参数  
	 * @return: MsgPrivateLetter    返回类型
	 */
	
	@Transactional
	@Override
	public boolean sendPrivateLetter(SendMsgPrivateLetterModel sendModel)throws BusinessException  {
		MsgPrivateLetter letter=new MsgPrivateLetter();
		Long date=sendModel.getCreateTime();
		//添加发送信息
		letter.setContentType((byte)Integer.parseInt(sendModel.getContentType()));
		letter.setIsDelete((byte)0);
		letter.setLetterContent(sendModel.getContent());
		letter.setUserCode(sendModel.getReceiveUserCode());
		letter.setSendUserCode(sendModel.getUserCode());
		//收信人userCode
		letter.setPrivateLetterUserCode(sendModel.getReceiveUserCode());
		letter.setCreateTime(date);
		letter.setIsRead((byte)0);
		letter.setModifyTime(date);
		letter.setOrgCode(sendModel.getUserOrgCode());
	//	letter.setOrgLevel((byte)Integer.parseInt(sendModel.getOrgLevel()));
		letter.setTenantCode(sendModel.getTenantCode());
		letter.setPrivateLetterCode(UUIDHelper.getUUID());
		
		msgPrivateLetterMapper.insert(letter);
		//发信人数据添加完成
		
		//收信人数据
		letter.setOrgCode(sendModel.getUserOrgCode());
		letter.setUserCode(sendModel.getUserCode()); 
		letter.setPrivateLetterUserCode(sendModel.getReceiveUserCode());
		letter.setPrivateLetterCode(UUIDHelper.getUUID());
		//添加接收人数据
		int state=msgPrivateLetterMapper.insert(letter);
		
		redisUtil.addNumber(sendModel.getReceiveUserCode(), 5, 1);
		
		return state==1;
	}


	/**
	 * 
	 * @Title: getPrivateLetterMainList  
	 * @Description: 获取私信主列表
	 * @param letter
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getPrivateLetterMainList(MessagePrivateLetterModel letter) throws BusinessException  {
		redisUtil.delMsgNumber(letter.getUserCode(), 5);
		
		PageBean pageBean=new PageBean();
		int total=msgPrivateLetterMapper.selectCountMain(letter);
		letter.setPageNo(letter.getPageSize()*letter.getPageNo());
		List<MsgPrivateLetter> list=msgPrivateLetterMapper.selectPageMain(letter);
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		
		return pageBean;
	}


	/**
	 * 
	 * @Title: getPrivateLetterInfoList  
	 * @Description: 获取私信详细列表
	 * @param letter
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getPrivateLetterInfoList(MessagePrivateInfoModel letterModel) throws BusinessException {
		PageBean pageBean=new PageBean();
		letterModel.setPageNo(letterModel.getPageSize()*letterModel.getPageNo());
		pageBean.setTotalCount(msgPrivateLetterMapper.selectCountInfo(letterModel));
		pageBean.setRecordList(	msgPrivateLetterMapper.selectPageInfo(letterModel));
		
		return pageBean;
	}


	
	/**
	 * 
	 * @Title: delPrivateLetter  
	 * @Description: 删除私信
	 * @param userCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delPrivateLetter(String userCode,String letterUserCode) throws BusinessException {
		
		DelPrivateLetterVO vo=new DelPrivateLetterVO();
		vo.setUserCode(userCode);
		vo.setLetterUserCode(letterUserCode);
		
		int state=msgPrivateLetterMapper.delPrivateLetter(vo);
		
		return state==0?false:true;
	}
	
	
}