package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.config.AuthConfig;
import com.zssq.constants.ComplaintConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.mapper.SequenceTeamMapper;
import com.zssq.dao.mapper.TeamFansMapper;
import com.zssq.dao.mapper.TeamFriendMapper;
import com.zssq.dao.mapper.TeamInfoMapper;
import com.zssq.dao.mapper.TeamMemberMapper;
import com.zssq.dao.mapper.TeamMessageMapper;
import com.zssq.dao.mapper.TeamMessageReplyMapper;
import com.zssq.dao.mapper.TeamZoneMapper;
import com.zssq.dao.pojo.HonorTeam;
import com.zssq.dao.pojo.MyManageTeam;
import com.zssq.dao.pojo.RecommondTeam;
import com.zssq.dao.pojo.SequenceTeam;
import com.zssq.dao.pojo.TeamCourse;
import com.zssq.dao.pojo.TeamFans;
import com.zssq.dao.pojo.TeamFriend;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMessage;
import com.zssq.dao.pojo.TeamMessageReply;
import com.zssq.dao.pojo.TeamRecommend;
import com.zssq.dao.pojo.TeamZone;
import com.zssq.dao.pojo.UserTeamState;
import com.zssq.dao.vo.MessageGPSVo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.ITeamInfoService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * @ClassName TeamInfoServiceImpl
 * @Description 班组信息服务
 * @author JiPengChun
 * @date 2017年3月15日 上午11:04:48
 * @version 1.0
 * @since JDK 1.7
 */
