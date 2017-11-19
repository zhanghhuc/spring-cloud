package com.zssq.constants;

/**
 * 
    * @ClassName: MblogConstants  
    * @Description: 微博常量类  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class MblogConstants {

	/* ==========微博空==========  */
	public static final String MBLOG_BLANK = "";
	public static final int MBLOG_ZERO = 0;
	public static final byte MBLOG_BYTE_ZERO = 0;
	public static final long MBLOG_TIME_ZERO = 0L;
	/* ==========微博空==========  */
	
	/* ==========微博开关==========  */
	/** YES **/
	public static final byte MBLOG_YES = 1;
	/** NO **/
	public static final byte MBLOG_NO = 0;
	/* ==========微博开关==========  */
	
	/* ==========微博所属==========  */
	/** 个人 **/
	public static final byte MBLOG_DEPEND_SELF = 1;
	/** 班组 **/
	public static final byte MBLOG_DEPEND_GROUP = 2;
	/* ==========微博所属==========  */
	
	/* ==========微博来源==========  */
	/** 原创 **/
	public static final byte MBLOG_SOURCE_SELF = 1;
	/** 转发 **/
	public static final byte MBLOG_SOURCE_FORWARD = 2;
	/** 代发 **/
	public static final byte MBLOG_SOURCE_AGENT = 3;
	/* ==========微博来源==========  */
	
	/* ==========微博可见范围==========  */
	/** 私有 **/
	public static final byte MBLOG_VISIBLE_SELF = 2;
	/** 公开 **/
	public static final byte MBLOG_VISIBLE_PUBLIC = 1;
	/* ==========微博可见范围==========  */
	
	
	/* ==========微博属性范围==========  */
	/** 个人 **/
	public static final byte MBLOG_FOLLOW_SELF = 1;
	/** 班组 **/
	public static final byte MBLOG_FOLLOW_GROUP = 2;
	/** 门户 **/
	public static final byte MBLOG_FOLLOW_GATEWAY = 3;
	/* ==========微博属性范围==========  */
	
	/* ==========微博资源类型==========  */
	/** 文本 **/
	public static final byte MBLOG_RESOURCE_TEXT = 1;
	/** 图片 **/
	public static final byte MBLOG_RESOURCE_IMG = 2;
	/** 音频 **/
	public static final byte MBLOG_RESOURCE_AUDIO = 3;
	/** 视频 **/
	public static final byte MBLOG_RESOURCE_VIDEO = 4;
	/** 话题 **/
	public static final byte MBLOG_RESOURCE_TOPIC = 5;
	/** 摘要 **/
	public static final byte MBLOG_RESOURCE_SUMMARY = 6;
	/* ==========微博资源类型==========  */
	
	
	/* ==========微博AT类型==========  */
	/** 发布 **/
	public static final byte MBLOG_AT_PUS = 1;
	/** 转发 **/
	public static final byte MBLOG_AT_FOR = 2;
	/** 评论 **/
	public static final byte MBLOG_AT_COM = 3;
	/** 回复 **/
	public static final byte MBLOG_AT_REP = 4;
	/* ==========微博AT类型==========  */
	
	/* ==========微博AT位置==========  */
	/** 微博 **/
	public static final byte MBLOG_AT_SITE_MBLOG = 1;
	/** 评论 **/
	public static final byte MBLOG_AT_SITE_COM = 2;
	/** 回复 **/
	public static final byte MBLOG_AT_SITE_REP = 3;
	/* ==========微博AT位置==========  */
	
	
	/* ==========微博点赞类型==========  */
	/** 微博 **/
	public static final byte MBLOG_PRAISE_MBLOG = 1;
	/** 评论 **/
	public static final byte MBLOG_PRAISE_COM = 2;
	/** 回复 **/
	public static final byte MBLOG_PRAISE_REP = 3;
	/* ==========微博AT位置==========  */
	
	
	/* ==========微博数量变更==========  */
	/** 数量+1 **/
	public static final int MBLOG_NUM_ADD = 1;
	/** 数量-1 **/
	public static final int MBLOG_NUM_SUB = -1;
	/* ==========微博数量变更==========  */
	
	/* ==========微博订阅类型==========  */
	/** 个人 **/
	public static final byte MBLOG_SUB_PER = 1;
	/** 班组 **/
	public static final byte MBLOG_SUB_TEAM = 2;
	/* ==========微博订阅类型==========  */
	
	
	/* ==========微博数据来源信息==========  */
	/** 微博 **/
	public static final byte MBLOG_NUM_MBLOG = 1;
	/** 评论 **/
	public static final byte MBLOG_NUM_COMMENT = 2;
	/** 回复 **/
	public static final byte MBLOG_NUM_REPLY = 3;
	/* ==========微博数据来源信息==========  */
	
	
	/* ==========微博动作类型信息==========  */
	/** 转发 **/
	public static final byte MBLOG_ACTION_FORWARD = 1;
	/** 收藏 **/
	public static final byte MBLOG_ACTION_COLLECT = 2;
	/** 评论 **/
	public static final byte MBLOG_ACTION_COMMENT = 3;
	/** 回复 **/
	public static final byte MBLOG_ACTION_REPLY = 4;
	/** 微博点赞 **/
	public static final byte MBLOG_ACTION_MBLOG_PRAISE = 5;
	/** 评论点赞 **/
	public static final byte MBLOG_ACTION_COMMENT_PRAISE = 6;
	/** 回复点赞 **/
	public static final byte MBLOG_ACTION_REPLY_PRAISE = 7;
	/* ==========微博动作类型信息==========  */
	
	/* ==========微博kafkaTopic信息==========  */
	/** 数量--不包含转发和置精 **/
	public static final String MBLOG_KAFKA_TOPIC_NUM = "mblog_topic_num";
	/** 数量-转发**/
	public static final String MBLOG_KAFKA_TOPIC_FORWARD = "mblog_topic_forward";
	/** 数量-置精**/
	public static final String MBLOG_KAFKA_TOPIC_ESSENCE = "mblog_topic_essence";
	/* ==========微博kafkaTopic信息==========  */
	
	/* ==========微博置精范围==========  */
	/** 集团-置精**/
	public static final byte MBLOG_ESSENCE_SCOPE_GROUP = 1;
	/** 省级-置精**/
	public static final byte MBLOG_ESSENCE_SCOPE_PROVINCE = 2;
	/** 市级-置精**/
	public static final byte MBLOG_ESSENCE_SCOPE_CITY = 3;
	/** 班组-置精**/
	public static final byte MBLOG_ESSENCE_SCOPE_TEAM = 4;
	/* ==========微博置精范围==========  */
	
	/* ==========线程池 数据==========  */
	/** 核心线程数 **/
	public static final int SERVICE_THREADPOOL_CORESIZE = 10;
	/** 最多线程数 **/
	public static final int SERVICE_THREADPOOL_MAXSIZE = 40;
	/** 最多保持 **/
	public static final int SERVICE_THREADPOOL_ALIVETIME = 15;
	/** 队列数 **/
	public static final int SERVICE_THREADPOOL_QUEUESIZE = 25;
	/* ==========线程池 数据==========  */
	
	/* ==========微博回复类型==========  */
	/** 评论回复 **/
	public static final byte MBLOG_REPLY_COMMENT = 1;
	/** 回复回复 **/
	public static final byte MBLOG_REPLY_REPLY = 2;
	/* ==========微博回复类型==========  */
	
}
