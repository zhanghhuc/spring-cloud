package com.zssq.activity.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
/**
 * 活动模块动态业务程序
 * @ClassName DynamicActivityProcedure
 * @Description 
 * @author liurong
 * @date 2017年4月20日 上午10:42:34
 * @version 1.0
 * @since JDK 1.7
 */
@Component("dynamicActivityProcedure")
public class DynamicActivityProcedure {
	
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	
	/**
	 * 参与活动动态
	 * @Function publishDynamicOfJoin
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param activityCode 活动编码
	 * @throws BusinessException
	 */
	public void publishDynamicOfJoin(String tenantCode, String orgCode, String joinUserCode, String activityCode) throws BusinessException {
		sendJoinAndShareDynamic(tenantCode, orgCode, joinUserCode, activityCode, RelationConstants.RELATION_DYNAMIC_JOIN_ACTIVITY);
	}
	/**
	 * 分享活动动态
	 * @Function publishDynamicOfShare
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param activityCode 活动编码
	 * @throws BusinessException
	 */
	public void publishDynamicOfShare(String tenantCode, String orgCode, String joinUserCode, String activityCode) throws BusinessException {
		sendJoinAndShareDynamic(tenantCode, orgCode, joinUserCode, activityCode, RelationConstants.RELATION_DYNAMIC_SHARE_ACTIVITY);
	}
	
	/**
	 * 发送参与or分享活动动态
	 * @Function sendJoinAndShareDynamic
	 * @Description 
	 * @param tenantCode   租户编码
	 * @param orgCode      组织机构编码
	 * @param joinUserCode 参与用户编码
	 * @param activityCode 活动编码
	 * @param dynamicType  动态类型：9.分享活动；10.参与活动；
	 * @throws BusinessException
	 */
	private void sendJoinAndShareDynamic(String tenantCode, String orgCode, String joinUserCode, String activityCode, Byte dynamicType) throws BusinessException {
		long curTime = DateUtils.getFormatDateLong();
		// 组织动态表数据
		RelationDynamic rd = new RelationDynamic();
		rd.setDynamicCode(UUIDHelper.getUUID());
		rd.setTenantCode(tenantCode);
		rd.setOrgCode(orgCode);
		rd.setOperateTime(curTime);
		rd.setCreateTime(curTime);
		rd.setModifyTime(curTime);
		rd.setDynamicClass(dynamicType);// 动态类型：9.分享活动；10.参与活动；
		rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);// 从属关系1-个人
		rd.setUserCode(joinUserCode);// 所属人编号
		rd.setSubjectCode(activityCode);// 内容编号
		rd.setDynamicIsDelete(RelationConstants.RELATION_NO);// 是否删除0-否；1-是；
		rd.setDynamicIsShield(RelationConstants.RELATION_NO);// 是否屏蔽0-否；1-是；
		rd.setIsSubjectShow(RelationConstants.RELATION_YES);// 是否显示内容(0-否；1-是；注：动态类型为16时该值为0；其余为1)
		rd.setIsSubjectDataShow(RelationConstants.RELATION_NO);// 是否显示内容数据(0-否；1-是；注：动态类型为1/2/3/4/6/12/13时该值为1；其余为0)
		
		
		relationThirdDynamicService.saveDynamic(rd, null, null, null);
	}
	

}
