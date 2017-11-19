package com.zssq.service.impl;

import com.zssq.biz.NewsArchiveBiz;
import com.zssq.biz.NewsCommentBiz;
import com.zssq.biz.NewsInfoContentBiz;
import com.zssq.biz.NewsInfoHistoryBiz;
import com.zssq.constants.NewsConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.dao.pojo.NewsArchive;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.model.*;
import com.zssq.news.service.NewsArchiveService;
import com.zssq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @ClassName: NewsArchiveServiceImpl  
 * @Description: 新闻归档操作service  
 * @author ZKZ  
 * @date 2017年7月24日  
 *
 */
@Service
public class NewsArchiveServiceImpl implements NewsArchiveService {

    @Autowired
    private NewsArchiveBiz newsArchiveBiz;
    @Autowired
    private NewsInfoContentBiz newsInfoContentBiz;
    @Autowired
    private NewsCommentBiz newsCommentBiz;
    @Autowired
    private NewsInfoHistoryBiz newsInfoHistoryBiz;
    
    /**
     * 查询已归档新闻列表
     */
    @Override
    public PageBean getNewsArchives(NewsArchiveQuery newsArchiveQuery) throws BusinessException {
        newsArchiveQuery.setOrderByClause("modify_time desc");
        PageBean pageBean = new PageBean();
        int count = newsArchiveBiz.selectCount(newsArchiveQuery);
        List<NewsArchive> list = newsArchiveBiz.selectList(newsArchiveQuery);
        pageBean.setTotalCount(count);
        pageBean.setRecordList(list);
        return pageBean;
    }

    /**
     * 修改新闻状态
     */
    @Override
    public int addNewsArchives(NewsArchiveModel newsArchiveModel) throws BusinessException {
    	// 添加归档记录
        newsArchiveBiz.addNewsArchive(newsArchiveModel);
        String newsCode = newsArchiveModel.getNewsCodes();
        // 修改新闻状态
        newsInfoContentBiz.updateArchiveNews(newsCode);
        NewsModel newsModel = generateNewsModel(newsCode, newsArchiveModel);
        // 添加操作记录
       return  newsInfoHistoryBiz.addNewsHistory(newsModel);
    }

    /**
     * 
     * @Title: generateNewsModel  
     * @Description: 查询新闻详情
     * @param newsCode
     * @param newsArchiveModel
     * @return: NewsModel    返回类型
     */
    private NewsModel generateNewsModel(String newsCode, NewsArchiveModel newsArchiveModel) {
        NewsModel newsModel = new NewsModel();
        newsModel.setNewsCode(newsCode);
        newsModel.setUserCode(newsArchiveModel.getCreatorCode());
        newsModel.setInfoOperatorOrg(newsArchiveModel.getOrgCode());
        newsModel.setInfoStatus(NewsConstants.NEWS_STATUS_ARCHIVED);
        newsModel.setTenantCode(newsArchiveModel.getTenantCode());
        return newsModel;
    }

    /**
     * 获取新闻详情
     */
	@Override
	public NewsInfoContent generateNewsData(String newsCodes) {
		// 查询所有的新闻
		NewsInfoContent newsInfoContent = newsInfoContentBiz.selectByArchivedCodes(newsCodes);
		if (null != newsInfoContent) {
			// 循环新闻，查询新闻下所有评论和回复
			List<NewsCommentModel> commentModels = getComments(newsInfoContent.getNewsCode());
			newsInfoContent.setNewsCommentModels(commentModels);
		}

		return newsInfoContent;
	}

//    private List<String> generateNewsCodes(String newsCodes) {
//        List<String> newsCodeList = new ArrayList<>();
//        String[] array = newsCodes.split(",");
//        for (int index = 0; index < array.length; index++) {
//            newsCodeList.add(array[index]);
//        }
//        return newsCodeList;
//    }

	/**
	 * 
	 * @Title: getComments  
	 * @Description: 查询新闻下的评论和回复
	 * @param newsCode
	 * @return: List<NewsCommentModel>    返回类型
	 */
    private List<NewsCommentModel> getComments(String newsCode) {
        NewsCommentQuery newsCommentQuery = new NewsCommentQuery();
        newsCommentQuery.setNewsCode(newsCode);
        newsCommentQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
        newsCommentQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
        newsCommentQuery.setOrderByClause("create_time desc");
        return newsCommentBiz.selectCommentsAndReply(newsCommentQuery);
    }

}
