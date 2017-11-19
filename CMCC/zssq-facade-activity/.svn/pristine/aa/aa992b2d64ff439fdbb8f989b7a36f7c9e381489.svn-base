package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;

public class ActivityInfoWithBLOBs extends ActivityInfo implements Serializable {
    private String introduce;

    private String activityRule;
    
   
    /**活动奖项集合*/
    private List<ActivityPrize> prizes;
    
    /**活动资源集合*/
    private List<ActivityResource> resources;
    
    private static final long serialVersionUID = 1L;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule == null ? null : activityRule.trim();
    }

	public List<ActivityPrize> getPrizes() {
		return prizes;
	}

	public void setPrizes(List<ActivityPrize> prizes) {
		this.prizes = prizes;
	}

	public List<ActivityResource> getResources() {
		return resources;
	}

	public void setResources(List<ActivityResource> resources) {
		this.resources = resources;
	}
}