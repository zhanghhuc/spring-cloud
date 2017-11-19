package com.zssq.news.model.third;

import com.zssq.news.model.BaseModel;

/**
 * @author SharlaCheung
 * @ClassName: NewsShieldModel
 * @Description: 新闻屏蔽参数类
 * @date 2017年04月22日
 */
public class NewsCommentShieldModel extends BaseModel {

    private  String newsCommentCode ;

    public String getNewsCommentCode() {
        return newsCommentCode;
    }

    public void setNewsCommentCode(String newsCommentCode) {
        this.newsCommentCode = newsCommentCode;
    }
}
