package com.zssq.biz;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.NewsConstants;
import com.zssq.dao.mapper.NewsInfoContentMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.model.NewsCommentModel;
import com.zssq.news.model.NewsModel;
import com.zssq.news.model.NewsPraiseModel;
import com.zssq.news.model.NewsQuery;
import com.zssq.shiro.mysecurity.UUIDHelper;

/**
 * Created by admin on 2017-04-05.
 */
@Service
public class NewsInfoContentBiz {

    @Autowired
    private NewsInfoContentMapper newsInfoContentMapper;


    public int getNewsInfoCount(NewsQuery newsModel) {
        return newsInfoContentMapper.selectCount(newsModel);
    }

    public int getCollectNewsCount(NewsQuery newsQuery) {
        return newsInfoContentMapper.selectCollectNewsCount(newsQuery);
    }

    public List<NewsInfoContent> getNewsInfos(NewsQuery newsModel) {
        return newsInfoContentMapper.selectByQuery(newsModel);
    }


    public List<NewsInfoContent> getUserCollectNews(NewsQuery newsQuery) {
        return newsInfoContentMapper.selectUserCollectNews(newsQuery);
    }

    public List<NewsInfoContent> getNewsInfosPc(NewsQuery newsModel) {
        return newsInfoContentMapper.selectByQueryPc(newsModel);
    }
    public NewsInfoContent getNewsInfoDetail(NewsQuery newsModel) {
        return newsInfoContentMapper.getNewsInfoDetail(newsModel);
    }

    public int addNews(NewsModel newsModel) {
        long time = System.currentTimeMillis();
        NewsInfoContent newsInfoContent = generateNewsInfoContent(newsModel, time, time);
        newsInfoContent = addAttribute(newsInfoContent);

        Integer sortNumber = newsInfoContentMapper.selectMinSort();
        if(null == sortNumber){
            sortNumber= 0 ;
        }
        if(newsModel.getInfoStatus() == NewsConstants.NEWS_STATUS_PUBLISHED){//发布新闻以后--审核通过
            newsInfoContent.setPublishTime(time);
        }
        newsInfoContent.setInfoSort(sortNumber);
        return newsInfoContentMapper.insert(newsInfoContent);
    }


    public Integer updateCommentCount(NewsCommentModel newsCommentModel, int actionType) {
        NewsInfoContent newsInfoContent = newsInfoContentMapper.selectByNewsCode(newsCommentModel.getNewsCode()) ;
        if(actionType==NewsConstants.NEWS_DEL_COMMENT){
            if(newsInfoContent.getInfoCommentCount()>1){
                newsInfoContent.setInfoCommentCount(newsInfoContent.getInfoCommentCount()-1);
            }
        }else{
            newsInfoContent.setInfoCommentCount(newsInfoContent.getInfoCommentCount()+1);
        }
        return  newsInfoContentMapper.updateNews(newsInfoContent) ;
    }

    public int updateNews(NewsModel newsModel) {
        long time = System.currentTimeMillis();
        NewsInfoContent newsInfoContent = generateNewsInfoContent(newsModel, 0, time);
        return newsInfoContentMapper.updateNews(newsInfoContent);
    }

    public int updateArchiveNews(String newsCode) {
        Map<String,Object> maps= new HashedMap() ;
        maps.put("newsCode" ,newsCode) ;
        maps.put("modifyTime", System.currentTimeMillis()) ;
        maps.put("isArchived", NewsConstants.NEWS_IS_ARCHIVED) ;
        maps.put("infoStatus",NewsConstants.NEWS_STATUS_ARCHIVED ) ;
        return newsInfoContentMapper.updateArchiveNews(maps);
    }

    public int updateThirdNews(NewsModel newsModel) {
        NewsQuery newsQuery = new NewsQuery() ;
        newsQuery.setNewsCode(newsModel.getNewsCode());
        NewsInfoContent newsInfoContent = newsInfoContentMapper.getNewsInfoDetail(newsQuery) ;
        newsInfoContent.setIsShield(newsModel.getIsShield());
        newsInfoContent.setInfoOperatorCode(newsModel.getInfoOperatorCode());
        newsInfoContent.setModifyTime(System.currentTimeMillis());
        return newsInfoContentMapper.updateNews(newsInfoContent) ;
    }

