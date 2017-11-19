package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 新闻审计参数类
 * @date 2017年04月02日
 */
public class NewsInfoMoveVo extends NewsBaseVo {

	private static final long serialVersionUID = 1L;
	
	@IntType(expression = ">=0")
    private String moveType;
    @IntType(expression = "<=0")
    private String infoSort;

    private String infoStatus;
    private String infoTitle;
    private String startTime;
    private String endTime;


    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInfoSort() {
        return infoSort;
    }

    public void setInfoSort(String infoSort) {
        this.infoSort = infoSort;
    }
}
