package com.zssq.news.model;

import java.io.Serializable;

/**
 * Created by admin on 2017-04-22.
 */
public class BaseModel  implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;

    private String userCode ;

    private Integer isShield ;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getIsShield() {
        return isShield;
    }

    public void setIsShield(Integer isShield) {
        this.isShield = isShield;
    }
}
