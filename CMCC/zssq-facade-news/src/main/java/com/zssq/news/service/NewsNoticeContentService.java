package com.zssq.news.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsNoticeContent;
import com.zssq.news.dao.pojo.NewsNoticeHistory;
import com.zssq.news.model.NoticeHistoryQuery;
import com.zssq.news.model.NoticeModel;
import com.zssq.news.model.NoticeQuery;
import com.zssq.utils.PageBean;

import java.util.List;

public interface NewsNoticeContentService {
	/**
	 * 公告列表接口
	 * @param noticeQuery
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNotices(NoticeQuery noticeQuery) throws BusinessException;
	/**
	 * 公告列表接口
	 * @param noticeQuery
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getNoticesPc(NoticeQuery noticeQuery) throws BusinessException;
	/**
	 * 公告列表接口
	 * @param noticeQuery
	 * @return
	 * @throws BusinessException
	 */
	public NewsNoticeContent getNoticeDetail(NoticeQuery noticeQuery) throws BusinessException;

	/**
	 * 公告新增接口
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNoticeInfo(NoticeModel noticeModel) throws BusinessException ;

	/**
	 * 公告修改接口
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNotice(NoticeModel noticeModel) throws BusinessException ;

	/**
	 * 公告删除接口
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean deleteNotice(NoticeModel  noticeModel) throws BusinessException ;

	/**
	 * 公告置顶
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsNoticeUp(NoticeModel noticeModel) throws BusinessException ;
	/**
	 * 公告审核接口
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean addNoticeInfoHistory(NoticeModel noticeModel) throws BusinessException ;
	/**
	 * 公告审核接口
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNoticeMove(NoticeModel noticeModel) throws BusinessException ;

	/**
	 * 公告隐藏或者取消隐藏
	 * @param noticeModel
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateNewsNoticeHidden(NoticeModel noticeModel);

	/**
	 * 公告历程列表接口
	 * @param noticeHistoryQuery
	 * @return
	 * @throws BusinessException
	 */
	public List<NewsNoticeHistory> getNoticeHistorys(NoticeHistoryQuery noticeHistoryQuery) throws BusinessException;

	/**
	 * 公告撤销接口
	 * @param noticeModel
	 * @return
	 */
	public boolean updateNewsNoticeRepeal(NoticeModel noticeModel);
}