@Service("teamInfoService")
public class TeamInfoServiceImpl implements ITeamInfoService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 班组基本信息
	 */
	@Autowired
	private TeamInfoMapper teamInfoMapper;
	
	/**
	 * 班组粉丝
	 */
	@Autowired
	private TeamFansMapper teamFansMapper;
	
	/**
	 * 友好班组
	 */
	@Autowired
	private TeamFriendMapper teamFriendMapper;
	
	/**
	 * 班组留言板
	 */
	@Autowired
	private TeamMessageMapper teamMessageMapper;
	
	/**
	 * 班组留言回复
	 */
	@Autowired
	private TeamMessageReplyMapper teamMessageReplyMapper;
	
	/**
	 * 班组成员
	 */
	@Autowired
	private TeamMemberMapper teamMemberMapper;
	
	/**
	 * 新增班组主键生成
	 */
	@Autowired
	private SequenceTeamMapper sequenceTeamMapper;
	
	/**
	 * 记录该用户上次访问的班组编码(仅用于H5)
	 */
	@Autowired
	private TeamZoneMapper teamZoneMapper;
	
	/**
	 * 获取配置的默认头像
	 */
	@Autowired
	private AuthConfig authConfig;
	
	/**
	 * 获取班组列表(按组织CODE)
	 * @see com.zssq.service.ITeamInfoService#getTeamInfoList(com.zssq.utils.PageParam, com.zssq.dao.pojo.TeamInfo)
	 */
	@Override
	public PageBean getTeamInfoList(PageParam pageParam,TeamInfo record) throws BusinessException{
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<TeamInfo> dataList = teamInfoMapper.selectPage(record);
			int count = teamInfoMapper.selectPageCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("获取班组列表(按组织CODE)", e);
			throw new BusinessException("获取班组列表(按组织CODE)", e);
		}
	}
	
	/**
	 * 班组归档列表
	 * @see com.zssq.service.ITeamInfoService#selectRecordTeam(com.zssq.utils.PageParam, com.zssq.dao.pojo.TeamInfo)
	 */
	@Override
	public PageBean selectRecordTeam(PageParam pageParam, TeamInfo record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<TeamInfo> dataList = teamInfoMapper.selectTeamRecords(record);
			int count = teamInfoMapper.selectTeamRecordCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("班组归档列表", e);
			throw new BusinessException("班组归档列表", e);
		}
	}

	/**
	 * @Function addTeam
	 * @Description 新建班组
	 * @param record
	 * @return
	 */
	@Override
	public boolean addTeam(TeamInfo record) throws BusinessException{
		try {
			if(repeatName(record.getTeamName(),null)){
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"新建班组","班组名称重复"));
			}
			return teamInfoMapper.insertSelective(record) == 1;
		} catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			log.error("新建班组异常", e);
			throw new BusinessException("新建班组异常", e);
		}
	}
	
	/**
	 * 修改班组信息
	 * @throws BusinessException 
	 * @see com.zssq.service.ITeamInfoService#updateTeamInfo(com.zssq.dao.pojo.TeamInfo)
	 */
	@Override
	public boolean updateTeamInfo(String teamCode,String teamName,Byte teamType,String orgCode) throws BusinessException{
		try {
			if(repeatName(teamName,teamCode)){
				Message m = PropertiesUtil.getMessage(TeamConstants.RETURNCODE_TEAM_15004);
				throw new BusinessException(m.getCode(),String.format(m.getTip(),"编辑班组","班组名称重复"));
			}
			
			TeamInfo record = new TeamInfo();
	    	record.setTeamCode(teamCode);
	    	record.setTeamName(teamName);
	    	record.setTeamType(teamType);
	    	record.setOrgCode(orgCode);
			return teamInfoMapper.updateByCodeSelective(record) == 1;
		} catch(BusinessException e){
			throw e;
		}  catch (Exception e) {
			log.error("编辑班组异常", e);
			throw new BusinessException("编辑班组异常", e);
		}
	}

	/**
	 * 含有我的班组
	 * @see com.zssq.service.ITeamInfoService#getMyTeamList(java.lang.String)
	 */
	@Override
	public List<TeamInfo> getMyTeamList(String userCode) throws BusinessException {
		try{
			return teamInfoMapper.selectMyTeam(userCode);
		} catch (Exception e) {
			log.error("获得含有我的班组异常", e);
			throw new BusinessException("获得含有我的班组异常", e);
		}
	}
	

	/**
	 * 我管理的班组(根据我的组织获取)
	 * @see com.zssq.service.ITeamInfoService#getMyManageTeam(com.zssq.utils.PageParam, com.zssq.dao.pojo.RecommondTeam)
	 */
	@Override
	public PageBean getMyManageTeam(PageParam pageParam,MyManageTeam record) throws BusinessException{
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<MyManageTeam> dataList = teamInfoMapper.selectMyManageList(record);
			int count = teamInfoMapper.selectMyManageCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询我管理的班组异常", e);
			throw new BusinessException("查询我管理的班组异常", e);
		}
	}
	
	/**
	 * 我推荐的班组/我接收的推荐
	 * @see com.zssq.service.ITeamInfoService#getMyRecommondList(java.lang.String)
	 */
	@Override
	public PageBean getMyRecommondList(PageParam pageParam,TeamCourse record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<RecommondTeam> dataList = teamInfoMapper.selectMyRecommondList(record);
			int count = teamInfoMapper.selectMyRecommondCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("我推荐的班组/我接收的推荐异常", e);
			throw new BusinessException("我推荐的班组/我接收的推荐异常", e);
		}
	}

	/**
	 * 根据班组CODE获取班组信息    team_info表
	 * @see com.zssq.service.ITeamInfoService#selectByCode(java.lang.String)
	 */
	@Override
	public TeamInfo selectByCode(String teamCode) throws BusinessException {
		try {
			TeamInfo teamInfo = teamInfoMapper.selectByCode(teamCode);
			if(teamInfo != null && StringTools.isEmpty(teamInfo.getTeamIcon())){
				teamInfo.setTeamIcon(authConfig.getTeamIcon());
			}
			return teamInfo;
		} catch (Exception e) {
			log.error("根据班组CODE获取班组信息异常", e);
			throw new BusinessException("根据班组CODE获取班组信息异常", e);
		}
	}

	/**
	 * 获取班组头信息
	 * @see com.zssq.service.ITeamInfoService#selectHeadInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public TeamInfo selectHeadInfo(String userCode, String teamCode) throws BusinessException {
		try {
			TeamMember record = new TeamMember();
			record.setUserCode(userCode);
			record.setTeamCode(teamCode);
			TeamInfo teamInfo = teamInfoMapper.selectHeadInfo(record);
//			//我加入该班组,team_zone更新状态
//			if(teamInfo != null && teamInfo.getIsLeader() != null){
//				String teamZoneCode = teamZoneMapper.selectByUserCode(userCode);
//				TeamZone teamZone = new TeamZone();
//				teamZone.setUserCode(userCode);
//				teamZone.setTeamCode(teamCode);
//				if(teamZoneCode == null){//插入不到插入
//					teamZoneMapper.insert(teamZone);
//				}else if(!teamZoneCode.equals(teamCode)){//查到了,不一样更新
//					teamZoneMapper.updateByUserCode(teamZone);
//				}
//			}
			return teamInfo;
		} catch (Exception e) {
			log.error("获取班组头信息异常", e);
			throw new BusinessException("获取班组头信息异常", e);
		}
	}
	
	/**
	 * 添加班组访问记录
	 * @see com.zssq.service.ITeamInfoService#addTeamZone(java.lang.String, java.lang.String)
	 */
	@Override
	public void addTeamZone(String userCode,String teamCode) throws BusinessException{
		try {
			String teamZoneCode = teamZoneMapper.selectByUserCode(userCode);
			TeamZone teamZone = new TeamZone();
			teamZone.setUserCode(userCode);
			teamZone.setTeamCode(teamCode);
			if(teamZoneCode == null){//查询不到,插入
				teamZoneMapper.insert(teamZone);
			}else if(!teamZoneCode.equals(teamCode)){//查到了,不一样更新
				teamZoneMapper.updateByUserCode(teamZone);
			}
		} catch (Exception e) {
			log.error("添加班组访问记录异常", e);
			throw new BusinessException("添加班组访问记录", e);
		}
	}
	
	/**
	 * 拿到记录的访问班组
	 * @see com.zssq.service.ITeamInfoService#getTeamZone(java.lang.String)
	 */
	@Override
	public String getTeamZone(String userCode) throws BusinessException{
		try {
			return teamZoneMapper.selectByUserCode(userCode);
		} catch (Exception e) {
			log.error("拿到记录的访问班组异常", e);
			throw new BusinessException("拿到记录的访问班组", e);
		}
	}

	/**
	 * 更新班组简介
	 * @see com.zssq.service.ITeamInfoService#updateTeamIntro(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateTeamIntro(String teamCode, String intro) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			record.setTeamCode(teamCode);
			record.setTeamIntro(intro);
			return teamInfoMapper.updateByCodeSelective(record) == 1;
		} catch (Exception e) {
			log.error("更新班组简介异常", e);
			throw new BusinessException("更新班组简介异常", e);
		}
	}

	/**
	 * 更新班组头像
	 * @see com.zssq.service.ITeamInfoService#updateTeamHead(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateTeamHead(String teamCode, String head) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			record.setTeamCode(teamCode);
			record.setTeamIcon(head);
			return teamInfoMapper.updateByCodeSelective(record) == 1;
		} catch (Exception e) {
			log.error("更新班组头像异常", e);
			throw new BusinessException("更新班组头像异常", e);
		}
	}

	/**
	 * 新增粉丝
	 * @see com.zssq.service.ITeamInfoService#addFans(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addFans(String teamCode, String userCode) throws BusinessException {
		try {
			TeamFans record = new TeamFans();
			record.setTeamCode(teamCode);
			record.setUserCode(userCode);
			int count = teamFansMapper.selectCount(record);
			if(count > 0){
				return true;
			}
			record = new TeamFans();
			record.setTeamFansCode(UUIDHelper.getUUID());
			record.setTeamCode(teamCode);
			record.setUserCode(userCode);
			record.setCreateTime(DateUtils.getFormatDateLong());
			return teamFansMapper.insertSelective(record) == 1;
		} catch (Exception e) {
			log.error("增加班组粉丝异常", e);
			throw new BusinessException("增加班组粉丝异常", e);
		}
	}

	/**
	 * 是否为组粉丝
	 * @see com.zssq.service.ITeamInfoService#isFans(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isFans(String teamCode, String userCode) throws BusinessException {
		try {
			TeamFans record = new TeamFans();
			record.setTeamCode(teamCode);
			record.setUserCode(userCode);
			return teamFansMapper.selectCount(record) >= 1;
		} catch (Exception e) {
			log.error("是否为组粉丝异常", e);
			throw new BusinessException("是否为组粉丝异常", e);
		}
	}

	/**
	 * 移除粉丝
	 * @see com.zssq.service.ITeamInfoService#delFans(java.lang.String)
	 */
	@Override
	public String delFans(String code) throws BusinessException {
		try {
			TeamFans teamFans = teamFansMapper.selectByCode(code);
			if(teamFansMapper.deleteByCode(code) == 1){
				return teamFans.getTeamCode();
			}else{
				return "";
			}
		} catch (Exception e) {
			log.error("移除组粉丝异常", e);
			throw new BusinessException("移除组粉丝异常", e);
		}
	}

	/**
	 * 取消对班组的关注
	 * @see com.zssq.service.ITeamInfoService#unfollow(java.lang.String, java.lang.String)
	 */
	@Override
	public String unfollow(String userCode, String teamCode) throws BusinessException {
		try {
			TeamFans record = new TeamFans();
			record.setUserCode(userCode);
			record.setTeamCode(teamCode);
			List<TeamFans> teamFanList = teamFansMapper.selectPage(record);
			if(teamFanList.size() > 0){
				teamFansMapper.unfollow(record);
				return teamFanList.get(0).getTeamCode();
			}else{
				return "";
			}
			
		} catch (Exception e) {
			log.error("取消对班组的关注异常", e);
			throw new BusinessException("取消对班组的关注异常", e);
		}
	}

	/**
	 * 粉丝列表
	 * @see com.zssq.service.ITeamInfoService#selectFans(com.zssq.utils.PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectFans(PageParam pageParam, String teamCode) throws BusinessException {
		try {
			TeamFans record = new TeamFans();
			record.setTeamCode(teamCode);
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());	
			}
			List<TeamFans> dataList = teamFansMapper.selectPage(record);
			int count = teamFansMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("获取班组粉丝异常", e);
			throw new BusinessException("获取班组粉丝异常", e);
		}
	}
	
	/**
	 * 我关注的班组列表
	 * @see com.zssq.service.ITeamInfoService#selectConcernsTeam(com.zssq.utils.PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectConcernsTeam(PageParam pageParam, String userCode) throws BusinessException {
		try {
			TeamFans record = new TeamFans();
			record.setUserCode(userCode);
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());	
			}
			List<TeamFans> dataList = teamFansMapper.selectConcersTeamPage(record);
			TeamMember teamMember = new TeamMember();
			for (int i = 0; i < dataList.size(); i++) {
				teamMember.setTeamCode(dataList.get(i).getTeamCode());
				List<String> memList = teamMemberMapper.selectTop3(teamMember);
				dataList.get(i).setTeamMember(memList);
			}
			int count = teamFansMapper.selectConcersTeamCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("我关注的班组列表异常", e);
			throw new BusinessException("我关注的班组列表异常", e);
		}
	}

	/**
	 * @Function addFriendTeam
	 * @Description 添加友好班组
	 * @param teamCode
	 * @param friendTeamCode
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public boolean addFriendTeam(String userCode, String teamCode) throws BusinessException {
		try {
			if(StringTools.isEmpty(teamCode)){
				return false;
			}
			//查询我是班组长得班组
			TeamMember record = new TeamMember();
			record.setUserCode(userCode);
			record.setIsLeader(TeamConstants.BOOLEAN_TRUE);
			List<TeamMember> memList = teamMemberMapper.selectPage(record);
			//查询友好班组是这个班组的班组列表
			TeamFriend param = new TeamFriend();
			param.setFriendTeamCode(teamCode);
			List<TeamFriend> teamFriendList = teamFriendMapper.selectPage(param);
			//这个班组是不是我的班组的友好班组
			for (int i = 0; i < memList.size(); i++) {
				for (int j = 0; j < teamFriendList.size(); j++) {
					if(memList.get(i).getTeamCode().equals(teamFriendList.get(j).getTeamCode())){
						memList.remove(i);
						i--;
						break;
					}
				}
			}
			for (int i = 0; i < memList.size(); i++) {
				if(!teamCode.equals(memList.get(i).getTeamCode())){
					//不是插入
					TeamFriend teamFriend = new TeamFriend();
					teamFriend.setTeamFriendCode(UUIDHelper.getUUID());
					teamFriend.setTeamCode(memList.get(i).getTeamCode());
					teamFriend.setFriendTeamCode(teamCode);
					teamFriend.setCreateTime(DateUtils.getFormatDateLong());
					teamFriendMapper.insertSelective(teamFriend);
				}
			}
			return true;
		} catch (Exception e) {
			log.error("添加友好班组异常", e);
			throw new BusinessException("添加友好班组异常", e);
		}
	}

	/**
	 * 移除友好班组
	 * @see com.zssq.service.ITeamInfoService#delFriendTeam(java.lang.String)
	 */
	@Override
	public boolean delFriendTeam(String code) throws BusinessException {
		try {
			return teamFriendMapper.deleteByCode(code) == 1;
		} catch (Exception e) {
			log.error("移除友好班组异常", e);
			throw new BusinessException("移除友好班组异常", e);
		}
	}

	/**
	 * 是否友好班组
	 * @see com.zssq.service.ITeamInfoService#isFriendTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isFriendTeam(String myTeam, String yourTeam) throws BusinessException {
		try {
			TeamFriend record = new TeamFriend();
			record.setTeamCode(myTeam);
			record.setFriendTeamCode(yourTeam);
			return teamFriendMapper.selectCount(record) >= 1;
		} catch (Exception e) {
			log.error("是否友好班组异常", e);
			throw new BusinessException("是否友好班组异常", e);
		}
	}

	/**
	 * 查询友好班组列表
	 * @see com.zssq.service.ITeamInfoService#selectFriendTeam(com.zssq.utils.PageParam, com.zssq.dao.pojo.TeamFriend)
	 */
	@Override
	public PageBean selectFriendTeam(PageParam pageParam, String teamCode) throws BusinessException {
		try {
			TeamFriend record = new TeamFriend();
			record.setTeamCode(teamCode);
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());	
			}
			List<TeamFriend> dataList = teamFriendMapper.selectPage(record);
			int count = teamFriendMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询友好班组列表异常", e);
			throw new BusinessException("查询友好班组列表异常", e);
		}
	}

	/**
	 * 我关注的班组
	 * @see com.zssq.service.ITeamInfoService#selectConcernsTeam(java.lang.String)
	 */
	@Override
	public List<String> selectConcernsTeam(String userCode) throws BusinessException {
		try {
			return teamFansMapper.selectConcernsTeam(userCode);
		} catch (Exception e) {
			log.error("我关注的班组", e);
			throw new BusinessException("我关注的班组", e);
		}
	}

	/**
	 * 班组留言
	 * @see com.zssq.service.ITeamInfoService#addTeamMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TeamMessage addTeamMessage(String teamCode, String userCode, String message) throws BusinessException {
		try {
			TeamMessage record = new TeamMessage();
			record.setTeamMessageCode(UUIDHelper.getUUID());
			record.setTeamCode(teamCode);
			record.setUserCode(userCode);
			record.setContent(message);
			record.setCreateTime(DateUtils.getFormatDateLong());
			record.setIsDelete(TeamConstants.BOOLEAN_FALSE);
			teamMessageMapper.insertSelective(record);
			return record;
		} catch (Exception e) {
			log.error("班组留言异常", e);
			throw new BusinessException("班组留言异常", e);
		}
	}

	/**
	 * 删除班组留言
	 * @see com.zssq.service.ITeamInfoService#delTeamMessage(java.lang.String)
	 */
	@Override
	public TeamMessage delTeamMessage(String messageCode) throws BusinessException {
		try {
			TeamMessage teamMessage = teamMessageMapper.selectByCode(messageCode);
			teamMessageReplyMapper.deleteByMesCode(messageCode);
			teamMessageMapper.deleteByCode(messageCode);
			return teamMessage;
		} catch (Exception e) {
			log.error("删除班组留言异常", e);
			throw new BusinessException("删除班组留言异常", e);
		}
	}

	/**
	 * 查询班组留言列表
	 * @see com.zssq.service.ITeamInfoService#selectTeamMeassage(com.zssq.utils.PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectTeamMeassage(PageParam pageParam, String teamCode) throws BusinessException {
		try {
			TeamMember teamMember = new TeamMember();
			teamMember.setTeamCode(teamCode);
			teamMember.setIsLeader(TeamConstants.BOOLEAN_TRUE);
			List<TeamMember> memList = teamMemberMapper.selectPage(teamMember);
			List<String> leaders = new ArrayList<String>();
			for (int j = 0; j < memList.size(); j++) {
				leaders.add(memList.get(j).getUserCode());
			}
			TeamMessage record = new TeamMessage();
			record.setTeamCode(teamCode);
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());	
			}
			List<TeamMessage> dataList = teamMessageMapper.selectPage(record);//查询留言
			List<String> mesCodes = new ArrayList<String>();
			for (int i = 0; i < dataList.size(); i++) {
				mesCodes.add(dataList.get(i).getTeamMessageCode());
			}
			if(mesCodes.size() > 0){
				List<TeamMessageReply> replyList = teamMessageReplyMapper.selectByMesCodes(mesCodes);//查询留言对应的回复
				for (int i = 0; i < dataList.size(); i++) {
					List<TeamMessageReply> reply = new ArrayList<TeamMessageReply>();
					for (int j = 0; j < replyList.size(); j++) {
						if(dataList.get(i).getTeamMessageCode().equals(replyList.get(j).getMessageCode())){
							reply.add(replyList.get(j));
							replyList.remove(j);
							j--;//遍历过的会被移除
						}
					}
					dataList.get(i).setTeamMessageReply(reply);
					dataList.get(i).setLeaders(leaders);
				}
			}
			int count = teamMessageMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询班组留言列表异常", e);
			throw new BusinessException("查询班组留言列表异常", e);
		}
	}

	/**
	 * 查询留言单条
	 * @see com.zssq.service.ITeamInfoService#selectTeamMeassage(java.lang.String)
	 */
	@Override
	public TeamMessage selectTeamMeassage(String teamMessageCode) throws BusinessException {
		try {
			return teamMessageMapper.selectByCode(teamMessageCode);
		} catch (Exception e) {
			log.error("查询留言单条异常", e);
			throw new BusinessException("查询留言单条异常", e);
		}
	}

	/**
	 * 班组长回复留言
	 * @see com.zssq.service.ITeamInfoService#addMesReply(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TeamMessageReply addMesReply(String mesCode, String userCode,String replyUserCode, String reply) throws BusinessException {
		try {
			TeamMessageReply record = new TeamMessageReply();
			record.setReplyCode(UUIDHelper.getUUID());
			record.setMessageCode(mesCode);
			record.setUserCode(userCode);
			record.setReplyUserCode(replyUserCode);
			record.setReplyMessaage(reply);
			record.setCreateTime(DateUtils.getFormatDateLong());
			teamMessageReplyMapper.insertSelective(record);
			return  record;
		} catch (Exception e) {
			log.error("班组长回复留言", e);
			throw new BusinessException("班组长回复留言", e);
		}
	}

	/**
	 * 删除班组长回复留言
	 * @see com.zssq.service.ITeamInfoService#delMesReply(java.lang.String)
	 */
	@Override
	public boolean delMesReply(String replyCode) throws BusinessException {
		try {
			return teamMessageReplyMapper.deleteByCode(replyCode) == 1;
		} catch (Exception e) {
			log.error("删除班组长回复留言", e);
			throw new BusinessException("删除班组长回复留言", e);
		}
	}

	/**
	 * 查询颁发荣誉的百强班组
	 * @see com.zssq.service.ITeamInfoService#selectHonorTeam(com.zssq.utils.PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectHonorTeam(PageParam pageParam, String electCode) throws BusinessException {
		try {
			TeamRecommend record = new TeamRecommend();
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			record.setTeamElectCode(electCode);
			List<HonorTeam> dataList = teamInfoMapper.selectHonorTeamPage(record);
			int count = teamInfoMapper.selectHonorTeamCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询颁发荣誉的百强班组异常", e);
			throw new BusinessException("查询颁发荣誉的百强班组异常", e);
		}
	}


	/**
	 * 进入班组空间  显示  我是否关注这个班组 这个班组是否是我的班组的友好班组
	 * @see com.zssq.service.ITeamInfoService#userState(java.lang.String, java.lang.String)
	 */
	@Override
	public UserTeamState userState(String userCode, String teamCode) throws BusinessException {
		try {
			UserTeamState userState = new UserTeamState();
			userState.setIsFriendTeam(TeamConstants.BOOLEAN_FALSE);
			userState.setIsConcern(TeamConstants.BOOLEAN_FALSE);
			//查询我是班组长得班组
			TeamMember record = new TeamMember();
			record.setUserCode(userCode);
			record.setIsLeader(TeamConstants.BOOLEAN_TRUE);
			List<String> memList = teamMemberMapper.selectTeamCode(record);
			if(memList.size() == 0){//我不是班组长
				userState.setIsFriendTeam(Byte.valueOf("2"));
			}else{
				//查询友好班组是这个班组的班组集合
				List<String> teamFriendList = teamFriendMapper.selectTeamCode(teamCode);
				if(teamFriendList.containsAll(memList)){//全部包含,是友好
					userState.setIsFriendTeam(TeamConstants.BOOLEAN_TRUE);
				}
			}
			
			//我是不是这个班组的粉丝
			TeamFans teamFans = new TeamFans();
			teamFans.setUserCode(userCode);
			teamFans.setTeamCode(teamCode);
			if(teamFansMapper.selectPage(teamFans).size()>0){
				userState.setIsConcern(TeamConstants.BOOLEAN_TRUE);
			}
			return userState;
		} catch (Exception e) {
			log.error("进入班组空间  显示  我是否关注这个班组 这个班组是否是我的班组的友好班组", e);
			throw new BusinessException("进入班组空间  显示  我是否关注这个班组 这个班组是否是我的班组的友好班组", e);
		}
	}

	/**
	 * 根据name(模糊)查询code
	 * @see com.zssq.service.ITeamInfoService#selectCodeByName(java.lang.String)
	 */
	@Override
	public List<String> selectCodeByName(String name) throws BusinessException {
		try {
			return teamInfoMapper.selectCodeByName(name);
		} catch (Exception e) {
			log.error("根据name(模糊)查询code", e);
			throw new BusinessException("根据name(模糊)查询code", e);
		}
	}

	/**
	 * 根据teamCodes返回多个对象(人数/班组orgCode/班组名称)
	 * @see com.zssq.service.ITeamInfoService#selectByTeamCodes(java.util.List)
	 */
	@Override
	public List<TeamInfo> selectByTeamCodes(List<String> teamCodes) throws BusinessException {
		try {
			return teamInfoMapper.selectByTeamCodes(teamCodes);
		} catch (Exception e) {
			log.error("根据teamCodes返回多个对象(人数/班组orgCode/班组名称)", e);
			throw new BusinessException("根据teamCodes返回多个对象(人数/班组orgCode/班组名称)", e);
		}
	}

	/**
	 * 根据orgCode返回班组信息
	 * @see com.zssq.service.ITeamInfoService#selectByOrgCode(java.lang.String)
	 */
	@Override
	public PageBean selectByOrgCode(PageParam pageParam,String orgCode) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setOrgCode(orgCode);
			List<TeamInfo> dataList = teamInfoMapper.selectByOrgCode(record);
			int count = teamInfoMapper.selectByOrgCodeCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("根据orgCode返回班组信息", e);
			throw new BusinessException("根据orgCode返回班组信息", e);
		}
	}
	
	/**
	 * 按班组名称查询数量
	 * @see com.zssq.service.ITeamInfoService#repeatName(java.lang.String)
	 */
	@Override
	public boolean repeatName(String teamName,String teamCode) throws BusinessException{
		try {
			TeamInfo record = new TeamInfo();
			record.setTeamName(teamName);
			record.setTeamCode(teamCode);
			return teamInfoMapper.selectRepeatName(record) > 0;
		} catch (Exception e) {
			log.error("按班组名称查询数量", e);
			throw new BusinessException("按班组名称查询数量", e);
		}
	}

	/**
	 * 获取班组主键
	 * @see com.zssq.service.ITeamInfoService#getTeamCode()
	 */
	@Override
	public String getTeamCode() throws BusinessException {
		try {
			SequenceTeam record = new SequenceTeam();
			int i = sequenceTeamMapper.insert(record);
			if(i>0){
				Calendar c = Calendar.getInstance();
				String s = c.get(Calendar.YEAR)+"";
				String teamId = StringUtils.leftPad(record.getId()+"", 5, "0");
				return TeamConstants.TEAM_NAME_PREFIX + s.substring(2, 4) + teamId;
			}else{
				throw new BusinessException();
			}
		} catch (Exception e) {
			log.error("获取班组主键", e);
			throw new BusinessException("获取班组主键", e);
		}
	}

	/**
	 * 根据班组名称查询
	 * @see com.zssq.service.ITeamInfoService#selectByName(com.zssq.utils.PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectByName(PageParam pageParam, String teamName) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setTeamName(teamName);
			List<TeamInfo> dataList = teamInfoMapper.selectByName(record);
			int count = teamInfoMapper.selectByNameCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("获取班组主键", e);
			throw new BusinessException("获取班组主键", e);
		}
	}

	/**
	 * 班组留言举报/取消举报
	 * @see com.zssq.service.ITeamInfoService#complaintTeamMessage(java.lang.String, java.lang.Byte, java.lang.Byte)
	 */
	@Override
	public void complaintTeamMessage(String code, Byte type, Byte action) throws BusinessException {
		try {
			if(ComplaintConstants.COMPLAINT_TYPE_APP.equals(type)){//1.评论
				TeamMessage record = new TeamMessage();
				if(ComplaintConstants.COMPLAINT_YES.equals(action)){
					record.setIsDelete(TeamConstants.BOOLEAN_TRUE);
					record.setTeamMessageCode(code);
					teamMessageMapper.complaintMessage(record);
				}else if(ComplaintConstants.COMPLAINT_NO.equals(action)){
					record.setIsDelete(TeamConstants.BOOLEAN_FALSE);
					record.setTeamMessageCode(code);
					teamMessageMapper.complaintMessage(record);
				}
			}else if(ComplaintConstants.COMPLAINT_TYPE_COMMENT.equals(type)){//2.回复
				TeamMessageReply record = new TeamMessageReply();
				if(ComplaintConstants.COMPLAINT_YES.equals(action)){
					record.setIsDelete(TeamConstants.BOOLEAN_TRUE);
					record.setReplyCode(code);
					teamMessageReplyMapper.complaintMessageReply(record);
				}else if(ComplaintConstants.COMPLAINT_NO.equals(action)){
					record.setIsDelete(TeamConstants.BOOLEAN_FALSE);
					record.setReplyCode(code);
					teamMessageReplyMapper.complaintMessageReply(record);
				}
			}
		} catch (Exception e) {
			log.error("班组留言举报/取消举报", e);
			throw new BusinessException("班组留言举报/取消举报", e);
		}
	}

	@Override
	public PageBean selectTeamMessage(List<String> teamCodes, PageParam pageParam) throws BusinessException {
		try {
			TeamMessage record = new TeamMessage();
			record.setTeamCodes(teamCodes);
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());	
			List<TeamMessage> dataList = teamMessageMapper.selectPage(record);
			int count = teamMessageMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("班组留言列表(内容审核)", e);
			throw new BusinessException("班组留言列表(内容审核)", e);
		}
	}

	@Override
	public List<String> selectByOrgCode(String orgCode) throws BusinessException {
		try {
			return teamInfoMapper.selectCodesByOrg(orgCode);	
		} catch (Exception e) {
			log.error("根据组织机构查询班组编码集合", e);
			throw new BusinessException("根据组织机构查询班组编码集合", e);
		}
	}

	@Override
	public List<TeamMessageReply> selectReply(long id, int pageCount, String messageCode) throws BusinessException {
		try {
			TeamMessageReply record = new TeamMessageReply();
			record.setId(id);
			record.setMessageCode(messageCode);
			record.setPageCount(pageCount);
			return teamMessageReplyMapper.selectReplyPage(record);
		} catch (Exception e) {
			log.error("根据组织机构查询班组编码集合", e);
			throw new BusinessException("根据组织机构查询班组编码集合", e);
		}
	}

	@Override
	public void teamArchive(String teamCode,String url,String userCode) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			record.setTeamCode(teamCode);
			record.setRecordFileUrl(url);
			record.setIsRecord(TeamConstants.BOOLEAN_TRUE);
			record.setIsDissolve(TeamConstants.BOOLEAN_TRUE);
			record.setDissolveTime(DateUtils.getFormatDateLong());
			record.setUserCode(userCode);
			teamInfoMapper.updateByCodeSelective(record);
		} catch (Exception e) {
			log.error("班组归档异常", e);
			throw new BusinessException("班组归档异常", e);
		}
	}
	
	@Override
	public PageBean searchTeamMessageGPS(PageParam pageParam, MessageGPSVo messageGPSVo) throws BusinessException {
		
		try {
			TeamMember teamMember = new TeamMember();
			teamMember.setTeamCode(messageGPSVo.getTeamCode());
			teamMember.setIsLeader(TeamConstants.BOOLEAN_TRUE);						
			
			messageGPSVo.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			messageGPSVo.setLimitEnd(pageParam.getPageSize());
			
			// 根据班组Code查询留言信息，并过滤掉指定的留言编码
			List<TeamMessage> messageList = teamMessageMapper.selectTeamMessageGPS(messageGPSVo);
			
			if(pageParam.getPageNo() == 0) {
				// 根据留言Code查询留言信息，追加进留言集合，并保证放在集合中的第一位
				TeamMessage messageGPS = teamMessageMapper.selectByCode(messageGPSVo.getMessageCode());
				messageList.add(0, messageGPS);
			}			
			
			List<String> messageCodes = new ArrayList<String>();
			for (TeamMessage teamMessage : messageList) {
				messageCodes.add(teamMessage.getTeamMessageCode());
			}
			
			if(messageCodes.size() > 0){
				List<TeamMessageReply> replyList = teamMessageReplyMapper.selectByMesCodes(messageCodes);
				for (int i = 0; i < messageList.size(); i++) {
					
					List<TeamMessageReply> reply = new ArrayList<TeamMessageReply>();
					for (int j = 0; j < replyList.size(); j++) {
						if(messageList.get(i).getTeamMessageCode().equals(replyList.get(j).getMessageCode())){
							if(StringUtils.equals(messageGPSVo.getReplyCode(), replyList.get(j).getMessageCode())) {
								reply.add(0, replyList.get(j));
							} else {
								reply.add(replyList.get(j));
							}													
							replyList.remove(j);
							j--;
						}
					}
					messageList.get(i).setTeamMessageReply(reply);
				}
			}
			int count = teamMessageMapper.selectTeamMessageGPSCount(messageGPSVo);
			return new PageBean(messageGPSVo.getLimitStart(), messageGPSVo.getLimitEnd(), count, messageList);
		} catch (Exception e) {
			log.error("查询班组留言列表异常", e);
			throw new BusinessException("查询班组留言列表异常", e);
		}
	}

	
	@Override
	public void teamArchiving(String teamCode, Byte archiving) throws BusinessException {
		try {
			TeamInfo record = new TeamInfo();
			record.setTeamCode(teamCode);
			record.setIsRecord(archiving);
			teamInfoMapper.updateByCodeSelective(record);
		} catch (Exception e) {
			log.error("班组归档状态变更异常", e);
			throw new BusinessException("班组归档状态变更异常", e);
		}
		
	}

	@Override
	public List<String> selectTeamCodesByOrgs(List<String> orgCode) throws BusinessException {
		try {
			return teamInfoMapper.selectTeamCodesByOrgs(orgCode);
		} catch (Exception e) {
			log.error("根据组织机构编码,返回班组编码异常", e);
			throw new BusinessException("根据组织机构编码,返回班组编码异常", e);
		}
	}

	@Override
	public List<String> selectDisTeamCodes(String tenantCode) throws BusinessException {
		try {
			return teamInfoMapper.selectDisTeamCodes(tenantCode);
		} catch (Exception e) {
			log.error("返回解散的班组编码集合异常", e);
			throw new BusinessException("返回解散的班组编码集合异常", e);
		}
	}	

}
