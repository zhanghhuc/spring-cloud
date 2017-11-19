package com.zssq.constants;

/**
 * @ClassName AuthConstants
 * @Description 授权常量
 * @author JiPengChun
 * @date 2017年3月15日 下午5:20:02
 * @version 1.0
 * @since JDK 1.7
 */
public class AuthConstants {
	
	/** 0:否:假 */
	public static final Byte BOOLEAN_FALSE = 0;
	/** 1:是:真 */
	public static final Byte BOOLEAN_TRUE = 1;
	
	/** 0:否:假 */
	public static final String BOOLEAN_0 = "0";
	/** 1:是:真 */
	public static final String BOOLEAN_1 = "1";
	
	
	/** 本地组织状态：禁用 */
	public static final String LOCAL_ORGSTATUS_DISABLE = "0";
	
	/** 本地组织状态：启用 */
	public static final String LOCAL_ORGSTATUS_ENABLE = "1";
	
	/** 本地组织状态：删除 */
	public static final String LOCAL_ORGSTATUS_DELETE = "2";
	
	
	/** A级门户:集团 */
	public static final String ORG_TYPE_A = "A";
	/** B级门户:省 */
	public static final String ORG_TYPE_B = "B";
	/** C级门户:市 */
	public static final String ORG_TYPE_C = "C";
	
	/** 1:Saas管理员 */
	public static final Byte ADMIN_TYPE_1 = 1;
	/** 2:租户超级管理员 */
	public static final Byte ADMIN_TYPE_2 = 2;
	/** 3:系统授权管理员 */
	public static final Byte ADMIN_TYPE_3 = 3;
	
	/** 租户code */
	public static final String TENANT_CODE = "856966d06b96418fab2e430d71a1e84e";
	
	/** 权限不足 */
	public static final String RETURNCODE_COMMON_403 = "403";	
	/** 请求成功 */
	public static final String RETURNCODE_AUTH_200 = "AUTH_200";
	/** 请求失败 */
	public static final String RETURNCODE_AUTH_10002 = "AUTH_10002";
	/** 请求成功(原因) */
	public static final String RETURNCODE_AUTH_10003 = "AUTH_10003";
	/** 请求失败(原因) */
	public static final String RETURNCODE_AUTH_10004 = "AUTH_10004";
	/** 组织数据已存在，不能重复添加 */
	public static final String RETURNCODE_AUTH_10005 = "AUTH_10005";
	/** 不能向非行政组织节点下添加行政组织 */
	public static final String RETURNCODE_AUTH_10006 = "AUTH_10006";
	/** 不能删除存在下级节点的组织 */
	public static final String RETURNCODE_AUTH_10007 = "AUTH_10007";
	
	/** 领导 */
	public static final String USER_ROLE_1 = "880CB4D8BAEFF1EDE76B12C2B0AF6598";
	/** 普通员工 */
	public static final String USER_ROLE_0 = "4AEBC102A359A0C82434AB8B5967F5A4";
	/** 领导 */
	public static final String ROLE_NAME_1 = "领导";
	/** 普通员工 */
	public static final String ROLE_NAME_0 = "普通员工";
	
	/** 代发应用类别 */
	public static final String DEPUTY_APP = "DEPUTY_APP";
	/** 微博 */
	public static final String DEPUTY_WB = "2E3E22CE73BE0D15BE722D31C9882586";
	/** 博客 */
	public static final String DEPUTY_BK = "86F1DDE858C0D70A4A10B157ED780239";
	/** 荣誉 */
	public static final String DEPUTY_RY = "86F1DDE858C0D70A4A10B157ED780255";
	
	/** 代发编号前缀 */
	public static final String DEPUTY_NAME_PREFIX = "PZ";
}
