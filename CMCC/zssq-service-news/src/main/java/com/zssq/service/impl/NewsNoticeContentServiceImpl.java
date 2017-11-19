package com.zssq.service.impl;

import com.zssq.biz.NewsNoticeContentBiz;
import com.zssq.biz.NewsNoticeHistoryBiz;
import com.zssq.constants.NewsConstants;
import com.zssq.constants.NewsNoticeConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsNoticeContent;
import com.zssq.news.dao.pojo.NewsNoticeHistory;
import com.zssq.news.model.NoticeHistoryQuery;
import com.zssq.news.model.NoticeModel;
import com.zssq.news.model.NoticeQuery;
import com.zssq.news.service.NewsNoticeContentService;
import com.zssq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsNoticeContentServiceImpl implements NewsNoticeContentService {

    @Autowired
    private NewsNoticeContentBiz newsNoticeContentBiz;

    @Autowired
    private NewsNoticeHistoryBiz newsNoticeHistoryBiz;

    /**
     * 公告列表接口
     */
    @Override
    public PageBean getNotices(NoticeQuery noticeQuery) throws BusinessException {
        noticeQuery.setOrderByClause("is_top desc ,notice_update_time desc ,notice_sort asc ,modify_time desc");
        if(null == noticeQuery.getNoticeStatus() || (null != noticeQuery.getSelfFlag() && noticeQuery.getSelfFlag()==1) || (null != noticeQuery
                .getNoticeStatus() && noticeQuery.getNoticeStatus() != NewsNoticeConstants.NOTICE_STATUS_PUBLISHED)){
            noticeQuery.setOrderByClause(" modify_time desc");
        }
        PageBean pageBean = new PageBean() ;
        int totalCount = newsNoticeContentBiz.getNoticesCount(noticeQuery) ;
        List<NewsNoticeContent> list = newsNoticeContentBiz.getNotices(noticeQuery) ;
        pageBean.setTotalCount(totalCount);
        pageBean.setRecordList(list);
        noticeQuery.setIsTop(1);
        int topCount = newsNoticeContentBiz.getNoticesCount(noticeQuery) ;//查询置顶总数
        pageBean.setNumPerPage(topCount);
        return pageBean;
    }
    
    /**
     * 公告列表接口
     */
    @Override
    public PageBean getNoticesPc(NoticeQuery noticeQuery) throws BusinessException {
        noticeQuery.setOrderByClause("is_top desc ,notice_update_time desc ,notice_sort asc ,modify_time desc");
        if((null != noticeQuery.getSelfFlag() && noticeQuery.getSelfFlag()==1) || (null != noticeQuery.getNoticeStatus() &&
                noticeQuery.getNoticeStatus() != NewsNoticeConstants.NOTICE_STATUS_PUBLISHED)){
            noticeQuery.setOrderByClause(" modify_time desc");
        }
        PageBean pageBean = new PageBean() ;
        int totalCount = newsNoticeContentBiz.getNoticesCount(noticeQuery) ;
        List<NewsNoticeContent> list = newsNoticeContentBiz.getNotices(noticeQuery) ;
        pageBean.setTotalCount(totalCount);
        pageBean.setRecordList(list);
        return pageBean;
    }
    @Override
    public NewsNoticeContent getNoticeDetail(NoticeQuery noticeQuery) throws BusinessException {
        return newsNoticeContentBiz.getNoticeDetail(noticeQuery);
    }

    @Override
    public boolean addNoticeInfo(NoticeModel noticeModel) throws BusinessException {
        int number =  newsNoticeContentBiz.addNoticeInfo(noticeModel);
        number = newsNoticeHistoryBiz.addNoticeHistory(noticeModel);
        return number >0 ;
    }

    @Override
    public boolean updateNotice(NoticeModel noticeModel) throws BusinessException {
        int number =  newsNoticeContentBiz.updateNotice(noticeModel);
        if(NewsNoticeConstants.NOTICE_STATUS_WAITING_AUDIT == noticeModel.getNoticeStatus() ){//提交审核审核，添加到公告历程
            number = newsNoticeHistoryBiz.addNoticeHistory(noticeModel);
        }
        return number >0 ;
    }

    @Override
    public boolean deleteNotice(NoticeModel  noticeModel) throws BusinessException {
        int number =  newsNoticeContentBiz.deleteNotice(noticeModel);
        number = newsNoticeHistoryBiz.addNoticeHistory(noticeModel);
        return number >0 ;
    }

    /**
     * 公告置顶
     */
    @Override
    public boolean updateNewsNoticeUp(NoticeModel noticeModel) throws BusinessException {
        int number =  newsNoticeContentBiz.updateNewsNoticeUp(noticeModel);
        return number >0 ;
    }

    @Override
    public boolean addNoticeInfoHistory(NoticeModel noticeHistoryModel) throws BusinessException {
        int number = newsNoticeContentBiz.auditNotice(noticeHistoryModel) ;
        number  = newsNoticeHistoryBiz.addNoticeHistory(noticeHistoryModel);
        return number > 0  ;
    }

    /**
     * 公告审核接口
     */
    @Override
    public boolean updateNoticeMove(NoticeModel noticeModel) throws BusinessException {
        int number =  newsNoticeContentBiz.updateNoticeMove(noticeModel);
        return number >0 ;
    }

    /**
     * 公告隐藏或者取消隐藏
     */
    @Override
    public boolean updateNewsNoticeHidden(NoticeModel noticeModel) {
        int cancelHidden = NewsNoticeConstants.NOTICE_IS_HIDDEN ;
        if(noticeModel.getIsHidden() == NewsNoticeConstants.NOTICE_IS_HIDDEN ){
            noticeModel.setNoticeStatus(NewsNoticeConstants.NOTICE_STATUS_HIDDENED);
        }else{
            cancelHidden = NewsNoticeConstants.NOTICE_CANCEL_HIDDENED ;
            noticeModel.setNoticeStatus(NewsNoticeConstants.NOTICE_STATUS_PUBLISHED);
        }
        int number =  newsNoticeContentBiz.updateNewsNoticeHidden(noticeModel);
        noticeModel.setNoticeStatus(cancelHidden);
        newsNoticeHistoryBiz.addNoticeHistory(noticeModel) ;
        return number >0 ;
    }

    /**
     * 公告历程列表接口
     */
    @Override
    public List<NewsNoticeHistory> getNoticeHistorys(NoticeHistoryQuery noticeHistoryQuery) throws BusinessException {
        return newsNoticeHistoryBiz.getNoticeHistorys(noticeHistoryQuery);
    }

    /**
     * 公告撤销接口
     */
    @Override
    public boolean updateNewsNoticeRepeal(NoticeModel noticeModel) {
        int number =  newsNoticeContentBiz.updateNewsNoticeRepeal(noticeModel);
        newsNoticeHistoryBiz.addNoticeHistory(noticeModel) ;
        return number >0 ;
    }


}
