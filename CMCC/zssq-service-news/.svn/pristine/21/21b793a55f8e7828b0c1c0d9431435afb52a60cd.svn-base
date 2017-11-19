package com.zssq.biz;

import com.zssq.dao.mapper.NewsPraiseMapper;
import com.zssq.news.dao.pojo.NewsPraise;
import com.zssq.news.model.NewsPraiseModel;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsPraiseBiz {

    @Autowired
    private NewsPraiseMapper newsPraiseMapper;

    /**
     * 新闻点赞
     * @param newsPraiseModel
     * @return
     */
    public boolean addNewsInfoGood(NewsPraiseModel newsPraiseModel) {
        long currentTime = System.currentTimeMillis() ;
        NewsPraise newsPraise = generateNewsPraise(newsPraiseModel,currentTime,currentTime) ;
        int number = newsPraiseMapper.insertSelective(newsPraise) ;
        return number >0 ;
    }

    public int deleteNewsInfoGood(NewsPraiseModel newsPraiseModel) {
        return newsPraiseMapper.deleteNewsInfoGood(newsPraiseModel) ;
    }

    public NewsPraise selectPraiseByPraiseUser(String userCode, String newsCode) {
        return  newsPraiseMapper.selectPraiseByPraiseUser(userCode,newsCode) ;
    }

    /**
     *
     * @param newsPraiseModel
     * @param createTime
     * @param modifyTime
     * @return
     */
    private NewsPraise generateNewsPraise(NewsPraiseModel newsPraiseModel, long createTime, long modifyTime ) {
        NewsPraise newsPraise = new NewsPraise() ;
        newsPraise.setNewsPraiseCode(UUIDHelper.getUUID());
        newsPraise.setNewsCode(newsPraiseModel.getNewsCode());
        newsPraise.setPraisePeopleCode(newsPraiseModel.getPraisePeopleCode());
        newsPraise.setPraisePeopleLevel(newsPraiseModel.getRevertPeopleLevel());
        newsPraise.setPraisePeopleOrg(newsPraiseModel.getRevertPeopleOrg());
        newsPraise.setRevertPeopleCode(newsPraiseModel.getRevertPeopleCode());
        newsPraise.setRevertPeopleLevel(newsPraiseModel.getRevertPeopleLevel());
        newsPraise.setRevertPeopleOrg(newsPraiseModel.getRevertPeopleOrg());
        newsPraise.setCreateTime(createTime);
        newsPraise.setModifyTime(modifyTime);
        newsPraise.setTenantCode(newsPraiseModel.getTenantCode());
        newsPraise.setOrgCode(newsPraiseModel.getOrgCode());
        newsPraise.setOrgLevel(newsPraiseModel.getOrgLevel());
        return newsPraise ;
    }

}
