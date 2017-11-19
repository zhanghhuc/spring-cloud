package com.zssq.rss.command;

import java.io.Serializable;

import com.zssq.rss.po.RssQueryPo;

/**
 * 
 * @ClassName: RssCommand  
 * @Description: 前端使用查询对象  
 * @author sry  
 * @date 2017年4月7日  
 *
 */
public class RssCommand extends RssQueryPo implements Serializable{

	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;
	/**
	 * 订阅频道url
	 */
	private String channelUrl;
	/**
	 * 详情url
	 */
	private String itemUrl;
	/**
	 * 渠道标题
	 */
	private String title;
	/**
	 * 渠道描述
	 */
	private String des;
	/**
	 * 渠道类别：
	 * 1，个人-微博
	 * 2，班组-微博
	 * 3，个人-博客
	 * 4，班组-博客
	 * 
	 */
	private int rssType;
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getRssType() {
		return rssType;
	}
	public void setRssType(int rssType) {
		this.rssType = rssType;
	}
	@Override
	public String toString() {
		return "RssCommand [channelUrl=" + channelUrl + ", itemUrl=" + itemUrl + ", title=" + title + ", des=" + des
				+ ", rssType=" + rssType + "]";
	}
	
	
}
