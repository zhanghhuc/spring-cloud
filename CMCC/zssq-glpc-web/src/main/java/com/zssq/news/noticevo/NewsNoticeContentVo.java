package com.zssq.news.noticevo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsNoticeContentVo
 * @Description: 公告增删改参数类
 * @date 2017年04月14日
 */
public class NewsNoticeContentVo  implements Serializable {
	
    private static final long serialVersionUID = -1606166264275829882L;

    @NotBlank(message = "{empty.message}")
    private String userCode ;
    @NotBlank(message = "{empty.message}")
    private String token ;
    private String noticeOperatorCode ;
    private String noticeCreatorCode ;
    @NotBlank(message = "{empty.message}")
    private String noticeTitle ;
    private String noticeContentText ;
    @NotBlank(message = "{empty.message}")
    private String noticeContentHtml ;
    @IntType(expression = ">=0")
    private String noticeStatus ;
    private String noticeRemark ;
    private String noticeCode ;
    private String noticeFileUrl ;

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

    public String getNoticeOperatorCode() {
        return noticeOperatorCode;
    }

    public void setNoticeOperatorCode(String noticeOperatorCode) {
        this.noticeOperatorCode = noticeOperatorCode;
    }

    public String getNoticeCreatorCode() {
        return noticeCreatorCode;
    }

    public void setNoticeCreatorCode(String noticeCreatorCode) {
        this.noticeCreatorCode = noticeCreatorCode;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContentText() {
        return noticeContentText;
    }

    public void setNoticeContentText(String noticeContentText) {
        this.noticeContentText = noticeContentText;
    }

    public String getNoticeContentHtml() {
        return noticeContentHtml;
    }

    public void setNoticeContentHtml(String noticeContentHtml) {
        this.noticeContentHtml = noticeContentHtml;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getNoticeRemark() {
        return noticeRemark;
    }

    public void setNoticeRemark(String noticeRemark) {
        this.noticeRemark = noticeRemark;
    }

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeFileUrl() {
        return noticeFileUrl;
    }

    public void setNoticeFileUrl(String noticeFileUrl) {
        this.noticeFileUrl = noticeFileUrl;
    }
}
