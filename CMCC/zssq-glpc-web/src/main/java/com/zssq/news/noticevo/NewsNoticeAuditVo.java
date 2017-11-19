package com.zssq.news.noticevo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 公告审计参数类
 * @date 2017年04月14日
 */
public class NewsNoticeAuditVo extends NoticeBaseVo {

	private static final long serialVersionUID = 1L;
	
	@IntType(expression = ">=0")
    private String noticeStatus ;
    @NotBlank(message = "{empty.message}")
    private String noticeCreatorCode ;

    private String noticeRemark ;


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

    public String getNoticeCreatorCode() {
        return noticeCreatorCode;
    }

    public void setNoticeCreatorCode(String noticeCreatorCode) {
        this.noticeCreatorCode = noticeCreatorCode;
    }
}
