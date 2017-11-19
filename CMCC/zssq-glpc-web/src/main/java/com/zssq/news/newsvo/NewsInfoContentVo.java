package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 新闻增删改参数类
 * @date 2017年04月02日
 */
public class NewsInfoContentVo  implements Serializable {
	
    private static final long serialVersionUID = -1606166264275829882L;

    @NotBlank(message = "{empty.message}")
    private String userCode ;
    @NotBlank(message = "{empty.message}")
    private String token ;
    @NotBlank(message = "{empty.message}")
    private String infoTitle ;
    @IntType(expression = ">=0")
    private String infoStatus ;

    private String infoOperatorCode ;
    private String infoCreatorCode ;
    private String fileUrl ;
    private String infoContentText ;
    private String infoContentHtml ;
    private String infoRemark ;
    private String newsCode ;
    

    public String getInfoOperatorCode() {
        return infoOperatorCode;
    }

    public void setInfoOperatorCode(String infoOperatorCode) {
        this.infoOperatorCode = infoOperatorCode;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoContentText() {
        return infoContentText;
    }

    public void setInfoContentText(String infoContentText) {
        this.infoContentText = infoContentText;
    }

    public String getInfoContentHtml() {
        return infoContentHtml;
    }

    public void setInfoContentHtml(String infoContentHtml) {
        this.infoContentHtml = infoContentHtml;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoRemark() {
        return infoRemark;
    }

    public void setInfoRemark(String infoRemark) {
        this.infoRemark = infoRemark;
    }

    public String getInfoCreatorCode() {
        return infoCreatorCode;
    }

    public void setInfoCreatorCode(String infoCreatorCode) {
        this.infoCreatorCode = infoCreatorCode;
    }

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
