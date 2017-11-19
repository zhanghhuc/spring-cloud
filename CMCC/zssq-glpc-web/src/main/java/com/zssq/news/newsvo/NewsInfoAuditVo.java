package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 新闻审核参数类
 * @date 2017年04月02日
 */
public class NewsInfoAuditVo extends NewsBaseVo {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
    private String infoCreatorCode ;
    @NotBlank(message = "{empty.message}")
    private String infoTitle ;
    @IntType(expression = ">=0")
    private String infoStatus ;

    private String infoContentText ;
    private String fileUrl ;
    private String infoRemark ;
    

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
