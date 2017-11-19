package com.zssq.constants;

/**
 * 
    * @ClassName: NewsNoticeConstants
    * @Description: 公告常量类
    * @author SharlaCheung
    * @date 2017年5月9日
    *
 */
public class NewsNoticeConstants {

/**
 * 公告状态（0:草稿,1:待审核,2:审核未通过,3:已撤销,4:已发布,5:已隐藏）
 */
	/* ==========公告状态==========  */
	/** 公告草稿 **/
	public static final int NOTICE_STATUS_DRAFT = 0;
	/** 公告待审核 **/
	public static final int NOTICE_STATUS_WAITING_AUDIT = 1;
	/** 公告审核未通过 **/
	public static final int NOTICE_STATUS_NOT_PASS = 2;
	/** 公告已撤销 **/
	public static final int NOTICE_STATUS_REPEALED = 3;
	/** 公告已发布 **/
	public static final int NOTICE_STATUS_PUBLISHED = 4;
	/** 公告已隐藏 **/
	public static final int NOTICE_STATUS_HIDDENED = 5;
	/** 公告取消隐藏 **/
	public static final int NOTICE_CANCEL_HIDDENED = 7;
	
	
	/* ==========公告是否隐藏==========  */
	/** 公告隐藏 **/
	public static final int NOTICE_IS_HIDDEN = 1;
	/** 公告显示 **/
	public static final int NOTICE_NO_HIDDEN = 0;


	/* ==========公告是否删除==========  */
	/** 公告删除 **/
	public static final int NOTICE_IS_DELETE = 1;
	/** 公告未删除 **/
	public static final int NOTICE_NO_DELETE = 0;



	/* ==========公告是否置顶 ==========  */
	/** 公告删除 **/
	public static final int NOTICE_IS_TOP = 1;
	/** 公告未删除 **/
	public static final int NOTICE_NO_TOP = 0;



}
