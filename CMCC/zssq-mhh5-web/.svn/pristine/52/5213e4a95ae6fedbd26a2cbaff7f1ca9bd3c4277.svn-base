package com.zssq.news.newsvo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsCommentVo
 * @Description: 新闻评论增删改参数类
 * @date 2017年04月11日
 */
public class NewsCommentVo implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;
    @NotBlank(message = "{empty.message}")
    private String userCode ;
    @NotBlank(message = "{empty.message}")
    private String token ;
    @NotBlank(message = "{empty.message}")
    private String newsCode ;
    private String commentContent ;
    private String revertPeopleCode ;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getRevertPeopleCode() {
        return revertPeopleCode;
    }

    public void setRevertPeopleCode(String revertPeopleCode) {
        this.revertPeopleCode = revertPeopleCode;
    }

}
