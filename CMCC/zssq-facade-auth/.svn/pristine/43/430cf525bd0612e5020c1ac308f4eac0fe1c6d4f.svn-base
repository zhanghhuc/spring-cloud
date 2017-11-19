package com.mdcl.smap.service;


import java.util.List;

import com.mdcl.smap.bean.AttrRequest;
import com.mdcl.smap.bean.OrgRequest;
import com.mdcl.smap.bean.OrgResponse;
import com.mdcl.smap.bean.SyncRequest;
import com.mdcl.smap.bean.UserRequest;
import com.mdcl.smap.bean.UserResponse;

public interface ISyncService {
	/**
	 * 获得发生变化的用户数据列表
	 * 
	 * @param SyncRequest
	 * @return List
	 * @throws Exception
	 */
	List getUserList(SyncRequest request);
	
	/**
	 * 获得发生变化的组织数据列表
	 * 
	 * @param SyncRequest
	 * @return List
	 * @throws Exception
	 */
	List getOrgList(SyncRequest request);
	
	/**
	 * 获得一个用户的所有属性信息
	 * 
	 * @param UserRequest
	 * @return UserResponse
	 * @throws Exception
	 */
	UserResponse getUser(UserRequest userRequest);
	
	/**
	 * 获得一个组织的所有属性信息
	 * 
	 * @param OrgRequest
	 * @return OrgResponse
	 * @throws Exception
	 */
	OrgResponse getOrg(OrgRequest orgRequest);

	/**
	 * 获得已开通应用权限的人的指定属性的列表
	 * 
	 * @param AttrRequest
	 * @return List
	 * @throws Exception
	 */
	List getUserListByAttr(AttrRequest request);
	
	/**
	 * 根据省份编码获得已开通应用权限的人的指定属性的列表
	 * 
	 * @param AttrRequest
	 * @return List
	 * @throws Exception
	 */
	List getUserListByArea(AttrRequest request);

	/**
	 * 根据UID获得已开通应用权限的人的指定属性的列表
	 * 
	 * @param AttrRequest
	 * @return List
	 * @throws Exception
	 */
	List getUserListByUid(AttrRequest request);

}
