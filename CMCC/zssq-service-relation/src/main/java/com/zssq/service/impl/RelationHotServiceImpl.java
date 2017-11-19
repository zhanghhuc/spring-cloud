package com.zssq.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.RelationConstants;
import com.zssq.dao.mapper.RelationHotHideMapper;
import com.zssq.dao.mapper.RelationHotMapper;
import com.zssq.dao.pojo.RelationHot;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.RelationHotListModel;
import com.zssq.model.RelationPortalFrontHotModel;
import com.zssq.model.RelationPortalHotModel;
import com.zssq.model.RelationTeamHideHotModel;
import com.zssq.service.RelationHotService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationHotVO;

/**
 * 
 * @ClassName: RelationHotServiceImpl  
 * @Description: 热点  
 * @author ZKZ  
 * @date 2017年3月18日  
 *
 */
@Service("relationHotService")
public class RelationHotServiceImpl implements RelationHotService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RelationHotMapper relationHotMapper;
	
	@Autowired
	private RelationHotHideMapper relationHotHideMapper;
	
	
	/**
	 * 管理后台获取热点列表
	 */
	@Override
	public PageBean getGLPortalHotList(PageParam pageParam, RelationHotVO relationHotVO) throws BusinessException {
		
		//返回值
		PageBean pageBean = null;
		
		try {
			//参数效验
			if (pageParam == null || relationHotVO == null) {
				log.error("RelationHotServiceImpl.getGLPortalHotList：参数为空");
				throw BusinessException.build("COMMON_402","参数");
			}
			//获取数据
			Byte hotStatus = relationHotVO.getHotStatus();
			String subjectTitle = relationHotVO.getSubjectTitle();
			Byte subjectClass = relationHotVO.getSubjectClass();
			Long beginTime = relationHotVO.getBeginTime();
			Long endTime = relationHotVO.getEndTime();
			String orgCode = relationHotVO.getOrgCode();
			Integer pageNo = pageParam.getPageNo();
			Integer pageSize = pageParam.getPageSize();
			Long queryTime = relationHotVO.getQueryTime();
			//数据效验
			if (StringTools.isEmpty(orgCode)) {
				log.error("RelationHotServiceImpl.getGLPortalHotList：orgCode为空");
				throw BusinessException.build("COMMON_402","orgCode");
			}
			if (pageSize == null || pageNo == null) {
				log.error("RelationHotServiceImpl.getGLPortalHotList：分页信息为空");
				throw BusinessException.build("COMMON_402","分页信息");
			}
			if (queryTime == null) {
				log.error("RelationHotServiceImpl.getGLPortalHotList：查询时间为空");
				throw BusinessException.build("COMMON_402","查询时间");
			}
			
			//封装参数
			Map<String, Object> map = new HashMap<>();
			map.put("hotStatus",hotStatus);
			map.put("subjectTitle", subjectTitle);
			map.put("subjectClass", subjectClass);
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
			map.put("orgCode", orgCode);
			map.put("limitStart", pageNo*pageSize);
			map.put("limitCount", pageSize); 
			map.put("queryTime",queryTime);
			map.put("hotHideDepend",RelationConstants.RELATION_HOT_HIDE_GROUP );
			
			//判断显示状态
			if (RelationConstants.RELATION_HOT_STATUS_SHOW.equals(hotStatus)) {
				//显示已展示列表
				int showCount = relationHotMapper.selectShowCountByGL(map);
				List<RelationHotListModel> showList = relationHotMapper.getShowHotListByGL(map);
				pageBean = new PageBean(pageNo, pageSize, showCount, showList);
			} else if (RelationConstants.RELATION_HOT_STATUS_HIDE.equals(hotStatus)) {
				//显示已隐藏列表
				int hideCount = relationHotMapper.selectHideCountByGL(map);
				List<RelationHotListModel> hideList = relationHotMapper.getHideHotListByGL(map);
				pageBean = new PageBean(pageNo, pageSize, hideCount, hideList);
			} else {
				//显示全部列表
				int allCount = relationHotMapper.selectAllHotCountByGL(map);
				List<RelationHotListModel> allList = relationHotMapper.getAllHotListByGL(map);
				pageBean = new PageBean(pageNo, pageSize, allCount, allList);
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		
		return pageBean;
	}
	/**
	 * 查询门户展示中热点列表
	 */
	@Override
	public List<RelationPortalHotModel> getMHPortalHotList(RelationHotVO relationHotVO) throws BusinessException {
		
		//返回值
		List<RelationPortalHotModel> list = null;
		
		try {
			//参数效验
			if (relationHotVO == null) {
				log.error("RelationHotServiceImpl.getMHPortalHotList:参数为空");
				throw BusinessException.build("COMMON_402", "relationHotVO参数");
			}
			//获取参数
			String orgCode = relationHotVO.getOrgCode();	//门户编号
			Integer pageSize = relationHotVO.getPageSize();	//显示条数
			Integer pageNo = relationHotVO.getPageNo();	
			Long queryTime = relationHotVO.getQueryTime();	
			String userCode = relationHotVO.getUserCode();//登陆用户code
			//效验数据
			if (StringUtils.isBlank(orgCode)) {
				log.error("RelationHotServiceImpl.getMHPortalHotList:门户编号为空");
				throw BusinessException.build("COMMON_402", "门户编号");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationHotServiceImpl.getMHPortalHotList:用户编号为空");
				throw BusinessException.build("COMMON_402", "用户门户编号");
			}
			if (pageSize == null||pageNo==null) {
				log.error("RelationHotServiceImpl.getMHPortalHotList:显示条数为空");
				throw BusinessException.build("COMMON_402", "显示条数");
			}
			//封装参数
			Map<String,Object> map = new HashMap<>();
			map.put("orgCode", orgCode);
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			
			map.put("pageFirst", pageParam.getPageNo() * pageParam.getPageSize()); // 查询开始条数
			map.put("pageSize", pageParam.getPageSize()); // 查询结果条数
			map.put("userCode", userCode);
			map.put("queryTime", queryTime);
			list = relationHotMapper.getPortalHotList(map);
		} catch (BusinessException e) {
			throw e;
		}
		
		return list;
		
	}
	/**
	 * 查询门户首页展示热点列表
	 */
	@Override
	public List<RelationPortalFrontHotModel> getMHPortalFrontHotList(RelationHotVO relationHotVO) throws BusinessException {
		
		//返回值
		List<RelationPortalFrontHotModel> list = null;
		
		try {
			//参数效验
			if (relationHotVO == null) {
				log.info("RelationHotServiceImpl.getMHPortalFrontHotList:参数为空");
				throw BusinessException.build("COMMON_402", "relationHotVO参数");
			}
			//获取参数
			String orgCode = relationHotVO.getOrgCode();	//门户编号
			Integer showSize = relationHotVO.getShowSize();	//显示条数
			String userCode = relationHotVO.getUserCode();//登陆用户code
			//参数效验
			if (StringUtils.isBlank(orgCode)) {
				log.info("RelationHotServiceImpl.getMHPortalFrontHotList:门户编号为空");
				throw BusinessException.build("COMMON_402", "门户编号");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationHotServiceImpl.getMHPortalFrontHotList:用户编号为空");
				throw BusinessException.build("COMMON_402", "用户门户编号");
			}
			if (showSize == null) {
				log.info("RelationHotServiceImpl.getMHPortalFrontHotList:显示条数为空");
				throw BusinessException.build("COMMON_402", "显示条数");
			}
			//封装数据
			Map<String, Object> map = new HashMap<>();
			map.put("orgCode", orgCode);
			map.put("showSize", showSize);
			map.put("userCode", userCode);
			list = relationHotMapper.getPortalFrontHotList(map);
		} catch (BusinessException e) {
			throw e;
		}
		
		return list;
	}

	/**
	 * 获取班组热点列表
	 */
	@Override
	public PageBean getTeamHotList(PageParam pageParam, RelationHotVO relationHotVO) throws BusinessException {
		
		//返回值
		PageBean pageBean = null;
		
		try {
			//参数效验
			if (pageParam == null || relationHotVO == null) {
				log.error("RelationHotServiceImpl.getTeamHotList:参数为空");
				throw BusinessException.build("COMMON_402", "relationHotVO or pageParam");
			}
			//获取参数
			String teamCode = relationHotVO.getTeamCode();	//班组编号
			Byte hotStatus = relationHotVO.getHotStatus();	//用于判断展示 显示中的班组热点还是隐藏中的班组热点
			Integer pageNo = pageParam.getPageNo();			//页码,从0开始
			Integer pageSize = pageParam.getPageSize();		//每页条数
			List<String> teamUserCodes = relationHotVO.getUserCodeList();//班组用户codes
			Long queryTime = relationHotVO.getQueryTime();
			String userCode = relationHotVO.getUserCode();//登陆用户code
			//效验数据
			if (StringUtils.isBlank(teamCode)) {
				log.error("RelationHotServiceImpl.getTeamHotList:班组编号为空");
				throw BusinessException.build("COMMON_402", "班组编号");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationHotServiceImpl.getTeamHotList:用户编号为空");
				throw BusinessException.build("COMMON_402", "用户编号");
			}
			if (hotStatus == null||!(hotStatus.equals(RelationConstants.RELATION_HOT_STATUS_SHOW)||hotStatus.equals(RelationConstants.RELATION_HOT_STATUS_HIDE))) {
				log.error("RelationHotServiceImpl.getTeamHotList:查询热点状态不合法");
				throw BusinessException.build("COMMON_402", "查询热点状态");
			}
			if (pageNo == null || pageSize == null) {
				log.error("RelationHotServiceImpl.getTeamHotList:分页信息为空");
				throw BusinessException.build("COMMON_402", "分页信息");
			}
			Map<String, Object> map = new HashMap<>();
			//数据拼装
			map.put("limitStart", pageNo*pageSize); // 查询开始条数
			map.put("limitCount", pageSize); // 查询结果条数
			map.put("teamCode", teamCode);//班组code
			map.put("teamUserCodes", teamUserCodes);//班组中用户code
			map.put("userCode", userCode);//当前登陆用户code
			map.put("queryTime", queryTime);//查询时间
			//判断展示状态,是展示班组显示热点还是隐藏热点
			if (hotStatus.equals(RelationConstants.RELATION_HOT_STATUS_SHOW)) {
				// 查询列表总数
				int count = relationHotMapper.getTeamShowHotCountByMH(map);
				
				// 查询列表内容
				List<RelationTeamHideHotModel> hotList = relationHotMapper.listTeamShowHotByMH(map);
				// 返回值
				pageBean = new PageBean(pageNo,pageSize, count, hotList);
			} else {
				// 查询列表总数
				int count = relationHotMapper.getTeamHideHotCountByMH(map);
				
				// 查询列表内容
				List<RelationTeamHideHotModel> hotList = relationHotMapper.listTeamHideHotByMH(map);
				// 返回值
				pageBean = new PageBean(pageNo,pageSize, count, hotList);
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return pageBean;
		
	}
	/**
	 *  隐藏/显示班组热点
	 */
	@Override
	public boolean updateTeamHotStatus(RelationHotVO relationHotVO) throws BusinessException {
		//返回值
		boolean mark = false;
		try {
			//参数效验
			if (relationHotVO == null) {
				log.error("RelationHotServiceImpl.updateHotStatus:参数为空");
				throw BusinessException.build("COMMON_402", "relationHotVO");
			}
			//获取参数并效验
			String hotCode = relationHotVO.getHotCode();	//热点编号
			Byte actionClass = relationHotVO.getActionClass();	//操作类型：1.隐藏；2.显示
			String teamCode = relationHotVO.getTeamCode();	//班组编号
			Byte hotHideDepend = relationHotVO.getHotHideDepend();	//热点隐藏从属关系：1.班组；2.门户
			String orgCode = relationHotVO.getOrgCode();//组织编号
			String tenantCode = relationHotVO.getTenantCode();//租户code
			String userCode = relationHotVO.getUserCode();//用户code
			if (StringUtils.isBlank(hotCode)) {
				log.error("RelationHotServiceImpl.updateHotStatus:热点编号为空");
				throw BusinessException.build("COMMON_402", "热点编号");
			}
			if (StringUtils.isBlank(userCode)) {
				log.error("RelationHotServiceImpl.updateHotStatus:用户编号为空");
				throw BusinessException.build("COMMON_402", "用户编号");
			}
			if (!actionClass.equals(RelationConstants.RELATION_HOT_HIDE) && !actionClass.equals(RelationConstants.RELATION_HOT_SHOW)) {
				log.error("RelationHotServiceImpl.updateHotStatus:热点操作类型不合法");
				throw BusinessException.build("COMMON_402", "热点操作类型");
			}
			if (StringUtils.isBlank(teamCode)) {
				log.error("RelationHotServiceImpl.updateHotStatus:班组编号为空");
				throw BusinessException.build("COMMON_402", "班组编号");
			}
			if (!hotHideDepend.equals(RelationConstants.RELATION_HOT_HIDE_TEAM) && !hotHideDepend.equals(RelationConstants.RELATION_HOT_HIDE_GROUP)) {
				log.error("RelationHotServiceImpl.updateHotStatus:热点隐藏从属关系不合法");
				throw BusinessException.build("COMMON_402", "热点隐藏从属关系");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RelationHotServiceImpl.updatePortalHotStatus:orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			//封装数据
			Map<String, Object> map = new HashMap<>();
			map.put("hotCode", hotCode);
			map.put("teamCode", teamCode);
			map.put("hotHideDepend", hotHideDepend);
			map.put("orgCode", orgCode);//机构编码
			map.put("tenantCode", tenantCode);//租户code
			map.put("userCode", userCode);
			
			//判断操作类型
			if (actionClass.equals(RelationConstants.RELATION_HOT_HIDE)) {
				//隐藏操作
				Long time = new Date().getTime();
				map.put("hotHideCode", UUIDHelper.getUUID());
				map.put("createTime", time);
				map.put("modifyTime", time);
				map.put("remark", "");
				//获取 热点
				RelationHot relationHot = relationHotMapper.getHotByCode(map);
				if(relationHot!=null){
					//封装数据
					map.put("subjectCode", relationHot.getSubjectCode());
					map.put("subjectClass", relationHot.getSubjectClass());
				}else{
					log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：热点信息未找到！ ");
					throw BusinessException.build("RELATION_19002", "更新");
				}
				//判断是否隐藏过
				int count = relationHotHideMapper.selectCountByMap(map);
				if(count<1){
					//插入隐藏
					int insertNum = relationHotHideMapper.insertHideHot(map);
					if(insertNum!=1){
						log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：插入" + insertNum + "条热点隐藏信息 ");
						throw BusinessException.build("RELATION_19002", "更新");
					}else{
						mark = true;
					}
				}
			} else {
				int showNum = relationHotHideMapper.deleteHideHot(map);
				if(showNum!=1){
					log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：删除" + showNum + "条热点隐藏信息 ");
					//throw BusinessException.build("RELATION_19002", "更新");
				}else{
					mark = true;
				}
			}
		} catch (BusinessException e) {
			throw e;
		}
		
		return mark;
		
	}
	/**
	 * 管理端 显示/隐藏 (门户) 热点
	 */
	@Override
	public boolean updatePortalHotStatusByGL(RelationHotVO relationHotVO) throws BusinessException {
		//返回值
		boolean mark = false;
		try {
			//参数效验
			if (relationHotVO == null) {
				log.error("RelationHotServiceImpl.updatePortalHotStatusByGL:参数为空");
				throw BusinessException.build("COMMON_402", "relationHotVO");
			}
			//获取参数并效验
			String hotCode = relationHotVO.getHotCode();	//热点编号
			Byte actionClass = relationHotVO.getActionClass();	//操作类型：1.隐藏；2.显示
			Byte hotHideDepend = relationHotVO.getHotHideDepend();	//热点隐藏从属关系：1.班组；2.门户
			String orgCode = relationHotVO.getOrgCode();//组织编号
			String tenantCode = relationHotVO.getTenantCode();//租户code
			if (StringUtils.isBlank(hotCode)) {
				log.error("RelationHotServiceImpl.updatePortalHotStatus:热点编号为空");
				throw BusinessException.build("COMMON_402", "热点编号");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RelationHotServiceImpl.updatePortalHotStatus:orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (!actionClass.equals(RelationConstants.RELATION_HOT_HIDE) && !actionClass.equals(RelationConstants.RELATION_HOT_SHOW)) {
				log.error("RelationHotServiceImpl.updatePortalHotStatus:热点操作类型不合法");
				throw BusinessException.build("COMMON_402", "热点操作类型");
			}
			if (hotHideDepend == null|| !hotHideDepend.equals(RelationConstants.RELATION_HOT_HIDE_GROUP)) {
				log.error("RelationHotServiceImpl.updatePortalHotStatus:热点隐藏从属关系不合法");
				throw BusinessException.build("COMMON_402", "热点隐藏从属关系");
			}
			//封装数据
			Map<String, Object> map = new HashMap<>();
			map.put("hotCode", hotCode);
			map.put("hotHideDepend", hotHideDepend);
			map.put("teamCode", "");//门户 默认班组长
			map.put("orgCode", orgCode);//机构编码
			map.put("tenantCode", tenantCode);//租户code
			//判断操作类型
			if (actionClass.equals(RelationConstants.RELATION_HOT_HIDE)) {
				//隐藏操作
				Long time = new Date().getTime();
				map.put("hotHideCode", UUIDHelper.getUUID());
				map.put("createTime", time);
				map.put("modifyTime", time);
				map.put("remark", "");
				RelationHot relationHot = relationHotMapper.getHotByCode(map);
				if(relationHot!=null){
					//封装数据
					map.put("subjectCode", relationHot.getSubjectCode());
					map.put("subjectClass", relationHot.getSubjectClass());
				}else{
					log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：热点信息未找到！ ");
					throw BusinessException.build("RELATION_19002", "更新");
				}
				//判断是否隐藏过
				int count = relationHotHideMapper.selectCountByMap(map);
				if(count<1){
					//插入隐藏
					int insertNum = relationHotHideMapper.insertHideHot(map);
					if(insertNum!=1){
						log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：插入" + insertNum + "条热点隐藏信息 ");
						throw BusinessException.build("RELATION_19002", "更新");
					}else{
						mark = true;
					}
				}
			} else {
				int showNum = relationHotHideMapper.deleteHideHot(map);
				if(showNum!=1){
					log.error("RelationHotServiceImpl.updatePortalHotStatusByGL：删除" + showNum + "条热点隐藏信息 ");
					throw BusinessException.build("RELATION_19002", "更新");
				}else{
					mark = true;
				}
			}
		} catch (BusinessException e) {
			throw e;
		}
		return mark;
		
	}
	/**
	 * 定时任务删除 7 天前 热点
	 */
	@Override
	public boolean deleteBeforeSevenDayHotData(Long beforeSevenDayTime) {
//		relationHotHideMapper.deleteHideHotByHotSevenDay(beforeSevenDayTime);
//		relationHotHideMapper.deleteHideHotSevenDay(beforeSevenDayTime);
		int hotDeleteNum = relationHotMapper.deleteHotDataSevenDay(beforeSevenDayTime);
		log.info("RelationHotServiceImpl.deleteBeforeSevenDayHotData:删除 "+hotDeleteNum+"条热点,time="+beforeSevenDayTime);
		// 不做删除判断
		return true;
	}
	/**
	 * 获取共几条记录
	 */
	@Override
	public int selectCountByJob(Map<String, Object> map) {
		return relationHotMapper.selectCountByJob(map);
	}
	@Override
	public List<RelationHot> selectHotByJob(Map<String, Object> map) {
		return relationHotMapper.selectHotByJob(map);
	}
	@Override
	public int updateHotWeight(List<RelationHot> rhList) {
		return relationHotMapper.updateHotWeight(rhList);
	}


}