    /**
     * 
     * @Title: updateNewsInfosUp  
     * @Description: 新闻置顶
     * @param newsModel
     * @return: int    返回类型
     */
    public int updateNewsInfosUp(NewsModel newsModel) {
        NewsInfoContent newsInfoContent = newsInfoContentMapper.selectByNewsCode(newsModel.getNewsCode());
        newsInfoContent.setIsTop(newsModel.getIsTop());
        long currentTime = System.currentTimeMillis() ;
        newsInfoContent.setModifyTime(currentTime);
        if(newsModel.getIsTop() == NewsConstants.NEWS_IS_TOP){
            newsInfoContent.setInfoSort(0);
            newsInfoContent.setTopTime(currentTime);
        }else{
        	Integer sortNumber = newsInfoContentMapper.selectMinSort();
            if(null == sortNumber){
                sortNumber= 0 ;
            }
            newsInfoContent.setInfoSort(sortNumber);
            newsInfoContent.setTopTime(0L);
        }
        newsInfoContent.setInfoOperatorCode(newsModel.getUserCode());
        return newsInfoContentMapper.updateNews(newsInfoContent);
    }

    public int deleteNewsByCode(NewsModel newsModel) {
        return newsInfoContentMapper.deleteByNewsCode(newsModel);
    }

    public int auditNews(NewsModel newsModel) {
        long time = System.currentTimeMillis();
        NewsInfoContent newsInfoContent = newsInfoContentMapper.selectByNewsCode(newsModel.getNewsCode()) ;
        newsInfoContent.setInfoStatus(newsModel.getInfoStatus());
        newsInfoContent.setModifyTime(time);
        if(newsModel.getInfoStatus() == NewsConstants.NEWS_STATUS_PUBLISHED){//发布新闻以后--审核通过
            newsInfoContent.setPublishTime(time);
        }
        return newsInfoContentMapper.updateNews(newsInfoContent);
    }

    /**
     * 
     * @Title: updateNewsInfoSort  
     * @Description: 新闻上移或者下移接口
     * @param newsModel
     * @throws BusinessException    参数  
     * @return: int    返回类型
     */
    public int updateNewsInfoSort(NewsModel newsModel) throws BusinessException {
        long updateTime = System.currentTimeMillis();
        NewsInfoContent newsInfoContent = newsInfoContentMapper.selectByNewsCode(newsModel.getNewsCode());
        newsInfoContent.setInfoOperatorCode(newsModel.getInfoOperatorCode());
        newsInfoContent.setModifyTime(updateTime);
        newsInfoContent.setOrgLevel(Integer.valueOf(newsModel.getInfoOperatorLevel()));
        newsInfoContent.setOrgCode(newsModel.getInfoOperatorOrg());
        int number = 0;
        NewsInfoContent besideNewsInfoContent = null;
        NewsQuery newsQuery = new NewsQuery() ;
        newsQuery.setLimitStart(0);
        newsQuery.setPageSize(1);
        newsQuery.setIsDelete(0);
        newsQuery.setIsHidden(0);
        newsQuery.setIsTop(0);
        newsQuery.setInfoTitle(newsModel.getInfoTitle());
        newsQuery.setInfoStatus(newsModel.getInfoStatus());
        newsQuery.setStartTime(newsModel.getStartTime());
        newsQuery.setEndTime(newsModel.getEndTime());
        if(null != newsModel.getInfoTitle()){
            newsQuery.setInfoTitle(newsModel.getInfoTitle());
        }
        if(null != newsModel.getStartTime() && newsModel.getStartTime() > 0){
            newsQuery.setStartTime(newsModel.getStartTime());
        }
        if(null != newsModel.getEndTime()&& newsModel.getEndTime() > 0){
            newsQuery.setEndTime(newsModel.getEndTime());
        }
        if (newsModel.getMoveType() == 0) {//上移
            newsQuery.setSortEnd(newsModel.getInfoSort());//表示最大排序号之前的
            newsQuery.setOrderByClause("info_sort desc");
            besideNewsInfoContent = newsInfoContentMapper.selectByNewsSort(newsQuery);
            newsInfoContent.setInfoSort(besideNewsInfoContent.getInfoSort());
        } else {
            newsQuery.setSortStart(newsModel.getInfoSort());//表示最小排序号--之后的，大于这个排序号的第一个
            newsQuery.setOrderByClause("info_sort asc");
            besideNewsInfoContent = newsInfoContentMapper.selectByNewsSort(newsQuery);
            newsInfoContent.setInfoSort(besideNewsInfoContent.getInfoSort());
        }
        number = newsInfoContentMapper.updateNews(newsInfoContent);
        if (null != besideNewsInfoContent) {
            besideNewsInfoContent.setInfoOperatorCode(newsModel.getInfoOperatorCode());
            besideNewsInfoContent.setInfoSort(newsModel.getInfoSort());
//            besideNewsInfoContent.setModifyTime(updateTime);
            besideNewsInfoContent.setOrgLevel(Integer.valueOf(newsModel.getInfoOperatorLevel()));
            besideNewsInfoContent.setOrgCode(newsModel.getInfoOperatorOrg());
            number = newsInfoContentMapper.updateNews(besideNewsInfoContent);
        }
        return number;
    }

