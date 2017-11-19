package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.model.MessageNoticeListModel;

public interface UserMsgNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMsgNotice record);

    int insertSelective(UserMsgNotice record);

    UserMsgNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMsgNotice record);

    int updateByPrimaryKey(UserMsgNotice record);
    
    List<UserMsgNotice> selectPage(MessageNoticeListModel record);
    
    int selectCount(MessageNoticeListModel record);

	UserMsgNotice getNoticeInfo(String msgCode);

	int delNotice(String msgCode);

	void saveNoticeList(List<UserMsgNotice> list);
    
}