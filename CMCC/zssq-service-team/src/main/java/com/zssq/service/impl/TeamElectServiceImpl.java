package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.TeamConstants;
import com.zssq.dao.mapper.SequenceElectMapper;
import com.zssq.dao.mapper.TeamElectMapper;
import com.zssq.dao.mapper.TeamRecommendMapper;
import com.zssq.dao.pojo.SequenceElect;
import com.zssq.dao.pojo.TeamElect;
import com.zssq.dao.pojo.TeamRecommend;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.ITeamElectService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

/**
 * @ClassName TeamElectServiceImpl
 * @Description 评选
 * @author JiPengChun
 * @date 2017年3月15日 下午4:47:04
 * @version 1.0
 * @since JDK 1.7
 */
@Service("teamElectService")
public class TeamElectServiceImpl implements ITeamElectService {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组评选
	 */
	@Autowired
	private TeamElectMapper teamElectMapper;
	
	/**
	 * 百强班组
	 */
	@Autowired
	private TeamRecommendMapper teamRecommendMapper;
	
	/**
	 * 评选code
	 */
	@Autowired
	private SequenceElectMapper sequenceElectMapper;
	
	
	/**
	 * 开始评选
	 * @throws BusinessException 
	 * @see com.zssq.service.ITeamElectService#startElect()
	 */
	@Override
	public boolean startElect(TeamElect record) throws BusinessException {
		try{
			TeamElect teamElect = new TeamElect();
			teamElect.setIsLatest(TeamConstants.ELECT_TYPE_2);
			if(teamElectMapper.selectByRecord(teamElect).size()>0){//有正在进行的评选
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"开始评选","有正在进行的评选"));
			}
			return teamElectMapper.insertSelective(record) == 1;
		} catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			log.error("开始评选异常", e);
			throw new BusinessException("开始评选异常", e);
		}
	}
	
	/**
	 * 拿到评选code
	 * @see com.zssq.service.ITeamElectService#getElectCode()
	 */
	@Override
	public String getElectCode() throws BusinessException{
		try {
			SequenceElect record = new SequenceElect();
			int i = sequenceElectMapper.insert(record);
			if(i>0){
				Calendar c = Calendar.getInstance();
				String s = c.get(Calendar.YEAR)+"";
				String teamId = StringUtils.leftPad(record.getId()+"", 5, "0");
				return TeamConstants.ELECT_NAME_PREFIX + s.substring(2, 4) + teamId;
			}else{
				throw new BusinessException();
			}
		} catch (Exception e) {
			log.error("获取班组主键", e);
			throw new BusinessException("获取班组主键", e);
		}
	}

	/**
	 * 拿到正在进行的评选
	 * @see com.zssq.service.ITeamElectService#getNowTeamElect()
	 */
	@Override
	public TeamElect getNowTeamElect() throws BusinessException{
		try{
			TeamElect record = new TeamElect();
			record.setIsLatest(TeamConstants.ELECT_TYPE_2);
			List<TeamElect> electList  = teamElectMapper.selectByRecord(record);
			/*if(electList.size()==0){
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"获取评选","无正在进行的评选"));
			}*/
			if(CollectionUtils.isNotEmpty(electList)) {
				return electList.get(0);
			}
			return null;
		} catch (Exception e) {
			log.error("获取正在进行的评选异常", e);
			throw new BusinessException("获取正在进行的评选异常", e);
		}
	}

	/**
	 * 获取正在进行的评选
	 * @see com.zssq.service.ITeamElectService#getLastTeamElect()
	 */
	@Override
	public TeamElect getLastTeamElect() throws BusinessException {
		try{
			TeamElect record = new TeamElect();
			record.setIsLatest(TeamConstants.ELECT_TYPE_1);
			
			List<TeamElect> teamElects = teamElectMapper.selectByRecord(record);
			if(CollectionUtils.isNotEmpty(teamElects)) {
				return teamElects.get(0);
			}
			return null;
		} catch (Exception e) {
			log.error("获取正在进行的评选异常", e);
			throw new BusinessException("获取正在进行的评选异常", e);
		}
	}

	/**
	 * 获取班组评选列表
	 * @see com.zssq.service.ITeamElectService#selectTeamElectList(com.zssq.dao.pojo.TeamElect)
	 */
	@Override
	public PageBean selectTeamElectList(TeamElect record,PageParam pageParam) throws BusinessException {
		try{
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<TeamElect> electList = teamElectMapper.selectByRecord(record); 
			int count = teamElectMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, electList);
		} catch (Exception e) {
			log.error("获取班组评选列表", e);
			throw new BusinessException("获取班组评选列表", e);
		}
	}


	/**
	 * 完成评选
	 * @see com.zssq.service.ITeamElectService#closeElect()
	 */
	@Override
	public boolean closeElect() throws BusinessException {
		try{
			TeamElect record = new TeamElect();
			record.setIsLatest(TeamConstants.ELECT_TYPE_2);
			List<TeamElect> teamElect = teamElectMapper.selectByRecord(record);
			if(teamElect.size()==0){
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"完成评选","无正在进行的评选"));
			}
			teamElectMapper.lastToHis();// 1 变 0 上一期评选变往期评选
			return teamElectMapper.nowToLast() > 0;// 2 变 1 正在进行的评选变上期评选
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("完成评选异常", e);
			throw new BusinessException("完成评选异常", e);
		}
	}
	
	
	
	/**
	 * 百强班组颁发荣誉标识
	 * @see com.zssq.service.ITeamElectService#awardHonor(java.util.List)
	 */
	@Override
	public boolean awardHonor(List<String> teamCodes) throws BusinessException {
		try {
			TeamElect teamElect = getLastTeamElect(); 
			if(teamElect != null && teamCodes.size() > 0 ){
				TeamRecommend record = new TeamRecommend();
				record.setTeamCodes(teamCodes);
				record.setTeamElectCode(teamElect.getTeamElectCode());
				record.setElectTime(DateUtils.getFormatDateLong());
				return teamRecommendMapper.awardHonor(record) > 1;//变标志  0 变1
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error("百强班组颁发荣誉", e);
			throw new BusinessException("百强班组颁发荣誉", e);
		}
	}
	
	/**
	 * 是否是百强班组
	 * @see com.zssq.service.ITeamElectService#isExcelent(java.lang.String)
	 */
	@Override
	public boolean isExcelent(String teamCode) throws BusinessException {
		try {
			TeamElect teamElect = getLastTeamElect();
			if(teamElect != null){
				TeamRecommend record = new TeamRecommend();
				record.setTeamCode(teamCode);
				record.setTeamElectCode(teamElect.getTeamElectCode());
				return teamRecommendMapper.selectByRecord(record).size() > 0;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error("是否是百强班组", e);
			throw new BusinessException("是否是百强班组", e);
		}
	}
	
	/**
	 * 拿到刚结束的百强班组
	 * @see com.zssq.service.ITeamElectService#selectLastHonorTeam()
	 */
	@Override
	public List<String> selectLastHonorTeam() throws BusinessException{
		try {
			TeamElect teamElect = getLastTeamElect();
			if(teamElect == null){
				return new ArrayList<String>();
			}
			return teamRecommendMapper.selectTeamCodeByElect(teamElect.getTeamElectCode());
		} catch (Exception e) {
			log.error("拿到刚结束的百强班组", e);
			throw new BusinessException("拿到刚结束的百强班组", e);
		}
	}
	
}
