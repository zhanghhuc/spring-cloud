package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.TeamConstants;
import com.zssq.dao.mapper.TeamInfoMapper;
import com.zssq.dao.mapper.TeamMemberMapper;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMemberVo;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ITeamMemberService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * @ClassName TeamMemberServiceImpl
 * @Description 班组成员
 * @author JiPengChun
 * @date 2017年3月15日 下午5:44:16
 * @version 1.0
 * @since JDK 1.7
 */
@Service("teamMemberService")
public class TeamMemberServiceImpl implements ITeamMemberService {

	/**
	 * 班组成员
	 */
	@Autowired
	private TeamMemberMapper teamMemberMapper;
	
	/**
	 * 班组成员
	 */
	@Autowired
	private TeamInfoMapper teamInfoMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 批量插入组长/组员
	 * @see com.zssq.service.ITeamMemberService#batchAdd(java.util.List)
	 */
	@Override
	public boolean batchAdd(List<TeamMember> memList) throws BusinessException {
		try {
			return teamMemberMapper.batchInsert(memList) >= 1;
		} catch (Exception e) {
			log.error("批量插入组长/组员异常", e);
			throw new BusinessException("批量插入组长/组员异常", e);
		}
	}
	
	/**
	 * 添加成员
	 * @see com.zssq.service.ITeamMemberService#addTeamMember(com.zssq.dao.pojo.TeamMember)
	 */
	@Override
	public boolean addTeamMember(TeamMember record) throws BusinessException{
		try {
			return teamMemberMapper.insertSelective(record) == 1;
		} catch (Exception e) {
			log.error("添加成员异常", e);
			throw new BusinessException("添加成员异常", e);
		}
	}

	/**
	 * 移除成员
	 * @see com.zssq.service.ITeamMemberService#delTeamMember(java.lang.String)
	 */
	@Override
	public boolean delTeamMember(String memberCode) throws BusinessException{
		try {
			return teamMemberMapper.deleteByCode(memberCode) == 1;
		} catch (Exception e) {
			log.error("移除成员异常", e);
			throw new BusinessException("移除成员异常", e);
		}
	}

	/**
	 * 根据Record更新
	 * @see com.zssq.service.ITeamMemberService#setIsLeader(com.zssq.dao.pojo.TeamMember)
	 */
	@Override
	public boolean updateByRecord(TeamMember record) throws BusinessException{
		try {
			return teamMemberMapper.updateByCodeSelective(record) >= 1;
		} catch (Exception e) {
			log.error("变更班组成员异常", e);
			throw new BusinessException("变更班组成员异常", e);
		}
	}

	/**
	 * 班组成员查询
	 * @see com.zssq.service.ITeamMemberService#getTeamMemList(PageParam, com.zssq.dao.pojo.TeamMember)
	 */
	@Override
	public PageBean getTeamMemList(PageParam pageParam,TeamMember record) throws BusinessException {
		try {
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			List<TeamMember> dataList = teamMemberMapper.selectPage(record);
			int count = teamMemberMapper.selectPageCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询班组成员异常", e);
			throw new BusinessException("查询班组成员异常", e);
		}
	}

	/**
	 * 根据userCode返回班组列表(该用户所在的班组集合)
	 * @see com.zssq.service.ITeamMemberService#selectTeamByUser(java.lang.String)
	 */
	@Override
	public List<TeamInfo> selectTeamByUser(String userCode) throws BusinessException {
		try {
			TeamMember teamMember = new TeamMember();
			teamMember.setUserCode(userCode);
			List<TeamMember> memList = teamMemberMapper.selectPage(teamMember);
			List<String> teamCodes = new ArrayList<>();
			for (int i = 0; i < memList.size(); i++) {
				teamCodes.add(memList.get(i).getTeamCode());
			}
			if(teamCodes.size() == 0){
				return null;
			}else{
				return teamInfoMapper.selectByCodes(teamCodes);
			}
		} catch (Exception e) {
			log.error("根据userCode返回班组列表异常", e);
			throw new BusinessException("根据userCode返回班组列表异常", e);
		}
	}

	/**
	 * 根据班组code查询班组成员
	 * @see com.zssq.service.ITeamMemberService#selectByTeamCode(java.lang.String)
	 */
	@Override
	public List<TeamMember> selectByTeamCode(String teamCode) throws BusinessException {
		try {
			return teamMemberMapper.selectByTeamCode(teamCode);
		} catch (Exception e) {
			log.error("根据班组code查询班组成员", e);
			throw new BusinessException("根据班组code查询班组成员", e);
		}
	}

