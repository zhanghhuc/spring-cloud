package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.MapOrgRelationMapper;
import com.zssq.dao.pojo.MapOrgRelation;
import com.zssq.service.IMapOrgRelationService;

@Service("mapOrgRelationService")
public class MapOrgRelationServiceImpl implements IMapOrgRelationService {

	@Autowired
	private MapOrgRelationMapper mapOrgRelationMapper;
	
	@Override
	public List<MapOrgRelation> searchAllRelationData(MapOrgRelation mapOrgRelation) {
		
		return mapOrgRelationMapper.selectAllRelationData(mapOrgRelation);
	}

	@Override
	public MapOrgRelation selectByCode(String orgCode){

		return mapOrgRelationMapper.selectByCode(orgCode);
	}

}
