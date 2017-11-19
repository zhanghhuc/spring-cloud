package com.zssq.service.impl;

import com.zssq.biz.NewsCommentBiz;
import com.zssq.biz.NewsReplyBiz;
import com.zssq.biz.NewsReplyPraiseBiz;
import com.zssq.constants.NewsReplyConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.NewsReplyModel;
import com.zssq.news.model.NewsReplyPraiseModel;
import com.zssq.news.model.NewsReplyQuery;
import com.zssq.news.service.NewsReplyService;
import com.zssq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zssq.constants.NewsReplyConstants.NEWS_REPLY_LEFT;

@Service
public class NewsReplyServiceImpl  implements NewsReplyService {

    @Autowired
    private NewsReplyBiz newsReplyBiz;//新闻点赞逻辑层
    @Autowired
    private NewsReplyPraiseBiz newsReplyPraiseBiz;//新闻回复点赞逻辑层

    @Autowired
    private NewsCommentBiz newsCommentBiz;//新闻点赞逻辑层


    @Override
    public PageBean getNewsReplys(NewsReplyQuery newsReplyQuery) throws BusinessException {
        PageBean pageBean = new PageBean() ;
        newsReplyQuery.setOrderByClause("create_time desc");
        int count = newsReplyBiz.selectCount(newsReplyQuery);
        List<NewsReply> newsReplies = newsReplyBiz.selectList(newsReplyQuery);
        pageBean.setTotalCount(count);
        pageBean.setRecordList(newsReplies);
        return pageBean;
    }

    @Override
    public PageBean getNewsReplesPC(NewsReplyQuery newsReplyQuery) throws BusinessException {
        PageBean pageBean = new PageBean() ;
        newsReplyQuery.setOrderByClause("create_time desc");
        newsReplyQuery.setPageNo(0);
        int count =  newsReplyBiz.selectCount(newsReplyQuery);
        List<NewsReply> newsReplies = newsReplyBiz.selectReplyList(newsReplyQuery);
        pageBean.setTotalCount(count);
        pageBean.setRecordList(newsReplies);
        return pageBean;
    }

    @Override
    public NewsReply addNewsReplyPC(NewsReplyModel newsReplyModel) throws BusinessException {
        NewsReply newsReply =newsReplyBiz.addNewsReplyPC(newsReplyModel) ;
        if(null != newsReply){
            newsReplyModel.setReplyType(1);
          newsCommentBiz.updateNewsCommentReplyNum(newsReplyModel) ;
        }
        return newsReply ;
    }

    @Override
    public NewsReply getNewsReplyDetail(NewsReplyQuery newsReplyQuery) throws BusinessException {
        return newsReplyBiz.getNewsReplyDetail(newsReplyQuery);
    }

    @Override
    public boolean deleteNewsReply(NewsReplyModel newsReplyModel) throws BusinessException {
        boolean flag = newsReplyBiz.deleteNewsReply(newsReplyModel);
        if(flag){
            newsReplyModel.setReplyType(0);
           int number = newsCommentBiz.updateNewsCommentReplyNum(newsReplyModel) ;
            return number>0 ;
        }
        return false ;
    }


    @Override
    public boolean updateNewsReplyGood(NewsReplyPraiseModel newsReplyPraiseModel) throws BusinessException {
        int number = 0 ;
        if(newsReplyPraiseModel.getActionType()== NewsReplyConstants.NEWS_REPLY_NOT_PRAISE){//表示取消点赞
            number =  newsReplyPraiseBiz.deleteNewsReplyGood(newsReplyPraiseModel);
        }else{
            number =  newsReplyPraiseBiz.addNewsReplyGood(newsReplyPraiseModel) ;
        }
       number =  newsReplyBiz.updateReplyLikeNumber(newsReplyPraiseModel) ;
        return  number > 0 ;
    }


}
