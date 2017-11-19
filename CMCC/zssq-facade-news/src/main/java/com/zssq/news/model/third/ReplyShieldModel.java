package com.zssq.news.model.third;

import com.zssq.news.model.BaseModel;

/**
 * @author SharlaCheung
 * @ClassName: NewsShieldModel
 * @Description: 新闻屏蔽参数类
 * @date 2017年04月22日
 */
public class ReplyShieldModel extends BaseModel {

    private  String replyCode ;

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }
}
