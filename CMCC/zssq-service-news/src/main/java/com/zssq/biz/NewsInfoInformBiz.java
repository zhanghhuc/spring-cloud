package com.zssq.biz;

import com.zssq.dao.mapper.NewsInfoInformMapper;
import com.zssq.news.dao.pojo.NewsInfoInform;
import com.zssq.news.model.NewsInfoInformModel;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsInfoInformBiz {

    @Autowired
    private NewsInfoInformMapper newsInfoInformMapper;

    public boolean addNewsInfoReport(NewsInfoInformModel newsInfoInformModel) {
        long currentTime = System.currentTimeMillis() ;
        NewsInfoInform record = generateNewsCollect(newsInfoInformModel,currentTime,currentTime) ;
        int number = newsInfoInformMapper.insertSelective(record);
        return number >0;
    }

    private NewsInfoInform generateNewsCollect(NewsInfoInformModel newsInfoInformModel, long createTime, long modifyTime) {
        NewsInfoInform newsInfoInform = new NewsInfoInform() ;
        newsInfoInform.setInformCode(UUIDHelper.getUUID());
        newsInfoInform.setNewsCode(newsInfoInformModel.getNewsCode());
        newsInfoInform.setPublishUserCode(newsInfoInformModel.getInfoCreatorCode());
        newsInfoInform.setInformType(newsInfoInformModel.getInformType());
        newsInfoInform.setInformPeopleCode(newsInfoInformModel.getInformPeopleCode());
        newsInfoInform.setOrgCode(newsInfoInformModel.getOrgCode());
        newsInfoInform.setOrgLevel(newsInfoInformModel.getOrgLevel());
        newsInfoInform.setTenantCode(newsInfoInformModel.getTenantCode());
        newsInfoInform.setProcessorUserCode(newsInfoInformModel.getProcessorUserCode());
        if(modifyTime > 0){
            newsInfoInform.setCreateTime(createTime);
        }
        newsInfoInform.setModifyTime(modifyTime);
        if(null != newsInfoInformModel.getRemark() && !"".equals(newsInfoInformModel.getRemark())){
            newsInfoInform.setRemark(newsInfoInformModel.getRemark());
        }
        return newsInfoInform ;
    }

}
