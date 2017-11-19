package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.MapOrgRelation;

/**
 * 门户地图插件与组织对应关系业务组件
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public interface IMapOrgRelationService {

	/**
	 * 查询所有地图与组织对应关系数据
	 * 
	 * @param mapOrgRelation
	 * 			查询条件：门户类型/地图插件省名称
	 * @return 地图与组织对应关系数据集合
	 */
	public List<MapOrgRelation> searchAllRelationData(MapOrgRelation mapOrgRelation);
	
	/**
	 * 根据原始组织机构编码查询
	 * 
	 * @param orgCode
	 * 			查询条件：原始组织机构编码
	 * @return 地图与组织对应关系数据
	 */
	public MapOrgRelation selectByCode(String orgCode);
}
