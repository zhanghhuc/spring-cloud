package com.zssq.biz;

import com.zssq.constants.NewsConstants;
import com.zssq.dao.mapper.NewsCollectMapper;
import com.zssq.news.dao.pojo.NewsCollect;
import com.zssq.news.model.NewsCollectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsCollectBiz {

    @Autowired
    private NewsCollectMapper newsCollectMapper;


    public boolean addNewsInfoCollect(NewsCollectModel newsCollectModel) {
        int number = 0 ;
        if(newsCollectModel.getActionType() == NewsConstants.NEWS_NOT_COLLECT){
            number = newsCollectMapper.deleteNewsCollect(newsCollectModel) ;
        }else {
            long currentTime = System.currentTimeMillis();
            NewsCollect newCollect = generateNewsCollect(newsCollectModel, currentTime, currentTime);
            number = newsCollectMapper.insertSelective(newCollect);
        }
        return number >0;
    }


    public NewsCollect selectByCollectUser(String userCode, String newsCode) {
        return newsCollectMapper.selectByCollectUser(userCode, newsCode);
    }

    private NewsCollect generateNewsCollect(NewsCollectModel newsCollectModel, long createTime, long modifyTime) {
        NewsCollect newCollect = new NewsCollect() ;
        newCollect.setInfoCollectCode(newsCollectModel.getCollectCode());
        newCollect.setCollectPeopleCode(newsCollectModel.getUserCode());
        newCollect.setNewsCode(newsCollectModel.getNewsCode());
        newCollect.setOrgCode(newsCollectModel.getOrgCode());
        newCollect.setOrgLevel(newsCollectModel.getOrgLevel());
        newCollect.setTenantCode(newsCollectModel.getTenantCode());
        if(modifyTime > 0){
            newCollect.setCreateTime(createTime);
        }
        newCollect.setModifyTime(modifyTime);
        return newCollect ;
    }

}
