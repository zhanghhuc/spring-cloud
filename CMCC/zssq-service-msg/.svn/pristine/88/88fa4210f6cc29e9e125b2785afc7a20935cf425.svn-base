package com.zssq.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgPraiseMapper;
import com.zssq.dao.pojo.UserMsgPraise;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: MessagePraiseBiz  
 * @Description: 消息点赞BIZ  
 * @author YDB  
 * @date 2017年3月20日  
 *
 */
@Service("messagePraiseBiz")
public class MessagePraiseBiz {
	
	@Autowired
	private UserMsgPraiseMapper userMsgPraiseDao;
	
	/**
	 * @Title: getPraiseList  
	 * @Description: 消息点赞查询
	 * @param userCode
	 * @param pageParam
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPraiseList(UserMsgPraise userMsgPraise, PageParam pageParam)  {
			
			PageBean pageBean=new PageBean();
			userMsgPraise.setLimitStart(pageParam.getPageNo()*pageParam.getPageSize());
			userMsgPraise.setLimitEnd(pageParam.getPageSize());
			
			List<UserMsgPraise> list=userMsgPraiseDao.selectPage(userMsgPraise);
			int total=userMsgPraiseDao.selectCount(userMsgPraise);
			
			pageBean.setRecordList(list);
			pageBean.setTotalCount(total);
			
			return pageBean;
		}

	
	

}
