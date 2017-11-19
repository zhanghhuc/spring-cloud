package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserMsgComment;
import com.zssq.model.MessageCommentModel;

public interface UserMsgCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsgComment record);

    int insertSelective(UserMsgComment record);

    UserMsgComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsgComment record);

    int updateByPrimaryKey(UserMsgComment record);
    
    List<UserMsgComment> selectPage(MessageCommentModel record);
    
    int selectCount(MessageCommentModel record);
    
    int delComment(UserMsgComment record);
    
    
}