package com.zssq.dao.pojo;

import java.util.Date;
import java.util.List;

import com.zssq.constants.MblogConstants;

/**
 * 
    * @ClassName: MblogInfo  
    * @Description: 微博信息对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogInfo extends BaseEntity{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

    private String mblogCode = "";

    private String orgCode = "";

    private String userCode = "";

    private String agentUserCode = "";

    private String agentOrgCode = "";

    private String teamCode = "";

    private String sourceMblogCode = "";

    private String sourceUserCode = "";

    private Byte mblogDepend = 1;

    private Byte mblogSource = 1;

    private Byte mblogVisible = 1;

    private Byte isDelete = 0;

    private Byte isShield = 0;

    private Long publishTime = new Date().getTime();

    private Integer commentNum = 0;

    private Integer collectNum = 0;

    private Integer recommendNum = 0;

    private Integer reportNum = 0;

    private Integer forwardNum = 0;

    private Integer praiseNum = 0;

    private String tenantCode = "";

    private Integer teamEssenceNum = 0;

    private Integer cityEssenceNum = 0;

    private Integer provinceEssenceNum = 0;

    private Integer groupEssenceNum = 0;

    private String mblogLogo = "";

    private String dynamicCode = "";
    
    private String shieldUserCode = "";
    
    private Long shieldTime = 0L;
    
    /*  =============  外部关联数据  =================== */
    /** 我是否点赞 **/
    private Byte mePraise = 0;
    /** 我是否收藏 **/
    private Byte meCollect = 0;
    /** 资源信息 **/
    private List<MblogResource> resList;
    /********* GET ***********/
    public String getContent(){
    	String content = "";
    	if( null != resList && !resList.isEmpty()){
    		for(MblogResource mr : resList){
    			if(MblogConstants.MBLOG_RESOURCE_TEXT == mr.getResType()){
    				content = mr.getContent();
    				break;
    			}
    		}
    	}
    	return content;
    }
    public String getSummary(){
    	String summary = "";
    	if( null != resList && !resList.isEmpty()){
    		for(MblogResource mr : resList){
    			if(MblogConstants.MBLOG_RESOURCE_SUMMARY == mr.getResType()){
    				summary = mr.getContent();
    				break;
    			}
    		}
    	}
    	return summary;
    }
    public String getImgs(){
    	StringBuilder imgs = new StringBuilder("");
    	if( null != resList && !resList.isEmpty()){
    		for(MblogResource mr : resList){
    			if(MblogConstants.MBLOG_RESOURCE_IMG == mr.getResType()){
    				imgs.append(mr.getContent()).append(",");
    			}
    		}
    		if(imgs.toString().endsWith(",")){
    			return imgs.substring(0, imgs.length()-1);
    		}
    	}
    	return imgs.toString();
    }
	public String getAudios(){
		StringBuilder audios = new StringBuilder("");
		if( null != resList && !resList.isEmpty()){
    		for(MblogResource mr : resList){
    			if(MblogConstants.MBLOG_RESOURCE_AUDIO == mr.getResType()){
    				audios.append(mr.getContent()).append(",");
    			}
    		}
    		if(audios.toString().endsWith(",")){
    			return audios.substring(0, audios.length()-1);
    		}
    	}
		return audios.toString();
	}
	public String getVideos(){
		StringBuilder videos = new StringBuilder("");
		if( null != resList && !resList.isEmpty()){
    		for(MblogResource mr : resList){
    			if(MblogConstants.MBLOG_RESOURCE_VIDEO == mr.getResType()){
    				videos.append(mr.getContent()).append(",");
    			}
    		}
    		if(videos.toString().endsWith(",")){
    			return videos.substring(0, videos.length()-1);
    		}
    	}
		return videos.toString();
	}
    /********* GET ***********/
    /*  =============  外部关联数据  =================== */
    
    
	
	
	
    public String getMblogCode() {
        return mblogCode;
    }

    public String getShieldUserCode() {
		return shieldUserCode;
	}
	public void setShieldUserCode(String shieldUserCode) {
		this.shieldUserCode = shieldUserCode;
	}
	public Long getShieldTime() {
		return shieldTime;
	}
	public void setShieldTime(Long shieldTime) {
		this.shieldTime = shieldTime;
	}
	public String getDynamicCode() {
		return dynamicCode;
	}
	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public Byte getMePraise() {
		return mePraise;
	}

	public void setMePraise(Byte mePraise) {
		this.mePraise = mePraise;
	}

	public Byte getMeCollect() {
		return meCollect;
	}

	public void setMeCollect(Byte meCollect) {
		this.meCollect = meCollect;
	}

	public List<MblogResource> getResList() {
		return resList;
	}

	public void setResList(List<MblogResource> resList) {
		this.resList = resList;
	}

	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}


	public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? "" : orgCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? "" : userCode.trim();
    }

    public String getAgentUserCode() {
        return agentUserCode;
    }

    public void setAgentUserCode(String agentUserCode) {
        this.agentUserCode = agentUserCode == null ? "" : agentUserCode.trim();
    }

    public String getAgentOrgCode() {
        return agentOrgCode;
    }

    public void setAgentOrgCode(String agentOrgCode) {
        this.agentOrgCode = agentOrgCode == null ? "" : agentOrgCode.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? "" : teamCode.trim();
    }

   

    public String getSourceMblogCode() {
		return sourceMblogCode;
	}


	public void setSourceMblogCode(String sourceMblogCode) {
		this.sourceMblogCode = sourceMblogCode;
	}


	public String getSourceUserCode() {
        return sourceUserCode;
    }

    public void setSourceUserCode(String sourceUserCode) {
        this.sourceUserCode = sourceUserCode == null ? "" : sourceUserCode.trim();
    }

    public Byte getMblogDepend() {
        return mblogDepend;
    }

    public void setMblogDepend(Byte mblogDepend) {
        this.mblogDepend = mblogDepend;
    }

    public Byte getMblogSource() {
        return mblogSource;
    }

    public void setMblogSource(Byte mblogSource) {
        this.mblogSource = mblogSource;
    }

    public Byte getMblogVisible() {
        return mblogVisible;
    }

    public void setMblogVisible(Byte mblogVisible) {
        this.mblogVisible = mblogVisible;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsShield() {
        return isShield;
    }

    public void setIsShield(Byte isShield) {
        this.isShield = isShield;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(Integer recommendNum) {
        this.recommendNum = recommendNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? "" : tenantCode.trim();
    }

    public Integer getTeamEssenceNum() {
        return teamEssenceNum;
    }

    public void setTeamEssenceNum(Integer teamEssenceNum) {
        this.teamEssenceNum = teamEssenceNum;
    }

    public Integer getCityEssenceNum() {
        return cityEssenceNum;
    }

    public void setCityEssenceNum(Integer cityEssenceNum) {
        this.cityEssenceNum = cityEssenceNum;
    }

    public Integer getProvinceEssenceNum() {
        return provinceEssenceNum;
    }

    public void setProvinceEssenceNum(Integer provinceEssenceNum) {
        this.provinceEssenceNum = provinceEssenceNum;
    }

    public Integer getGroupEssenceNum() {
        return groupEssenceNum;
    }

    public void setGroupEssenceNum(Integer groupEssenceNum) {
        this.groupEssenceNum = groupEssenceNum;
    }

    public String getMblogLogo() {
        return mblogLogo;
    }

    public void setMblogLogo(String mblogLogo) {
        this.mblogLogo = mblogLogo == null ? "" : mblogLogo.trim();
    }
}