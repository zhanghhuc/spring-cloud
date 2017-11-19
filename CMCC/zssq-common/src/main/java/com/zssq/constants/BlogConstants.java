package com.zssq.constants;

/**
 * 
 * @ClassName: BlogConstants  
 * @Description: 博客常量  
 * @author ZKZ  
 * @date 2017年3月23日  
 *
 */
public class BlogConstants {
	
	/* 公共 */
	/** 否 **/
	public static final Byte BLOG_NO = 0;
	/** 是 **/
	public static final Byte BLOG_YES = 1;
	/** 操作类型：点赞、收藏、屏蔽、关注的个人 **/
	public static final Byte BLOG_ACTION_YES = 1;
	/** 操作类型：取消点赞、取消收藏、取消屏蔽、关注的班组 **/
	public static final Byte BLOG_ACTION_NO = 2;
	
	/* 屏蔽类型 */
	/** 博客 **/
	public static final Byte BLOG_SHIELD_BLOG = 1;
	/** 评论 **/
	public static final Byte BLOG_SHIELD_COMMENT = 2;
	/** 回复 **/
	public static final Byte BLOG_SHIELD_REPLY = 3;
	
	/* 被回复类型 */
	/** 评论 **/
	public static final Byte BLOG_REPLY_COMMENT = 1;
	/** 回复 **/
	public static final Byte BLOG_REPLY_REPLY = 2;
	
	/* 博客分类从属关系 */
	/** 个人 **/
	public static final Byte BLOG_CLASS_DEPEND_USER = 1;
	/** 班组 **/
	public static final Byte BLOG_CLASS_DEPEND_TEAM = 2;
	
	/* 博客从属关系 */
	/** 个人 **/
	public static final Byte BLOG_DEPEND_USER = 1;
	/** 班组 **/
	public static final Byte BLOG_DEPEND_TEAM = 2;
	
	/* 博客来源 */
	/** 个人 **/
	public static final Byte BLOG_SOURCE_ORIGINAL = 1;
	/** 班组 **/
	public static final Byte BLOG_SOURCE_FORWARD = 2;
	
	/* 博客草稿从属关系 */
	/** 个人 **/
	public static final Byte BLOG_DRAFT_DEPEND_USER = 1;
	/** 班组 **/
	public static final Byte BLOG_DRAFT_DEPEND_TEAM = 2;
	
	/* 被订阅类型 */
	/** 个人 **/
	public static final Byte BLOG_SUB_USER = 1;
	/** 班组 **/
	public static final Byte BLOG_SUB_TEAM = 2;
	
	/* 点赞操作类型 */
	/** 博客 **/
	public static final Byte BLOG_LIKE_SUBJECT_BLOG = 1;
	/** 评论 **/
	public static final Byte BLOG_LIKE_SUBJECT_COMMENT = 2;
	/** 回复 **/
	public static final Byte BLOG_LIKE_SUBJECT_REPLY = 3;
	
	/* 默认名称 */
	/** 默认分类标识 **/
	public static final String BLOG_CLASS_DEFAULT_FLAG = "1";
	/** 默认分类 **/
	public static final String BLOG_CLASS_DEFAULT = "默认分类";
	/** 每人最多拥有的分类数 **/
	public static final Integer BLOG_CLASS_MAX_NUM = 5;
	
	/** 执行定时发布博客任务的类 **/
	public static final String BLOG_TIMING_BLOG_CLASS = "com.zssq.job.task.BlogTimingTaskJob";
	/** 执行定时发布博客名称前缀 **/
	public static final String BLOG_TIMING_BLOG_NAME = "timingBlog";
	/** 执行定时任务分片数 **/
	public static final String BLOG_SHARDING_TOTAL_COUNT = "1";
	/** 定时发布博客立即发布时执行定时任务延迟的时间 **/
	public static final Integer BLOG_PUBLISH_DELAY_TIME = 10000;
	
	/** kafka的topic名称 **/
	public static final String BLOG_TOPIC = "blog";
	
	/* kafka 异步 修改 数量 */
	/** 修改班组置精次数 **/
	public static final byte BLOG_THIRD_BLOG_QUALITY_TEAM = 1;
	/** 修改集团置精次数 **/
	public static final byte BLOG_THIRD_BLOG_QUALITY_GROUP = 2;
	/** 修改省置精次数 **/
	public static final byte BLOG_THIRD_BLOG_QUALITY_PROVINCE = 3;
	/** 修改市置精次数 **/
	public static final byte BLOG_THIRD_BLOG_QUALITY_CITY = 4;
	/** 修改博客浏览量 **/
	public static final byte BLOG_THIRD_BLOG_READ = 5;
	/** 修改博客点赞量 **/
	public static final byte BLOG_THIRD_BLOG_LIKE = 6;
	/** 修改博客收藏量 **/
	public static final byte BLOG_THIRD_BLOG_COLLECT = 7;
	/** 修改博客转发量 **/
	public static final byte BLOG_THIRD_BLOG_FORWARD = 8;
	/** 修改博客评论量 **/
	public static final byte BLOG_THIRD_BLOG_COMMENT = 9;
	/** 修改博客分享量 **/
	public static final byte BLOG_THIRD_BLOG_SHARE = 10;
	/** 修改博客附件下载量 **/
	public static final byte BLOG_THIRD_BLOG_ATTACH_DOWN = 11;
	/** 修改分类下博客量 **/
	public static final byte BLOG_THIRD_CLASS_BLOG = 12;
	/** 修改评论回复量 **/
	public static final byte BLOG_THIRD_COMMENT_REPLY = 13;
	/** 修改评论点赞量 **/
	public static final byte BLOG_THIRD_COMMENT_LIKE = 14;
	/** 修改回复点赞 **/
	public static final byte BLOG_THIRD_REPLY_LIKE = 15;

}
