package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ForumCommentReply;
import com.zssq.dao.pojo.ForumCommentReplyExample;

public interface ForumCommentReplyMapper {
    int countByExample(ForumCommentReplyExample example);

    int deleteByExample(ForumCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumCommentReply record);

    int insertSelective(ForumCommentReply record);

    List<ForumCommentReply> selectByExampleWithBLOBs(ForumCommentReplyExample example);

    List<ForumCommentReply> selectByExample(ForumCommentReplyExample example);

    ForumCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumCommentReply record, @Param("example") ForumCommentReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") ForumCommentReply record, @Param("example") ForumCommentReplyExample example);

    int updateByExample(@Param("record") ForumCommentReply record, @Param("example") ForumCommentReplyExample example);

    int updateByPrimaryKeySelective(ForumCommentReply record);

    int updateByPrimaryKeyWithBLOBs(ForumCommentReply record);

    int updateByPrimaryKey(ForumCommentReply record);
    
    /**
     * 获取举报评论回复所在行号
     * @param reply
     * @return
     */
    int getComplaintReplyRownum(ForumCommentReply reply);
    /**
     * 加载更多的方式分页查询
     * @Function queryByPageWithLoadMore
     * @Description 
     * @param reply
     * @return
     */
    List<ForumCommentReply> queryByPageWithLoadMore(ForumCommentReply reply);
}