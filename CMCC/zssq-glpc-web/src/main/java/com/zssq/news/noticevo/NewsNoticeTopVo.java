package com.zssq.news.noticevo;

import com.zssq.annotation.validation.IntType;

/**
 * @author SharlaCheung
 * @ClassName: NewsNoticeTopVo
 * @Description: 公告置顶参数类
 * @date 2017年04月14日
 */
public class NewsNoticeTopVo extends NoticeBaseVo {

	private static final long serialVersionUID = 1L;
	
	@IntType(expression = ">=0")
    private String isTop;

	
    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }
}
