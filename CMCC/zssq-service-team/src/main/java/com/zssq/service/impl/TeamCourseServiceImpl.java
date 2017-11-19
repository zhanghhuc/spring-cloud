package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.AuthConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.mapper.TeamCourseMapper;
import com.zssq.dao.mapper.TeamRecommendMapper;
import com.zssq.dao.pojo.HonorTeam;
import com.zssq.dao.pojo.TeamCourse;
import com.zssq.dao.pojo.TeamRecommend;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.ITeamCourseService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

/**
 * @ClassName TeamCourseServiceImpl
 * @Description 班组推荐历程
 * @author JiPengChun
 * @date 2017年3月18日 上午11:20:16
 * @version 1.0
 * @since JDK 1.7
 */
@Service("teamCourseService")
public class TeamCourseServiceImpl implements ITeamCourseService {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组推荐历程
	 */
	@Autowired
	private TeamCourseMapper teamCourseMapper;
	
	/**
	 * 百强班组
	 */
	@Autowired
	private TeamRecommendMapper teamRecommendMapper;
	
	
	/**
	 * 添加历程
	 * @see com.zssq.service.ITeamCourseService#addTeamCourse(com.zssq.dao.pojo.TeamCourse)
	 */
	@Override
	public boolean addTeamCourse(TeamCourse record) throws BusinessException {
		try{
			return teamCourseMapper.insertSelective(record) == 1;
		} catch (Exception e) {
			log.error("推荐上级",e);
			throw new BusinessException("推荐上级",e);
		}
	}

