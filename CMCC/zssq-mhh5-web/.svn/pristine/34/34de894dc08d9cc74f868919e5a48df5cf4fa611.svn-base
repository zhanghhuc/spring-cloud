package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsPraiseVo
 * @Description: 新闻点赞参数类
 * @date 2017年04月11日
 */
public class NewsCommentPraiseVo implements Serializable {

    private static final long serialVersionUID = -1606166264275829882L;
    @NotBlank(message = "{empty.message}")
    private String userCode ;
    @NotBlank(message = "{empty.message}")
    private String token ;
    @NotBlank(message = "{empty.message}")
    private String newsCode ;
    @NotBlank(message = "{empty.message}")
    private String commentCode ;//新闻评论编码
    private String commentUserCode ;//评论新闻的用户编码
    @IntType(expression = ">=0")
    private String actionType;


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

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode;
    }

    public String getCommentUserCode() {
        return commentUserCode;
    }

    public void setCommentUserCode(String commentUserCode) {
        this.commentUserCode = commentUserCode;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
