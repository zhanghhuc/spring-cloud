package com.zssq.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.NewsArchiveMapper;
import com.zssq.news.dao.pojo.NewsArchive;
import com.zssq.news.model.NewsArchiveModel;
import com.zssq.news.model.NewsArchiveQuery;
import com.zssq.shiro.mysecurity.UUIDHelper;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsArchiveBiz {

    @Autowired
    private NewsArchiveMapper newsArchiveMapper;

    /**
     * 
     * @Title: addNewsArchive  
     * @Description: 添加归档记录
     * @param newsArchiveModel
     * @return: int    返回类型
     */
    public int addNewsArchive(NewsArchiveModel newsArchiveModel) {
        long currentTime = System.currentTimeMillis() ;
        NewsArchive record = generateNewsInfoComment(newsArchiveModel,currentTime,currentTime);
        return  newsArchiveMapper.insertSelective(record) ;
    }

    /**
     * 
     * @Title: selectCount  
     * @Description: 查询已归档新闻个数
     * @param newsArchiveQuery
     * @return: Integer    返回类型
     */
    public Integer selectCount(NewsArchiveQuery newsArchiveQuery) {
        return  newsArchiveMapper.selectCount(newsArchiveQuery) ;
    }

    /**
     * 
     * @Title: selectList  
     * @Description: 查询已归档新闻列表
     * @param newsArchiveQuery
     * @return: List<NewsArchive>    返回类型
     */
    public List<NewsArchive> selectList(NewsArchiveQuery newsArchiveQuery) {
        return  newsArchiveMapper.selectList(newsArchiveQuery) ;
    }

    /**
     * 
     * @Title: generateNewsInfoComment  
     * @Description: 拼接归档记录信息
     * @param newsArchiveModel
     * @param createTime
     * @param modifyTime
     * @return: NewsArchive    返回类型
     */
    private NewsArchive generateNewsInfoComment(NewsArchiveModel newsArchiveModel,long createTime,long modifyTime) {
        NewsArchive record = new NewsArchive() ;
        record.setNewsCode(newsArchiveModel.getNewsCodes());
        record.setTenantCode(newsArchiveModel.getTenantCode());
        record.setCreatorCode(newsArchiveModel.getCreatorCode());
        record.setOrgCode(newsArchiveModel.getOrgCode());
        record.setArchiveCode(UUIDHelper.getUUID());
        record.setArchiveTitle(newsArchiveModel.getArchiveTitle());
        record.setArchiveName(newsArchiveModel.getArchiveName());
        record.setArchiveUrl(newsArchiveModel.getArchiveUrl());
        record.setIsDelete(newsArchiveModel.getIsShield());
        record.setIsShield(newsArchiveModel.getIsDelete());
        record.setRemark(newsArchiveModel.getRemark());
        if(modifyTime > 0){
            record.setCreateTime(createTime);
        }
        record.setModifyTime(modifyTime);
        return record ;
    }

}
