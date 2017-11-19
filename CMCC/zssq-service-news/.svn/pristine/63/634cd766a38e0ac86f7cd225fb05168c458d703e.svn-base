package com.zssq.service.impl;

import com.zssq.biz.NewsCommentBiz;
import com.zssq.biz.NewsInfoContentBiz;
import com.zssq.biz.NewsInfoHistoryBiz;
import com.zssq.biz.NewsReplyBiz;
import com.zssq.constants.NewsConstants;
import com.zssq.constants.NewsNoticeThirdConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.model.*;
import com.zssq.news.model.third.ShieldModel;
import com.zssq.news.service.NewsThridService;
import com.zssq.pojo.RssReturnPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsThridServiceImpl implements NewsThridService {

    @Autowired
    private NewsInfoContentBiz newsInfoContentBiz;

    @Autowired
    private NewsCommentBiz newsCommentBiz;

    @Autowired
    private NewsReplyBiz newsReplyBiz;

    @Autowired
    private NewsInfoHistoryBiz newsInfoHistoryBiz;

    @Override
    public boolean modifyShield(ShieldModel shieldModel) throws BusinessException {
        if(shieldModel.getActionType() == NewsNoticeThirdConstants.SHIELD_THIRD_NEWS){
            return modifyNewsShield(shieldModel) ;
        }else if(shieldModel.getActionType() == NewsNoticeThirdConstants.SHIELD_THIRD_COMMENT){
            return modifyNewsCommentShield(shieldModel) ;
        }else if(shieldModel.getActionType() == NewsNoticeThirdConstants.SHIELD_THIRD_REPLY){
            return modifyReplyShield(shieldModel) ;
        }
        return false;
    }

    @Override
    public boolean modifyNewsShield(ShieldModel shieldModel) throws BusinessException {
        NewsModel newsModel = new NewsModel() ;
        newsModel.setNewsCode(shieldModel.getActionCode());
        newsModel.setIsShield(shieldModel.getIsShield());
        newsModel.setUserCode(shieldModel.getUserCode());
        return newsInfoContentBiz.updateThirdNews(newsModel) > 0;
    }

    @Override
    public boolean modifyNewsCommentShield(ShieldModel shieldModel) throws BusinessException {
        NewsCommentModel newsCommentModel = new NewsCommentModel() ;
        newsCommentModel.setCommentCode(shieldModel.getActionCode());
        newsCommentModel.setIsShield(shieldModel.getIsShield());
        newsCommentModel.setUserCode(shieldModel.getUserCode());
        newsCommentModel.setModifyTime(System.currentTimeMillis());
        return newsCommentBiz.updateThirdNewsComment(newsCommentModel) > 0;
    }

    @Override
    public boolean modifyReplyShield(ShieldModel shieldModel) {
        NewsReplyModel newsReplyModel = new NewsReplyModel() ;
        newsReplyModel.setReplyCode(shieldModel.getActionCode());
        newsReplyModel.setIsShield(shieldModel.getIsShield());
        newsReplyModel.setUserCode(shieldModel.getUserCode());
        newsReplyModel.setModifyTime(System.currentTimeMillis());
        return newsReplyBiz.updateThirdNewsReply(newsReplyModel) > 0;
    }


    @Override
    public List<RssReturnPo> getNewsInfosRss(NewsRssQuery newsRssQuery) throws BusinessException {
        List<RssReturnPo> rssReturnPos = new ArrayList<>() ;
        NewsQuery newsQuery= new NewsQuery() ;
        newsQuery.setOrderByClause("create_time desc");
        newsQuery.setPageSize(newsRssQuery.getPageSize());
        newsQuery.setOrgCode(newsRssQuery.getOrgCode());
        newsQuery.setLimitStart(newsRssQuery.getLimitStart());
        newsQuery.setIsHidden(NewsConstants.NEWS_NO_HIDDEN);
        newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
        newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
        List<NewsInfoContent>  list = newsInfoContentBiz.getNewsInfos(newsQuery) ;
        if(null != list && list.size()>0){
            RssReturnPo rssReturnPo = null ;
            for(NewsInfoContent newsInfoContent : list){
                rssReturnPo = new RssReturnPo() ;
                rssReturnPo.setTitle(newsInfoContent.getInfoTitle());
                rssReturnPo.setAuthorCode(newsInfoContent.getInfoCreatorCode());
                rssReturnPo.setCreateTime(newsInfoContent.getCreateTime());
                rssReturnPo.setObjectCode(newsInfoContent.getNewsCode());
                rssReturnPo.setDescription(newsInfoContent.getInfoContentText());
                rssReturnPos.add(rssReturnPo) ;
            }
        }
        return rssReturnPos;
    }

}