	/**
	 * 设为百强班组
	 * @see com.zssq.service.ITeamCourseService#addHonorTeam(com.zssq.dao.pojo.TeamCourse, com.zssq.dao.pojo.TeamRecommend)
	 */
	@Override
	public boolean addHonorTeam(String teamCode,String electCode,String userCode,String orgCode) throws BusinessException {
		try{
			TeamCourse record = new TeamCourse();
			record.setTeamElectCode(electCode);
			record.setTeamCode(teamCode);
			record.setCurrentOrgType(AuthConstants.ORG_TYPE_B);
			List<TeamCourse> teamCourses = selectTeamCourseByRecord(record);//查询该班组是否有省推荐
			TeamCourse teamCourse = new TeamCourse();
			TeamRecommend teamRecommend = new TeamRecommend();
        	if(teamCourses.size()>0){//省级推荐
        		record = teamCourses.get(0);
        		teamCourse.setTeamCourseCode(UUIDHelper.getUUID());
        		teamCourse.setTeamElectCode(record.getTeamElectCode());
        		teamCourse.setTeamCode(record.getTeamCode());
        		teamCourse.setCurrentRecommendReason(record.getCurrentRecommendReason());
        		teamCourse.setCurrentOrgCode(orgCode);
        		teamCourse.setCurrentOrgType(AuthConstants.ORG_TYPE_A);
        		teamCourse.setSuperOrgCode("");
        		teamCourse.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
        		teamCourse.setUserCode(userCode);
        		teamCourse.setCreateTime(DateUtils.getFormatDateLong());
        		
        		teamRecommend.setTeamRecommendCode(UUIDHelper.getUUID());
        		teamRecommend.setTeamElectCode(electCode);
        		teamRecommend.setTeamCode(teamCode);
        		teamRecommend.setRecommendReason(record.getCurrentRecommendReason());
        		teamRecommend.setIsExcellent(TeamConstants.BOOLEAN_TRUE);
        		teamRecommend.setUserCode(userCode);
        		teamRecommend.setElectTime(DateUtils.getFormatDateLong());
        		teamRecommend.setIsHonor(TeamConstants.BOOLEAN_FALSE);
        		teamRecommend.setOrgCode(orgCode);
        	}else{//集团直推
        		teamCourse.setTeamCourseCode(UUIDHelper.getUUID());
        		teamCourse.setTeamElectCode(electCode);
        		teamCourse.setTeamCode(teamCode);
        		teamCourse.setCurrentRecommendReason("");
        		teamCourse.setCurrentOrgCode(orgCode);
        		teamCourse.setCurrentOrgType(AuthConstants.ORG_TYPE_A);
        		teamCourse.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
        		teamCourse.setSuperOrgCode("");
        		teamCourse.setUserCode(userCode);
        		teamCourse.setCreateTime(DateUtils.getFormatDateLong());
        		
        		teamRecommend.setTeamRecommendCode(UUIDHelper.getUUID());
        		teamRecommend.setTeamElectCode(electCode);
        		teamRecommend.setTeamCode(teamCode);
        		teamRecommend.setRecommendReason("");
        		teamRecommend.setIsExcellent(TeamConstants.BOOLEAN_TRUE);
        		teamRecommend.setUserCode(userCode);
        		teamRecommend.setElectTime(DateUtils.getFormatDateLong());
        		teamRecommend.setIsHonor(TeamConstants.BOOLEAN_FALSE);
        		teamRecommend.setOrgCode(orgCode);
        	}
			teamCourseMapper.insertSelective(teamCourse);
			return teamRecommendMapper.insertSelective(teamRecommend) == 1;
		} catch (Exception e) {
			log.error("设为百强班组",e);
			throw new BusinessException("设为百强班组",e);
		}
	}
	
	
	/**
	 * 移除百强班组
	 * @see com.zssq.service.ITeamCourseService#delHonorTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delHonorTeam(String teamCode,String electCode) throws BusinessException {
		try{
			TeamCourse record = new TeamCourse();
			record.setTeamElectCode(electCode);
			record.setTeamCode(teamCode);
			record.setCurrentOrgType(AuthConstants.ORG_TYPE_A);
			List<TeamCourse> courses = teamCourseMapper.selectByRecord(record);
			if(courses.size() > 0){
				TeamCourse teamCourse = courses.get(0);
				record = new TeamCourse();
				record.setTeamCourseCode(teamCourse.getTeamCourseCode());
				record.setIsRecommend(TeamConstants.BOOLEAN_FALSE);
				teamCourseMapper.updateByCode(record);//先更新状态    A0  A:设为百强 0:非最新
				teamCourse.setIsRecommend(TeamConstants.BOOLEAN_TRUE);
				teamCourse.setTeamCourseCode(UUIDHelper.getUUID());
				teamCourse.setCurrentOrgType(TeamConstants.ORG_TYPE_X);
				teamCourse.setId(null);
				teamCourseMapper.insertSelective(teamCourse);//插入一条   X1  X：移除百强  1:最新状态
				
				TeamRecommend teamRecommend = new TeamRecommend();
				teamRecommend.setTeamCode(teamCode);
				teamRecommend.setTeamElectCode(electCode);
				teamRecommendMapper.deleteHonorTeam(teamRecommend);//移除百强班组表
				return true;
			}else{
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"移除百强班组","非百强班组"));
			}
		} catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			log.error("移除百强班组",e);
			throw new BusinessException("移除百强班组",e);
		}
	}

	/**
	 * 查询班组历程
	 * @see com.zssq.service.ITeamCourseService#getTeamCourseList(com.zssq.dao.pojo.TeamCourse)
	 */
	@Override
	public List<TeamCourse> selectTeamCourseByRecord(TeamCourse record) throws BusinessException {
		try {
			return teamCourseMapper.selectByRecord(record);
		} catch (Exception e) {
			log.error("查询班组历程",e);
			throw new BusinessException("查询班组历程",e);
		}
	}

	/**
	 * 查询班组历程  带分页
	 * @see com.zssq.service.ITeamCourseService#selectTeamCourseList(PageParam, com.zssq.dao.pojo.TeamCourse)
	 */
	@Override
	public PageBean selectHonorTeamHis(PageParam pageParam, TeamCourse record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<HonorTeam> dataList = teamCourseMapper.selectHonorTeamHisPage(record);
			int count = teamCourseMapper.selectHonorTeamHisCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询班组历程异常",e);
			throw new BusinessException("查询班组历程异常",e);
		}
	}
	
	/**
	 * 查询百强班组(用于推送)
	 * @see com.zssq.service.ITeamCourseService#selectLastHonorTeam(java.lang.String)
	 */
	public List<HonorTeam> selectLastHonorTeam(String electCode) throws BusinessException{
		try {
			return teamCourseMapper.selectLastHonorTeam(electCode);
		} catch (Exception e) {
			log.error("查询百强班组历史异常",e);
			throw new BusinessException("查询百强班组历史异常",e);
		}
	}
	
	
}
