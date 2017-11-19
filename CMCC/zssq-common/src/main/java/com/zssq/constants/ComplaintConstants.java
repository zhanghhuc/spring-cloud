package com.zssq.constants;

public class ComplaintConstants {

	/** 否 **/
	public static final Byte COMPLAINT_NO = 0;
	/** 是 **/
	public static final Byte COMPLAINT_YES = 1;

	/** 1:应用 **/
	public static final Byte COMPLAINT_TYPE_APP = 1;
	/** 2:应用下的评论 **/
	public static final Byte COMPLAINT_TYPE_COMMENT = 2;
	/** 3:应用下的评论的回复 **/
	public static final Byte COMPLAINT_TYPE_REPLY = 3;

	/** 操作 0：恢复（取消屏蔽） **/
	public static final Byte COMPLAINT_BACK = 0;
	/** 操作 1：屏蔽 **/
	public static final Byte COMPLAINT_SHIELD = 1;

	/** 举报信息状态 1-未处理 **/
	public static final Byte COMPLAINT_STATUS_UNHANDLE = 1;
	/** 举报信息状态 2-屏蔽 **/
	public static final Byte COMPLAINT_STATUS_HIDE = 2;
	/** 举报信息状态 3-忽略 **/
	public static final Byte COMPLAINT_STATUS_IGNORE = 3;
	/** 举报信息状态 4-恢复 **/
	public static final Byte COMPLAINT_STATUS_BACK = 4;

	/** 内容监控操作：1-删除 **/
	public static final Byte OPT_DELETE = 1;
	/** 内容监控操作：2-恢复 **/
	public static final Byte OPT_BACK = 2;

	/**  应用 1-新闻  **/
	public static final Byte NEWS = 1;
	/**  应用  2-微博   **/
	public static final Byte MBOLG = 2;
	/**  应用 3-博客   **/
	public static final Byte BOLG = 3;
	/**  应用 4-论坛 **/
	public static final Byte FORUM = 4;
	/**  应用 5-投票 **/
	public static final Byte VOTE = 5;
	/**  应用 6-活动  **/
	public static final Byte ACTIVITY = 6;
	/**  应用 7-荣誉  **/
	public static final Byte HONOR = 7;
	/**  应用 8-网盘文件  **/
	public static final Byte DISK = 8;
	/**  应用 9-个人留言  **/
	public static final Byte PERSON_MESSAGE = 9;
	/**  应用 10-班组留言  **/
	public static final Byte TEAM_MESSAGE = 10;

}