    public int updateNewsInfosHidden(NewsModel newsModel) {
        long time = System.currentTimeMillis();
        NewsInfoContent newsInfoContent = generateNewsInfoContent(newsModel, 0, time);
        return newsInfoContentMapper.updateNewsHidden(newsInfoContent);
    }


    public NewsInfoContent selectByArchivedCodes(String newsCode) {
        return newsInfoContentMapper.selectByArchivedCodes(newsCode) ;
    }

    public int updateNewsInfoGoodNum(NewsPraiseModel newsPraiseModel) {
        NewsInfoContent newsInfoContent = newsInfoContentMapper.selectByNewsCode(newsPraiseModel.getNewsCode());
        if(newsPraiseModel.getActionType()==0){
            if(newsInfoContent.getInfoGoodCount()>0){
                newsInfoContent.setInfoGoodCount(newsInfoContent.getInfoGoodCount()-1);
            }
        }else{
            if(newsInfoContent.getInfoGoodCount()==null){
                newsInfoContent.setInfoGoodCount(1);
            }else{
                newsInfoContent.setInfoGoodCount(newsInfoContent.getInfoGoodCount()+1);
            }

        }
        return newsInfoContentMapper.updateNews(newsInfoContent) ;
    }

    private NewsInfoContent generateNewsInfoContent(NewsModel newsModel, long createTime, long updateTime) {
        NewsInfoContent newsInfoContent = new NewsInfoContent();
        newsInfoContent.setInfoOperatorCode(newsModel.getInfoOperatorCode());
        newsInfoContent.setInfoCreatorCode(newsModel.getInfoCreatorCode());
        newsInfoContent.setOrgCode(newsModel.getInfoOperatorOrg());
        newsInfoContent.setOrgLevel(Integer.valueOf(newsModel.getInfoOperatorLevel()));
        newsInfoContent.setInfoTitle(newsModel.getInfoTitle());
        newsInfoContent.setInfoContentText(newsModel.getInfoContentText());
        newsInfoContent.setInfoContentHtml(newsModel.getInfoContentHtml());
        newsInfoContent.setInfoStatus(newsModel.getInfoStatus());
        newsInfoContent.setNewsCode(newsModel.getNewsCode());
        newsInfoContent.setTenantCode(newsModel.getTenantCode());
        newsInfoContent.setIsHidden(newsModel.getIsHidden());
        newsInfoContent.setFileUrl(newsModel.getFileUrl());
        if (createTime > 0) {
            newsInfoContent.setCreateTime(createTime);
        }
        if (updateTime > 0) {
            newsInfoContent.setInfoUpdateTime(updateTime);
            newsInfoContent.setModifyTime(updateTime);
        }
        newsInfoContent.setTopTime(0l);
        return newsInfoContent;
    }


    private NewsInfoContent addAttribute(NewsInfoContent newsInfoContent) {
        newsInfoContent.setInfoSort(0);
        newsInfoContent.setIsTop(0);
        newsInfoContent.setIsDelete(0);
        newsInfoContent.setIsHidden(0);
        newsInfoContent.setIsShield(0);
        newsInfoContent.setInfoCommentCount(0);
        newsInfoContent.setInfoGoodCount(0);
        return newsInfoContent ;
    }

    public static void main(String[] args){
        System.out.println(UUIDHelper.getUUID());

    }

}