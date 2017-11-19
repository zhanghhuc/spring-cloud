package com.zssq.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: RssReturnPo  
 * @Description: 各个系统返回的 rss统一对象    
 * @author sry  
 * @date 2017年4月7日  
 *
 */
public class RssReturnPo implements Serializable{

	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = -7292436473291538063L;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容主键code：例，博客code
	 */
	private String objectCode;
	/**
	 * 作者名称（不用传）
	 */
	private String authorName;
	/**
	 * 详情
	 */
	private String description;
	
	/**
	 * 创建时间
	 */
	private Long createTime;
	
	/**
	 * 发布人code
	 */
	private String authorCode;
	/**
	 * 音频
	 */
	private String audio;
	
	/**
	 * 视频
	 */
	private String video;
	/*private List<String> sourceUrls;*/
	
	
	public String getTitle() {
		return title;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	
	
}
