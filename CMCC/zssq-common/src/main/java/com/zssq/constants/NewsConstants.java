package com.zssq.constants;

/**
 * 
    * @ClassName: NewsConstants
    * @Description: 新闻常量类
    * @author SharlaCheung
    * @date 2017年5月9日
    *
 */
public class NewsConstants {

	public static final int NEWS = 1 ;

/**
 * 新闻状态（0:草稿,1:待审核,2:审核未通过,3:已撤销,4:已发布,5:已隐藏）
 */
	/* ==========新闻状态==========  */
	/** 新闻草稿 **/
	public static final int NEWS_STATUS_DRAFT = 0;
	/** 新闻待审核 **/
	public static final int NEWS_STATUS_WAITING_AUDIT = 1;
	/** 新闻审核未通过 **/
	public static final int NEWS_STATUS_NOT_PASS = 2;
	/** 新闻已撤销 **/
	public static final int NEWS_STATUS_REPEALED = 3;
	/** 新闻已发布 **/
	public static final int NEWS_STATUS_PUBLISHED = 4;
	/** 新闻已隐藏 **/
	public static final int NEWS_STATUS_HIDDENED = 5;
	/** 新闻归档 **/
	public static final int NEWS_STATUS_ARCHIVED = 6;
	/** 新闻取消隐藏 **/
	public static final int NEWS_CANCEL_HIDDENED = 7;

	/* ==========新闻是否隐藏==========  */
	/** 新闻隐藏 **/
	public static final int NEWS_IS_HIDDEN = 1;
	/** 新闻显示 **/
	public static final int NEWS_NO_HIDDEN = 0;

	/* ==========新闻是否删除==========  */
	/** 新闻删除 **/
	public static final int NEWS_IS_DELETE = 1;
	/** 新闻未删除 **/
	public static final int NEWS_NO_DELETE = 0;

	/* ==========新闻是否删除==========  */
	/** 新闻删除 **/
	public static final int NEWS_IS_SHIELD = 1;
	/** 新闻未删除 **/
	public static final int NEWS_NO_SHIELD = 0;

	/* ==========新闻是否置顶==========  */
	/** 新闻置顶 **/
	public static final int NEWS_IS_TOP = 1;
	/** 新闻未/取消置顶 **/
	public static final int NEWS_NO_TOP = 0;

	/* ==========新闻评论==========  */
	/** 新闻评论 **/
	public static final int NEWS_ADD_COMMENT = 1;
	/** 新闻删除评论 **/
	public static final int NEWS_DEL_COMMENT = -1;


	/* ==========新闻点赞==========  */
	/** 新闻点赞 **/
	public static final int NEWS_PRAISE_FLAG = 1;
	/** 新闻取消点赞 **/
	public static final int NEWS_CANCEL_PRAISE_FLAG = 0;

	/* ==========新闻评论点赞==========  */
	/** 新闻评论点赞 **/
	public static final int NEWS_COMMENT_PRAISE_FLAG = 1;
	/** 新闻评论取消点赞 **/
	public static final int NEWS_COMMENT_CANCEL_PRAISE_FLAG = 0;

	/* ==========新闻收藏==========  */
	/** 新闻收藏 **/
	public static final int NEWS_COLLECT = 1;
	/** 新闻取消收藏 **/
	public static final int NEWS_NOT_COLLECT = 0;

	/* ==========新闻收藏==========  */
	/** 新闻收藏 **/
	public static final int NEWS_IS_ARCHIVED = 1;
	/** 新闻取消收藏 **/
	public static final int NEWS_NOT_ARCHIVED = 0;


	/* ==========新闻所属=========  */
	/** 个人自己 **/
	public static final int NEWS_IS_SELF = 1;
	/** 所有 **/
	public static final int NEWS_NOT_SELF = 0;



	/** news 模块在kafka的topic名称 **/
	public static final String NEWS_TOPIC = "news";

}
