package com.zssq.news.noticevo;

import com.zssq.annotation.validation.IntType;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 新闻审计参数类
 * @date 2017年04月02日
 */
public class NewsNoticeHiddenVo extends NoticeBaseVo {

	private static final long serialVersionUID = 1L;
	
	@IntType(expression = ">=0")
    private String isHidden;
	

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }
}
