package com.zssq.biz;

import com.zssq.dao.mapper.NewsCommentPraiseMapper;
import com.zssq.news.dao.pojo.NewsCommentPraise;
import com.zssq.news.model.NewsCommentPraiseModel;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsCommentPraiseBiz {

    @Autowired
    private NewsCommentPraiseMapper newsCommentPraiseMapper;

    /**
     * 新闻评论点赞
     * @param newsCommentPraiseModel
     * @return
     */
    public boolean addNewsInfoCommentGood(NewsCommentPraiseModel newsCommentPraiseModel) {
        long currentTime = System.currentTimeMillis() ;
        NewsCommentPraise newsCommentPraise = generateNewsCommentPraise(newsCommentPraiseModel,currentTime,currentTime) ;

        int number = newsCommentPraiseMapper.insertSelective(newsCommentPraise) ;
        return number >0 ;
    }


    public int deleteNewsCommentGood(NewsCommentPraiseModel newsCommentPraiseModel) {
        return newsCommentPraiseMapper.deleteNewsCommentGood(newsCommentPraiseModel);
    }


    public NewsCommentPraise selectByPraiseUser(String userCode, String commentCode) {
        return newsCommentPraiseMapper.selectByPraiseUser(userCode, commentCode) ;
    }
    /**
     *
     * @param newsCommentPraiseModel
     * @param createTime
     * @param modifyTime
     * @return
     */
    private NewsCommentPraise generateNewsCommentPraise(NewsCommentPraiseModel newsCommentPraiseModel, long createTime, long modifyTime ) {
        NewsCommentPraise newsCommentPraise = new NewsCommentPraise() ;
        newsCommentPraise.setNewsPraiseCode(UUIDHelper.getUUID());
        newsCommentPraise.setNewsCode(newsCommentPraiseModel.getNewsCode());
        newsCommentPraise.setCommentCode(newsCommentPraiseModel.getCommentCode());
        newsCommentPraise.setPraisePeopleCode(newsCommentPraiseModel.getPraisePeopleCode());
        newsCommentPraise.setPraisePeopleLevel(newsCommentPraiseModel.getRevertPeopleLevel());
        newsCommentPraise.setPraisePeopleOrg(newsCommentPraiseModel.getRevertPeopleOrg());
        newsCommentPraise.setRevertPeopleCode(newsCommentPraiseModel.getRevertPeopleCode());
        newsCommentPraise.setRevertPeopleLevel(newsCommentPraiseModel.getRevertPeopleLevel());
        newsCommentPraise.setRevertPeopleOrg(newsCommentPraiseModel.getRevertPeopleOrg());
        newsCommentPraise.setCreateTime(createTime);
        newsCommentPraise.setModifyTime(modifyTime);
        newsCommentPraise.setTenantCode(newsCommentPraiseModel.getTenantCode());
        newsCommentPraise.setOrgCode(newsCommentPraiseModel.getOrgCode());
        newsCommentPraise.setOrgLevel(newsCommentPraiseModel.getOrgLevel());
        return newsCommentPraise ;
    }

}