	/**
	 * 批量插入组长和组员
	 * @see com.zssq.service.ITeamMemberService#batchAddAll(java.util.List, java.lang.String)
	 */
	@Override
	public List<TeamMemberVo> batchAddAll(List<TeamMemberVo> memList,String teamCode) throws BusinessException {
		try {
			//查询班组所有成员
			List<String> userCodes = this.selectUserCodeByTeamCode(teamCode);
			List<TeamMember> teamMemberList = new ArrayList<TeamMember>();
			for (int i = 0; i < memList.size(); i++) {
				//去重
				if(!userCodes.contains(memList.get(i).getUserCode())){
					TeamMember teamMember = new TeamMember();
					teamMember.setTeamMemberCode(UUIDHelper.getUUID());
					teamMember.setTeamCode(teamCode);
					teamMember.setCreateTime(DateUtils.getFormatDateLong());
					teamMember.setIsLeader(memList.get(i).getIsLeader());
					teamMember.setUserCode(memList.get(i).getUserCode());
					teamMemberList.add(teamMember);
				}else{
					memList.remove(i);
					i--;
				}
			}
			if(memList.size() != 0){
				teamMemberMapper.batchInsert(teamMemberList);
			}
			return memList;
		} catch (Exception e) {
			log.error("批量插入组长和组员异常", e);
			throw new BusinessException("批量插入组长和组员异常", e);
		}
	}
	
	/**
	 * 根据teamCode查询班组长code
	 * @see com.zssq.service.ITeamMemberService#selectTeamLeaders(java.lang.String)
	 */
	@Override
	public List<String> selectTeamLeaders(String teamCode) throws BusinessException {
		try {
			return teamMemberMapper.selectLeaderByTeamCode(teamCode);
		} catch (Exception e) {
			log.error("根据teamCode查询班组长code", e);
			throw new BusinessException("根据teamCode查询班组长code", e);
		}
	}

	/**
	 * 根据班组成员唯一标识查询
	 * @see com.zssq.service.ITeamMemberService#selectByCode(java.lang.String)
	 */
	@Override
	public TeamMember selectByCode(String teamMemberCode) throws BusinessException {
		try {
			return teamMemberMapper.selectByCode(teamMemberCode);
		} catch (Exception e) {
			log.error("根据班组成员唯一标识查询", e);
			throw new BusinessException("根据班组成员唯一标识查询", e);
		}
	}
	
	
	/**
	 * 根据班组成员唯一标识查询
	 * @see com.zssq.service.ITeamMemberService#selectByCodes(java.util.List)
	 */
	@Override
	public List<TeamMember> selectByCodes(List<String> teamMemberCodes) throws BusinessException {
		try {
			TeamMember teamMember = new TeamMember();
			teamMember.setTeamMemberCodes(teamMemberCodes);
			return teamMemberMapper.selectPage(teamMember);
		} catch (Exception e) {
			log.error("根据班组成员唯一标识查询", e);
			throw new BusinessException("根据班组成员唯一标识查询", e);
		}
	}

	/**
	 * 根据班组code,查询班组成员code
	 * @see com.zssq.service.ITeamMemberService#selectUserCodeByTeamCode(java.lang.String)
	 */
	@Override
	public List<String> selectUserCodeByTeamCode(String teamCode) throws BusinessException {
		try {
			return teamMemberMapper.selectUserCodeByTeamCode(teamCode);
		} catch (Exception e) {
			log.error("根据班组code,查询班组成员code", e);
			throw new BusinessException("根据班组code,查询班组成员code", e);
		}
	}

	/**
	 * 这个人是否是这个班组的班组长
	 * @see com.zssq.service.ITeamMemberService#isTeamLeader(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTeamLeader(String teamCode, String userCode) throws BusinessException {
		try {
			TeamMember teamMember = new TeamMember();
			teamMember.setIsLeader(TeamConstants.BOOLEAN_TRUE);
			teamMember.setUserCode(userCode);
			teamMember.setTeamCode(teamCode);
			return teamMemberMapper.selectPageCount(teamMember) > 0;
		} catch (Exception e) {
			log.error("这个人是否是这个班组的班组长", e);
			throw new BusinessException("这个人是否是这个班组的班组长", e);
		}
	}

}


