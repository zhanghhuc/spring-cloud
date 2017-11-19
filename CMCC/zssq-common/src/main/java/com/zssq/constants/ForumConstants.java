package com.zssq.constants;
/**
 * 论坛模块常量类
 * @ClassName ForumConstants
 * @Description 
 * @author liurong
 * @date 2017年3月17日 下午5:11:25
 * @version 1.0
 * @since JDK 1.7
 */
public class ForumConstants {

	/**是否状态：0-否 **/
	public static final Byte NO = 0;
	/**是否状态：1-是 **/
	public static final Byte YES = 1;
	
	/** 操作 置顶 --top*/
	public static final String OPERATION_TOP = "top";
	/** 操作 取消置顶--cancelTop*/
	public static final String OPERATION_DELETE_TOP = "cancelTop";
	/** 操作 置精--best*/
	public static final String OPERATION_BEST = "best";
	/** 操作 取消置精--cancelBest*/
	public static final String OPERATION_DELETE_BEST = "cancelBest";
	/** 操作 删除帖子--delete*/
	public static final String OPERATION_DELETE = "delete";
	/** 操作 收藏帖子--1*/
	public static final Byte OPERATION_COLLECT = 1;
	/** 操作	取消帖子--0*/
	public static final Byte OPERATION_DELETE_COLLECT = 0;
	
	/** 待删除/屏蔽信息类型：1-跟帖 */
	public static final String TOBE_DS_INFOTYPE_1 = "1";
	/** 待删除/屏蔽信息类型：2-评论/回复 */
	public static final String TOBE_DS_INFOTYPE_2 = "2";
	/** 待删除/屏蔽信息类型：3-回复 */
	public static final String TOBE_DS_INFOTYPE_3 = "3";
	
	/** 处理信息类型： 1-主帖 */
	public static final Byte HANDLE_INFO_TYPE_1  = 1;
	/** 处理信息类型： 2-跟帖 */
	public static final Byte HANDLE_INFO_TYPE_2  = 2;
	/** 处理信息类型： 3-评论/回复 */
	public static final Byte HANDLE_INFO_TYPE_3  = 3;
	
	/** 统计对象类型： 1-个人 */
	public static final Byte STATISTICS_OBJECT_TYPE_1  = 1;
	/** 统计对象类型： 2-班组 */
	public static final Byte STATISTICS_OBJECT_TYPE_2  = 2;
	
}
