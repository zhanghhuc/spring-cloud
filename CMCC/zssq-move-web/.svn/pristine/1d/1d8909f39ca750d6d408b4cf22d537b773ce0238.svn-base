package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.zssq.constants.MblogConstants;
import com.zssq.utils.StringTools;

public class Db2MblogVo implements RowMapper<Db2MblogVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer userId;
	private Integer teamId;
	private Integer insteadUser;
	private Integer rootTid;
	private Byte actionType;
	private Byte delFlag;
	private Date pubTime;
	private String logo;
	private String videoUrl;
	private String audioUrl;
	private String contentExt;
	private String imgUrls;
	private Integer toMblogId;
	private Integer toUid;
	private Integer replyNum;
	private Integer forwardNum;
	private Byte insteadFlag;
	
	private String mblogCode = "";
	private String userCode = "";
	private String orgCode = "";
	private String teamCode = "";
	private String agentUserCode = "";
	private String agentOrgCode = "";
	private String sourceMblogCode = "";
	private String sourceUserCode = "";
	private String tenantCode = "";
	private String dynamicCode = "";
	
	public Byte getMblogDepend() {
		if(null == teamId || teamId == 0){
			return MblogConstants.MBLOG_DEPEND_SELF;
		}else{
			return MblogConstants.MBLOG_DEPEND_GROUP;
		}
	}
	
	
	public Byte getInsteadFlag() {
		return insteadFlag;
	}


	public void setInsteadFlag(Byte insteadFlag) {
		this.insteadFlag = insteadFlag;
	}


	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public String getMblogCode() {
		return mblogCode;
	}


	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getTeamCode() {
		return teamCode;
	}


	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}


	public String getAgentUserCode() {
		return agentUserCode;
	}


	public void setAgentUserCode(String agentUserCode) {
		this.agentUserCode = agentUserCode;
	}


	public String getAgentOrgCode() {
		return agentOrgCode;
	}


	public void setAgentOrgCode(String agentOrgCode) {
		this.agentOrgCode = agentOrgCode;
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
		this.sourceUserCode = sourceUserCode;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getTeamId() {
		return teamId;
	}


	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


	public Integer getInsteadUser() {
		return insteadUser;
	}


	public void setInsteadUser(Integer insteadUser) {
		this.insteadUser = insteadUser;
	}


	public Integer getRootTid() {
		return rootTid;
	}


	public void setRootTid(Integer rootTid) {
		this.rootTid = rootTid;
	}


	public Byte getActionType() {
		return actionType;
	}


	public void setActionType(Byte actionType) {
		this.actionType = actionType;
	}


	public Byte getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}


	public Date getPubTime() {
		return pubTime;
	}


	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}


	public String getLogo() {
		if(StringTools.isEmpty(logo)){
			return "";
		}
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getVideoUrl() {
		return videoUrl;
	}


	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}


	public String getAudioUrl() {
		return audioUrl;
	}


	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}


	public String getContentExt() {
		return contentExt;
	}


	public void setContentExt(String contentExt) {
		this.contentExt = contentExt;
	}


	public String getImgUrls() {
		return imgUrls;
	}


	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}


	public Integer getToMblogId() {
		return toMblogId;
	}


	public void setToMblogId(Integer toMblogId) {
		this.toMblogId = toMblogId;
	}


	public Integer getToUid() {
		return toUid;
	}


	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	}


	public Integer getReplyNum() {
		return replyNum;
	}


	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}


	public Integer getForwardNum() {
		return forwardNum;
	}


	public void setForwardNum(Integer forwardNum) {
		this.forwardNum = forwardNum;
	}

	
	/**
	 * 
	    * @Title: getPageStatement  
	    * @Description: 获取分页数据
	    * @param endNum		页码
	    * @param pageSize	每页条数
	    * @param actionType	动作类型
		* @return String    返回类型
	 */
	public static String getPageStatement(int endNum,int pageSize,int actionType){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,PUB_TIME,USER_ID,TEAM_ID,CONTENT_EXT,MBLOG_TYPE,ROOT_TID,TO_MBLOG_ID,TO_UID,REPLY_NUM,FORWARD_NUM,ACTION_TYPE,INSTEAD_USER,DEL_FLAG,INSTEAD_FLAG, ")
		  .append(" (SELECT  replace(replace(xml2clob(xmlagg(xmlelement(NAME a, IMG_URL||','))),'<A>',''),'</A>','') FROM MBLOG_IMAGE WHERE MBLOG_ID = mb.ID) as IMG_URLS, ")
		  .append(" (SELECT MUSIC_URL FROM MBLOG_MUSIC WHERE ID = mb.MUSIC_ID) as AUDIO_URL, ")
		  .append(" (SELECT VIDEO_URL FROM MBLOG_VIDEO WHERE ID = mb.VIDEO_ID) as VIDEO_URL, ")
		  .append(" (SELECT IMG_URL FROM MBLOG_IMAGE WHERE ID = mb.IMAGE_ID) as LOGO ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" * ")
		  .append(" FROM ")
		  .append(" mblog.MBLOG ")
		  .append(" WHERE ")
		  .append(" DEL_FLAG = 0 ")
		  .append(" and (BUSI_TYPE is null or BUSI_TYPE = 2 or BUSI_TYPE = 4 or BUSI_TYPE = 5) ")
		  .append(" and ACTION_TYPE = "+actionType+" ")
		  .append(" ORDER BY ID ")
		  .append(" FETCH FIRST ")
		  .append(" "+endNum+" ")
		  .append(" ROWS ONLY")
		  .append(" ) as mb")
		  .append(" ORDER BY ID DESC")
		  .append(" FETCH FIRST ")
		  .append(" "+pageSize+" ")
		  .append(" ROWS ONLY");
		return sb.toString();
	}
	
	/**
	 * 
	    * @Title: getRowPageStatement  
	    * @Description: rownum实现
	    * @param actionType
		* @return String    返回类型
	 */
	public static String getRowPageStatement(int actionType){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,PUB_TIME,USER_ID,TEAM_ID,CONTENT_EXT,MBLOG_TYPE,ROOT_TID,TO_MBLOG_ID,TO_UID,REPLY_NUM,FORWARD_NUM,ACTION_TYPE,INSTEAD_USER,DEL_FLAG,INSTEAD_FLAG, ")
		  .append(" (SELECT  replace(replace(xml2clob(xmlagg(xmlelement(NAME a, IMG_URL||','))),'<A>',''),'</A>','') FROM MBLOG_IMAGE WHERE MBLOG_ID = mb.ID) as IMG_URLS, ")
		  .append(" (SELECT MUSIC_URL FROM MBLOG_MUSIC WHERE ID = mb.MUSIC_ID) as AUDIO_URL, ")
		  .append(" (SELECT VIDEO_URL FROM MBLOG_VIDEO WHERE ID = mb.VIDEO_ID) as VIDEO_URL, ")
		  .append(" (SELECT IMG_URL FROM MBLOG_IMAGE WHERE ID = mb.IMAGE_ID) as LOGO ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" ID,PUB_TIME,USER_ID,TEAM_ID,CONTENT_EXT,MUSIC_ID,VIDEO_ID,IMAGE_ID,MBLOG_TYPE,ROOT_TID,TO_MBLOG_ID,TO_UID,REPLY_NUM,FORWARD_NUM,ACTION_TYPE,INSTEAD_USER,DEL_FLAG,INSTEAD_FLAG,  ")
		  .append(" rownumber() over(ORDER BY ID) as rn ")
		  .append(" FROM ")
		  .append(" mblog.MBLOG ")
		  .append(" WHERE ")
		  .append(" DEL_FLAG = 0  ")
		  .append(" and (BUSI_TYPE is null or BUSI_TYPE = 2 or BUSI_TYPE = 4 or BUSI_TYPE = 5) ")
		  .append(" and ACTION_TYPE = "+actionType+" ")
		  .append(" ) as mb ")
		  .append(" WHERE ")
		  .append(" mb.rn BETWEEN ? AND ? ");
		return sb.toString();
	}

	/**
	 * 
	    * @Title: getPageStatement  
	    * @Description: 获取分页数据
	    * @param pageNo		页码
	    * @param pageSize	每页条数
		* @return String    返回类型
	 */
	public static String getPageStatement(int pageNo,int pageSize){
		StringBuilder sb = new StringBuilder();
		int endNum = (pageNo+1)*pageSize;
		sb.append(" SELECT ")
		  .append(" ID,PUB_TIME,USER_ID,TEAM_ID,CONTENT_EXT,MBLOG_TYPE,ROOT_TID,TO_MBLOG_ID,TO_UID,REPLY_NUM,FORWARD_NUM,ACTION_TYPE,INSTEAD_USER,DEL_FLAG,INSTEAD_FLAG, ")
		  .append(" (SELECT  replace(replace(xml2clob(xmlagg(xmlelement(NAME a, IMG_URL||','))),'<A>',''),'</A>','') FROM MBLOG_IMAGE WHERE MBLOG_ID = mb.ID) as IMG_URLS, ")
		  .append(" (SELECT MUSIC_URL FROM MBLOG_MUSIC WHERE ID = mb.MUSIC_ID) as AUDIO_URL, ")
		  .append(" (SELECT VIDEO_URL FROM MBLOG_VIDEO WHERE ID = mb.VIDEO_ID) as VIDEO_URL, ")
		  .append(" (SELECT IMG_URL FROM MBLOG_IMAGE WHERE ID = mb.IMAGE_ID) as LOGO ")
		  .append(" FROM ")
		  .append(" ( ")
		  .append(" SELECT ")
		  .append(" * ")
		  .append(" FROM ")
		  .append(" mblog.MBLOG ")
		  .append(" WHERE ")
		  .append(" DEL_FLAG = 0 ")
		  .append(" and (BUSI_TYPE is null or BUSI_TYPE = 2 or BUSI_TYPE = 4 or BUSI_TYPE = 5) ")
		  .append(" ORDER BY ID ")
		  .append(" FETCH FIRST ")
		  .append(" "+endNum+" ")
		  .append(" ROWS ONLY")
		  .append(" ) as mb")
		  .append(" ORDER BY ID DESC")
		  .append(" FETCH FIRST ")
		  .append(" "+pageSize+" ")
		  .append(" ROWS ONLY");
		return sb.toString();
	}
	
	@Override
	public Db2MblogVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Db2MblogVo vo = new Db2MblogVo();
		vo.setId(rs.getLong("ID"));
		vo.setActionType(rs.getByte("ACTION_TYPE"));
		vo.setAudioUrl(rs.getString("AUDIO_URL"));
		vo.setContentExt(rs.getString("CONTENT_EXT"));
		vo.setDelFlag(rs.getByte("DEL_FLAG"));
		vo.setForwardNum(rs.getInt("FORWARD_NUM"));
		vo.setImgUrls(rs.getString("IMG_URLS"));
		vo.setInsteadUser(rs.getInt("INSTEAD_USER"));
		vo.setLogo(rs.getString("LOGO"));
		vo.setPubTime(rs.getDate("PUB_TIME"));
		vo.setReplyNum(rs.getInt("REPLY_NUM"));
		vo.setRootTid(rs.getInt("ROOT_TID"));
		vo.setTeamId(rs.getInt("TEAM_ID"));
		vo.setToMblogId(rs.getInt("TO_MBLOG_ID"));
		vo.setToUid(rs.getInt("TO_UID"));
		vo.setUserId(rs.getInt("USER_ID"));
		vo.setVideoUrl(rs.getString("VIDEO_URL"));
		vo.setInsteadFlag(rs.getByte("INSTEAD_FLAG"));
		
		return vo;
	}

	
	/**
	 * 
	    * @Title: getCountStatement  
	    * @Description: 获取计算数值语句
	    * @param actionType
		* @return String    返回类型
	 */
	public static String getCountStatement(int actionType){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" COUNT(ID) ")
		  .append(" FROM ")
		  .append(" mblog ")
		  .append(" WHERE ")
		  .append(" DEL_FLAG = 0 ")
		  .append(" and (BUSI_TYPE is null or BUSI_TYPE = 2 or BUSI_TYPE = 4 or BUSI_TYPE = 5) ")
		  .append(" and ACTION_TYPE = "+actionType+" ");
		return sb.toString();
	}
	
	/**
	 * 
	    * @Title: getMysqlStatement  
	    * @Description: mysql查询语句
	    * @param actionType
		* @return String    返回类型
	 */
	public static String getMySqlStatement(int actionType){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" ID,PUB_TIME,USER_ID,TEAM_ID,CONTENT_EXT,MBLOG_TYPE,ROOT_TID,TO_MBLOG_ID,TO_UID,REPLY_NUM,FORWARD_NUM,ACTION_TYPE,INSTEAD_USER,DEL_FLAG,INSTEAD_FLAG, ")
		  .append(" (SELECT  GROUP_CONCAT(IMG_URL) FROM mblog_image WHERE MBLOG_ID = mblog.ID) as IMG_URLS, ")
		  .append(" (SELECT MUSIC_URL FROM mblog_music WHERE ID = mblog.MUSIC_ID) as AUDIO_URL, ")
		  .append(" (SELECT VIDEO_URL FROM mblog_video WHERE ID = mblog.VIDEO_ID) as VIDEO_URL, ")
		  .append(" (SELECT IMG_URL FROM mblog_image WHERE ID = mblog.IMAGE_ID) as LOGO ")
		  .append(" FROM ")
		  .append(" mblog ")
		  .append(" WHERE ")
		  .append(" DEL_FLAG = 0  ")
		  .append(" and (BUSI_TYPE is null or BUSI_TYPE = 2 or BUSI_TYPE = 4 or BUSI_TYPE = 5) ")
		  .append(" and ACTION_TYPE = "+actionType+" ")
		  .append(" ORDER BY ID ")
		  .append(" LIMIT ?,? ");
		return sb.toString();
	}
}
